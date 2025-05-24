
(function() {
	'use strict'

	var forms = document.querySelectorAll('.needs-validation');

	Array.prototype.slice.call(forms).forEach(function(form) {
		form.addEventListener('submit', async function(event) {
			event.preventDefault(); // Ngăn form submit mặc định

			const password = document.getElementById('password');
			const confirmPassword = document.getElementById('confirmPassword');
			const email = document.getElementById('email');

			// 1. Kiểm tra mật khẩu khớp
			if (password && confirmPassword && password.value !== confirmPassword.value) {
				confirmPassword.setCustomValidity("Mật khẩu không khớp");
			} else {
				confirmPassword.setCustomValidity("");
			}

			// 2. Kiểm tra email qua API
			try {
				const response = await fetch('http://localhost:8080/Assignment_2/api-email');
				const data = await response.json();

				let check = data.find(value => value.email === email.value);
				if (check) {
					email.setCustomValidity('Email đã tồn tại!');
					email.nextElementSibling.textContent = "Email đã tồn tại!";
				} else {
					email.setCustomValidity('');
				}
			} catch (error) {
				console.error("Lỗi khi gọi API email:", error);
				email.setCustomValidity("Không thể kiểm tra email.");
			}

			// 3. Kiểm tra form hợp lệ sau khi đã kiểm tra API
			if (!form.checkValidity()) {
				event.stopPropagation();
			} else {
				form.submit(); // Gửi form nếu hợp lệ
			}

			form.classList.add('was-validated');
		}, false);
	});
})();

