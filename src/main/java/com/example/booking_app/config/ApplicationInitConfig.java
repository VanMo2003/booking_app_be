package com.example.booking_app.config;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.booking_app.constant.PredefinedPermission;
import com.example.booking_app.constant.PredefinedRole;
import com.example.booking_app.entity.Permission;
import com.example.booking_app.entity.Role;
import com.example.booking_app.entity.User;
import com.example.booking_app.repository.PermissionRepository;
import com.example.booking_app.repository.RoleRepository;
import com.example.booking_app.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    @NonFinal
    static String ADMIN_USER_NAME = "admin";

    @NonFinal
    static String ADMIN_PASSWORD = "admin";

    /**
     * Be run every time the application start
     * @return
     */
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (!userRepository.findByUsername(ADMIN_USER_NAME).isPresent()) {
                Permission getAllUserPermission = permissionRepository.save(Permission.builder()
                        .name(PredefinedPermission.GET_ALL_USER)
                        .description("Get all user")
                        .build());
                Permission getAllRolePermission = permissionRepository.save(Permission.builder()
                        .name(PredefinedPermission.GET_ALL_ROLE)
                        .description("Get all role")
                        .build());
                Permission getAllPermission = permissionRepository.save(Permission.builder()
                        .name(PredefinedPermission.GET_ALL_PERMISSION)
                        .description("Get all permission")
                        .build());

                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("User role")
                        .build());

                roleRepository.save(Role.builder()
                        .name(PredefinedRole.HOTELIER)
                        .description("hotelier role")
                        .build());

                roleRepository.save(Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .description("admin role")
                        .build());

                Set<Permission> permissions = new HashSet<>();
                permissions.add(getAllUserPermission);
                permissions.add(getAllRolePermission);
                permissions.add(getAllPermission);

                Role role = new Role();
                role.setName(PredefinedRole.ADMIN_ROLE);
                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .role(role)
                        .build();

                userRepository.save(user);
                log.warn(
                        "{ADMIN_USER_NAME} user has been create with default password: {ADMIN_PASSWORD}, please change it");
            }
        };
    }
}
