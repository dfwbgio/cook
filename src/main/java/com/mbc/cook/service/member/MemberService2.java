package com.mbc.cook.service.member;

import com.mbc.cook.dto.member.MemberDTO;
import com.mbc.cook.entity.member.MemberEntity;
import org.springframework.stereotype.Service;

@Service
public interface MemberService2 {
    String idCheck(String id);

    void insert(MemberDTO memberDTO);
}
