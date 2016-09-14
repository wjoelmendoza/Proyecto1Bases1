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