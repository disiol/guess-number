package com.ramdom.guess_number;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.ramdom.guess_number.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String MY = "My";
    private ActivityMainBinding binding;
    private String url = "https://bugaga.ru/celebrities/1146765025-lica-znamenitostey-krupnym-planom.html";
    private List<Button> buttons = new ArrayList<>();
    private int numberOfRightButon;
    private int numberOfRightAnswer;

    //TODO private int points;
    private int butonNaber = 0;
    private String MYLOG_TEG = "Mylog";
    private int caunterNabbers = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        addBatonsToButtons();
        addButtonsValies();
        playGame();


    }

    private void addBatonsToButtons() {
        buttons.add(binding.button);
        buttons.add(binding.button2);
        buttons.add(binding.button3);
        buttons.add(binding.button4);
        buttons.add(binding.button5);
    }

    private void generateQuestion() {
        numberOfRightButon = (int) (Math.random() * buttons.size() - 1);
        numberOfRightAnswer = (int) (Math.random() * caunterNabbers);
        caunterNabbers = caunterNabbers--;

        Log.d(MYLOG_TEG, "numberOfRightAnswer = " + numberOfRightAnswer);

    }

    private int generateWrongAnswer() {
        caunterNabbers = caunterNabbers--;
        return (int) (Math.random() * caunterNabbers);

    }

    public void addButtonsValies() {
        while (butonNaber < buttons.size()) {
            Button button = buttons.get(butonNaber);
            Log.d(MYLOG_TEG, "buttonId = " + button);

            button.setOnClickListener(v -> {
                String tag = button.getTag().toString();
                if (Integer.parseInt(tag) == numberOfRightAnswer) {
                    showMessage("Верно!");
                } else {
                    showMessage("Неверно, правильный ответ: " + numberOfRightAnswer);
                }

            });

            butonNaber++;

        }

    }

    private void playGame() {
        generateQuestion();
        addTextTobattons();


    }

    private void addTextTobattons() {
        for (int i = 0; i < buttons.size(); i++) {
            if (i == numberOfRightButon) {

                buttons.get(i).setText(String.valueOf(numberOfRightAnswer));
                buttons.get(i).setTag(numberOfRightAnswer);
                Log.d(MYLOG_TEG, "name size numberOfRightAnswer = " + numberOfRightAnswer);

            } else {
                int wrongAnswer = generateWrongAnswer();
                buttons.get(i).setText(String.valueOf(wrongAnswer));
                buttons.get(i).setTag(wrongAnswer);

                Log.d(MYLOG_TEG, "wrongAnswere =  " + wrongAnswer);

            }
        }
    }


    public void showMessage(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        (dialog, id) -> {
                            playGame();
                            dialog.cancel();
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}