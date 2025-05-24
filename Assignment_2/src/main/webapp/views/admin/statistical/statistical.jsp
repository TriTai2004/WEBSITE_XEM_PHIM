<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row pt-4 mb-2">
	<div class="col-10">
		<form class="d-flex my-2 my-lg-0 w-25 position-relative">
			<input
				class="form-control me-sm-2 form-control-sm pe-5 ps-4 rounded-3"
				type="search" placeholder="Tìm kiếm" />
			<button
				class="border-0 bg-transparent me-4 position-absolute end-0 top-50 translate-middle-y"
				type="submit">
				<i class="bi bi-search"></i>
			</button>
		</form>
	</div>
</div>

<div class="table-responsive">
	<table class="table table-hover text-center">
		<thead>
			<tr>
				<th scope="col">Stt</th>
				<th scope="col">Tiêu đề</th>
				<th scope="col">Lượt xem</th>
				<th scope="col">Lượt thích</th>
				<th scope="col">Trạng thái</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td scope="row">1</td>
				<td>R1C2</td>
				<td>R1C3</td>
				<td>R1C3</td>
				<td>r</td>
			</tr>
			<tr>
				<td scope="row">1</td>
				<td>R1C2</td>
				<td>R1C3</td>
				<td>R1C3</td>
				<td>r</td>
			</tr>
			<tr>
				<td scope="row">1</td>
				<td>R1C2</td>
				<td>R1C3</td>
				<td>R1C3</td>
				<td>r</td>
			</tr>
			<tr>
				<td scope="row">1</td>
				<td>R1C2</td>
				<td>R1C3</td>
				<td>R1C3</td>
				<td>r</td>
			</tr>
			<tr>
				<td scope="row">1</td>
				<td>R1C2</td>
				<td>R1C3</td>
				<td>R1C3</td>
				<td>r</td>
			</tr>
			<tr>
				<td scope="row">1</td>
				<td>R1C2</td>
				<td>R1C3</td>
				<td>R1C3</td>
				<td>r</td>
			</tr>
			<tr>
				<td scope="row">1</td>
				<td>R1C2</td>
				<td>R1C3</td>
				<td>R1C3</td>
				<td>r</td>
			</tr>
		</tbody>
	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-end">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">...</a></li>
			<li class="page-item"><a class="page-link" href="#">7</a></li>
			<li class="page-item"><a class="page-link" href="#">8</a></li>
			<li class="page-item"><a class="page-link" href="#"
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
				<h5>Bạn chắc chắn muốn xóa video ?</h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Quay lại</button>
				<form>
					<input type="hidden">
					<button type="submit" class="btn btn-danger">Xóa</button>
				</form>
			</div>
		</div>
	</div>
</div>