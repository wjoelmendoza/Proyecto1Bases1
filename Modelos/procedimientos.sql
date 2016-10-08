--Procedimiento para crear un nuevo usuario
create or replace PROCEDURE REGISTRAR_CLIENTE(
  P_NOMBRE VARCHAR2,
  P_FNAC DATE,
  P_CORREO VARCHAR2,
  P_PAGO CHAR,
  P_CLAVE VARCHAR2,
  P_PAIS VARCHAR2
)
IS
BEGIN
  INSERT INTO usuario(nombre,fecha_nac,cod_pais,tipo,clave,pago,correo)
  values (p_nombre,p_fnac,(SELECT P.cod_pais from pais P where P.NOMBRE = p_pais),'C',p_clave,p_pago,p_correo);
  commit;
END;

--OBTENER LOS DATOS DEL USUARIO SEGUN CODIGO DE USUARIO

CREATE OR REPLACE PROCEDURE get_d_usuario(
  COD_USER_I IN INTEGER,
  DATOS_I OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN DATOS_I FOR
    SELECT U.NOMBRE, U.CORREO, U.FECHA_NAC, P.NOMBRE
    FROM USUARIO U, PAIS P
    WHERE U.COD_USUARIO = COD_USER_I AND U.COD_PAIS = P.COD_PAIS;
END;

--REFRESCAR USUARIO
CREATE OR REPLACE PROCEDURE act_d_usuario(
  cod_u_i IN INTEGER,
  nombre_i IN VARCHAR2,
  correo_i IN VARCHAR2,
  fecha_i IN DATE,
  p_nombre_i IN VARCHAR2,
  c_correo_i IN VARCHAR2
)
IS
  cod_pais_w INTEGER;
BEGIN
  SELECT S.COD_PAIS INTO cod_pais_w 
  FROM PAIS S
  WHERE S.NOMBRE = p_nombre_i;
  
  IF(c_correo_i ='si') THEN
    UPDATE USUARIO U
    SET
      U.CORREO = correo_i,
      U.NOMBRE = nombre_i,
      U.FECHA_NAC = fecha_i,
      U.COD_PAIS = cod_pais_w
    WHERE U.COD_USUARIO = cod_u_i;
  ELSE
    UPDATE USUARIO U
      SET
        U.NOMBRE = nombre_i,
        U.FECHA_NAC = fecha_i,
        U.COD_PAIS = cod_pais_w
      WHERE U.COD_USUARIO = cod_u_i;
  END IF;
END;

--Lista los paises de la base
CREATE OR REPLACE PROCEDURE listado_paises(
  paises OUT SYS_REFCURSOR
)
IS 
BEGIN
  OPEN paises FOR
    SELECT P.nombre
    FROM pais P
    ORDER BY P.nombre;
END;

--Busca si nombre que se pasa como referencia cuenta con un equipo

CREATE OR REPLACE PROCEDURE buscar_equipo(
  DATOS OUT SYS_REFCURSOR,
  nombre_pais IN VARCHAR2
)
IS
BEGIN
  OPEN DATOS FOR
    SELECT E.cod_equipo, E.director
    FROM equipo E, Pais P
    WHERE p.nombre = nombre_pais AND E.COD_PAIS = P.COD_PAIS;
END;

--busca la plantilla de un equipo
CREATE OR REPLACE PROCEDURE listar_plantilla(
  codigo_equipo IN INTEGER,
  jugadores OUT SYS_REFCURSOR
) 
IS
BEGIN
  OPEN jugadores FOR
    SELECT J.camiseta, J.FECHA_NAC, J.estatura, J.peso, J.nombre, J.equipo, J.POSICION
    FROM JUGADOR J
    WHERE J.cod_equipo = codigo_equipo;
END;

--Sirve para hacer login
CREATE OR REPLACE PROCEDURE login_usuarios(
  u_correo IN VARCHAR2,
  u_clave IN VARCHAR2,
  d_usuario OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN d_usuario FOR
    SELECT U.COD_USUARIO, U.NOMBRE, U.TIPO
    FROM usuario U
    WHERE U.correo = u_correo AND U.clave = u_clave;
END;

--valida que el correo no este registrado en la base de datos
CREATE OR REPLACE PROCEDURE validar_correo(
  v_correo IN VARCHAR2,
  d_usuario OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN d_usuario FOR
    SELECT U.cod_usuario
    FROM USUARIO U
    WHERE U.correo = v_correo;
END;

--cambia la clave del usuario
CREATE OR REPLACE PROCEDURE cambiar_clave(
  codigo_usuario IN INTEGER,
  clave IN VARCHAR2,
  n_clave IN VARCHAR2,
  resultado OUT INTEGER
) 
IS
  clave_u VARCHAR2(15) DEFAULT 'nokey';
BEGIN
  SELECT U.clave INTO clave_u
  FROM usuario U
  WHERE U.cod_usuario = codigo_usuario;
  IF clave = clave_u then
    UPDATE USUARIO U
    SET U.clave = n_clave
    WHERE U.COD_USUARIO = codigo_usuario;
    resultado := 1;
  ELSE
    resultado := 0;
  END IF;
END;


--consulta todos los equipos
CREATE OR REPLACE PROCEDURE listar_equipos(
  equipos OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN  equipos FOR
    SELECT E.COD_EQUIPO, P.NOMBRE
    FROM equipo E, Pais P
    WHERE E.COD_PAIS = P.COD_PAIS
    ORDER BY p.NOMBRE;
END;


/**
*Consulta los equipos que se enfrentaran
*Segun el codigo del partido que se pasa
*como referencia
*/

CREATE OR REPLACE PROCEDURE get_rivales(
  codigo_partido IN INTEGER,
  rivales OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN rivales FOR
    SELECT R.COD_PART, E.COD_EQUIPO, P.NOMBRE
    FROM PAIS P,EQUIPO E, RIVALES R
    WHERE R.COD_PART = codigo_partido AND R.COD_EQUIPO = E.COD_EQUIPO 
      AND P.COD_PAIS = E.COD_PAIS;
END;

--Eliminar usuario

CREATE OR REPLACE PROCEDURE eliminar_usuario(
  codigo_usuario IN INTEGER,
  clave_usuario IN VARCHAR2,
  resultado OUT INTEGER
)
IS
clave_user VARCHAR(15);
BEGIN
  SELECT U.clave into clave_user FROM USUARIO U WHERE U.cod_usuario = codigo_usuario;
  
  IF clave_usuario = clave_user THEN
    resultado := 1;
    DELETE FROM marcador M WHERE M.COD_USUARIO = codigo_usuario;
    DELETE FROM usuario U WHERE U.cod_usuario = codigo_usuario;
  ELSE
    resultado := 0;
  END IF;
END;

--listar paises por grupo
CREATE OR REPLACE PROCEDURE listar_equipo_grupo(
  grupo_s in VARCHAR2,
  resultado  OUT   SYS_REFCURSOR
)
IS
BEGIN
  OPEN resultado FOR
    SELECT E.DIRECTOR , P.NOMBRE, C.ACRONIMO, E.COD_EQUIPO
    FROM Equipo E, Pais P, Confederacion C
    WHERE E.cod_pais = P.COD_PAIS AND p.cod_conf = C.cod AND  E.GRUPO = grupo_s;
END;

--busca el equipo segun codigo
CREATE OR REPLACE PROCEDURE buscar_equipo_cod(
  DATOS OUT SYS_REFCURSOR,
  cod_eq IN INTEGER,
  grupo_eq IN VARCHAR2
)
IS
BEGIN
  OPEN DATOS FOR
    SELECT E.cod_equipo, E.director, P.NOMBRE
    FROM equipo E, Pais P
    WHERE E.COD_EQUIPO = cod_eq AND E.COD_PAIS = P.COD_PAIS AND E.GRUPO = grupo_eq;
END;

--DEBERIA DE SACAR LOS PARTIDOS SEGUN EL GRUPO QUE SE PASA
--POR PARAMETRO
CREATE OR REPLACE PROCEDURE get_rivales_grupo(
  grupo_cons IN VARCHAR2,
  datos OUT SYS_REFCURSOR
)
IS
BEGIN
OPEN datos for
  SELECT R.COD_PART, E.COD_EQUIPO, P.NOMBRE
  FROM PAIS P,EQUIPO E, RIVALES R
  WHERE R.COD_EQUIPO = E.COD_EQUIPO AND P.COD_PAIS = E.COD_PAIS AND E.GRUPO = grupo_cons
  ORDER BY R.COD_PART;
END;

--Esto consulta a la base pos si un usuario ya ingreso un marcador
--si ya se encuentra el marcador, el cursor marcardor lleva datos de lo
--contrario se propone la creacion de un nuevo marcador

CREATE OR REPLACE PROCEDURE get_marcador_partido(
  grupo_cons IN VARCHAR2,
  codigo_partido IN INTEGER,
  codigo_usuario IN INTEGER,
  partido OUT SYS_REFCURSOR,
  marcador OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN partido FOR
    SELECT R.COD_PART,E.COD_EQUIPO,C.NOMBRE 
    FROM  rivales R, pais C, equipo E
    WHERE R.COD_PART=codigo_partido AND R.COD_EQUIPO = E.COD_EQUIPO AND C.COD_PAIS = E.COD_PAIS 
    AND E.GRUPO = grupo_cons;
  
  OPEN marcador FOR
    SELECT E.COD_EQUIPO,P.NOMBRE, M.GOLES, M.COD_PART
    FROM marcador M, pais P, equipo E
    WHERE M.COD_EQUIPO = E.COD_EQUIPO AND E.COD_PAIS = P.COD_PAIS
    AND E.GRUPO = grupo_cons AND M.COD_PART = codigo_partido AND M.COD_USUARIO = codigo_usuario;
END;


--este procedimiento guarda
CREATE OR REPLACE PROCEDURE guardar_marcador(
  codigo_usuario IN INTEGER,
  codigo_partido IN INTEGER,
  cod_equipo1 IN INTEGER,
  cod_equipo2 IN INTEGER,
  goles_1 IN INTEGER,
  goles_2 IN INTEGER,
  tipo_in IN VARCHAR2,
  crear IN VARCHAR2
)
IS
BEGIN
  IF crear = 'SI' THEN
    INSERT INTO MARCADOR (GOLES,COD_EQUIPO,COD_PART,TIPO,COD_USUARIO) VALUES(goles_1,cod_equipo1,codigo_partido,tipo_in,codigo_usuario);
    INSERT INTO MARCADOR (GOLES,COD_EQUIPO,COD_PART,TIPO,COD_USUARIO) VALUES(goles_2,cod_equipo2,codigo_partido,tipo_in,codigo_usuario);
    
  ELSE
    UPDATE MARCADOR
    SET GOLES = goles_1
    WHERE cod_equipo = cod_equipo1 AND COD_PART = codigo_partido AND COD_USUARIO =codigo_usuario;
    UPDATE MARCADOR
    SET GOLES = goles_2
    WHERE cod_equipo = cod_equipo2 AND COD_PART = codigo_partido AND COD_USUARIO =codigo_usuario;
  END IF;
END;

---------------------------PROCEDIMIENTOS DE ADMINISTRADOR-------------------------------------------------


--------------------------------CRUD CONFEDERACIONES-----------------------------------
---PROCEDIMIENTO PARA CREAR UNA CONFDERACION, ME RETORNA UN MENSAJE DE VALIDACION
CREATE OR REPLACE PROCEDURE set_confederacion(nom IN varchar2,acro IN varchar2)
AS
BEGIN
  INSERT INTO confederacion(nombre,acronimo)values(nom,acro);
END set_confederacion;

-- PROCEDIMIENTO PARA OBTENER TODOS LA CONFEDERACIONES

CREATE OR REPLACE PROCEDURE get_confederacion(cursor1 OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN cursor1 FOR
  select * from CONFEDERACION;
END get_confederacion;

--PROCEDIMIENTO PARA TRAER INFO DE UNA CONFEDERACION EN ESPECIFICO

CREATE OR REPLACE PROCEDURE get_confederacion2(cursor1 OUT SYS_REFCURSOR,codd IN INTEGER)
AS
BEGIN
  OPEN cursor1 FOR
  select * from CONFEDERACION WHERE cod=codd;
END get_confederacion2;
-----
-----PROCEDIMIENTO PARA ELIMINAR UNA CONFEDERACION
CREATE OR REPLACE PROCEDURE E_confederacion(codigo IN INTEGER)
AS
BEGIN
  delete from CONFEDERACION 
  WHERE  COD=codigo;
END E_confederacion;

--PROCEDIMIENTO PARA MODIFICAR UNA CONFEERACION

CREATE OR REPLACE PROCEDURE M_confederacion(codigo IN INTEGER,nom IN VARCHAR2,acronim IN VARCHAR2)
AS
BEGIN
  UPDATE CONFEDERACION
  SET nombre=nom,acronimo=acronim
  WHERE cod=codigo;
END M_confederacion;
--------------------------------------------





--------------------------------CRUD PARTIDOS---------------------------------
--ESTE PROCEDIMIENTO ME INDICA SI EL ADMIN YA CREO CON ANTERIORIDAD UN PARTIDO PARA LOS DOS EQUIPO INDICADOS,
--  SI ENCUENTRA UN PARTIDO ME RETORNA TODA LA INFO DE ESTE(ES MAS QUE TODO PARA DEPLEGAR LA INFO PARA LA MODIFICACION Y ELIMINACION DE ELLA)
create or replace PROCEDURE get_info_partido(codeq1 IN INTEGER,codeq2 IN INTEGER,mess OUT varchar2,cursorr OUT SYS_REFCURSOR)
AS
cursor cursor1(codeqq1 INTEGER) is select cod_equipo,cod_part from rivales where cod_equipo=codeqq1;  
cursor cursor2(codeqq2 INTEGER) is select cod_equipo,cod_part from rivales where cod_equipo=codeqq2;
flagw INTEGER;
codigoparti INTEGER;
BEGIN
  flagw :=0;
  FOR equipo1 IN cursor1(codeq1)
  LOOP
    FOR equipo2 IN cursor2(codeq2)
    LOOP
      --dbms_output.put_line(equipo1.cod_part);
      IF equipo1.cod_part = equipo2.cod_part THEN--YA TIENE CREADO UN PARTIDO. se recupera el codigo de partido para buscar el codigo de ciudad
        flagw :=1;
        OPEN cursorr FOR
        select * from partido where CODIGO = equipo1.cod_part;
        mess :='SI';
      END IF;
      
      IF flagw=1 THEN
        EXIT;
      END IF;
      
    END LOOP;
    
    IF flagw=1 THEN
      EXIT;
    END IF;
    
  END LOOP;
  IF flagw=0 THEN--SI NO LO ENCONTRO
    mess :='NO';
  END IF;
  
END get_info_partido;
-- ESTE METODO ME RETORNA TODA LA INFORMACION DE LA CIUDAD A LA QUE EL PARTIDO FUE ASIGNADO,(IGUAL SOLO PARA FINES DE MOSTRARLO EN PANTALLA 
--  Y MODIFICAR O ELIMINAR)
create or replace PROCEDURE get_ciudad_partido(codciu IN INTEGER,cursorr OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN cursorr for
    select * from ciudad where COD_CIUDAD=codciu;
END get_ciudad_partido;
-- ESTE METODO ME RETORNA TODA LA INFORMACION DE LOS MARCADORES PARA UN PARTIDO ASOCIADO,(IGUAL SOLO PARA FINES
--  DE MOSTRARLO EN PANTALLA   Y MODIFICAR O ELIMINAR)
create or replace PROCEDURE get_marcador_partido_a(codparti IN INTEGER,cursorr OUT SYS_REFCURSOR)
AS
BEGIN
  open cursorr for
    select goles,cod_equipo from marcador where tipo='A' and cod_part=codparti;
END;




create or replace PROCEDURE set_partido(codeq1 IN INTEGER,codeq2 IN INTEGER,goleq1 IN INTEGER,goleq2 IN INTEGER,grr IN char,fecc IN DATE,codciudad IN integer,flag IN char,codu IN INTEGER, mess OUT VARCHAR2)
AS

cursor cursor1(codeqq1 INTEGER) is select cod_equipo,cod_part from rivales where cod_equipo=codeqq1;  
cursor cursor2(codeqq2 INTEGER) is select cod_equipo,cod_part from rivales where cod_equipo=codeqq2;
flagw INTEGER;
type ididp IS RECORD(codigooo PARTIDO.CODIGO%TYPE);
idparti ididp;
BEGIN
  flagw :=0;
  FOR equipo1 IN cursor1(codeq1)
  LOOP
    FOR equipo2 IN cursor2(codeq2)
    LOOP
      --dbms_output.put_line(equipo1.cod_part);
      IF equipo1.cod_part = equipo2.cod_part THEN--YA TIENE CREADO UN PARTIDO
        flagw :=1;
        IF flag='G' THEN--SE TIENE QUE HACER UN UPDATE
                update marcador
                set goles=goleq1
                WHERE cod_equipo=codeq1 and tipo='A' and cod_part=equipo1.cod_part;
            
                update marcador
                set goles=goleq2
                WHERE cod_equipo=codeq2 and tipo='A' and cod_part=equipo2.cod_part;
            
                update partido
                set fecha = fecc,hora_inicio=fecc,cod_ciudad=codciudad
                where codigo = equipo1.cod_part;
                exit;
                mess :='SE HISO LA ACTUALIZACION DEL MARCADOR';
                
        ELSIF flag='E' THEN-- SE TIENE QUE UN DELETE
                DELETE FROM marcador mar  where mar.cod_part = equipo1.cod_part;
                DELETE FROM rivales riv where riv.cod_part = equipo1.cod_part;
                delete from partido  where codigo = equipo1.cod_part;
                mess :='SE ELIMINO EL MARCADOR';
                exit;
        ELSE
          mess :='FLAG INCORRECTA';
          EXIT;
          
        END IF;
        
      
      END IF;
      
    END LOOP;
    IF flagw=1 THEN
      EXIT;
    END IF;
  END LOOP;
  IF flagw=0 THEN--SI NO LO ENCONTRO
    IF flag='G' THEN--NO EXISTE EN RIVALES ASI QUE ES LA PRIMERA VES QUE SE CREA ESE MARCADOR
      insert into partido(fecha,hora_inicio,cod_ciudad)values(fecc,fecc,codciudad)returning codigo into idparti.codigooo;
      insert into rivales(cod_equipo,cod_part)values(codeq1,idparti.codigooo);
      insert into rivales(cod_equipo,cod_part)values(codeq2,idparti.codigooo);
      insert into marcador(goles,cod_equipo,cod_part,tipo,cod_usuario)values(goleq1,codeq1,idparti.codigooo,'A',codu);
      insert into marcador(goles,cod_equipo,cod_part,tipo,cod_usuario)values(goleq2,codeq2,idparti.codigooo,'A',codu);
      mess :='SE CREO EL PARTIDO POR PRIMERA VEZ';
    ELSIF flag='E' THEN
      mess :='NO SE PUEDE ELIMINAR UN PARTIDO O MARCADOR QUE NO SE A CREADO';
    ELSE
      mess :='FLAG INCORRECTA';
    
      
    END IF;
  END IF;
  
END set_partido;




-------------------------------CRUD SELECCIONES------------------
CREATE OR REPLACE PROCEDURE get_PaisesDisponibles(cursor1 OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN cursor1 FOR
  SELECT pais.cod_pais,pais.nombre FROM pais
  WHERE pais.cod_pais NOT IN(SELECT equipo.COD_PAIS FROM equipo);
  
END get_PaisesDisponibles;


create or replace PROCEDURE set_Equipo(direc IN VARCHAR2,codpais IN SMALLINT,grrr char,mess OUT VARCHAR2)
AS
  cantidad integer;
  cursor1 SYS_REFCURSOR;
BEGIN
  OPEN cursor1 FOR
 Select sumass
  FROM
  (
    select eq.grupo, count(*) as sumass from equipo eq
    where eq.grupo=grrr
    group by eq.grupo
  );
  
  
  IF cursor1 IS NOT NULL THEN
    Select sumass INTO cantidad
  FROM
  (
    select eq.grupo, count(*) as sumass from equipo eq
    where eq.grupo=grrr
    group by eq.grupo
  );
  
    IF cantidad<4 THEN
        mess := grrr ||' SE INGRESARA PAIS,GRUPO NO LLENO';
        INSERT INTO equipo(director,cod_pais,grupo)values(direc,codpais,grrr);
    ELSE
      mess := grrr ||' NO SE INGRESARA PAIS,GRUPO LLENO';
    END IF;
  
  ELSE
    mess := grrr ||' SE INGRESARA PAIS,GRUPO NO INICIALIZADO';
    INSERT INTO equipo(director,cod_pais,grupo)values(direc,codpais,grrr);
      
  END IF;  
END set_Equipo;
----------------------CRUD PAISES----------------------------------
create or replace PROCEDURE set_pais(nom IN varchar2,codconf IN integer,mess OUT varchar2)
AS
BEGIN
   
      INSERT INTO pais(nombre,cod_conf)VALUES(nom,codconf);
      mess := nom||'PAIS CREADO SATISFACTORIAMENTE';
  
  commit;
END set_pais;

--DEVULVE TODOS LOS PAISES CON NOMBRE Y CODIGO.
create or replace PROCEDURE get_paises(
  paises OUT SYS_REFCURSOR
)
IS 
BEGIN
  OPEN paises FOR
    SELECT P.cod_pais,P.nombre
    FROM pais P
    ORDER BY P.nombre;
END;



CREATE OR REPLACE PROCEDURE get_infopais(codp IN integer,cursor1 OUT SYS_REFCURSOR)
AS
BEGIN
  open cursor1 FOR
    select * from pais where cod_pais=codp;
END;

CREATE OR REPLACE PROCEDURE E_pais(codp IN integer)
AS
BEGIN
  DELETE FROM pais where cod_pais=codp;
END;

  CREATE OR REPLACE PROCEDURE M_pais(codp IN integer,nom IN varchar,codconfe IN integer)
  AS
  BEGIN
    UPDATE pais
    SET nombre=nom,cod_conf=codconfe
    WHERE cod_pais=codp;
  END;


--------------------------------------CRUD Arbitros-----------------------

create or replace procedure set_arbitro(nom IN varchar,codp IN integer)
AS
BEGIN
  INSERT INTO arbitro(nombre,cod_pais)values(nom,codp);
  commit;
END set_arbitro;

create or replace procedure E_arbitro(coda IN INTEGER)
AS
BEGIN
  DELETE FROM asig WHERE COD_ARB = coda;
  DELETE FROM arbitro where COD_ARB=coda;
  commit;
END E_arbitro;

create or replace procedure M_arbitro(coda IN integer,nom IN varchar,codp IN integer)
AS
BEGIN
  UPDATE arbitro set nombre=nom,COD_PAIS=codp
  WHERE COD_ARB = coda;
  commit;
END M_arbitro;


CREATE OR REPLACE PROCEDURE get_Arbitros(cursor1 OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN cursor1 FOR
  SELECT * from arbitro;
END get_Arbitros;

CREATE OR REPLACE PROCEDURE get_Arbitro(cursor1 OUT SYS_REFCURSOR,coda IN INTEGER)
AS
BEGIN
  OPEN cursor1 FOR
  SELECT * from arbitro where cod_arb=coda;
END get_Arbitro;

--------------------------CRUD JUJADORES----------------------------




-------------------CRUD CIUDADES--------------
create or replace procedure get_ciudades(cursor1 OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN cursor1 for
    SELECT * from ciudad;
END get_ciudades;
commit;


-------------------------REPORTES------------------------------------------------
CREATE OR REPLACE PROCEDURE reporte_1(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
    SELECT COD_CONF, ACRONIMO, SELECCIONES  
    FROM
    (
    SELECT P.COD_CONF,C.ACRONIMO ,COUNT(*) AS SELECCIONES
    FROM equipo E, pais P, confederacion C
    WHERE P.COD_PAIS = E.COD_PAIS AND P.COD_CONF = C.COD 
    GROUP BY P.COD_CONF, C.ACRONIMO
    ORDER BY COUNT(*) DESC) R
    WHERE ROWNUM =1;
END;

CREATE OR REPLACE PROCEDURE reporte_2(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
  SELECT P.COD_CONF,C.ACRONIMO ,COUNT(*) AS SELECCIONES
  FROM equipo E, pais P, confederacion C
  WHERE P.COD_PAIS = E.COD_PAIS AND P.COD_CONF = C.COD 
  GROUP BY P.COD_CONF, C.ACRONIMO
  ORDER BY COUNT(*) DESC;
END;

CREATE OR REPLACE PROCEDURE reporte_3(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
  SELECT P.COD_CONF, C.ACRONIMO, COUNT(*) AS ARBITROS
  FROM arbitro A, pais P, confederacion C 
  WHERE P.COD_PAIS = A.COD_PAIS AND C.COD =  P.COD_CONF
  GROUP BY  P.COD_CONF, C.ACRONIMO
  ORDER BY COUNT(*);
END;

CREATE OR REPLACE PROCEDURE reporte_4(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
    SELECT A.COD_ARB, A.NOMBRE, COUNT(*) AS CENTRAL 
    FROM ARBITRO A, ASIG S
    WHERE A.COD_ARB = S.COD_ARB AND S.POSICION = 'CENTRAL'--verificar como estan guardados los datos
    GROUP BY A.COD_ARB, A.NOMBRE
    ORDER BY COUNT(*);
END;

CREATE OR REPLACE PROCEDURE reporte_5(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
  SELECT S.COD_EQUIPO, S.NOMBRE, S.P_GANADOS 
  FROM
  (SELECT E.COD_EQUIPO , P.NOMBRE,
  SUM(CASE WHEN E.COD_EQUIPO= R.EQ1 AND R.G1 > R.G2 THEN 1 ELSE 0 END ) AS P_GANADOS
  FROM(
    SELECT M1.COD_PART, M1.COD_EQUIPO AS EQ1, M1.GOLES AS G1, 
    M2.COD_EQUIPO AS EQ2 , M2.GOLES AS G2
    FROM MARCADOR M1 , MARCADOR M2
    WHERE M1.COD_PART = M1.COD_PART AND M2.TIPO = 'A' AND M2.TIPO = M1.TIPO
    AND M1.COD_PART = M2.COD_PART AND M1.COD_EQUIPO <> M2.COD_EQUIPO)R
    , PAIS P, EQUIPO E
    WHERE P.COD_PAIS = E.COD_PAIS AND R.EQ1 = E.COD_EQUIPO
    GROUP BY E.COD_EQUIPO, P.NOMBRE) S
    WHERE ROWNUM <6
    ORDER BY S.P_GANADOS DESC;
END;

CREATE OR REPLACE PROCEDURE reporte_6(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
    SELECT E.COD_EQUIPO, P.NOMBRE, 
    SUM(CASE WHEN E.COD_EQUIPO=RM.EQ1 THEN RM.G1 ELSE 0 END) AS GF ,
    SUM(CASE WHEN E.COD_EQUIPO=RM.EQ1 THEN RM.G2 ELSE 0 END) AS GC 
    FROM
    (SELECT M1.COD_PART, M1.COD_EQUIPO AS EQ1, M1.GOLES AS G1, 
    M2.COD_EQUIPO AS EQ2 , M2.GOLES AS G2
    FROM MARCADOR M1 , MARCADOR M2
    WHERE M1.COD_PART = M1.COD_PART AND M2.TIPO = 'A' AND M2.TIPO = M1.TIPO
    AND M1.COD_PART = M2.COD_PART AND M1.COD_EQUIPO <> M2.COD_EQUIPO) RM,
    EQUIPO E, PAIS P
    WHERE P.COD_PAIS = E.COD_PAIS AND RM.EQ1 = E.COD_EQUIPO
    GROUP BY P.NOMBRE, E.COD_EQUIPO
    ORDER BY GF DESC;
END;  

CREATE OR REPLACE PROCEDURE reporte_7(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
    SELECT R.COD_EQUIPO, R.NOMBRE, AVG(R.EDAD) AS PROMEDIO
    FROM (
    SELECT E.COD_EQUIPO, P.NOMBRE,TRUNC( MONTHS_BETWEEN
       (SYSDATE, J.FECHA_NAC)/12 )AS EDAD
    FROM JUGADOR J, EQUIPO E, PAIS P
    WHERE E.COD_EQUIPO = J.COD_EQUIPO AND E.COD_PAIS = P.COD_PAIS) R
    GROUP BY R.COD_EQUIPO, R.NOMBRE
    ORDER BY AVG(R.EDAD);
END;

CREATE OR REPLACE PROCEDURE reporte_8(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
    SELECT COD_EQUIPO, NOMBRE, GOLES 
    FROM (
    SELECT E.COD_EQUIPO, P.NOMBRE, SUM(M.GOLES) AS GOLES
    FROM EQUIPO E, PAIS P, MARCADOR M
    WHERE E.COD_PAIS = P.COD_PAIS AND E.COD_EQUIPO = M.COD_EQUIPO AND M.TIPO = 'A'
    GROUP BY E.COD_EQUIPO, P.NOMBRE
    ORDER BY SUM(M.GOLES) DESC) R
    WHERE ROWNUM = 1;
END;

CREATE OR REPLACE PROCEDURE reporte_9(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
    SELECT C.COD, C.ACRONIMO, SUM(M.GOLES) AS GOLES
    FROM EQUIPO E, PAIS P, MARCADOR M, CONFEDERACION  C
    WHERE E.COD_PAIS = P.COD_PAIS  AND E.COD_EQUIPO = M.COD_EQUIPO 
    AND P.COD_CONF = C.COD AND M.TIPO = 'A'
    GROUP BY C.COD, C.ACRONIMO;
END;

CREATE OR REPLACE PROCEDURE reporte_10(REPORT OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN REPORT FOR
    SELECT CODIGO, FECHA, CIUDAD, GOLES  
    FROM
    (SELECT P.CODIGO, P.FECHA, C.NOMBRE AS CIUDAD, SUM(M.GOLES) AS GOLES
    FROM PARTIDO P, MARCADOR M, CIUDAD C
    WHERE P.CODIGO = M.COD_PART AND P.COD_CIUDAD = C.COD_CIUDAD AND M.TIPO = 'A'
    GROUP BY P.CODIGO, P.FECHA, C.NOMBRE) R
    WHERE ROWNUM < 6;
END;

---------REPORTE 11(PROMEDIO DE EDAD DDE LOS USUARIOS POR PAIS)
CREATE OR REPLACE PROCEDURE reporte_11(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
    SELECT PA.nombre,trunc(AVG(trunc((TRUNC (SYSDATE) - U.fecha_nac)/365))) as edad 
    FROM usuario U INNER JOIN pais PA ON U.cod_pais=PA.COD_PAIS
    group by PA.nombre;
END;

-- REPORTE 12 (top 10 de las personas con mas punteos)
CREATE OR REPLACE PROCEDURE reporte_12(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
    SELECT cod_usuario,nombre,puntos FROM usuario
	WHERE ROWNUM<11
	ORDER BY puntos DESC;
END;

-- REPORTE 13(ciudad con mayor cantidad de partidos asignados)
CREATE OR REPLACE PROCEDURE reporte_13(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
	select * from
	(
		select CI.cod_ciudad as cod_ciudad,Ci.NOMBRE as nombre,count(PAR.codigo) as conteo
		FROM ciudad CI INNER JOIN partido PAR ON PAR.COD_CIUDAD=CI.COD_CIUDAD
		GROUP BY CI.COD_CIUDAD,CI.NOMBRE
		order by conteo DESC
	)
	WHERE ROWNUM<2;
END;

--REPORTE 14 (puntos por edad)
CREATE OR REPLACE PROCEDURE reporte_14(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
	select TA.edad,SUM(TA.puntos) as puntos from
	(
		select trunc((TRUNC (SYSDATE) - U.fecha_nac)/365) edad,U.puntos
		FROM usuario U 
	) TA
	group BY TA.edad;
END;
--REPORTE 15(confederacion con mas usuarios)
CREATE OR REPLACE PROCEDURE reporte_15(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
	select * FROM
	(
		select CO.COD,CO.NOMBRE,count(U.COD_USUARIO) as usuarios
		from confederacion CO INNER JOIN pais ON CO.COD=pais.COD_CONF
		INNER JOIN usuario U ON pais.COD_PAIS=U.COD_PAIS

		GROUP BY CO.COD,CO.NOMBRE

		ORDER BY usuarios DESC

	)
	where ROWNUM <2;
END;

--REPORTE 16(total de usuarios que pagaron por las quinielas)
CREATE OR REPLACE PROCEDURE reporte_16(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
	select count(usuario.cod_usuario) as Usuarios_que_pagaron
	FROM usuario where PAGO='S';
END;
----REPORTE 17(listado de usuarios que no pagaron)
CREATE OR REPLACE PROCEDURE reporte_17(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
	select usuario.cod_usuario,usuario.nombre from usuario where pago='N'and TIPO<>'A';
END;
--REPORTE 18(confederacion con mayor numero de usuarios que pagaron)
CREATE OR REPLACE PROCEDURE reporte_18(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
	select * FROM
	(
		select CO.COD,CO.NOMBRE,count(U.cod_usuario) as cantidad
		FROM confederacion CO INNER JOIN pais ON pais.COD_CONF=CO.COD
  		INNER JOIN usuario U ON U.COD_PAIS = pais.COD_PAIS
		WHERE U.PAGO='S'
		GROUP BY CO.COD,CO.NOMBRE
		ORDER BY cantidad DESC
	) TA
	WHERE ROWNUM<2;
END;
--REPORTE 19(equipo con menos goles en contra)
CREATE OR REPLACE PROCEDURE reporte_19(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
	select * from
	(
		SELECT E.COD_EQUIPO, P.NOMBRE, 
		SUM(CASE WHEN E.COD_EQUIPO=RM.EQ1 THEN RM.G2 ELSE 0 END) AS GC 
		FROM
		(
			SELECT M1.COD_PART, M1.COD_EQUIPO AS EQ1, M1.GOLES AS G1, 
			M2.COD_EQUIPO AS EQ2 , M2.GOLES AS G2
			FROM MARCADOR M1 , MARCADOR M2
			WHERE M1.COD_PART = M1.COD_PART AND M2.TIPO = 'A' AND M2.TIPO = M1.TIPO
			AND M1.COD_PART = M2.COD_PART AND M1.COD_EQUIPO <> M2.COD_EQUIPO
		) RM,EQUIPO E, PAIS P
		WHERE P.COD_PAIS = E.COD_PAIS AND RM.EQ1 = E.COD_EQUIPO
		GROUP BY P.NOMBRE, E.COD_EQUIPO
		ORDER BY GC ASC
	) TA
	WHERE ROWNUM<2;
END;

--REPORTE 20(equipo mas goleado)
CREATE OR REPLACE PROCEDURE reporte_20(REPORT OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN REPORT FOR
	select * from
	(
		SELECT E.COD_EQUIPO, P.NOMBRE, 
		SUM(CASE WHEN E.COD_EQUIPO=RM.EQ1 THEN RM.G2 ELSE 0 END) AS GC  
		FROM
		(
			SELECT M1.COD_PART, M1.COD_EQUIPO AS EQ1, M1.GOLES AS G1, 
			M2.COD_EQUIPO AS EQ2 , M2.GOLES AS G2
			FROM MARCADOR M1 , MARCADOR M2
			WHERE M1.COD_PART = M1.COD_PART AND M2.TIPO = 'A' AND M2.TIPO = M1.TIPO
			AND M1.COD_PART = M2.COD_PART AND M1.COD_EQUIPO <> M2.COD_EQUIPO
		) RM,EQUIPO E, PAIS P
		WHERE P.COD_PAIS = E.COD_PAIS AND RM.EQ1 = E.COD_EQUIPO
		GROUP BY P.NOMBRE, E.COD_EQUIPO
		ORDER BY GC DESC
	) TA
	WHERE ROWNUM<2;
END;



