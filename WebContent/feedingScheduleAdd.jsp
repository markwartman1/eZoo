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
	
		<h1>eZoo <small>Add Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<form action="feedingScheduleAdd" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <label for="id" class="col-sm-4 control-label">ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="id" name="id" placeholder="ID" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="feeding_time" class="col-sm-4 control-label">Feeding Time</label>
		    <div class="col-sm-4">
					<select required="required" name="feeding_time" class="form-control">
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
		    <label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
		    <div class="col-sm-4">
					<select required="required" name="recurrence" class="form-control">
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
		    <label for="food" class="col-sm-4 control-label">Food</label>
		    <div class="col-sm-4">
		      <input type="text" step="0.01" class="form-control" id="food" name="food" placeholder="Food" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-4">
		      <textarea rows="4" cols="20" class="form-control" id="notes" name="notes" placeholder="Notes: 80 Characters" required="required"></textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Add</button>
		    </div>
		  </div>
		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />