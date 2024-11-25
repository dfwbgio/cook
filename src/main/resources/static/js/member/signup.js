//시아
//시아

$(document).ready(function(){

    //주민번호 maxLength만큼 입력했을 시 커서 자동 이동
    $(".jumin1").keyup(function(){
        if(this.value.length == this.maxLength) {
            $(".jumin2").focus();
        }
    })

    //전화번호 maxLength만큼 입력했을 시 커서 자동 이동(첫번째->두번째)
    $("#tel1").keyup(function(){
        if(this.value.length == this.maxLength) {
            $("#tel2").focus();
        }
    })

    //전화번호 maxLength만큼 입력했을 시 커서 자동 이동(두번째->세번째)
    $("#tel2").keyup(function(){
        if(this.value.length == this.maxLength) {
            $("#tel3").focus();
        }
    })

    //비밀번호 보기 구현
    $('.pw i').on('click',function(){
        $('input').toggleClass('active');
        if($('input').hasClass('active')){
            $(this).attr('class',"fa fa-eye fa-lg")
            .prev('input').attr('type',"text");
        }else{
            $(this).attr('class',"fa fa-eye-slash fa-lg")
            .prev('input').attr('type','password');
        }
    });
    //아이디 유효성 검사후 중복체크
    $("#idcheck").click(function(){
        var id = $("#id").val();
        var vid=/^[a-zA-Z0-9]{4,16}$/;
        if(id==""){
            alertShow('오류','아이디를 입력해주세요.');
            return false;
        }
        else if(!vid.test(id)){
            alertShow('오류','아이디는 영문자와 숫자로만 4~16글자 이내로만 작성해야합니다.');
            return false;
        }
        $.ajax({
            type:"post",
            url:"/idcheck",
            async:true,
            data:{"id":id},
            success:function(data){
                if(data == null || data == "null" || data == ""){
                    alertShow('중복확인',"사용가능한 아이디 입니다.");
                    $('#idcheck2').val(0);
                }else{
                    alertShow('중복확인',"이미 사용중인 아이디 입니다.");
                    $('#idcheck2').val(1);
                }
            },
            error:function(){
                alertShow('에러','아이디를 다시 입력해주세요');
            }
        });
    });
});
//주소 API CDN 방식 사용
function execDaumPostcode(){
    new daum.Postcode({
        oncomplete: function(data){
            // 팝업을 통한 검색 결과 항목 클릭 시 실행
            var addr = ''; // 주소_결과값이 없을 경우 공백
            var extraAddr = ''; // 참고항목
            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R'){ // 도로명 주소를 선택
                addr = data.roadAddress;
            } else{ // 지번 주소를 선택
                addr = data.jibunAddress;
            }
            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            } else{
                document.getElementById("streetaddr").value = '';
            }
            // 선택된 우편번호와 주소 정보를 input 박스에 넣는다.
            document.getElementById('addr').value = data.zonecode;
            document.getElementById("streetaddr").value = addr;
            document.getElementById("streetaddr").value += extraAddr;
            document.getElementById("detailaddr").focus(); // 우편번호 + 주소 입력이 완료되었음으로 상세주소로 포커스 이동
        }
    }).open();
}
//비밀번호 일치 유무
function passwordCheck(){
    var pw = $("#pw").val();
    var pwcheck = $("#pwcheck").val();
    var pw_message = document.getElementById("pw_message");	//확인 메세지
    var correctColor = "#3d7797";	//맞았을 때 출력되는 색깔.
    var wrongColor = "#ff0000";		//틀렸을 때 출력되는 색깔
    if(pw == pwcheck){ //password 변수의 값과 passwordConfirm 변수의 값과 동일하다.
        if(pw.length<6 ||pw.length>16){
            pw_message.style.color = wrongColor;
            pw_message.innerHTML = "비밀번호는 6~16자 이내로 입력해주세요.";
        }
        else{
            pw_message.style.color = correctColor;/* span 태그의 ID(confirmMsg) 사용  */
            pw_message.innerHTML = " 비밀번호가 일치합니다.";/* innerHTML : HTML 내부에 추가적인 내용을 넣을 때 사용하는 것. */
        }
    }else{
        pw_message.style.color = wrongColor;
        pw_message.innerHTML = " 비밀번호가 일치하지 않습니다.";
    }
}
//중복확인 후 아이디 수정 시 다시 중복확인 구현
function idcheck_reset(){
    $('#idcheck2').val(1);
}
//이메일 도메인에 값 넣기
function mailSelect(){
    var domain = $('#domain_select').find('option:selected').val();
    if(domain == 'emailall'){
        $('#email_domain').val("");
    }
    else{
        $('#email_domain').val(domain);
    }
}
//마이페이지에서 비밀번호 수정 버튼을 눌렀을 때 비밀번호 입력란이 나오도록 구현
function pw_show() {
    $("#or_pw").remove();
     var pw_div = "";
     pw_div += "<div class='pw'>";
     pw_div += "<div><input type='password' class='form-control' id='pw' name='pw' placeholder='수정할 비밀번호를 입력하세요'>";
     pw_div += "<i class='fa fa-eye-slash fa-lg'></i></div></div>";
     pw_div += "<div class='pw'>";
     pw_div += "<label for='pwcheck'>비밀번호 확인</label>";
     pw_div += "<div class='pw1'>";
     pw_div += "<input type='password' name='pwcheck' id='pwcheck' placeholder='비밀번호를 한번 더 입력' onkeyup='passwordCheck()'>";
     pw_div += "<i class='fa fa-eye-slash fa-lg'></i>";
     pw_div += "<span id='pw_message'></span></div></div>";
     $("#update_pw").html(pw_div);

    //비밀번호 일치 유무
    function passwordCheck(){
        var pw = $("#pw").val();
        var pwcheck = $("#pwcheck").val();
        var pw_message = document.getElementById("pw_message");	//확인 메세지
        var correctColor = "#3d7797";	//맞았을 때 출력되는 색깔.
        var wrongColor = "#ff0000";		//틀렸을 때 출력되는 색깔
        if(pw == pwcheck){ //password 변수의 값과 passwordConfirm 변수의 값과 동일하다.
            if(pw.length<6 ||pw.length>16){
                pw_message.style.color = wrongColor;
                pw_message.innerHTML = "비밀번호는 6~16자 이내로 입력해주세요.";
            }
            else{
                pw_message.style.color = correctColor;/* span 태그의 ID(confirmMsg) 사용  */
                pw_message.innerHTML = " 비밀번호가 일치합니다.";/* innerHTML : HTML 내부에 추가적인 내용을 넣을 때 사용하는 것. */
            }
        }else{
            pw_message.style.color = wrongColor;
            pw_message.innerHTML = " 비밀번호가 일치하지 않습니다.";
        }
    }
     //비밀번호 보기 구현
     $('.pw i').on('click',function(){
         $('input').toggleClass('active');
         if($('input').hasClass('active')){
             $(this).attr('class',"fa fa-eye fa-lg")
             .prev('input').attr('type',"text");
         }else{
             $(this).attr('class',"fa fa-eye-slash fa-lg")
             .prev('input').attr('type','password');
         }
     });
}