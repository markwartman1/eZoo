	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
		
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>eZOO <small>Feeding Schedules - info</small></h1>
		<hr class="paw-primary">
		
		<div class="row">
		  	<div class="col-md-12">
		  		<h3>Feeding Schedules</h3>
		  		
		  				<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Id</th>
					<th class="text-center">Feeding Time</th>
					<th class="text-center">Recurrence</th>
					<th class="text-center">Food</th>
					<th class="text-center">Notes</th>
					<th class="text-center">Subscribers</th>
					<th class="text-center">Details</th>
					<th class="text-center">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fs" items="${feedingSchedules}">
					<tr>
						<td><c:out value="${fs.schedule_ID}" /></td>
						<td><c:out value="${fs.feeding_time}" /></td>
						<td><c:out value="${fs.recurrence}" /></td>
						<td><c:out value="${fs.food}" /></td>
						<td><c:out value="${fs.notes}" /></td>
						<td><select>
						<c:forEach var="animal" items="${fs.getAnimalList()}">
							<option>${animal.name}</option>
							</c:forEach></select></td>
						<td><form action="viewSubscriptions?_ID=${fs.schedule_ID}" method="get">
								<input type="hidden" name="schedule_ID" value="${fs.schedule_ID}">
								<button class="btn btn-sm btn-primary">Details</button>
							</form></td>
						<td><form action="feedingScheduleDelete" method="post">
								<input type="hidden" id="feedingSchedule_ID" name="feedingSchedule_ID" value="${fs.schedule_ID}">
								<button class="btn btn-sm btn-danger">Delete</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		  	</div>
		  	
		  	<!-- Column width 4 on the right side -->
		  	<%-- <div class="col-md-4">
		  	<h3>List of All Animals here</h3>
		  	<!-- my stuff -->
		  	<form action="TagAdd" method="post" class="form-horizontal">
		  	<input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
		  		<div class="form-group">
				    <label for="tag" class="col-sm-4 control-label">Add Tag</label>
				    <div class="row">
				    	<div class="col-sm-5">
				      		<input type="text" step="0.01" class="form-control" id="tag" name="tag" placeholder="enter tag here" />
				    	</div>
				    	<div class="col-sm-1">
				    		<button type="submit" class="btn btn-success">Add</button>
				    	</div>
				    </div>
		  		</div>
		  	</form>
		  <c:forEach var="animal" items="${animals}">
			  <form action="TagDelete" method="post" class="form-horizontal">
				  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${animalid }" />
				  <div class="form-group">
				    <label for="tag" class="col-sm-4 control-label">Tag</label>
					  <div class="row">
					  	<div class="col-sm-5">
			      			<input type="text" step="0.01" class="form-control" id="tag" name="tag"
			      				value="${animal.name}" 
			      				placeholder="tag" readonly />
			    		</div>
			    		<div class="col-sm-1">
			    			<button type="submit" class="btn btn-sm btn-danger">Delete</button>
			    		</div>
					  </div>
				  </div>
			  </form>
		  </c:forEach>
		  	
		  	</div> --%>
		</div>
		
	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
