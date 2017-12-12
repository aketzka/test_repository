package com.example.aketza.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main(Quiz)Activity";
    private static final String KEY_INDEX = "index";
    private TextView mQuestionText;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mPrevButton;
    private Button mNextButton;
    private Question[] mQuestionBank = {
            new Question(R.string.question_africa, false),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_oceans, true)};
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "creation");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);


        mQuestionText = (TextView)findViewById(R.id.question_text);
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mPrevButton = (Button)findViewById(R.id.prev_button);
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

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex-1+mQuestionBank.length)%mQuestionBank.length;
                refreshQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                refreshQuestion();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "saving index in bundle");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "starting");
        super.onStart();
    }

    @Override
    public void onPause(){
        Log.d(TAG, "taking pause");
        super.onPause();
    }

    @Override
    public void onResume(){
        Log.d(TAG, "resuming");
        super.onResume();
    }

    @Override
    public void onStop(){
        Log.d(TAG, "stopping");
        super.onStop();
    }

    @Override
    public void onDestroy(){
        Log.d(TAG, "destroing");
        super.onDestroy();
    }


    private void refreshQuestion(){
        Question question = mQuestionBank[mCurrentIndex];
        mQuestionText.setText(question.getQuestionText());
    }

    private void checkAnswer(boolean userPressedTrue){
        Question question = mQuestionBank[mCurrentIndex];
        int toastId = (userPressedTrue ^ question.isAnswerIsTrue()) ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(MainActivity.this, toastId, Toast.LENGTH_SHORT).show();
    }
}
