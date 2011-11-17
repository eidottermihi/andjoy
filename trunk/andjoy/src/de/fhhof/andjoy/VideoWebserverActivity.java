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
		MediaInfo m1 = (MediaInfo) getIntent().getExtras().getSerializable(
				"test");
		if (m1 != null) {
			setContentView(R.layout.video);
			VideoView myView = (VideoView) findViewById(R.id.videoView1);
			TextView textview = (TextView) findViewById(R.id.textView2);
			// Text aus Extras lesen
			textview.setText(m1.getText());
			Log.v("VideoWebserverActivity", "Video-URL aus MediaInfo-Objekt: " + m1.getVideoUrl());
			// Video-URL aus Extras lesen
			myView.setVideoURI(Uri.parse(m1.getVideoUrl()));
			myView.setMediaController(new MediaController(this));
			myView.start();
		} 
	}
}