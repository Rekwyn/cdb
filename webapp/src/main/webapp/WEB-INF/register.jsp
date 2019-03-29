<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<style>
body {background-color:#eeeeee;}
</style>
</head>
<body>
<h1 style="text-align:center;margin-bottom:75px;">REGISTER</h1>
<form action="/webapp/register" method="POST" enctype="utf8" style="margin-left:100px;">
    <div style="text-align:center;">
        <label style="font-weight:bold;">Login</label>
        <br/>
        <input type="text" name="login"/>
    </div>
    <br/>
    <div style="text-align:center;">
        <label style="font-weight:bold">Password</label>
        <br/>
        <input type="password" name="password"/>
    </div>
    <button style="text-align:center;" type="submit">submit</button>
</form>
</body>
</html>