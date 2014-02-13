<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				<button type="button" class="btn btn-default buttons"
					data-toggle="modal" data-target="#myModal${photo.id }">
					<span class="glyphicon glyphicon-comment"></span> <span
						class="buttonFont">Comment</span>
				</button>

				<!-- Modal -->
				<div class="modal fade" id="myModal${photo.id}" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Comments</h4>
							</div>
							<div class="modal-body">${photo.id }</div>
							<div class="modal-footer">

								<form action="postComment.do" method="post" id="commentForm${photo.id}"
									name="commentForm">
									<input type="hidden" name="photoId" value="${photo.getId() }">
									<input type="text" name="comment" class="form-control"
										placeholder="Your comment..."> <br />
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
									<button type="button" class="btn btn-primary"
										onclick="javascript:submitPage('${photo.id}');">Post</button>
								</form>

							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</c:forEach>

	<script>
		function submitPage(photoId) {
			var comm = document.getElementById("commentForm"+photoId);
			//this.form.comment;
			if (comm.comment.value.trim() == '') {
				return;
			} else {
				comm.submit();
			}
		}
	</script>
</div>