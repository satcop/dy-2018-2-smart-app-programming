package com.example.ktpark.text2;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		Resources res = getResources();

		Drawable shape = res.getDrawable(R.drawable.shape_title);

		TextView textView = (TextView) findViewById(R.id.title02);
		textView.setBackground(shape);



	}
}
