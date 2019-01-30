package com.example.cisco.wordgame;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView wordTextView;
    EditText wordEditText;
    TextView pointsTextView;

    RelativeLayout playGame;
    RelativeLayout endGame;

    TextView playTextView;
    TextView endTextView;
    TextView endPointsTextView;

    ArrayList<String> wordsArray = new ArrayList<>();

    int current_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addWords();
        initViews();

        playTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame.setVisibility(View.GONE);

            }
        });
        endTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endGame.setVisibility(View.GONE);
                playGame.setVisibility(View.VISIBLE);
                current_score =0;
                wordEditText.setText("");
                pointsTextView.setText("Score :");
                getWord();
            }
        });

        pointsTextView.setText("Score: ");
        wordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (getUserInput().length() == getCurrentWord().length()) {

                    if (getUserInput().equalsIgnoreCase(getCurrentWord())) {
                        current_score = current_score + getUserInput().length();
                        pointsTextView.setText("Score: " + current_score);
                        getWord();
                        wordEditText.setText("");
                    } else {

                        endGame.setVisibility(View.VISIBLE);
                        endPointsTextView.setText("Score: " + current_score);

               


                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void initViews() {

        wordTextView = findViewById(R.id.wordTextView);
        wordEditText = findViewById(R.id.wordEditText);
        pointsTextView = findViewById(R.id.pointsTextView);

        playGame = findViewById(R.id.play_holder);

        endGame = findViewById(R.id.game_over);

        playTextView = findViewById(R.id.playTextView);
        endTextView = findViewById(R.id.game_over_textView);
        endPointsTextView = findViewById(R.id.end_points);

        getWord();

    }

    public String getCurrentWord() {
        return wordTextView.getText().toString();

    }

    public void addWords() {

        wordsArray.add("free");
        wordsArray.add("winter");
        wordsArray.add("flowery");
        wordsArray.add("telephone");
        wordsArray.add("window");
        wordsArray.add("suit");
        wordsArray.add("school");
        wordsArray.add("false");
        wordsArray.add("yesterday");
        wordsArray.add("anyway");


    }

    public void getWord() {
        Random r = new Random();

        wordTextView.setText(wordsArray.get(r.nextInt(wordsArray.size())));

    }

    public String getUserInput() {
        return wordEditText.getText().toString();
    }
}
