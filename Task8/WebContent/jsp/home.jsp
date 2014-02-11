<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container appMenu">
	<c:forEach var="photo" items="${photos}" varStatus="status">
		<div class="row">
			<div class="col">
				<span>${photo.getOwnerName() }</span>
				<div class="thumbnail"> <img src="${photo.getURL() }">
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<jsp:include page="bottom.jsp" />