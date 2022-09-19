package com.untralvious.demo.security.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sys_user", schema = "securityexample", catalog = "")
public class SysUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;
    @Basic
    @Column(name = "username", nullable = true, length = 100)
    private String username;
    @Basic
    @Column(name = "realname", nullable = true, length = 100)
    private String realname;
    @Basic
    @Column(name = "password", nullable = true, length = 255)
    private String password;
    @Basic
    @Column(name = "salt", nullable = true, length = 45)
    private String salt;
    @Basic
    @Column(name = "avatar", nullable = true, length = 255)
    private String avatar;
    @Basic
    @Column(name = "birthday", nullable = true)
    private Timestamp birthday;
    @Basic
    @Column(name = "sex", nullable = true)
    private Byte sex;
    @Basic
    @Column(name = "email", nullable = true, length = 45)
    private String email;
    @Basic
    @Column(name = "phone", nullable = true, length = 45)
    private String phone;
    @Basic
    @Column(name = "org_code", nullable = true, length = 64)
    private String orgCode;
    @Basic
    @Column(name = "status", nullable = true)
    private Byte status;
    @Basic
    @Column(name = "del_flag", nullable = true)
    private Byte delFlag;
    @Basic
    @Column(name = "third_id", nullable = true, length = 100)
    private String thirdId;
    @Basic
    @Column(name = "third_type", nullable = true, length = 100)
    private String thirdType;
    @Basic
    @Column(name = "activiti_sync", nullable = true)
    private Byte activitiSync;
    @Basic
    @Column(name = "work_no", nullable = true, length = 100)
    private String workNo;
    @Basic
    @Column(name = "post", nullable = true, length = 100)
    private String post;
    @Basic
    @Column(name = "telephone", nullable = true, length = 45)
    private String telephone;
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
    @Basic
    @Column(name = "user_identity", nullable = true)
    private Byte userIdentity;
    @Basic
    @Column(name = "depart_ids", nullable = true, length = -1)
    private String departIds;
    @Basic
    @Column(name = "rel_tenant_ids", nullable = true, length = 100)
    private String relTenantIds;
    @Basic
    @Column(name = "client_id", nullable = true, length = 64)
    private String clientId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role",
        joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
        inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
    private Set<SysRole> sysRoles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public Byte getActivitiSync() {
        return activitiSync;
    }

    public void setActivitiSync(Byte activitiSync) {
        this.activitiSync = activitiSync;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Byte getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Byte userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getDepartIds() {
        return departIds;
    }

    public void setDepartIds(String departIds) {
        this.departIds = departIds;
    }

    public String getRelTenantIds() {
        return relTenantIds;
    }

    public void setRelTenantIds(String relTenantIds) {
        this.relTenantIds = relTenantIds;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Set<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(Set<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equals(id, sysUser.id) && Objects.equals(username, sysUser.username) && Objects.equals(realname, sysUser.realname) && Objects.equals(password, sysUser.password) && Objects.equals(salt, sysUser.salt) && Objects.equals(avatar, sysUser.avatar) && Objects.equals(birthday, sysUser.birthday) && Objects.equals(sex, sysUser.sex) && Objects.equals(email, sysUser.email) && Objects.equals(phone, sysUser.phone) && Objects.equals(orgCode, sysUser.orgCode) && Objects.equals(status, sysUser.status) && Objects.equals(delFlag, sysUser.delFlag) && Objects.equals(thirdId, sysUser.thirdId) && Objects.equals(thirdType, sysUser.thirdType) && Objects.equals(activitiSync, sysUser.activitiSync) && Objects.equals(workNo, sysUser.workNo) && Objects.equals(post, sysUser.post) && Objects.equals(telephone, sysUser.telephone) && Objects.equals(createBy, sysUser.createBy) && Objects.equals(createTime, sysUser.createTime) && Objects.equals(updateBy, sysUser.updateBy) && Objects.equals(updateTime, sysUser.updateTime) && Objects.equals(userIdentity, sysUser.userIdentity) && Objects.equals(departIds, sysUser.departIds) && Objects.equals(relTenantIds, sysUser.relTenantIds) && Objects.equals(clientId, sysUser.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, realname, password, salt, avatar, birthday, sex, email, phone, orgCode, status, delFlag, thirdId, thirdType, activitiSync, workNo, post, telephone, createBy, createTime, updateBy, updateTime, userIdentity, departIds, relTenantIds, clientId);
    }
}
