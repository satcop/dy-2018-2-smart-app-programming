package com.example.ktpark.picturealbum2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setTitle("명화 목록");
	}


	void displayPoem(View v) {

		String tag = (String)((LinearLayout)v).getTag();

		Intent intent = new Intent(this, Poem.class);
		intent.putExtra("IT_TAG", tag);

		startActivity(intent);
	}




}
