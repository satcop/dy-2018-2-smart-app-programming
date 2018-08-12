package com.example.ktpark.picturealbum2;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Poem extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poem);

		setTitle("명화 상세");

		Intent intent = getIntent();
		String tag = intent.getStringExtra("IT_TAG");

		//Toast.makeText(this, tag, Toast.LENGTH_LONG).show();

		TextView tvTitle = (TextView) findViewById(R.id.title);
		TextView tvAuthor = (TextView) findViewById(R.id.author);
		ImageView ivPicture = (ImageView) findViewById(R.id.picture);

		tvTitle.setText(getText("title", tag));
		tvAuthor.setText(getText("author", tag));

		//ivPicture.setBackground(getDrawable("photo", tag));
		ivPicture.setImageDrawable(getDrawable("photo", tag));

	}

	Drawable getDrawable(String category, String tag) {

		Resources resources = getResources();

		int id = resources.getIdentifier(category + tag, "drawable", getPackageName());

		return resources.getDrawable(id);
	}


	String getText(String category, String tag) {

		Resources resources = getResources();

		int id = resources.getIdentifier(category + tag, "string", getPackageName());

		return resources.getString(id);
	}


	void closePicture(View v) {

		finish();

	}

}
