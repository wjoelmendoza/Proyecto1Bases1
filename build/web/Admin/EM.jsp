<%-- 
    Document   : newjsp
    Created on : 22/09/2016, 03:47:42 AM
    Author     : byron
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>The Story - Free Bootstrap Theme</title>
        <%@include file="../WEB-INF/jspf/headera.jspf" %>
        <script src="http://code.jquery.com/jquery-latest.js">

        </script>
  
 <script>
            $(document).ready(function() {
		$('#btnconfederas').click(function(event) {
			
                        var fl = 'lconfederas';
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				flaa : fl
				
			}, function(responseText) {
				$('#selectconfederas').html(responseText);
			});
		});
	});
                  
        </script>       
        
 <script>
$(document).ready(function() {

$( "#selectconfederas" ).change(function () {
    var str = "";
    $( "select option:selected" ).each(function() {
      str += $( this ).val() + "";
      var fl='confederainfo';
      $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codconfe : str
				
			}, function(responseText) {
                            	$("#confederadiv").html(responseText);
			});
      
      
    });
    
  })
  .change();
    
    
});     

</script>    
   
        
        
        


        
    </head>
    
    <body class="page">
        
            <%@include file="../WEB-INF/jspf/menu_a.jspf"%>
            <!-- background images -->
            <div class="page-bg-imgs-list">
                <img src="../Template1/img/story-bg-1.jpg" id="page-1-img" class="main-img" alt="Confederaciones">
                <img src="../Template1/img/story-bg-2.jpg" id="page-2-img" alt="Paises">
                <img src="../Template1/img/story-bg-3.jpg" id="page-3-img" alt="Selecciones">
                <img src="../Template1/img/story-bg-4.jpg" id="page-4-img" alt="Grupos">                            
                
            
            </div>
            
            <div class="container-fluid">

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-5 col-xl-5">
                        
                        <div class="header">

                            <!-- site title -->
                            <header class="box box-white">
                                <a href="javascript:void(0)" class="js-site-title">
                                    <h1 class="box-text site-title-text">Creacion</h1>    
                                </a>            
                            </header>
                            
                            <!-- site navigation -->
                            <nav class="js-nav">
                                <ul class="nav-items-container">

                                    <li data-nav-item-id="page-1" class="block-keep-ratio block-keep-ratio-1-1 block-width-half box box-white box-nav-item js-nav-item pull-xs-left" id="btnconfederas">
                                        <a href="#page-1" class="block-keep-ratio-content box-nav-item-link">
                                            <span class="box-text box-text-nav-item flexbox-center">Confederaciones</span>
                                        </a>
                                    </li>

                                    <li data-nav-item-id="page-2" class="block-keep-ratio block-keep-ratio-1-1 block-width-half box box-white box-nav-item js-nav-item pull-xs-right">
                                        <a href="#page-2" class="block-keep-ratio-content box-nav-item-link">
                                            <span class="box-text box-text-nav-item flexbox-center">Paises</span>
                                        </a>
                                    </li>

                                    <li data-nav-item-id="page-3" class="block-keep-ratio block-keep-ratio-1-1 block-width-half box box-white box-nav-item js-nav-item pull-xs-left">
                                        <a href="#page-3" class="block-keep-ratio-content box-nav-item-link">
                                            <span class="box-text box-text-nav-item flexbox-center">Selecciones</span>
                                        </a>
                                    </li>

                                    <li data-nav-item-id="page-4" class="block-keep-ratio block-keep-ratio-1-1 block-width-half box box-white box-nav-item js-nav-item pull-xs-right">
                                        <a href="#page-4" class="block-keep-ratio-content box-nav-item-link">
                                            <span class="box-text box-text-nav-item flexbox-center">Grupos</span>
                                        </a>
                                    </li>
                                    <li data-nav-item-id="page-4" class="block-keep-ratio block-keep-ratio-1-1 block-width-half box box-white box-nav-item js-nav-item pull-xs-right">
                                        <a href="#page-4" class="block-keep-ratio-content box-nav-item-link">
                                            <span class="box-text box-text-nav-item flexbox-center">Marcadores</span>
                                        </a>
                                    </li>

                                </ul>
                            </nav>    
                        </div> <!-- .header -->

                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-7 col-xl-7">
                        <div class="content-wrapper js-content-wrapper">
                            <!-- about -->
                            <section data-page-id="page-1" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Crea las confederaciones</h2>
                                </header>
                                
                                <div class="content-text">
                                    <div id="confederasdiv">
                                        <select name="selectconfederas" id="selectconfederas"multiple="multiple">
  
                                        </select>
                                    
                                    </div>
                                    <br>
                                    <div id="confederadiv">
                                        fgftftft
                                        
                                    
                                    </div>
                                    
                                </div>
                               

                            </section> <!-- #about -->

                            <!-- gallery -->
                            <section data-page-id="page-2" class="content content-gallery js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Galllery</h2>
                                </header>
                                
                                <div class="content-text content-text-gallery">
                                    
                                    <!-- <p>Credits go to <a rel="nofollow" href="http://unsplash.com">Unsplash</a> for images.</p> -->

                                    <div class="flexslider-wrapper">

                                        <div id="slider" class="flexslider">
                                            <ul class="slides">
                                                <li><img src="../Template1/img/slider/slide1.jpg" alt="Slide 1" /></li>
                                                <li><img src="../Template1/img/slider/slide2.jpg" alt="Slide 2" /></li>
                                                <li><img src="../Template1/img/slider/slide3.jpg" alt="Slide 3" /></li>
                                                <li><img src="../Template1/img/slider/slide4.jpg" alt="Slide 4" /></li>
                                                <li><img src="../Template1/img/slider/slide5.jpg" alt="Slide 5" /></li>
                                                <li><img src="../Template1/img/slider/slide6.jpg" alt="Slide 6" /></li>
                                            </ul>
                                        </div> <!-- #slider -->
                                      
                                        <div id="carousel" class="flexslider">
                                            <ul class="slides">
                                                <li><img src="../Template1/img/slider/thumb1.jpg" alt="Thumbnail 1" /></li>
                                                <li><img src="../Template1/img/slider/thumb2.jpg" alt="Thumbnail 2" /></li>
                                                <li><img src="../Template1/img/slider/thumb3.jpg" alt="Thumbnail 3" /></li>
                                                <li><img src="../Template1/img/slider/thumb4.jpg" alt="Thumbnail 4" /></li>
                                                <li><img src="../Template1/img/slider/thumb5.jpg" alt="Thumbnail 5" /></li>
                                                <li><img src="../Template1/img/slider/thumb6.jpg" alt="Thumbnail 6" /></li>
                                            </ul>
                                        </div>  <!-- #carousel -->

                                    </div>
                                        
                                </div>            

                            </section> <!-- #gallery -->
                            
                            <!-- services -->
                            <section data-page-id="page-3" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Services</h2>
                                </header>
                                
                                <div class="content-text">
                                    <p>Download the story theme from templatemo. Quisque placerat turpis non tortor fringilla, in ornare magna eleifend. Etiam mattis varius fringilla.
                                    </p>
                                    <h4>Sub Title One</h4>
                                    <p>Nullam tortor mauris, bibendum ac ex vel, auctor congue est. Aenean quis tellus molestie, laoreet massa a, maximus est. Interdum et malesuada fames ac ante ipsum primis. 
                                    </p>
                                    <h4>Sub Title Two</h4>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut sapien quis ante faucibus tempus. Nam ut dui vitae tellus vehicula porttitor in nec lorem.
                                    </p>
                                </div>            

                            </section> <!-- #services -->

                            <!-- contact -->
                            <section data-page-id="page-4" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Contact</h2>
                                </header>
                                
                                <div class="content-text">
                                    <p>In felis turpis, tincidunt a odio interdum, ornare interdum magna. Proin leo tortor, adipiscing et volutpat tincidunt, imperdiet sit amet purus.
                                    </p>
                                    <form action="index.html" method="post" class="contact-form">

                                        <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group-2-col-left">
                                            <input type="text" id="contact_name" name="contact_name" class="form-control" placeholder="Name" required/>
                                        </div>
                                        <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group-2-col-right">
                                            <input type="email" id="contact_email" name="contact_email" class="form-control" placeholder="Email" required/>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" id="contact_subject" name="contact_subject" class="form-control" placeholder="Subject" required/>
                                        </div>
                                        <div class="form-group">
                                            <textarea id="contact_message" name="contact_message" class="form-control" rows="4" placeholder="Message" required></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary submit-btn">Send</button>

                                    </form>                           
                                </div>            

                            </section> <!-- #contact -->
                        </div>
                    </div>

                </div>
                    
                <!-- footer row -->
                <footer class="row footer js-footer">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                        <p class="copyright-text">Copyright &copy; 2016 Company Name 
                        
                        | Design: <a href="http://www.templatemo.com/tm-480-story" target="_parent" title="Story Bootstrap Theme">The Story</a></p>  

                    </div>                
                </footer>

            </div>  <!-- .container-fluid -->

        <div id="preloader">
            <div id="status">&nbsp;</div>
        </div><!-- /#preloader -->      
            
        <!-- load JS files -->
       <%@include file="../WEB-INF/jspf/scriptsa.jspf" %>
    
    </body>
</html>