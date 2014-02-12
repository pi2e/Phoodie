<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="container searchBar">
		<div class="input-group">
			<input type="text" class="form-control appMenu"> <span
				class="input-group-btn">
				<button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</span>
		</div>
	</div>

<div class="container appMenu">

	<c:forEach var="photo" items="${photos}" varStatus="status">
		<div class="row">
			<div class="thumbnail appRow">
				<span class="text-left">${photo.getOwnerName() } &nbsp;&nbsp;</span>
				<a href="#">@ Five Guys Burgers</a>
				<div style="float: right">
					<span class="glyphicon glyphicon-star-empty"></span> <span
						class="glyphicon glyphicon-star-empty"></span> <span
						class="glyphicon glyphicon-star-empty"></span> <span
						class="glyphicon glyphicon-star-empty"></span>
				</div>
				<img class="feedPic" src="${photo.getURL() }">
				<button type="button" class="btn btn-default buttons">
					<span class="glyphicon glyphicon-heart-empty"></span> <span
						class="buttonFont">Fav</span>
				</button>
				<button type="button" class="btn btn-default buttons">
					<span class="glyphicon glyphicon-star"></span> <span
						class="buttonFont">Must try!</span>
				</button>
				<button type="button" class="btn btn-default buttons">
					<span class="glyphicon glyphicon-comment"></span> <span
						class="buttonFont">Comment</span>
				</button>
			</div>

		</div>
	</c:forEach>
</div>

<jsp:include page="bottom.jsp" />