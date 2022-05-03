package com.anime.Webanime.repo;

import com.anime.Webanime.entity.Anime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeRepository extends CrudRepository<Anime, Long> {
    List<Anime> findByTitle(String title);
}
