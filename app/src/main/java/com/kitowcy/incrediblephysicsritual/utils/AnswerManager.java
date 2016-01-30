package com.kitowcy.incrediblephysicsritual.utils;

import com.kitowcy.incrediblephysicsritual.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class AnswerManager {

    public static List<Integer> getRandomCharacter(int correctAnswer) {

        int first = giveMeAnswerResource(correctAnswer);
        int second = giveMeAnswerResource(new Random().nextInt(2));
        int third = giveMeAnswerResource(new Random().nextInt(2));

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(first);
        arrayList.add(second);
        arrayList.add(third);

        return arrayList;
    }

    public static int giveMeAnswerResource(int resource) {

        switch (resource) {
            case 0:
                return R.drawable.einst;
            case 1:
                return R.drawable.tesla;
            case 2:
                return R.drawable.newton;
            case 3:
                return R.drawable.koperek;
            case 4:
                return R.drawable.curie;
            case 5:
                return R.drawable.szrodinger;
            case 6:
                return R.drawable.edison;
            case 7:
                return R.drawable.student;
            default:
                return R.drawable.einst;
        }
    }
}
