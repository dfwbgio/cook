package com.mbc.cook.service.member;

import com.mbc.cook.dto.member.MemberDTO;
import com.mbc.cook.entity.member.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    List<MemberEntity> getid(String name, String tel, String email);

    int getpw(String id, String name, String tel, String email);

    void pwupdate(String id, String pw);

    Page<MemberEntity> UserControl(int page);

}
