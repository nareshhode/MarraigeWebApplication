<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--  <script src="${contextPath}/resources/static/js/jquery-3.3.1.min.js" type="text/javascript"></script> --%>
<!-- CSS for Bootstrap -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- <link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" /> -->
<script  src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="http://jcrop-cdn.tapmodo.com/v0.9.12/js/jquery.Jcrop.min.js"></script>
<link rel="stylesheet" href="http://jcrop-cdn.tapmodo.com/v0.9.12/css/jquery.Jcrop.css" type="text/css" />


 <style>
div.gallery {
	margin: 15px;
	float: left;
	width: 250px;
}
div.gallery img {
	width: 100%;
	height: 25%;
}


* {box-sizing: border-box}
body {font-family: Verdana, sans-serif; margin:0}
.mySlides {display: none}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 500px;
  position: relative;
  margin: auto;
}

/* Next & previous buttons */
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  padding: 16px;
  margin-top: -22px;
  color: white;
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  border-radius: 0 3px 3px 0;
  user-select: none;
}

/* Position the "next button" to the right */
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
}

/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  cursor: pointer;
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active, .dot:hover {
  background-color: #717171;
}

/* Fading animation */
.fade {
 
  animation-name: fade;
  animation-duration: 1.5s;
  opacity :1
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .prev, .next,.text {font-size: 11px}
}
</style>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Upload Images</title>
   
    </head>
    <body>
        <noscript>
            <h2>Sorry! Your browser doesn't support Javascript</h2>
        </noscript>
        <div class="upload-container">
            <div class="upload-header">
                <h2>Upload Images</h2>
            </div>
            <div class="upload-content">
                <div class="single-upload">
                    <h3>Upload Single File</h3>
                    <form id="singleUploadForm" name="singleUploadForm">
                        <input id="singleFileUploadInput" type="file" name="file" class="file-input"  required />
                        <button type="button" id="singleFileUpload_Submit" class="primary submit-btn">Submit</button>
                    </form>
                    <div class="upload-response">
                        <div id="singleFileUploadError"></div>
                        <div id="singleFileUploadSuccess"></div>
                    </div>
                </div>
                  <!-- Bootstrap Progress bar -->
			    <div class="progress">
			      <div id="progressBar" class="progress-bar progress-bar-success" role="progressbar"
			        aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
			    </div>
			
			    <!-- Alert -->
			    <div id="alertMsg" style="color: red;font-size: 18px;"></div>
			    <br>
			    <div id="uploadedImage" >
			   <!--  <img src="#"  style="width: 100%" id="uploadedImageObj" /> -->
			    <div id="views"></div>
			    <button id="cropbutton" type="button">Crop</button>
				  <button id="scalebutton" type="button">Scale</button>
				  <button id="rotatebutton" type="button">Rotate</button>
				  <button id="hflipbutton" type="button">H-flip</button>
				  <button id="vflipbutton" type="button">V-flip</button>
			    <c:set var="myVar1" value="${requestUrl}" />
			    <%
							System.out.println( "::" + pageContext.findAttribute("myVar1"));
				%>
			    </div>
			   <!--  <div id="views"></div> -->
			    <br>
			    <br>
                <!-- <div class="multiple-upload">
                    <h3>Upload Multiple Files</h3>
                    <form id="multipleUploadForm" name="multipleUploadForm">
                        <input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />
                        <button type="submit" class="primary submit-btn">Submit</button>
                    </form>
                    <div class="upload-response">
                        <div id="multipleFileUploadError"></div>
                        <div id="multipleFileUploadSuccess"></div>
                    </div>
                </div> -->
                
                <%
                  int i=0; 
                %>
	                <c:set var="i" value="1"/>
	                <c:set var="lenOfList" value="${fn:length(listProfileImages)}" />
				<div class="slideshow-container">
					<c:forEach var="profileImage" items="${listProfileImages}">
						<c:set var="myVar" value="${profileImage.id}" />
						
						<%
							System.out.println(i++ + "::" + pageContext.findAttribute("myVar"));
						%>
						<%--Old code commented to create gallery --%>
						<%-- 	<div class="gallery">
						<a target="_blank" th:href=""> <img src="getImages/<c:out value="${myVar}"/>"
							height="200" />
						</a>
						<div>
							<form action="/delete/${myVar}" method="POST">
								<table>
									<input type="text" th:value="*{file}" name="text" readonly="true" />
									<input type="hidden" th:name="${_csrf.parameterName}"
										th:value="${_csrf.token}" />
									<td><input type="submit" value="Delete" /></td>
								</table>
							</form>
						</div>
					</div> --%>
						<div class="mySlides fade">
							<div class="numbertext">${i}/${lenOfList}</div>
							<img src="getImages/<c:out value="${myVar}"/>" style="width: 100%" />
							<div class="text">Caption Text</div>
						</div>
	
	                   <c:set var="i" value="${i+1}"/>
					</c:forEach>
					<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a
						class="next" onclick="plusSlides(1)">&#10095;</a>
				</div>
				<br>
				<div style="text-align:center">
				<c:forEach 	var = "j" begin = "1" end = "${lenOfList}"> 
				  <span class="dot" onclick="currentSlide(${j})"></span> 
				 </c:forEach>
				</div>	
			</div>
       <%--  <script  src="${contextPath}/resources/static/js/main.js"></script> --%>
       <script>
			var slideIndex = 1;
			showSlides(slideIndex);
			
			function plusSlides(n) {
			  showSlides(slideIndex += n);
			}
		
			function currentSlide(n) {
			  showSlides(slideIndex = n);
			}
		
			function showSlides(n) {
			  var i;
			  var slides = document.getElementsByClassName("mySlides");
			  var dots = document.getElementsByClassName("dot");
			  if (n > slides.length) {slideIndex = 1}    
			  if (n < 1) {slideIndex = slides.length}
			  for (i = 0; i < slides.length; i++) {
			      slides[i].style.display = "none";  
			  }
			  for (i = 0; i < dots.length; i++) {
			      dots[i].className = dots[i].className.replace(" active", "");
			  }
			  slides[slideIndex-1].style.display = "block";  
			  dots[slideIndex-1].className += " active";
			}
			
				//Start Crop
			    var crop_max_width = 400;
				var crop_max_height = 400;
				var jcrop_api;
				var canvas=null;
				var context;
				var image;
				var prefsize=null;
	
				/* 	$("#file1").change(function() {
					  loadImage(this);
					});
	 			*/
			
	 			$(function() {
	 				
	 				$("#singleFileUploadInput").change(function() {
	 					  loadImage(this);
	 					});

					$("#cropbutton").click(function(e) {
					  applyCrop();
					});
					
					$("#scalebutton").click(function(e) {
					  var scale = prompt("Scale Factor:", "1");
					  applyScale(scale);
					});
					
					$("#rotatebutton").click(function(e) {
					  applyRotate();
					});
					
					$("#hflipbutton").click(function(e) {
					  applyHflip();
					});
					
					$("#vflipbutton").click(function(e) {
					  applyVflip();
					});	
				
					//Start:main.js content
					$('#singleFileUpload_Submit').click(function(event) {
				
						event.preventDefault();
						 //Disable submit button
						$(this).prop('disabled',true);
						    
					    var formElement = document.getElementById("singleUploadForm");
					    // You can directly create form data from the form element
					    // (Or you could get the files from input element and append them to FormData as we did in vanilla javascript)
					    var formData = new FormData(formElement);
					    
					    var blob = dataURLtoBlob(canvas.toDataURL('image/jpg'));
					    alert("blob"+blob)
					    formData.append("cropped_image[]", blob);
			
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

			 
			
			      //image = new Image();
			      //image.onload = validateImage;
			     
			      
	/* 			$('#singleFileUploadInput').on('change', function(e){
				    readFile(this.files[0], function(e) {
				        // use result in callback...
				       // $('#output_field').text(e.target.result);
				    	 $('#uploadedImageObj')
				         .attr('src', e.target.result)
				         .width(150)
				         .height(200)
				         .css("display", "block");
				    	 
				    	 image.src = e.target.result;
				    	 
				    	 $('#uploadedImage').css("display", "block");
				    });
				}); */
			});
				//End: main.js content

			/* 	$("#form").submit(function(e) {
				  e.preventDefault();
				  formData = new FormData($(this)[0]);
				  var blob = dataURLtoBlob(canvas.toDataURL('image/png'));
				  //---Add file blob to the form data
				  formData.append("cropped_image[]", blob);
				  $.ajax({
				    url: "whatever.php",
				    type: "POST",
				    data: formData,
				    contentType: false,
				    cache: false,
				    processData: false,
				    success: function(data) {
				      alert("Success");
				    },
				    error: function(data) {
				      alert("Error");
				    },
				    complete: function(data) {}
				  });
				});
				}); */

				 function loadImage(input) {
				  if (input.files && input.files[0]) {
				    var reader = new FileReader();
				    canvas = null;
				    	
				      readFile(input.files[0], function(e) {
					        // use result in callback...
					       // $('#output_field').text(e.target.result);
				    	  image = new Image();
					      image.onload = validateImage;
					      image.src = e.target.result;
					    });
				   // reader.readAsDataURL(input.files[0]);
				  }
				}
				
				function dataURLtoBlob(dataURL) {
				  var BASE64_MARKER = ';base64,';
				  if (dataURL.indexOf(BASE64_MARKER) == -1) {
				    var parts = dataURL.split(',');
				    var contentType = parts[0].split(':')[1];
				    var raw = decodeURIComponent(parts[1]);

				    return new Blob([raw], {
				      type: contentType
				    });
				  }
				  var parts = dataURL.split(BASE64_MARKER);
				  var contentType = parts[0].split(':')[1];
				  var raw = window.atob(parts[1]);
				  var rawLength = raw.length;
				  var uInt8Array = new Uint8Array(rawLength);
				  for (var i = 0; i < rawLength; ++i) {
				    uInt8Array[i] = raw.charCodeAt(i);
				  }

				  return new Blob([uInt8Array], {
				    type: contentType
				  });
				}

				function validateImage() {
				  if (canvas != null) {
				    image = new Image();
				    image.onload = restartJcrop;
				    image.src = canvas.toDataURL('image/jpg');
				  } else restartJcrop();
				}

				function restartJcrop() {
				  if (jcrop_api != null) {
				    jcrop_api.destroy();
				  }
				  $("#views").empty();
				  $("#views").append("<canvas id=\"canvas\">");
				  canvas = $("#canvas")[0];
				  context = canvas.getContext("2d");
				  canvas.width = image.width;
				  canvas.height = image.height;
				  context.drawImage(image, 0, 0);
				  $("#canvas").Jcrop({
				    onSelect: selectcanvas,
				    onRelease: clearcanvas,
				    boxWidth: crop_max_width,
				    boxHeight: crop_max_height
				  }, function() {
				    jcrop_api = this;
				  });
				  clearcanvas();
				}

				function clearcanvas() {
				  prefsize = {
				    x: 0,
				    y: 0,
				    w: canvas.width,
				    h: canvas.height,
				  };
				}

				function selectcanvas(coords) {
				  prefsize = {
				    x: Math.round(coords.x),
				    y: Math.round(coords.y),
				    w: Math.round(coords.w),
				    h: Math.round(coords.h)
				  };
				}

				function applyCrop() {
				
				 if(prefsize ==null){	
					 prefsize = {
							    x: 0,
							    y: 0
							   /*  w: canvas.width,
							    h: canvas.height, */
							  };
				 }
				  canvas.width = prefsize.w;
				  canvas.height = prefsize.h;
				  context.drawImage(image, prefsize.x, prefsize.y, prefsize.w, prefsize.h, 0, 0, canvas.width, canvas.height);
				  validateImage();
				}

				function applyScale(scale) {
				  if (scale == 1) return;
				  canvas.width = canvas.width * scale;
				  canvas.height = canvas.height * scale;
				  context.drawImage(image, 0, 0, canvas.width, canvas.height);
				  validateImage();
				}

				 function applyRotate() {
				  canvas.width = image.height;
				  canvas.height = image.width;
				  context.clearRect(0, 0, canvas.width, canvas.height);
				  context.translate((image.height)/2,(image.width)/2);
				  context.rotate(Math.PI / 2);
				  context.drawImage(image, -image.width/2, -image.height/2);
				  validateImage();
				}  

				function applyHflip() {
				  context.clearRect(0, 0, canvas.width, canvas.height);
				  context.translate(image.width, 0);
				  context.scale(-1, 1);
				  context.drawImage(image, 0, 0);
				  validateImage();
				}

				function applyVflip() {
				  context.clearRect(0, 0, canvas.width, canvas.height);
				  context.translate(0, image.height);
				  context.scale(1, -1);
				  context.drawImage(image, 0, 0);
				  validateImage();
				}
		//End Crop
				 function readFile(file, onLoadCallback){
				    var reader = new FileReader();
				    reader.onload = onLoadCallback;
				    reader.readAsDataURL(file);
				} 
		
</script>
     </body>
</html>