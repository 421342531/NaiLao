<%@ page language="java" contentType="text/html; charset=utf-8"
import="java.time.LocalDate"
import="java.time.Month"
import="java.time.Period"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" >
<title>奶酪成长记🧀 查询版</title>
</head>
<style>
#bodyid
{
	background: url("img/back1.jpg") no-repeat;
}
#startButton,#endButton,#shitButton,#huangdanButton,#nameButton,#weightButton,#refreshButton{
    width: 280px;
    border: none;
    background-color: #FF9000;
    color: #FFFFFF;
    height: 40px;
    margin-top: 10px;
    border-radius: 28px;
    outline: 0;
    font-size: 16px;
}

#inputHuangDan,#getNameText{
 margin-top: 10px;
}

</style>
<body id ="bodyid" 
 onload="queryShit();queryWeekHuangDan();queryShitCharts();QueryEatStartAndEndTime();queryWeight();tongjiEatTime();tongjiShitTimeStamp();">
 
<% String[] showDate = {"","",""};
LocalDate today = LocalDate.now();
showDate[0] = ""+ today;
LocalDate birthDate = LocalDate.of(2020, Month.AUGUST, 26);
showDate[1] = "" + birthDate;
Period p = Period.between(birthDate, today);
showDate[2] =
		 p.getYears()+"年"+
					p.getMonths()+"月"+
						(p.getDays()+1)+"日";%>

<br>
<h1>当前时间: <input id="time" style="font-size:30px;" value="<%=showDate[0] %>"></h1>
<h1>当前日期: <input style="font-size:30px;" value="<%=showDate[0] %>"></h1>	
<h1>奶酪生日: <input style="font-size:30px;" value="<%=showDate[1] %>"></h1>
<h1>年      龄  :<input style="font-size:30px;" value="<%=showDate[2] %>"/></h1><br><br>	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="js/jquery-3.5.1.min.js"></script>
 <script src="js/echarts.min.js"></script>
<script type="text/javascript">


