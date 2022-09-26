package com.untralvious.demo.security.service.dto;

import com.untralvious.demo.security.config.Constants;
import com.untralvious.demo.security.domain.SysRole;
import com.untralvious.demo.security.domain.SysUser;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SysAdminUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String username;

    @Size(max = 50)
    private String realName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    private boolean activated = false;

    private String createBy;

    private Timestamp createTime;

    private String updateBy;

    private Timestamp updateTime;

    private Set<String> sysRoles;

    public SysAdminUserDto() {
        // Empty constructor needed for Jackson.
    }

    public SysAdminUserDto(SysUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.realName = user.getRealname();
        this.email = user.getEmail();
        //        this.activated = user.isActivated();
        this.createTime = user.getCreateTime();
        this.createBy = user.getCreateBy();
        this.updateTime = user.getUpdateTime();
        this.updateBy = user.getUpdateBy();
        this.sysRoles = user.getAuthorities().stream().map(SysRole::getRoleName).collect(Collectors.toSet());
    }

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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

    public Set<String> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(Set<String> sysRoles) {
        this.sysRoles = sysRoles;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdminUserDTO{" +
            "login='" + username + '\'' +
            ", firstName='" + realName + '\'' +
            ", email='" + email + '\'' +
            ", activated=" + activated +
            ", createdBy=" + createBy +
            ", createTime=" + createTime +
            ", updateBy='" + updateBy + '\'' +
            ", updateTime=" + updateTime +
            ", authorities=" + sysRoles +
            "}";
    }
}
