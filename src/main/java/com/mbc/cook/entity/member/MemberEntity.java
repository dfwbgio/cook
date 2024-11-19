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
    private String jumin;

    @Column
    private String addr;

    @Column
    private String streetaddr;

    @Column
    private String detailaddr;

    @Column
    private String tel;

    @Column
    private String email;

    @Builder
    public MemberEntity(String id, String pw, String name, String jumin, String addr, String streetaddr, String detailaddr, String tel, String email) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.jumin = jumin;
        this.addr = addr;
        this.streetaddr = streetaddr;
        this.detailaddr = detailaddr;
        this.tel = tel;
        this.email = email;
    }

}
