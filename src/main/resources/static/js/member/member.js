//현준

    $('#is').click(function(){ //아이디 찾기 팝업창
        var windowFeatures = "top=300, popup=yes";
        var popup = window.open('idsearch', "_blank", windowFeatures);
        popup.resizeTo(650,500);
    });

    $('#ps').click(function(){ //비밀번호 찾기 팝업창
        var windowFeatures = "top=300, popup=yes";
        var popup = window.open('pwsearch', "_blank", windowFeatures);
        popup.resizeTo(650,500);
    });

    $(document).ready(function() {
        $('#name').focus();

        $('i').on('click',function(){
            $('input').toggleClass('active');
            if($('input').hasClass('active')){
                $(this).attr('class',"fa fa-eye fa-lg")
                .prev('input').attr('type',"text");
            }else{
                $(this).attr('class',"fa fa-eye-slash fa-lg")
                .prev('input').attr('type','password');
            }
        });
    });

    function click_btn(){
        if(window.event.keyCode == 13){
            $('#findid').click();
        }
    };

    function click_btn(){
    		if(window.event.keyCode == 13){
    			$('#findpw').click();
    		}
    	};
    $(document).ready(function() {
        $('#id').focus();
    });

    function checkform(){ //유효성 검사
        if(win_href.includes('/pwsearch')){ //pwsearch가 포함되어있으면 실행
            var id = $('#id').val();
            var cid = /^[a-zA-Z0-9]{4,10}$/;
            if(id==""){
                alertShow('아이디를 입력해주세요','');
                $('#id').focus();
                return false;
            }
            if(!cid.test(id)){
                alertShow('오류','아이디는 영문자+숫자 4~10글자 이내로 입력해주세요');
                $('#id').focus();
                return false;
            }
        }
        var vname=/^[가-힣]{2,5}$/;
        var name=$('#name').val();
        if(name==""){
            alertShow('이름을 입력해주세요.','');
            $('#name').focus();
            return false;
        }
        if(!vname.test(name)){
            alertShow('이름은 한글로 2~5자만 입력해주세요.');
            $('#name').focus();
            return false;
        }

        var fir_tel = $("select[id='fir_tel'] option:selected").val();
        var mid_tel = $('#mid_tel').val();
        var end_tel = $('#end_tel').val();
        var tel = fir_tel + "-" + mid_tel + "-" + end_tel;
        var tel_regexp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
        if(!tel_regexp.test(tel)){
            alertShow('오류','전화번호를 다시 입력해주세요');
            $('#mid_tel').focus();
            return false;
        }

        var email = $('#email_id').val();
        var domain = $('#email_domain').val();
        var email_regexp =/^[a-zA-Z0-9]{1,15}$/;
        if(email==""){
            alertShow('오류','이메일을 입력해주세요.');
            $('#email_id').focus();
            return false;
        }
        if(domain==""){
            alertShow('오류','도메인을 입력해주세요');
            $('#email_domain').focus();
            return false;
        }
        if(!email_regexp.test(email)){
            alertShow('오류','이메일은 영문자+숫자만 입력해주세요.');
            $('#email_id').focus();
            return false;
        }
        return true;
    };

    function checkform1(){ //패스워드 유효성 검사
        var pw=$('#pw').val();
        var pwcheck =$('#pwcheck').val();
        var vpw =/^[a-zA-Z0-9]{6,16}$/;
        if(pw==""){
            alertShow('오류','비밀번호를 입력해주세요');
            $('#pw').focus();
            return false;
        }
        else if(pwcheck==""){
            alertShow('오류','비밀번호 확인을 입력해주세요');
            $('#pwcheck').focus();
            return false;
        }
        else if(!vpw.test(pw)){
            alertShow('오류','비밀번호는 영문자+숫자 6~16글자 이내로 입력해주세요');
            $('#pw').focus();
            return false;
        }
        else if(pw != pwcheck){
            alertShow('오류','새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            $('#pwcheck').focus();
            return false;
        }
        else{return true};
    };

    function findID() { //아이디 찾기
        var name=$('#name').val();
        var fir_tel = $("select[id='fir_tel'] option:selected").val();
        var mid_tel = $('#mid_tel').val();
        var end_tel = $('#end_tel').val();
        var tel = fir_tel + "-" + mid_tel + "-" + end_tel;
        var email1=$('#email_id').val();
        var email2=$('#email_domain').val();
        var email = email1 + "@" + email2;
        if(checkform()){
            $.ajax({
                type:"post",
                url:"/getid",
                async:true,
                data:{"name":name, "tel":tel, "email":email},
                success:function(data){
                    alertShow('아이디 찾기',data);
                },
                error:function(){
                    alertShow("다시 입력해주세요.","");
                }
            });
        }
    };

    function findPW() { //패스워드찾기
        var id=$('#id').val();
        var name=$('#name').val();
        var fir_tel = $("select[id='fir_tel'] option:selected").val();
        var mid_tel = $('#mid_tel').val();
        var end_tel = $('#end_tel').val();
        var tel = fir_tel + "-" + mid_tel + "-" + end_tel;
        var email1=$('#email_id').val();
        var email2=$('#email_domain').val();
        var email = email1 + "@" + email2;
        if(checkform()){
            $.ajax({
                type:"post",
                url:"/getpw",
                async:true,
                data:{"id":id, "name":name, "tel":tel, "email":email},
                success:function(data){
                    if(data==1){
                        location.href='pwupdate?id='+id;
                    }
                    else{
                        alertShow('회원정보가 없습니다.','');
                    }
                },
                error:function(){
                    alertShow("다시 입력해주세요.","");
                }
            });
        }
    };
    function check(){ //패스워드 변경
        var id= $('#id').val();
        var pw = $('#pw').val();
        var pwcheck = $('#pwcheck').val();
        if(checkform1()){
            $.ajax({
                type: "post",
                url: "/pwUpdate",
                async: true,
                data: {"id":id,"pw":pw},
                success:function(){
                    alertShow('변경 완료','비밀번호 변경이 완료되었습니다.');
                    setTimeout(function(){window.close();}, 10000);
                },
                error:function(){
                    alertShow("오류",'비밀번호를 다시 입력해주세요');
                }
            });
        }
    };



    //이메일 도메인에 값 넣기
    function mailSelect(){
        var domain = $('#domain_select').find('option:selected').val();
        if(domain == 'emailall'){
            $('#email_domain').val("");
        }
        else{
            $('#email_domain').val(domain);
        }
    };

    function email_concat() {
        var email_val1 = $('#email_id').val();
        var email_val2 = $('#email_domain').val();
        var total_email = email_val1+"@"+email_val2;
        $('#total_email').val(total_email);
    };


