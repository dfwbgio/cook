//혜린
//카테고리 클릭 시 유효성검사
//json 이용하여 세부 카테고리를 가져오기 위함
function recipeCategoryClick(){
    var recipe_category_val = $('input[name="recipe_category1"]:checked').val();
    $.ajax({
        type: "post",
        url: "/categoryGet",
        async: true,
        data: {"category":recipe_category_val},
        success: function(data){
           var sublist =JSON.parse(data);
           alert("성공");
           console.log(sublist);
           /*
           var ta="<table border='1' width='350px' >";
           ta+="<tr>";
           ta+="<th>글번호</th><th>원글번호</th><th>내용</th><th>작성자</th></tr>";
           for(var i in sdata.list){
              ta+="<tr><td>"+ sdata.list[i].bcnum+"</td>";
              ta+="<td>"+sdata.list[i].bcnum2+"</td>";
              ta+="<td>"+sdata.list[i].bccontent+"</td>";
              ta+="<td>"+sdata.list[i].bcwriter+"</td>";
              ta+="</tr>";
           }
           ta+="</table>";
           $("#result").html(ta);
           */
        },
         error: function (request, status, error) {
            console.log("code: " + request.status)
            console.log("message: " + request.responseText)
            console.log("error: " + error);
        }
    });
}
$(document).ready(function(){
    console.log('업데이트 완료');
});