package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kitowcy.incrediblephysicsritual.R;

import butterknife.Bind;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class CoilGameFragment extends Fragment {

    @Bind(R.id.piorun)
    ImageView piorun;

    public static CoilGameFragment newInstace() {
        CoilGameFragment myFragment = new CoilGameFragment();
        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coil_game_fragment, container, false);

        return view;
    }
}
