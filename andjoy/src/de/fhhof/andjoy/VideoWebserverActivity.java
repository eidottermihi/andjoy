package de.fhhof.andjoy;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoWebserverActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String src = getString(R.string.testVideo1);
//        String src = "http://h1930837.stratoserver.net:8080/LanCenter/video/testvideo.3gp";
        setContentView(R.layout.main);
        VideoView myView = (VideoView) findViewById(R.id.videoView1);
        myView.setVideoURI(Uri.parse(src));
        myView.setMediaController(new MediaController(this));
        myView.start();
    }
}