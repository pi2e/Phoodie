<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container appMenu">
	<c:forEach var="photo" items="${photos}" varStatus="status">
		<div class="row">
			<div class="thumbnail appRow">
				<span>${photo.getOwnerName() }</span> <img class="feedPic" src="${photo.getURL() }">

				<div class="btn-group">
					<div class="btn-group">
						<button type="button" class="btn btn-default"><span
					class="glyphicon glyphicon-heart-empty"></span> <span class="buttonFont">Fav</span></button>
					</div>
					<div class="btn-group">
						<button type="button" class="btn btn-default"><span
					class="glyphicon glyphicon-star"></span> <span class="buttonFont">Must try!</span></button>
					</div>
					<div class="btn-group">
						<button type="button" class="btn btn-default"><span
					class="glyphicon glyphicon-comment"></span> <span class="buttonFont">Comment</span></button>
					</div>
				</div>

			</div>
		</div>
	</c:forEach>
</div>

<jsp:include page="bottom.jsp" />