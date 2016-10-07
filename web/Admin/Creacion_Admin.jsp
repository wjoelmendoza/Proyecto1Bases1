<%-- 
    Document   : newjsp
    Created on : 22/09/2016, 03:47:42 AM
    Author     : byron
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!--<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">-->
        
        <title>Admin-CreationCenter</title>
        <%@include file="../WEB-INF/jspf/headera.jspf" %>
       <!-- <script src="http://code.jquery.com/jquery-latest.js">

        </script>
       -->
        
        
    </head>
    
    <body class="page">
        
            <%@include file="../WEB-INF/jspf/menu_a.jspf"%>
            <!-- background images -->
            <div class="page-bg-imgs-list">
                
                <img src="../Template1/img/background.jpg" id="page-1-img" class="main-img" alt="Confederaciones">
                <img src="../Template1/img/background.jpg" id="page-2-img" alt="Paises">
                <img src="../Template1/img/background.jpg" id="page-3-img" alt="Selecciones">
                <img src="../Template1/img/background.jpg" id="page-4-img" alt="Grupos"> 
                <img src="../Template1/img/background.jpg" id="page-5-img" alt="Arbitros"> 
                <img src="../Template1/img/background.jpg" id="page-6-img" alt="Plantillas">
                <img src="../Template1/img/background.jpg" id="page-7-img" alt="Ciudades">
               
            
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

                                    <li data-nav-item-id="page-2" class="box-nav-item js-nav-item pull-xs-left" id="btnpartidos">
                                        <a href="#page-2">
                                            <span>Partidos</span>
                                        </a>
                                    </li>

                                    <li data-nav-item-id="page-3" class="box-nav-item js-nav-item pull-xs-left" id="btnselecciones">
                                        <a href="#page-3">
                                            <span>Selecciones</span>
                                        </a>
                                    </li>

                                    <li data-nav-item-id="page-4" class="box-nav-item js-nav-item pull-xs-left" id="btnpaises">
                                        <a href="#page-4">
                                            <span>Paises</span>
                                        </a>
                                    </li>
                                    <li data-nav-item-id="page-5" class="box-nav-item js-nav-item pull-xs-left" id="btnarbitros">
                                        <a href="#page-5">
                                            <span>Arbitros</span>
                                        </a>
                                    </li>
                                    <li data-nav-item-id="page-6" class="box-nav-item js-nav-item pull-xs-left" id="btnarbitros">
                                        <a href="#page-6">
                                            <span>Plantillas</span>
                                        </a>
                                    </li>
                                    <li data-nav-item-id="page-7" class="box-nav-item js-nav-item pull-xs-left" id="btnarbitros">
                                        <a href="#page-7">
                                            <span>Ciudades</span>
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
                                    <h2 class="box-text page-title-text">Confederaciones</h2>
                                </header>
                                
                                <div class="content-text">
                                    <form id="form1">
                                        <table cellpadding="5" >
                                            <tr>
                                                <td>Nombre:</td>
                                                <td><input id="nombreconf" type="text" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Acronimo:</td>
                                                <td><input id="acronimoconf" type="text" maxlength="50" required></td>
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
                                    </form>
                                         <div id="respconf">
                                    
                                        </div>
                                        <br>
                                        <br>
                                        <br>
                                        <input class="btn btn-success" id="btnrefreshconfe" type="button" value="Get" >
                                        <div>
                                            
                                            <select name="selectconfederas" id="selectconfederas" multiple="multiple">
  
                                            </select>
                                    
                                        </div>
                                        <br>
                                        <div id="confederadiv">
                                            fgftftft
                                        
                                    
                                        </div>
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
                                    <h2 class="box-text page-title-text">Crea Los Paises</h2>
                                </header>
                                
                                <div class="content-text">
                                  
                                    
                                    <form id="formarbitros">
                                        <table cellpadding="5" >
                                            <tr>
                                                <td>Nombre:</td>
                                                <td><input id="nombrepais" type="text" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Confederacion:</td>
                                                <td>
                                                        <select id="4lallconfederas">
                                                            
                                                            
                                                    
                                                        </select>
                                                
                                                </td>
                                            </tr>
                                                                  
                                            <tr>
                                                
                                                <td></td>
                                                <td>
                                                <input class="btn btn-success" id="btnsubmitpais" type="button" value="Crear" >
                                                
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                    <div id="divrespsubmitpais">
                                        
                                    </div>
                                    <br>
                                    <h5>Modificacion y Eliminacion</h5>
                                    <br>
                                    <input class="btn btn-success" id="btngetpaises" type="button" value="Get" >
                                    <input class="btn btn-success" id="btnshowpais" type="button" value="Show" >
                                    <select id="4selectlpaises">
                                        
                                    </select>
                                    <br>
                                    <br>
                                    <div id="divpaisinfo">
                                        
                                    </div>                       
                                    <div id="divEMpais">
                                        
                                    </div>
                                    
                                    
                                    
                                                               
                                </div>
                                

                            </section> <!-- #crea partidos -->
                            <section data-page-id="page-5" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Crea Los Arbitros</h2>
                                </header>
                                
                                <div class="content-text">
                                   <form id="formarbitros">
                                        <table cellpadding="5" >
                                            <tr>
                                                <td>Nombre:</td>
                                                <td><input id="nombrearbitro" type="text" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Pais:</td>
                                                <td>
                                                        <select id="lallpaises">
                                                            
                                                            
                                                    
                                                        </select>
                                                
                                                </td>
                                            </tr>
                                                                  
                                            <tr>
                                                
                                                <td></td>
                                                <td>
                                                <input class="btn btn-success" id="btnsubmitarbitro" type="button" value="Crear" >
                                                
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                    <div id="divrespsubmitarbitro"></div>
                                         
                                    <br>
                                    <h5>Modificacion y Eliminacion</h5>
                                    <br>
                                    <input class="btn btn-success" id="btngetarbitros" type="button" value="Get" >
                                    <input class="btn btn-success" id="btnshowarbitro" type="button" value="Show" >
                                    <select id="5selectlarbitros">
                                        
                                    </select>
                                    <br>
                                    <br>
                                    <div id="divarbitroinfo">
                                        
                                    </div>                       
                                    <div id="divEMarbitro">
                                        
                                    </div>
                                    
                                </div>

                            </section> <!-- #crea ARBITROS -->
                            
                            <section data-page-id="page-6" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Ingresa los jugadores a sus Selecciones</h2>
                                </header>
                                
                                <div class="content-text">
                                   <form id="formarbitros">
                                        <table cellpadding="5" >
                                            <tr>
                                                <td>Camiseta:</td>
                                                <td><input id="nombrearbitro" type="number" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Fecha Nacimiento:</td>
                                                <td><input id="nombrearbitro" type="date" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Estatura:</td>
                                                <td><input type="number" step="0.01"></td>
                                            </tr>
                                            <tr>
                                                <td>Peso:</td>
                                                <td><input type="number" step="0.01"></td>
                                            </tr>
                                            
                                            <tr>
                                                <td>Nombre:</td>
                                                <td><input id="nombrearbitro" type="text" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Equipo:</td>
                                                <td><input id="nombrearbitro" type="text" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Posicion:</td>
                                                <td><input id="nombrearbitro" type="text" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Seleccion:</td>
                                                <td>
                                                        <select id="6lallselecciones">
                                                            
                                                            
                                                    
                                                        </select>
                                                
                                                </td>
                                            </tr>
                                                                  
                                            <tr>
                                                
                                                <td></td>
                                                <td>
                                                <input class="btn btn-success" id="btnsubmitarbitro" type="button" value="Crear" >
                                                
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                    <div id="divrespsubmitarbitro"></div>
                                         
                                    <br>
                                    <h5>Modificacion y Eliminacion</h5>
                                    <br>
                                    <input class="btn btn-success" id="btngetarbitros" type="button" value="Get" >
                                    <input class="btn btn-success" id="btnshowarbitro" type="button" value="Show" >
                                    <select id="5selectlarbitros">
                                        
                                    </select>
                                    <br>
                                    <br>
                                    <div id="divarbitroinfo">
                                        
                                    </div>                       
                                    <div id="divEMarbitro">
                                        
                                    </div>
                                    
                                </div>

                            </section> <!-- #crea ARBITROS -->
                            <section data-page-id="page-7" class="content js-content">
                                
                                <header class="box box-black margin-b-20">
                                    <h2 class="box-text page-title-text">Crea las Ciudades</h2>
                                </header>
                                
                                <div class="content-text">
                                    <form id="form1">
                                        <table cellpadding="5" >
                                            <tr>
                                                <td>Nombre:</td>
                                                <td><input id="nombreconf" type="text" maxlength="50" required></td>
                                            </tr>
                                            <tr>
                                                <td>Acronimo:</td>
                                                <td><input id="acronimoconf" type="text" maxlength="50" required></td>
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
                                    </form>
                                         <div id="respconf">
                                    
                                        </div>
                                        <br>
                                        <br>
                                        <br>
                                        <input class="btn btn-success" id="btnrefreshconfe" type="button" value="Get" >
                                        <div>
                                            
                                            <select name="selectconfederas" id="selectconfederas" multiple="multiple">
  
                                            </select>
                                    
                                        </div>
                                        <br>
                                        <div id="confederadiv">
                                            fgftftft
                                        
                                    
                                        </div>
                                        </div>
                                    </form>
                                </div>
                               

                            </section> <!-- #about -->

                            
                            
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