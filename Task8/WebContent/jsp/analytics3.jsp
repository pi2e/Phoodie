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
<%  RestaurantRank[] arrayData =(RestaurantRank[]) request.getAttribute("arrayData");%>
<% if(arrayData != null){%>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	 
        var data = google.visualization.arrayToDataTable([
          ['Dish', 'Mood',{role: "style"}],
          <% 
          int len = arrayData.length > 5 ? 5 : arrayData.length;
          for(int i = 0; i < len; i++) { %>
          
          ['<%=arrayData[i].getRestaurantId()%>',<%=arrayData[i].getMoodProb()%>,"green"],

		<%} %>
	]);

		var options = {
			title : 'Sentiment',
			 vAxis : {
				title : 'Restaurant',
				titleTextStyle : {
					 /* color : 'blue'  */
				}
			}, 
      		bar:{
    			groupWidth: '40%', 
    		},
    		legend: {
    			position: 'left', 
    			
    			},
		};

		var chart = new google.visualization.BarChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>
<%}%>

</script>
<%  RestaurantRank arrayData3 =(RestaurantRank) request.getAttribute("restaurantData");
/* Double averageMood = (arrayData3.getMoodProb()*100);
int val = averageMood.intValue();*/
%>
<% if(arrayData3 != null){%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
      google.load('visualization', '1', {packages:['gauge']});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    
        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['Mood',<%=arrayData3.getMoodProb()*100%>],
         
        ]);

        var options = {
                min : -100,
                max : 100,
                width: 270, height: 270,
                redFrom: -100, redTo: -33,
                greenFrom: 34, greenTo: 100,
                yellowFrom: -33, yellowTo: 34,
                 minorTicks: 5
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div3'));
        chart.draw(data, options);
      }
    	 
    </script>
<%}%>

</script>
<%  RestaurantByDate[] arrayData2 =(RestaurantByDate[]) request.getAttribute("arrayData2");%>
<% if(arrayData2 != null){%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  
    		var data = google.visualization.arrayToDataTable([
    		                                  				[ 'Date', 'Mood' ],
    		                                  			    <% for(int i = 0; i < arrayData2.length; i++) { %>
    		                                  		         
    		                                  		          [new Date('<%=arrayData2[i].getDate()%>'), <%=arrayData2[i].getAverage()%>],
    		                                  		          
    		                                  		          <%} %>
    		                                  		          
    		                                  				]);

        var options = {
          title: 'Sentiment Over Time',
          backgroundColor: 'transparent',
     	 
          vAxis: {
  		    gridlines: {
  		        color: 'transparent'
  		    }
  		},
  hAxis: {
      gridlines: {
          color: 'transparent'
      }
  },
  legend: {
		position: 'left', 
		
		},
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div2'));
        chart.draw(data, options);
      }
 	  
    </script>
<%}%>

</script>
<% if(arrayData2 != null){%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    
    		var data = google.visualization.arrayToDataTable([
    		                                  				[ 'Date', 'Share' ],
    		                                  			    <% for(int i = 0; i < arrayData2.length; i++) { %>
    		                                  		         
    		                                  		          [new Date('<%=arrayData2[i].getDate()%>'), <%=arrayData2[i].getShareCount()%>],
    		                                  		          
    		                                  		          <%} %>
    		                                  		          
    		                                  				]);

        var options = {
          title: 'Conversation Over Time',
          backgroundColor: 'transparent',
     	 
          vAxis: {
  		    gridlines: {
  		        color: 'transparent'
  		    }
  		},
  hAxis: {
      gridlines: {
          color: 'transparent'
      }
  },
  legend: {
		position: 'left', 
		
		},
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div4'));
        chart.draw(data, options);
      }
 	   
    </script>
<%}%>

<script>
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);
function drawChart() {
var data = google.visualization.arrayToDataTable([
                                                  ['Restaurant Name','Negative', 'Neutral', 'Positive'],
                                                  ['${searchTerm}', ${commentdatas.negativecount}, ${commentdatas.neutralcount}, ${commentdatas.positivecount}]
                                                ]);

 var options = {
		 title : 'Sentiment',
		 backgroundColor: 'transparent',
                                                  width: 600,
                                                  height: 200,
                                                  legend: { position: 'top', maxLines: 3 },
                                          	      bar: { groupWidth: '50%' },
                                                  isStacked: true,
                                                  colors:['red','orange','green'],
                                                }; 

var chart = new google.visualization.BarChart(document.getElementById('chart_div5'));
chart.draw(data, options);
}
</script>
</head>

<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="analytics.do">Cuisine</a></li>
		<li><a href="analytics2.do">Dish</a></li>
		<li class="active"><a href="analytics3.do">Restaurant</a></li>
	</ul>
</div>

<br/>
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
<br/>
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Top Restaurants</div>
		<div class="panel-body">
			<div id="chart_div" style="width: 500px; height: 300px;"></div>
		</div>
	</div>

	<c:choose>
		<c:when test="${searchTerm != null}">
			<div class="panel panel-default">
				<div class="panel-heading">${searchTerm}</div>
				<div class="panel-body">
					<div class="well" style="width: 300px;">
						<div id="chart_div3" style="width: 270px; height: 270px;"></div>
					</div>
					<div class="well" style="width: 600px;">
						<div id="chart_div5" style="width: 600px; height: 200px;"></div>
					</div>
					<div class="well" style="width: 900px;">
						<div id="chart_div2" style="width: 900px; height: 270px;"></div>
					</div>
					<div class="well" style="width: 900px;">
						<div id="chart_div4" style="width: 900px; height: 270px;"></div>
					</div>
				</div>
			</div>
		</c:when>
	</c:choose>
</div>
<!-- <div class="container">
	<div id="chart_div" style="width: 600px; height: 400px;"></div>
	<div id="chart_div3" style="width: 500px; height: 150px;"></div>
	<div id="chart_div2" style="width: 900px; height: 400px;"></div>
	<div id="chart_div4" style="width: 900px; height: 400px;"></div>
</div> -->
<jsp:include page="bottom.jsp" />