package com.example.park.musicalbum;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    String getString(String type, String tag) {
        Resources resources = getResources();
        int id = resources.getIdentifier(type + tag, "string", getPackageName());
        return resources.getString(id);
    }


    public void onClickCell(View v) {

        String tag = (String)v.getTag();

        String name = getString("name", tag);
        String author = getString("singer", tag);
        String time = getString("time", tag);


        Toast.makeText(this, " TEST !!! " + name + ", " + author + ", " + time, Toast.LENGTH_LONG).show();


        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra("tag", tag);
        startActivity(intent);
    }









}
