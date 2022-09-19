package com.untralvious.demo.security.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sys_role", schema = "securityexample", catalog = "")
public class SysRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;
    @Basic
    @Column(name = "role_name", nullable = true, length = 200)
    private String roleName;
    @Basic
    @Column(name = "role_code", nullable = false, length = 100)
    private String roleCode;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @Basic
    @Column(name = "create_by", nullable = true, length = 32)
    private String createBy;
    @Basic
    @Column(name = "create_time", nullable = true)
    private Timestamp createTime;
    @Basic
    @Column(name = "update_by", nullable = true, length = 32)
    private String updateBy;
    @Basic
    @Column(name = "update_time", nullable = true)
    private Timestamp updateTime;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sysRoles")
    private Set<SysUser> sysUsers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_role_permission",
        joinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)},
        inverseJoinColumns = { @JoinColumn(name = "permission_id", nullable = false, updatable = false) })
    private Set<SysPermission> sysPermissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<SysPermission> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(Set<SysPermission> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Set<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(Set<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(id, sysRole.id) && Objects.equals(roleName, sysRole.roleName) && Objects.equals(roleCode, sysRole.roleCode) && Objects.equals(description, sysRole.description) && Objects.equals(createBy, sysRole.createBy) && Objects.equals(createTime, sysRole.createTime) && Objects.equals(updateBy, sysRole.updateBy) && Objects.equals(updateTime, sysRole.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, roleCode, description, createBy, createTime, updateBy, updateTime);
    }
}
