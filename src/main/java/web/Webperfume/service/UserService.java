package web.Webperfume.service;

import web.Webperfume.Role;
import web.Webperfume.model.User;
import web.Webperfume.repository.IRoleRepository;
import web.Webperfume.repository.IUserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.Webperfume.repository.UserRepository;

@Service
@Slf4j
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository; // Lưu người dùng mới vào cơ sở dữ liệu sau khi mã hóa mật khẩu.

    public void save(@NotNull User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    // Gán vai trò mặc định cho người dùng dựa trên tên người dùng.
    public void setDefaultRole(String username) {
        userRepository.findByUsername(username).ifPresentOrElse(user -> {
            user.getRoles().add(roleRepository.findRoleById(Role.USER.value));
            userRepository.save(user);
        }, () -> {
            throw new UsernameNotFoundException("User not found");
        });
    } // Tải thông tin chi tiết người dùng để xác thực.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities(user.getAuthorities()).accountExpired(!user.isAccountNonExpired()).accountLocked(!user.isAccountNonLocked()).credentialsExpired(!user.isCredentialsNonExpired()).disabled(!user.isEnabled()).build();
    }
    @Autowired
    private UserRepository userRepository1;

    // Existing methods...

    public User findByUsername(String username) {
        return userRepository1.findByUsername(username);
    }
    public User findById(Long id) {
        return userRepository1.findById(id).orElse(null); // Trả về null nếu không tìm thấy sản phẩm
    }
    public boolean existsByUsername(String username) {
        return userRepository1.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository1.existsByEmail(email);
    }
}
