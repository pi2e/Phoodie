<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

<title>PHOODIE</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="bootstrap/js/jquery-2.1.0.min.js" type="text/javascript"></script>
<!-- Custom styles for this template -->

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="navbar navbar-fixed-top navbar-default appMenu"
		role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span><span
						class="glyphicon glyphicon-cutlery"></span><span class="appHead">
							Phoodie</span></span></a> <a href="allPhotos.do" class="navbar-icon"><span
					class="glyphicon glyphicon-home"></span><span class="hidden-xs">
						Home</span></a> <a href="userPhotos.do" class="navbar-icon"><span
					class="glyphicon glyphicon-camera"></span><span class="hidden-xs">
						Your Photos</span></a>
						<a href="discover.do" class="navbar-icon"><span
					class="glyphicon glyphicon-search"></span><span class="hidden-xs">
						Search</span></a>
						<a href="viewPhoodList.do" class="navbar-icon hidden-xs"><span
					class="glyphicon glyphicon-star"></span><span class="hidden-xs">
						Phood List</span></a>
					
			</div>

			<div class="navbar-collapse collapse navbar-right">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-cog"></span></a>
						<ul class="dropdown-menu">
							<li><a href="twitterLogin.do">Twitter</a></li>
							<li><a href="analytics.do"><span
					class="glyphicon glyphicon-stats"></span> Analytics</a></li>
							<li><a href="logout.do">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>
