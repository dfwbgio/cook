package com.mbc.cook.service.member;

import com.mbc.cook.entity.member.MemberEntity;
import com.mbc.cook.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService{
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<MemberEntity> getid(String name, String tel, String email) {
           return memberRepository.findID(name,tel,email);
    }

    @Override
    public int getpw(String id, String name, String tel, String email) {
        return memberRepository.findPW(id, name, tel, email);  // findPW 메서드 호출
    }

    @Override
    public void pwupdate(String id, String pw) {
        String encryptedPW = bCryptPasswordEncoder.encode(pw);
        memberRepository.pwupdate(id,encryptedPW);
    }

    @Override
    public Page<MemberEntity> UserControl(int page) {
        return memberRepository.findAll(PageRequest.of(page,10, Sort.by(Sort.Direction.DESC,"id")));
    }


}
