<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="top.jsp" />
<style>
.suggestionsBox {
	position: relative;
	left: 0px;
	margin: 0px 0px 0px 0px;
	width: 400px;
	background-color: #C0C0C0;
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
	border: 2px solid #000;
	color: #fff;
}

.suggestionList {
	margin: 0px;
	padding: 0px;
}

.suggestionList li {
	margin: 0px 0px 3px 0px;
	padding: 3px;
	cursor: pointer;
}

.suggestionList li:hover {
	background-color: #DD45CD;
}
</style>

<div class="container appMenu">
<div class="row gridRow">
	<c:forEach var="photo" items="${photoList}" varStatus="status">
		<div class="col-xs-3 gridThumb">
			<div class="thumbnail">
				<img class="gridPic" src="${photo.getURL() }">
				<button type="button" class="btn btn-default buttons"
					data-toggle="modal" data-target="#myModal${photo.id }">
					<span class="glyphicon glyphicon-plus"></span> <span
						class="buttonFont">Add</span>
				</button>

				<!-- Modal -->
				<div class="modal fade" id="myModal${photo.id}" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header hidden-xs">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Add to Phoodie</h4>
							</div>
							<div class="modal-body">
								<form action="postToGroup.do" method="post"
									id="groupForm${photo.id}" name="groupForm">
									Food <input type="text" name="dish" id="dish${photo.id}" class="form-control"
										placeholder="Dish Name..." onchange=""> <br /> 
										<input
										id="cuisine${photo.id}" type="hidden" name="cuisine" />  
										<input type="text" id="yelpId${photo.id}"
										name="yelpId" />
										<input type="text" id="rating${photo.id}"
										name="rating" />
										<input
										type="hidden" name="photoId" value="${photo.id }">
									Place <input type="text" id="inputString${photo.id}"
										onkeyup="lookup(this.value,'${photo.id}');" name="restaurant"
										class="form-control" placeholder="Restaurant Name,City..">
									<br />

									<br />
									<div class="suggestionsBox" id="suggestions${photo.id}"
										style="display: none;">
										<div class="suggestionList" id="autoSuggestionsList${photo.id}"></div>
									</div>
									<input type="hidden" name="photoId" value="${photo.id }" />

									<div class="modal-footer">


										<button type="button" class="btn btn-default"
											data-dismiss="modal">Cancel</button>

										<button type="button" class="btn btn-primary"
											onclick="javascript:submitPage('${photo.id}');">Post</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</c:forEach>
</div>
</div>

<script>
	function submitPage(photoId) {

		var comm = document.getElementById("groupForm" + photoId);

		if (comm.dish.value.trim() == '') {
			return;
		} else if (comm.restaurant.value.trim() == '') {
			return;
		} else {
			comm.submit();
		}
	}

	function lookup(inputString,photoId) {
		if (inputString.length == 0) {
			$('#suggestions'+photoId).hide();
		} else {
			$.post("searchYelp.do", {
				queryString : "" + inputString ,
				photoId : "" + photoId
				
			}, function(data) {

				if (data.length > 0) {
					$('#suggestions'+photoId).show();
					$('#autoSuggestionsList'+photoId).html(data);
				}
			});
		}
	}
</script>

<jsp:include page="bottom.jsp" />