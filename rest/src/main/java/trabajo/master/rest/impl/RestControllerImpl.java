package trabajo.master.rest.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import trabajo.master.dto.ModeloDto;
import trabajo.master.exception.NotFoundException;
import trabajo.master.exception.NotValidDataException;
import trabajo.master.rest.Controller;
import trabajo.master.servicio.Servicio;
import trabajo.master.vo.ModeloVo;

// TODO: Auto-generated Javadoc
/**
 * The Class RestControllerImpl.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 */

/** The Constant log. */

/** The Constant log. */
@Slf4j
@RestController
public class RestControllerImpl implements Controller {

  /** The servicio. */
  private Servicio servicio;

  /**
   * Utilidad para la transfomacion de objetos.
   */
  private Mapper dozerMapper;

  /**
   * Instantiates a new rest controller impl.
   *
   * @param servicio
   *          the servicio
   * @param dozerMapper
   *          the dozer mapper
   */
  @Autowired
  public RestControllerImpl(Servicio servicio, Mapper dozerMapper) {
    this.servicio = servicio;
    this.dozerMapper = dozerMapper;
  }

  /**
   * Registrar medición, para ello indexa el dato en ElasticSearch tras
   * comprobar la validez de los datos introducidos y lanza excepciones si no
   * fuesen válidos.
   *
   * @param modelo
   *          the modelo
   * @return the string
   * @throws NotValidDataException
   *           the not valid data exception
   * @throws JsonProcessingException
   *           the json processing exception
   * @see trabajo.master.rest.Controller#registrarMedicion(trabajo.master.dto.
   *      ModeloDto)
   */
  @RequestMapping(path = "/api/mediciones", method = RequestMethod.POST,
      consumes = "application/json", produces = "application/json")
  public String registrarMedicion(@RequestBody(required = true) ModeloDto modelo)
      throws NotValidDataException, JsonProcessingException {
    log.debug("El modelo dto recibido es: " + modelo);
    ModeloVo modeloVo = dozerMapper.map(modelo, ModeloVo.class);
    log.debug("El modelo tras pasar por dozzerMapper es: " + modeloVo);
    return servicio.indexarMedida(modeloVo);
  }

  /**
   * Buscar Mediciones, busca todas las mediciones registradas en ElasticSearch
   * en el índice indicado, si no hubiese datos o fallase el proceso lanza
   * excepción.
   *
   * @return the string
   * @throws JsonProcessingException
   *           the json processing exception
   * @throws NotValidDataException
   *           the not valid data exception
   * @throws NotFoundException
   *           the not found exception
   * @see trabajo.master.rest.Controller#buscarMediciones()
   */
  @RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json")
  public String buscarMediciones()
      throws JsonProcessingException, NotValidDataException, NotFoundException {
    return servicio.buscarMediciones();
  }

  /**
   * Buscar Medición, busca una medición en concreto a través del id de la misma,
   *  dicho parámetro es requerido. Si no encuentra el dato pedido se lanzará excepción,
   *  pero en caso afirmativo se devuelve la medición en formato JSON.
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
   * @see trabajo.master.rest.Controller#buscarMedicion(java.lang.String)
   */
  @RequestMapping(path = "/api/medicion", method = RequestMethod.POST,
      produces = "application/json")
  public String buscarMedicion(@RequestBody(required = true) String indice)
      throws JsonProcessingException, NotValidDataException, NotFoundException {
    return servicio.buscarMedicion(indice);
  }

}
