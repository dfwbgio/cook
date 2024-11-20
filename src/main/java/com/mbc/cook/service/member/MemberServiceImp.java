package com.mbc.cook.service.member;

import com.mbc.cook.dto.member.MemberDTO;
import com.mbc.cook.entity.member.MemberEntity;
import com.mbc.cook.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class MemberServiceImp implements MemberService{
    @Autowired
    MemberRepository memberRepository;

    @Override
    public List<MemberEntity> getid(String name, String tel, String email) {
           return memberRepository.findID(name,tel,email);
    }

    @Override
    public List<MemberEntity> getpw(String id, String name, String tel, String email) {
        return memberRepository.findPW(id,name,tel,email);
    }

    @Override
    public void pwupdate(String id, String pw) {
        memberRepository.pwupdate(id,pw);
    }

    @Override
    public List<MemberEntity> UserControl() {
        return memberRepository.findAll();
    }


}
