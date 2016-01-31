package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    @Bind(R.id.story_image)
    ImageView storyImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.story_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final long delay = 3000;

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) getActivity()).setFragment(Config.FRAGMENT_QUESTION_BASE);
            }
        }, delay);

    }
}
