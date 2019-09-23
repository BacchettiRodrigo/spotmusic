package br.com.reignited.spotmusic.contract;

import java.util.List;

import br.com.reignited.spotmusic.model.Music;
import br.com.reignited.spotmusic.model.Playlist;

public interface IMusicDao {

	public void insert(Music music);

	public void update(Music music);

	public void delete(Music music);

	public Music findByPLaylistIdMusicId(Playlist playlist, Music music);

	public List<Music> findByIdPlaylist(Playlist playlist);
}
