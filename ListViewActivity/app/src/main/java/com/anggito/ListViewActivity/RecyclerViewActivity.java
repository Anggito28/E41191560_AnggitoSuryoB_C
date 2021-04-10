package com.anggito.ListViewActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private MahasiswaAdapter mAdapter;
    private RecyclerView rvMahasiswa;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        addData();

        rvMahasiswa = findViewById(R.id.rv_mahasiswa);

        mAdapter  = new MahasiswaAdapter(mahasiswaArrayList);
        rvMahasiswa.setLayoutManager(new LinearLayoutManager(this));
        rvMahasiswa.setAdapter(mAdapter);
    }

    private MahasiswaAdapter getmAdapter() {
        return mAdapter;
    }

    private void addData() {
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Puteri", "E41190143",
                "123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Ivon", "E41192408",
                "123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Nidha", "E41190455",
                "123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Dandi", "E41190142",
                "123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Angga", "E41192098",
                "123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Rifky", "E41192428",
                "123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Anggito", "E41190255",
                "123456789"));
    }}
