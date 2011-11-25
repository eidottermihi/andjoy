package de.fhhof.andjoy;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Kapselt einen Menüeintrag. Klasse ist Erweiterung von LinearLayout und beinhaltet einen ImageButton und eine TextView darunter.
 * @author Michael
 *
 */
public class MyMenuEntry extends LinearLayout {
	TextView tView;
	MediaInfo mediaInfo;
	ImageButton imageButton;

	public MyMenuEntry(Context context, MediaInfo mediaInfo, ImageButton imageButton) {
		super(context);
		this.mediaInfo = mediaInfo;
		this.imageButton = imageButton;
		this.init(context, imageButton, mediaInfo);
		
	}
	
	private void init(Context ctx, ImageButton imageButton, MediaInfo mediaInfo){
		tView = new TextView(ctx);
		//tView.setText(cutString(mediaInfo.getHeadLine()));
		tView.setText(mediaInfo.getHeadLine());
		//tView.setTextColor(Color.WHITE);
		this.setOrientation(LinearLayout.VERTICAL);
		this.addView(imageButton);
		this.addView(tView);
	}

	private String cutString(String headLine) {
		String s = headLine;
		if(headLine.length()>10){
			StringBuilder sb = new StringBuilder();
			sb.append(headLine.substring(0, 8));
			sb.append("..");
			return sb.toString();
		}
		return s;
	}

	public TextView gettView() {
		return tView;
	}

	public void settView(TextView tView) {
		this.tView = tView;
	}

	public MediaInfo getMediaInfo() {
		return mediaInfo;
	}

	public void setMediaInfo(MediaInfo mediaInfo) {
		this.mediaInfo = mediaInfo;
	}

	public ImageButton getImageButton() {
		return imageButton;
	}

	public void setImageButton(ImageButton imageButton) {
		this.imageButton = imageButton;
	}
	
	

}
