package com.kitowcy.incrediblephysicsritual.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kitowcy.incrediblephysicsritual.MainActivity;
import com.kitowcy.incrediblephysicsritual.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class QuestionBaseFragment extends Fragment {

    @Bind(R.id.first_image)
    ImageView firstImage;

    @Bind(R.id.second_image)
    ImageView secondImage;

    @Bind(R.id.third_image)
    ImageView thirdImage;

    @Bind(R.id.question_text)
    TextView questionText;

    public static QuestionBaseFragment newInstace() {
        QuestionBaseFragment myFragment = new QuestionBaseFragment();
        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_base_fragment, container, false);
        ButterKnife.bind(this, view);

        firstImage.setImageResource(R.drawable.einst);
        secondImage.setImageResource(R.drawable.tesla);
        thirdImage.setImageResource(R.drawable.student);

        questionText.setText("I never think of the future.\nIt comes soon enough.");

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) getActivity()).callTimer();

    }
}
