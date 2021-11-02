package ua.com.alevel.db;

import customcollections.DynamicArray;
import ua.com.alevel.console.log.ErrorLog;
import ua.com.alevel.console.log.entries.Entry;
import ua.com.alevel.console.messages.ErrorMessages;
import ua.com.alevel.console.utilities.InputUtils;
import ua.com.alevel.controller.UserController;
import ua.com.alevel.entity.User;

import java.util.Objects;
import java.util.UUID;

public class UserDB {

    private final DynamicArray<User> users;
    private static UserDB instance;

    private UserDB(){
        users = new DynamicArray<>();
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void create(User user){
        user.setId(generateId());
        users.add(user);
    }

    public void update(User user){
        User current = findById(user.getId()); // а если вернется null ?
        current.setName(user.getName());
        current.setLastName(user.getLastName());
        current.setAge(user.getAge());
        current.setEmail(user.getEmail());
    }

    public void delete(String id) {
        for (int i = 0; i<users.size(); i++){
            if (Objects.equals(users.get(i).getId(), id)){
                users.remove(i);
            }
        }
    }

    public User findById(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getId(), id)) {
                return users.get(i);
            }
        }
        return null;
    }

    public DynamicArray<User> findAll() {
        return users;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}