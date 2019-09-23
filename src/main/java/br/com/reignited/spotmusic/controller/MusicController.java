package br.com.reignited.spotmusic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.reignited.spotmusic.contract.IMusicService;
import br.com.reignited.spotmusic.model.Music;
import br.com.reignited.spotmusic.model.Playlist;

@Controller
@RequestMapping("/playlists/{playlistId}/musics")
public class MusicController {

	@Autowired
	private IMusicService musicService;

	@GetMapping("/findAll")
	public ModelAndView findAll(@PathVariable("playlistId") long playlistId, ModelMap model) {
		Playlist playlist = new Playlist();
		playlist.setId(playlistId);
		model.addAttribute("musics", musicService.findByIdPlaylist(playlist));
		model.addAttribute("playlistId", playlistId);
		return new ModelAndView("/music/list", model);
	}

	@GetMapping("/insert")
	public String preInsert(@ModelAttribute("music") Music music, @PathVariable("playlistId") long playlistId) {
		return "/music/add";
	}

	@PostMapping("/save")
	public String save(@PathVariable("playlistId") long playlistId, @Valid @ModelAttribute("music") Music music, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/music/add";
		}

		Playlist playlist = new Playlist();
		playlist.setId(playlistId);
		music.setPlaylist(playlist);
		musicService.insert(music);
		attr.addFlashAttribute("mensagem", "Música salva com sucesso!");
		return "redirect:/playlists/" + playlistId + "/musics/findAll";
	}

	@GetMapping("/{musicId}/update")
	public ModelAndView preUpdate(@PathVariable("playlistId") long playlistId, @PathVariable("musicId") long musicId, ModelMap model) {
		Playlist p = new Playlist();
		p.setId(playlistId);
		Music music = new Music();
		music.setPlaylist(p);
		music.setId(musicId);
		music = musicService.findByPLaylistIdMusicId(p, music);
		model.addAttribute("music", music);
		model.addAttribute("playlistId", playlistId);
		return new ModelAndView("/music/add", model);
	}
	
	@PutMapping("/save")
	public String update(@PathVariable("playlistId") long playlistId, @Valid @ModelAttribute("music") Music music, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/music/add";
		}

		Playlist playlist = new Playlist();
		playlist.setId(playlistId);
		music.setPlaylist(playlist);
		musicService.update(music);
		attr.addFlashAttribute("mensagem", "Música atualizada com sucesso!");
		return "redirect:/playlists/" + playlistId + "/musics/findAll";
	}
	
	@GetMapping("/{musicId}/remove")
	public String remove(@PathVariable("playlistId") long playlistId, @PathVariable("musicId") long musicId, RedirectAttributes attr) {
		Playlist p = new Playlist();
		p.setId(playlistId);
		Music music = new Music();
		music.setId(musicId);
		music.setPlaylist(p);
		musicService.delete(music);
		attr.addFlashAttribute("mensagem", "Música excluída com sucesso!");
		return "redirect:/playlists/" + playlistId + "/musics/findAll";
	}
}
