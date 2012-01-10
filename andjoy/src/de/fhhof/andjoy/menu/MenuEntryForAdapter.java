package de.fhhof.andjoy.menu;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.fhhof.andjoy.data.Settings;

/**
 * Repräsentiert einen Menüeintrag. Dieser setzt sich aus einem Bild (ImageView) oben und
 * einem Lauftext (TextView) darunter zusammen.
 * @author Michael
 *
 */
public class MenuEntryForAdapter extends LinearLayout {
	private ImageView imageView;
	private TextView textView;
	private Settings settings;

//	public MenuEntryForAdapter(Context context) {
//		super(context);
//	}
	
	public MenuEntryForAdapter(Context context, ImageView imageView, TextView textView) {
		super(context);
		this.settings = Settings.getInstance(context);
		this.setImageView(imageView);
		this.setTextView(textView);
		this.setOrientation(LinearLayout.VERTICAL);
		// Initialisieren
		// Text als Lauftext
		this.textView.setGravity(Gravity.CENTER);
		this.textView.setSingleLine(true);
		this.textView.setEllipsize(TruncateAt.MARQUEE);
		this.textView.setSelected(true);
		if(settings.getFontColor() != null){
			this.textView.setTextColor(settings.getFontColor());
		}
		// Schatten des Textes
		this.textView.setShadowLayer(2, 2, 2, Color.BLACK);
		
		
		this.addView(imageView);
		this.addView(textView);
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public TextView getTextView() {
		return textView;
	}

	public void setTextView(TextView textView) {
		this.textView = textView;
	}
	

}
