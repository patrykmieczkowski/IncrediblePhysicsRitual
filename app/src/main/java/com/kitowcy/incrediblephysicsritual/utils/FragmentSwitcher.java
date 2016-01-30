package com.kitowcy.incrediblephysicsritual.utils;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class FragmentSwitcher {

    public static void switchToFragment(AppCompatActivity activity, Fragment fragment, int placeHolderResource) {

        if (activity.getApplicationContext() != null && fragment != null) {

            activity.getFragmentManager()
                    .beginTransaction()
                    .replace(placeHolderResource, fragment)
                    .commit();
        }

    }
}