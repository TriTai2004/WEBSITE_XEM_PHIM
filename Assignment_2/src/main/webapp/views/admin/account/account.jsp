<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="row pt-4 mb-2">
	<form action="/Assignment_2/admin/tai-khoan" class="d-flex my-2 my-lg-0 w-25 position-relative">
		<input
			class="form-control me-sm-2 form-control-sm pe-5 ps-4 rounded-3"
			type="search" value="${ search }" name="search" placeholder="Tìm kiếm" />
		<button
			class="border-0 bg-transparent me-5 position-absolute end-0 top-50 translate-middle-y"
			type="submit">
			<i class="bi bi-search"></i>
		</button>
	</form>
</div>
<div class="table-responsive">
	<table class="table table-hover table-dark border-primary">
		<thead>
			<tr>
				<th scope="col">Stt</th>
				<th scope="col">Email</th>
				<th scope="col">Họ tên</th>
				<th scope="col" class="text-end">Hành động</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ list }" var="user" varStatus="index">
				<tr>
					<td scope="row">${ (pageNumber - 1) * 7 + index.index +1 }</td>
					<td>${ user.email }</td>
					<td>${ user.fullName }</td>
					<td class="text-end">
						<!-- <form action=""> --> <input type="hidden" name="id">
						<button " class="bg-transparent border-0" onClick="active(${ user.id }, ${ user.active })">
							<label class="toggle-switch me-3"> <input  type="checkbox"
								${ user.active ? 'checked' : '' } id="switch" value="${ user.active ? true : false }"> <span class="slider"></span>
							</label>
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-end">
			<li class="page-item"><a class="page-link" href="?page=${ pageNumber == 1 ? 1 : pageNumber - 1 }&search=${ search }"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<c:forEach var="page" begin="1" end="${ pagination }">
				<li class="page-item ${ page == pageNumber ? 'active':'' }"><a class="page-link" href="?page=${ page }&search=${ search }">${ page }</a></li>
			</c:forEach>
			<li class="page-item"><a class="page-link" href="?page=${ pageNumber == pagination ? pagination : pageNumber + 1 }&search=${ search }"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>
</div>
