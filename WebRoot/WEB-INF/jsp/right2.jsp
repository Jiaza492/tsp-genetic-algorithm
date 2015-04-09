<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
    <body>
  	<form action="doDrawByPath.action" method="post">
  		<table width="30" border="1" align="center" >
  			<tr>
  				<td>
  					
  				</td>
  			</tr>
  			<tr>
  				<th colspan="2">请选择优化结果文件</th>
  				
  			</tr>
  			<tr>
  				<td>结果路径：</td>
  				<td align="left">
  					<input name="result" type="text" id="result"/>
  				</td>
  			</tr>
  			<tr>
  				<td>站点数据：</td>
  				<td align="left">
  					<input name="stationList" type="text"/>
  				</td>
  			
  			</tr>
  			<tr>
  				<td colspan="1" algin="center">
  					<input type="submit" value="显示站点路径" />
  				</td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>

