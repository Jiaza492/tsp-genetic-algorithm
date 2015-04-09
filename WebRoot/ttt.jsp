<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {width:100%;height: 100%;margin:10;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=AE235826865751601022eadaa4f2b9d9"></script>
<title>Caculate</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<%String[] b = (String[])session.getAttribute("jdd");
String[] c = (String[])session.getAttribute("wdd");
String[] dura;
String[] dist;
 %>
<script type="text/javascript">

<%int m=0;
int i=0;
int j=0;
int k=-1;
%>

<%
int n=0;int t=20;//times for
%>


// 百度地图API功能

var map = new BMap.Map("allmap");
map.enableScrollWheelZoom(true);
map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT}));                      // 右下
//map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);

setTimeout(function(){
<%for (m=0;m<=t;m++){%>



//<%if (m==n) continue;%>

setTimeout(function(){
var aa=<%=b[m]%>
var bb=<%=c[m]%>
var cc=<%=b[m+1]%>
var dd=<%=c[m+1]%>

var p1 = new BMap.Point(aa,bb);
var p2 = new BMap.Point(cc,dd);
var opts = {
  position : p1,    // 指定文本标注所在的地理位置
  offset   : new BMap.Size(30, -30)    //设置文本偏移量
 
}
var label = new BMap.Label("S"+<%=m%>, opts);  // 创建文本标注对象
	label.setStyle({
		 color : "red",
		 fontSize : "12px",
		 height : "20px",
		 lineHeight : "20px",
		 fontFamily:"微软雅黑"
	 });
map.addOverlay(label); 
//var p3=new BMap.Point(116.301328,39.777199);

var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
driving.search(p1, p2);
//alert("111")
//=var output = "timelll="+i;
var dua=[];
 var dis=[];

var searchComplete = function (results){
    if (transit.getStatus() != BMAP_STATUS_SUCCESS){
        return ;
    }
        var plan = results.getPlan(0);
        dua[<%=m%>]=plan.getDuration(true).replace(/[\u4e00-\u9fa5]+/g,"");
       dis[<%=m%>]=plan.getDistance(true).replace(/[\u4e00-\u9fa5]+/g,"");
     //if ( dua[<%=m%>]>1)
  //   document.write("fffff");
 alert(dua[<%=m%>]+"[]"+<%=m%>+"[]"+<%=n%>+"km"+dis[<%=m%>]);

var table = document.getElementById("table");
var rows = table.getElementsByTagName("tr");
 var cells = rows[0].getElementsByTagName("td");
// if (dua[<%=m%>]<5)
//rows[1].cells[1].innerHTML=rows[1].cells[1].innerHTML+(<%=m%>)+"///";
//nowTR = table.insertRow();
//nowTD = nowTR.insertCell().innerHTML="0";
//table.insertRow(-1).innerHTML="0";
//row[0].insertCell(-1).innerHTML="0";


// table set=================================================
//rows[4].cells[3].innerHTML="53";
};
  

      // = output += dua +i+ "\n";                //获取时间
  // =    output += "distance=" ;
    // =  output += plan.getDistance(true) + "\n";             //获取距离

var transit = new BMap.DrivingRoute(map, {renderOptions: {map: map},
  onSearchComplete: searchComplete,
  
   });
//
      // alert("before window.location set");

//alert("after window.location set");
//transit.search(p1, p2);


//document.write(<%=m%>+"[]"+<%=n%>+"[]"+"dis"+dis[<%=m%>]+"[]dur"+dua[<%=m%>]+"<br>"); 
//},2000);

 },2);
 //dua[99]=dua[<%=m%>];
 //var totalt;
 //totalt=totalt+dua[<%=m%>];
 //alert (totalt);
 <%}%>


    },100);
//location.href="gt.jsp?arr="+dua;


//var sdu=duar.toString();
//alert(duar[1]);



//var url="gt.jsp?var1="+dua+"&var2="+dis;
       
   // location  = encodeURI(url);

</script>
<table border=1 >

</table>
<table border="1" id="table" bgcolor="#d7f199">
<script>
for(var i=0;i<2;i++){//画行
 document.write("<tr>");
 for(var j=0;j<45;j++){//画列
  document.write("<td width=20  height=20>0</td>");
 }
 document.write("</tr>");
}
</script>

</table> 
PS:Row[0]Column[1] represents students start from address[0] to [1]. 

