package com.mbc.cook.dto.member;

import com.mbc.cook.entity.member.MemberEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {

    private String id;

    private String pw;

    @Pattern(regexp = "^(?:[가-힣]*$)",message = "한글만 입력 가능합니다:) ")
    private String name;

    @Size(min = 6,max = 6,message = "주민번호를 확인해주세요:) ")
    private String jumin1;

    @Size(min = 1,max = 1)
    private String jumin2;


    private String addr;
    private String streetaddr;
    private String detailaddr;

    @Pattern(regexp = "^(?:(01[016789]))*$",message = "전화번호를 확인해주세요:) ")
    @Size(min = 3,max = 3)
    private String tel1;

    @Size(min = 4,max = 4)
    private String tel2;

    @Size(min = 4,max = 4)
    private String tel3;

    @Pattern(regexp = "^(?:[a-zA-Z0-9]*$)",message = "이메일을 확인해주세요:) ")
    private String email_id;

    private String email_domain;


    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .jumin1(jumin1)
                .jumin2(jumin2)
                .addr(addr)
                .streetaddr(streetaddr)
                .detailaddr(detailaddr)
                .tel1(tel1)
                .tel2(tel2)
                .tel3(tel3)
                .email_id(email_id)
                .email_domain(email_domain)
                .build();
    }





}
