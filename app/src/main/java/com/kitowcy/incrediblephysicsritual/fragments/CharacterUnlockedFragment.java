package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
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
public class CharacterUnlockedFragment extends Fragment {

    public static CharacterUnlockedFragment newInstace() {
        CharacterUnlockedFragment myFragment = new CharacterUnlockedFragment();
        return myFragment;
    }

    @Bind(R.id.unlock_image)
    ImageView unlockImage;

    @Bind(R.id.unlock_text)
    TextView unlockText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unlock_character_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String textString = "";

        int score = ((MainActivity) getActivity()).questionNumber;
        if (score == Config.LEVEL_0) {
            unlockImage.setImageResource(R.drawable.unlock_einstein);
            textString = "Congratulations!\nYou unlocked Albert Einstein!\nAnswer to "
                    + String.valueOf(Config.LEVEL_1) + " questions now!";
            unlockText.setText(textString);
        } else if (score == Config.LEVEL_1) {
            unlockImage.setImageResource(R.drawable.unlock_curie);
            textString = "Congratulations!\nYou unlocked Marie Curie!\nAnswer to "
                    + String.valueOf(Config.LEVEL_2) + " questions now!";
            unlockText.setText(textString);
        } else if (score == Config.LEVEL_2) {
            unlockImage.setImageResource(R.drawable.unlock_newton);
            textString = "Congratulations!\nYou unlocked Isaac Newton!\nAnswer to "
                    + String.valueOf(Config.LEVEL_3) + " questions now!";
            unlockText.setText(textString);
        }

        final long delay = 2000;

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) getActivity()).setFragment(Config.FRAGMENT_QUESTION_BASE);
            }
        }, delay);

    }
}
