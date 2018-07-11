package com.example.biem.alamien.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.biem.alamien.R;
import com.example.biem.alamien.fragment.cirikhas_frag;
import com.example.biem.alamien.fragment.daftarulang_fragment;
import com.example.biem.alamien.fragment.isiData_fragmen;
import com.example.biem.alamien.fragment.riwayatsekolah_frag;

public class isiData extends AppCompatActivity {
private EditText siswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("FORM DATA SISWA");
        setContentView(R.layout.activity_isi_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Fragment fragment = new isiData_fragmen();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.isidata,fragment).commit();
    }
}
