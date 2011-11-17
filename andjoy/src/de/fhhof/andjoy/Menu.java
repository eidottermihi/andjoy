package de.fhhof.andjoy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Menu extends Activity implements OnClickListener {


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		// 1: To add a new Button first start in the menu.xml
		initiateClickListener();
		// Einlesen der mediainfo.xml
		try {
			parseMediaInfo();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void parseMediaInfo() throws JDOMException, IOException {
		AllMedia allMedia = AllMedia.getInstance();		
		if (allMedia.getMediaInfo() == null) {
			// XML wurde noch nicht geparst
			InputStream in = this.getResources().openRawResource(
					R.raw.mediainfo);
			Document doc = new SAXBuilder().build(in);
			Element root = doc.getRootElement();
			// Über alle <media>-Elemente iterieren, bis man Eintrag für den
			// Button
			// findet
			List<Element> elementList = root.getChildren("media");
			List<MediaInfo> mediaList = new ArrayList<MediaInfo>();
			for (Element element : elementList) {
				MediaInfo media = new MediaInfo();
				media.setButtonName(element.getAttributeValue("button"));
				media.setText(element.getChildTextTrim("text"));
				media.setVideoUrl(element.getChildTextTrim("video-url"));
				mediaList.add(media);
			}
			allMedia.setMediaInfo(mediaList);
		} else {
			// XML wurde bereits geparst und ist im Speicher
			// Nothing to do
		}
	}

	/**
	 * 2: This method sets all clickListeners for the Buttons on the Main menu.
	 * To initiate a new Button copy a line and change the imageButtonXX to the
	 * value u chose in the menu.xml for ur new Button.
	 */
	private void initiateClickListener() {
		findViewById(R.id.imageButton11).setOnClickListener(this);
		findViewById(R.id.imageButton12).setOnClickListener(this);
		findViewById(R.id.imageButton13).setOnClickListener(this);
		findViewById(R.id.imageButton14).setOnClickListener(this);
		findViewById(R.id.imageButton21).setOnClickListener(this);
		findViewById(R.id.imageButton22).setOnClickListener(this);
		findViewById(R.id.imageButton23).setOnClickListener(this);
		findViewById(R.id.imageButton24).setOnClickListener(this);
	}

	/**
	 * 3: This method represents the clickListener for the Button of the Main
	 * menu The Listener links the Buttons created in the menu.xml with a new
	 * Activity. To add a new Listener copy a case-Statement and change
	 * "R.id.imageButtonXX" to the value of the Button u initiated in the
	 * menu.xml and defined in the initiateClickListener() method.
	 */
	public void onClick(View v) {
		int viewId = v.getId();
		ImageButton button = (ImageButton) findViewById(viewId);
		String btTag = (String) button.getTag();
		Log.v("Menu", "Button mit Tag = " + btTag + " wurde gedrückt.");
		MediaInfo m = null;
		m = AllMedia.getInstance().getMediaInfoFor(btTag);
		if (m != null) {
			Intent intent = new Intent(this, VideoWebserverActivity.class);
			intent.putExtra("test", m);
			startActivity(intent);
		} else {
			Log.v("Menu", "MediaInfo nicht in Singleton-Klasse gefunden, m == null");
		}
	}

	// private MediaInfo getMediaInfo(String buttonId) throws JDOMException,
	// IOException {
	// MediaInfo mediaInfo = new MediaInfo();
	// // XML einlesen und Dokumentenbaum mit JDOM bauen
	// InputStream in = this.getResources().openRawResource(R.raw.mediainfo);
	// Document doc = new SAXBuilder().build(in);
	// Element root = doc.getRootElement();
	// // Über alle <media>-Elemente iterieren, bis man Eintrag für den Button
	// // findet
	// List<Element> elementList = root.getChildren("media");
	// for (Element element : elementList) {
	// if (element.getAttributeValue("button").equals(buttonId)) {
	// Log.v("Menu", "Eintrag für Button " + buttonId
	// + " wurde gefunden.");
	// mediaInfo.setVideoUrl(element.getChildTextTrim("video-url"));
	// mediaInfo.setText(element.getChildTextTrim("text"));
	// return mediaInfo;
	// }
	// }
	// Log.v("Menu", "Eintrag für Button " + buttonId
	// + " wurde nicht gefunden.");
	// return null;
	// }
}
