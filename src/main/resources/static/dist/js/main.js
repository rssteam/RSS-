$(document).ready(function(){
    $("#subscribe").click(function(){
        $("#showhome").hide();
        $("#showsubscribe").show();
    });
    $("#backhome").click(function(){
        $("#showhome").show();
        $("#showsubscribe").hide();
    });
    $("#basic-addon2").click(function(){
        $("#searchitems").show();
        $("#recommenditem").hide();
    });
    $("#backrecommend").click(function(){
        $("#searchitems").hide();
        $("#recommenditem").show();
    });
    $("#showAll").click(function(){
        $(".showAll").show();
        $(".showGroup").hide();
    });
    $("#showGroup").click(function(){
        $(".showGroup").show();
        $(".showAll").hide();
    });
    $(function () {
        $(window).scroll(function () {
            if ($(window).scrollTop() >= 50) {
                $('#btn_top').fadeIn();
            }
            else {
                $('#btn_top').fadeOut();
            }
        });
    });
    $('#btn_top').click(function () {
        $('html,body').animate({ scrollTop: 0 }, 500);
    });

});
