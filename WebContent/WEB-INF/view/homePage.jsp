<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/WEB-INF/view/layout/header.jsp"%>


<div class="container">

	<div class="jumbotron">
		<h1>Hello, world!</h1>
		<p>Mirali Fikretzade</p>
		<p>
			<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/AddPilot" role="button">Pilot Elave Et</a>
		</p>
	</div>

</div>

<%@include file="/WEB-INF/view/layout/footer.jsp"%>