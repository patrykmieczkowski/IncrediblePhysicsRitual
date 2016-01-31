package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kitowcy.incrediblephysicsritual.MainActivity;
import com.kitowcy.incrediblephysicsritual.R;
import com.kitowcy.incrediblephysicsritual.utils.Config;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class StartScreenFragment extends Fragment {

    public static final String TAG = StartScreenFragment.class.getSimpleName();

    public static StartScreenFragment newInstace() {
        StartScreenFragment myFragment = new StartScreenFragment();
        return myFragment;
    }

    @Bind(R.id.score_text_main)
    TextView scoreTextMain;

    @Bind(R.id.transmutation_circle)
    ImageView transmutationCircle;

    @Bind(R.id.scoring_hint_text)
    TextView scoringHintText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_screen_fragment, container, false);
        Log.d(TAG, "onCreateView StartScreenFragment");
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("data_loading", Context.MODE_PRIVATE);
        int currentBest = sharedPref.getInt("highscore", 0);

        if (currentBest != 0) {
            scoreTextMain.setText(String.valueOf(currentBest));
            setImageForTransmutationCircle(currentBest);
        }

    }

    @OnClick(R.id.main_button_play)
    public void startPlay() {
        Log.d(TAG, "startPlay()");
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).prepareQuestions();
            ((MainActivity) getActivity()).setFragment(Config.FRAGMENT_QUESTION_BASE);
        }
    }

    @OnClick(R.id.button_team)
    public void meetTheTeam() {
        ((MainActivity) getActivity()).setFragment(Config.FRAGMENT_MEET_THE_TEAM);
    }

    private void setImageForTransmutationCircle(int score) {

        String scorString = "";
        if (score < 5) {
            scorString = "Answer to 5 questions in raw to unlock next character!";
        } else if (score >= 5 && score < 10) {
            transmutationCircle.setImageResource(R.drawable.circle_1);
            scorString = "Answer to 10 questions in raw to unlock next character!";
        } else if (score >= 6 && score <= 14) {
            transmutationCircle.setImageResource(R.drawable.circle_2);
            scorString = "Answer to 15 questions in raw to unlock next character!";
        } else if (score >= 15 && score <= 29) {
            transmutationCircle.setImageResource(R.drawable.circle_3);
            scorString = "Answer to 30 questions in raw to unlock next character!";
        } else if (score >= 30 && score <= 49) {
            transmutationCircle.setImageResource(R.drawable.circle_4);
            scorString = "Answer to 50 questions in raw to unlock next character!";
        } else if (score >= 50 && score <= 99999) {
            transmutationCircle.setImageResource(R.drawable.circle_5);
            scorString = "Congratulations! You summoned Cthulhu!";
        }

        scoringHintText.setText(scorString);
    }
}
