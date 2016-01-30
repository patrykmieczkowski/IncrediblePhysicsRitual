package com.kitowcy.incrediblephysicsritual;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.kitowcy.incrediblephysicsritual.fragments.CoilGameFragment;
import com.kitowcy.incrediblephysicsritual.fragments.StartScreenFragment;
import com.kitowcy.incrediblephysicsritual.fragments.TimeUpCallback;
import com.kitowcy.incrediblephysicsritual.utils.Config;
import com.kitowcy.incrediblephysicsritual.utils.FragmentSwitcher;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private int selectedFragment = Config.FRAGTMENT_START;
    @Bind(R.id.seekBar)
    SeekBar seekBar;

    rx.Subscription seekBarSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    public void restartSeekbar(@Nullable final TimeUpCallback endCallback) {
        seekBar.setMax(5000);
        seekBar.getThumb().mutate().setAlpha(0);
//        seekBar.setColo
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                seekBarSubscription = rx.Observable.interval(10, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Long>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "onCompleted: ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: " + e.getMessage());
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(Long aLong) {
                                Log.d(TAG, "onNext: ");
                                seekBar.setProgress((int) (5000 - aLong * 10));
                                if (aLong > 499) {
                                    seekBarSubscription.unsubscribe();
                                    if (endCallback != null) endCallback.call();
                                }
                            }
                        });
            }
        }, 100);
    }

    boolean switcher;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        seekBarSubscription.unsubscribe();
        seekBar.setProgress(0);

        super.onStop();
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

    public void callTimer() {
        restartSeekbar(new TimeUpCallback() {
            @Override
            public void call() {
                switcher = !switcher;
                FragmentSwitcher
                        .switchToFragment(MainActivity.this,
                                switcher ?
                                        StartScreenFragment.newInstace()
                                        : CoilGameFragment.newInstace()
                                , R.id.main_placeholder);

            }
        });
    }
}
