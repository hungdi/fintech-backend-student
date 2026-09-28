package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/** TODO [특강 1 / 1-2] 고객 식별자와 고객번호의 유일성, 필수 필드의 DB 매핑을 작성하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class CmCustomer extends BaseTimeEntity {

    private Long customerId;

    private String customerNo;

    private String name;

    private String email;

    private String phoneNumber;

    private CustomerStatus status;

    protected CmCustomer() {
    }

    public CmCustomer(String customerNo, String name, String email, String phoneNumber) {
        this.customerNo = customerNo;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = CustomerStatus.ACTIVE;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerStatus getStatus() {
        return status;
    }
}
