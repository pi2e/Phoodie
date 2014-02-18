<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container appMenu">
	<div class="row">${twitterlogin == 'false' ? 'You must login with twitter account before comment' : ''}
	</div>

	<c:forEach var="photo" items="${photos}" varStatus="status">
		<div class="row">
			<div class="thumbnail appRow">
				<span class="text-left">${photo.getOwnerName() } &nbsp;&nbsp;</span>
				<c:if test="${photo.restaurantName ne ''}">
				<a href="#">@ ${ photo.restaurantName}</a></c:if>
				<div style="float: right">
				<c:if test="${photo.rating ne ''}">
				<c:forEach begin="1" end="${photo.rating }" varStatus="loop">
    			<span class="glyphicon glyphicon-star-empty"></span> 
				</c:forEach></c:if>
					
				</div>
				<img class="feedPic" src="${photo.getURL() }">

				<button type="button" onclick="fav(${photo.id}, this);"
					class="btn btn-default buttons ${photo.favorite == 'true' ? 'disabled' : ''}">
					<span class="glyphicon glyphicon-heart-empty"></span> <span
						class="buttonFont">Fav</span>
				</button>

				<input type="hidden" name="photoId" value="${photo.id }">
				<button type="submit" onclick="musttry(${photo.id}, this);"
					class="btn btn-default buttons ${photo.mustTry == 'true' ? 'disabled' : ''}">
					<span class="glyphicon glyphicon-star"></span> <span
						class="buttonFont">Must try!</span>
				</button>


				<button type="button" class="btn btn-default buttons ${twitterlogin == 'false' ? 'disabled' : ''}"
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

								<c:set var="fieldLength" value="${fn:length(photo.statuses)}" />
								<c:forEach var="comment" items="${photo.statuses}"
									varStatus="status">
									<div class="media">
										<div class="media-body">
											<h5 class="media-heading text-primary">@${comment.user.screen_name}</h5>
											${comment.text}
										</div>
									</div>
	
									<button type="button"
										onclick="reply(${photo.getId()}, '${comment.id_str}', '${comment.user.screen_name}');"
										class="btn btn-default buttons">
										<span class="glyphicon glyphicon-share-alt"></span> <span
											class="buttonFont">Reply</span>
									</button>
									<button type="button"
										onclick="retweet('${comment.id_str}', this);"
										class="btn btn-default buttons">
										<span class="glyphicon glyphicon-retweet"></span> <span
											class="buttonFont">Retweet</span>
									</button>
								</c:forEach>

								<c:set var="fieldLength" value="${fn:length(photo.comments)}" />
								<c:forEach var="comment" items="${photo.comments}"
									varStatus="status">
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
									<input type="hidden" name="type" value=""> <input
										type="hidden" id="replyid${photo.getId()}" value=""> <input
										type="hidden" name="photoId" value="${photo.getId() }">
									<input type="text" name="comment" class="form-control"
										placeholder="Your comment..." id="comment${photo.id}">
									<br />
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
									<button type="button" class="btn btn-primary"
										onclick="javascript:submitPage('${photo.id}', 'tweet');">
										Tweet <img class="flickrButton" src="./img/tweet.png" />
									</button>
								
								</form>

							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</c:forEach>


	<script>
	function fav(photoId, button) {
		var url = "./fav.do?photoid=" + photoId;
		$.get(url);
		button.className = "btn btn-default buttons disabled";
		
	}
	
	
	function reply(photoId, tid, name) {
		$("#replyid"+photoId).val(tid);
		$("#comment" + photoId).val("@" + name + " ");
		
	}
	
	function retweet(tid) {
		var url = "./postComment.do?retweetId=" + tid;
		$.get(url);
		$("#comment" + photoId).val("");
		button.className = "btn btn-default buttons disabled";
		alert("Successful retweet!");
		
	}
	
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
			alert("Successful send tweet!");
			
	}
	</script>
</div>