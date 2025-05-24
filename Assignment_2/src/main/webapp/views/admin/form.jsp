<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <!-- Bootstrap CSS v5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
    <style>
            .toggle-switch {
                position: relative;
                width: 50px;
                height: 25px;
                cursor: pointer;
            }
            .toggle-switch input {
                opacity: 0;
                width: 0;
                height: 0;
            }
            .slider {
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                border-radius: 25px;
                transition: .4s;
            }
            .slider:before {
                content: "";
                position: absolute;
                height: 18px;
                width: 18px;
                left: 4px;
                bottom: 3.5px;
                background-color: white;
                border-radius: 50%;
                transition: .4s;
            }
            input:checked + .slider {
                background-color: #1E88E5;
            }
            input:checked + .slider:before {
                transform: translateX(24px);
            }
            body{
            	background: #100028;
            }


        </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-1">
                <nav class="nav sticky-top text-center flex-column pt-3 ">
                    <a href="${pageContext.request.contextPath}/trang-chu" class="py-2 text-white mb-4">
						<img style="width: 116px;" alt="" src="http://localhost:8080/Assignment_2/assets/img/logo.png">
					</a>
                    <a class="nav-link  d-flex flex-column fs-5 ${ uri.contains('trang-chu') ? 'text-primary':'text-white' }" aria-current="page" href="${pageContext.request.contextPath}/admin/trang-chu">
                        <i class="bi bi-house-door"></i>
                        <span style="font-size: 10px;">Trang chủ</span>
                    </a>
                    <a class="nav-link  d-flex flex-column fs-5 ${ uri.contains('video') ? 'text-primary':'text-white' }"" aria-current="page"
                        href="${pageContext.request.contextPath}/admin/video">
                        <i class="bi bi-camera-video"></i>
                        <span style="font-size: 10px;">Video</span>
                    </a>
                    <a class="nav-link  d-flex flex-column fs-5 ${ uri.contains('tai-khoan') ? 'text-primary':'text-white' }"" aria-current="page"
                        href="${pageContext.request.contextPath}/admin/tai-khoan">
                        <i class="bi bi-person-vcard"></i>
                        <span style="font-size: 10px;">Tài khoản</span>
                    </a>
             
                </nav>
            </div>
            <div  class="col-11">
                <nav style="background: #100028 !important; color: #fff;"  class="navbar navbar-expand sticky-top bg-white py-4">
                    <div  class="container-fluid d-flex justify-content-end">
                        
                        <div class="account d-flex">
                            <div class="bell me-2">
                                <i class="bi bi-bell"></i>
                            </div>
                            <div class="user me-2">
                                <a class="me-4 text-dark text-white" href="/Assignment_2/trang-chu"><i class="bi bi-house-door"></i></a>
                            </div>
                        </div>
                    </div>
                </nav>
                <main class="container">
                    <jsp:include page="${ page }"></jsp:include>
                </main>
            </div>
        </div>
    </div>
    <footer>
        <!-- place footer here -->
    </footer>
    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
        <script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
	<script>
		const active = (userId, status) =>{
			console.log("do day roi")
			const data = {"userId": userId, "status": status};
			fetch("http://localhost:8080/Assignment_2/api-user",
					{
						method: "PUT",
						headers: {"Content-Type": "application/json"},
						body: JSON.stringify(data)}).then(res => res.json()).then(json => console.log(json.status))
		}
	
	</script>
</body>