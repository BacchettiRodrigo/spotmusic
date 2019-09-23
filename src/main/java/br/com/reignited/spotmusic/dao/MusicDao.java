package br.com.reignited.spotmusic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.reignited.spotmusic.contract.IMusicDao;
import br.com.reignited.spotmusic.model.Music;
import br.com.reignited.spotmusic.model.Playlist;

@Repository
public class MusicDao implements IMusicDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void insert(Music music) {
		entityManager.persist(music);
	}

	public void update(Music music) {
		entityManager.merge(music);

	}

	public void delete(Music music) {
		entityManager.remove(entityManager.getReference(Music.class, music.getId()));

	}

	public Music findByPLaylistIdMusicId(Playlist playlist, Music music) {
		return entityManager.createQuery("select m from Music m where m.playlist.idPlaylist = :playlistId and m.idMusic = : musicId", Music.class)
				.setParameter("playlistId", playlist.getId()).setParameter("musicId", music.getId()).getSingleResult();
	}

	public List<Music> findByIdPlaylist(Playlist playlist) {
		return entityManager.createQuery("select m from Music m where m.playlist.id = :playlistId", Music.class)
				.setParameter("playlistId", playlist.getId()).getResultList();
	}

}
