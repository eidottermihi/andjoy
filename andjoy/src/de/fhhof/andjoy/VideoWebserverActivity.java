package de.fhhof.andjoy;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoWebserverActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String src = getString(R.string.testVideo1);
		// String src =
		// "http://h1930837.stratoserver.net:8080/LanCenter/video/testvideo.3gp";
		MediaInfo m1 = (MediaInfo) getIntent().getExtras().getSerializable(
				"test");
		if (m1 != null) {
			setContentView(R.layout.video);
			VideoView myView = (VideoView) findViewById(R.id.videoView1);
			TextView textview = (TextView) findViewById(R.id.textView2);
			textview.setText(m1.getTextUrl());
			myView.setVideoURI(Uri.parse(m1.getVideoUrl()));
			myView.setMediaController(new MediaController(this));
			myView.start();
		} else {
			Log.v("XMLParser", "NODE NOT FOUND");
		}
	}
}