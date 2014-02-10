<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<c:forEach var="photo" items="${photos}" varStatus="status">
		<div class="row text-center">
			<div class="col-xs-8 col-lg-4">
				<a href="#" class="thumbnail"> <img class="img-responsive" src="${photo.getURL() }">
				</a>
			</div>
		</div>
	</c:forEach>
</div>

<jsp:include page="bottom.jsp" />