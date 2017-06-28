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
import trabajo.master.exception.NotValidDataException;
import trabajo.master.exception.NotValidNumberException;
import trabajo.master.rest.Controller;
import trabajo.master.servicio.Servicio;
import trabajo.master.vo.ModeloVo;

/**
 * The Class RestControllerImpl.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 */

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
   * Registrar medicion.
   *
   * @param modelo          the modelo
   * @return the string
   * @throws NotValidDataException           the not valid data exception
   * @throws JsonProcessingException the json processing exception
   * @throws NotValidNumberException the not valid number exception
   * @see trabajo.master.rest.Controller#registrarMedicion(trabajo.master.dto.
   *      ModeloDto)
   */
  @RequestMapping(path = "/api/mediciones", method = RequestMethod.POST, 
      consumes = "application/json", produces = "application/json")
  public String registrarMedicion(@RequestBody(required = true) ModeloDto modelo)
      throws NotValidDataException, JsonProcessingException, NotValidNumberException {
    log.debug("El modelo dto recibido es: " + modelo);
    ModeloVo modeloVo = dozerMapper.map(modelo, ModeloVo.class);
    log.debug("El modelo tras pasar por dozzerMapper es: " + modeloVo);
    return servicio.indexarMedida(modeloVo);
  }

  /**
   * Buscar Mediciones.
   *
   * @return the string
   * @throws JsonProcessingException           the json processing exception
   * @throws NotValidDataException the not valid data exception
   * @see trabajo.master.rest.Controller#buscarMediciones()
   */
  @RequestMapping(path = "", method = RequestMethod.GET)
  public String buscarMediciones() throws JsonProcessingException, NotValidDataException {
    return servicio.buscarMediciones();
  }

  /**
   * Buscar Medición.
   *
   * @param indice          the indice
   * @return the string
   * @throws JsonProcessingException           the json processing exception
   * @throws NotValidDataException the not valid data exception
   * @see trabajo.master.rest.Controller#buscarMedicion(java.lang.String)
   */
  @RequestMapping(path = "/api/medicion", method = RequestMethod.POST)
  public String buscarMedicion(@RequestBody(required = true) String indice)
      throws JsonProcessingException, NotValidDataException {
    return servicio.buscarMedicion(indice);
  }

}
