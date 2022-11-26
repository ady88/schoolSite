$('document').ready(function() {
	var myCarousel = document.getElementById('carouselExampleDark');
	let imagesData = myCarousel.getAttribute('images');
	console.log(imagesData);
	if (imagesData != null && imagesData.length > 0) {
		console.log(imagesData[0]);
		console.log(JSON.parse(imagesData));
		let jsonImageData = JSON.parse(imagesData);
		console.log(jsonImageData[0]['pageTitle']);
		let imgTitle = document.getElementById("imgDescriptionTitle");
		let imgDescription = document.getElementById("imgDescription");
		imgTitle.innerHTML = jsonImageData[0]['pageTitle'];
		imgDescription.innerHTML = jsonImageData[0]['description'];
	}
	let currentIndex = 0;

	myCarousel.addEventListener('slide.bs.carousel', function() {
		let imagesData = myCarousel.getAttribute('images');

		if (imagesData != null && imagesData.length > 0) {
			console.log(imagesData[0]);
			console.log(JSON.parse(imagesData));
			let jsonImageData = JSON.parse(imagesData);
			currentIndex = currentIndex + 1;
			let index = currentIndex % jsonImageData.length;
			console.log(index);
			console.log(jsonImageData[index]['pageTitle']);
			document.getElementById("imgDescriptionTitle").innerHTML = jsonImageData[index]['pageTitle'];
			document.getElementById("imgDescription").innerHTML = jsonImageData[index]['description'];
		}

	})


});