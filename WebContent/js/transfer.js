$(function() {
    $('#search').click(function(){
       
		$('#showinfo').html("Search Records");
		initpoint();
    });
});
function initpoint(){
	//获取时间
	 var time1=$('#datetimepicker1').val();
     var time2=$('#datetimepicker2').val();
	var timelen=0;
      
     var sellerid=getCookie('sellerid');
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "seller/amoutpoint",
        "method": "POST",
        "headers": {
            "content-type": "application/x-www-form-urlencoded",
            "cache-control": "no-cache",
            "postman-token": "39881d2f-df99-b39b-1beb-9cdc0e50a89b"
        },
        "data": {
            "sellerid": sellerid,
			"time1":time1,
			"time2":time2
        }
    }

    $.ajax(settings).done(function (response) {
      
        if(response.status==0){
            var data=response.data;
            if(data.income>0){
            	  $('#income').html(data.income);
            }else{
            	 $('#income').html(0);
            }
            if(data.outcome>0){
            	 $('#outcome').html(data.outcome);
            }else{
            	 $('#outcome').html(0);
            }
          
		    console.log(timelen+time1+time2+sellerid);

       
		
		  
           
        }

    });
	
	 var url = 'seller/listrecord';
        jQuery.ajax({
            type: 'POST',
            async: true,
            data: {
                sellerid:sellerid,
                time1:time1,
                time2:time2,
                timelen:timelen
            },
            url:url,
            success: function (data) {
                //
                if (data.status == 0) {
                    var guide=new Array();
                    guide=data.data;
                    if(guide.length>0){
                        var i = 0;
                        //
                        var stringhtml = '';
                        for (i = 0; i < guide.length; i++) {
                            stringhtml += ' <tr >' +
                                '<td scope="row">'+(i+1)+'</th></td>' +
                                ' <td>' + guide[i].username + '</td>' +
                                ' <td>' + guide[i].type+ '</td>' +
                                ' <td>' + guide[i].points + '</td>' +
                                ' <td>' + guide[i].time + '</td>' +
                                ' </tr>';
                        }
                        $('#result').html(stringhtml);
						
                    }else{
                    	$('#result').html("no record");
                    }
					

                }
            },
            cache: false,
            dataType: 'json'
        });
	
	
       

	
		
      
	
	
}

/**
 * Created by KEN on 2016/5/27.
 */
//дcookies
function setCookie(name,value)
{
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
//��ȡcookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
//ɾ��cookies
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}