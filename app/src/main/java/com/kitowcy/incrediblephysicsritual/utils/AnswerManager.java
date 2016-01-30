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
        int second = giveMeAnswerResource(new Random().nextInt(3 - 1) + 1);
        int third = giveMeAnswerResource(new Random().nextInt(3 - 1) + 1);

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
                return R.drawable.student;
            default:
                return R.drawable.einst;
        }
    }
}
