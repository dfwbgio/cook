package com.mbc.cook.service.member;

import com.mbc.cook.dto.member.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService2 {
    String idCheck(String id);

    void insert(MemberDTO memberDTO);
}
