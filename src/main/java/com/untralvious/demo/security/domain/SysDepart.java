package com.untralvious.demo.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "sys_depart", schema = "securityexample", catalog = "")
public class SysDepart implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    @Basic
    @Column(name = "parent_id", nullable = true, length = 32)
    private String parentId;

    @Basic
    @Column(name = "depart_name", nullable = false, length = 100)
    private String departName;

    @Basic
    @Column(name = "depart_name_en", nullable = true, length = 500)
    private String departNameEn;

    @Basic
    @Column(name = "depart_name_abbr", nullable = true, length = 500)
    private String departNameAbbr;

    @Basic
    @Column(name = "depart_order", nullable = true)
    private Integer departOrder;

    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Basic
    @Column(name = "org_category", nullable = false, length = 10)
    private String orgCategory;

    @Basic
    @Column(name = "org_type", nullable = true, length = 10)
    private String orgType;

    @Basic
    @Column(name = "org_code", nullable = false, length = 64)
    private String orgCode;

    @Basic
    @Column(name = "mobile", nullable = true, length = 32)
    private String mobile;

    @Basic
    @Column(name = "fax", nullable = true, length = 32)
    private String fax;

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    private String address;

    @Basic
    @Column(name = "memo", nullable = true, length = 500)
    private String memo;

    @Basic
    @Column(name = "status", nullable = true, length = 1)
    private String status;

    @Basic
    @Column(name = "del_flag", nullable = true, length = 1)
    private String delFlag;

    @Basic
    @Column(name = "qywx_identifier", nullable = true, length = 100)
    private String qywxIdentifier;

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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sysDeparts")
    private Set<SysUser> sysUsers;

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

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDepartNameEn() {
        return departNameEn;
    }

    public void setDepartNameEn(String departNameEn) {
        this.departNameEn = departNameEn;
    }

    public String getDepartNameAbbr() {
        return departNameAbbr;
    }

    public void setDepartNameAbbr(String departNameAbbr) {
        this.departNameAbbr = departNameAbbr;
    }

    public Integer getDepartOrder() {
        return departOrder;
    }

    public void setDepartOrder(Integer departOrder) {
        this.departOrder = departOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrgCategory() {
        return orgCategory;
    }

    public void setOrgCategory(String orgCategory) {
        this.orgCategory = orgCategory;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getQywxIdentifier() {
        return qywxIdentifier;
    }

    public void setQywxIdentifier(String qywxIdentifier) {
        this.qywxIdentifier = qywxIdentifier;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDepart sysDepart = (SysDepart) o;
        return (
            Objects.equals(id, sysDepart.id) &&
            Objects.equals(parentId, sysDepart.parentId) &&
            Objects.equals(departName, sysDepart.departName) &&
            Objects.equals(departNameEn, sysDepart.departNameEn) &&
            Objects.equals(departNameAbbr, sysDepart.departNameAbbr) &&
            Objects.equals(departOrder, sysDepart.departOrder) &&
            Objects.equals(description, sysDepart.description) &&
            Objects.equals(orgCategory, sysDepart.orgCategory) &&
            Objects.equals(orgType, sysDepart.orgType) &&
            Objects.equals(orgCode, sysDepart.orgCode) &&
            Objects.equals(mobile, sysDepart.mobile) &&
            Objects.equals(fax, sysDepart.fax) &&
            Objects.equals(address, sysDepart.address) &&
            Objects.equals(memo, sysDepart.memo) &&
            Objects.equals(status, sysDepart.status) &&
            Objects.equals(delFlag, sysDepart.delFlag) &&
            Objects.equals(qywxIdentifier, sysDepart.qywxIdentifier) &&
            Objects.equals(createBy, sysDepart.createBy) &&
            Objects.equals(createTime, sysDepart.createTime) &&
            Objects.equals(updateBy, sysDepart.updateBy) &&
            Objects.equals(updateTime, sysDepart.updateTime)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            parentId,
            departName,
            departNameEn,
            departNameAbbr,
            departOrder,
            description,
            orgCategory,
            orgType,
            orgCode,
            mobile,
            fax,
            address,
            memo,
            status,
            delFlag,
            qywxIdentifier,
            createBy,
            createTime,
            updateBy,
            updateTime
        );
    }
}
