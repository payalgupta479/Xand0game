package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:xyello(X) 1:red(0)  2:empty
    int[] gameState={2,2,2,2,2,2,2,2,2};

    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameactive=true;

    int activePlayer=0;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;


            int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]== 2 && gameactive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.xyello);
                ;
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }


            counter.animate().translationYBy(1500).setDuration(5);

            for (int[] winningPositions : winningPositions) {

                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] && gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]] != 2) {

                    gameactive=false;

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "X";
                    } else {

                        winner = "0";
                    }


                    Button playagainbutton=(Button)findViewById(R.id.playAgainButton);

                    TextView winnertextview=(TextView)findViewById(R.id.winnertextView);

                    winnertextview.setText(winner + " HAS WON! ");

                    playagainbutton.setVisibility(View.VISIBLE);

                    winnertextview.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view){

        Button playagainbutton=(Button)findViewById(R.id.playAgainButton);

        TextView winnertextview=(TextView)findViewById(R.id.winnertextView);

        playagainbutton.setVisibility(View.INVISIBLE);

        winnertextview.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++) {

            ImageView counter=(ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++){

            gameState[i]=2;
        }



        activePlayer=0;

        gameactive=true;



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}