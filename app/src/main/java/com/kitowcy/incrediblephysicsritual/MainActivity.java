package com.kitowcy.incrediblephysicsritual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kitowcy.incrediblephysicsritual.fragments.CoilGameFragment;
import com.kitowcy.incrediblephysicsritual.fragments.StartScreenFragment;
import com.kitowcy.incrediblephysicsritual.utils.Config;
import com.kitowcy.incrediblephysicsritual.utils.FragmentSwitcher;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private int selectedFragment = Config.FRAGTMENT_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showStartingFragment();
    }

    @Override
    public void onBackPressed() {
        if (selectedFragment != Config.FRAGTMENT_START) {
            setFragment(Config.FRAGTMENT_START);
        } else {
            super.onBackPressed();
        }
    }

    private void showStartingFragment() {
        Log.d(TAG, "showStartingFragment()");

        FragmentSwitcher.switchToFragment(this, StartScreenFragment.newInstace(), R.id.main_placeholder);
    }

    public void setFragment(int fragmentId) {

        switch (fragmentId) {
            case Config.FRAGTMENT_START:
                selectedFragment = Config.FRAGTMENT_START;
                FragmentSwitcher.switchToFragment(this, StartScreenFragment.newInstace(), R.id.main_placeholder);
                break;
            case Config.FRAGTMENT_COIL_GAME:
                selectedFragment = Config.FRAGTMENT_COIL_GAME;
                FragmentSwitcher.switchToFragment(this, CoilGameFragment.newInstace(), R.id.main_placeholder);
                break;
            default:
                selectedFragment = Config.FRAGTMENT_START;
                FragmentSwitcher.switchToFragment(this, StartScreenFragment.newInstace(), R.id.main_placeholder);
                break;
        }

    }

}
