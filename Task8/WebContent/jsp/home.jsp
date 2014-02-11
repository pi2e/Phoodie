<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<c:forEach var="photo" items="${photos}" varStatus="status">
		<div class="row">
			<div class="col">
				<div class="thumbnail"> <img src="${photo.getURL() }">
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<jsp:include page="bottom.jsp" />