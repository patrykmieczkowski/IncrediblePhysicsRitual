package com.kitowcy.incrediblephysicsritual.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Patryk Mieczkowski on 30.01.16.
 */
public class RealmQuestion extends RealmObject {

    @PrimaryKey
    private int id;
    private String question;
    private int answer;

    public RealmQuestion() {
    }

    public RealmQuestion(int id, String question, int answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

}
