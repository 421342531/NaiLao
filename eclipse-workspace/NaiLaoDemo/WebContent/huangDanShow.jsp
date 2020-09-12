<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.time.LocalDate"
import="java.time.Month"
import="java.time.Period"
import="java.util.HashMap"
import ="java.util.Map"
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

<%   Map<String ,String >  map = ( Map<String ,String > )request.getAttribute("data"); %>
<center>
<table id="tableMain" border="1px" cellspacing="0" width="500px"  >
<thread>
	<tr id =tableThread>
		<td >序号</td>
		<td >日期</td>
		<td >黄疸值</td>
	</tr>
</thread>
<% 
int index =0;
for (String key : map.keySet()) { 
%>
<tbody>
	<tr>
    <td ><%=++index%> </td>
    <td ><%=key%> </td>
	<td colspan = "4"  ><%= map.get(key)%> </td>
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