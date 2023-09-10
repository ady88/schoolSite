$('document').ready(function() {

	var myNav = document.getElementById('my-nav');
	let theme = myNav.getAttribute('theme');
	
	let hoverColor = '#111111';
	let noHoverColor = '#111111';
	let hoverForegroundColor = '#222222';
	let linkBackground = '#333333';
	let activeClass = 'none';
	if (theme == 'BLUE') {
		activeClass = 'activeBLUE';
		hoverColor = '#6fcdf2';
		noHoverColor = '#1097cd';
	    hoverForegroundColor = '#e1eff5';
	    linkBackground = '#e1eff5';
	} else if (theme == 'GREEN') {
		activeClass = 'activeGREEN';
		hoverColor = '#6ff2cd';
		noHoverColor = '#10cd97'; 
	    hoverForegroundColor = '#e1f5ef';
	    linkBackground = '#e1f5ef';
	} else if (theme == 'RED') {
		activeClass = 'activeRED';
		hoverColor = '#cd6ff2';
		noHoverColor = '#9710cd'; 
	    hoverForegroundColor = '#f5e1ef';
	    linkBackground = '#f5e1ef';
	}
	
	$(".utilLinks").mouseup(function () {
    	$(this).css('background-color', linkBackground);
	});
	
	$(".utilLinks").mousedown(function () {
		window.open($(this).attr("linkUrl"));
    	$(this).css('background-color', hoverColor);
	});
	
	$(".utilLinks").hover(function () {
    	$(this).css('background-color', hoverColor);
	}, function () {
    	$(this).css('background-color', linkBackground);
	});


	

	$(".social_media").hover(function () {
    		$(this).css('color', hoverForegroundColor);
		}, function () {
    		$(this).css('color', noHoverColor);
		});


	$("a").hover(function () {
    	if ($(this).parent().hasClass(activeClass) == false && $(this).parent().hasClass('card-body') == false && $(this).hasClass('linktest') == false) {
    		$(this).css('background-color', hoverColor);
    		$(this).css('color', hoverForegroundColor);
    	}
	}, function () {
		if ($(this).parent().hasClass(activeClass) == false && $(this).parent().hasClass('col-auto') == false && $(this).parent().hasClass('card-body') == false && $(this).hasClass('linktest') == false) {
    		$(this).css('background-color', noHoverColor);
    		$(this).css('color', hoverForegroundColor);
    	}
	});

	
	
	var myCarousel = document.getElementById('carouselExampleDark');
	if (myCarousel != null) {
	
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
	}

});