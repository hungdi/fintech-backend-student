package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/** TODO [특강 4 / 1-2-2] 사용자와 역할의 관계 및 같은 역할 중복 연결 방지를 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class AmAuthUserRole extends BaseTimeEntity {

    private Long authUserRoleId;

    private AmAuthUser authUser;

    private AcAuthRole authRole;

    protected AmAuthUserRole() {
    }

    public AmAuthUserRole(AmAuthUser authUser, AcAuthRole authRole) {
        this.authUser = authUser;
        this.authRole = authRole;
    }

    public Long getAuthUserRoleId() {
        return authUserRoleId;
    }

    public AmAuthUser getAuthUser() {
        return authUser;
    }

    public AcAuthRole getAuthRole() {
        return authRole;
    }
}
