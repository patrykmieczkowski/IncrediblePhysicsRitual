package com.kitowcy.incrediblephysicsritual;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kitowcy.incrediblephysicsritual.fragments.QuestionBaseFragment;
import com.kitowcy.incrediblephysicsritual.fragments.StartScreenFragment;
import com.kitowcy.incrediblephysicsritual.model.RealmQuestion;
import com.kitowcy.incrediblephysicsritual.utils.Config;
import com.kitowcy.incrediblephysicsritual.utils.DataFromFileLoadedCallback;
import com.kitowcy.incrediblephysicsritual.utils.FragmentSwitcher;
import com.kitowcy.incrediblephysicsritual.utils.LoadData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private int selectedFragment = Config.FRAGTMENT_START;

    public List<Integer> mySmallList = new ArrayList<>();
    public int questionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showStartingFragment();

        SharedPreferences sharedPref = getSharedPreferences("data_loading", Context.MODE_PRIVATE);
        if (!sharedPref.getBoolean("isLoaded", false)) {
            Log.d(TAG, "copying files to DB!");
            copyDataToRealm();
        } else {
            Realm realm = Realm.getInstance(this);
            List<RealmQuestion> realmQuestionList = realm.where(RealmQuestion.class).findAll();
            Log.d(TAG, "realm question list size: " + realmQuestionList.size());
            for (RealmQuestion realmQuestion : realmQuestionList) {
                Log.d(TAG, "RealmQuestion: " + realmQuestion.getAnswer() + ", " + realmQuestion.getQuestion());
            }
            realm.close();
        }
    }

    @Override
    public void onBackPressed() {
        if (selectedFragment != Config.FRAGTMENT_START) {
            setFragment(Config.FRAGTMENT_START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
//        if (seekBarSubscription != null) {
//            seekBarSubscription.unsubscribe();
//            seekBar.setProgress(0);
//        }

        super.onStop();
    }

    private void showStartingFragment() {
        Log.d(TAG, "showStartingFragment()");
        FragmentSwitcher.switchToFragment(this, StartScreenFragment.newInstace(), R.id.main_placeholder);
    }

    public void setFragment(int fragmentId) {

        switch (fragmentId) {
            case Config.FRAGTMENT_START:
                selectedFragment = Config.FRAGTMENT_START;
                FragmentSwitcher.switchToFragment(this, StartScreenFragment.newInstace(), R.id.main_placeholder);
                break;
            case Config.FRAGMENT_QUESTION_BASE:
                selectedFragment = Config.FRAGMENT_QUESTION_BASE;
                FragmentSwitcher.switchToFragment(this, QuestionBaseFragment.newInstace(), R.id.main_placeholder);
                break;
            default:
                selectedFragment = Config.FRAGTMENT_START;
                FragmentSwitcher.switchToFragment(this, StartScreenFragment.newInstace(), R.id.main_placeholder);
                break;
        }

    }

    private void copyDataToRealm() {
        Log.d(TAG, "copyDataToRealm");

        LoadData.loadDataFromCsv(this, new DataFromFileLoadedCallback() {
            @Override
            public void finishedDataLoading() {
                Log.d(TAG, "finishedDataLoading!");

                SharedPreferences sharedPref = getSharedPreferences("data_loading", Context.MODE_PRIVATE);
                sharedPref.edit().putBoolean("isLoaded", true).apply();

            }
        });

    }

    public void prepareQuestions() {

        Realm realm = Realm.getInstance(this);
        int mylistsize = realm.where(RealmQuestion.class).findAll().size();

        for (int x = 0; x < mylistsize; x++) {
            mySmallList.add(x);
        }

        Log.d(TAG, "prepareQuestions: smallList: " + mySmallList.toString());
        Collections.shuffle(mySmallList);
        Log.d(TAG, "prepareQuestions: smallList randomized " + mySmallList.toString());

    }

//    public void callTimer() {
//        restartSeekbar(new TimeUpCallback() {
//            @Override
//            public void call() {
//                switcher = !switcher;
//                FragmentSwitcher
//                        .switchToFragment(MainActivity.this,
//                                switcher ?
//                                        StartScreenFragment.newInstace()
//                                        : QuestionBaseFragment.newInstace()
//                                , R.id.main_placeholder);
//
//            }
//        });
//    }
//
//    public void restartSeekbar(@Nullable final TimeUpCallback endCallback) {
//        seekBar.setMax(5000);
//        seekBar.getThumb().mutate().setAlpha(0);
////        seekBar.setColo
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                seekBarSubscription = rx.Observable.interval(10, TimeUnit.MILLISECONDS)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<Long>() {
//                            @Override
//                            public void onCompleted() {
//                                Log.d(TAG, "onCompleted: ");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.d(TAG, "onError: " + e.getMessage());
//                                e.printStackTrace();
//                            }
//
//                            @Override
//                            public void onNext(Long aLong) {
////                                Log.d(TAG, "onNext: ");
//                                seekBar.setProgress((int) (5000 - aLong * 10));
//                                if (aLong > 499) {
//                                    seekBarSubscription.unsubscribe();
//                                    if (endCallback != null) endCallback.call();
//                                }
//                            }
//                        });
//            }
//        }, 100);
//    }
//
//    boolean switcher;

}
