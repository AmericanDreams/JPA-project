<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/WEB-INF/view/layout/header.jsp"%>


<div class="container">

<p class="bg-danger">${exception}</p>

<p class="bg-success">${succsess }</p>

	<div class="row">
		<div class="col-lg-12">
			<a href="<%=request.getContextPath()%>/AddPilot"
				class="btn btn-default">New Pilot</a>
		</div>
	</div>
	<hr>
	<table class="table table-striped">
		<tr>
			<th>S/s</th>
			<th>Ad</th>
			<th>Soyad</th>
			<th>Təvəllüd</th>
			<th>Rütbə</th>
			<th></th>
		</tr>
		<c:forEach items="${pilots }" var="pilot" varStatus="status">
			<tr>
				<td>${status.count }</td>
				<td>${pilot.name }</td>
				<td>${pilot.surname }</td>
				<td><fmt:formatDate value="${pilot.dob}" pattern="dd-MM-yyyy" />
				</td>
				<td>${pilot.rank }</td>
				<td>

					<div class="dropdown">
						<span class="dropdown-toggle" 
							id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="true" style="cursor: pointer;">
								Redakə
						</span>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a href="<%=request.getContextPath()%>/EditPilot?id=${pilot.id}">Redaktə Et</a></li>
							<li><a href="#">Uçuşa təhkim Et</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<%=request.getContextPath()%>/DeletePilot?id=${pilot.id}">Sil</a></li>
						</ul>
					</div>

				</td>

			</tr>
		</c:forEach>

	</table>

</div>

<%@include file="/WEB-INF/view/layout/footer.jsp"%>