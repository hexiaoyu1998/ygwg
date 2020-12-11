$(function (){

    function isEmpty(str){
        if(str==null||str.trim()=="")
            return 1;
        return 0;
    }

    $("#btn-logout").click(function(){
        $.post("/ygwg/logout",function (){
            window.location.href="index";
        })
    })

    $("#setting").css("height",$("#info").height()-20);

    window.onresize=function (){
        $("#setting").css("height",$("#info").height());
    };

    //提交绑定id
    $("#btn-inputid").click(function (){
        var Id=$("#userinputid").val();
        if (isEmpty(Id))
        {

            $("#inputidinfo").text("Please input a correct member Id!");
        }
        else{
            let data={
                memberId : Id,
            }
            $.post("/ygwg/member/updateid",data,function (result){
                if (result=="done")
                {
                    $("#inputidinfo").text("Associated with success!");
                    $.post("/ygwg/in/Space",data,function (result2){

                        if(result2=="userspace"){
                            window.location.href="userspace";
                        }
                        else{
                            window.location.href="memberspace";
                        }

                    });

                }
                else{
                    $("#inputidinfo").text(result);
                }
            })
        }
    })




})