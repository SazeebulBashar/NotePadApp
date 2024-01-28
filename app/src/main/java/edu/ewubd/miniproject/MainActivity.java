package edu.ewubd.miniproject;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton, pauseButton, resetButton;

    private long startTime = 0;
    private long elapsedTime = 0;
    private boolean isRunning = false;

    private Button todoBtn,stopwatchBtn;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateTimer();
            handler.postDelayed(this, 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        todoBtn  = findViewById(R.id.todo);
//        stopwatchBtn = findViewById(R.id.stopwatch);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        todoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ToDo.class);
                startActivity(i);
            }
        });
    }

    private void startTimer() {
        if (!isRunning) {
            if (elapsedTime == 0) {
                startTime = System.currentTimeMillis();
            } else {
                startTime = System.currentTimeMillis() - elapsedTime;
            }
            handler.postDelayed(runnable, 1);
            isRunning = true;
            if(startButton.getText().toString().equalsIgnoreCase("resume")){
                startButton.setText("Start");
            }
        }
    }

    private void pauseTimer() {
        if (isRunning) {
            handler.removeCallbacks(runnable);
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
            if(startButton.getText().toString().equalsIgnoreCase("start")){
                startButton.setText("Resume");
            }
        }
    }

    private void resetTimer() {
        handler.removeCallbacks(runnable);
        startTime = 0;
        elapsedTime = 0;
        isRunning = false;
        String time = String.format("%02d:%02d:%03d", 0, 0, 0);
        timerTextView.setText(time);
        startButton.setText("Start");
    }

    private void updateTimer() {
        long currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - startTime;

        int minutes = (int) (elapsedTime / (1000 * 60));
        int seconds = (int) ((elapsedTime / 1000) % 60);
        int millis = (int) (elapsedTime % 1000);

        String time = String.format("%02d:%02d:%03d", minutes, seconds, millis);
        timerTextView.setText(time);
    }
}
