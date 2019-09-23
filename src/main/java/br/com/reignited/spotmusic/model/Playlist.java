package br.com.reignited.spotmusic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "playlist")
public class Playlist implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLAYLIST")
	private long idPlaylist;
	
	@NotBlank
	@Size(min = 2, max = 60)
	@Column(name = "NAME")
	@NotNull
	private String name;
	
	@NotBlank
	@Column(name = "DESCRIPTION")
	@NotNull
	private String description;
	
	@OneToMany(mappedBy = "playlist")
	private List<Music> musicList;

	public Playlist() {
		
	}

	public long getId() {
		return idPlaylist;
	}

	public void setId(long id) {
		this.idPlaylist = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPlaylist ^ (idPlaylist >>> 32));
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
		Playlist other = (Playlist) obj;
		if (idPlaylist != other.idPlaylist)
			return false;
		return true;
	}

	
	
	
}
