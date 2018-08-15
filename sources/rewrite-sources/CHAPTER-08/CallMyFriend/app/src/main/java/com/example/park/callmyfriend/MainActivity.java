package com.example.park.callmyfriend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	String getText(String category, String tag) {
		Resources resources = getResources();
		int resId = resources.getIdentifier(category + tag, "string", getPackageName());
		return resources.getString(resId);
	}

	private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 101;



	void callPhone(View v) {

		String tag = (String) v.getTag();
		String phone = getText("phone", tag);

		Uri url = Uri.parse("tel:" + phone);
		Intent intent = new Intent(Intent.ACTION_CALL, url);

		int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

		if (permission != PackageManager.PERMISSION_GRANTED) {

			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {

				// Show an expanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.

				Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_LONG).show();

				String[] requestPermission = {Manifest.permission.CALL_PHONE};
				ActivityCompat.requestPermissions(this, requestPermission, MY_PERMISSIONS_REQUEST_CALL_PHONE);

			} else {

				// No explanation needed, we can request the permission.

				String[] requestPermission = {Manifest.permission.CALL_PHONE};
				ActivityCompat.requestPermissions(this, requestPermission, MY_PERMISSIONS_REQUEST_CALL_PHONE);

				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}


			return;
		}

		startActivity(intent);
	}



	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}


	/*

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

		StringBuilder sb = new StringBuilder();

		sb.append("requestCode:" + requestCode + "\n");

		if (permissions != null) {
			for (String x : permissions) {
				sb.append(x + "\n");
			}
		}

		if (grantResults != null) {
			for (int x : grantResults) {
				sb.append(x + "\n");
			}
		}

		Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();

		switch (requestCode) {
			case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

					// permission was granted, yay! Do the
					// contacts-related task you need to do.

				} else {

					// permission denied, boo! Disable the
					// functionality that depends on this permission.
				}
				return;
			}

			// other 'case' lines to check for other
			// permissions this app might request
		}
	}


	*/
}
