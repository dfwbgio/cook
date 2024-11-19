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
            category_div += "<p class='register_subtitle'>레시피 소분류</p>";
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
}
var ingreArray = [];
var makeArray = [];
function recipeRegister(){
    var val_img = $('input[name="image"]').val();//이미지
    var val_food = $('input[name="food"]').val();//음식명
    var val_cat1 = $('input[name="category1"]').val();//카테고리1
    var val_cat2 = $('input[name="category2"]').val();//카테고리2
    var len_ingr = $('input[name="ingredient"]').length;//재료
    var ingre_div = "";
    if(val_img == ''
    || val_food == ''
    || val_cat1 == ''
    || val_cat2 == ''
    || len_ingr == ''
    ){
        alertShow('정보 미입력','정보를 모두 입력해주세요.');
        return false;
    }
    $('input[name="recipe_ingredient"]:checked').each(function(){
        var this_val = $(this).val();
        if(this_val != ''){
            ingreArray.push(this_val);
            ingre_div += this_val;
            ingre_div += "<br>";
            $('#recipe_ingre').val(ingre_div);
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
function recipeIngreSelect(){
    var ingre_val = $('input[name="recipe_ingredient_input"]').val();
    /*$.ajax({
        type: "POST",
        url: "/ingreSelect",
        data: {"ingredient": ingre_val},
        success: function(response) {
            try {
                // response가 이미 객체라면 JSON.parse를 사용하지 않습니다.
                if (typeof response === 'string') {
                    response = JSON.parse(response);
                }

                response.forEach(result => {
                    console.log(`재료: ${result.ingredient}`);
                    result.items.forEach(item => {
                        console.log(`상품명: ${item.title}, 가격: ${item.price}, 링크: ${item.link}`);
                    });
                });
            } catch (e) {
                console.error("JSON 파싱 오류:", e);
            }
        },
        error: function(request, status, error) {
            console.log("AJAX 오류:", request.responseText);
        }
    });
    */
    $.ajax({
        type: "POST",
        url: "/ingreSelect",
        data: { "ingredient": "당근" },  // 예시 데이터
        success: function(response) {
            console.log("응답 데이터:", response); // 응답 내용을 확인

            // 응답이 문자열일 경우 JSON으로 파싱 시도
            if (typeof response === 'string') {
                try {
                    response = JSON.parse(response); // JSON 문자열 파싱
                } catch (e) {
                    console.error("JSON 파싱 오류:", e);
                    return;  // 파싱 오류가 발생하면 종료
                }
            }

            // 응답이 배열인지 확인 후 처리
            if (Array.isArray(response)) {
                response.forEach(result => {
                    console.log(`재료: ${result.ingredient}`);
                    result.items.forEach(item => {
                        console.log(`상품명: ${item.title}, 가격: ${item.price}`);
                    });
                });
            } else {
                console.error("응답 데이터가 배열이 아닙니다. 응답:", response);
            }
        },
        error: function(request, status, error) {
            console.log("AJAX 오류:", request.responseText);
        }
    });
}

//리스트-필터 클릭 시
function searchToggle(){
    $('.list_search').toggle();
}
$(document).ready(function(){
});
