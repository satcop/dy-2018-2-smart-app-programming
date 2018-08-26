package com.example.ktpark.audio2;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	MediaPlayer mediaPlayer = new MediaPlayer();

	int currentPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void playButton(View v) {

		/*if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}*/

		Resources resources = getResources();
		int id = resources.getIdentifier("buzz1", "raw", getPackageName());
		mediaPlayer = MediaPlayer.create(this, id);

		//mediaPlayer = MediaPlayer.create(this, R.raw.buzz1);

		mediaPlayer.setLooping(false);
		mediaPlayer.start();

	}

	public void pauseButton(View v) {

		if (mediaPlayer.isPlaying()) {
			currentPosition = mediaPlayer.getCurrentPosition();
			mediaPlayer.pause();
		}

		mediaPlayer.getDuration();

	}

	public void continueButton(View v) {

		if (mediaPlayer != null && mediaPlayer.isPlaying() == false) {
			mediaPlayer.start();
			mediaPlayer.seekTo(currentPosition);
		}

	}

	public void stopButton(View v) {

		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}

	}

	void release() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}

	@Override
	protected void onDestroy() {
		release();
		super.onDestroy();
	}

}
