package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, String> {}
