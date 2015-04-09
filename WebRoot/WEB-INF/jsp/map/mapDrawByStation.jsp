<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;hidden;margin:0;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=w5ZUl1WCVWMOTOz6zSHqiCNy"></script>
<title>添加普通标注点</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>


<%List<String[][]> asl = (List<String[][]>)request.getAttribute("allStationList");%> 
<%List<double[]> pl = (List<double[]>)request.getAttribute("pathList");%>

<script type="text/javascript">

var map = new BMap.Map("allmap");
map.enableScrollWheelZoom(true);
map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT})); 
map.centerAndZoom(new BMap.Point(116.249, 40.041), 14);
<%for(int k = 0;k < asl.size();k++){%>
	<%String[][] sl = asl.get(k);%>
	<%double[] paths = pl.get(k);%>
	// 定义支路标志
	var p1;
	<%for(int i = 0;i<paths.length;i++){%>
		
        var long1 = <%=sl[(int)paths[i]-1][0]%>;
		var lat1 = <%=sl[(int)paths[i]-1][1]%>;
		
		// 定义起终点
        p1 = new BMap.Point(lat1,long1);
      	var opts = {position:p1,offset:new BMap.Size(30, -30)};
      	var label = new BMap.Label("第"+<%=k+1%>+"区|"+<%=(int)paths[i]%>+"站", opts); 
		// 区别各总支的颜色
		var dis = <%=k%>;
		switch (dis){
			case 0:
				label.setStyle({color : "red",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			case 1:
				label.setStyle({color : "blue",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			case 2:
				label.setStyle({color : "green",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			case 3: 
				label.setStyle({color : "yellow",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			case 4:
				label.setStyle({color : "orange",fontSize : "12px",height : "20px",lineHeight : "20px"});
				break;
			}
		map.addOverlay(label); 
        // 百度地图Label功能
		var marker1 = new BMap.Marker(p1);  // 创建标注
		map.addOverlay(marker1);              // 将标注添加到地图中
		<%if(paths[i]==1){%>
		    var school = new BMap.Label("这里是学校",opts);
        	school.setStyle({color : "red",fontSize : "20px",height : "20px",lineHeight : "30px"});
        	map.addOverlay(school);
        <%}%>
	<%}%>
<%}%>
</script>