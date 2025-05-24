<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row pt-4">
	<div class="col">
		<form action="${pageContext.request.contextPath}/admin/video/edit" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
			<input type="hidden" value="${ video.id }" name="id">
			<div class="row">
				<div class="col">
					<div class="mb-3">
						<label for="title" class="form-label text-white">Tiêu đề</label> <input
							type="text" name="title" id="title" class="form-control text-white bg-transparent"
							value="${ video.title }"
							placeholder="" required />
						<div class="invalid-feedback">Tiêu đề không được bỏ trống!</div>
					</div>
					<div class="mb-3">
						<label for="url_video" class="form-label text-white">Url video</label> <input
							type="text" name="urlVideo" id="url_video" class="form-control text-white bg-transparent"
							value="${ video.urlVideo }"
							placeholder="" required />
						<div class="invalid-feedback">Url video không dược bỏ trống!</div>
					</div>
				</div>
				<div class="col">
					<div class="mb-3">
						<label for="posster" class="form-label text-white">Ảnh</label> <input
							type="file" name="poster" id="posster" class="form-control text-white bg-transparent"	
							placeholder="" ${ video.id == null ? 'required':''} />
						<div class="invalid-feedback">Ảnh không được bỏ trống!</div>
					</div>
					<div class="mb-3">
						<label for="" class="form-label text-white">Trạng thái</label>
						<div class="d-flex">
							<div class="form-check me-3">
								<input class="form-check-input text-white"  type="radio"
									checked
									name="active"
									id="true" value="true" /> <label class="form-check-label text-white"
									for="true"> Hoạt động </label>
							</div>
							<div class="form-check">
								<input class="form-check-input text-white" type="radio" 
									${ !video.active ? 'checked' : '' }
									name="active"
									id="false" value="false" /> <label class="form-check-label text-white"
									for="false"> Ngừng hoạt động </label>
							</div>
						</div>
					</div>
				</div>
				<div class="mb-3">
					<label for="description" class="form-label text-white">Mô tả</label>
					<textarea rows="11" class="form-control text-white bg-transparent" required
						name="description" id="description">${ video.description }</textarea>
					<div class="invalid-feedback">Mô tả không được bỏ trống!</div>
				</div>
			</div>
			<div class="d-flex justify-content-end">
				<a href="${pageContext.request.contextPath}/admin/video" class="btn btn-success me-2">Quay
					lại</a>
				<button class="btn btn-primary">Lưu</button>
			</div>
		</form>

	</div>
</div>