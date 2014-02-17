<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="com.phoodie.databean.CuisineRank"%>
<%@page import="com.phoodie.databean.CuisineByDate"%>

<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	 <%  CuisineRank[] arrayData =(CuisineRank[]) request.getAttribute("arrayData");%>
        var data = google.visualization.arrayToDataTable([
          ['Cuisine', 'Mood'],
          <% for(int i = 0; i < 5; i++) { %>
          
          ['<%=arrayData[i].getCuisineId()%>',<%=arrayData[i].getMoodProb()%>],

		<%} %>
	]);

		var options = {
			title : 'Cuisine By Mood',
			vAxis : {
				title : 'Cuisine',
				titleTextStyle : {
					 color : 'blue' 
				}
			}
		};

		var chart = new google.visualization.BarChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>
<%  CuisineRank arrayData3 =(CuisineRank) request.getAttribute("cuisineData");
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
    
 <%  CuisineByDate[] arrayData2 =(CuisineByDate[]) request.getAttribute("arrayData2");%>
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
          title: 'Mood By Date'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div2'));
        chart.draw(data, options);
      }
 	   <%}%>
    </script>

</head>

<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="analytics.do">Cuisine</a></li>
		<li><a href="analytics2.do">Dish</a></li>
		<li><a href="analytics3.do">Restaurant</a></li>
	</ul>
</div>

<form id="searchForm" action="analytics.do" method="post">
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
	<div id="chart_div" style="width: 600px; height: 400px;"></div>
	<div id="chart_div3" style="width: 500px; height: 150px;"></div>
	<div id="chart_div2" style="width: 900px; height: 400px;"></div>
</body>

<jsp:include page="bottom.jsp" />