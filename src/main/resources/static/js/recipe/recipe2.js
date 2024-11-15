//혜린
//카테고리 클릭 시 유효성검사
//json 이용하여 세부 카테고리를 가져오기 위함
function recipeCategoryClick(){
    var recipe_category_val = $('input[name="recipe_category1"]:checked').val();
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
                category_div += "       <input type='radio' id='recipe_category2_"+i+"' name='recipe_category2' value='"+sublist.subcategorylist[i].subcategory+"' onclick='inputShow(this)' data-num='3' hidden>";
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
function inputShow(ths){
    var data_num = ths.dataset.number;
    data_num++;
    $('#recipe_div'+data_num).addClass('move_dw');
    console.log(data_num);
}
function recipeMake(){
    var method_len = $('#recipe_method').val();
    method_len++;
    var recipe_new_div = "";
    recipe_new_div += "<div id='recipemake"+method_len+"'>"
    recipe_new_div += " <input type='text' id='recipemake"+method_len+"' name='recipemake' placeholder='요리 방법 입력' data-number='5' onkeyup='inputShow(this)'>";
    recipe_new_div += " <button type='button' onclick='recipeMake()'>방법 +</button>";
    recipe_new_div += "</div>";
    $('#register_make').append(recipe_new_div);
}
$(document).ready(function(){
    console.log("로드 완료2");
});
