<%@ page import="com.chat.controller.UserDAOImpl"%>
<%@ page import="com.chat.controller.UserDAO"%>
<%@ page import="com.chat.entity.User"%>
<%@ page import="com.chat.entity.Relation"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 下星期三卓越班有课，晚上可能有在 -->
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>在线聊天系统</title>
	<link rel="stylesheet" href="./css/index_style.css">
	<script src="./js/jquery-1.12.0.min.js"></script>
	<!-- 实验报告一-计科142班6103114077江军-实验名称 -->
</head>
<body>
<%
	User sessionUser = (User)session.getAttribute("user");
	System.out.println("一开始的输出"+sessionUser);
	UserDAO u = new UserDAOImpl();
	if(sessionUser==null){
		System.out.println("if里面的输出");
		response.sendRedirect("login.jsp");
		return;//一定要加return，坑。。。
	}else{
		System.out.println("else里面的输出");
		//获取在线好友
		String id = sessionUser.getId();
		List onelineUsers = u.selectAllOnLineFriend(id,1);
		pageContext.setAttribute("onLineFriend", onelineUsers);
		//获取好友请求信息
		/* List relation = u.getAllFriendRequest(sessionUser.getId());
		pageContext.setAttribute("relations", relation); */
		//获取所有好友
		List allUsers = u.selectAllOnLineFriend(id,0);
		pageContext.setAttribute("allFriend", allUsers);
	}
%>

<div id="message-tip">
	
</div>




