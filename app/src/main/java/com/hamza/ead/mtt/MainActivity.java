package com.hamza.ead.mtt;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    public int numofrounds= 0;
   public List<Integer> scores = new ArrayList<>();
    public List<Integer> latencies = new ArrayList<>();
    private Button button7;
    private TextView resultText;
    private Timer timer;
    private TimerTask timerTask;
    boolean gameover =false;
    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resultText.setText("Hello, " + editText.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button7 = (Button) findViewById(R.id.button7);
        resultText = (TextView) findViewById(R.id.result);

        final List<Integer> sum = new ArrayList<>();
        final View arr1 = findViewById(R.id.imageView1);
        final View arr2 = findViewById(R.id.imageView3);
        final TextView tv1 = (TextView) findViewById(R.id.textView);
        final TextView tv2 = (TextView) findViewById(R.id.textView2);
        final Button button = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button4);

        tv2.setText("ROUND: " + numofrounds);
        try {

            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            numofrounds++;

                            tv2.setText("ROUND: " + numofrounds);
                            final int num2 = (Math.random() < 0.5) ? 0 : 1;
                            Random r1 = new Random();
                            final int num1 = r1.nextInt(3) + 0;

                            arr1.setRotation(0);
                            arr2.setRotation(0);
                            // This code will always run on the UI thread, therefore is safe to modify UI elements.
                            if (num2 == 1) {


                                tv1.setText("LOCATION");
                            } else {
                                tv1.setText("DIRECTION");
                            }
                            if (num1 == 0) {
                                Log.d("RUN FUNC", "reached: " + num1);
                                arr1.setRotation(0);
                                arr1.setVisibility(View.VISIBLE);
                                arr2.setVisibility(View.INVISIBLE);
                            }
                            if (num1 == 1) {
                                Log.d("RUN FUNC", "reached: " + num1);
                                arr1.setRotation(180);
                                arr1.setVisibility(View.VISIBLE);
                                arr2.setVisibility(View.INVISIBLE);
                            }
                            if (num1 == 2) {
                                Log.d("RUN FUNC", "reached: " + num1);
                                arr1.setRotation(+0);
                                arr1.setVisibility(View.INVISIBLE);
                                arr2.setVisibility(View.VISIBLE);
                            }
                            if (num1 == 3) {
                                Log.d("RUN FUNC", "reached: " + num1);
                                arr1.setRotation(180);
                                arr1.setVisibility(View.INVISIBLE);
                                arr2.setVisibility(View.VISIBLE);
                            }
                            button.setOnClickListener(new OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    if(num1==0) {
                                        if (num2==0) {
                                            scores.add(1);
                                        }
                                        else{
                                            scores.add(0);
                                        }
                                    }
                                    if(num1==1) {
                                        if (num2==0) {
                                            scores.add(0);
                                        }
                                        else{
                                            scores.add(1);
                                        }

                                    }
                                    if(num1==2) {
                                        if (num2==0) {
                                            scores.add(1);
                                        }
                                        else{
                                            scores.add(0);
                                        }

                                    }
                                    if(num1==3) {
                                        if (num2==0) {
                                            scores.add(0);
                                        }
                                        else{
                                            scores.add(1);
                                        }

                                    }

                                    showInputDialog();
                                }
                            });

                            button2.setOnClickListener(new View.OnClickListener() {

                                public void onClick(View v) {
                                    if(num1==0) {
                                        if (num2==0) {
                                            scores.add(0);
                                        }
                                        else{
                                            scores.add(1);
                                        }
                                    }
                                    if(num1==1) {
                                        if (num2==0) {
                                            scores.add(1);
                                        }
                                        else{
                                            scores.add(0);
                                        }

                                    }
                                    if(num1==2) {
                                        if (num2==0) {
                                            scores.add(0);
                                        }
                                        else{
                                            scores.add(1);
                                        }

                                    }
                                    if(num1==3) {
                                        if (num2==0) {
                                            scores.add(1);
                                        }
                                        else{
                                            scores.add(0);
                                        }

                                    }
                                    Log.d("RUN FUNC", "works " + num2);
                                }
                            });

                        }
                    });
                }
            };
            timer.schedule(timerTask, 2000, 2000);
        } catch (IllegalStateException e) {
            android.util.Log.i("Damn", "resume error");
        }
    }


     protected void onStart(){
         super.onStart();


    }
    public void onPause(){
        super.onPause();
        timer.cancel();

    }

    protected void onResume(){
        super.onResume();


        /*View arr1 = findViewById(R.id.imageView1);
        arr1.setRotation(arr1.getRotation() + 180);

            int arrowState = gennum();
            Log.d("AdebugTag", "Value: " + Integer.toString(arrowState));*/
    }



    };






