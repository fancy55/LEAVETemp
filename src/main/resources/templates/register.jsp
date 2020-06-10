<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>我要注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/cubeportfolio.min.css" rel="stylesheet" />
	<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<div class="container" style="margin-top:30px;font-family:'Microsoft Yahei'">
	<div class="row">
	    <div class="col-xs-12 col-sm-8 col-md-5 col-sm-offset-2 col-md-offset-3">
			<form action="User_register" role="form" class="register-form" method="post">
				<h2>注册用户<small></small></h2>
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12">
						<div class="form-group">
	                        <input type="text" name="user.username" id="username" class="form-control input-lg" placeholder="请输入昵称" tabindex="1">
						</div>
					</div>
				</div>
				<div class="form-group">
					<input type="password" name="user.password" id="password" class="form-control input-lg" placeholder="请输入密码" tabindex="2">
				</div>
				<div class="form-group">
					<input type="password" name="result" class="form-control input-lg" placeholder="请确认密码" tabindex="3">
				</div>
				<div class="form-group" style="color:red;">
					<s:fielderror><s:param>null</s:param></s:fielderror>
					<s:fielderror><s:param>unequal</s:param></s:fielderror>
				</div>
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-12 col-md-6"><input type="submit" value="立即注册" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
					<div class="col-xs-12 col-md-6" style="text-align:center;padding-top:10px;">已经有账户? <a href="./login.jsp">登录</a></div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>