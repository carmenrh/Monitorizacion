package trabajo.master.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import trabajo.master.exception.NotValidCordinateException;
import trabajo.master.exception.NotValidDataException;
import trabajo.master.exception.NotValidNumberException;
import trabajo.master.vo.GeoPointVo;
import trabajo.master.vo.ModeloVo;

/**
 * The Class Validaciones.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 */

/** The Constant log. */
@Slf4j
@Component
public class Validador {

  /**
   * Valida obligatorio.
   *
   * @param dato
   *          the dato
   * @param tipoDato
   *          the tipo dato
   * @return the string
   * @throws NotValidDataException
   *           the not valid data exception
   */
  private boolean validaObligatorio(String dato, String tipoDato)
      throws NotValidDataException {
    return !StringUtils.isEmpty(dato);
  }

  /**
   * Valida coordenadas.
   *
   * @param ubicacion
   *          the ubicacion,
   * @return the geo point vo
   * @throws NotValidCordinateException
   *           the not valid cordinate exception
   */
  private GeoPointVo validaCoordenadas(GeoPointVo ubicacion)
      throws NotValidCordinateException {
    if (StringUtils.isEmpty(ubicacion.getLat())
        && StringUtils.isEmpty(ubicacion.getLon())) {
      throw new NotValidCordinateException("La ubicación debe poseer dos coordenadas. ");
    } else if (ubicacion.getLat().contains(",") || ubicacion.getLon().contains(",")) {
      throw new NotValidCordinateException(
          "Las coordenadas se esccriben con punto no con coma. ");
    } else {
      return ubicacion;
    }
  }

  /**
   * Validaciones de obligatoriedad de datos y formación correcta de los mismos.
   *
   * @param modelo          the modelo
   * @return true, if successful
   * @throws NotValidDataException           the not valid data exception
   * @throws NotValidNumberException the not valid number exception
   */
  //boolean cambiar por lista errores
  public List<String> validaciones(ModeloVo modelo)
      throws NotValidDataException, NotValidNumberException {
    List<String> listaE = new ArrayList<String>();
//    StringBuilder mensaje = new StringBuilder();
      if(!validaObligatorio(modelo.getTipo(), "El tipo de sensor")){
        listaE.add("Sensor");
      }
//   
//      log.debug(mensaje.toString());
//      mensaje.append(e.getMessage());
//    }
//    try {
//      validaObligatorio(modelo.getModelo(), "El modelo del sensor");
//    } catch (NotValidDataException e) {
//      log.debug(mensaje.toString());
//      mensaje.append(e.getMessage());
//    }
//    try {
//      validaObligatorio(modelo.getValorActual(),
//          "El valor actual registrado por el sensor");
//    } catch (NotValidDataException e) {
//      log.debug(e.getMessage());
//      mensaje.append(e.getMessage());
//    }
//    try {
//      validaObligatorio(modelo.getLocalidad(), "La localidad del sensor");
//    } catch (NotValidDataException e) {
//      log.debug(e.getMessage());
//      mensaje.append(e.getMessage());
//    }
//    try {
//      validaNumero(modelo.getValorActual());
//    } catch (NotValidNumberException e) {
//      log.debug(e.getMessage());
//      mensaje.append(e.getMessage());
//    }
//    try {
//      validaObligatorio(modelo.getUbicacion().getLat(),
//          "La latitud de la ubicación del sensor");
//    } catch (NotValidDataException e) {
//      log.debug(mensaje.toString());
//      mensaje.append(e.getMessage());
//    }
//    try {
//      validaObligatorio(modelo.getUbicacion().getLon(),
//          "La longitud de la ubicación del sensor");
//    } catch (NotValidDataException e) {
//      log.error(e.getCause().toString());
//      mensaje.append(e.getMessage());
//    }
//    try {
//      validaObligatorio(modelo.getFechaMedida(), "La fecha de medida del sensor");
//    } catch (NotValidDataException e) {
//      log.debug(e.getMessage());
//      mensaje.append(e.getMessage());
//    }
//    try {
//      validaCoordenadas(modelo.getUbicacion());
//    } catch (NotValidCordinateException e) {
//      log.debug(e.getMessage());
//      mensaje.append(e.getMessage());
//    }
//
//    if (!StringUtils.isEmpty(mensaje.toString())) {
//      log.debug(mensaje.toString());
//      throw new NotValidDataException(mensaje.toString());
//    }

    return listaE;

  }

  /**
   * Valida numero.
   *
   * @param numero the numero
   * @return the string
   * @throws NotValidNumberException the not valid number exception
   */
  private String validaNumero(String numero) throws NotValidNumberException {
    try {
      Integer.parseInt(numero);
      return numero;
    } catch (NumberFormatException e) {
      throw new NotValidNumberException(e.getMessage());
    }
  }

}
