package de.fhhof.andjoy;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter für die Menüeinträge. Datenquelle des Adapters ist eine Instanz
 * der Klasse AllMedia.
 * Analog zu: {@link http://developer.android.com/resources/tutorials/views/hello-gridview.html}
 * @author Michael
 *
 */
public class MenuEntryAdapter extends BaseAdapter {
	Context mContext;
	AllMedia mediaInstance;
	public static int i = 0;
	
	public MenuEntryAdapter(Context c){
		mContext = c;
		mediaInstance = AllMedia.getInstance(c);
	}

	public int getCount() {
		return mediaInstance.getMediaInfo().size();
	}

	public Object getItem(int position) {
		return mediaInstance.getMediaInfo().get(position);
	}

	public long getItemId(int position) {
		return 0;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		TextView textView;
		MenuEntryForAdapter returnView;
		MediaInfo tempInfo = (MediaInfo) this.getItem(position);
		Log.v("MenuEntryAdapter", "Liefere View mit Headline = " + tempInfo.getHeadLine());
		if(convertView == null){
			// Neue View erzeugen
			imageView = new ImageView(mContext);
			textView = new TextView(mContext);			
			returnView = new MenuEntryForAdapter(mContext, imageView, textView);
					
		} else {
			// Alte View umkonfigurieren
			returnView = (MenuEntryForAdapter) convertView;
						
		}
//		returnView.getImageView().setScaleType(ImageView.ScaleType.FIT_XY);
		returnView.getImageView().setAdjustViewBounds(true);
		returnView.getImageView().setMaxHeight(100);
		returnView.getImageView().setMinimumHeight(100);
		returnView.getImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
		
		int id = mContext.getResources().getIdentifier(tempInfo.getButtonImage(), "drawable", "de.fhhof.andjoy");
		if(id == 0){
			Log.v("MenuEntryAdapter", "frog.jpg nicht gefudnen");
		} else {
			Log.v("MenuEntryAdapter", "frog.jpg wurde gefunden. id = " + id);
			returnView.getImageView().setImageResource(id);
		}
		
		returnView.getTextView().setText(tempInfo.getHeadLine());
		returnView.getTextView().setTextColor(Color.WHITE);
//		returnView.setLayoutParams(new LinearLayout.LayoutParams(85,85));
		returnView.setPadding(8, 8, 8, 8);	
		return returnView;
	}

}
