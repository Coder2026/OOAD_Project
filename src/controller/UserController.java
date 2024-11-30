package controller;

import java.util.List;

import model.User;
import repository.UserRepository;

public class UserController {

    private UserRepository userRepository;

    public UserController() {
        userRepository = new UserRepository();
    }

    public boolean register( String userEmail, String userName, String userPassword, String userRole) {
        User user = new User(userEmail, userName, userPassword, userRole);
        return userRepository.createUser(user);
    }
    
    public User login(String email, String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUser_email().equals(email) && user.getUser_password().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update user
    public boolean updateUser(String userEmail, String userName, String userPassword, String userRole) {
        User user = new User(userEmail, userName, userPassword, userRole);
        return userRepository.updateUser(user);
    }

    // Delete user
    public boolean deleteUser(String userId) {
        return userRepository.deleteUser(userId);
    }
}
