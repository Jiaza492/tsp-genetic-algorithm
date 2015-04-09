<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.io.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6O16rviutLS0HE4WfFX9aCOh"></script>
<title>根据起终点名称驾车导航</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>

<%List<String[][]> asl = (List<String[][]>)request.getAttribute("allStationList");%> 
<%List<double[]> pl = (List<double[]>)request.getAttribute("pathList");%>

<script type="text/javascript">


var map = new BMap.Map("allmap");

//map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
<%for(int k = 0;k < asl.size();k++){%>
	<%String[][] sl = asl.get(k);%>
	<%double[] paths = pl.get(k);%>
	// 定义支路标志
	var sign = 1;
	<%for(int i = 0;i<paths.length;i++){%>
		<%if(paths[i]==1){%>
        	sign = sign+1; //判断支路标志
        <%}%>
        var long1 = <%=sl[(int)paths[i]-1][0]%>;
		var lat1 = <%=sl[(int)paths[i]-1][1]%>;
		var long2 = <%=sl[(int)paths[(i+1)%paths.length]-1][0]%>;
		var lat2 = <%=sl[(int)paths[(i+1)%paths.length]-1][1]%>;
		// 定义起终点
        var p1 = new BMap.Point(lat1,long1);
        var p2 = new BMap.Point(lat2,long2);
        // 百度地图Label功能
		var opts = {position:p1,offset:new BMap.Size(30, -30)}
		var label = new BMap.Label("S"+<%=(int)paths[i]%>, opts); 
		// 区别各支路的颜色
		switch (sign){
			case 1:
				label.setStyle({color : "red",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			case 2:
				label.setStyle({color : "blue",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			case 3:
				label.setStyle({color : "green",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			case 4: 
				label.setStyle({color : "yellow",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			}
		map.addOverlay(label); 
		// 绘制路径
		var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
		driving.search(p1, p2);
	<%}%>
<%}%>
</script>

