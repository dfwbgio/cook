$(document).ready(function(){
    //상세보기 페이지들에 css 링크 추가
    if(win_href.includes('/delete')
    || win_href.includes('/select')
    || win_href.includes('/detail')
    || win_href.includes('/register')
    || win_href.includes('/update')
    || win_href.includes('/mypage')
    || win_href.includes('/signup')
    || win_href.includes('/login')
    || win_href.includes('/faq')
    || win_href.includes('/info/info')
    || win_href.includes('/category')
    || win_href.includes('/cart')
    ){
        $('head').prepend('<link rel="stylesheet" href="/css/common/detail.css">');
    }
    //페이지별 로드 시키기
    if(win_href.includes('/login')
    || win_href.includes('/member/list')
    || win_href.includes('/idsearch')
    || win_href.includes('/pwsearch')
    || win_href.includes('/pwupdate')
    || win_href.includes('/pwUpdate')){
        $('head').append('<script src="/js/member/member.js"></script>');
    }
    else if(win_href.includes('/signup')
    || win_href.includes('/mypage')){
        $('head').append('<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>');
        $('head').append('<script src="/js/member/signup.js"></script>');
    }
    else if(win_href.includes('/usersave')){
        $('head').append('<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>');
        $('head').append('<script src="/js/member/signup.js"></script>');
        $('head').append('<link rel="stylesheet" href="/css/member/signup.css">');

    }
    else if(win_href.includes('/info')){
        $('head').append('<script src="/js/info/info.js"></script>');
    }
    else if(win_href.includes('/community')){
        $('head').append('<script src="/js/community/community.js"></script>');
    }
    else if(win_href.includes('/random')){
        $('head').append('<script src="/js/random/random.js"></script>');
    }
    else if(win_href.includes('/recipe/cart')
    || win_href.includes('/recipe/select')
    && win_search.includes('path=detail')
    ){
        $('head').append('<script src="/js/recipe/recipe.js"></script>');
    }
    else if(win_href.includes('/recipe/list')
    || win_href.includes('/recipe/register')
    || win_href.includes('/recipe/select')
    && win_search.includes('path=update')
    || win_search.includes('path=delete')
    ){
        $('head').append('<script src="/js/recipe/recipe2.js"></script>');
    }
    else if(win_path==('/')
    || win_href.includes('/main')){
        $('head').append('<script src="/js/home/index.js"></script>');
    }
    //페이지별 헤더 a 태그에 class 추가/삭제
    if(win_href.includes('/recipe/')){
        $('#header_recipe').addClass('link_on');
    }
    else if(win_href.includes('/member/list')){
        $('#header_member').addClass('link_on');
    }
    else if(win_href.includes('/community/')){
        $('#header_community').addClass('link_on');
    }
    else if(win_href.includes('/random/')){
        $('#header_random').addClass('link_on');
    }
    else if(win_href.includes('/info/list')){
        $('#header_info').addClass('link_on');
    }
    else if(win_href.includes('signup')){
        $('#header_signup').addClass('link_on');
    }
    else if(win_href.includes('login')){
        $('#header_login').addClass('link_on');
    }
    else if(win_href.includes('mypage')){
        $('#header_mypage').addClass('link_on');
    }
    //페이지별 푸터 a 태그에 class 추가
    if(win_href.includes('/recipe/list')){
        $('#footer_recipe').addClass('footer_on');
    }
    else if(win_href.includes('/community/list')){
        $('#footer_community').addClass('footer_on');
    }
    else if(win_href.includes('/random/list')){
        $('#footer_random').addClass('footer_on');
    }
    else if(win_href.includes('/info/list')){
        $('#footer_info').addClass('footer_on');
    }
    else if(win_path == '/'){
        $('#footer_index').addClass('footer_on');
    }
});