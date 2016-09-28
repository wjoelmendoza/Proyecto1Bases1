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
    SELECT J.camiseta, J.FECHA_NAC, J.estatura, J.peso, J.nombre, J.equipo
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

--cambia la vlae del usuario
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