function tongjiShitTimeStamp(){
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath }/TongJiShit",
		data:{"id":'123'}, 
		success:function(data) {
			var jsonObj = JSON.parse(data);
			var xStr =new Array();
			var yStr = new Array();
				for(var key in jsonObj){
					var time = key.substr(8,11);
					xStr.push(time);
					yStr.push(jsonObj[key])
					}
				console.log('x= '+xStr);
				console.log('x length='+xStr.length);
				console.log('y='+yStr);
				var dom = document.getElementById("mainShitTimeStamp");
				var option = {
				        title:{
				            text:'今日已经臭臭'+xStr.length+' 次', 
				            left:'center',
				            fontSize:40,
				            fontWeight:'bold'
				        },
				        tooltip:{},
				        legend: {
				            right: 10,
				            data: ['1', '1.0001']
				        },
				        xAxis:{
				   		 data:xStr,
				   		 name:'时间(时:分)'
				   		},
				        label: {normal: {
				        	show: true
				        	}},
				        yAxis:{
				        	 name:''
				        },
				        series:[{
				            name:'',
				            type:'scatter',
				            data: yStr,
				            symbolSize: 30
				        }],
				        itemStyle: {
			            	color:function(e){
			            		console.log(e.data)
			            		if(e.data == '1'){
			            			console.log(e.data+'--red');
			            			return 'blue';
			            		}else{
			            			console.log(e.data+'--blue')
			            			return 'red';
			            		}
			            	}
			            }
				    };
				var myChart = echarts.init(dom);
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
		
		}
		});
}
	function tongjiEatTime(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/TongJiEat",
			data:{"id":'123'}, 
			success:function(data) {
				var jsonObj = JSON.parse(data);
				var xStr =new Array();
				var yStr = new Array();
					for(var key in jsonObj){
						var time = key.substr(8,11);
						xStr.push(time);
						yStr.push(jsonObj[key])
						}
					console.log('x= '+xStr);
					console.log('x length='+xStr.length);
					console.log('y='+yStr);
					var dom = document.getElementById("mainTongjiEat");
					var option = {
					        title:{
					            text:'今日已经喂奶🍼 '+xStr.length+' 次',
					            left:'center',
					            fontSize:40,
					            fontWeight:'bold'
					        },
					        tooltip:{},
					        legend: {
					            right: 10,
					            data: ['1', '1.0001']
					        },
					        xAxis:{
					   		 data:xStr,
					   		 name:'时间(时:分)'
					   		},
					        label: {normal: {
					        	show: true
					        	}},
					        yAxis:{
					        	 name:''
					        },
					        series:[{
					            name:'',
					            type:'scatter',
					            data: yStr,
					            symbolSize: 30
					        }],
					        itemStyle: {
				            	color:function(e){
				            		console.log(e.data)
				            		if(e.data == '1'){
				            			console.log(e.data+'--red');
				            			return 'red';
				            		}else{
				            			console.log(e.data+'--blue')
				            			return 'blue';
				            		}
				            	}
				            }
					    };
					var myChart = echarts.init(dom);
					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);
			
			}
			});
	}
	
	var ajax;
	function refresh(){
	//	queryShit();
		queryWeekHuangDan();
		queryShitCharts();
	//	QueryEatStartAndEndTime();
		queryWeight();
		tongjiEatTime();
		tongjiShitTimeStamp();
	}
	
	function insertWeight(){
		var id = 
			document.getElementById("inputWeight").value;
			//console.log('weight = '+id);
			if(id==''){
				alert('请输入体重！');
			}
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/RecordWeightServlet",
			data:{
				"id":id
			}, 
			success:function(data) {
				queryWeight();
				alert("体重录入成功！");
			}
		});
	}
	
	function QueryEatStartAndEndTime(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/QueryEatStartAndEndTime",
			data:{"id":'123'}, 
			success:function(data) {
				//console.log(data); 
				var jsonObj = JSON.parse(data);
				//console.log( jsonObj.startTime) ;
				var time_start = jsonObj.time_start;
				var time_end= jsonObj.time_end;
				document.getElementById("startButton").innerHTML = "开始喂奶🍼"+"     开始时间:"+time_start;;
			}
		});
	}	
		
	function queryWeight(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/QueryWeigthServlet",
			data:{"id":'123'}, 
			success:function(data) {
				//console.log(data); 
				var jsonObj = JSON.parse(data);
				var xStr =new Array();
				var yStr = new Array();
					for(var key in jsonObj){
						xStr.push(key);
						yStr.push(jsonObj[key])
						}
					//console.log(xStr);
					//console.log(yStr);
				
				var dom = document.getElementById("mainWeight");
				//console.log('weight='+dom);
				
				var option = {
				        title:{
				            text:'',
				            left:'center',
				            fontSize:40,
				            fontWeight:'bold'
				        },
				        axisLabel:{
				            textStyle:{
				              fontSize:25
				            }
				        },
				        tooltip:{},
				        legend:{
				            data:['访客来源']
				        },
				        xAxis:{
				            data:xStr,
				            name:'日期'
				        },
				        label: {normal: {
				        	show: true
				        	}},
				        yAxis:{
				        	 name:'体重'
				        },
				        
				        series:[{
				        	itemStyle : { normal: {label : {show: true}}},
				            name:'体重',
				            type:'line',
				            data:yStr,
				            color:'#0000FF'
				        }]
				    };
				var myChart = echarts.init(dom);
				//console.log('myChart='+myChart)
				myChart.setOption(option);
			}
		});
	}
	
	function queryWeekHuangDan(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/QueryHuangDanWeekServlet",
			data:{"id":'123'}, 
			success:function(data) {
				//console.log(data); 
				var jsonObj = JSON.parse(data);
				var xStr =new Array();
				var yStr = new Array();
					for(var key in jsonObj){
						xStr.push(key);
						yStr.push(jsonObj[key])
						}
					//console.log(xStr);
					//console.log(yStr);
				
				var dom = document.getElementById("main");
				//console.log('dom='+dom);
				
				var option = {
				        title:{
				            text:'',
				            left:'center',
				            fontSize:40,
				            fontWeight:'bold'
				        },
				        tooltip:{},
				        legend:{
				            data:['访客来源']
				        },
				        xAxis:{
				            data:xStr,
				            name:'日期'
				        },
				        label: {normal: {
				        	show: true
				        	}},
				        yAxis:{
				        },
				        
				        series:[{
				        	itemStyle : { normal: {label : {show: true}}},
				            name:'黄疸值',
				            type:'line',
				            data:yStr
				        }]
				    };
				var myChart = echarts.init(dom);
				//console.log('myChart='+myChart)
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			}
		});
	}
	
	function queryShitCharts(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/QueryShitWeekServlet",
			data:{"id":'123'}, 
			success:function(data) {
				//console.log(data); 
				var jsonObj = JSON.parse(data);
				var xStr =new Array();
				var yStr = new Array();
					for(var key in jsonObj){
						xStr.push(key);
						yStr.push(jsonObj[key]) 
					　　}
					//console.log(xStr);
					//console.log(yStr);
				var domShit = document.getElementById("mainShit");
				var optionShit = {
						title:{
				            text:'',
				            left:'center',
				            fontSize:40,
				            fontWeight:'bold'
				        },
						xAxis: {
					        type: 'category',
					        data: xStr,
				            name:'日期(月:日)'
				        },
					    yAxis: {
					        type: 'value',
					        name:'次数'
					    },
					    series: [{
					        data: yStr ,//[120, 200, 150, 80, 70, 110, 130],
					        type: 'bar',
					        itemStyle : { normal: {label : {show: true}}},
					        name:'臭臭次数',
					        color:'#C0C0C0'
					    }]
				    };
				var myChartShit = echarts.init(domShit);
				// 使用刚指定的配置项和数据显示图表。
				myChartShit.setOption(optionShit);
			}
			});
	}
	
	function startEating(obj){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/startEatServlet",
			data:{"id":'123'}, 
			success:function(data) {
				obj.innerHTML="开始喂奶🍼 "+"   开始时间:"+data;
				document.getElementById("endButton").innerHTML ="结束喂奶🍼";
				tongjiEatTime();
			}
		});
	}
	function endEatting(obj){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/StopEatServlet",
			data:{"id":''}, 
			success:function(data) {
				//console.log(data); 
				var jsonObj = JSON.parse(data);
				//console.log( jsonObj.startTime) ;
				var startTime = jsonObj.startTime;
				var endTime = jsonObj.endTime;
				var timeCount= jsonObj.timeCount;
				obj.innerHTML="结束喂奶🍼 "+"开始时间:"+startTime+" 结束时间:"+endTime+" 吃奶用时:"+timeCount+"分钟";
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
	 
	 function shitPlus(obj){
		 $.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/addShitServlet",
				data:{"id":'999'}, 
				success:function(data) {
					obj.innerHTML="又来一次臭臭💩 "+"今日累计臭臭次数:"+data+"次";
					queryShitCharts();
					tongjiShitTimeStamp();
				}
			});
	 }
	 function insertHuangDan(){
		 $.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/AddHuangDanServlet",
				data:{"id":document.getElementById("inputHuangDan").value}, 
				success:function(data) {
					if(data==0){
						queryWeekHuangDan();
						alert("今日黄疸值录入成功:"+document.getElementById("inputHuangDan").value);	
					}
					if(data==1){
						queryWeekHuangDan();
						alert("今日黄疸值更新成功:"+document.getElementById("inputHuangDan").value);
					}
					if(data==2){
						alert("请输入正确的黄疸值");	
					}
				}
			});
	 }
	 function queryShit(){
		 $.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/QueryShitServlet",
				data:{"id":'999'}, 
				success:function(data) {
					document.getElementById("showText3").innerHTML = "又来一次臭臭💩 今日累计臭臭次数:"+data+"次";
				}
			});
	 }
	 function getName(){
		 $.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/getNameServlet",
				data:{"id":'999'}, 
				success:function(data) {
					document.getElementById("getNameText").innerHTML = data;
					document.getElementById("getNameText").value = data;
				}
			});
	 }
</script>
	<center>
	<button id ="refreshButton"  type="button" onClick="refresh()"
						style="width: 100%;height:100px;font-size:40px;" >刷新</button>
	<h1 id = "showText"> 今日喂奶🍼时间点统计</h1>
		<div id  ="mainTongjiEat" style="height:500px;width:100%;font-size:30px;" > </div>
 <br><br>
	<h1>黄疸值日趋势</h1>
	<div id  ="main" style="height:500px;width:100%;font-size:30px;" > </div>
	<h1>体重趋势(kg)</h1>
	<div id  ="mainWeight" style="height:500px;width:100%;font-size:30px;" > </div>
	<h1>每日臭臭次数统计</h1>
	<div id  ="mainShit" style="height:500px;width:100%;font-size:30px;" > </div>
	<h1>今日臭臭💩时间点统计</h1>
	<div id  ="mainShitTimeStamp" style="height:500px;width:100%;font-size:30px;" > </div>
	</center>

<script>
</script>
</body>
</html>