package br.com.reignited.spotmusic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.reignited.spotmusic.contract.IMusicDao;
import br.com.reignited.spotmusic.contract.IMusicService;
import br.com.reignited.spotmusic.model.Music;
import br.com.reignited.spotmusic.model.Playlist;

@Service
@Transactional
public class MusicService implements IMusicService{
	
	@Autowired
	private IMusicDao dao;
	
	public void insert(Music music) {
		dao.insert(music);
	}

	public void update(Music music) {
		dao.update(music);
	}

	public void delete(Music music) {
		dao.delete(music);
	}
	
	@Transactional(readOnly = true)
	public Music findByPLaylistIdMusicId(Playlist playlist, Music music) {
		return dao.findByPLaylistIdMusicId(playlist, music);
	}
	
	@Transactional(readOnly = true)
	public List<Music> findByIdPlaylist(Playlist playlist) {
		return dao.findByIdPlaylist(playlist);
	}
	
}
