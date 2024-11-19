package com.mbc.cook.service.member;

import com.mbc.cook.dto.member.MemberDTO;
import com.mbc.cook.entity.member.MemberEntity;
import com.mbc.cook.repository.member.MemberRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceIMP2 implements MemberService2 {

    @Autowired
    MemberRepository2 memberRepository2;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String idCheck(String id) {
        return memberRepository2.idcheck(id);
    }

    @Override
    public void insert(MemberDTO memberDTO) {
        memberDTO.setPw(bCryptPasswordEncoder.encode(memberDTO.getPw()));
        memberRepository2.save(memberDTO.toEntity());
    }

}
