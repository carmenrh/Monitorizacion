package trabajo.master.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import trabajo.master.dto.ErrorDetails;
import trabajo.master.vo.GeoPointVo;
import trabajo.master.vo.ModeloVo;

/**
 * The Class Validaciones.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
@Component
@Slf4j
public class Validador {

  /**
   * Valida obligatorio, comprueba si el dato introducido es nulo o vacio.
   *
   * @param dato
   *          the dato
   * @return true, if successful
   */
  private boolean validaObligatorio(String dato) {
    return !StringUtils.isEmpty(dato);
  }

  /**
   * Valida coordenadas, comprueba que las coordenadas estén escritas con puntos.
   *
   * @param ubicacion
   *          the ubicacion,
   * @return true, if successful
   */
  private boolean validaCoordenadas(GeoPointVo ubicacion) {
    return (ubicacion.getLat().contains(".") && ubicacion.getLon().contains("."));
  }

  /**
   * Validaciones de obligatoriedad de datos y formación correcta de los mismos.
   *
   * @param modelo
   *          the modelo
   * @return true, if successful
   * @throws NotValidDataException
   *           the not valid data exception
   * @throws NotValidNumberException
   *           the not valid number exception
   */
  public List<ErrorDetails> validaciones(ModeloVo modelo) {
    List<ErrorDetails> listaErrores = new ArrayList<ErrorDetails>();
    if (!validaObligatorio(modelo.getTipo())) {
      listaErrores.add(new ErrorDetails(Constantes.TIPO_SENSOR, Constantes.CAMPO_OBLIGATORIO));
    }
    if (!validaObligatorio(modelo.getModelo())) {
      listaErrores.add(new ErrorDetails(Constantes.MODELO_SENSOR, Constantes.CAMPO_OBLIGATORIO));
    }
    if (!validaObligatorio(modelo.getValorActual())) {
      listaErrores.add(new ErrorDetails(Constantes.VALOR_ACTUAL, Constantes.CAMPO_OBLIGATORIO));
    }
    if (!validaObligatorio(modelo.getLocalidad())) {
      listaErrores.add(new ErrorDetails(Constantes.LOCALIDAD, Constantes.CAMPO_OBLIGATORIO));
    }
    if (!validaNumero(modelo.getValorActual())) {
      listaErrores.add(new ErrorDetails(Constantes.VALOR_ACTUAL + ": " + modelo.getValorActual(),
          Constantes.NO_REAL));
    }
    if (!validaObligatorio(modelo.getUbicacion().getLat())) {
      listaErrores.add(new ErrorDetails(Constantes.LATITUD, Constantes.CAMPO_OBLIGATORIO));
    }
    if (!validaObligatorio(modelo.getUbicacion().getLon())) {
      listaErrores.add(new ErrorDetails(Constantes.LONGITUD, Constantes.CAMPO_OBLIGATORIO));
    }
    if (!validaObligatorio(modelo.getFechaMedida())) {
      listaErrores.add(new ErrorDetails(Constantes.FECHA_MEDIDA, Constantes.CAMPO_OBLIGATORIO));
    }
    if (!validaCoordenadas(modelo.getUbicacion())) {
      listaErrores.add(new ErrorDetails(Constantes.UBICACION, Constantes.PUNTOS_NO_COMAS));
    }
    if (!validaFecha(modelo.getFechaMedida())) {
      listaErrores.add(new ErrorDetails(Constantes.FECHA_MEDIDA + ": " + modelo.getFechaMedida(),
          Constantes.BAD_FORMAT_FECHA));
    }
    return listaErrores;
  }

  /**
   * Valida numero, comprueba si el dato introducido es un número real.
   *
   * @param numero
   *          the numero
   * @return the string
   * @throws NotValidNumberException
   *           the not valid number exception
   */
  private boolean validaNumero(String numero) {
    try {
      Float.parseFloat(numero);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Valida si la fecha cumple el patrón "yyyy-mm-dd'T'hh:mm:ss.SSSZ".
   *
   * @param fecha
   *          the fecha
   * @return true, if successful
   */
  private boolean validaFecha(String fecha) {
    try {
      DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-mm-dd'T'HH:mm:ss.SSSZ");
      dtf.parseDateTime(fecha);
    } catch (UnsupportedOperationException e) {
      log.debug(Constantes.FECHA_MEDIDA + ": " + fecha + Constantes.BAD_FORMAT_FECHA);
      return false;
    } catch (IllegalArgumentException e) {
      log.debug(Constantes.FECHA_MEDIDA + ": " + fecha + Constantes.BAD_FORMAT_FECHA);
      return false;
    }
    return true;
  }

}
