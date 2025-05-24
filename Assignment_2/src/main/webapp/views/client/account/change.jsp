<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="/Assignment_2/doi-mat-khau" method="post" class="needs-validation" novalidate>
	<input type="hidden" value="${ user.email }" name="email">
	<div class="mb-3">
		<label for="email" class="form-label">Email</label> <input type="text"
			class="form-control" value="${ user.email }" disabled>
			
			
	</div>
	<div class="mb-3">
		<label for="passwordOld" class="form-label">Mật khẩu cũ </label> <input
			type="password" id="passwordOld" name="passwordOld" class="form-control" required>
			 <div class="invalid-feedback">Mật khẩu cũ không được bỏ trống!</div>
			 <small class="text-danger">${ messagePassOld }</small>
	</div>
	<div class="mb-3">
		<label for="passwordNew" class="form-label">Mât khẩu mới</label> <input
			type="password" id="passwordNew" name="passwordNew" class="form-control" required>
			 <div class="invalid-feedback">Mật khẩu mới không được bỏ trống!</div>
	</div>
	<div class="mb-3">
		<label for="confirmPass" class="form-label">Xác nhận mật khẩu</label> <input
			type="password" id="confirmPass" name="confirmPass" class="form-control" required>
			 <div class="invalid-feedback">Xác nhận mật khẩu không được bỏ trống!</div>
			 <small class="text-danger">${ messageConfirm }</small>
	</div>
	<button>Lưu thông tin</button>
</form>