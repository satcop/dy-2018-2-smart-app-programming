package com.example.ktpark.audio1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	MediaPlayer mediaPlayer = new MediaPlayer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void buttonClick(View v) {

		//Toast.makeText(this, " TEST !!! ", Toast.LENGTH_LONG).show();

		mediaPlayer = MediaPlayer.create(this, R.raw.n1);
		mediaPlayer.setLooping(false);
		mediaPlayer.start();

	}

	@Override
	protected void onDestroy() {
		mediaPlayer.stop();
		mediaPlayer.release();

		super.onDestroy();
	}
}
