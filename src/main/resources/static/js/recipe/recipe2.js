//혜린
//등록
//카테고리 클릭 시 유효성검사
//json 이용하여 세부 카테고리를 가져오기 위함
function recipeCategoryClick(){
    var recipe_category_val = $('input[name="category1"]:checked').val();
    $.ajax({
        type: "post",
        url: "/subCategoryGet",
        async: true,
        data: {"category" : recipe_category_val},
        success: function(data){
            var sublist =JSON.parse(data);
            var category_div = "";
            category_div += "<p>레시피 소분류</p>";
            category_div += "<div class='register_category display_flex flex_align_c'>";
            for(var i in sublist.subcategorylist){
                category_div += "   <label for='recipe_category2_"+i+"'>";
                category_div += "       <input type='radio' id='recipe_category2_"+i+"' name='category2' value='"+sublist.subcategorylist[i].subcategory+"' onclick='inputShow(this)' data-number='3' hidden>";
                category_div += "       <span>"+sublist.subcategorylist[i].subcategory+"</span>";
                category_div += "   </label>";
            }
            category_div += "</div>";
           $("#recipe_div3").html(category_div);
        },
         error: function (request, status, error) {
            console.log("message: " + request.responseText)
        }
    });
}
function recipeImgChange(event){//카테고리 등록 시 이미지 파일 찾기 바뀌면 이미지 바뀜
    var reader = new FileReader();
    reader.onload = function(event) {
      $('#recipePreviewImg').attr("src", event.target.result);
    };
    reader.readAsDataURL(event.target.files[0]);
}
function recipeMethodMake(){
    var method_len = $('#recipe_method_len').val();
    method_len++;
    $('#recipe_method_len').attr('value', method_len);
    var recipe_new_div = "";
    recipe_new_div += "<div id='recipe_method_make"+method_len+"'>"
    recipe_new_div += " <input type='text' id='recipemakeinput"+method_len+"' name='recipe_method_make' placeholder='요리 방법 입력' data-number='5' onkeyup='inputShow(this)'>";
    recipe_new_div += " <button type='button' data-id='recipe_method_make"+method_len+"' data-num='"+method_len+"' onclick='recipeMathodDelete(this)'>방법 -</button>";
    recipe_new_div += "</div>";
    $('#register_make').append(recipe_new_div);
}
function recipeMathodDelete(ths){
    var recipe_len = $('.register_make').find('div').length;
    var recipe_num = ths.dataset.num;
    var recipe_div = ths.dataset.id;
    $('#'+recipe_div).remove();
    for(i = recipe_num; i <= recipe_len; i++){
        $('#recipe_method_make'+i).attr('id', 'recipe_method_make'+(i-1));
        $('#recipe_method_make'+(i-1)).find('input').attr('id', 'recipemakeinput'+(i - 1));
        $('#recipe_method_make'+(i-1)).find('button').attr('data-id', 'recipe_method_make'+(i - 1));
        $('#recipe_method_make'+(i-1)).find('button').attr('data-num', (i - 1));
    }
    var recipe_tot = $('#recipe_method_len').val();
    recipe_tot--;
    $('#recipe_method_len').attr('value', recipe_tot);
}
function inputShow(ths){
    var data_num = ths.dataset.number;
    data_num++;
    $('#recipe_div'+data_num).addClass('move_dw');
    console.log(data_num);
}
var ingreArray = [];
var makeArray = [];
function recipeRegister(){
    var val_img = $('input[name="image"]').val();//이미지
    var val_food = $('input[name="food"]').val();//음식명
    var val_cat1 = $('input[name="category1"]').val();//카테고리1
    var val_cat2 = $('input[name="category2"]').val();//카테고리2
    var val_ingr = $('input[name="ingredient"]').val();//재료
    $('input[name="recipe_ingredient"]').each(function(){
        var this_val = $(this).val();
        if(this_val != ''){
            ingreArray.push(this_val);
            $('#recipe_ingre').val(ingreArray);
        }
    });
    $('input[name="recipe_method_make"]').each(function(){
        var this_val = $(this).val();
        if(this_val != ''){
            makeArray.push(this_val);
            $('#recipe_way').val(makeArray);
        }
    });
    $('#recipe_form').submit();
}

//리스트-필터 클릭 시
function searchToggle(){
    $('.list_search').toggle();
}
$(document).ready(function(){
    console.log("로드완료2");
});
