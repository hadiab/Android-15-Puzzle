package com.example.hipro.puzzle15;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PuzzleActivity extends AppCompatActivity {

    TextView[] textArray;
    GameBoard gameBoard;
    Button newBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        textArray = new TextView[16];
        newBtn = (Button) findViewById(R.id.newgame);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoard.initGame();
            }
        });

        // Get all text view for the ui
        for(int i = 0; i < textArray.length; i++) {
            // Get the text view dynamically
            String buttonID = "textView" + (i+1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());

            // Push the text view into the array
            textArray[i] = (TextView) findViewById(resID);
        }

        gameBoard = new GameBoard(textArray);
        gameBoard.initGame();
    }

    public void onClick(View v) {
        // Get the clicked box by tag
        int tag = Integer.parseInt(v.getTag().toString());
        TextView box = textArray[tag];

        Log.i("Tag", "==> You clicked text view with tag: " + tag);

        // Check for move
        gameBoard.moveBox(box, tag);

        // Check for winning
        if(gameBoard.checkWinning()) {
            Log.i("Tag", "*** You Won! ***");
            Toast.makeText(this, "You win!!", Toast.LENGTH_SHORT).show();
        }
    }
}
