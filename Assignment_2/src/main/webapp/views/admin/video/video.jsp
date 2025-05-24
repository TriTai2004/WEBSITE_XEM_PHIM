<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="row pt-4 mb-2">
	<div class="col-10">
		<form action="${pageContext.request.contextPath}/admin/video" method="get" class="d-flex my-2 my-lg-0 w-25 position-relative">
			<input
				class="form-control me-sm-2 form-control-sm pe-5 ps-4 rounded-3 "
				name="tim-kiem"
				value="${ search }"
				type="search" placeholder="Tìm kiếm" />
			<button
				class="border-0 bg-transparent me-4 position-absolute end-0 top-50 translate-middle-y"
				type="submit">
				<i class="bi bi-search"></i>
			</button>
		</form>
	</div>
	<div class="col text-end">
		<a href="${pageContext.request.contextPath}/admin/video/edit" class="btn btn-primary">Thêm mới
			video</a>
	</div>
</div>

<div class="table-responsive">
	<table class="table table-hover table-dark border-primary">
		<thead>
			<tr>
				<th scope="col">Stt</th>
				<th scope="col">Tiêu đề</th>
				<th scope="col">Lượt xem</th>
				<th scope="col">Lượt thích</th>
				<th scope="col">Lượt chia sẻ</th>
				<th scope="col">Trạng thái</th>
				<th scope="col" class="text-end">Hành động</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ list }" var="video" varStatus="stt">
				<tr>
					<td scope="row">${ (trang - 1) * 7 + stt.count }</td>
					<td>${ video.title }</td>
					<td>${ video.views }</td>
					<td>${video.likes != null ? video.likes.size() : 0}</td>
					<td>${video.shares != null ? video.shares.size() : 0}</td>
					<td>${ video.active ? 'Hoạt động' : 'Dừng hoạt động' }</td>
					<td class="text-end">
						<a href="${pageContext.request.contextPath}/admin/video/edit?id=${video.id}"
						class="btn btn-outline-success btn-sm">Sửa</a>
						<a href="/Assignment/admin/video?id=${ video.id }"
						class="btn btn-outline-danger btn-sm" data-bs-toggle="modal"
						onclick="document.getElementById('videoIdToDelete').value='${video.id}'"
						data-bs-target="#videoDelete">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-end">
			<li  class="page-item"><a href="${pageContext.request.contextPath}/admin/video?tim-kiem=${search}&trang=${trang == 1 ? 1 : trang - 1}" class="page-link" "
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			
			<c:forEach var="page" begin="1" end="${ pagination }">
				<li class="page-item ${ trang == page ? 'active':'' }"><a href="${pageContext.request.contextPath}/admin/video?tim-kiem=${search}&trang=${page}" class="page-link">${ page }</a></li>
			</c:forEach>
			<li class="page-item"><a href="${pageContext.request.contextPath}/admin/video?tim-kiem=${search}&trang=${trang >= pagination ? pagination : trang + 1}" class="page-link"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>
</div>

<!-- Modal -->
<div class="modal fade" id="videoDelete" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Xóa video</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<h5>Bạn chắc chắn muốn xóa video?</h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Quay lại</button>
				<form action="${pageContext.request.contextPath}/admin/xoa" method="post">
					<input type="hidden" name="id" id="videoIdToDelete">
					<button type="submit" class="btn btn-danger">Xóa</button>
				</form>
			</div>
		</div>
	</div>
</div>