package com.anime.Webanime.controllers;

import com.anime.Webanime.entity.Anime;
import com.anime.Webanime.repo.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class AnimeController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/anime")
    public String anime(Model model) {
        Iterable<Anime> animes = animeRepository.findAll();
        model.addAttribute("animes", animes);
        return "anime";
    }

    @PostMapping("/anime")
    public String animePostFilter(@RequestParam String title, Model model) {
        Iterable<Anime> animes = animeRepository.findAll();
        List<Anime> result = new ArrayList<>();
        for (Anime anime : animes) {
            if (anime.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(anime);
            }
        }
        model.addAttribute("animes", result);
        return "anime";
    }

    @GetMapping("/anime-add")
    public String animeAdd(Model model) {
        return "anime-add";
    }

    @PostMapping("/anime-add")
    public String animePostAdd(@RequestParam String title, @RequestParam String type, @RequestParam String description,
                               @RequestParam String genre, @RequestParam String status, @RequestParam int num_of_episodes,
                               @RequestParam int episode_time, @RequestParam int year,
                               @RequestParam(value = "file") MultipartFile file, Model model) throws IOException {
        Anime anime = new Anime(title, genre, description, type, status, num_of_episodes, episode_time, year);
       if (file != null && !file.getOriginalFilename().isEmpty()) {
           File uploadFolder = new File(uploadPath);

           if (!uploadFolder.exists()) {
               uploadFolder.mkdir();
           }

           String uuidFile = UUID.randomUUID().toString();
           String resultFileName = uuidFile + "." + file.getOriginalFilename();
           file.transferTo(new File(uploadPath + "/" + resultFileName));
           anime.setFilename(resultFileName);
       }
        animeRepository.save(anime);
        return "redirect:/anime";
    }

    @GetMapping("/anime/{id}")
    public String animeInfo(@PathVariable(value = "id") long id, Model model) {
        if (!animeRepository.existsById(id)) {
            return "redirect:/anime";
        }

        Optional<Anime> anime = animeRepository.findById(id);
        ArrayList<Anime> res = new ArrayList<>();
        anime.ifPresent(res::add);
        model.addAttribute("anime", res);
        return "anime-info";
    }

    @GetMapping("/anime/{id}/edit")
    public String animeEdit(@PathVariable(value = "id") long id, Model model) {
        if (!animeRepository.existsById(id)) {
            return "redirect:/anime";
        }

        Optional<Anime> anime = animeRepository.findById(id);
        ArrayList<Anime> res = new ArrayList<>();
        anime.ifPresent(res::add);
        model.addAttribute("anime", res);
        return "anime-edit";
    }

    @PostMapping("/anime/{id}/edit")
    public String animePostEdit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String type,
                                @RequestParam String description, @RequestParam String genre, @RequestParam String status,
                                @RequestParam int num_of_episodes, @RequestParam int episode_time, @RequestParam int year,
                                @RequestParam(value = "file") MultipartFile file, Model model) throws IOException {
        Anime anime = animeRepository.findById(id).orElseThrow();
        anime.setTitle(title);
        anime.setType(type);
        anime.setDescription(description);
        anime.setGenre(genre);
        anime.setStatus(status);
        anime.setNum_of_episodes(num_of_episodes);
        anime.setEpisode_time(episode_time);
        anime.setYear(year);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            anime.setFilename(resultFileName);
        }
        animeRepository.save(anime);
        return "redirect:/anime";
    }

    @PostMapping("/anime/{id}/delete")
    public String animePostDelete(@PathVariable(value = "id") long id, Model model) {
        Anime anime = animeRepository.findById(id).orElseThrow();
        animeRepository.delete(anime);
        return "redirect:/anime";
    }

}
