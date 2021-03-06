package de.fhhof.andjoy;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import de.fhhof.andjoy.data.MediaInfo;
import de.fhhof.andjoy.data.Settings;


/**
 * Klasse VideoWebserverActivity gibt ein Video mit einer kurzen 
 * Beschreibung wieder. 
 */
public class VideoWebserverActivity extends Activity implements OnPreparedListener {
	Settings settings;
	MediaInfo mediaInfo;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mediaInfo = (MediaInfo) getIntent().getExtras().getSerializable(
				"test");
		if (mediaInfo != null) {
			setContentView(R.layout.video);
			VideoView myView = (VideoView) findViewById(R.id.videoView1);
			TextView textUnterVideoView = (TextView) findViewById(R.id.textView2);
			TextView textUeberVideoView = (TextView) findViewById(R.id.textView1);
			// Text aus Extras lesen
			textUnterVideoView.setText(mediaInfo.getTextVideo());
			textUeberVideoView.setText(mediaInfo.getHeadLine());
			settings = Settings.getInstance(this);
			if(settings.getFontColorVideo() != null){
				textUnterVideoView.setTextColor(settings.getFontColorVideo());
				textUeberVideoView.setTextColor(settings.getFontColorVideo());
			}
			// Hintergrund setzen
			setBackground();
			Log.v("VideoWebserverActivity", "Video-URL aus MediaInfo-Objekt: " + mediaInfo.getVideoUrl());
			// Video-URL aus Extras lesen
			myView.setOnPreparedListener(this);
			myView.setVideoURI(Uri.parse(mediaInfo.getVideoUrl()));
			MediaController mediaController = new MediaController(this);
			myView.setMediaController(mediaController);
			myView.requestFocus();
			myView.start();
		} 
	}

	private void setBackground() {
		LinearLayout linLay = (LinearLayout) findViewById(R.id.linearLayout1);
		// Auf Eintrag in mediainfo.xml pr�fen
		// Entweder Farbe oder Bild als Hintergrund setzen
		if(!mediaInfo.getBackgroundVideo().equals("")){			
			if(mediaInfo.getBackgroundVideo() instanceof Integer){
				linLay.setBackgroundColor((Integer) mediaInfo.getBackgroundVideo());
			} else if(mediaInfo.getBackgroundVideo() != null){
				int id = this.getResources().getIdentifier((String) mediaInfo.getBackgroundVideo(), "drawable", "de.fhhof.andjoy");
				if(id != 0){
					linLay.setBackgroundResource(id);
				}
			}
		} else if(!settings.getBackgroundVideo().equals("")) {
			if(settings.getBackgroundVideo() instanceof Integer){
				linLay.setBackgroundColor((Integer) settings.getBackgroundVideo());
			} else if(settings.getBackgroundVideo() != null){
				int id = this.getResources().getIdentifier((String) settings.getBackgroundVideo(), "drawable", "de.fhhof.andjoy");
				if(id != 0){
					linLay.setBackgroundResource(id);
				}
			}
		}
	}

	public void onPrepared(MediaPlayer mp) {
		ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		progressBar.setVisibility(View.GONE);		
	}
}