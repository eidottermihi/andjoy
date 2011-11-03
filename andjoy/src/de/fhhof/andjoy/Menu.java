package de.fhhof.andjoy;

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

		initiateClickListener();
		
	}

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

	public void onClick(View v) {
		int viewId = v.getId();
		Intent intent = new Intent(this, VideoWebserverActivity.class);

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

	}

}
