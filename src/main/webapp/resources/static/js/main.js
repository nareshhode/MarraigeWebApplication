$(function() {
$('#singleFileUpload_Submit').click(function(event) {
	
	event.preventDefault();
	 //Disable submit button
	$(this).prop('disabled',true);
	    
    var formElement = document.getElementById("singleUploadForm");
    // You can directly create form data from the form element
    // (Or you could get the files from input element and append them to FormData as we did in vanilla javascript)
    var formData = new FormData(formElement);

    var ajaxReq =$.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/uploadFile",
        data: formData,
        processData: false,
        cache : false,
        contentType: false,
      /*  success: function (response) {
            console.log(response);
            alert("Image have been uploaded successfully")
            // process response
        },
        error: function (error) {
        	alert("Hi"+error);
            console.log(error);
            // process error
        }*/
        xhr: function(){
            //Get XmlHttpRequest object
             var xhr = $.ajaxSettings.xhr() ;
            
            //Set onprogress event handler 
             xhr.upload.onprogress = function(event){
              	var perc = Math.round((event.loaded / event.total) * 100);
              	$('#progressBar').text(perc + '%');
              	$('#progressBar').css('width',perc + '%');
              	
             };
             return xhr ;
        	},
        	beforeSend: function( xhr ) {
        		//Reset alert message and progress bar
        		$('#alertMsg').text('');
        		$('#progressBar').text('');
        		$('#progressBar').css('width','0%');
        		
                  }
       
    });

    // Called on success of file upload
    ajaxReq.done(function(msg) {
      $('#alertMsg').text("File Uploaded successfully");
      $('input[type=file]').val('');
      $('#singleFileUpload_Submit').prop('disabled',false);
      $('#uploadedImage').css("display", "block");
      readURL();
    });
    
    // Called on failure of file upload
    ajaxReq.fail(function(jqXHR) {
      $('#alertMsg').text(jqXHR.responseText+'('+jqXHR.status+
      		' - '+jqXHR.statusText+')');
      $('#singleFileUpload_Submit').prop('disabled',false);
      $('#uploadedImage').css("display", "none");
    });
  });

	$('#singleFileUploadInput').on('change', function(e){
	    readFile(this.files[0], function(e) {
	        // use result in callback...
	       // $('#output_field').text(e.target.result);
	    	 $('#uploadedImageObj')
	         .attr('src', e.target.result)
	         .width(150)
	         .height(200)
	         .css("display", "block");
	    	 
	    	 $('#uploadedImage').css("display", "block");
	    });
	});
});

/*function readURL(input1) {

    if (input1.files && input1.files[0]) {
        var reader = new FileReader();
        
        reader.readAsText(input1.files[0]);
        
        
        reader.onloadend = function () {
        	  console.log('DONE', reader.readyState); // readyState will be 2
       };
        
        reader.onload = function (e) {
            $('#uploadedImageObj')
                .attr('src', e.target.result)
                .width(150)
                .height(200)
                .css("display", "block");;
        };

        reader.readAsDataURL(input1.files[0]);
    }
}
*/





function readFile(file, onLoadCallback){
    var reader = new FileReader();
    reader.onload = onLoadCallback;
    reader.readAsDataURL(file);
}