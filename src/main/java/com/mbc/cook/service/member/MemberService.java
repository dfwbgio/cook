package com.mbc.cook.service.member;

import com.mbc.cook.dto.member.MemberDTO;
import com.mbc.cook.entity.member.MemberEntity;

import java.util.List;

public interface MemberService {
    List<MemberEntity> getid(String name, String tel, String email);

    List<MemberEntity> getpw(String id, String name, String tel, String email);

    void pwupdate(String id, String pw);
}
