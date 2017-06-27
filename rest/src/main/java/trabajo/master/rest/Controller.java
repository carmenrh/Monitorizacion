package trabajo.master.rest;

import com.fasterxml.jackson.core.JsonProcessingException;

import trabajo.master.dto.ModeloDto;
import trabajo.master.exception.NotValidDataException;
import trabajo.master.exception.NotValidNumberException;

/**
 * The Interface Controller.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
public interface Controller {

  /**
   * Registrar medicion.
   *
   * @param modelo
   *          the modelo
   * @return the string
   * @throws NotValidDataException
   *           the not valid data exception
   * @throws JsonProcessingException
   *           the json processing exception
   * @throws NotValidNumberException
   *           the not valid number exception
   */
  String registrarMedicion(ModeloDto modelo)
      throws NotValidDataException, JsonProcessingException, NotValidNumberException;

  /**
   * Buscar mediciones.
   *
   * @return the string
   * @throws JsonProcessingException
   *           the json processing exception
   */
  String buscarMediciones() throws JsonProcessingException;

  /**
   * Buscar medicion.
   *
   * @param indice
   *          the indice
   * @return the string
   * @throws JsonProcessingException
   *           the json processing exception
   * @throws NotValidDataException
   *           the not valid data exception
   */
  String buscarMedicion(String indice)
      throws JsonProcessingException, NotValidDataException;

}
