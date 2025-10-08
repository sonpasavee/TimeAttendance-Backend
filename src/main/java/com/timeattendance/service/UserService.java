package com.timeattendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.timeattendance.model.Profile;
import com.timeattendance.model.User;
import com.timeattendance.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register user
    public User register(User user) {
        // เข้ารหัสรหัสผ่าน
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) user.setRole("USER");

        // สร้าง profile ถ้า null
        if (user.getProfile() == null) {
            Profile p = new Profile();
            p.setFullName(""); // หรือ username
            p.setPhoneNumber("");
            p.setProfileImageUrl("");
            p.setPosition("");
            p.setUser(user);
            user.setProfile(p);
        } else {
            user.getProfile().setUser(user);
        }

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Update user safely
    public User updateUser(Long id, User userData) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ===== Update basic User fields =====
        u.setEmail(userData.getEmail());
        u.setUsername(userData.getUsername());
        u.setRole(userData.getRole());

        // Update password if provided
        if (userData.getPassword() != null && !userData.getPassword().isEmpty()) {
            u.setPassword(passwordEncoder.encode(userData.getPassword()));
        }

        // ===== Update or create Profile safely =====
        if (userData.getProfile() != null) {
            Profile p = u.getProfile();
            if (p == null) {
                // สร้าง Profile ใหม่ถ้าไม่เคยมี
                p = new Profile();
                p.setUser(u);
                u.setProfile(p);
            }

            // Update fields ของ profile จาก userData
            p.setFullName(userData.getProfile().getFullName() != null ? userData.getProfile().getFullName() : "");
            p.setPhoneNumber(userData.getProfile().getPhoneNumber() != null ? userData.getProfile().getPhoneNumber() : "");
            p.setPosition(userData.getProfile().getPosition() != null ? userData.getProfile().getPosition() : "");
            p.setProfileImageUrl(userData.getProfile().getProfileImageUrl() != null ? userData.getProfile().getProfileImageUrl() : "");
        }

        return userRepository.save(u);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
