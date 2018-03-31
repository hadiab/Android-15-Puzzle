package com.example.hipro.puzzle15;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by hipro on 29/03/2018.
 */

public class GameBoard {

    private TextView textArray[];
    private int boxColor = Color.parseColor("#4277ce");
    private int emptyColor = Color.parseColor("#eeeeee");

    public GameBoard(TextView[] textArray) {
        this.textArray = textArray;
    }

    public void initGame() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random rand = new Random();

        // Shuffle array randomly
        for(int i = 0; i < array.length; i++) {
            int n = rand.nextInt(array.length);
            int tmp = array[i];
            array[i] = array[n];
            array[n] = tmp;
        }

        // Set text and color for each box
        for(int i = 0; i < array.length; i++) {
            TextView textView = textArray[i];
            
            // Empty box
            if(array[i] == 0) {
                textView.setText("");
                textView.setBackgroundColor(emptyColor);
            } 
            // Box with number
            else {
                textView.setText("" + array[i]);
                textView.setBackgroundColor(boxColor);
            }
        }
    }

    public void moveBox(TextView box, int tag) {
        // check for winning
        if(this.checkWinning()) return;

        // check if the box is not empty
        if(box.getText().length() != 0) {
            Log.i("Tag", "Not Empty");

            /*
            * Check for available move
            */

            // Right move
            if(tag + 1 < textArray.length && textArray[tag + 1].getText().length() == 0 && tag != 3 && tag != 7 && tag != 11) {
                Log.i("Tag", "Can move to right");

                // Replace between clicked box with empty box
                TextView emptyBox = textArray[tag + 1];
                emptyBox.setText(box.getText());
                emptyBox.setBackgroundColor(boxColor);

                box.setText("");
                box.setBackgroundColor(emptyColor);
            }

            // Left move
            else if(tag - 1 >= 0 && textArray[tag - 1].getText().length() == 0 && tag != 4 && tag != 8 && tag != 12) {
                Log.i("Tag", "Can move to left");

                // Replace between clicked box with empty box
                TextView emptyBox = textArray[tag - 1];
                emptyBox.setText(box.getText());
                emptyBox.setBackgroundColor(boxColor);

                box.setText("");
                box.setBackgroundColor(emptyColor);
            }

            // Down move
            else if(tag + 4 < textArray.length && textArray[tag + 4].getText().length() == 0) {
                Log.i("Tag", "Can move to down");

                // Replace between clicked box with empty box
                TextView emptyBox = textArray[tag + 4];
                emptyBox.setText(box.getText());
                emptyBox.setBackgroundColor(boxColor);

                box.setText("");
                box.setBackgroundColor(emptyColor);
            }

            // Down move
            else if(tag - 4 >= 0 && textArray[tag - 4].getText().length() == 0) {
                Log.i("Tag", "Can move to up");

                // Replace between clicked box with empty box
                TextView emptyBox = textArray[tag - 4];
                emptyBox.setText(box.getText());
                emptyBox.setBackgroundColor(boxColor);

                box.setText("");
                box.setBackgroundColor(emptyColor);
            }
        }
    }

    // Check for winning position
    public boolean checkWinning() {
        for(int i = 0; i < textArray.length; i++) {
            if(textArray[i].getText().length() == 0 && i != textArray.length - 1) return false;

            if(textArray[i].getText().length() > 0) {
                int textVal = Integer.parseInt(textArray[i].getText().toString());
                int tagVal = Integer.parseInt(textArray[i].getTag().toString()) + 1;

                Log.i("Tag", "==> Check winning: " + textVal + ", " + tagVal);

                if(textVal != tagVal) {
                    return false;
                }
            }
        }

        return true;
    }
}
