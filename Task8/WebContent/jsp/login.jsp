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
	<div class="container-fluid col-md-6 col-lg-5" style="float:center">
		<div class="panel panel-default text-center">

			<div class="panel-body">
				<h3 class="panel-title">
					<span><span class="glyphicon glyphicon-cutlery" style="font-size:26px"></span><span
						class="appHead" style="font-size:34px"> Phoodie</span></span>
				</h3>
				<br/>
				<span class="appMenu" style="font-size:22px">where<br/>photography &amp; food<br/>meats.</span>
				<br/><br/><br/>
				<form method="post" action="login.do">
					<input type="hidden" id="login" name="login" value="login" />
					<button type="submit" class="btn btn-default">
					<span class="appMenu">Log in with</span>&nbsp;
						<img class="flickrButton" src="./img/Flickr_logo.png"/>
					</button>
				</form>
				<form method="post" action="twitterLogin.do">
					<input type="hidden" id="login" name="login" value="login" />
					<button type="submit" class="btn btn-default">
					<span class="appMenu">Log in with Twitter</span>&nbsp;
					</button>
				</form>
			</div>
		</div>
	</div>
</body>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="offcanvas.js"></script>
</body>
</html>