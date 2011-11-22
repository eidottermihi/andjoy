package de.fhhof.andjoy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Menu extends Activity implements OnClickListener {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Nicht mehr ben�tigt
		// setContentView(R.layout.menu);
		// 1: To add a new Button first start in the menu.xml
		// initiateClickListener();
		
		
		// Einlesen der mediainfo.xml
		try {
			parseMediaInfo();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Layout manuell erzeugen
		createUI();

	}

	/**
	 * Erzeugt das Hauptmen� in Abh�ngigkeit der vorhandenen Media-Eintr�gen.
	 */
	private void createUI() {
		List<MediaInfo> mediaInfo = AllMedia.getInstance().getMediaInfo();
		// LinearLayout
		LinearLayout linLay = new LinearLayout(this);
		linLay.setOrientation(LinearLayout.VERTICAL);
		// Scrollbar
		ScrollView scrView = new ScrollView(this);
		// TableLayout
		TableLayout tabLay = new TableLayout(this);
		tabLay.setStretchAllColumns(true);
		// Verschachteln
		scrView.addView(tabLay);
		linLay.addView(scrView);

		// F�r jeden Media-Eintrag einen zugeh�rigen ImageButton erzeugen
		List<MyImageButton> imgBts = new ArrayList<MyImageButton>();
		for (MediaInfo media : mediaInfo) {
			MyImageButton tempBt = new MyImageButton(this, media);
			tempBt.setImageResource(R.drawable.icon);
			imgBts.add(tempBt);
		}
		// Berechnung der notwendigen Zeilen (akt. 4 Eintr�ge pro Zeile)
		int buttons = mediaInfo.size();
		int btsPerRow = 4;
		int rows;
		if (buttons > 0) {
			rows = (buttons / btsPerRow);
			rows += 1;
		} else {
			rows = 0;
		}
		// Iterator �ber die vorhandenen Buttons
		Iterator<MyImageButton> it = imgBts.iterator();
		for (int i = 0; i < rows; i++) {
			// F�r jede Zeile eine TableRow
			TableRow tRow = new TableRow(this);
			for (int j = 0; j < btsPerRow; j++) {
				// F�r jede Zeile maximal 4 Eintr�ge hinzuf�gen
				if (it.hasNext()) {
					MyImageButton tmpBt = it.next();
					// ClickListener registrieren
					tmpBt.setOnClickListener(this);
					// MyMenuEntry erzeugen und einf�gen
					// TODO: MyImageButton-Klasse ist nicht mehr notwendig, Code-Refactoring!
					MyMenuEntry tempMyMenuEntry = new MyMenuEntry(this, tmpBt.getMedia(), tmpBt);
					tRow.addView(tempMyMenuEntry);
					Log.v("Menu", "Menu-Eintrag hinzugef�gt: " + tmpBt.getMedia().getHeadLine());
				}
			}
			// Zeile hinzuf�gen
			tabLay.addView(tRow);
		}
		this.setContentView(linLay);
	}
	
	/**
	 * Parst die XML, die alle Informationen zu den Medien enth�lt.
	 * @throws JDOMException
	 * @throws IOException
	 */
	private void parseMediaInfo() throws JDOMException, IOException {
		AllMedia allMedia = AllMedia.getInstance();
		if (allMedia.getMediaInfo() == null) {
			// XML wurde noch nicht geparst
			InputStream in = this.getResources().openRawResource(
					R.raw.mediainfo);
			Document doc = new SAXBuilder().build(in);
			Element root = doc.getRootElement();
			// �ber alle <media>-Elemente iterieren, bis man Eintrag f�r den
			// Button
			// findet
			List<Element> elementList = root.getChildren("media");
			List<MediaInfo> mediaList = new ArrayList<MediaInfo>();
			for (Element element : elementList) {
				MediaInfo media = new MediaInfo();
				media.setButtonName(element.getAttributeValue("button"));
				media.setText(element.getChildTextTrim("text"));
				media.setVideoUrl(element.getChildTextTrim("video-url"));
				media.setImageUrl(element.getChildTextTrim("image-url"));
				media.setAudioUrl(element.getChildTextTrim("audio-url"));
				media.setHeadLine(element.getChildTextTrim("headline"));
				mediaList.add(media);
			}
			allMedia.setMediaInfo(mediaList);
		} else {
			// XML wurde bereits geparst und ist im Speicher
			// Nothing to do
		}
	}


	/**
	 * 3: This method represents the clickListener for the Button of the Main
	 * menu The Listener links the Buttons created in the menu.xml with a new
	 * Activity. To add a new Listener copy a case-Statement and change
	 * "R.id.imageButtonXX" to the value of the Button u initiated in the
	 * menu.xml and defined in the initiateClickListener() method.
	 */
	public void onClick(View v) {
		// int viewId = v.getId();
		MyImageButton button = (MyImageButton) v;
		MediaInfo mediaInfo = button.getMedia();
		Intent intent = new Intent(this, DetailViewActivity.class);
		intent.putExtra("test", mediaInfo);
		startActivity(intent);
	}

}
