package trabajo.master.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;

import trabajo.master.exception.NotFoundException;
import trabajo.master.exception.NotValidDataException;
import trabajo.master.vo.ModeloVo;

/**
 * The Interface Service.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
public interface Servicio {

  /**
   * Indexar medida.
   *
   * @param modelo
   *          the modelo
   * @return the string
   * @throws NotValidDataException
   *           the not valid data exception
   * @throws JsonProcessingException
   *           the json processing exception
   */
  String indexarMedida(ModeloVo modelo) throws NotValidDataException, JsonProcessingException;

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
   * Buscar medida.
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
