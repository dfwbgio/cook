//규리
function confirmOk(){
    alertShow('레시피1','레시피1 js');
}
$(document).ready(function(){
    var ingredient = $('#ingredient').val().split(",");
    var ingredient_div = "";
       for (var i in ingredient){
           ingredient_div += "<div class='ingredient'>"
           ingredient_div +=    "<label><input type='checkbox' id='ingredient_"+i+"' name='ingredient' value='"+ingredient[i]+"' checked><span><span class='check'>✔</span>"+ingredient[i]+"</span></label>";
           ingredient_div += "</div>";
       }
       $('#ingredientBox').append(ingredient_div);
    //요리 레시피 분할
    var recipe = $('#recipe').val().split("<br>");
    var recipe_div = "";
    for (var i in recipe){
        recipe_div += "<div class='recipeProcess'>"
        recipe_div +=   "<p>"+ (Number(i)+1) +"</p><input type='text' id='recipeProcess_"+i+"' name='recipeProcess_"+i+"' value='"+recipe[i]+"' readonly>";
        recipe_div += "</div>";
    }
    $('#recipeProcess').append(recipe_div);
});
