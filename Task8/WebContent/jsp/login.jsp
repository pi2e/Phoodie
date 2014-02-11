<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<form method="post" action="login.do">
		<input type="hidden" id="login" name="login" value="login"/> Flickr
		Login:
		<button type="submit" class="btn btn-success">
			<img src="./img/flickr-logo-button.png" width="30"  height="30" />
		</button>
	</form>
</div>