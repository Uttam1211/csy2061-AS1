package com.example.thechristmatic.rollDice;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thechristmatic.R;

import java.util.Random;

public class RollDiceActivity extends AppCompatActivity {

    private ImageView diceImage;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);

        diceImage = findViewById(R.id.diceImage);
        random = new Random();

        diceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }

    private void rollDice() {
        int randomNumber = random.nextInt(6) + 1;
        switch (randomNumber) {
            case 1:
                diceImage.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                diceImage.setImageResource(R.drawable.dice_6);
                break;
        }
    }
}
