package de.fhhof.andjoy;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.fhhof.andjoy.data.MediaInfo;
import de.fhhof.andjoy.data.Settings;

public class DetailViewActivity extends Activity implements OnClickListener {
	MediaInfo mediaInfo;
	MediaPlayer audio;
	Settings settings;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mediaInfo = (MediaInfo) getIntent().getExtras().getSerializable("test");
		if (mediaInfo != null) {
			setContentView(R.layout.detailview);
			ImageView imgView = (ImageView) findViewById(R.id.imageView1);
			Drawable image = getDrawable(mediaInfo.getImageUrl());
			imgView.setImageDrawable(image);

			setAudio();

			Button button1 = (Button) findViewById(R.id.audioButton);
			button1.setOnClickListener(this);
			Button button2 = (Button) findViewById(R.id.videoButton);
			button2.setOnClickListener(this);
			// Text anzeigen
			TextView tView = (TextView) findViewById(R.id.textView1);
			tView.setText(mediaInfo.getText());
			settings = Settings.getInstance(this);
			if(settings.getFontColorDetail() != null){
				tView.setTextColor(settings.getFontColorDetail());
			}
			// Hintergrundbild
			LinearLayout linLay = (LinearLayout) findViewById(R.id.linearLayout1);
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
	 * und bereitet es zum abspielen vor
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
}
