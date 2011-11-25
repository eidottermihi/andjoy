package de.fhhof.andjoy;

import java.io.Serializable;

public class MediaInfo implements Serializable {
	String buttonName;
	String videoUrl;
	String audioUrl;
	String imageUrl;
	String text;
	String headLine;
	private String buttonImage;

	public MediaInfo() {
	}

	public MediaInfo(String videoUrl, String audioUrl, String imageUrl,
			String textUrl, String headLine, String buttonImage) {
		super();
		this.videoUrl = videoUrl;
		this.audioUrl = audioUrl;
		this.imageUrl = imageUrl;
		this.text = textUrl;
		this.headLine = headLine;
		this.buttonImage = buttonImage;
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

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
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
