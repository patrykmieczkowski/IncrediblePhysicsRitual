package com.kitowcy.incrediblephysicsritual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kitowcy.incrediblephysicsritual.fragments.StartScreenFragment;
import com.kitowcy.incrediblephysicsritual.utils.FragmentSwitcher;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showStartingFragment();
    }

    private void showStartingFragment() {
        Log.d(TAG, "showStartingFragment()");

        FragmentSwitcher.switchToFragment(this, StartScreenFragment.newInstace(), R.id.main_placeholder);
    }
}
