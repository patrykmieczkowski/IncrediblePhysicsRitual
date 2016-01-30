package com.kitowcy.incrediblephysicsritual.utils;

import android.content.Context;

import com.kitowcy.incrediblephysicsritual.R;
import com.kitowcy.incrediblephysicsritual.model.RealmQuestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.realm.Realm;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class LoadData {

    public static void loadDataFromCsv(Context context, DataFromFileLoadedCallback dataFromFileLoadedCallback) {

        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.clear(RealmQuestion.class);

        InputStream inputStream = context.getResources().openRawResource(R.raw.data);

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String singleRecord = "";
        int counter = 0;

        try {
            while ((singleRecord = bufferedReader.readLine()) != null) {

                String[] values = singleRecord.split(";");
                realm.copyToRealmOrUpdate(new RealmQuestion(counter, values[0], Integer.valueOf(values[1])));
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        realm.commitTransaction();
        realm.close();

        dataFromFileLoadedCallback.finishedDataLoading();
    }

}
