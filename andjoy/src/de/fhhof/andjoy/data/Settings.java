package de.fhhof.andjoy.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import de.fhhof.andjoy.R;

public class Settings {

	private static final String FARBE_REGEX = "\\#[0-9a-fA-F]{6}";
	private static Settings instance;
	/**
	 * Hintergrund des Hauptmenüs.
	 */
	private Object backgroundMain;
	/**
	 * Hintergrund der Detailseite.
	 */
	private Object backgroundDetail;
	/**
	 * Hintergrund der Videoseite.
	 */
	private Object backgroundVideo;
	/**
	 * Schriftfarbe der Hauptseite.
	 */
	private Integer fontColorMain;
	/**
	 * Schriftfarbe der Detailansicht.
	 */
	private Integer fontColorDetail;
	/**
	 * Schriftfarbe der Videoansicht.
	 */
	private Integer fontColorVideo;

	private Settings() {
	};

	public static synchronized Settings getInstance(Context c) {
		if (instance == null) {
			instance = new Settings();
			try {
				instance.parseSettings(c);
			} catch (JDOMException e) {
				Log.e("Settings", e.getMessage());
			} catch (IOException e) {
				Log.e("Settings", e.getMessage());
			}
			return instance;
		} else {
			return instance;
		}
	}

	private void parseSettings(Context c) throws JDOMException, IOException {
		// XML wurde noch nicht geparst
		InputStream in = c.getResources().openRawResource(R.raw.setttings);
		Document doc = new SAXBuilder().build(in);
		Element root = doc.getRootElement();
		Pattern pattern = Pattern.compile(FARBE_REGEX);
		// Background-Main
		String backgroundMainStr = root.getChildTextTrim("background-main");
		if (pattern.matcher(backgroundMainStr).matches()) {
			this.backgroundMain = new Integer(
					Color.parseColor(backgroundMainStr));
		} else {
			this.backgroundMain = backgroundMainStr;
		}
		// Background-Detail
		String backgroundDetailStr = root.getChildTextTrim("background-detail");
		if (pattern.matcher(backgroundDetailStr).matches()) {
			this.backgroundDetail = new Integer(
					Color.parseColor(backgroundDetailStr));
		} else {
			this.backgroundDetail = backgroundDetailStr;
		}
		// Background-Video
		String backgroundVideoStr = root.getChildTextTrim("background-video");
		if (pattern.matcher(backgroundVideoStr).matches()) {
			this.backgroundVideo = new Integer(
					Color.parseColor(backgroundVideoStr));
		} else {
			this.backgroundVideo = backgroundVideoStr;
		}
		// Schriftfarbe Hauptseite
		String fontColorStr = root.getChildTextTrim("font-color-main");
		if (pattern.matcher(fontColorStr).matches()) {
			this.fontColorMain = new Integer(Color.parseColor(fontColorStr));
		}
		// Schriftfarbe Detailseite
		String fontColorDetailStr = root.getChildTextTrim("font-color-detail");
		if (pattern.matcher(fontColorDetailStr).matches()) {
			this.fontColorDetail = new Integer(Color.parseColor(fontColorDetailStr));
		}
		// Schriftfarbe Videoseite
		String fontColorVideoStr = root.getChildTextTrim("font-color-video");
		if (pattern.matcher(fontColorVideoStr).matches()) {
			this.fontColorVideo = new Integer(Color.parseColor(fontColorVideoStr));
		}

	}

	public Object getBackgroundMain() {
		return backgroundMain;
	}

	public void setBackgroundMain(Object backgroundMain) {
		this.backgroundMain = backgroundMain;
	}

	public Object getBackgroundDetail() {
		return backgroundDetail;
	}

	public void setBackgroundDetail(Object backgroundDetail) {
		this.backgroundDetail = backgroundDetail;
	}

	public Object getBackgroundVideo() {
		return backgroundVideo;
	}

	public void setBackgroundVideo(Object backgroundVideo) {
		this.backgroundVideo = backgroundVideo;
	}

	public Integer getFontColor() {
		return fontColorMain;
	}

	public void setFontColor(Integer fontColor) {
		this.fontColorMain = fontColor;
	}

	public Integer getFontColorDetail() {
		return fontColorDetail;
	}

	public void setFontColorDetail(Integer fontColorDetail) {
		this.fontColorDetail = fontColorDetail;
	}

	public Integer getFontColorVideo() {
		return fontColorVideo;
	}

	public void setFontColorVideo(Integer fontColorVideo) {
		this.fontColorVideo = fontColorVideo;
	}

}
