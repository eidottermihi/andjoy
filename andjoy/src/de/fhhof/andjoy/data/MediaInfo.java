package de.fhhof.andjoy.data;

import java.io.Serializable;

public class MediaInfo implements Serializable {
	String videoUrl;
	String audioUrl;
	String imageUrl;
	String text;
	String headLine;
	String textVideo;
	String background;
	private String buttonImage;

	public MediaInfo() {
	}

	public MediaInfo(String videoUrl, String audioUrl, String imageUrl,
			String textUrl, String headLine, String buttonImage, String textVideo, String background) {
		super();
		this.videoUrl = videoUrl;
		this.audioUrl = audioUrl;
		this.imageUrl = imageUrl;
		this.text = textUrl;
		this.headLine = headLine;
		this.buttonImage = buttonImage;
		this.textVideo = textVideo;
		this.background = background;
	}
	
	

	public String getTextVideo() {
		return textVideo;
	}

	public void setTextVideo(String textVideo) {
		this.textVideo = textVideo;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getHeadLine() {
		return headLine;
	}

	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getButtonImage() {
		return buttonImage;
	}

	public void setButtonImage(String buttonImage) {
		this.buttonImage = buttonImage;
	}

}
