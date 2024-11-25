function confirmOk(){
    alertShow('랜덤','랜덤 js');
}
function randomClick() {
    $.ajax({
        url:"/randomResult",
        async:true,
        type:"post",
        success: function(data){
            var sublist =JSON.parse(data);
            var listnum=sublist.recipeRandom.length;
            var ranrdom = Math.floor(Math.random()*listnum);
            var random_div="";
            random_div+="<div class='random_div' data-seq='"+sublist.recipeRandom[ranrdom].recipe_seq+"' onclick='recipeGo(this)'>";
            random_div+="   <img src='/image/upload/"+sublist.recipeRandom[ranrdom].image+"' alt='"+sublist.recipeRandom[ranrdom].food+" 이미지'>";
            random_div+="   <p>"+sublist.recipeRandom[ranrdom].food+"</p>";
            random_div+="</div>";
            $('#random_place').html(random_div);
            randomPopShow();
            console.log(ranrdom);
        },
         error: function (request, status, error) {
            console.log("message: " + request.responseText)
        }
    });
}
function randomPopShow(){
    $('body').css('overflow', 'hidden');
    $('.random_pop').show();
}
function randomPopHide(){
    $('body').css('overflow', 'auto');
    $('.random_pop').hide();
}
function recipeGo(ths){
    var this_num = ths.dataset.seq;
    location.href="/recipe/select?num="+this_num+"&path=detail";
}