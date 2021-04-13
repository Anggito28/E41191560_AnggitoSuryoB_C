package com.anggito.intent2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MoveWithDataActivity extends AppCompatActivity {

    private TextView tvdataintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_data);

        tvdataintent = findViewById(R.id.tvdataintent);
//methode dalam memindah data
        String nama = getIntent().getStringExtra("extra_name");
        int age = getIntent().getIntExtra("extra_age", 0);
        String text = "Nama: "+nama+", usia: "+age;
        tvdataintent.setText(text);
    }
}