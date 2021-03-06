package de.fhhof.andjoy;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import de.fhhof.andjoy.data.MediaInfo;
import de.fhhof.andjoy.data.Settings;

/**
 * Klasse DetailViewActivity zeigt ein Bild, Text und Buttons f�r Audio und
 * Video an.
 */
public class DetailViewActivity extends Activity implements OnClickListener, Runnable {
	MediaInfo mediaInfo;
	MediaPlayer audio;
	Settings settings;
	Button audioButton;
	private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                audioButton.setEnabled(true);
        }
};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mediaInfo = (MediaInfo) getIntent().getExtras().getSerializable("test");
		if (mediaInfo != null) {
			setContentView(R.layout.detailview);
			ImageView imgView = (ImageView) findViewById(R.id.imageView1);
			// Imagecaching
			URL url;
			Drawable drawable = null;
			try {
				url = new URL(mediaInfo.getImageUrl());
				URLConnection connection = url.openConnection();
				connection.setUseCaches(true);
				drawable = Drawable.createFromStream(connection.getInputStream(), "src");
			} catch (MalformedURLException e) {
			} catch (IOException e) {
			}
			if(drawable != null){
				imgView.setImageDrawable(drawable);
			}
			// Audio-Button
			audioButton = (Button) findViewById(R.id.audioButton);
			Log.v("AUDIO-URL", "URL:>>" + mediaInfo.getAudioUrl() + "<<");
			if(mediaInfo.getAudioUrl() != null && !mediaInfo.getAudioUrl().equals("")){
				audioButton.setOnClickListener(this);
				Thread th = new Thread(this);
				th.start();				
			} else {
				audioButton.setEnabled(false);
			}
			// Video-Button
			Button button2 = (Button) findViewById(R.id.videoButton);
			Log.v("VIDEO-URL", "URL:>>" + mediaInfo.getVideoUrl() + "<<");
			if(mediaInfo.getVideoUrl() != null && !mediaInfo.getVideoUrl().equals("")){
				button2.setOnClickListener(this);
			} else {
				button2.setEnabled(false);
			}
			


			// Text anzeigen
			TextView tView = (TextView) findViewById(R.id.textView1);
			tView.setText(mediaInfo.getText());
			settings = Settings.getInstance(this);
			if(settings.getFontColorDetail() != null){
				tView.setTextColor(settings.getFontColorDetail());
			}
			setBackground();
			
		}
	}
	
	/**
	 * Hintergrund der Aktivit�t konfigurieren.
	 */
	private void setBackground() {
		RelativeLayout linLay = (RelativeLayout) findViewById(R.id.relativeLayout1);
		// Auf Eintrag in mediainfo.xml pr�fen
		// Entweder Farbe oder Bild als Hintergrund setzen
		if(!mediaInfo.getBackgroundDetail().equals("")){			
			if(mediaInfo.getBackgroundDetail() instanceof Integer){
				linLay.setBackgroundColor((Integer) mediaInfo.getBackgroundDetail());
			} else if(mediaInfo.getBackgroundDetail() != null){
				int id = this.getResources().getIdentifier((String) mediaInfo.getBackgroundDetail(), "drawable", "de.fhhof.andjoy");
				if(id != 0){
					linLay.setBackgroundResource(id);
				}
			}
		} else if(!settings.getBackgroundDetail().equals("")) {
			if(settings.getBackgroundDetail() instanceof Integer){
				linLay.setBackgroundColor((Integer) settings.getBackgroundDetail());
			} else if(settings.getBackgroundDetail() != null){
				int id = this.getResources().getIdentifier((String) settings.getBackgroundDetail(), "drawable", "de.fhhof.andjoy");
				if(id != 0){
					linLay.setBackgroundResource(id);
				}
			}
		}
		
	}

	/**
	 * Generiert das MediaPlayer Objekt in der Klassenvariable (MediaPlayer)audio
	 * und bereitet es zum abspielen vor.
	 */
	private void setAudio() {
		audio = new MediaPlayer();

		try {
			audio.setDataSource(mediaInfo.getAudioUrl());
			audio.setVolume(10, 10);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			audio.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Erzeugt ein Drawable Objekt aus einem JPEG
	 * @param url zum JPEG
	 * @return Drawable eines JPEG 
	 */
	private Drawable getDrawable(String url) {
		try {
			URL myUrl = new URL(url);
			Object content = myUrl.getContent();
			InputStream is = (InputStream) content;
			Drawable d = Drawable.createFromStream(is, "src");
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	

	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.audioButton) {
			//starte MediaPlayer aus Klassenveriable
			audio.start();
		} else if (id == R.id.videoButton) {
			//springe zu VideoWebserverActivity
			Intent intent = new Intent(this, VideoWebserverActivity.class);
			intent.putExtra("test", mediaInfo);
			startActivity(intent);
		}

	}

	public void run() {
		setAudio();
		handler.sendEmptyMessage(0);
	}

	@Override
	protected void onPause() {
		if(audio != null){
			audio.reset();
		}
		super.onPause();
	}

	@Override
	protected void onStop() {
		if(audio != null){
			audio.reset();
		}
		super.onStop();
	}

	@Override
	protected void onResume() {
		if(audio != null){
			audioButton.setEnabled(false);
			Thread th = new Thread(this);
			th.start();	
		}
		super.onResume();
	}
	
	
	
	
	
	
}
