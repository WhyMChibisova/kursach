package com.anime.Webanime.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
