$(function(){
     // $('.logo.top').addClass('animated swing').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
     //    $(this).removeClass('animated swing');});
    $('.logo.bottom .main-house').addClass('animated zoomIn').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
        $(this).removeClass('animated zoomIn');}).ready(function(){
    $('.logo.bottom .left-house').addClass('animated fadeInLeft').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
        $(this).removeClass('animated fadeInLeft');});
    $('.logo.bottom .right-house').addClass('animated fadeInRight').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
        $(this).removeClass('animated fadeInRight');});
    });


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


$(".dropdown-menu>li").click(function(){
    nav_click();
    $($(this).parents("li")).addClass("active");
    $($(this).children('a').data("href")).fadeIn();
    document.getElementsByTagName("title")[0].innerText=this.innerText.trim()+ " - YGWG ";
});


function newsjunmp(){
    $("#drpd-active").addClass("active");
    $("#News").fadeIn(); 

}
$(".home-ha").click(function(){
    jump();
    newsjunmp();
    $(this).trigger("click");
});


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
function contactUs() {

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
}



/* //百度地图
//创建和初始化地图函数：
function initMap(){
    createMap();//创建地图
    setMapEvent();//设置地图事件
    addMapControl();//向地图添加控件
    addMarker();//向地图中添加marker
}

//创建地图函数：
function createMap(){
    var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
    var point = new BMap.Point(113.6064084336,33.4829666296);//定义一个中心点坐标
    map.centerAndZoom(point,6);//设定地图的中心点和坐标并将地图显示在地图容器中
    window.map = map;//将map变量存储在全局
}

//地图事件设置函数：
function setMapEvent(){
    map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
    map.enableScrollWheelZoom();//启用地图滚轮放大缩小
    //map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
    map.enableKeyboard();//启用键盘上下左右键移动地图
}

//地图控件添加函数：
function addMapControl(){
//向地图中添加缩放控件
    var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
    map.addControl(ctrl_nav);
//向地图中添加缩略图控件
    var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
    map.addControl(ctrl_ove);
//向地图中添加比例尺控件
    var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
    map.addControl(ctrl_sca);
}

//标注点数组
var markerArr = [
    {title:"Nanjing&nbsp;Normal&nbsp;University",content:"<strong>E-mail:</strong>&nbsp;chenmin0902@163.com<br><strong>Address:</strong>&nbsp;1&nbsp;Wenyuan&nbsp;Road,Nanjing,Jiangsu,China",point:"118.918885|32.114483",isOpen:1,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}},
    {title:"Xi'an&nbsp;Jiaotong&nbsp;University",content:"<strong>E-mail:</strong>&nbsp;lihegeo@xjtu.edu.cn<br><strong>Address:</strong>&nbsp;28&nbsp;Xianning&nbsp;Road&nbsp;West,Xi'an,Shanxi,China",point:"108.99027|34.25280",isOpen:1,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
];
//创建marker
function addMarker(){
    for(var i=0;i<markerArr.length;i++){
        var json = markerArr[i];
        var p0 = json.point.split("|")[0];
        var p1 = json.point.split("|")[1];
        var point = new BMap.Point(p0,p1);
        
        var marker = new BMap.Marker(point);
        var iw = createInfoWindow(i);
        var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x-50,-40)});
        marker.setLabel(label);
        map.addOverlay(marker);
        label.setStyle({
            borderColor:"#808080",
            color:"#333",
            cursor:"pointer"
        });

        (function(){
            var index = i;
            var _iw = createInfoWindow(i);
            var _marker = marker;
            _marker.addEventListener("click",function(){
                this.openInfoWindow(_iw);
            });
            _iw.addEventListener("open",function(){
                _marker.getLabel().hide();
            })
            _iw.addEventListener("close",function(){
                _marker.getLabel().show();
            })
            label.addEventListener("click",function(){
                _marker.openInfoWindow(_iw);
            })
            if(!!json.isOpen){
                label.hide();
                _marker.openInfoWindow(_iw);
            }
        })()
    }
}
//创建InfoWindow
function createInfoWindow(i){
    var json = markerArr[i];
    var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
    return iw;
}

initMap();//创建和初始化地图

/* function ac(){
    $("#nav1").removeClass("active");
    $("#nav2").addClass("active");

} */
});
 


