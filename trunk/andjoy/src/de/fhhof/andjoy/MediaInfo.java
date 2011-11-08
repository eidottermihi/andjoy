package de.fhhof.andjoy;

import java.io.Serializable;

public class MediaInfo implements Serializable{

String videoUrl;
String audioUrl;
String imageUrl;
String textUrl;
String headLine;

public MediaInfo() {
}
public MediaInfo(String videoUrl, String audioUrl, String imageUrl,
		String textUrl, String headLine) {
	super();
	this.videoUrl = videoUrl;
	this.audioUrl = audioUrl;
	this.imageUrl = imageUrl;
	this.textUrl = textUrl;
	this.headLine = headLine;
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
public String getTextUrl() {
	return textUrl;
}
public void setTextUrl(String textUrl) {
	this.textUrl = textUrl;
}
public String getHeadLine() {
	return headLine;
}
public void setHeadLine(String headLine) {
	this.headLine = headLine;
}


}
