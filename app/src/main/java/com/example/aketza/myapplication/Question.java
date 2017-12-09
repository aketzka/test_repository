package com.example.aketza.myapplication;

/**
 * Created by aketza on 09.12.17.
 */

public class Question {
    boolean mAnswerIsTrue;
    int mQuestionText;

    Question(int questionText, boolean answerIsTrue){
        mQuestionText = questionText;
        mAnswerIsTrue = answerIsTrue;
    }

    public boolean isAnswerIsTrue() {
        return mAnswerIsTrue;
    }

    public void setAnswerIsTrue(boolean answerIsTrue) {
        mAnswerIsTrue = answerIsTrue;
    }

    public int getQuestionText() {
        return mQuestionText;
    }

    public void setQuestionText(int questionText) {
        mQuestionText = questionText;
    }
}
