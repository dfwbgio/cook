//규리
function confirmOk(title, subtitle, type){
    confirmShow(title,subtitle,type);
}
$(document).ready(function(){
    //요리 레시피 분할
    var recipe = $('#recipe').val().split("<br>");
    var recipe_div = "";
    for (var i in recipe){
        recipe_div += "<div class='recipeProcess'>"
        recipe_div +=   "<p>"+ (Number(i)+1) +"</p><input type='text' id='recipeProcess_"+i+"' name='recipeProcess_"+i+"' value='"+recipe[i]+"' readonly>";
        recipe_div += "</div>";
    }
    $('#recipeProcess').append(recipe_div);

    $('#cartClick').click(function(){
        confirmOk("해당 상품들을 장바구니에 담으시겠습니까?","담길재료내용","장바구니에 재료가 담겼습니다.");

    });
});
