package com.mbc.cook.service.member;

import com.mbc.cook.entity.member.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService2 {

    String idCheck(String id);

    void insert(MemberEntity memberDTO);

    MemberEntity mypage(String loginId);

    String pwchk(String id);
}
