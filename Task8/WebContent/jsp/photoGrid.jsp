<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container appMenu gridRow">
	<div class="row">${twitterlogin == 'false' ? 'You must login with twitter account before comment' : ''}
	</div>

<div class="row">

	<c:forEach var="photo" items="${photos}" varStatus="status">
		<div class="col-xs-3">
			<div class="gridAppRow">
				<img class="gridFeedPic" src="${photo.getURL() }">
			</div>
		</div>
	</c:forEach>

</div>
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