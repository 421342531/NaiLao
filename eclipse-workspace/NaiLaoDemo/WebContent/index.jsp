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
showDate[0] = ""+ today;
//System.out.println("Today : " + today);
LocalDate birthDate = LocalDate.of(2020, Month.AUGUST, 26);
// System.out.println("BirthDate : " + birthDate);
showDate[1] = "" + birthDate;
Period p = Period.between(birthDate, today);
// System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
showDate[2] =
		 p.getYears()+"年"+
					p.getMonths()+"月"+
						p.getDays()+"日";%>

<br>
<h2>当前时间: <input id="time" style="font-size:20px;" value="<%=showDate[0] %>"></h2>	
<h2>当前日期: <input style="font-size:20px;" value="<%=showDate[0] %>"></h2>	
<h2>奶酪生日: <input style="font-size:20px;" value="<%=showDate[1] %>"></h2>
<h2>年      龄  :<input style="font-size:20px;" value="<%=showDate[2] %>"/></h2><br><br>	
<script src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	var ajax;
	function startEating(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/startEatServlet",
			data:{"id":'123'}, 
			success:function(data) {
				//alert(data);
				document.getElementById("startTime").value = "开始时间:"+data;//data;
				document.getElementById("endTime").value = "";
			}
		});
		
	}
	function endEatting(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/StopEatServlet",
			data:{"id":'456'}, 
			success:function(data) {
				//alert(data);
				var showText =data.split("|");
				//var showText1 = showText(0);
				var part1=showText[0];
				var part2=showText[1];
				var part3=showText[2];
				
				document.getElementById("endTime").value = 
					"开始时间:"+part1+" 结束时间:"+part2+" 吃奶用时:"+part3+"分钟";
			//	document.getElementById("timeCount").value = data;
			}
		});
	}
	function datetime() {
	 	var now = new Date();
	 	document.getElementById("time").value = now.getFullYear() + "-"
	 	+ (now.getMonth() + 1) + "-" + now.getDate();
	 	document.getElementById("time").value += " " + now.getHours() + ":"
	 	+ now.getMinutes() + ":" + now.getSeconds();
		 }
	 window.setInterval("datetime()", 1000);
	 
	 function shitPlus(){
		 $.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/addShitServlet",
				data:{"id":'999'}, 
				success:function(data) {
					document.getElementById("countShit").value = "今日累计臭臭次数:"+data+"次";
				}
			});
	 }

</script>
	<br>
	<input type="submit" style="height:60px;width:30%;font-size:20px;" value="开始喂奶🍼" onClick="startEating()" style="text-align: left;" />
	<input id="startTime" style="width:40%;font-size:20px;" /><br><br>
	<input type="submit" style="height:60px;width:30%;font-size:20px;" value="结束喂奶🍼" onClick="endEatting()" style="text-align: left;" />
	<input id="endTime" style="width:40%;font-size:20px;" />
	<br><br	>
	<input type="button" onClick="shitPlus()" value="又来一次臭臭" 
						style="width: 30%;height:60px;font-size:20px;">	
	<input id="countShit" style="width:40%;font-size:20px;" />
</body>
</html>