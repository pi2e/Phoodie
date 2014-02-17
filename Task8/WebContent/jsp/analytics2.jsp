<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="com.phoodie.databean.DishRank"%>
<%@page import="com.phoodie.databean.DishByDate"%>

<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="analytics.do">Cuisine</a></li>
		<li class="active"><a href="analytics2.do">Dish</a></li>
		<li><a href="analytics3.do">Restaurant</a></li>
	</ul>

</div>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	 <%  DishRank[] arrayData =(DishRank[]) request.getAttribute("arrayData");%>
        var data = google.visualization.arrayToDataTable([
          ['Dish', 'Mood'],
          <% for(int i = 0; i < 5; i++) { %>
          
          ['<%=arrayData[i].getDish()%>',<%=arrayData[i].getMoodProb()%>],

		<%} %>
	]);

		var options = {
			title : 'Dish By Mood',
			vAxis : {
				title : 'Dish',
				titleTextStyle : {
					/* color : 'blue' */
				}
			}
		};

		var chart = new google.visualization.BarChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>

</script>
<%  DishRank arrayData3 =(DishRank) request.getAttribute("dishData");
/* Double averageMood = (arrayData3.getMoodProb()*100);
int val = averageMood.intValue();*/
%>
 <script type='text/javascript' src='https://www.google.com/jsapi'></script>
    <script type='text/javascript'>
      google.load('visualization', '1', {packages:['gauge']});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  <% if(arrayData3 != null){%>
        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['Mood',<%=arrayData3.getMoodProb()*100%>],
         
        ]);

        var options = {
          width: 400, height: 120,
          redFrom: 90, redTo: 100,
          yellowFrom:75, yellowTo: 90,
          minorTicks: 5
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div3'));
        chart.draw(data, options);
      }
    	  <%}%>
    </script>
</script>
 <%  DishByDate[] arrayData2 =(DishByDate[]) request.getAttribute("arrayData2");%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  <% if(arrayData2 != null){%>
    		var data = google.visualization.arrayToDataTable([
    		                                  				[ 'Date', 'Mood' ],
    		                                  			    <% for(int i = 0; i < arrayData2.length; i++) { %>
    		                                  		         
    		                                  		          [new Date('<%=arrayData2[i].getDate()%>'), <%=arrayData2[i].getAverage()%>],
    		                                  		          
    		                                  		          <%} %>
    		                                  		          
    		                                  				]);

        var options = {
          title: 'Dish By Date'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div2'));
        chart.draw(data, options);
      }
 	   <%}%>
    </script>
</head>

<form id="searchForm" action="analytics2.do" method="post">
	<div class="container searchBar">
		<div class="input-group">
			<input type="text" id="search" name="search"
				class="form-control appMenu">
			<div class="input-group-btn">
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</div>
			<!-- /btn-group -->
		</div>
		<!-- /input-group -->

	</div>

</form>

<body>
	<div id="chart_div" style="width: 500px; height: 400px;"></div>
<div id="chart_div3" style="width: 500px; height: 150px;"></div>
	<div id="chart_div2" style="width: 900px; height: 400px;"></div>
</body>
<jsp:include page="bottom.jsp" />