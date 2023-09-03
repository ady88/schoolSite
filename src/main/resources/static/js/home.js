$('document').ready(function() {

	
	$(".utilLinks").mouseup(function () {
    	$(this).css('background-color', 'floralwhite');
	});
	
	$(".utilLinks").mousedown(function () {
		window.open($(this).attr("linkUrl"));
    	$(this).css('background-color', 'burlywood');
	});
	
	$(".utilLinks").hover(function () {
    	$(this).css('background-color', 'burlywood');
	}, function () {
    	$(this).css('background-color', 'floralwhite');
	});

	
	
	var myCarousel = document.getElementById('carouselExampleDark');
	let imagesData = myCarousel.getAttribute('images');
	if (imagesData != null && imagesData.length > 0) {
		let jsonImageData = JSON.parse(imagesData);
		let imgTitle = document.getElementById("imgDescriptionTitle");
		let imgDescription = document.getElementById("imgDescription");
		imgTitle.innerHTML = jsonImageData[0]['title'];
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
			document.getElementById("imgDescriptionTitle").innerHTML = jsonImageData[index]['title'];
			document.getElementById("imgDescription").innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;' + jsonImageData[index]['description'];
		}

	})


});