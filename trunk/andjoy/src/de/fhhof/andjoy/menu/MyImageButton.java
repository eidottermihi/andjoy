package de.fhhof.andjoy.menu;

import de.fhhof.andjoy.data.MediaInfo;
import android.content.Context;
import android.widget.ImageButton;

/**
 * Klasse erweitert ImageButton. Enth�lt jetzt als Membervariable die
 * zugeh�rigen Medieninformationen.
 * 
 * @author Michael
 * 
 */
public class MyImageButton extends ImageButton {
	private MediaInfo media;

	public MyImageButton(Context context) {
		super(context);
	}

	public MyImageButton(Context context, MediaInfo media) {
		super(context);
		this.media = media;

	}

	public MediaInfo getMedia() {
		return media;
	}

	public void setMedia(MediaInfo media) {
		this.media = media;
	}

}
