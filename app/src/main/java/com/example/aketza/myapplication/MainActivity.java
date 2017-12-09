package com.example.aketza.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mQuestionText;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Question[] mQuestionBank = {
            new Question(R.string.question_africa, false),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_oceans, true)};
    int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionText = (TextView)findViewById(R.id.question_text);
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (Button)findViewById(R.id.next_button);

        refreshQuestion();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(view.getId() == mTrueButton.getId());
            }
        };
        mTrueButton.setOnClickListener(onClickListener);
        mFalseButton.setOnClickListener(onClickListener);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                refreshQuestion();
            }
        });
    }

    private void refreshQuestion(){
        Question question = mQuestionBank[mCurrentIndex];
        mQuestionText = (TextView)findViewById(R.id.question_text);
        mQuestionText.setText(question.getQuestionText());
    }

    private void checkAnswer(boolean userPressedTrue){
        Question question = mQuestionBank[mCurrentIndex];
        int toastId = (userPressedTrue ^ question.isAnswerIsTrue()) ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(MainActivity.this, toastId, Toast.LENGTH_SHORT).show();
    }
}
