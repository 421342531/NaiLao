<!DOCTYPE html>
<html> 
<head>
<meta charset="utf-8" ><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>Insert title here</title>
</head>
<script src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
function newStrart(){
	
	var id = document.getElementById("inputWeight").value; 
	id='12';
	console.log("sequence : "+id);
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath }/SequenceServlet",
		data:{"id":id}, 
		success:function(data) {

			alert(data);
		
		}
	});
	
}


</script>
<body>
	<div id="blockButton">
	<button id ="weightButton" type="button" onClick="newStrart()" 
						style="width: 60%;height:100px;font-size:40px;">点击确认序列值</button>
						<input  placeholder="请输入当前序列号值" id="inputWeight"
		 style="width:30%;height:100px;font-size:30px;" />	
		 </div>
	<br>	

</body>
</html>