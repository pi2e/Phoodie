<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="com.phoodie.databean.CuisineRank"%>
<%@page import="com.phoodie.databean.CuisineByDate"%>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<%
	CuisineRank[] arrayData = (CuisineRank[]) request
			.getAttribute("arrayData");
%>
<%
	if (arrayData != null) {
%>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	
        var data = google.visualization.arrayToDataTable([
          ['Cuisine', 'Mood', {role: "style"}],
     /*   ['Cuisine', 'Mood'], */
          <%int len = arrayData.length > 5 ? 5 : arrayData.length;
				for (int i = 0; i < len; i++) {%>
          
          ['<%=arrayData[i].getName()%>',<%=arrayData[i].getMoodProb()%>,'green'],

		<%}%>
	]);

		var options = {
			title : 'Sentiment',
			backgroundColor: 'transparent',
			
		legend: {
			position: 'left', 
			legendBackgroundColor:'#428bca',
			
			},
		bar:{
			groupWidth: '40%', 
		}
			
		};

		var chart = new google.visualization.BarChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>
<%
	}
%>
<%
	CuisineRank arrayData3 = (CuisineRank) request
			.getAttribute("cuisineData");
%>
<%
	if (arrayData3 != null) {
%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
      google.load('visualization', '1', {packages:['gauge']});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	
        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['Mood',<%=arrayData3.getMoodProb() * 100%>],
         
        ]);

        var options = {
          width: 270, height: 270,
          redFrom: 0, redTo: 34,
          greenFrom: 68, greenTo: 100,
          yellowFrom:34, yellowTo: 68,
          minorTicks: 5
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div3'));
        chart.draw(data, options);
      }
    	  
    </script>
<%
	}
%>


<%
	CuisineByDate[] arrayData2 = (CuisineByDate[]) request
			.getAttribute("arrayData2");
%>
<%
	if (arrayData2 != null) {
%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  
    		var data = google.visualization.arrayToDataTable([
    		                                  				[ 'Date', 'Mood' ],
    		                                  			    <%for (int i = 0; i < arrayData2.length; i++) {%>
    		                                  		         
    		                                  		          [new Date('<%=arrayData2[i].getDate()%>'), <%=arrayData2[i].getAverage()%>],
    		                                  		          
    		                                  		          <%}%>
    		                                  		          
    		                                  				]);

        var options = {
          title: 'Sentiments over Time',
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
<%
	}
%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  <%if (arrayData2 != null) {%>
    		var data = google.visualization.arrayToDataTable([
    		                                  				[ 'Date', 'Share' ],
    		                                  			    <%for (int i = 0; i < arrayData2.length; i++) {%>
    		                                  		         
    		                                  		          [new Date('<%=arrayData2[i].getDate()%>'),<%=arrayData2[i].getShareCount()%>],
<%}%>
	]);

		var options = {
			title : 'Conversation over Time',
			 backgroundColor: 'transparent',
	        	
			vAxis : {
				gridlines : {
					color : 'transparent'
				}
			},
			hAxis : {
				gridlines : {
					color : 'transparent'
				}
			},
			 legend: {
					position: 'left', 
					
					},
		};

		var chart = new google.visualization.LineChart(document
				.getElementById('chart_div4'));
		chart.draw(data, options);
	}
<%}%>
	
</script>


<script>
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);
function drawChart() {
var data = google.visualization.arrayToDataTable([
                                                  ['cuisine name','Negative', 'Neutral', 'Positive'],
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
                                                }; 

var chart = new google.visualization.BarChart(document.getElementById('chart_div5'));
chart.draw(data, options);
}
</script>




</head>

<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="analytics.do">Cuisine</a></li>
		<li><a href="analytics2.do">Dish</a></li>
		<li><a href="analytics3.do">Restaurant</a></li>
	</ul>
</div>
<br />
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
<br />
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Top Cuisines</div>
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
					<div class="well" style="width: 900px;">
						<div id="chart_div5" style="width: 900px; height: 270px;"></div>
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

<jsp:include page="bottom.jsp" />