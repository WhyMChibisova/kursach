package com.anime.Webanime.service;

import com.anime.Webanime.entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}