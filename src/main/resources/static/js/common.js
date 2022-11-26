function upload(event) {
	console.log("ADRIAN 124 ");
	let selectedFile = event.target.files[0];
	console.log(selectedFile);
	console.log(selectedFile.name);
	var reader = new FileReader();
	let url = "/admin/updateImage";
	reader.onload = function(event) {
		$.ajax({
			type: "post",
			data: event.target.result,
			url: url,
			dataType: "text",
			success: function(fragment) {
				$("#news_insert_image").replaceWith(fragment);
			}
		});

	};
	reader.readAsDataURL(selectedFile);
}

function uploadImages(event) {
	console.log("ADRIAN 124 ");
	let selectedFile = event.target.files[0];
	console.log(selectedFile);
	console.log(selectedFile.name);
	var reader = new FileReader();
	let url = "/admin/updateMainImage";
	reader.onload = function(event) {
		$.ajax({
			type: "post",
			data: event.target.result,
			url: url,
			dataType: "text",
			success: function(fragment) {
				$("#images_insert_image").replaceWith(fragment);
			}
		});

	};
	reader.readAsDataURL(selectedFile);
}

function uploadResources(event) {
	console.log("ADRIAN 125 ");
	let selectedFile = event.target.files[0];
	console.log(selectedFile);
	console.log(selectedFile.name);
	var reader = new FileReader();
	let url = "/admin/updateResource";
	reader.onload = function(event) {
		$.ajax({
			type: "post",
			data: event.target.result,
			url: url,
			dataType: "text",
			success: function(fragment) {
				$("#resourcesList").replaceWith(fragment);
			}
		});

	};
	reader.readAsDataURL(selectedFile);
}


function selectCurrentNews(title) {
	console.log('ADRIAN 1');
	console.log(title);
	let url = '/admin/news/select/' + title;
	$('#newsPage').load(url);
}

function selectCurrentImages(title) {
	console.log('ADRIAN 1');
	console.log(title);
	let url = '/admin/images/select/' + title;
	$('#imagesPage').load(url);
}

function selectCurrentResources(title) {
	console.log('ADRIAN 1');
	console.log(title);
	let url = '/admin/resources/select/' + title;
	$('#resourcesPage').load(url);
}

function selectCurrentStaff(firstname, lastname) {
	console.log('ADRIAN 1');
	console.log(firstname);
	console.log(lastname);
	let url = '/admin/staff/select/' + firstname + "/" + lastname;
	$('#staffPage').load(url);
}

function removeNews(title) {
	console.log('ADRIAN 2');
	console.log(title);
	let url = '/admin/news/remove/' + title;
	$('#newsList').load(url);
}

function removeImages(title) {
	console.log('ADRIAN 2');
	console.log(title);
	let url = '/admin/images/remove/' + title;
	$('#imagesList').load(url);
}

function removeResources(title) {
	console.log('ADRIAN 2');
	console.log(title);
	let url = '/admin/resources/remove/' + title;
	$('#resourcesList').load(url);
}

function removeStaff(firstname, lastname) {
	console.log(firstname);
	console.log(lastname);
	let url = '/admin/staff/remove/' + firstname + "/" + lastname;
	$('#staffList').load(url);
}

function showMenu() {
	console.log('ADRIAN 22');
	$('#my-nav').toggleClass('show');
}

