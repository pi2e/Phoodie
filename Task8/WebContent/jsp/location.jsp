<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" name="photoId" value="${photoId }"/>
<c:forEach var="locations" items="${locationlist}" varStatus="status">

<li style="text-align:left" onclick="fill('${locations.name} ,${locations.location.address[0]} , ${locations.location.city}', '${locations.categories}','${photoId}','${locations.id}','${locations.rating}');">
${locations.name}, ${locations.location.address[0]}, ${locations.location.city}
</li>
</c:forEach>

<script>
function fill(thisValue, category, photoId, yelpId, rating) {
var str = category.split(',');
var cat = str[1].replace(/\[|\]/g, '');

$('#inputString'+photoId, window.parent.document).val(thisValue);
$('#cuisine'+photoId, window.parent.document).val(cat);
$('#yelpId'+photoId, window.parent.document).val(yelpId);
$('#rating'+photoId, window.parent.document).val(rating);
var sugg = $('#suggestions'+photoId);
setTimeout(sugg.hide(), 200);
}
</script>