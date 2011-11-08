package de.fhhof.andjoy;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Menu extends Activity implements OnClickListener {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		//1: To add a new Button first start in the menu.xml
		initiateClickListener();
		
	}

	/**2:
	 * This method sets all clickListeners for the Buttons on the Main menu.
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

	/**3:
	 * This method represents the clickListener for the Button of the Main menu
	 * The Listener links the Buttons created in the menu.xml with a new Activity.
	 * To add a new Listener copy a case-Statement and change "R.id.imageButtonXX" to the 
	 * value of the Button u initiated in the menu.xml and defined in the initiateClickListener() method.
	 */
	public void onClick(View v) {
		int viewId = v.getId();
		MediaInfo m;
		try {
			m = getMediaInfo("imageButton11");
		
		Intent intent = new Intent(this, VideoWebserverActivity.class);
		intent.putExtra("test", m);

		switch (viewId) {
		case R.id.imageButton11:
			startActivity(intent);
			
			break;
		case R.id.imageButton12:
			startActivity(intent);
			break;
		case R.id.imageButton13:
			startActivity(intent);
			break;
		case R.id.imageButton14:
			startActivity(intent);
			break;
		case R.id.imageButton21:
			startActivity(intent);
			break;
		case R.id.imageButton22:
			startActivity(intent);
			break;
		case R.id.imageButton23:
			startActivity(intent);
			break;
		case R.id.imageButton24:
			startActivity(intent);
			break;

		default:
			break;
		}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private MediaInfo getMediaInfo(String ButtonId) throws JDOMException, IOException{
		
		MediaInfo mediaInfo = new MediaInfo();
		Document doc = new SAXBuilder().build("../XML/mediainfo.xml");
		Element videoElement = (Element) XPath.selectSingleNode(doc, "/mediaInfo/media/video-url");
		String videoUrl = videoElement.getText();
		String audioUrl;
		String imageUrl;
		String textUrl = "I m text URL";
		String headLine= "I m headline";
		mediaInfo.setVideoUrl(videoUrl);
		mediaInfo.setTextUrl(textUrl);
		mediaInfo.setHeadLine(headLine);
		return mediaInfo;
		
	}

}
