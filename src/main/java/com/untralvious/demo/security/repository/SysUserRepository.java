package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysUser;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String> {
    Optional<SysUser> findSysUserByUsername(String username);

    String SYS_USERS_BY_LOGIN_CACHE = "sysUsersByLogin";

    String SYS_USERS_BY_EMAIL_CACHE = "sysUsersByEmail";

    Optional<SysUser> findOneByActivationKey(String activationKey);
    List<SysUser> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreateTimeBefore(Instant dateTime);
    Optional<SysUser> findOneByResetKey(String resetKey);

    Optional<SysUser> findOneByEmailIgnoreCase(String email);

    Optional<SysUser> findOneByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = SYS_USERS_BY_LOGIN_CACHE)
    Optional<SysUser> findOneWithAuthoritiesByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = SYS_USERS_BY_EMAIL_CACHE)
    Optional<SysUser> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<SysUser> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);
}
