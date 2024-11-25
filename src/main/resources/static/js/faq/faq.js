$(document).ready(function(){
    $('.faq_row').on('click', function(){
        if($(this).hasClass('faq_on')){
            $(this).removeClass('faq_on');
        }else{
            $('.faq_row').removeClass('faq_on');
            $(this).toggleClass('faq_on');
        }
    });
});