//혜린
//리스트
//필터 클릭 시
function searchToggle(){
    $('.list_search').toggle();
}
//레시피 검색
function recipeListSearch(){
    var category_val1 = $('input[name="category1"]:checked').val();
    var category_len1 = $('input[name="category1"]:checked').length;
    var category_val2 = $('input[name="category2"]:checked').val();
    var category_len2 = $('input[name="category2"]:checked').length;
    var food_val = $('#food_name').val();
    if(category_len1 < 1 && category_len2 < 1 && food_val == ''){
        location.href='/recipe/list';
    }
    else{
        if(category_len1 < 1){
            category_val1 = 'none';
        }
        if(category_len2 < 1){
            category_val2 = 'none';
        }
        if(food_val == '' || food_val == null){
            food_val = 'none';
        }
        console.log(category_val1);
        console.log(category_val2);
        console.log(food_val);
        location.href='/recipe/search?category1='+category_val1+'&category2='+category_val2+'&food='+food_val;
    }
}
//검색 초기화
function recipeSearchReset(){
    $('input[name="category1"]').prop('checked', false);
    $('input[name="category2"]').prop('checked', false);
    $('input[name="food"]').val('');
}
//등록
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
                category_div += "       <input type='radio' id='recipe_category2_"+i+"' name='category2' value='"+sublist.subcategorylist[i].subcategory+"' data-number='3' hidden>";
                category_div += "       <span>"+sublist.subcategorylist[i].subcategory+"</span>";
                category_div += "   </label>";
            }
            category_div += "</div>";
           $("#recipe_div3").html(category_div);
        },
         error: function (request, status, error) {
            alertShow("오류 발생!!!", request.responseText);
        }
    });
}
//카테고리 등록 시 이미지 파일 찾기 바뀌면 이미지 바뀜
function recipeImgChange(event){
    var reader = new FileReader();
    reader.onload = function(event) {
      $('#recipePreviewImg').attr("src", event.target.result);
    };
    reader.readAsDataURL(event.target.files[0]);
}
//레시피 등록 버튼 클릭 시
var seqArray = [];//시퀀스
var makeArray = [];//방법
function recipeRegister(){
    var val_img = $('input[name="image"]').val();//이미지
    var val_food = $('input[name="food"]').val();//음식명
    var val_cat1 = $('input[name="category1"]').val();//카테고리1
    var val_cat2 = $('input[name="category2"]').val();//카테고리2
    var val_ingr = $('input[name="ingredient"]').val();//재료
    var val_way = $('input[name="recipe"]').val();//재료
    var ingre_ing = "";
    //재료 array recipe_ingre div에 값 넣기
    $('#recipe_ingre').val(seqArray);
    //각 방법 뒤에 <br> 붙이기
    var way_make = "";
    var method_len = $('input[name="recipe_method_make"]').length - 1;
    $('input[name="recipe_method_make"]').each(function(index){
        var this_val = $(this).val();
        if(this_val !== '' && index !== method_len){
            way_make += this_val + "<br>";
        } else {
            way_make += this_val;
        }
    });
    $('#recipe_way').val(way_make);
    //유효성
    if(win_href.includes('/register')
    && val_img == ''
    || val_food == ''
    || val_cat1 == ''
    || val_cat2 == ''
    ){
        alertShow('정보 미입력','정보를 모두 입력해주세요.');
        return false;
    }else if(win_href.includes('&path=update')
    && val_food == ''
    || val_cat1 == ''
    || val_cat2 == ''
    || val_ingr == ''
    || val_way == ''
    ){
      alertShow('정보 미입력','정보를 모두 입력해주세요.');
      return false;
    }
    standbyShow('저장 중', '잠시만 기다려 주세요.');
    $('#recipe_form').submit();
    standbyHide();
}
//등록-재료 입력 크롤링 부분
function recipeIngreSelect(object) {
    standbyShow('로드 중', '잠시만 기다려주세요.');
    var ingre_type = object.dataset.type;
    var ingredient = $('#ingredient_input').val();
    if(ingre_type == 'crolling'){//크롤링, 불러오기
        $.ajax({
            type: "POST",
            url: "/ingreCrolling",//크롤링
            data: JSON.stringify({"ingredient": ingredient}),  // ingredient 값 전송
            contentType: "application/json; charset=UTF-8",  // JSON 형식으로 전송
            success: function(response) {
                standbyHide();
                alertShow('크롤링 완료', '성공적으로 재료들을 크롤링 했습니다.');
                ingreLoad(response);
            },
            error: function(request, status, error) {
                standbyHide();
                alertShow("오류 발생!!!", request.responseText);
            }
        });
    }else{
        $.ajax({ // JSON 형식으로 전송
            type: "post",
            url: "/ingreFind",
            async: true,
            data: {"ingredient" : ingredient},
            success: function(response) {
                standbyHide();
                alertShow('불러오기 완료', '성공적으로 재료들을 불러왔습니다.');
                ingreLoad(response);
            },
            error: function(request, status, error) {
                standbyHide();
                alertShow("오류 발생!!!", request.responseText);
            }
        });
    }
}
//쿠팡 크롤링 이후 재료 로드
function ingreLoad(object){
    var ingrelist = JSON.parse(object);
    var ingredient_div = "";
    if(ingrelist.ingredientlist.length != 0){
        for(var i in ingrelist.ingredientlist){
            ingredient_div += "<label for='recipe_ingredient"+ingrelist.ingredientlist[i].ingreseq+"'>";
            ingredient_div += " <input type='checkbox' onclick='ingreClick(this)' id='recipe_ingredient"+ingrelist.ingredientlist[i].ingreseq+"' name='recipe_ingredient' data-id='recipe_ingredient"+ingrelist.ingredientlist[i].ingreseq+"' data-seq='"+ingrelist.ingredientlist[i].ingreseq+"' data-name='"+ingrelist.ingredientlist[i].ingrename+"' value='"+ingrelist.ingredientlist[i].ingreseq+"' hidden>";
            ingredient_div += " <span>"+ingrelist.ingredientlist[i].ingrename+"</span>";
            ingredient_div += "</label>";
        }
    }else{
        ingredient_div += "<p class='no_data_txt'>해당 데이터가 없습니다.</p>"
    }
    $("#register_ingredient").html(ingredient_div);
    ingreChk();
}
//재료 클릭 시
function ingreClick(object){
    var ingre_seq = object.dataset.seq;//재료 시퀀스 넘버
    var ingre_name = object.value;//재료 이름
    var index = seqArray.indexOf(ingre_seq);
    var ingre_br = "";
    if(index < 0)  {//시퀀스 번호가 seqArray 배열에 없으면 ingreArray 배열에 값을 넣어줌
        seqArray.push(ingre_seq);
        ingre_br += "<div class='ingre_checked' id='ingre_checked"+ingre_seq+"' value='"+ingre_seq+"' data-seq='"+ingre_seq+"' onclick='ingreClick(this)'>";
        ingre_br += "   <p>" + ingre_name + "</p>";
        ingre_br += "   <span></span>";
        ingre_br += "</div>";
        $('#clicked_ingredient').append(ingre_br);
    }
    else{
        seqArray.splice(index, 1);
        $('#ingre_checked'+ingre_seq).remove();
        $('#recipe_ingredient'+ingre_seq).prop('checked', false);
    }
}
//재료 불러오기 or 크롤링 후
function ingreChk(){
    for(var n in seqArray){
        $('#recipe_ingredient'+seqArray[n]).prop('checked', true);
    }
}
//요리 방법 + 클릭 시
function recipeMethodMake(){
    var method_len = $('#recipe_method_len').val();
    method_len++;
    $('#recipe_method_len').attr('value', method_len);
    var recipe_new_div = "";
    recipe_new_div += "<div id='recipe_method_make"+method_len+"'>";
    recipe_new_div += " <p>"+method_len+".</p>";
    recipe_new_div += " <input type='text' id='recipemakeinput"+method_len+"' name='recipe_method_make' placeholder='요리 방법 입력' data-number='5'>";
    recipe_new_div += " <button type='button' data-id='recipe_method_make"+method_len+"' data-num='"+method_len+"' onclick='recipeMathodDelete(this)'>방법 -</button>";
    recipe_new_div += "</div>";
    $('#register_make').append(recipe_new_div);
}
//레시피 등록 방법 삭제
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
        $('#recipe_method_make'+(i-1)).find('p').text((i - 1)+'.');
    }
    var recipe_tot = $('#recipe_method_len').val();
    recipe_tot--;
    $('#recipe_method_len').attr('value', recipe_tot);
}
$(document).ready(function(){
    if(win_href.includes('/recipe/select') && win_href.includes('&path=update') || win_href.includes('&path=delete')){//업데이트
        //재료
        var recipe_ingre = $('#recipe_ingre').val().split(',');
        var ingre_num = recipe_ingre.length;
        for(var g = 0; g < ingre_num; g++){
            seqArray.push(recipe_ingre[g]);
        }
        $('.ingre_checked').each(function(index){
            $(this).attr('data-seq', seqArray[index]);
            $(this).attr('id', 'ingre_checked'+seqArray[index]);
            $(this).attr('value', seqArray[index]);
        });
        ingreChk();
        //방법
        var make_input = $('#recipe_way').val().split('<br>');
        var make_num = make_input.length;
        var make_br = "";
        for(var n = 0; n <= make_num - 1; n++){
            make_br += "<div id='recipe_method_make"+(n + 1)+"'>";
            make_br += "    <p>"+(n + 1)+".</p>";
            if(win_href.includes('&path=update')){
                make_br += "    <input type='text' data-id='recipe_method_make"+(n + 1)+"' name='recipe_method_make' value='"+make_input[n]+"' placeholder='요리 방법 입력'>";
                if(n >= 1){
                    make_br += "    <button type='button' data-id='recipe_method_make"+(n + 1)+"' data-num='"+(n + 1)+"' onclick='recipeMathodDelete(this)'>방법 -</button>";
                }
            }else{
                make_br += "    <input type='text' data-id='recipe_method_make"+(n + 1)+"' name='recipe_method_make' value='"+make_input[n]+"' placeholder='요리 방법 입력' readonly>";
            }
            make_br += "</div>";
        }
        $('#register_make').append(make_br);
        $('#recipe_method_len').val(make_num);
    }
});
