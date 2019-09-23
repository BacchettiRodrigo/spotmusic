package br.com.reignited.spotmusic.contract;

import java.util.List;

import br.com.reignited.spotmusic.model.Playlist;

public interface IPlaylistDao {

	public void insert(Playlist playlist);

	public void update(Playlist playlist);

	public void delete(Playlist playlist);

	public List<Playlist> findAll();

	public Playlist findById(Playlist playlist);
}
