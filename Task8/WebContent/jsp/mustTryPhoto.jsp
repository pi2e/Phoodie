<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container appMenu">

	<c:forEach var="photo" items="${photos}" varStatus="status">
		<div class="row">
			<div class="thumbnail appRow">
				<span class="text-left">${photo.getOwnerName() } &nbsp;&nbsp;</span>
				<img class="feedPic" src="${photo.getURL() }">

				<button type="button" class="btn btn-default buttons"
					data-toggle="modal" data-target="#myModal${photo.id }">
					<span class="glyphicon glyphicon-star"></span> <span
						class="buttonFont">Tried it!</span>
				</button>

				<!-- Modal -->
				<div class="modal fade" id="myModal${photo.id}" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header hidden-xs">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Share your experience!</h4>
							</div>
							<div class="modal-body">

								<form action="removeFood.do" method="post"
									id="commentForm${photo.id}" name="commentForm">
									<input type="hidden" name="type" value=""> 
									<input
										type="hidden" id="replyid${photo.getId()}" value=""> <input
										type="hidden" name="photoId" value="${photo.getId() }">
									<input type="text" name="comment" class="form-control"
										placeholder="Your comment..." id="comment${photo.id}">
									<br />
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
									<button type="submit" class="btn btn-primary">
										No thanks
									</button>
									<button type="button" class="btn btn-primary"
										onclick="javascript:submitPage('${photo.id}', 'tweet');">
										Tweet <img class="flickrButton" src="./img/tweet.png" />
									</button>
									<%-- 									<button type="button" class="btn btn-primary"
										onclick="javascript:submitPage('${photo.id}', 'comment');">Post</button> --%>
								</form>

							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</c:forEach>


	<script>
	
	function musttry(photoId, button) {
		var url = "./mustTry.do?photoId=" + photoId;
		$.get(url);
		button.className = "btn btn-default buttons disabled";
		
	}
	
	function submitPage(photoId, type) {
			var comm = document.getElementById("comment" + photoId);
			comm.type.value = type;
			var url = "./postComment.do?photoId=" + photoId + "&comment=" + comm.value + "&replyid=" + $("#replyid"+photoId).val();
			$.get(url);
			$("#comment" + photoId).val("");
			$('#infobody').html('Successful send tweet!');
			$('#info').modal('show');
			bootbox.alert("Successful send tweet!");
			
	}
	</script>
</div>