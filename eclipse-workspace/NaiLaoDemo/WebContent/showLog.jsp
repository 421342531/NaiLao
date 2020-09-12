<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.time.LocalDate"
import="java.time.Month"
import="java.time.Period"
import="java.util.HashMap"
import ="java.util.Map"
import ="com.alibaba.fastjson.JSON"
import ="com.alibaba.fastjson.JSONObject"
import ="java.util.Iterator"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎来到奶酪🧀的世界️</title>
<style type="text/css">#tableMain
{
	align:center;
	valign：center;
	border:none;
	margin-bottom:10px;
	border-collapse:collapse;
	width:800px;
	text-align:center;
	margin:0 auto;
}
#tableThread
{

	align:center;
	valign：center;
	border:none;
	margin-bottom:10px;
	border-collapse:collapse;
	width:800px;
	text-align:center;
	margin:0 auto;
	background-color:#FFF8EE;


}
</style>
</head>
<body>

<%   
JSONObject jsonObject  =(JSONObject)request.getAttribute("data");
System.out.println("jsonObject="+jsonObject);


%>
<center>
<table id="tableMain" border="1px" cellspacing="0" width="500px"  >
<thread>
	<tr id =tableThread>
		<td >序号</td>
		<td >时间</td>
		<td >操作类型0:开始吃 1:结束吃 2:臭臭</td>
	</tr>
</thread>
<% 
int index = 0;
Iterator iter = jsonObject.entrySet().iterator();
while (iter.hasNext()) {
    Map.Entry entry = (Map.Entry) iter.next();
    System.out.println(entry.getKey().toString());
    System.out.println(entry.getValue().toString());


%>
<tbody>
	<tr>
    <td ><%=++index%> </td>
    <td ><%=entry.getKey().toString()%> </td>
	<td colspan = "4"  ><%= entry.getValue().toString()%> </td>
	</tr>
</tbody>
<%} %>
</table>
<br>




<input id ="button" type="button" value="返回"
 	style="height:50px;width:20% " onclick="doBack()" />
</center>
<script type="text/javascript" src="js/echarts.js"></script>

<script src="js/jquery-3.5.1.min.js"></script>

 <script type="text/javascript">



	var ajax;
	 
	 function queryHuangDan(){
		 $.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/QueryHuangDanHist",
				data:{"id":'999'}, 
				success:function(data) {
					alert(data);
				}
			});
	 }
	 function doBack(){

			//alert("refresh");
			document.doBackForm.submit();
		}
	 
	

</script>
<form name = "doBackForm" class="form-signin" 
	role="form" method="post" action="IndexServlet">
</form>

</body>
</html>