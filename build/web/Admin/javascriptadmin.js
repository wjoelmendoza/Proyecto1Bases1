/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

	$(document).ready(function() {
		$('#submitconf').click(function(event) {
			var nom = $('#nombreconf').val();
                        var acro = $('#acronimoconf').val();
                        var fl = $('#flagconf').val();
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				nombre : nom,
                                acronimo:acro,
                                flaa : fl
				
			}, function(responseText) {
				$('#respconf').html(responseText);
			});
		});
	});
       
        
        
        
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
            
      
           
            $(document).ready(function() {
		$('#btngetpartidos').click(function(event) {
			
                       var fl='creapartidos';
                       var grupo =document.getElementById('2lgrupos').value;
                       
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				flaa : fl,
                                grupo : grupo
                                				
			}, function(responseText) {
                                //alert(responseText);
				$('#divbtnspartidos').html(responseText);
			});

		});
         
                
                
                
	});

            function jsbtnpartido(eq1,eq2,grp) {
                var fl = 'creapartidos2';
                //alert("BOTONASO");                
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codeq1 : eq1,
                                codeq2 : eq2,
                                grupo : grp
				
			}, function(responseText) {
                            	$("#divbtnscontenido").html(responseText);
			});
            }

            function jsGrabarPartido(wflag) {
                alert("jsGrabarPartido:"+wflag);
                var fl = 'grabarpartido';
                var cod1 = $('#txtcodq1').val();
                var cod2 = $('#txtcodq2').val();
                var goleq1 = $('#txtgolq1').val();
                var goleq2 = $('#txtgolq2').val();
                var fecha = $('#txtfecpartido').val();
                var hora = $('#txthorapartido').val();
                var grr = $('#2lgrupos').val();
                var flagw = wflag;
                var codciudad = $('#selectciudadpartido').val();
                    
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codeq1 : cod1,
                                codeq2 : cod2,
                                golq1 : goleq1,
                                golq2 : goleq2,
                                fecha : fecha,
                                hora : hora,
                                grr : grr,
                                flagw : flagw,
                                codciudad : codciudad
				
			}, function(responseText) {
                            	$("#divbtnscontenido").html(responseText);
			});
            }
   
            $(document).ready(function() {
                $('#btnrefreshconfe').click(function(event) {
			
                        var fl = 'lconfederas';
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				flaa : fl
				
			}, function(responseText) {
				$('#selectconfederas').html(responseText);
			});

		});
                
            });
      
            $(document).ready(function() {
                
                 $('#btnconfederaciones').click(function(event) {
			
                        var fl = 'lconfederas';
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				flaa : fl
				
			}, function(responseText) {
				$('#selectconfederas').html(responseText);
			});
		});
                
            });
           
	
      
            $(document).ready(function() {

                $( "#selectconfederas" ).change(function () {
                var str = "";
               
                str += $( this ).val() + "";
                var fl='confederainfo';
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codconfe : str
				
			}, function(responseText) {
                            	$("#confederadiv").html(responseText);
			});
      
      
                
    
                })  
                .change();
        
        
        
                 
   
            }); 
            
            
 
            function jsMEconfe(wflag) {
                //alert("HOLAA");
                var fle = 'Econfe';
                var flm = 'Mconfe';
                
                var codeconfe =document.getElementById('selectconfederas').value;
                if(wflag=="E")
                {
                    $.post('/Apuestas/ACrear', {
				flaa : fle,
                                codeconfe :codeconfe
				
			}, function(responseText) {
                            	$("#divEMconfe").html(responseText);
			});
                }
                else if(wflag=="M")
                {
                    var nomconfe =$('#txtnombreconf').val();
                    var acroconfe =$('#txtacroconfe').val();
                    $.post('/Apuestas/ACrear', {
				flaa : flm,
                                codeconfe :codeconfe,
                                nomconfe : nomconfe,
                                acroconfe : acroconfe
				
			}, function(responseText) {
                            	$("#divEMconfe").html(responseText);
			});
                }
                
            }
    
    /* AQUI SON LOS METODOS PARA TODO LO RELACIONADO CON EL ARBITRO*/
            $(document).ready(function() {
                
                 $('#btnarbitros').click(function(event) {
			//alert("btnarbitros")
                        var fl = 'lpaises';
                        var fl2 = 'larbitros'
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('/Apuestas/ACrear', {
				flaa : fl
				
			}, function(responseText) {
				$('#lallpaises').html(responseText);
			});
                        
                        $.post('/Apuestas/ACrear', {
				flaa : fl2
				
			}, function(responseText) {
				$('#5selectlarbitros').html(responseText);
			});
                        
		});
                
                
                 $('#btngetarbitros').click(function(){
                     
                     var fl2 = 'larbitros'
                     $.post('/Apuestas/ACrear', {
				flaa : fl2
				
			}, function(responseText) {
				$('#5selectlarbitros').html(responseText);
			});
                     
                 });
                 
                 
                $('#btnshowarbitro').click(function(){
                    
                    var fl = 'infoarbitro';
                    var coda = document.getElementById('5selectlarbitros').value;
                    $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codigoarbitro : coda
				
			}, function(responseText) {
				$('#divarbitroinfo').html(responseText);
			});
                    
                    
                });
                
                
                $( "#5selectlarbitros" ).change(function () {
                
               
                var coda= $( this ).val()+'';
                var fl='infoarbitro';
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codigoarbitro : coda
				
			}, function(responseText) {
                            	$("#divarbitroinfo").html(responseText);
			});
      
      
                
    
                }).change();
                
                
                $('#btnsubmitarbitro').click(function(){
                    
                    var fl='submitarbitro';
                    var nom= document.getElementById('nombrearbitro').value;
                    var codp= document.getElementById('lallpaises').value;
                    $.post('/Apuestas/ACrear', {
				flaa : fl,
                                nombre : nom,
                                codigop : codp
				
			}, function(responseText) {
				$('#divrespsubmitarbitro').html(responseText);
			});
                    
                    
                });
                
            });
            
            
            
            
            function jsMEarbitro(wflag) {
                //alert("HOLAA jsMEarbitro");
                var fle = 'Earbitro';
                var flm = 'Marbitro';
                
                
                if(wflag=="E")
                {
                    var coda = $('#txtcodarbitro').val();
                    
                    $.post('/Apuestas/ACrear', {
				flaa : fle,
                                codarbitro :coda
				
			}, function(responseText) {
                            	$("#divEMarbitro").html(responseText);
			});
                }
                else if(wflag=="M")
                {
                    var coda = $('#txtcodarbitro').val();
                    var nom = $('#txtnombrearbitro').val();
                    var codp = document.getElementById('selectarbpaiact').value;
                    $.post('/Apuestas/ACrear', {
				flaa : flm,
                                codarbitro :coda,
                                nombre : nom,
                                codpais : codp
				
			}, function(responseText) {
                            	$("#divEMarbitro").html(responseText);
			});
                }
                
            }

        /*ESTOS SON METODO PARA LOS PAISES*/
        $(document).ready(function() {
            
            $('#btnpaises').click(function(){
                var fl='lconfederas';
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                
				
			}, function(responseText) {
				$('#4lallconfederas').html(responseText);
			});
                
                
            });
            $('#btnsubmitpais').click(function(){
                
                var fl='submitpais';
                var nom= document.getElementById('nombrepais').value;
                var codconfe= document.getElementById('4lallconfederas').value;
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                nombre : nom,
                                codconferencia : codconfe
				
			}, function(responseText) {
				$('#divrespsubmitpais').html(responseText);
			});
                    
                
            });
            $('#btngetpaises').click(function(){
                
                var fl='lpaises';
                $.post('/Apuestas/ACrear', {
				flaa : fl
                                
				
			}, function(responseText) {
				$('#4selectlpaises').html(responseText);
			});
                    
                
            });
            $('#btnshowpais').click(function(){
                
                var fl='infopais';
                var codpais= document.getElementById('4selectlpaises').value;
                
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codigopais : codpais
                                
				
			}, function(responseText) {
				$('#divpaisinfo').html(responseText);
			});
                    
                
            });
            
            
            $( "#4selectlpaises" ).change(function () {
                
               
                var codpais= $( this ).val()+'';
                var fl='infopais';
                $.post('/Apuestas/ACrear', {
				flaa : fl,
                                codigopais : codpais
				
			}, function(responseText) {
                            	$("#divpaisinfo").html(responseText);
			});
      
      
                
    
                }).change();
            
            
            
        });
        
        
        function jsMEpais(wflag) {
                //alert("HOLAA jsMEarbitro");
                var fle = 'Epais';
                var flm = 'Mpais';
                
                
                if(wflag=="E")
                {
                    var codp = $('#txtcodpais').val();
                    
                    $.post('/Apuestas/ACrear', {
				flaa : fle,
                                codpais :codp
				
			}, function(responseText) {
                            	$("#divEMpais").html(responseText);
			});
                }
                else if(wflag=="M")
                {
                    var codp = $('#txtcodpais').val();
                    var nom = $('#txtnombrepais').val();
                    var codconfe = document.getElementById('selectconfepaisact').value;
                    $.post('/Apuestas/ACrear', {
				flaa : flm,
                                codpais :codp,
                                nombre : nom,
                                codconfe : codconfe
				
			}, function(responseText) {
                            	$("#divEMpais").html(responseText);
			});
                }
                
            }
        
        
                       
     

