package com.example.kaan.pigfinal;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int score_p1;
    private int score_p2;
    private TextView p1;
    private TextView p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        score_p1 = intent.getIntExtra("score_p1", 0);
        score_p2 = intent.getIntExtra("score_p2", 0);
        p1= (TextView) findViewById(R.id.p1);
        p1.setText("P1: " +Integer.toString(score_p1));
        p2= (TextView) findViewById(R.id.p2);
        p2.setText("P2: "+Integer.toString(score_p2));
        //Toast.makeText(this, "The score is: " + score_p1, Toast.LENGTH_LONG).show();
        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();


            }
        });

        hold=(Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,Player2.class);
                intent.putExtra("score_p1", score_p1);
                intent.putExtra("score_p2", score_p2);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                /*AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("You Won!");
                alertDialog.setMessage("Niggaaaaaaaa");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();*/

            }
        });

        die1=(FrameLayout) findViewById(R.id.die1);
        die2=(FrameLayout) findViewById(R.id.die2);
    }

    public void rollDice(){
        int val1 = 1 +(int)(6*Math.random());
        int val2 = 1 + (int)(6*Math.random());
        setDie(val1, die1);
        setDie(val2, die2);
        Intent intent = new Intent(MainActivity.this,Player2.class);
        if(val1==1 || val2==1){

            intent.putExtra("score_p2", score_p2);
            intent.putExtra("score_p1", score_p1);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
        else{
            score_p1 += val1+val2;
            if(score_p1>=100){
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("You Won!");
                alertDialog.setMessage("Niggaaaaaaaa");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            intent.putExtra("score_p1", score_p1);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        }
    }

    @TargetApi(16)
    public void setDie(int value, FrameLayout layout){
        Drawable pic = null;
        switch(value){
            case 1:
                pic=getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
