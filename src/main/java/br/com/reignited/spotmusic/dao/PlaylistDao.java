package br.com.reignited.spotmusic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.reignited.spotmusic.contract.IPlaylistDao;
import br.com.reignited.spotmusic.model.Playlist;

@Repository
public class PlaylistDao implements IPlaylistDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void insert(Playlist playlist) {
		entityManager.persist(playlist);
	}

	public void update(Playlist playlist) {
		entityManager.merge(playlist);
	}

	public void delete(Playlist playlist) {
		entityManager.remove(entityManager.getReference(Playlist.class, playlist.getId()));
	}

	public List<Playlist> findAll() {
		return entityManager.createQuery("select p from Playlist p", Playlist.class).getResultList();
	}

	public Playlist findById(Playlist playlist) {
		return entityManager.find(Playlist.class, playlist.getId());
	}

}
