$('document').ready(function() {
	var myCarousel = document.getElementById('carouselExampleDark');
	let imagesData = myCarousel.getAttribute('images');
	if (imagesData != null && imagesData.length > 0) {
		let jsonImageData = JSON.parse(imagesData);
		let imgTitle = document.getElementById("imgDescriptionTitle");
		let imgDescription = document.getElementById("imgDescription");
		imgTitle.innerHTML = jsonImageData[0]['pageTitle'];
		imgDescription.innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;' + jsonImageData[0]['description'];
	}
	let currentIndex = 0;

	myCarousel.addEventListener('slide.bs.carousel', function(event) {
		let imagesData = myCarousel.getAttribute('images');
		let direction =  event.direction;

		if (imagesData != null && imagesData.length > 0) {
			let jsonImageData = JSON.parse(imagesData);
			
			if (direction == 'left') {
				currentIndex = currentIndex + 1;
			} else {
				if (currentIndex == 0) {
					currentIndex = jsonImageData.length - 1;
				} else {
					currentIndex = currentIndex - 1;
				}
			}
			
			
			let index = currentIndex % jsonImageData.length;
			document.getElementById("imgDescriptionTitle").innerHTML = jsonImageData[index]['pageTitle'];
			document.getElementById("imgDescription").innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;' + jsonImageData[index]['description'];
		}

	})


});