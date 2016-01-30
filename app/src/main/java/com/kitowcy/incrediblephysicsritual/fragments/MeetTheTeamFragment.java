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
public class MeetTheTeamFragment extends Fragment {

    public static MeetTheTeamFragment newInstace() {
        MeetTheTeamFragment myFragment = new MeetTheTeamFragment();
        return myFragment;
    }

    @Bind(R.id.meet_the_team_img)
    ImageView meetTheTeam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meet_the_team_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meetTheTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(Config.FRAGTMENT_START);
            }
        });
    }
}