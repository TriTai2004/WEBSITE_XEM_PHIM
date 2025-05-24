const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    if (!SpeechRecognition) {
      alert("Trình duyệt không hỗ trợ SpeechRecognition!");
    } else {
      const recognition = new SpeechRecognition();
      recognition.lang = "vi-VN";
      recognition.continuous = false;
      recognition.interimResults = false;
		
      recognition.onresult = function (event) {
        let text = "";
        for (let i = event.resultIndex; i < event.results.length; i++) {
          text += event.results[i][0].transcript;
        }
		document.getElementById("micro").value = '';
        document.getElementById("micro").value += text;
		
      };	
      recognition.onstart = function () {
		Swal.fire({
		          title: 'Đang lắng nghe...',
		          icon: 'info',
		          allowOutsideClick: false,
		          showConfirmButton: false
		        });
      };
      recognition.onend = function () {
       	document.getElementById("search-micro").click();
      };

      window.start = function () {
        recognition.start();
      };
    }