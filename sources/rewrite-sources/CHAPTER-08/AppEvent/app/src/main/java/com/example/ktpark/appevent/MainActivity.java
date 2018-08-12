package com.example.ktpark.appevent;

import android.content.res.Resources;
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
	}


	void displayPoem(View v) {

		// 교재에 있는 내용을 수정

		String tag = (String)v.getTag();

		String title = getText (tag, "title");
		String author = getText (tag, "author");
		String desc = getText (tag, "desc");

		Toast toast = Toast.makeText(this, title + " / " + author + "\n\n" + desc, Toast.LENGTH_LONG);
		toast.show();

	}

	String getText(String id, String ctg) {

		Resources resources = getResources();

		int resId = resources.getIdentifier(ctg + id, "string", getPackageName());
		String text = resources.getString(resId);

		return text;
	}

}
