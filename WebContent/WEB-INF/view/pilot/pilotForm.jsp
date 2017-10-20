<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/view/layout/header.jsp"%>

<div class="container">

		<c:if test="${hasError == true}">
				<p class="bg-danger">Form düzgün doldurulmamışdır</p>
		</c:if>
		
		
		<p class="bg-danger">${exception}</p>
		
	
	
		<form method="post" action="<%=request.getContextPath() %>/AddPilot">

			<div class="form-group ${ (nameError!= null) ? 'has-error': '' }">
				<label for="exampleInputEmail1">${addPilot_label_name }</label> <input type="text"
					class="form-control " id="name" name="name" placeholder="Name" value='<c:out value='${name }'/>'>
					<small  class="text-danger">
          					${nameError }
        			</small> 
			</div>
			<div class="form-group ${ (surnameError!= null) ? 'has-error': '' }">
				<label for="exampleInputEmail1">${addPilot_label_surname }</label> <input type="text"
					class="form-control" id="surname" name="surname"
					placeholder="Surname" value="<c:out value='${surname }'/>">
					<small  class="text-danger">
          					${surnameError }
        			</small> 
			</div>
			<div class="form-group ${ (dobError!= null) ? 'has-error': '' }">
				<label for="exampleInputEmail1">${addPilot_label_dob }</label> <input
					type="text" class="form-control" id="dob" name="dob"
					placeholder="Date of Birthdate" value="<c:out value='${dob }'/>">
					<small  class="text-danger" >
          					${dobError }
        			</small> 
			</div>
			<div class="form-group ${ (pilotRankError!= null) ? 'has-error': '' }">
				<label for="exampleInputEmail1">${addPilot_label_pilotRank }</label> <select
					class="form-control" name="pilotRank">
					<c:forEach items="${pilotRanks}" var="rank">
							<option value="${rank}">${rank}</option>
					</c:forEach>			
				</select>
				<small  class="text-danger">
          					${pilotRankError }
        			</small> 
			</div>


			<button type="submit" class="btn btn-default">Insert new Pilot</button>

		</form>
	</div>
<%@include file="/WEB-INF/view/layout/footer.jsp"%>
