package de.test.AndroidTest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

public class AndroidTestActivity extends Activity {
	
	
	  String SrcPath = "rtsp://v1.cache3.c.youtube.com/CjYLENy73wIaLQkgD1YcagRmYhMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYK2GwP6GsJ6tTgw=/0/0/0/video.3gp";
       
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       

     
        /** Called when the activity is first created. */
       
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);
            myVideoView.setVideoURI(Uri.parse(SrcPath));
            myVideoView.setMediaController(new MediaController(this));
            myVideoView.requestFocus();
            myVideoView.start();
        
     

        }
        }
