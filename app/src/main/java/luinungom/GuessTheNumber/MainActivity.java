package luinungom.GuessTheNumber;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private int number;
    private int counter;
    private int score;
    private int highScore = 0;
    private Button checkButton;
    private EditText textBox;
    private TextView numericScoreText;
    private TextView numericHighScoreText;
    private TextView guessingText;
    private TextView symbolText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.constraintLayout);
        checkButton = findViewById(R.id.buttonCheck);
        Button playAgainButton = findViewById(R.id.buttonPlayAgain);
        textBox = findViewById(R.id.editTextNumber);
        numericScoreText = findViewById(R.id.numericScoreText);
        numericHighScoreText = findViewById(R.id.numericHighScoreText);
        guessingText = findViewById(R.id.guessinText);
        symbolText = findViewById(R.id.symbolText);

        resetValues();

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAllValues();
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logic();
            }
        });

    }

    /**
     * Generates a random number between 1 and 20.
     * @return int from 1 to 20.
     */
    private static int numberGenerator() {
        return (int) Math.floor(Math.random()*20+1);
    }

    /**
     * Resets several values to the initial status.
     */
    private void resetValues() {
        number = numberGenerator();
        checkButton.setEnabled(true);
        counter = 1;
        score = 20;
        numericScoreText.setText(String.valueOf(score));
        numericHighScoreText.setText(String.valueOf(highScore));
    }

    /**
     * Resets all values from resetValues() and several texts.
     */
    private void resetAllValues() {
        resetValues();
        textBox.setText("");
        layout.setBackgroundColor(getResources().getColor(R.color.darkGrey));
        guessingText.setText(R.string.start_guessing);
        symbolText.setText("?");
    }

    /**
     * Game's logic.
     */
    private void logic() {
        int guess = 0;
        try {
            guess = Integer.parseInt(textBox.getText().toString());
        }catch (NumberFormatException e) {
            guessingText.setText(R.string.no_number);
        }
        if (counter < 20) {
            if (guess == 0) {
                guessingText.setText(R.string.no_number);
            } else {
                counter ++;
                if (guess > number) {
                    guessingText.setText(R.string.tooHighValue);
                    numericScoreText.setText(String.valueOf(score));
                    textBox.setText(null);
                    score--;
                }
                if (guess < number) {
                    guessingText.setText(R.string.tooLowValue);
                    numericScoreText.setText(String.valueOf(score));
                    textBox.setText(null);
                    score--;
                }
                if (guess == number) {
                    guessingText.setText(R.string.success);
                    numericScoreText.setText(String.valueOf(score));
                    layout.setBackgroundColor(getResources().getColor(R.color.happyGreen));
                    checkButton.setEnabled(false);
                    symbolText.setText("!");
                    if (score > highScore) {
                        highScore = score;
                        numericHighScoreText.setText(String.valueOf(highScore));
                    }
                }
            }
        } else {
            guessingText.setText(R.string.gameOver);
            numericScoreText.setText("0");
            symbolText.setText("!");
            checkButton.setEnabled(false);
            layout.setBackgroundColor(getResources().getColor(R.color.sadRed));
        }
    }

}