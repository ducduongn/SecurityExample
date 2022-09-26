package com.untralvious.demo.security.service.impl;

import com.untralvious.demo.security.domain.SysRole;
import com.untralvious.demo.security.domain.SysUser;
import com.untralvious.demo.security.repository.SysRoleRepository;
import com.untralvious.demo.security.repository.SysUserRepository;
import com.untralvious.demo.security.security.AuthoritiesConstants;
import com.untralvious.demo.security.security.SecurityUtils;
import com.untralvious.demo.security.service.*;
import com.untralvious.demo.security.service.dto.SysAdminUserDto;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.security.RandomUtil;

@Service
public class SysUserServiceImpl {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final SysUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final SysUserRepository sysUserRepository;

    @Autowired
    private final SysRoleRepository sysRoleRepository;

    private final CacheManager cacheManager;

    public SysUserServiceImpl(
        SysUserRepository userRepository,
        PasswordEncoder passwordEncoder,
        SysUserRepository sysUserRepository,
        SysRoleRepository sysRoleRepository,
        CacheManager cacheManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sysUserRepository = sysUserRepository;
        this.sysRoleRepository = sysRoleRepository;
        this.cacheManager = cacheManager;
    }

    public Optional<SysUser> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return sysUserRepository
            .findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                this.clearUserCaches(user);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<SysUser> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userRepository
            .findOneByResetKey(key)
            .filter(user -> user.getResetTime().toInstant().isAfter(Instant.now().minus(1, ChronoUnit.DAYS)))
            .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetTime(null);
                this.clearUserCaches(user);
                return user;
            });
    }

    public Optional<SysUser> requestPasswordReset(String mail) {
        return userRepository
            .findOneByEmailIgnoreCase(mail)
            .filter(SysUser::isActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetTime(Timestamp.from(Instant.now()));
                this.clearUserCaches(user);
                return user;
            });
    }

    public SysUser registerUser(SysAdminUserDto userDTO, String password) {
        userRepository
            .findOneByUsername(userDTO.getUsername().toLowerCase())
            .ifPresent(existingUser -> {
                boolean removed = removeNonActivatedUser(existingUser);
                if (!removed) {
                    throw new UsernameAlreadyUsedException();
                }
            });
        userRepository
            .findOneByEmailIgnoreCase(userDTO.getEmail())
            .ifPresent(existingUser -> {
                boolean removed = removeNonActivatedUser(existingUser);
                if (!removed) {
                    throw new EmailAlreadyUsedException();
                }
            });
        SysUser newUser = new SysUser();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(userDTO.getUsername().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setRealname(userDTO.getRealName());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<SysRole> sysRoles = new HashSet<>();
        sysRoleRepository.findById(AuthoritiesConstants.USER).ifPresent(sysRoles::add);
        newUser.setSysRoles(sysRoles);
        userRepository.save(newUser);
        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    private boolean removeNonActivatedUser(SysUser existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        this.clearUserCaches(existingUser);
        return true;
    }

    public SysUser createUser(SysAdminUserDto userDTO) {
        SysUser user = new SysUser();
        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setRealname(userDTO.getRealName());
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail().toLowerCase());
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetTime(Timestamp.from(Instant.now()));
        user.setActivated(true);
        if (userDTO.getSysRoles() != null) {
            Set<SysRole> authorities = userDTO
                .getSysRoles()
                .stream()
                .map(sysRoleRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setSysRoles(authorities);
        }
        userRepository.save(user);
        this.clearUserCaches(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
    public Optional<SysAdminUserDto> updateUser(SysAdminUserDto userDTO) {
        return Optional
            .of(userRepository.findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                user.setUsername(userDTO.getUsername().toLowerCase());
                user.setRealname(userDTO.getRealName());
                if (userDTO.getEmail() != null) {
                    user.setEmail(userDTO.getEmail().toLowerCase());
                }
                user.setActivated(userDTO.isActivated());
                Set<SysRole> managedAuthorities = user.getSysRoles();
                managedAuthorities.clear();
                userDTO
                    .getSysRoles()
                    .stream()
                    .map(sysRoleRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(SysAdminUserDto::new);
    }

    public void deleteUser(String usernme) {
        userRepository
            .findOneByUsername(usernme)
            .ifPresent(user -> {
                userRepository.delete(user);
                this.clearUserCaches(user);
                log.debug("Deleted User: {}", user);
            });
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param email     email id of user.
     */
    public void updateUser(String realName, String email) {
        SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByUsername)
            .ifPresent(user -> {
                user.setRealname(realName);
                if (email != null) {
                    user.setEmail(email.toLowerCase());
                }

                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    @Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByUsername)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                this.clearUserCaches(user);
                log.debug("Changed password for User: {}", user);
            });
    }

    @Transactional(readOnly = true)
    public Optional<SysUser> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByUsername(login);
    }

    @Transactional(readOnly = true)
    public Optional<SysUser> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        userRepository
            .findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreateTimeBefore(Instant.now().minus(3, ChronoUnit.DAYS))
            .forEach(user -> {
                log.debug("Deleting not activated user {}", user.getUsername());
                userRepository.delete(user);
                this.clearUserCaches(user);
            });
    }

    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    @Transactional(readOnly = true)
    public List<String> getAuthorities() {
        return sysRoleRepository.findAll().stream().map(SysRole::getRoleName).collect(Collectors.toList());
    }

    private void clearUserCaches(SysUser user) {
        Objects.requireNonNull(cacheManager.getCache(SysUserRepository.SYS_USERS_BY_LOGIN_CACHE)).evict(user.getUsername());
        if (user.getEmail() != null) {
            Objects.requireNonNull(cacheManager.getCache(SysUserRepository.SYS_USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
        }
    }
}