<input type="hidden" id="uid" value="${user.id}"/>
	<div class="main" id="main">
		<!-- 左边部分开始 -->
		<div class="left">
			<!-- 头像顶部 -->
			<div class="user-top">
				<img class="head-img" src="<%=sessionUser.getImg()%>" alt="">
				<p><%=sessionUser.getUsername()%></p>
				<span><a id="add" href="javascript:;" title="添加学生" style="color:#6b6f7c">+</a></span>
			</div>
			<!-- 添加好友的弹出层开始-->
			<div class="add-friend" id="add-friend">
				<div class="add-input">
					<input type="text" name="id" id="search-key" placeholder="请输入ID查找好友"/>
				</div>
				<div class="result" id="result">
					<!-- 没找到 -->
					<!-- <p class="null">没有搜索到该好友</p> -->
					<!-- 搜索到好友
					<div class="seeked">
						<div class="headimg"><img src="./images/head-img.png" alt=""></div>
						<div class="f-info">
							<p id="username">八级大狂风</p>
							<p id="id">ID:56829312</p>
						</div>
						<div class="add-submit"><p>添加</p></div>
					</div>
					 -->
				</div>
				<div class="button">
					<div class="add-search" id="search"><a href="javascript:;">查找</a></div>
					<div class="cancle" id="cancle"><a href="javascript:;">取消</a></div>
				</div>
			</div>
			<!-- 添加好友的弹出层结束-->

			<!-- 搜索框 -->
			<div class="search" id="search-input">
				<div class="search-icon"><img src="./images/search.png" alt=""></div>
				<div class="input"><input type="text" placeholder="搜索"></div>
			</div>

			<!-- tab标题 -->
			<div class="tab-tag">
				<div class="message" style="border-left:none" onclick="change('weixin',1,this,1)"><img src="./images/weixin.png" alt=""></div>
				<div class="contact" onclick="change('contact',2,this,0)"><img src="./images/contact-no.png" alt=""></div>
				<div class="friend" onclick="change('man',3,this,2)"><img src="./images/man-no.png" alt=""></div>
			</div>

			<div class="contain">
				<div class="message-content">
				<%
					List users = (ArrayList)pageContext.getAttribute("onLineFriend");
					if(users!=null){
						for(int i=0;i<users.size();i++){
							User user = (User)users.get(i);
				%>
					<div class="item">
						<div class="wrap" onclick="chatWith(this)">
							<div class="img"><img src="<%=user.getImg()%>" alt=""></div>
							<div class="text"><p><%=user.getUsername() %></p></div>
							<input type="hidden" class="uid" value="<%=user.getId() %>"/>
							<div class="time"><p>15:36</p></div>
						</div>
					</div>
				<%
						}
					}else{
						
				%>
					<div class="null-tip">
						<p>你的好友都没在线</p>
					</div>
				<%
					}
				%>
				</div>
				<div class="contact-content">
				<%-- <%
					UserDAO userDAO = new UserDAOImpl();
					List lists = (ArrayList<Relation>)pageContext.getAttribute("relations");
					if(lists!=null){
						for(int i=0;i<lists.size();i++){
							Relation relation = (Relation)lists.get(i);
							String fid = relation.getAdder_id();
							System.out.println("jsp页面获取到对方的ID："+fid);
							User ruser = userDAO.userInfo(fid);
				%>
					
				<%
						}
					}else{
				%>
					<div class="null-tip">
						<p>你还没有任何消息</p>
					</div>
				<%
					}
				%> --%>
				</div>
				<div class="friend-content" id="friend-content">
				<c:choose>
				   <c:when test="${!empty pageScope.allFriend}"> 
						<c:forEach var="user" items="${pageScope.allFriend}" varStatus="">
							<div class="friend-item" onclick="checkFriendInfo(${user.id})">
								<img src="${user.img }" alt="">
								<input type="hidden" value="${user.id }"/>
								<p>${user.username}</p>
							</div>	
						</c:forEach>
				   </c:when>
				   <c:otherwise> 
				    	<div class="null-tip" id="all-friend">
							<p>你还没有任何好友</p>
						</div>
				   </c:otherwise>
				</c:choose>
				</div>
				<!-- 右击好友列表的弹出层 -->
				<div class="f-option" id="f-option">
					<div class="delete" onclick="deleteFriend(this)"><p>删除</p></div>
					<div id="friend-hidden-wrap"></div>
					<div class="sendmsg" onclick="downloadChatRecord(this)" style="border-top:0.01em solid #f1f1f1"><p>导出聊天记录</p></div>
					
				</div>
				<!-- 好友列表弹出层bg背景 -->
				<div class="bg"></div>
			</div>
		</div>
		<!-- 左边部分结束 -->
		<!-- 右边部分开始 -->
		<div class="right">
			<!-- 右边消息主体开始 -->
			<div class="main-message" id="message">
				<div class="name">
					<p>未选中发消息的好友</p>
					<input id="hidden-fid" type="hidden" value=""/>
				</div>
				<div class="chat-record" id="console"></div>
				<div class="send-msg">
					<div class="icons">
						<img src="./images/laughter.png" alt="">
						<img src="./images/screen.png" alt="">
						<img src="./images/file.png" alt="">
					</div>
					<div class="input-content">
						<textarea name="" id="chat" cols="30" ></textarea>
					</div>
					<div class="submit">
						<span>发送</span>
					</div>
				</div>
			</div>
			<!-- 右边消息主体结束 -->
			<!-- 好友印象开始 -->
			
			<div class="impress" id="impress">
				<div class="unselect">
					<p>未选中好友，请点击好友查看好友信息和评价</p>
				</div>
			</div>
			<!-- 好友印象结束 -->
		</div>
		<!-- 右边部分结束 -->
	</div>
