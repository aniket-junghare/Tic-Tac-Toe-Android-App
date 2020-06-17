package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer popup;

    // 0  - X
    // 1  - O
    // 2  - blank

    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};


    // winning positions
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
    public void playerTap(View view){

        popup.start();
        ImageView img = (ImageView)view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }


        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.red);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Green's Turn - Tap to Play");
            }else{
                img.setImageResource(R.drawable.green);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Red's Turn - Tap to Play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        // check if any player has won
        for(int[] winPosition: winPositions){
            String winner;
            if((gameState[winPosition[0]] == gameState[winPosition[1]] ) &&
                    (gameState[winPosition[1]] == gameState[winPosition[2]]) &&
                    gameState[winPosition[0]] != 2 ){
                // Somebody has won! - who?

                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winner = "Red has won!";

                }else{
                    winner = "Green has won!";
                }

                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winner);
            }
        }


    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.v0)).setImageResource(0);
        ((ImageView)findViewById(R.id.v1)).setImageResource(0);
        ((ImageView)findViewById(R.id.v2)).setImageResource(0);
        ((ImageView)findViewById(R.id.v3)).setImageResource(0);
        ((ImageView)findViewById(R.id.v4)).setImageResource(0);
        ((ImageView)findViewById(R.id.v5)).setImageResource(0);
        ((ImageView)findViewById(R.id.v6)).setImageResource(0);
        ((ImageView)findViewById(R.id.v7)).setImageResource(0);
        ((ImageView)findViewById(R.id.v8)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popup = MediaPlayer.create(MainActivity.this,R.raw.pop);
    }
}
