package trabajo.master.servicio.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import trabajo.master.dto.ErrorDetails;
import trabajo.master.exception.NotFoundException;
import trabajo.master.exception.NotValidDataException;
import trabajo.master.servicio.Servicio;
import trabajo.master.utils.Validador;
import trabajo.master.vo.ModeloVo;
import trabajo.master.vo.RespuestaBusquedaTodasVo;
import trabajo.master.vo.RespuestaBusquedaVo;
import trabajo.master.vo.RespuestaIndexadoVo;

/**
 * The Class ServiceImpl.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
@Slf4j
@Service
public class ServicioImpl implements Servicio {

  /** The template. */
  private RestTemplate template;

  /** The validate. */
  private Validador validate;

  /** The url indexado. */
  @Value("${url.indexado}")
  private String urlIndexado;

  /** The url busqueda. */
  @Value("${url.busqueda}")
  private String urlBusqueda;

  /** The url busqueda. */
  @Value("${url.busqueda.medida}")
  private String urlBusquedaMedida;

  /** The username. */
  private String username = "elastic";

  /** The password. */
  private String password = "changeme";

  /**
   * Instantiates a new service impl.
   *
   * @param template
   *          the template
   * @param validate
   *          the validate
   */
  @Autowired
  public ServicioImpl(RestTemplate template, Validador validate) {
    this.template = template;
    this.validate = validate;
  }

  /**
   * Indexar medición en ElasticSearch si se cumplen las restriciones respecto a
   * los datos introducidos.
   *
   * @param modelo
   *          the modelo
   * @return the string
   * @throws NotValidDataException
   *           the not valid data exception
   * @throws JsonProcessingException
   *           the json processing exception
   * @see trabajo.master.service.Servicio#indexarMedida(trabajo.master.vo.ModeloVo)
   */
  public String indexarMedida(ModeloVo modelo)
      throws NotValidDataException, JsonProcessingException {

    List<ErrorDetails> errores = validate.validaciones(modelo);

    if (errores.isEmpty()) {
      HttpHeaders headers = creacionCabeceras();
      HttpEntity<?> httpEntity = new HttpEntity<Object>(modelo, headers);

      try {
        ResponseEntity<RespuestaIndexadoVo> result = template.exchange(urlIndexado,
            HttpMethod.POST, httpEntity, RespuestaIndexadoVo.class);

        ObjectMapper mapper = new ObjectMapper();
        String resultadoJson = mapper.writeValueAsString(result.getBody());

        log.debug(resultadoJson);
        return resultadoJson;
      } catch (RestClientException e) {
        log.error(e.getMessage());
        throw new NotValidDataException("Error al indexar datos");
      }
    } else {
      log.error("Datos no indexados", errores);
      throw new NotValidDataException("Datos no indexados", errores);
    }
  }

  /**
   * (non-Javadoc).
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
   * @see trabajo.master.servicio.Servicio#buscarMedicion(java.lang.String)
   */
  public String buscarMedicion(String indice)
      throws JsonProcessingException, NotValidDataException, NotFoundException {
    String urlMedida = "";

    urlMedida = urlBusquedaMedida + indice;

    HttpHeaders headers = creacionCabeceras();
    HttpEntity<?> entity = new HttpEntity<Object>(headers);

    try {
      ResponseEntity<RespuestaBusquedaVo> result = template.exchange(urlMedida, HttpMethod.GET,
          entity, RespuestaBusquedaVo.class);

      if (result != null) {
        ObjectMapper mapper = new ObjectMapper();
        String resultadoJson = mapper.writeValueAsString(result.getBody());

        log.debug(resultadoJson);
        return resultadoJson;
      } else {
        log.error("No hay datos para la medición " + indice);
        throw new NotFoundException("No hay datos para la medición " + indice);
      }

    } catch (RestClientException e) {
      urlMedida = "";
      log.error("Error al buscar medición. " + e.getMessage());
      throw new NotValidDataException("Error al buscar medición");
    }

  }

  /**
   * (non-Javadoc).
   *
   * @return the string
   * @throws JsonProcessingException
   *           the json processing exception
   * @throws NotValidDataException
   *           the not valid data exception
   * @throws NotFoundException
   *           the not found exception
   * @see trabajo.master.servicio.Servicio#buscarMediciones()
   */
  public String buscarMediciones()
      throws JsonProcessingException, NotValidDataException, NotFoundException {
    
    HttpHeaders headers = creacionCabeceras();
    HttpEntity<?> entity = new HttpEntity<Object>(headers);
    
    try {
      ResponseEntity<RespuestaBusquedaTodasVo> result = template.exchange(urlBusqueda,
          HttpMethod.GET, entity, RespuestaBusquedaTodasVo.class);

      if (result != null) {
        ObjectMapper mapper = new ObjectMapper();
        String resultadoJson = mapper.writeValueAsString(result.getBody());

        log.debug(resultadoJson);
        return resultadoJson;
      } else {
        log.error("No hay datos para recuperar");
        throw new NotFoundException("No hay datos para recuperar");
      }
    } catch (RestClientException e) {
      log.error("Error al buscar mediciones." + e.getMessage());
      throw new NotValidDataException("Error al buscar mediciones");
    }
  }

  /**
   * Creacion cabeceras, añade a las cabeceras http el contenttype y la
   * autenticación de la aplicación.
   *
   * @return the http headers
   */
  private HttpHeaders creacionCabeceras() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String auth = username + ":" + password;
    byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
    String authHeader = "Basic " + new String(encodedAuth);

    headers.add("Authorization", authHeader);

    return headers;
  }

}
