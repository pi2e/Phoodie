<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="searchForm" action="discover.do">
	<div class="container searchBar">

		<div class="input-group">
			<input type="text" id="search" name="search"
				class="form-control appMenu">
			<div class="input-group-btn">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span class="glyphicon glyphicon-search"></span><span class="caret"></span>
				</button>

				<input type="hidden" id="type" name="type" value="">
				<ul class="dropdown-menu pull-right">
					<li><a href="#" onclick="javascript:setType('dish');">Dish</a></li>
					<li><a href="#" onclick="javascript:setType('cuisine');">Cuisine</a></li>
					<li><a href="#" onclick="javascript:setType('restaurant');">Restaurant</a></li>
				</ul>
			</div>
			<!-- /btn-group -->
		</div>
		<!-- /input-group -->

	</div>

</form>

<script>

function setType(type){
	var s = document.getElementById("search");
	if(s.value.trim() == '') {
		return;
	} else {

	var comm = document.getElementById("searchForm");
	document.getElementById("type").value = type;
	comm.submit();
	}
	
}
	</script>

<jsp:include page="photo.jsp" />

<jsp:include page="bottom.jsp" />