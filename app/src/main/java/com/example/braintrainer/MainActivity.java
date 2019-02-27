package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView showSum;
    TextView showTimer;
    TextView showScore;
    TextView showAnswer;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playButton;
    int answer;
    int turn;
    int correct;
    boolean timeNotOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSum = findViewById(R.id.showSum);
        showTimer = findViewById(R.id.showTimer);
        showScore = findViewById(R.id.showScore);
        showAnswer = findViewById(R.id.showAnswer);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playButton = findViewById(R.id.playButton);
        showSum.setVisibility(View.GONE);
        showTimer.setVisibility(View.GONE);
        showScore.setVisibility(View.GONE);
        showAnswer.setVisibility(View.GONE);
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        playButton.setVisibility(View.GONE);

    }

    public void go(View view) {
        showSum.setVisibility(View.VISIBLE);
        showTimer.setVisibility(View.VISIBLE);
        showScore.setVisibility(View.VISIBLE);
        showAnswer.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);
        Button go = findViewById(R.id.go);
        go.setVisibility(View.GONE);
        startPlay();
    }

    public void play(View view) {
        startPlay();
    }
    public void startPlay() {

        turn = 0;
        correct = 0;
        timeNotOver = true;
        showAnswer.setVisibility(View.GONE);
        if( playButton.getVisibility()==View.VISIBLE) {
            playButton.setVisibility(View.GONE);
        }
        showTimer.setText("30s");
        showScore.setText(correct+"/"+turn);
        new CountDownTimer(30000+100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                showTimer.setText(String.format("%02ds",(int)(millisUntilFinished/1000)));
            }

            @Override
            public void onFinish() {
                timeNotOver = false;
                showTimer.setText("00s");
                playButton.setVisibility(View.VISIBLE);
                showAnswer.setText("Your Score is "+correct + "/" + turn);
                correct=0;
                turn=0;
            }
        }.start();
        createQuestion();

    }

    public void createQuestion(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0;i<51;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        int first = list.get(0);
        int second = list.get(1);
        answer = first + second;
        showSum.setText(first+" + "+second);
        int random = (int)(Math.random()*3)+1;
        for(int i=2;i<5;i++) {
            if(list.get(i)==answer) {
                list.remove(i);
            }
        }
        switch (random){
            case 1:
                button1.setText(answer+"");
                button2.setText(list.get(2)+"");
                button3.setText(list.get(3)+"");
                button4.setText(list.get(4)+"");
                break;
            case 2:
                button2.setText(answer+"");
                button1.setText(list.get(2)+"");
                button3.setText(list.get(3)+"");
                button4.setText(list.get(4)+"");
                break;
            case 3:
                button3.setText(answer+"");
                button2.setText(list.get(2)+"");
                button1.setText(list.get(3)+"");
                button4.setText(list.get(4)+"");
                break;
            case 4:
                button4.setText(answer+"");
                button2.setText(list.get(2)+"");
                button3.setText(list.get(3)+"");
                button1.setText(list.get(4)+"");
                break;
        }


    }

    public void checkAnswer(View view) {
        if(timeNotOver) {
            Button clicked = findViewById(view.getId());
            turn++;
            if (Integer.parseInt((clicked.getText().toString())) == answer) {
                correct++;
                showAnswer.setText("Correct!");
            }
            else {
                showAnswer.setText("Wrong!");
            }
            showAnswer.setVisibility(View.VISIBLE);
            showScore.setText(correct + "/" + turn);
            createQuestion();
        }
    }
}
