package de.fhhof.andjoy;

import java.util.List;

public class AllMedia {

	/**
	 * Privates Klassenattribut, wird beim erstmaligen Gebrauch (nicht beim
	 * Laden) der Klasse erzeugt
	 */
	private static AllMedia instance;
	private List<MediaInfo> mediaInfo;

	/** Konstruktor ist privat, Klasse darf nicht von außen instanziiert werden. */
	private AllMedia() {
	}

	/**
	 * Statische Methode „getInstance()“ liefert die einzige Instanz der Klasse
	 * zurück. Ist synchronisiert und somit thread-sicher.
	 */
	public synchronized static AllMedia getInstance() {
		if (instance == null) {
			instance = new AllMedia();
		}
		return instance;
	}

	public List<MediaInfo> getMediaInfo() {
		return mediaInfo;
	}

	public void setMediaInfo(List<MediaInfo> mediaInfo) {
		this.mediaInfo = mediaInfo;
	}
	
	/**
	 * Liefert die MediaInfo zum angegebenen Button. Wenn keine MediaInfo vorhanden ist,
	 * liefert die Methode {@code null} zurück.
	 * @param buttonId	String - Button ID (Button Tag)
	 * @return	MediaInfo oder {@code null}
	 */
	public MediaInfo getMediaInfoFor(String buttonId){
		for (MediaInfo media : this.getMediaInfo()) {
			if(media.getButtonName().equals(buttonId)){
				return media;
			}
		}
		return null;
	}

}
