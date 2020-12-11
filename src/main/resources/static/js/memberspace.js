$(function (){
    $("#btn-renew").click(function (){
       $.post("/ygwg/member/renew",function (result){
           $("#renewinfor").text(result);
       })
    })

    $("#btn-logout").click(function(){
        $.post("/ygwg/logout",function (){
            window.location.href="index";
        })
    })
})