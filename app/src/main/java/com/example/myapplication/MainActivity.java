package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winingPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive= true;
    public void dropin(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter =Integer.parseInt( counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(360).setDuration(300);
            for (int[] winingPos : winingPos) {
                if (gameState[winingPos[0]] == gameState[winingPos[1]] && gameState[winingPos[1]] == gameState[winingPos[2]] && gameState[winingPos[0]] != 2) {
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                    Button playAgainBUtton = (Button) findViewById(R.id.playAgainButton);
                    TextView winerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winerTextView.setText(winner+ " has won!");
                    playAgainBUtton.setVisibility(View.VISIBLE);
                    winerTextView.setVisibility(View.VISIBLE);


                }
            }
        }
    }

    public  void playAgain(View view){
        Button playAgainBUtton = (Button) findViewById(R.id.playAgainButton);
        TextView winerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainBUtton.setVisibility(View.VISIBLE);
        winerTextView.setVisibility(View.VISIBLE);
        GridLayout gridLayout= (GridLayout)  findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
           ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length;i++){
            gameState[i]=2;
        }
         activePlayer=0;
         gameActive= true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}