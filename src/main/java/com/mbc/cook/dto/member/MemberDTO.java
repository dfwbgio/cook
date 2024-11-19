package com.mbc.cook.dto.member;

import com.mbc.cook.entity.member.MemberEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {

    private String id;

    private String pw;

    @ NotBlank(message = "이름은 필수로 입력해주세요:) ")
    @Pattern(regexp = "^(?:[가-힣]*$)",message = "한글만 입력 가능합니다:) ")
    private String name;

    private String jumin;

    private String addr;
    private String streetaddr;
    private String detailaddr;

    @ NotBlank(message = "전화번호는 필수로 입력해주세요:) ")
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",message = "전화번호를 확인해주세요:) ")
    private String tel;

    @ NotBlank(message = "이메일은 필수로 입력해주세요:) ")
    @Email(message = "이메일을 확인해주세요:) ")
    private String email;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .jumin(jumin)
                .addr(addr)
                .streetaddr(streetaddr)
                .detailaddr(detailaddr)
                .tel(tel)
                .email(email)
                .build();
    }

}
