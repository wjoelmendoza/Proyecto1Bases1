<%-- 
    Document   : reportes
    Created on : 7/10/2016, 01:03:11 AM
    Author     : wxjoy
--%>
<%@include file="../WEB-INF/jspf/val_a.jspf" %>
<%valAdmin(session,response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headera.jspf"%>
        <title>Reportes</title>
        <script>
        function jsReporte(){
            var idrq = 'formReq';
            var reporte = document.frep.srep.value;
            $.post(
                '/Apuestas/Reportes',
                {
                    idreq: idrq,
                    rep: reporte
                }, function(responseText){
                    $("#repdiv").html(responseText);
                }
                );
            //$("#repdiv").html(reporte);
        }
    </script>
    </head>
    <body role="document">
        <%@include file="../WEB-INF/jspf/menu_a.jspf"%>
        <div role="main" class="container theme-showcase">
            <br>
            <div class="jumbotron">
                <h2>Generar reportes</h2>
                seleccione que reporte quiere visualizar.
                <form method="post" name="frep">
                    <select name="srep" id="srep">
                        <option>Reporte 1</option>
                        <option>Reporte 2</option>
                        <option>Reporte 3</option>
                        <option>Reporte 4</option>
                        <option>Reporte 5</option>
                        <option>Reporte 6</option>
                        <option>Reporte 7</option>
                        <option>Reporte 8</option>
                        <option>Reporte 9</option>
                        <option>Reporte 10</option>
                        <option>Reporte 11</option>
                        <option>Reporte 12</option>
                        <option>Reporte 13</option>
                        <option>Reporte 14</option>
                        <option>Reporte 15</option>
                        <option>Reporte 16</option>
                        <option>Reporte 17</option>
                        <option>Reporte 18</option>
                        <option>Reporte 19</option>
                        <option>Reporte 20</option>
                        <!--resto de reportes-->
                    </select>
                    <input type="button" value="Ver" class="btn btn-primary" onclick="jsReporte()">
                </form>
                <hr>
                <div id="repdiv">
                   
                </div>
                <br>
            </div>
        </div>
        <%@include file="../WEB-INF/jspf/scriptsu.jspf"%>
    </body>
</html>
