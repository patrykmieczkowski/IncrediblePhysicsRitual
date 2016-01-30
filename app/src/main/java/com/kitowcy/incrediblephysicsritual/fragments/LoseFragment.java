package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class LoseFragment extends Fragment {

    @Bind(R.id.score_text)
    TextView scoreText;

    public static LoseFragment newInstace() {
        LoseFragment myFragment = new LoseFragment();
        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lost_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MainActivity mainActivity = ((MainActivity) getActivity());
        int levelScore = mainActivity.questionNumber;
        String textString = "Score: " + String.valueOf(levelScore);
        scoreText.setText(textString);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("data_loading", Context.MODE_PRIVATE);
        int currentBest = sharedPref.getInt("highscore", 0);

        if (levelScore > currentBest) {
            sharedPref.edit().putInt("highscore", levelScore).apply();
        }


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mainActivity.questionNumber = 0;
                mainActivity.setFragment(Config.FRAGTMENT_START);
            }
        }, 2000);

    }
}
