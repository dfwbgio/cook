//규리
function confirmOk(object){
    var id =$('#id').val();
    if(object=='cartOk'){
        var length = $("input[name='ingredient']:checked").length;
        var ingredient = "";
        if(length > 1){
            $("input[name='ingredient']:checked").each(function(index){
                var value = $(this).val();
                if(length-1 == index){
                    ingredient += value;
                }else{
                    ingredient += value + ",";
                }
            });
        }
        $.ajax({
            url:"/recipe/cartsave",
            type:"post",
            async:"true",
            data:{"id":id,"ingredient":ingredient},
            success:function(data){
                confirmShow(data,"","cartgo");
                $('.btn_hide').text("확인");
                $('#pop_confirm_btn').text("장바구니로 가기");
            },
            error:function(){alertShow("에러","");}
        });
    }
    if(object == 'cartgo'){
        location.href="/recipe/cart?id="+id;
    }
}
function summary(){
    var sum=0;
    var delivery;
    $('input[name="total"]').each(function(index){
        sum += parseInt($(this).val());
    });
    if(sum>50000){delivery=0;}
    else{delivery=3000;}

    var tot = sum + delivery;
    $('#tot').text(addCommas(sum));
    $('#delivery').text(addCommas(delivery));
    $('#total').text(addCommas(tot));
}
function deleteIngredient(ingreNum){
    var id = $('#id').val();
    $.ajax({
        url:"/cart/ingreDelete",
        type:"post",
        async:"true",
        data:{"ingredient":ingreNum, "id":id},
        success:function(data){
            standbyShow("삭제 중입니다","");
            setTimeout(function(){
            location.reload();}, 1000);
       },
       error:function(){alertShow("에러","");}
   });
}
function tot(a){
    var num = $('#num'+a).val();
    var price = $('#eachprice'+a).val();
    var tot = num * price;
    $('#total'+a).val(tot);
    $('#totChange'+a).text(addCommas(tot));
    summary();
}
function addCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

$(document).ready(function(){
    if(win_href.includes("recipe/select") && win_search.includes("path=detail")){
        var recipe = $('#recipe').val().split("<br>");
        var recipe_div = "";
        for (var i in recipe){
            recipe_div += "<div class='recipeProcess'>"
            recipe_div +=   "<p>"+ (Number(i)+1) +"</p><input type='text' id='recipeProcess_"+i+"' name='recipeProcess_"+i+"' value='"+recipe[i]+"' readonly>";
            recipe_div += "</div>";
        }
        $('#recipeProcess').append(recipe_div);

        $('#cartClick').click(function(){
            confirmShow("해당 상품들을 장바구니에 담으시겠습니까?","담길재료내용","cartOk");
        });
    }
    if(win_href.includes("recipe/cart")){
        summary();
    }
});
