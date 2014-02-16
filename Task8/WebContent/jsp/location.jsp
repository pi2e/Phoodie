<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" name="selected" value=""/>
<c:forEach var="locations" items="${locationlist}" varStatus="status">

<li style="text-align:left" onclick="fill('${locations.name} ,${locations.location.address[0]} , ${locations.location.city}', '${locations.categories}');">
${locations.name}, ${locations.location.address[0]}, ${locations.location.city}
</li>
</c:forEach>

<script>
function fill(thisValue, category) {
var str = category.split(',');
var cat = str[1].replace(/\[|\]/g, '');

$('#inputString', window.parent.document).val(thisValue);
$('#cuisine', window.parent.document).val(cat);
//$('#inputString').val(thisValue);
setTimeout("$('#suggestions').hide();", 200);
}
</script>