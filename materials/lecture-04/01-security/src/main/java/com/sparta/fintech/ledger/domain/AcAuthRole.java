package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/** TODO [특강 4 / 1-2-2] 역할 코드의 유일성과 필수 필드를 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class AcAuthRole extends BaseTimeEntity {

    private Long authRoleId;

    private String roleCode;

    private String roleName;

    private String description;

    protected AcAuthRole() {
    }

    public AcAuthRole(String roleCode, String roleName, String description) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.description = description;
    }

    public Long getAuthRoleId() {
        return authRoleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }
}
