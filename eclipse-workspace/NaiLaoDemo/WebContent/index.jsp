<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.time.LocalDate"
import="java.time.Month"
import="java.time.Period"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎来到奶酪🧀的世界️</title>
</head>
<body>

<% String[] showDate = {"","",""};
LocalDate today = LocalDate.now();
showDate[0] = "当前日期: " + today;
//System.out.println("Today : " + today);
LocalDate birthDate = LocalDate.of(2020, Month.AUGUST, 26);
// System.out.println("BirthDate : " + birthDate);
showDate[1] = "奶酪生日: " + birthDate;
Period p = Period.between(birthDate, today);
// System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
showDate[2] =
		"年      龄:"+ p.getYears()+"年"+
					p.getMonths()+"月"+
						p.getDays()+"日";%>

<%=showDate[0] %><br>
<%=showDate[1] %><br>
<%=showDate[2] %><br>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	var ajax;
	function startEating(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/startEatServlet",
			data:{"id":'123'}, 
			success:function(data) {
				alert(data);
			}
		});
		
	}
	function endEatting(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/endEatServlet",
			data:{"id":'456'}, 
			success:function(data) {
				alert(data);
			}
		});
	}
</script>
	<input type="submit" value="开始喂奶🍼" onClick="startEating()" /><br>
	<input type="submit" value="结束喂奶🍼" onClick="endEatting()" /><br>	
	
</body>
</html>