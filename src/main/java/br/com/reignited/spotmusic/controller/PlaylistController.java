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

import br.com.reignited.spotmusic.contract.IPlaylistService;
import br.com.reignited.spotmusic.model.Playlist;

@Controller
@RequestMapping("playlists")
public class PlaylistController {

	@Autowired
	private IPlaylistService playlistService;

	@GetMapping("/findAll")
	public ModelAndView findAll(ModelMap model) {
		model.addAttribute("playlists", playlistService.findAll());
		return new ModelAndView("/playlist/list", model);
	}
	
	@GetMapping("/insert")
	public String preInsert(@ModelAttribute("playlist") Playlist playlist) {
		return "/playlist/add";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "/playlist/add";
		}
		
		playlistService.insert(playlist);
		attr.addFlashAttribute("mensagem", "Playlist criada com sucesso!");
		return "redirect:/playlists/findAll";
	}
	
	@GetMapping("/{id}/update")
	public ModelAndView preUpdate(@PathVariable("id") long id, ModelMap model) {
		Playlist playlist = new Playlist();
		playlist.setId(id);
		playlist = playlistService.findById(playlist);
		model.addAttribute("playlist", playlist);
		return new ModelAndView("/playlist/add");
	}
	
	@PutMapping("/save")
	public String update(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "/playlist/add";
		}
		
		playlistService.update(playlist);
		attr.addFlashAttribute("mensagem", "Playlist atualizada com sucesso!");
		return "redirect:/playlists/findAll";
	}
	
	@GetMapping("/{id}/remove")
	public String remove(@PathVariable("id") long id, RedirectAttributes attr) {
		Playlist playlist = new Playlist();
		playlist.setId(id);
		playlistService.delete(playlist);
		attr.addFlashAttribute("mensagem", "Playlist excluída com sucesso!");
		return "redirect:/playlists/findAll";
	}
}
