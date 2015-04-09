<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%/**
* 
* @author Jia Ziang
* @see ziangj92@gmail.com
* @since 06/01/2014
* @version 1.0
*
*/  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
   		<frameset cols="10%,*">
   			<frame src="toLeft.action" noresize="noresize" scrolling="no"/>
   			<frame src="toRight.action" name="mainInfo"/>
   		</frameset>
   
  <body>
   
  </body> 
</html>
