<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
    <%@  page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>使用JSP读取TXT格式文件</title>
</head>

<body>

<%//这里是读取经度txt
String path="D:\\";  //目录分隔符必须用双斜杠
File file=new File(path,"jing.txt");
FileReader fr=new FileReader(file);   //字符输入流
BufferedReader br=new BufferedReader(fr);   //使文件可按行读取并具有缓冲功能
StringBuffer strB=new StringBuffer();  //strB用来存储jsp.txt文件里的内容
String str=br.readLine();
String[] jd = new String[99];
String xx=str;
jd[0]=str;
//out.println(jd[0]);
int i=0;
//out.println(str);
while(str!=null){
// out.println(str);

 i=i+1;
 strB.append(str).append(i+"<br>"); //将读取的内容放入strB
jd[i]=str;
 str=br.readLine();
}
br.close();            //关闭输入流
fr.close();
%>
<%//这里是读取纬度wei
String path2="D:\\";  //目录分隔符必须用双斜杠
File file2=new File(path,"wei.txt");
FileReader fr2=new FileReader(file2);   //字符输入流
BufferedReader br2=new BufferedReader(fr2);   //使文件可按行读取并具有缓冲功能
StringBuffer strB2=new StringBuffer();  //strB用来存储jsp.txt文件里的内容
String[] wd = new String[99];
String str2=br2.readLine();
wd[0]=str2;
String xx2=str2;
int j=0;
//out.println(str);
while(str2!=null){
// out.println(str);
 j=j+1;
 strB2.append(str2).append(j+"<br>"); //将读取的内容放入strB
wd[j]=str2;
 str2=br2.readLine();
}
br2.close();            //关闭输入流
fr2.close();
session.setAttribute("jdd",jd);
session.setAttribute("wdd",wd);
%>

<%=strB  %>
<%=strB2  %>
<%//String jdd=jd.toString();
//out.println(jdd);
 %>>
</body>
<a href="try1.jsp">gogogo</a>
<a href="ttt.jsp">go</a>
<script>

var jdd = <%=jd%>.toString();
document.write(jdd.toString())
//out.println(jdd);
//alert(jdd);


 var haha=<%=xx%>;
 var ha=<%=xx2%>
  alert(haha);
</script>

</form>

</html>