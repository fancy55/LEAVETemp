<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>登录</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="css/style.css" rel="stylesheet" />
	<link href="css/bootstrap.min.css" rel="stylesheet" />
</head>
<!--style="background-image:url('./images/bg.jpg');background-repeat:no-repeat;background-size:100% 100%;-->
<body>
	<section id="content"  style="font-family:'Microsoft Yahei';">
		<div class="container">
			<div class="row" >
			    <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-6">
					<form action="User_login" method="post" role="form" class="register-form">
						<h2 style="font-family:'Microsoft Yahei'">登录 <small>到你的账户</small></h2>
						<hr class="colorgraph">

						<div class="form-group">
							<input type="text" name="user.username" id="email" class="form-control input-lg" placeholder="请输入用户名" tabindex="4">
						</div>
						<div class="form-group">
							<input type="password" name="user.password" class="form-control input-lg" id="exampleInputPassword1" placeholder="请输入密码">
						</div>
						<!-- <div class="form-group">
							<div class="row">
								<div class="col-xs-8 col-sm-8 col-md-8">
									<input type="text" class="form-control input-lg"  placeholder="验证码">
								</div>
								<div class="col-xs-4 col-sm-4 col-md-4"><input type="text" class="form-control input-lg"></div>
							</div>
						</div>-->
						
						<hr class="colorgraph">
						<div class="row">
							<div class="col-xs-12 col-md-6"><input type="submit" value="登录" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
							<div class="col-xs-12 col-md-6">如果你还没有没有账户？<a href="./register.jsp">注册</a></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>