<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="row pt-4 mb-3">
	<h3 class="mb-3 text-white">Tổng quan</h3>
	<div class="col ">
		<div class="card bg-primary">
			<div class="card-body ">
				<h5 class="card-title text-white">Tổng video</h5>
				<h1 class="card-subtitle mb-2  text-white">${ countVideo }</h1>
			</div>
		</div>
	</div>
	<div class="col">
		<div class="card bg-primary">
			<div class="card-body">
				<h5 class="card-title text-white">Đang hoạt động</h5>
				<h1 class="card-subtitle mb-2 text-white">${ countVideoIsActive }</h1>
			</div>
		</div>
	</div>
	<div class="col">
		<div class="card bg-primary">
			<div class="card-body">
				<h5 class="card-title text-white">Không hoạt động</h5>
				<h1 class="card-subtitle mb-2 text-white">${ countVideoNoActive }</h1>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<h3 class="mb-3 text-white">Video nổi bật</h3>
	<c:forEach items="${ list }" var="video">
		<div class="col">
			<div class="card bg-transparent">
				<img style="max-height: 200px;"
					src="${pageContext.request.contextPath}/assets${ video.poster }"
					class="card-img-top" alt="img">
				<div class="card-body">
					<h5 class="card-title text-truncate text-white">${ video.title }</h5>
					<span class="text-white">Lượt xem ${ video.views } </span>
				</div>
			</div>
		</div>
	</c:forEach>
</div>