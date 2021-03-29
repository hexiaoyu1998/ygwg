$(function(){
     // $('.logo.top').addClass('animated swing').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
     //    $(this).removeClass('animated swing');});
    // $('.logo.bottom .main-house').addClass('animated zoomIn').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
    //     $(this).removeClass('animated zoomIn');}).ready(function(){
    // $('.logo.bottom .left-house').addClass('animated fadeInLeft').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
    //     $(this).removeClass('animated fadeInLeft');});
    // $('.logo.bottom .right-house').addClass('animated fadeInRight').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
    //     $(this).removeClass('animated fadeInRight');});
    // });
    $('.logo').addClass('animated zoomIn').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
        $(this).removeClass('animated zoomIn');});


    //修改用户入口
    $.get("/ygwg/in/getsession",function (data){
        //alert(data);
        if(data=="member"){
            $("#userentrance").text("Member space");
            $("#userentrance").attr("href","/ygwg/memberspace");
        }
        else if(data=="user"){

            $("#userentrance").text("User space");
            $("#userentrance").attr("href","/ygwg/userspace");
        }
    })



    function nav_click()
{
    $(".nav-item.active,.dropdown.active,.dropdown-menu>li").each(function(){
        $($(this).children('a').hasClass("active")).hide();
        $($(this).children("ul").children("li").children('a').data("href")).hide();
        $($(this).children('a').data("href")).hide();
        $(this).removeClass("active");
      });
    
    
}

$(".nav-item").click(function(){
    nav_click();
    $("#news").hide();
    $(this).addClass("active");
    $($(this).children('a').data("href")).fadeIn();
  
  if(this.innerText.match('Home')){
    document.getElementsByTagName("title")[0].innerText="YGWG of AGA-Young Geographer Working Group (YGWG) of Asian Geographical Association (AGA)";
  }           
  else{
    document.getElementsByTagName("title")[0].innerText=this.innerText.trim()+" - YGWG ";
  }
});

    $(".navbar-nav>.dropdown").click(function (){
        nav_click();
        $($(this).children("a").data("href")).fadeIn();
        console.log($(this).children("a"));
        // document.getElementsByTagName("title")[0].innerText=this.innerText.trim()+" - YGWG ";
        document.getElementsByTagName("title")[0].innerText=this.children[1].children[0].innerText.trim()+" - YGWG ";
    })


function newsjunmp(){
    $("#drpd-active").addClass("active");
    $("#News").fadeIn(); 

}
$(".home-ha").click(function(){
    jump();
    newsjunmp();
    $(this).trigger("click");
});

    $(".dropdown-menu>li").on("click",function (e) {
        e.stopPropagation();  //防止向父节点
        nav_click();
        $($(this).parents("li")).addClass("active");
        $($(this).children('a').data("href")).fadeIn();
        document.getElementsByTagName("title")[0].innerText=this.innerText.trim()+ " - YGWG ";


    })


function  jump(){
    //home新闻跳转
    $($(".nav-item").children('a').data("href")).each(function () {
        $("#Home").hide();
        $("#nav1").removeClass("active");
    });
    $($(".nav-item").children('a').data("href")).each(function () {
        $("#news").fadeIn();    
    });
}

$(".listgroup-li").click(function(){
    $(".listgroup-li.active").each(function(){
        $(this).removeClass("active");
        $($(this).children('a').data("href")).hide();
    });
    $(this).addClass("active");
    $($(this).children('a').data("href")).fadeIn();
});

/* 下拉菜单悬停  */
$(".dropdown").hover(function(){
    $($(this).children("ul")).show();
},function(){
    $($(this).children("ul")).hide();
})
$(".dropdown-menu").click(function(){
    $(this).hide();
})


//邮件

    $("#contactusemail").click(function() {

        //Contact us

        var c_name = document.getElementById("CName").value;
        var c_email = document.getElementById("CEmail").value;
        var c_message = document.getElementById("Cmessage").value;
        if(c_email==""){
            alert("Please enter your Email!")
        }
        else if(c_name=="")
        {
            alert("Please enter your name!");
        }
        else if(c_message=="")
        {
            alert("Please enter your message!");
        }
        else{


            $.post("https://formspree.io/f/xjvpwgnk",
                {
                   From :"aga-ygwg.com",
                   Name : c_name,
                   Email : c_email,
                   Message : c_message,
                },
                function () {
                    //
                });
            $.post("https://formspree.io/f/xleobwbk",
            {
                From :"aga-ygwg.com",
                Name : c_name,
                Email : c_email,
                Message : c_message,
            },
            function () {
            });


            document.getElementById("successfulcontactus").innerHTML = " Sucessfully submitted Please Wait we will get back to you";
            console.log("sending contactus");
        }
    })


});
 


