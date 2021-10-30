package ua.com.alevel.service;

import customcollections.DynamicArray;
import ua.com.alevel.dao.UserDAO;
import ua.com.alevel.entity.User;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public void create(User user){
        userDAO.create(user);
    }

    public void update(User user){
        userDAO.update(user);
    }

    public void delete(String id) {
        userDAO.delete(id);
    }

    public User findById(String id){
        return userDAO.findById(id);
    }

    public DynamicArray<User> findAll(){
        return userDAO.findAll();
    }
}