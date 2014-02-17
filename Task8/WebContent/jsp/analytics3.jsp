<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="com.phoodie.databean.RestaurantRank"%>
<%@page import="com.phoodie.databean.RestaurantByDate"%>
<head>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	 <%  RestaurantRank[] arrayData =(RestaurantRank[]) request.getAttribute("arrayData");%>
        var data = google.visualization.arrayToDataTable([
          ['Dish', 'Mood'],
          <% for(int i = 0; i < 5; i++) { %>
          
          ['<%=arrayData[i].getRestaurantId()%>',<%=arrayData[i].getMoodProb()%>],

		<%} %>
	]);

		var options = {
			title : 'Restaurant By Mood',
			vAxis : {
				title : 'Restaurant',
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

</script>
 <%  RestaurantByDate[] arrayData2 =(RestaurantByDate[]) request.getAttribute("arrayData2");%>
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
          title: 'Restaurant By Date'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div2'));
        chart.draw(data, options);
      }
 	   <%}%>
    </script>
</head>

<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="analytics.do">Cuisine</a></li>
		<li><a href="analytics2.do">Dish</a></li>
		<li class="active"><a href="analytics3.do">Restaurant</a></li>
	</ul>

</div>
<form id="searchForm" action="analytics3.do" method="post">
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

	<div id="chart_div2" style="width: 900px; height: 400px;"></div>
</body>

<jsp:include page="bottom.jsp" />