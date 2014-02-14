<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

				<button type="button" onclick="fav(${photo.id});"
					class="btn btn-default buttons">
					<span class="glyphicon glyphicon-heart-empty"></span> <span
						class="buttonFont">Fav</span>
				</button>

				<form action="mustTry.do" method="post" style="display: inline;">
					<input type="hidden" name="photoId" value="${photo.id }">
					<button type="submit"
						class="btn btn-default buttons ${photo.mustTry == 'true' ? 'disabled' : ''}">
						<span class="glyphicon glyphicon-star"></span> <span
							class="buttonFont">Must try!</span>
					</button>
				</form>


				<button type="button" class="btn btn-default buttons"
					data-toggle="modal" data-target="#myModal${photo.id }">
					<span class="glyphicon glyphicon-comment"></span> <span
						class="buttonFont">Comment</span>
				</button>

				<!-- Modal -->
				<div class="modal fade" id="myModal${photo.id}" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header hidden-xs">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Comments</h4>
							</div>
							<div class="modal-body">

								<c:set var="fieldLength" value="${fn:length(photo.comments)}"/>
								<c:forEach var="comment" items="${photo.comments}" varStatus="status">
									<div class="media">
										<div class="media-body">
											<h5 class="media-heading text-primary">${comment.username}</h5>
											${comment.comment}
										</div>
									</div>
								</c:forEach>

							</div>
							<div class="modal-footer">

								<form action="postComment.do" method="post"
									id="commentForm${photo.id}" name="commentForm">
									<input type="hidden" name="type" value="">
										<input type="hidden" name="photoId" value="${photo.getId() }">
									<input type="text" name="comment" class="form-control"
										placeholder="Your comment..."> <br />
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
										<button type="button" class="btn btn-primary"
										onclick="javascript:submitPage('${photo.id}', 'tweet');">Tweet <img class="flickrButton" src="./img/tweet.png"/></button>
									<button type="button" class="btn btn-primary"
										onclick="javascript:submitPage('${photo.id}', 'comment');">Post</button>
								</form>

							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</c:forEach>

	<script>
	function fav(photoId) {
		var url = "./fav.do?photoid=" + photoId;
		$.get(url);
		alert("fav it!");
		
	}
		function submitPage(photoId, type) {
			
			var comm = document.getElementById("commentForm" + photoId);
			comm.type.value = type;
			//this.form.comment;
			if (comm.comment.value.trim() == '') {
				return;
			} else {
				comm.submit();
			}
		}
	</script>
</div>