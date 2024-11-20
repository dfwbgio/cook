package com.mbc.cook.repository.member;

import com.mbc.cook.dto.member.MemberDTO;
import com.mbc.cook.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,String> {
    @Transactional
    @Modifying
    @Query(value = "select id, pw, name, tel, jumin, address, email from member1115" +
            " where member1115.name=:name and member1115.tel=:tel and" +
            " member1115.email=:email", nativeQuery = true)
    List<MemberEntity> findID(@Param("name") String name, @Param("tel") String tel, @Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "select id, pw, name, tel, jumin, address, email from member1115" +
            " where id=:id and name=:name and tel=:tel and" +
            " email=:email", nativeQuery = true)
    List<MemberEntity> findPW(@Param("id") String id, @Param("name") String name, @Param("tel") String tel, @Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "update member1115 set pw=:pw where id=:id", nativeQuery = true)
    void pwupdate(@Param("id") String id, @Param("pw") String pw);
}