<jsp:include page="top.jsp" />




	
	<jsp:include page="photo.jsp" />
	
<ul class="pager">
  <li><a href="allPhotos.do?page=${previousPage }">Previous</a></li>
  <li><a href="allPhotos.do?page=${nextPage }">Next</a></li>
</ul>

<jsp:include page="bottom.jsp" />