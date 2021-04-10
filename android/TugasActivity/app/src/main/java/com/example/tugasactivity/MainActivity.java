package com.example.tugasactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapater adapater;
    private ArrayList<mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapater = new MahasiswaAdapater(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapater);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new mahasiswa("Puteri", "E41190143",
                "123456789"));
        mahasiswaArrayList.add(new mahasiswa("Ivon", "E41192408",
                "123456789"));
        mahasiswaArrayList.add(new mahasiswa("Nidha", "E41190455",
                "123456789"));
        mahasiswaArrayList.add(new mahasiswa("Dandi", "E41190142",
                "123456789"));
        mahasiswaArrayList.add(new mahasiswa("Angga", "E41192098",
                "123456789"));
        mahasiswaArrayList.add(new mahasiswa("Rifky", "E41192428",
                "123456789"));
        mahasiswaArrayList.add(new mahasiswa("Anggito", "E41190255",
                "123456789"));
    }
}