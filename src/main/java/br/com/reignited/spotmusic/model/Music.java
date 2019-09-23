package br.com.reignited.spotmusic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "music")
public class Music implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MUSIC")
	private long idMusic;

	@NotBlank
	@NotNull
	@Column(name = "TITLE")
	private String title;

	@NotBlank
	@NotNull
	@Column(name = "ARTIST")
	private String artist;

	@Range(min = 0, max = 10)
	@Column(name = "NOTE")
	private Integer note;

	@ManyToOne
	@JoinColumn(name = "ID_PLAYLIST")
	private Playlist playlist;

	public Music() {

	}

	public long getId() {
		return idMusic;
	}

	public void setId(long id) {
		this.idMusic = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idMusic ^ (idMusic >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (idMusic != other.idMusic)
			return false;
		return true;
	}

}
