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
	
		<h1>eZOO <small>Feeding Schedule update & subscription management</small></h1>
		<hr class="paw-primary">
		
		<div class="row">
		  	<div class="col-md-4">
		  		<h3>Feeding Schedule</h3>
		
				<form action="feedingScheduleUpdate" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <label for="id" class="col-sm-3 control-label">ID</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" id="id" name="id" placeholder="ID" required="required"
		      	value="${feedingSchedule.schedule_ID }" readonly />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="feeding_time" class="col-sm-3 control-label">Feeding Time</label>
		    <div class="col-sm-9">
					<select name="feeding_time" id="feeding_time" class="form-control" >
						<option value="${feedingSchedule.feeding_time}">${feedingSchedule.feeding_time}</option>
						<option value="Morning">
							Morning
						</option>
						<option value="Noon">
							Noon
						</option>
						<option value="Evening">
							Evening
						</option>
						<option value="Twilight">
							Twilight
						</option>
					</select>
				</div>
			</div>	
		  <div class="form-group">
		    <label for="recurrence" class="col-sm-3 control-label">Recurrence</label>
		    <div class="col-sm-9">
					<select name="recurrence" id="recurrence" class="form-control">
						<option value="${feedingSchedule.recurrence}">${feedingSchedule.recurrence}</option>
						<option value="4 Hours">
							Every 4 Hours
						</option>
						<option value="8 Hours">
							Every 8 Hours
						</option>
						<option value="Daily">
							Daily
						</option>
					</select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="food" class="col-sm-3 control-label">Food</label>
		    <div class="col-sm-9">
		      <input type="text" step="0.01" class="form-control" id="food" name="food" placeholder="Food" 
		      	value="${feedingSchedule.food }"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="notes" class="col-sm-3 control-label">Notes</label>
		    <div class="col-sm-9">
		      <textarea rows="4" cols="20" class="form-control" id="notes" name="notes" placeholder="Notes: 80 Characters" >${feedingSchedule.notes}</textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="">
		      <button type="submit" class="btn btn-info">Update</button>
		    </div>
		  </div>
		</form>
		  	</div>
		  	
		  	<!-- SUBSCRIBED ANIMALS  MIDDLE SECTION -->
		  	<div class="col-md-4">
		  		<h3>Subscribed Animals</h3>
		  <c:forEach var="animal" items="${animalList}">
			  <form action="subscriptionDelete" method="get" class="form-horizontal">
				  <input type="hidden" id="animalID" name="animalID" value="${animal.animalID}" />
				  <input type="hidden" id="feedingSchedule_ID" name="feedingSchedule_ID" value="${feedingSchedule.schedule_ID}" />
				  <div class="form-group">
				    <label for="tag" class="col-sm-4 control-label">Subscriber</label>
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
		  	</div>
		  	
		  	
		  	<!-- ANIMAL TO ADD  RIGHT SIDE SECTION -->
		  	
		  	<div class="col-md-4">
		  	<h3>Animal To Subscribe</h3>
		  	<c:forEach var="a" items="${allAnimals}">
		  	<form action="subscriptionAdd" method="get" class="form-horizontal">
		  	
		  		<input type="hidden" id="animalID" name="animalID" value="${a.animalID}" />
				<input type="hidden" id="feedingSchedule_ID" name="feedingSchedule_ID" value="${feedingSchedule.schedule_ID}" />
		  		<div class="form-group">
				    <label for="tag" class="col-sm-4 control-label">Add Animal</label>
				    <div class="row">
				    	<div class="col-sm-5">
				      		<%-- <select class="form-control">
				      			<option><c:out value="${a.name}"></c:out></option>
				      		</select> --%>
				      		<input type="text" class="form-control" id="animal" name="animal" value="${a.name}" readonly />
				    	</div>
				    	<div class="col-sm-1">
				    		<button type="submit" class="btn btn-success">Add</button>
				    	</div>
				    </div>
		  		</div>
		  	</form>
		  	</c:forEach>
		  	
		  	<!-- FAILED ATTEMPT DROPDOWN MENU FOR SUBSCRIBE ADD -->
		  	<%-- <form action="subscriptionAdd" method="post" class="form-horizontal">
		  	
		  		<input type="hidden" id="animal" name="animal" value="${a.animalID}" />
				<input type="hidden" id="feedingSchedule" name="feedingSchedule" value="${feedingSchedule}" />
		  		<div class="form-group">
				    <label for="tag" class="col-sm-4 control-label">Add Animal</label>
				    <div class="row">
				    	<div class="col-sm-5">
				      		<input type="hidden" id="id" name="id" required="required" value="${a.amimalID}" />
				      		<select class="form-control">
				      		<c:forEach var="a" items="${allAnimals}">
				      			<option><c:out value="${a.name}"></c:out></option>
				      			<option><c:out value="${a.name}"/></option>
				      		</c:forEach>
				      		</select>
				      		
				      		
				    	</div>
				    	<div class="col-sm-1">
				    		<button type="submit" class="btn btn-success">Add</button>
				    	</div>
				    </div>
		  		</div>
		  	</form> --%>
		  	
		  	
		  	
		  	</div>	
		</div>
		
	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
