package com.mbc.cook.entity.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "usermember")
public class MemberEntity {
    @Id
    @Column
    private String id;

    @Column
    private String pw;

    @Column
    private String name;

    @Column
    private String jumin1;

    @Column
    private String jumin2;

    @Column
    private String addr;

    @Column
    private String streetaddr;

    @Column
    private String detailaddr;

    @Column
    private String tel1;

    @Column
    private String tel2;

    @Column
    private String tel3;

    @Column
    private String email_id;
    @Column
    private String email_domain;

    @Builder
    public MemberEntity(String id, String pw, String name, String jumin1, String jumin2, String addr, String streetaddr, String detailaddr, String tel1,String tel2,String tel3, String email_id, String email_domain) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.jumin1 = jumin1;
        this.jumin2 = jumin2;
        this.addr = addr;
        this.streetaddr = streetaddr;
        this.detailaddr = detailaddr;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
        this.email_id = email_id;
        this.email_domain = email_domain;
    }

}