</body>
<script>
	window.onload=function(){
		
    	var width = document.documentElement.clientWidth || document.body.clientWidth;
    	document.body.style.fontSize=width/24 + "px";
    	//获取屏幕高度，设置元素高度为屏幕高度
    	var height = document.documentElement.clientHeight || document.body.clientHeight;
    	document.getElementById("main").style.height=height+"px";
    	
    	
    };
    //Tab切换
	var li = $('.tab-tag div');
	li.click(function(){
		var index = $(this).index();
		$(this).attr("src","").siblings("li").removeClass("this");
		$(".contain").children().eq(index).show().siblings('div').hide();
	});
	function change(src,index,obj,slide){
		var des = "./images/"+ src +".png";
		var no = "./images/"+src + "-no.png";
		$(obj).children().attr("src",des).siblings('div img').attr("src",no);
		$(".contain").children().eq(index).show().siblings('div').hide();
		slideFun(slide);
	}
	// 实现消息和印象模块的切换
	function slideFun(mark){
		if(mark==1){
			$("#message").show();
			$("#impress").hide();
		}else if(mark==2){
			$("#message").hide();
			$("#impress").show();
		}else{
			return;
		}
	}
	// 消除在指定区域的浏览器的右键菜单弹出
	document.getElementById("friend-content").oncontextmenu = function(e){
		return false;
	};
	document.getElementById("f-option").oncontextmenu = function(e){
		return false;
	};
	//为指定区域绑定鼠标右键事件
	$(".friend-content div").mousedown(function(e){ 
        if(3 == e.which){ //鼠标右击事件
            $(".f-option").show();
        	var index = $(this).index();
        	var uid = $(this).children("input").val();
        	$(".bg").show();
        	var html = "<input type='hidden' id='option-hidden-uid' value='"+uid+"-"+index+"'/>";
        	$("#friend-hidden-wrap input").remove();
        	 $("#friend-hidden-wrap").append(html);
            $(".f-option").css("left",e.clientX);
            $(".f-option").css("top",e.clientY);
            return false;
        }
      	return;
    });
	$(".bg").click(function(){
		$(".f-option").hide();
		$(".bg").hide();
	});
	
	//删除好友
	function deleteFriend(event){
    	if(confirm("确定要删除好友？")){
    		var fid = $(event).next().children("input").val().split("-")[0];
    		var index = $(event).next().children("input").val().split("-")[1];
    		var mid = $("#uid").val();
    		$.post("User_deleteFriend",{fid:fid,mid:mid},function(data){
    			var result = eval("("+data.result+")");
    			if(result.res==1){
    				alert("删除成功！");
    				var string = ".friend-item:eq("+index+")";
    				$(string).remove();
    			}else{
    				alert("删除失败，请稍后再试！");
    			}
    		},'json');
    	}
		
	}
	//导出聊天记录
	function downloadChatRecord(event){
		var fid = $(event).prev().children("input").val().split("-")[0];
		var mid = $("#uid").val();
		window.location.href="download.servlet?fid="+fid+"&mid="+mid;
	}
	
    //点击弹出添加好友的弹出框
    $("#add").click(function(){
    	$("#add-friend").show();
    });
    //点击取消弹出框隐藏
     $("#cancle").click(function(){
    	$("#add-friend").hide();
    	//移除列出的好友
    	$(".seeked").remove();
    });
    
    
    //异步请求好友数据
    //给查找按钮绑定点击事件
    $("#search").bind("click",function(){
    	if($("#search-key").val()==""){
    		alert("请输入好友用户名！");
    		return;
    	}
    	$.ajax({
            type:"post",
			url:"User_search",//需要用来处理ajax请求的action
			data:{//传递的参数
				username:$("#search-key").val()
            },
            dataType:"json",//设置需要返回的数据类型
            success:function(data){
	            var d = eval("("+data.result+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
	             
	            if(d){
	            	 var html = "<div class='seeked'>"+
	 	            "<div class='headimg'><img src='"+d.img+"' alt=''></div>"+
	 	            "<div class='f-info'>"+
	 					"<p id='username'>"+d.username+"</p>"+
	 						"<p id='id'>ID:"+d.id+"</p>"+
	 					"</div><input type='hidden' value='"+d.status+"'/>"+
	 				"<div class='add-submit' onclick='addFriend()'><p>添加</p></div>"+
	 				"</div>";
	            }else{
	            	var html = "<div><p class=\"null\">没有搜索到该好友</p></div>";
	            }
	           	$("#result div").remove();
	            $("#result").append(html);
            },
            error:function(){
                alert("系统异常，请稍后重试！");
            }
		});
    });
    //异步请求好友数据结束
    
     //添加好友异步操作
    function addFriend(){
    	var fid = $("#id").text().split(":")[1];
    	var mid = $("#uid").val();
    	$.post("User_addFriend",{fid:fid,mid:mid},function(data){
    		var result = eval("("+data.result+")");
    		if(result.res==1){
    			$("#all-friend").remove();
    			var html = "<div class='friend-item' onclick='checkFriendInfo("+data.user.id+")'>"+
				    			"<img src='"+data.user.img+"' alt=''>"+
				    			"<input type='hidden' value='"+data.user.id+"'/>"+
				    			"<p>"+data.user.username+"</p>"+
				    		"</div>";
    			$("#friend-content").append(html);
    			alert("添加成功！");
    		}else if(result.res==0){
    			alert("添加失败,请稍后再试！");
    		}else if(result.res==4){
    			alert("不能添加自己！");
    		}else{
    			alert("你们已经是好友了！");
    		}; 
    	},'json');
    }
    //同意成为好友
    function agreeToFriend(event){
    	var fid = $(event).prev().children("input").val();
    	var mid = $("#uid").val();
    	$.post("User_agree",{fid:fid,mid:mid},function(data){
    		var result = eval("("+data.result+")");
    		alert(result.res);
    		/* var html = "<div class='friend-item' onclick='checkFriendInfo("+data.user.id+")'>"+
			"<img src='"+data.user.img+"' alt=''>"+
			"<input type='hidden' value='"+data.user.id+"'/>"+
			"<p>"+data.user.username+"</p>"
			"</div>";
		$("#friend-content").append(html); */
    	},'json');
    }
   //点击在线好友的图标，将ID，和用户名显示在右边消息窗口的顶部
   function chatWith(event){
	   var username = $(event).children(".text").text();
	   var fid = $(event).children(".uid").val();
	   $("#message .name p").html(username);
	   $("#hidden-fid").val(fid);
   }
   //查看好友信息和评价
   function checkFriendInfo(uid){
	   $("#impress>div").remove();//移除原来的内容
	   //动态添加好友信息
	   $.post("User_infoAndRemark",{uid:uid},function(data){
		   var remarkObj = $.parseJSON(data.result);
		   var uhtml =	"<div class='name'><p>好友印象</p></div>"+
			"<div class='slide'>"+
				"<div class='friend-info'>"+
					"<img src='"+data.user.img+"' alt=''>"+
					"<p>"+data.user.username+"</p>"+
					"<p style='color:#888' id='impress-fid'>ID:"+data.user.id+"</p>"+
				"</div>"+
			"<div class='evaluate-title'><p>好友评价</p></div>";
			var rhtml = "<div class='evaluate-list' id='evaluate-list'>";
			if(remarkObj.length<1){
				rhtml += "<p class='null-inpress'>该好友还没有人评价</p>";
				rhtml +="</div>";
			}else{
				for(var i=0;i<remarkObj.length;i++){
					rhtml +=	"<div class='evaluate-item'>"+
									"<div class='img-wrap'>"+
										"<img src='./images/ourjay.png' alt=''>"+
									"</div>"+
							 		"<div class='frient-remark'>"+
							 			"<p>"+remarkObj[i].content+"</p>"+
						     		"</div>"+
								"</div>";
					
				}
				rhtml +="</div>";
			}
			
			var html =	"<div class='impress-wrap'>"+uhtml+rhtml+
						"<div class='evaluate'>"+
							"<div class='my-evaluate'>"+
								"<textarea name='' id='remark-content' cols='30' rows='10' style='border:0.01em solid #d6d6d6;border-radius:0.3em;'></textarea>"+
							"</div>"+
							"<div class='evaluate-submit'>"+
								"<a href='javascript:;' onclick='submitMyRemark()'>提交评价</a>"+
							"</div>"+
						"</div>"+
					"</div>";
			$("#impress").append(html);
	   },'json');
   }
 
   function submitMyRemark(){
	   var fid = $("#impress-fid").text().split(":")[1];
	   $.ajax({
           type:"post",
			url:"User_addFriendImpress",//需要用来处理ajax请求的action
			data:{//传递的参数
				mid:$("#uid").val(),
				fid:fid,
				content:$("#remark-content").val()
           },
           dataType:"json",//设置需要返回的数据类型
           success:function(data){
        	   var jsonObj = eval("("+data.result+")");
	           if(jsonObj.result==1){
	        	   var rhtml = 	"<div class='evaluate-item'>"+
									"<div class='img-wrap'>"+
										"<img src='./images/ourjay.png' alt=''>"+
									"</div>"+
							 		"<div class='frient-remark'>"+
							 			"<p>"+$("#remark-content").val()+"</p>"+
						     		"</div>"+
								"</div>";
				   $(".null-inpress").remove();	
	        	   $(".evaluate-list").append(rhtml);//将最新评论动态添加到列表末尾
	        	   var console = document.getElementById("evaluate-list");
	        	   console.scrollTop = console.scrollHeight;//滚动条在最低端，可以看到刚刚评论的内容
	        	   document.getElementById("remark-content").value="";
	        	   alert("添加印象成功！");
	           }else{
	        	   alert("添加印象失败，请稍后再试！");
	           }
	           
           },
           error:function(){
               alert("系统异常，请稍后重试！");
           }
		});
   }
   //实时接收好友请求消息
   /* setInterval("getRequestInfo()",500);//0.5s
   function getRequestInfo(){
	   var mid = $("#uid").val();
       $.post("User_getAllRequestMessage",{mid:mid},function(data){
    	   var relationObj = $.parseJSON(data.result);
    	   if(relationObj.length<1){
    		   return;
    	   }else{
    		   var html="";
    		   for(var i=0;i<relationObj.length;i++){
    			   html += "<div class='sys-message'>"+
				       			"<div class='sys-headimg'><img src='"+relationObj[i].img+"' alt=''></div>"+
				       			"<div class='sys-f-info'>"+
				       				"<p>"+relationObj[i].username+"</p>"+
				       				"<input type='hidden' value='"+relationObj[i].id+"'/>"+
				       				"<p style='color:#6b6f7c;'>请求加为好友</p>"+
				       			"</div>"+
				       			"<div class='sys-add-submit' onclick='agreeToFriend(this)'><p>同意</p></div>"+
				       		"</div>";
    		   }
    		   $("#message-tip").append(html);
    		   $("#message-tip").show();
    	   }
        },"json");
   }  */
    //!!!!!!websocket的js代码开始!!!!!!!!!!!!!!!!!!!!!!!
  
     var Chat = {};
         Chat.socket = null;
         // 创建一个websocket实例
         Chat.connect = (function(host) {
             if ('WebSocket' in window) {
                 Chat.socket = new WebSocket(host);
             } else if ('MozWebSocket' in window) {
                 Chat.socket = new MozWebSocket(host);
             } else {
                 Console.log('Console.log:你的浏览器不支持WebSocket');
                 return;
             }
             Chat.socket.onopen = function(){
                 Console.log('Console.log:WebSocket链接打开');
                 //Chat.socket.send("初始化socket对象传到后台的UID");
                 document.getElementById('chat').onkeydown = function(event) {
                     if (event.keyCode == 13) {
                         Chat.sendMessage();
                     }
                 };
             };
             Chat.socket.onclose = function () {
                 document.getElementById('chat').onkeydown = null;
                 Console.log('Console.log:WebSocket前端链接关闭');
             };
             Chat.socket.onmessage = function (message) {
                 Console.log(message.data);
             };
         });
         Chat.initialize = function() {
        	 
             if (window.location.protocol == 'http:') {
                 Chat.connect("ws://" + window.location.host + "/onLineChat/index.jsp");
             } else {
                 Chat.connect("wss://" + window.location.host + "/onLineChat/index.jsp");
             }
         };
         Chat.sendMessage = (function() {
        	 var fid = $("#hidden-fid").val();
             var messageContain = document.getElementById('chat').value;
             var message = messageContain +"-"+fid;
             if(fid==""){
            	 alert("未选择发送消息的好友！");
            	 return;
             }else{
            	 if (messageContain != "") {
                     Chat.socket.send(message);
                     document.getElementById('chat').value = '';
                 }else{
                	 alert("发送消息不能为空！");
                 }
             }
             
         });
         var Console = {};
         Console.log = (function(message) {
             var console = document.getElementById('console');
             var p = document.createElement('p');
             p.style.wordWrap = 'break-word';
             p.innerHTML = message;
             console.appendChild(p);
            //让页面显示最新消息
             console.scrollTop = console.scrollHeight;
         });
         Chat.initialize();
         document.addEventListener("DOMContentLoaded", function() {
             var noscripts = document.getElementsByClassName("noscript");
             for (var i = 0; i < noscripts.length; i++) {
                 noscripts[i].parentNode.removeChild(noscripts[i]);
             }
         }, false);
</script>
</html>
