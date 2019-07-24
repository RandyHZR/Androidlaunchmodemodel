package pro.bigyellow.exams;


import android.app.Instrumentation;

import android.content.Intent;
import android.os.Debug;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {
    double[][] path;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        SimpleDateFormat date =
                new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String logDate = date.format(new Date());
// Applies the date and time to the name of the trace log.
        Debug.startMethodTracing(
                "sample-" + logDate);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
               // Debug.startMethodTracing("burgleaf");//关键语句

                Intent mainIntent = new Intent(MainActivity.this, cover.class);
                Intent thirdIntent = new Intent(MainActivity.this, nextcover.class);
                //mainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);



                Random r = new Random();
                try {
                    testDOMGetForms();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

                double i = r.nextDouble();

                double ac = 0.0;
                int j;
                for (j = 1; j <= path[99][99]; j++) {
                    ac = ac + path[1][j];
                    if (ac >= i) break;
                }
                switch (j) {
                    case 1:
                        onBack();
                        break;
                    case 2:
                        Intent coverintent = new Intent(MainActivity.this,cover.class);
                        coverintent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        MainActivity.this.startActivity(coverintent);
                        MainActivity.this.finish();
                        break;
                    case 3:
                        thirdIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        MainActivity.this.startActivity(thirdIntent);
                        MainActivity.this.finish();
                        break;
                }
            }
        }, 500);

        }
    public void testDOMGetForms() throws Throwable {

        InputStream inStream = getAssets().open("action.xml");

       path = DomActivityService.getForms(inStream);

       }




    public void onBack(){
        new Thread(){
            public void run() {
                try{
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                }
                catch (Exception e) {
                    Log.e("Exception when onBack", e.toString());
                }
            }
        }.start();
    }

    }

