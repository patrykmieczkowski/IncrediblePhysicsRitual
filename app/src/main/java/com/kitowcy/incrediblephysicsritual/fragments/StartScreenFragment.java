package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kitowcy.incrediblephysicsritual.R;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class StartScreenFragment extends Fragment {

    public static StartScreenFragment newInstace() {
        StartScreenFragment myFragment = new StartScreenFragment();
        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_screen_fragment, container, false);

        return view;
    }
}
