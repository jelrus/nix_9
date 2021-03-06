package ua.com.alevel.config.security;

public interface SecurityService {

    boolean isAuthenticated();

    void autoLogin(String username, String password);

    boolean existsByUsername(String username);
}