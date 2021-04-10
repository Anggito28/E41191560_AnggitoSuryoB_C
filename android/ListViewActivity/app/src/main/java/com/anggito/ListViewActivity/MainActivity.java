package com.anggito.ListViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnListView;
    private Button btnRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListView = findViewById(R.id.btn_list_view);
        btnRecyclerView = findViewById(R.id.btn_recycler_view);

        btnListView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, listviewactivity.class)));
        btnRecyclerView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class)));
    }
}