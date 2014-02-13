<jsp:include page="top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form>
	<div class="container searchBar">
		<div class="input-group">
			<input type="text" name="search" class="form-control appMenu">
			<span class="input-group-btn">
				<button class="btn btn-default" type="submit">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</span>
		</div>
	</div>
	<select class="selectpicker" name="type">
				<option value="dish">Dish</option>
				<option value="cuisine">Cuisine</option>
				<option value="restaurant">Restaurant</option>
			</select>
</form>

<jsp:include page="photo.jsp" />

<jsp:include page="bottom.jsp" />