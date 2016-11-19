function getSellerInfoByIndustryID(id) {
            $.ajax({
                type: "POST",
                dataType:"JSON",
                url: "info/getSellerInfoByIndustryID",
                async: false,
                data: { id: id
                },
                success: function (data) {
                var i;
                $("#sellerInfo").empty();
                $.each(data, function (index, val) {
                i = index + 1;
                $("#sellerInfo").append("<tr><th scope='row'>"+i+"</th><td>"+val.seller_Name+"</td><td><img class='media-object' src='"+val.seller_Logo+"' alt='...' width='50px'></td><td>"+val.seller_Description+"</td></tr>");
                });
                return true;
                }     
            });
        }
 function getIndustryInfo() {
            $.ajax({
                type: "POST",
                dataType:"JSON",
                url: "info/getIndustryInfo",
                async: false,
                success: function (data) {
                $.each(data, function (index, val) {
                $("#industry").append("<div class='col-md-1 col-sm-2 col-xs-3 thumb'><button class='thumbnail center-block' style='overflow: hidden' id='"+val.industry_id+"' onClick ='getSellerInfoByIndustryID(this.id)'><img class='img-responsive' src='"+val.industryType_Logo+"'></button></div>");
                });
                return true;
                }     
            });
        }
 function getSellerInfo() {
            $.ajax({
                type: "POST",
                dataType:"JSON",
                url: "info/getSellerInfo",
                async: false,
                success: function (data) {
                var i;
                $.each(data, function (index, val) {
                i = index + 1;
                $("#sellerInfo").append("<tr><th scope='row'>"+i+"</th><td>"+val.seller_Name+"</td><td><img class='media-object' src='"+val.seller_Logo+"' alt='...' width='50px'></td><td>"+val.seller_Description+"</td></tr>");
                });
                return true;
                }     
            });
        }
 
 
 
 function validateSeller_Username() {
		 var uname=document.getElementById("username").value;
		 if(uname.length < 3 || uname.length > 20) {
				$("#usernameError").text("Length must be between 3~20!");
				showError($("#usernameError"));
				return false;
			}
		 else{
$.ajax({
	 type: "POST",
    dataType:"JSON",
    url: "seller/validateSeller_Username",
    async: false,
    data: {username: uname
    },
	success:function (data) {
		if(data.message == "exist") {
			$("#usernameError").text("Username already exist!");
			document.getElementById("usernameError").style.color="Red";
			showError($("#usernameError"));
			return false;
		}else{
			$("#usernameError").text("User name available.");
			document.getElementById("usernameError").style.color="Green";
			showError($("#usernameError"));
			return true;
		}
	}
});
		 }
}

function TipForUsername(){
$("#usernameError").text("Input username.");
document.getElementById("usernameError").style.color="Black";
showError($("#usernameError"));
}



function searchByKey(x)
{
var keyword=document.getElementById(x).value;
            $.ajax({
                type: "POST",
                dataType:"JSON",
                url: "info/getSellerInfoByKeyWord",
                async: false,
                data: { keyword: keyword
                },
                success: function (data) {
                var i;
                $("#sellerInfo").empty();
                $.each(data, function (index, val) {
                i = index + 1;
                $("#sellerInfo").append("<tr><th scope='row'>"+i+"</th><td>"+val.seller_Name+"</td><td><img class='media-object' src='"+val.seller_Logo+"' alt='...' width='50px'></td><td>"+val.seller_Description+"</td></tr>");
                });
                return true;
                }   
            });
}



function registSeller() {
    var seller_Name=document.getElementById("seller_Name").value;  
    var seller_Address=document.getElementById("Address").value;  
    var seller_Telephone=document.getElementById("Telephone").value;  
    var seller_Email=document.getElementById("Email").value;  
    var seller_Username=document.getElementById("username").value;
    var seller_Password=document.getElementById("userpassword").value;  
    var industryType_id=document.getElementById("Industry").value; 
    var seller_Description=document.getElementById("Description").value; 
        $.ajaxFileUpload({
            type: "POST",
            dataType:"text",
            url: "seller/registSeller",
            secureuri : false,
            fileElementId : "Logo",
            async: false,
            data: {
            seller_Name:seller_Name,
            seller_Address:seller_Address,
            seller_Telephone:seller_Telephone,
            seller_Email:seller_Email,
            seller_Username:seller_Username,
            seller_Password:seller_Password,
            industryType_id:industryType_id,
            seller_Description:seller_Description
            },
            success: function (data) {//返回数据的数据不能用object形式读，ajaxFileUpload代码有问题
            	if(data=="success"){
            	location.href="PayMemberFee.html";
            	}
            	else if(data=="false_exception"){
            		alert("system exceptions! Please try again.");
            	}else if(data=="false_format_not_correct"){
            		alert("Image format is not correct!");
            	}else if(data=="false_type_null"){
            		alert("File format cannot be empty!");
            	}else if(data=="false_size_too_big")
            		alert("The picture is too big!");
            	}
        });
    }

function setCookie(name,value)
{
var Days = 30;
var exp = new Date();
exp.setTime(exp.getTime() + Days*24*60*60*1000);
document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name)
{
var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
if(arr=document.cookie.match(reg))
return unescape(arr[2]);
else
return null;
}

function delCookie(name)
{
var exp = new Date();
exp.setTime(exp.getTime() - 1);
var cval=getCookie(name);
if(cval!=null)
document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}


