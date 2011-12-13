package de.fhhof.andjoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import de.fhhof.andjoy.data.MediaInfo;
import de.fhhof.andjoy.data.Settings;
import de.fhhof.andjoy.menu.MenuEntryAdapter;

public class Menu extends Activity implements OnItemClickListener {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createUI();

	}

	/**
	 * Erzeugt das Hauptmenü in Abhängigkeit der vorhandenen Media-Einträgen.
	 */
	private void createUI() {		
		this.setContentView(R.layout.menu);
		GridView gridView = (GridView) findViewById(R.id.gridView1);
		Settings settings = Settings.getInstance(this);
		if (settings.getBackgroundMain() instanceof Integer) {
			gridView.setBackgroundColor((Integer)settings.getBackgroundMain());
		} else {
			int id = this.getResources().getIdentifier((String) settings.getBackgroundMain(), "drawable", "de.fhhof.andjoy");
			gridView.setBackgroundResource(id);
		}
		gridView.setAdapter(new MenuEntryAdapter(this));
		gridView.setOnItemClickListener(this);
	}
	
	
	/**
	 * Click-Listener für alle Items der GridView.
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		MediaInfo m = (MediaInfo) parent.getItemAtPosition(position);
		Intent intent = new Intent(this, DetailViewActivity.class);
		intent.putExtra("test", m);
		startActivity(intent);		
	}



}
