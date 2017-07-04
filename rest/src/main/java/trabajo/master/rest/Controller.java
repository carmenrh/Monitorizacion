package trabajo.master.rest;

import com.fasterxml.jackson.core.JsonProcessingException;

import trabajo.master.dto.ModeloDto;
import trabajo.master.exception.NotFoundException;
import trabajo.master.exception.NotValidDataException;

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
   */
  String registrarMedicion(ModeloDto modelo) throws NotValidDataException, JsonProcessingException;

  /**
   * Buscar mediciones.
   *
   * @return the string
   * @throws JsonProcessingException
   *           the json processing exception
   * @throws NotValidDataException
   *           the not valid data exception
   * @throws NotFoundException
   *           the not found exception
   */
  String buscarMediciones()
      throws JsonProcessingException, NotValidDataException, NotFoundException;

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
   * @throws NotFoundException
   *           the not found exception
   */
  String buscarMedicion(String indice)
      throws JsonProcessingException, NotValidDataException, NotFoundException;

}
