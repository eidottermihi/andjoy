package de.fhhof.andjoy.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import android.content.Context;
import android.util.Log;
import de.fhhof.andjoy.R;

public class AllMedia {

	private static final String TEXT = "text";
	private static final String VIDEO_URL = "video-url";
	private static final String IMAGE_URL = "image-url";
	private static final String AUDIO_URL = "audio-url";
	private static final String HEADLINE = "headline";
	private static final String BUTTON_IMAGE = "button-image";
	private static final String TEXT_VIDEO = "text-video";
	private static final String BACKGROUND_DETAIL = "background-detail";
	private static final String BACKGROUND_VIDEO = "background-video";
	/**
	 * Privates Klassenattribut, wird beim erstmaligen Gebrauch (nicht beim
	 * Laden) der Klasse erzeugt
	 */
	private static AllMedia instance;
	private List<MediaInfo> mediaInfo;

	/** Konstruktor ist privat, Klasse darf nicht von außen instanziiert werden. */
	private AllMedia() {
	}

	/**
	 * Statische Methode „getInstance()“ liefert die einzige Instanz der Klasse
	 * zurück. Ist synchronisiert und somit thread-sicher.
	 */
	public synchronized static AllMedia getInstance(Context c) {
		if (instance == null) {
			instance = new AllMedia();
			try {
				instance.parseMediaInfo(c);
			} catch (JDOMException e) {
				Log.e("AllMedia", e.getMessage());
			} catch (IOException e) {
				Log.e("AllMedia", e.getMessage());
			}
		}
		return instance;
	}

	public List<MediaInfo> getMediaInfo() {
		return mediaInfo;
	}

	public void setMediaInfo(List<MediaInfo> mediaInfo) {
		this.mediaInfo = mediaInfo;
	}


	/**
	 * Parst die XML, die alle Informationen zu den Medien enthält.
	 * 
	 * @throws JDOMException
	 * @throws IOException
	 */
	private void parseMediaInfo(Context c) throws JDOMException,
			IOException {
		// XML wurde noch nicht geparst
		InputStream in = c.getResources().openRawResource(
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
			media.setText(element.getChildTextTrim(TEXT));
			media.setVideoUrl(element.getChildTextTrim(VIDEO_URL));
			media.setImageUrl(element.getChildTextTrim(IMAGE_URL));
			media.setAudioUrl(element.getChildTextTrim(AUDIO_URL));
			media.setHeadLine(element.getChildTextTrim(HEADLINE));
			media.setButtonImage(element.getChildTextTrim(BUTTON_IMAGE));
			media.setTextVideo(element.getChildTextTrim(TEXT_VIDEO));
			media.setBackgroundDetail(element.getChildTextTrim(BACKGROUND_DETAIL));
			media.setBackgroundVideo(element.getChildTextTrim(BACKGROUND_VIDEO));
			mediaList.add(media);
		}
		this.setMediaInfo(mediaList);
	}

}
