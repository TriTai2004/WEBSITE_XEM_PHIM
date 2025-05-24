/**
 * 
 */


const listLike = document.querySelectorAll(".like");
const url = "http://localhost:8080/Assignment_2/api-like";
const showLike = () =>{
	fetch(url, {
		method: "GET"
	}).then(resp => resp.json()).then(data =>{
		if(data.status){
			console.log(data.status)
		}else{
				listLike.forEach(item => {
					const id = item.id.slice(5);
					const matched = data.find(itemm => itemm.video.id == id);
					if (matched) {
						item.classList.add("text-danger");
					}
				});
			
		}
		
	})

}
showLike();


const like = (userId, videoId) =>{
	if(userId == 0){
		 window.location.href = 'http://localhost:8080/Assignment_2/dang-nhap';
		return;
	}
	const data = {"userId":userId,"videoId":videoId};
	const itemLike = document.querySelector(`#like-${videoId}`);
	if(itemLike.className.includes('text-danger')){
		const option = { method: "DELETE", body: JSON.stringify(data) };
		fetch(url, option).then(resp => resp.json()).then(json => {
			if (json.status == "ok") {
				itemLike.classList.remove('text-danger');
				if(json.url.includes('yeu-thich')){
					window.location.href = json.url;
				}
				
			}
		})
	}else{
		const option = { method: "POST", body: JSON.stringify(data) };
		fetch(url, option).then(resp => resp.json()).then(json => {
			if (json.status == "ok") {
				itemLike.classList.add('text-danger');
			}
		})
	}
	
}

// Example starter JavaScript for disabling form submissions if there are invalid fields

