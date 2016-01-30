package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kitowcy.incrediblephysicsritual.MainActivity;
import com.kitowcy.incrediblephysicsritual.R;
import com.kitowcy.incrediblephysicsritual.model.RealmQuestion;
import com.kitowcy.incrediblephysicsritual.utils.AnswerManager;
import com.kitowcy.incrediblephysicsritual.utils.Config;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class QuestionBaseFragment extends Fragment {

    public static final String TAG = QuestionBaseFragment.class.getSimpleName();

    @Bind(R.id.first_image)
    ImageView firstImage;

    @Bind(R.id.second_image)
    ImageView secondImage;

    @Bind(R.id.third_image)
    ImageView thirdImage;

    @Bind(R.id.question_text)
    TextView questionText;

    @Bind(R.id.seekBar)
    SeekBar seekBar;

    @Bind(R.id.score_text)
    TextView scoreText;

    public static QuestionBaseFragment newInstace() {
        QuestionBaseFragment myFragment = new QuestionBaseFragment();
        return myFragment;
    }

    rx.Subscription seekBarSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_base_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MainActivity mainActivity = ((MainActivity) getActivity());
        int selectedQ = mainActivity.mySmallList.get(mainActivity.questionNumber);
        Realm realm = Realm.getInstance(getActivity());
        RealmQuestion realmQuestion = realm.where(RealmQuestion.class).equalTo("id", selectedQ).findFirst();

        int correctanswer = realmQuestion.getAnswer();
        List<Integer> mylist = new ArrayList<>();
        for (int h = 0; h < 3; h++) {
            mylist.add(h);
        }
        Log.d(TAG, "onViewCreated: list size before " + mylist.size());
        mylist.remove(correctanswer);
        Log.d(TAG, "onViewCreated: after deletion" + mylist.size());
        Collections.shuffle(mylist);
        int secondN = mylist.get(0);
        int thirdN = mylist.get(1);

        int first = AnswerManager.giveMeAnswerResource(correctanswer);
        int second = AnswerManager.giveMeAnswerResource(secondN);
        int third = AnswerManager.giveMeAnswerResource(thirdN);

        firstImage.setImageResource(first);
        secondImage.setImageResource(second);
        thirdImage.setImageResource(third);

        scoreText.setText(String.valueOf(mainActivity.questionNumber));
        questionText.setText(realmQuestion.getQuestion());

        realm.close();

        seekBar.getThumb().mutate().setAlpha(0);
        seekBar.setMax(5000);
//        ((MainActivity) getActivity()).callTimer();

        launchSeekBar(new TimeUpCallback() {
            @Override
            public void call() {
                mainActivity.questionNumber = 0;
                mainActivity.setFragment(Config.FRAGTMENT_START);
            }
        });
    }

    @Override
    public void onDestroy() {
        if (seekBarSubscription != null) {
            seekBarSubscription.unsubscribe();
            seekBar.setProgress(0);
        }
        super.onDestroy();
    }

    @OnClick(R.id.second_image)
    public void onImageClick() {
        ((MainActivity) getActivity()).questionNumber++;
        ((MainActivity) getActivity()).setFragment(Config.FRAGMENT_QUESTION_BASE);
    }

    private void launchSeekBar(@Nullable final TimeUpCallback endCallback) {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                seekBarSubscription = rx.Observable.interval(10, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Long>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "onCompleted: ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: " + e.getMessage());
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(Long aLong) {
//                                Log.d(TAG, "onNext: ");
                                seekBar.setProgress((int) (5000 - aLong * 10));
                                if (aLong > 499) {
                                    seekBarSubscription.unsubscribe();
                                    if (endCallback != null) endCallback.call();
                                }
                            }
                        });
            }
        }, 100);
    }
}
