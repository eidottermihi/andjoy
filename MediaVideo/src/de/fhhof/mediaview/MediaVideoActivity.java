package de.fhhof.mediaview;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.MediaController;

public class MediaVideoActivity extends Activity implements Callback, OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener {
	
	private SurfaceView mPreview;
	private SurfaceHolder holder;
	private Bundle extras;
	private int mVideoWidth;
	private int mVideoHeight;
	private boolean mIsVideoReadyToBePlayed;
	private boolean mIsVideoSizeKnown;
	private MediaPlayer mMediaPlayer;
	private MediaController mMediaController;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mPreview = (SurfaceView) findViewById(R.id.surfaceView1);
        holder = mPreview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        extras = getIntent().getExtras();
    }

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		playVideo();
	}

	private void playVideo() {
		doCleanUp();
		try{
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setDataSource("http://h1930837.stratoserver.net:8080/LanCenter/video/testvideo.3gp");
			mMediaPlayer.setDisplay(holder);
			mMediaPlayer.prepareAsync();
			mMediaPlayer.setOnBufferingUpdateListener(this);
			mMediaPlayer.setOnCompletionListener(this);
			mMediaPlayer.setOnPreparedListener(this);
			mMediaPlayer.setOnVideoSizeChangedListener(this);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		} catch (Exception e) {
			Log.e("MediaVideo", e.getMessage());
		}
		
	}

	private void doCleanUp() {
		mVideoHeight = 0;
		mVideoWidth = 0;
		mIsVideoReadyToBePlayed = false;
		mIsVideoSizeKnown = false;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		mIsVideoSizeKnown = true;
		mVideoWidth = width;
		mVideoHeight = height;
		if(mIsVideoReadyToBePlayed && mIsVideoSizeKnown){
			startVideoPlayback();
		}
	}

	private void startVideoPlayback() {
		holder.setFixedSize(mVideoWidth, mVideoHeight);
		mMediaPlayer.start();
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {
		mIsVideoReadyToBePlayed = true;
		if(mIsVideoReadyToBePlayed && mIsVideoSizeKnown){
			startVideoPlayback();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseMediaPlayer();
		doCleanUp();
	}

	private void releaseMediaPlayer() {
		if(mMediaPlayer != null){
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		releaseMediaPlayer();
		doCleanUp();
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		
	}

	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int arg1) {
		
	}
}