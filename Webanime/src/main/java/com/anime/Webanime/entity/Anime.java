package com.anime.Webanime.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, genre, description, type, status;
    private int num_of_episodes, episode_time, year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNum_of_episodes() {
        return num_of_episodes;
    }

    public void setNum_of_episodes(int num_of_episodes) {
        this.num_of_episodes = num_of_episodes;
    }

    public int getEpisode_time() {
        return episode_time;
    }

    public void setEpisode_time(int episode_time) {
        this.episode_time = episode_time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Anime() {
    }

    public Anime(String title, String genre, String description, String type, String status, int num_of_episodes, int episode_time, int year) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.type = type;
        this.status = status;
        this.num_of_episodes = num_of_episodes;
        this.episode_time = episode_time;
        this.year = year;
    }

}
