package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;
import java.util.*;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.repository.RoleRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository RoleRepository;

    public UserService(UserRepository userRepository, RoleRepository RoleRepository) {
        this.userRepository = userRepository;
        this.RoleRepository = RoleRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user) {
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }

    public User getUserById(long id) {
        User user = userRepository.findById(id);
        return user;
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.RoleRepository.findByName(name);
    }

}
