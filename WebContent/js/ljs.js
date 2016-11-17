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