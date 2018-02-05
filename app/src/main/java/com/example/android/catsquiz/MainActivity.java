package com.example.android.catsquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Submit button action
    public void submitAnswers(View view) {
        checkAnswersExists();
    }

    //Calculate score
    private void calculateScore() {
        score = 0;
        onRadioButtonClicked();
        onCheckboxClicked();
        textQuestion();
    }

    // Check EditText answer
    public void textQuestion() {
        EditText answer_7 = (EditText)findViewById(R.id.answer_7);
        if (answer_7.getText().toString().toLowerCase().equals("purr") || answer_7.getText().toString().toLowerCase().equals("purring")) {
            score = score + 4;
        }
    }

    // Check radioButtons answers
    private void onRadioButtonClicked() {
        RadioGroup radio1 = findViewById(R.id.radio_group_1);
        int radioButtonId1 = radio1.getCheckedRadioButtonId();
        if (radioButtonId1 == R.id.answer_1_3) score++;

        RadioGroup radio2 = findViewById(R.id.radio_group_2);
        int radioButtonId2 = radio2.getCheckedRadioButtonId();
        if (radioButtonId2 == R.id.answer_2_3) score++;

        RadioGroup radio3 = findViewById(R.id.radio_group_3);
        int radioButtonId3 = radio3.getCheckedRadioButtonId();
        if (radioButtonId3 == R.id.answer_3_1) score++;

        RadioGroup radio5 = findViewById(R.id.radio_group_5);
        int radioButtonId5 = radio5.getCheckedRadioButtonId();
        if (radioButtonId5 == R.id.answer_5_3) score++;
    }

    // Check CheckBoxes answers
    private void onCheckboxClicked() {
        CheckBox checkBoxAnswer_4_1 = (CheckBox) findViewById(R.id.answer_4_1);
        CheckBox checkBoxAnswer_4_2 = (CheckBox) findViewById(R.id.answer_4_2);
        CheckBox checkBoxAnswer_4_3 = (CheckBox) findViewById(R.id.answer_4_3);
        CheckBox checkBoxAnswer_4_4 = (CheckBox) findViewById(R.id.answer_4_4);
        if (!checkBoxAnswer_4_4.isChecked() && checkBoxAnswer_4_1.isChecked() && checkBoxAnswer_4_2.isChecked() && checkBoxAnswer_4_3.isChecked()) {
            score++;
        }
        CheckBox checkBoxAnswer_6_1 = (CheckBox) findViewById(R.id.answer_6_1);
        CheckBox checkBoxAnswer_6_2 = (CheckBox) findViewById(R.id.answer_6_2);
        CheckBox checkBoxAnswer_6_3 = (CheckBox) findViewById(R.id.answer_6_3);
        CheckBox checkBoxAnswer_6_4 = (CheckBox) findViewById(R.id.answer_6_4);
        if (!checkBoxAnswer_6_2.isChecked() && !checkBoxAnswer_6_4.isChecked() && checkBoxAnswer_6_1.isChecked() && checkBoxAnswer_6_3.isChecked()) {
            score++;
        }
    }

    // Check if each question has at least one answer and display toast messages based on score
    public void checkAnswersExists() {
        RadioGroup radio1 = findViewById(R.id.radio_group_1);
        RadioGroup radio2 = findViewById(R.id.radio_group_2);
        RadioGroup radio3 = findViewById(R.id.radio_group_3);
        RadioGroup radio5 = findViewById(R.id.radio_group_5);

        boolean selectionExistsQ4 = false;
        boolean selectionExistsQ6 = false;

        EditText answer_7 = (EditText)findViewById(R.id.answer_7);

        selectionExistsQ4 = (boolean) ((CheckBox) findViewById(R.id.answer_4_1)).isChecked() ? true : false ||
                (boolean) ((CheckBox) findViewById(R.id.answer_4_2)).isChecked() ? true : false ||
                (boolean) ((CheckBox) findViewById(R.id.answer_4_3)).isChecked() ? true : false ||
                (boolean) ((CheckBox) findViewById(R.id.answer_4_4)).isChecked() ? true : false;

        selectionExistsQ6 = (boolean) ((CheckBox) findViewById(R.id.answer_6_1)).isChecked() ? true : false ||
                (boolean) ((CheckBox) findViewById(R.id.answer_6_2)).isChecked() ? true : false ||
                (boolean) ((CheckBox) findViewById(R.id.answer_6_3)).isChecked() ? true : false ||
                (boolean) ((CheckBox) findViewById(R.id.answer_6_4)).isChecked() ? true : false;

        if (radio1.getCheckedRadioButtonId() == -1 || radio2.getCheckedRadioButtonId() == -1 || radio3.getCheckedRadioButtonId() == -1 || radio5.getCheckedRadioButtonId() == -1 || !selectionExistsQ4 || !selectionExistsQ6 || answer_7.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.notification_message, Toast.LENGTH_LONG).show();
        } else {
            calculateScore();
            if (score >= 0 && score <= 3) {
                Toast.makeText(this, getString(R.string.your_score_message) + score + getString(R.string.score_0_3_message), Toast.LENGTH_LONG).show();
            }
            if (score >= 4 && score <= 6) {
                Toast.makeText(this, getString(R.string.your_score_message) + score + getString(R.string.score_4_6_message), Toast.LENGTH_LONG).show();
            }
            if (score >= 7 && score <= 9) {
                Toast.makeText(this, getString(R.string.your_score_message) + score + getString(R.string.score_7_9_message), Toast.LENGTH_LONG).show();
            }
            if (score == 10) {
                Toast.makeText(this, getString(R.string.your_score_message) + score + getString(R.string.score_10_message), Toast.LENGTH_LONG).show();
            }
        }
    }

    //Reset all answers to default values
    public void resetAnswers(View view) {
        score = 0;

        RadioGroup radio1 = findViewById(R.id.radio_group_1);
        radio1.clearCheck();
        RadioGroup radio2 = findViewById(R.id.radio_group_2);
        radio2.clearCheck();
        RadioGroup radio3 = findViewById(R.id.radio_group_3);
        radio3.clearCheck();
        RadioGroup radio5 = findViewById(R.id.radio_group_5);
        radio5.clearCheck();

        CheckBox checkBoxAnswer_4_1 = (CheckBox) findViewById(R.id.answer_4_1);
        CheckBox checkBoxAnswer_4_2 = (CheckBox) findViewById(R.id.answer_4_2);
        CheckBox checkBoxAnswer_4_3 = (CheckBox) findViewById(R.id.answer_4_3);
        CheckBox checkBoxAnswer_4_4 = (CheckBox) findViewById(R.id.answer_4_4);
        CheckBox checkBoxAnswer_6_1 = (CheckBox) findViewById(R.id.answer_6_1);
        CheckBox checkBoxAnswer_6_2 = (CheckBox) findViewById(R.id.answer_6_2);
        CheckBox checkBoxAnswer_6_3 = (CheckBox) findViewById(R.id.answer_6_3);
        CheckBox checkBoxAnswer_6_4 = (CheckBox) findViewById(R.id.answer_6_4);
        checkBoxAnswer_4_1.setChecked(false);
        checkBoxAnswer_4_2.setChecked(false);
        checkBoxAnswer_4_3.setChecked(false);
        checkBoxAnswer_4_4.setChecked(false);
        checkBoxAnswer_6_1.setChecked(false);
        checkBoxAnswer_6_2.setChecked(false);
        checkBoxAnswer_6_3.setChecked(false);
        checkBoxAnswer_6_4.setChecked(false);

        EditText answer_7 = (EditText)findViewById(R.id.answer_7);
        answer_7.setText("");
    }
}