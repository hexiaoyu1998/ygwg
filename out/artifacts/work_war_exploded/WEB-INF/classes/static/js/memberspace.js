$(function (){
    $("#btn-renew").click(function (){
       $.post("/ygwg/member/renew",function (result){
           $("#renewinfor").text(result);
       })
    })
})