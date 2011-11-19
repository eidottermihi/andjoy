package de.fhhof.andjoy;

import android.content.Context;
import android.widget.ImageButton;

/**
 * Klasse erweitert ImageButton. Enthält jetzt als Membervariable die
 * zugehörigen Medieninformationen.
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
