package com.untralvious.demo.security.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sys_permission", schema = "securityexample", catalog = "")
public class SysPermission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;
    @Basic
    @Column(name = "parent_id", nullable = true, length = 32)
    private String parentId;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "url", nullable = true, length = 255)
    private String url;
    @Basic
    @Column(name = "component", nullable = true, length = 255)
    private String component;
    @Basic
    @Column(name = "component_name", nullable = true, length = 100)
    private String componentName;
    @Basic
    @Column(name = "redirect", nullable = true, length = 255)
    private String redirect;
    @Basic
    @Column(name = "menu_type", nullable = true)
    private Integer menuType;
    @Basic
    @Column(name = "perms", nullable = true, length = 255)
    private String perms;
    @Basic
    @Column(name = "perms_type", nullable = true, length = 10)
    private String permsType;
    @Basic
    @Column(name = "sort_no", nullable = true, precision = 2)
    private Double sortNo;
    @Basic
    @Column(name = "always_show", nullable = true)
    private Byte alwaysShow;
    @Basic
    @Column(name = "icon", nullable = true, length = 100)
    private String icon;
    @Basic
    @Column(name = "is_route", nullable = true)
    private Byte isRoute;
    @Basic
    @Column(name = "is_leaf", nullable = true)
    private Byte isLeaf;
    @Basic
    @Column(name = "keep_alive", nullable = true)
    private Byte keepAlive;
    @Basic
    @Column(name = "hidden", nullable = true)
    private Byte hidden;
    @Basic
    @Column(name = "hide_tab", nullable = true)
    private Byte hideTab;
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
    @Basic
    @Column(name = "del_flag", nullable = true)
    private Integer delFlag;
    @Basic
    @Column(name = "rule_flag", nullable = true)
    private Integer ruleFlag;
    @Basic
    @Column(name = "status", nullable = true, length = 2)
    private String status;
    @Basic
    @Column(name = "internal_or_external", nullable = true)
    private Byte internalOrExternal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getPermsType() {
        return permsType;
    }

    public void setPermsType(String permsType) {
        this.permsType = permsType;
    }

    public Double getSortNo() {
        return sortNo;
    }

    public void setSortNo(Double sortNo) {
        this.sortNo = sortNo;
    }

    public Byte getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Byte alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getIsRoute() {
        return isRoute;
    }

    public void setIsRoute(Byte isRoute) {
        this.isRoute = isRoute;
    }

    public Byte getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Byte isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Byte getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Byte keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Byte getHidden() {
        return hidden;
    }

    public void setHidden(Byte hidden) {
        this.hidden = hidden;
    }

    public Byte getHideTab() {
        return hideTab;
    }

    public void setHideTab(Byte hideTab) {
        this.hideTab = hideTab;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getRuleFlag() {
        return ruleFlag;
    }

    public void setRuleFlag(Integer ruleFlag) {
        this.ruleFlag = ruleFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Byte getInternalOrExternal() {
        return internalOrExternal;
    }

    public void setInternalOrExternal(Byte internalOrExternal) {
        this.internalOrExternal = internalOrExternal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPermission that = (SysPermission) o;
        return Objects.equals(id, that.id) && Objects.equals(parentId, that.parentId) && Objects.equals(name, that.name) && Objects.equals(url, that.url) && Objects.equals(component, that.component) && Objects.equals(componentName, that.componentName) && Objects.equals(redirect, that.redirect) && Objects.equals(menuType, that.menuType) && Objects.equals(perms, that.perms) && Objects.equals(permsType, that.permsType) && Objects.equals(sortNo, that.sortNo) && Objects.equals(alwaysShow, that.alwaysShow) && Objects.equals(icon, that.icon) && Objects.equals(isRoute, that.isRoute) && Objects.equals(isLeaf, that.isLeaf) && Objects.equals(keepAlive, that.keepAlive) && Objects.equals(hidden, that.hidden) && Objects.equals(hideTab, that.hideTab) && Objects.equals(description, that.description) && Objects.equals(createBy, that.createBy) && Objects.equals(createTime, that.createTime) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag) && Objects.equals(ruleFlag, that.ruleFlag) && Objects.equals(status, that.status) && Objects.equals(internalOrExternal, that.internalOrExternal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name, url, component, componentName, redirect, menuType, perms, permsType, sortNo, alwaysShow, icon, isRoute, isLeaf, keepAlive, hidden, hideTab, description, createBy, createTime, updateBy, updateTime, delFlag, ruleFlag, status, internalOrExternal);
    }
}
