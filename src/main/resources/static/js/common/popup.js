//팝업
//팝업 공통 닫기
function commonHide(){
    $('body').css('overflow', 'auto');
    $('.common_pop').hide();
}
//alert
function alertHide(){
    $('body').css('overflow', 'auto');
    $('.alert_whole').hide();
}
function alertShow(title, subtitle){
    $('body').css('overflow', 'hidden');
    $('.alert_whole').show();
    $('#alert_title').html(title);
    $('#alert_subtitle').html(subtitle);
}
//confirm
function confirmHide(){
    $('body').css('overflow', 'auto');
    $('.confirm_whole').hide();
}
function confirmShow(title, subtitle, type){
    $('body').css('overflow', 'hidden');
    $('.confirm_whole').show();
    $('#confirm_title').html(title);
    $('#confirm_subtitle').html(subtitle);
    $('#confirm_message').attr('value', type);
}
function popConfirm(){
    let confirm_message = $('#confirm_message').val();
    if(confirm_message == 'none'){
        commonHide();
    }
    else{
        confirmHide();
        confirmOk(confirm_message);
    }
}
//standby
function standbyHide(){
    $('body').css('overflow', 'auto');
    $('.standby_whole').hide();
}
function standbyShow(title, subtitle){
    $('body').css('overflow', 'hidden');
    $('.standby_whole').show();
    $('#standby_title').html(title);
    $('#standby_subtitle').html(subtitle);
}

function mypageChk() {
    $("body").css('overflow', 'hidden');
    $('.pwchk_whole').show();
}
//mypage 들어갈 때 pwchk
function pwchk() {
    console.log("click");
    var id = $("#header_mypage").text();
    var pw= $("#chkpw").val();
    $.ajax({
        type:"post",
        url:"/pwchk",
        async:true,
        data:{"id":id,"pw":pw},
        success:function(data){
            if(data){
                 location.href="/mypage?id="+id;
            } else {
                return false;
            }
        }
    });
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