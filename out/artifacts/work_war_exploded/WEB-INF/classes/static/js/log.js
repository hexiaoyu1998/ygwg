$(function (){

    $("#registerhere").click(function (){
        $("#login").hide();
        $("#register").fadeIn();
        $("#loginText").removeClass("line-bottom");
        $("#registerText").addClass("line-bottom");

    })



//提交登录
    $("#btn-login").click(function(){
        var Account=$("#loginAccount").val();
        var Password=$("#loginPassword").val();
        if(isEmpty(Account)){
            //登陆名空
            $("#loginfor").text("Please enter username or email address!");

        }
        else if(isEmpty(Password)){
            //密码空
            $("#loginfor").text("Please enter your password！");

        }
        else{
            //都不为空
            //判断密码是否符合规范
            if(isPwdvalidate(Password))
            {
                //符合规范
                $("#loginfor").text("");
                let datalogin ={
                    userAccount : Account,
                    userPassword : Password,
                }
                $.post("/ygwg/user/login",datalogin,function (result){
                    //alert(typeof result);
                    // if(result.memberId!=null){
                    //         $.post("/ygwg/in/Space",result,function (result2){
                    //             window.location.href="memberspace";
                    //         });
                    //
                    // }
                    if(result instanceof Object)
                    {
                        $.post("/ygwg/in/Space",result,function (result2){

                            //alert(result2);
                            if(result2=="userspace"){
                                window.location.href="userspace";
                            }
                            else{
                                window.location.href="memberspace";
                            }

                        });
                    }
                    else{
                        //alert(result);
                        $("#loginfor").text(result);

                    }
                })
            }
            else{
                //不符合规范
                $("#loginfor").text("Make sure the password at least 8 characters including a number and a lowercase letter.");
                //alert("Make sure the password  at least 8 characters including a number and a lowercase letter.");
            }
        }
    });

    //提交注册
    $("#btn-register").click(function (){
        var userName=$("#registerName").val();
        var userEmail=$("#registerEmail").val();
        var userPassword=$("#registerPassword").val();

        if(isEmpty(userName)||isEmpty(userEmail)||isEmpty(userPassword))
        {
            $("#registerinfo").text("Please input your Name、Email and Password!");
        }
        else{
            if (!isEmail(userEmail))
            {
                //邮箱不符合规范
                $("#registerinfo").text("Please input a correct Email address!");
            }
            else
            {
                //邮箱符合规范
                if(!isPwdvalidate(userPassword))
                {
                    //密码不符合规范
                    $("#registerinfo").text("Make sure the password  at least 8 characters including a number and a lowercase letter.");

                }
                else{
                    //邮箱密码符合规范
                    $("#registerinfo").text("");
                    let dataregister={
                        userName : userName,
                        userEmail : userEmail,
                        userPassword : userPassword,
                    }
                    $.post("/ygwg/user/register",dataregister,function (result){
                        $("#registerinfo").text(result);

                    })

                }
            }
        }
    })

    //提交renew
    $("#btn-renew").click(function (){
        if($("#spaceismember").text()=="Unregistered member")
        {
            $("#renewinfor").text("Please apply for membership first!Membership Application Form can be found under the page of \"Join Us\" on this website. Membership applicants need to fill the forms and email the forms to Chairman and Secretary General.");
        }
        else
        {
            //todo
        }
    })


    function isPwdvalidate(str){
        //判断密码是否符合规则
        if (/^.*?[\d]+.*$/.test(str) && /^.*?[a-z].*$/.test(str) && str.length>7) {

            return true;

        } else {
            return false;
        }
        // if (/\d/.test(str)){ //匹配数字
        //     if (/[a-z]/.test(str)) level++//如果有小写a-z，密码强度++
        //     if (/[A-Z]/.test(str)) level++//匹配大写A-Z
        //     if (str.length > 10) level++//密码长度是否大于10
        //     if (/[\.\~\@\#\$\%\^\&\*]/.test(str)) level++//匹配特殊字符
        //
        // }
        //     //匹配数字

    }

    function isEmpty(str){
        if(str==null||str.trim()=="")
            return 1;
        return 0;
    }

    function isEmail(str) {
        if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(str)) {
            return false; }
        else {
            return true;}
    }

    //切换login register
    $("#loginText").click(function (){
        //内容显示
        $("#information").css("height",$("#login").height());
        $("#login").fadeIn();
        $("#register").hide();
        $(this).addClass("line-bottom");
        $("#registerText").removeClass("line-bottom");


    });
    $("#registerText").click(function (){
        //内容显示
        $("#information").css("height",$("#register").height());
        $("#login").hide();
        $("#register").fadeIn();
        $(this).addClass("line-bottom");
        $("#loginText").removeClass("line-bottom");
        $("#information").style.borderLeft=$("#register").style.borderLeft;
    })



});