package com.anggito.intent2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MoveForResultActivity extends AppCompatActivity implements View.OnClickListener {
//deklarasi kelas dan variable
    public static final int RESULT_CODE = 110;
    public static final String EXTRA_VALUE = "EXTRA_VALUE";
    private Button buttonSubmit;
    private RadioGroup radioGroupAngka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_for_result);

        radioGroupAngka = findViewById(R.id.radio_grup);
        buttonSubmit = findViewById(R.id.btnSubmit);
        buttonSubmit.setOnClickListener(this);
    }
//metode fungsi pick radio button
    @Override
    public void onClick(View v) {
        if (radioGroupAngka.getCheckedRadioButtonId() != 0){
            int value = 0;
            switch (radioGroupAngka.getCheckedRadioButtonId()){
                case R.id.radioButton100:
                    value = 8;
                    break;
                case R.id.radioButton50:
                    value = 4;
                    break;
                case R.id.radioButton32:
                    value = 216;
                    break;
            }
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_VALUE, value);
            setResult(RESULT_CODE, resultIntent);
            finish();
        }
    }
}