package com.example.fitnessproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ActivityTwo extends AppCompatActivity {
    private Button nextButton;
    private TextView exeDisplay;
    private TextView timerDisplay;
    private ImageView imageView;
    private Button pauseButton;
    MediaPlayer ring;
    int exeIndex = 0;
    ArrayList<ExeDatatype> exeList;
    ExeDatatype exercise;
    CountDownTimer countDownTimer;
    long timeLeft;
    private Intent intent;
    boolean isRunning = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);


        exeDisplay = findViewById(R.id.exeDisplay);
        timerDisplay = findViewById(R.id.timerDisplay);
        imageView = findViewById(R.id.imageView3);
        pauseButton = findViewById(R.id.pauseButton);


        ring = MediaPlayer.create(this, R.raw.timer_bell);
        exeList = (ArrayList<ExeDatatype>) getIntent().getSerializableExtra("key");
        exercise = exeList.get(exeIndex);
        timeLeft = Long.parseLong(exercise.getReps());


     /*   if(exeIndex < exeList.size()){
            Log.d("test", "exe name "+ exercise.getExeName() +"exe img "+exercise.getImg()+"exe reps "+exercise.getReps());
            exeIndex ++;
            Toast.makeText(ActivityTwo.this, "exeIndex "+exeIndex , Toast.LENGTH_SHORT).show();
        } */

        // to add break to the list
        for (int i = 1; i < exeList.size(); i += 2) {
            exeList.add(i, new ExeDatatype("break", R.drawable.break_img , "3000", true));
        }

        exetimer(timeLeft);


    }

    public void exetimer(long time) {

        if (exercise.isUseTimer()) {
            countDownTimer = new CountDownTimer(timeLeft, 100) {
                @Override
                public void onTick(final long millisUntilFinished) {
                    timeLeft = millisUntilFinished;
                    timerDisplay.setText(""+String.format("%d : %d",
                            TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    imageView.setImageResource(exercise.getImg());
                    exeDisplay.setText(exercise.getExeName());

                    pauseButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timeLeft = millisUntilFinished;
                            if (pauseButton.getText().equals("pause")) {
                               // Toast.makeText(ActivityTwo.this, "pause", Toast.LENGTH_SHORT).show();
                                pauseButton.setText("resume");
                                countDownTimer.cancel();
                            } else if (pauseButton.getText().equals("resume")) {
                               // Toast.makeText(ActivityTwo.this, "resume", Toast.LENGTH_SHORT).show();
                                pauseButton.setText("pause");
                                exetimer(timeLeft);
                            }
                        }
                    });
                }

                @Override
                public void onFinish() {
                    ring.start();
                    if (!((exeIndex + 1) >= exeList.size())) {
                        exeIndex++;
                        exercise = exeList.get(exeIndex);
                        timeLeft = Long.parseLong(exercise.getReps());
                     //   Toast.makeText(ActivityTwo.this, "exeIndex " + exeIndex, Toast.LENGTH_SHORT).show();
                        exetimer(timeLeft);
                    } else {
                      //  Toast.makeText(ActivityTwo.this, "done", Toast.LENGTH_SHORT).show();
                        intent = new Intent(ActivityTwo.this, FinalActivity.class);
                        startActivity(intent);
                    }

                }
            }.start();  //count ends here


        } else if (!exercise.isUseTimer()) {
            pauseButton.setText("next");
            imageView.setImageResource(exercise.getImg());
            exeDisplay.setText(exercise.getExeName());
            timerDisplay.setText("" + exercise.getReps());
            if (!((exeIndex + 1) >= exeList.size())) {
                pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauseButton.setText("pause");
                        exeIndex++;
                        exercise = exeList.get(exeIndex);
                        exetimer(timeLeft);
                    }
                });
            } else {
              //  Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
                intent = new Intent(ActivityTwo.this, FinalActivity.class);
                startActivity(intent);
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        ring.stop();
        countDownTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ring.stop();
        countDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTwo.this);
        builder.setMessage("Do you want to quit workout ?");
        //builder.setTitle("quitter");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                                ring.stop();
                                countDownTimer.cancel();
                            }
                        });builder.setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

}





















