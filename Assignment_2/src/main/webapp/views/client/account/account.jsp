<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                    <form action="/Assignment_2/tai-khoan" method="post" class="needs-validation" novalidate>
                    <input type="hidden" name="id" value="${ user.id }">
                        <div class="mb-3">
                            <label for="" class="form-label">Email</label>
                            <input type="text" value="${ user.email }" class="form-control" disabled="disabled">
                        </div>
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Họ tên</label>
                            <input type="text" id="fullName" name = "fullName" value="${ user.fullName }" class="form-control" required>
                             <div class="invalid-feedback">Họ tên không được bỏ trống!</div>
                        </div>
                        <button>Lưu thông tin</button>
                    </form>