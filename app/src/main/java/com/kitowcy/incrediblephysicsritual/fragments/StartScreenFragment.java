package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kitowcy.incrediblephysicsritual.MainActivity;
import com.kitowcy.incrediblephysicsritual.R;
import com.kitowcy.incrediblephysicsritual.utils.Config;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_screen_fragment, container, false);
        Log.d(TAG, "onCreateView StartScreenFragment");
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.main_button_play)
    public void startPlay() {
        Log.d(TAG, "startPlay()");
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setFragment(Config.FRAGTMENT_COIL_GAME);
        }
    }
}
