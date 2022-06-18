function upload(event) {
    console.log("ADRIAN 124 ");
    let selectedFile = event.target.files[0];
    console.log(selectedFile);
    var reader = new FileReader();
    reader.onload = function(event) {
      console.log(event.target.result);
      console.log("ADRIAN 125");
      $.ajax({
        type:"post",
        data:event.target.result,
        url:"/admin/updateImage",
        dataType: "text",
        success: function(fragment){
         $("#news_insert_image").replaceWith(fragment); 
        }
      });

//      $.get("admin/updateImage").done(function(fragment) { 
//        $("#news_insert_image").replaceWith(fragment); 
//      });
    };
    reader.readAsDataURL(selectedFile);
}

function selectCurrentNews(title) {
	console.log('ADRIAN 1');
	console.log(title);
	let url = '/admin/news/select/' + title;
	$('#newsPage').load(url);
}
