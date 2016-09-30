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
        
        <title>Admin-CreationCenter</title>
        <%@include file="../WEB-INF/jspf/headera.jspf" %>
        <script src="http://code.jquery.com/jquery-latest.js">

        </script>
 
        <script>
	$(document).ready(function() {
		$('#submitconf').click(function(event) {
			var nom = $('#nombreconf').val();
                        var fl = $('#flagconf').val();
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				nombre : nom,
                                flaa : fl
				
			}, function(responseText) {
				$('#respconf').html(responseText);
			});
		});
	});
        </script>
        
        
         <script>
            $(document).ready(function() {
		$('#btnselecciones').click(function(event) {
			
                        var fl = 'lpaisesdispo';
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				flaa : fl
				
			}, function(responseText) {
				$('#selectpaisesdispo').html(responseText);
			});
                        $('#divselesubmit').html(" ");
		});
	});
                  
        </script>
        <script>
           
            $(document).ready(function() {
		$('#submitseles').click(function(event) {
			
                        var fl = 'submitseles';
                        var codpais = $('#selectpaisesdispo').val();
                        var grup = $('#selectlgrupos').val();
                        var dirsele = $('#dirsele').val();
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				flaa : fl,
                                codp : codpais,
                                grup : grup,
                                dir: dirsele
				
			}, function(responseText) {
				$('#divselesubmit').html(responseText);
			});

		});
	});
            
        </script>
        
        <script>
           
            $(document).ready(function() {
		$('#btngetpartidos').click(function(event) {
			
                       var fl='creapartidos';
                       var grupo =document.getElementById('2lgrupos').value;
                       
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				flaa : fl,
                                grupo : grupo
                                				
			}, function(responseText) {
                                alert(responseText);
				$('#divbtnspartidos').html(responseText);
			});

		});
	});
            
        </script>
        
        <script>
            function jsbtnpartido(eq1,eq2,grp) {
                var fl = 'creapartidos2';
                alert("BOTONASO");                
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codeq1 : eq1,
                                codeq2 : eq2,
                                grupo : grp
				
			}, function(responseText) {
                            	$("#divbtnscontenido").html(responseText);
			});
            }
        </script>
         <script>
            function jsGrabarPartido() {
                var fl = 'creapartidos2';
                alert("BOTONASO");                
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codeq1 : eq1,
                                codeq2 : eq2,
                                grupo : grp
				
			}, function(responseText) {
                            	$("#divbtnscontenido").html(responseText);
			});
            }
        </script>
        
    </head>
    
    <body class="page">
        
            <%@include file="../WEB-INF/jspf/menu_a.jspf"%>
            <!-- background images -->
            <div class="page-bg-imgs-list">
                
                <img src="../Template1/img/background.jpg" id="page-1-img" class="main-img" alt="Confederaciones">
                <img src="../Template1/img/background.jpg" id="page-2-img" alt="Paises">
                <img src="../Template1/img/background.jpg" id="page-3-img" alt="Selecciones">
                <img src="../Template1/img/background.jpg" id="page-4-img" alt="Grupos">                            
               
            
            </div>
            
            <div class="container-fluid">

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-5 col-xl-5">
                        
                        <div class="header">

                            <!-- site title -->
                            <header class="box-white">
                                <a href="javascript:void(0)" class="js-site-title">
                                    <h1 class="box-text site-title-text">Creacion</h1>    
                                </a>            
                            </header>
                            
                            <!-- site navigation -->
                            <nav class="js-nav"> 
                                <ul class="nav nav-pills">

                                    <li data-nav-item-id="page-1" class="box-nav-item js-nav-item pull-xs-left" id="btnconfederaciones">
                                        <a href="#page-1">
                                            <span>Confederaciones</span>
                                        </a>
                                    </li>

                                    <li data-nav-item-id="page-2" class="box-nav-item js-nav-item pull-xs-left" id="btnpaises">
                                        <a href="#page-2">
                                            <span>Partidos</span>
                                        </a>
                                    </li>

                                    <li data-nav-item-id="page-3" class="box-nav-item js-nav-item pull-xs-left" id="btnselecciones">
                                        <a href="#page-3">
                                            <span>Selecciones</span>
                                        </a>
                                    </li>

                                    <li data-nav-item-id="page-4" class="box-nav-item js-nav-item pull-xs-left">
                                        <a href="#page-4">
                                            <span>Paises</span>
                                        </a>
                                    </li>
                                    <li data-nav-item-id="page-5" class="box-nav-item js-nav-item pull-xs-left">
                                        <a href="#page-5">
                                            <span>Marcadores</span>
                                        </a>
                                    </li>

                                </ul>
                            </nav>    
                        </div> 

                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-7 col-xl-7">
                        <div class="content-wrapper js-content-wrapper">
                            <!-- about -->
                            <section data-page-id="page-1" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Crea las confederaciones</h2>
                                </header>
                                
                                <div class="content-text">
                                    <form id="form1">
                                        <table cellpadding="5" >
                                            <tr>
                                                <td>Nombre</td>
                                                <td><input id="nombreconf" type="text" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><input    id="flagconf" type="hidden" value="confederacion" ></td>
                                            </tr>
                        
                                            <tr>
                                                
                                                <td></td>
                                                <td>
                                                <input class="btn btn-success" id="submitconf" type="button" value="Crear" >
                                                
                                                </td>
                                            </tr>
                                        </table>
                                         <div id="respconf">
                                    
                                        </div>
                                    </form>
                                </div>
                               

                            </section> <!-- #about -->

                            <!-- gallery -->
                            <section data-page-id="page-2" class="content content-gallery js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Crea los partidos</h2>
                                </header>
                                <div class="content-text">
                                    <p>Escoje el grupo.</p>
                                    <br>
                                    <input class="btn btn-success" id="btngetpartidos" type="button" value="Get" >
                                    <select id="2lgrupos">
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                        <option value="F">F</option>
                                        <option value="G">G</option>
                                        <option value="H">H</option>
                                        
                                    </select>
                                    <br>
                                    <br>
                                    <div id="divbtnspartidos">
                                    
                                    </div>
                                    <br>
                                    <br>
                                    <div id="divbtnscontenido">
                                    
                                    </div>
                                                               
                                </div>
                                
                                
                                <div class="content-text content-text-gallery">
                                    
                                    

                                    <div class="flexslider-wrapper">

                                        <div id="slider" class="flexslider">
                                            <ul class="slides" id="marcadoresslides">
                                                
                                            </ul>
                                        </div> <!-- #slider -->
                                      
                                        <div id="carousel" class="flexslider">
                                            <ul class="slides">
                                               
                                            </ul>
                                        </div>  <!-- #carousel -->

                                    </div>
                                        
                                </div>            

                            </section> <!-- #gallery -->
                            
                            <!-- services -->
                            <section data-page-id="page-3" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">CREA LAS SELECCIONES DEL MUNDIAL</h2>
                                </header>
                                
                                <div class="content-text">
                                    <form id="formseles">
                                    <table cellpadding="15" cellspacing="30">
                                        <tr>
                                                <td>Director</td>
                                                <td><input id="dirsele" type="text" maxlength="100" required></td>
                                            </tr>
                                        <tr>
                                           
                                            <td>
                                                <div id="lpaisesdispo">
                                                    <select id="selectpaisesdispo">
                                                                     
                                                    </select>
                                                </div>
                                            </td>
                                            <td>
                                                <div id="lgrupos">
                                                    <select id="selectlgrupos">
                                                        <option value="A">A</option>
                                                        <option value="B">B</option>
                                                        <option value="C">C</option>
                                                        <option value="D">D</option>
                                                        <option value="E">E</option>
                                                        <option value="F">F</option>
                                                        <option value="G">G</option>
                                                        <option value="H">H</option>
                                                    </select>
                                                </div>
                                            </td>
                                        
                                            <td></td>
                                            <td>
                                                <input class="btn btn-success" id="submitseles" type="button" value="Crear" >
                                                
                                            </td>
                                        </tr>
                                    </table>
                                    </form>
                                    
                                    <br>
                                    <div id="divselesubmit">
                                        
                                    </div>
                                </div>            

                            </section> <!-- #services -->

                            <!-- crea partidos -->
                            <section data-page-id="page-4" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Crea Los Partidos</h2>
                                </header>
                                
                                <div class="content-text">
                                    <p>Escoje el grupo.</p>
                                    <br>
                                    <select id="4lgrupos">
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                        <option value="F">F</option>
                                        <option value="G">G</option>
                                        <option value="H">H</option>
                                        
                                    </select>
                                                               
                                </div>
                                <div class="content-text">
                                    
                                    
                                    
                                    
                                    
                                </div>

                            </section> <!-- #crea partidos -->
                        </div>
                    </div>

                </div>
                    
                <!-- footer row -->
                <footer class="row footer js-footer">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                        <p class="copyright-text">Copyright &copy; 2016 | Walter and Byron Productions
                        
                          

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