package br.com.reignited.spotmusic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.reignited.spotmusic.contract.IPlaylistDao;
import br.com.reignited.spotmusic.contract.IPlaylistService;
import br.com.reignited.spotmusic.model.Playlist;

@Service
@Transactional
public class PlaylistService implements IPlaylistService {

	@Autowired
	private IPlaylistDao dao;

	public void insert(Playlist playlist) {
		dao.insert(playlist);
	}

	public void update(Playlist playlist) {
		dao.update(playlist);
	}

	public void delete(Playlist playlist) {
		dao.delete(playlist);
	}

	@Transactional(readOnly = true)
	public List<Playlist> findAll() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public Playlist findById(Playlist playlist) {
		return dao.findById(playlist);
	}

}
