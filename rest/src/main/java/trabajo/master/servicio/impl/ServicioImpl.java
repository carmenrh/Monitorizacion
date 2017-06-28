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

/** The Constant log. */
@Slf4j
@Service
public class ServicioImpl implements Servicio {

  /** The template. */
  private RestTemplate template;

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
   */
  @Autowired
  public ServicioImpl(RestTemplate template, Validador validate) {
    this.template = template;
    this.validate = validate;
  }

  /**
   * (non-Javadoc).
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
   * @see trabajo.master.servicio.Servicio#buscarMedicion(java.lang.String)
   */
  public String buscarMedicion(String indice)
      throws JsonProcessingException, NotValidDataException {
    String urlMedida = "";

    urlMedida = urlBusquedaMedida + indice;

    try {
      ResponseEntity<RespuestaBusquedaVo> result = template.getForEntity(urlMedida,
          RespuestaBusquedaVo.class);

      if (result != null) {
        ObjectMapper mapper = new ObjectMapper();
        String resultadoJson = mapper.writeValueAsString(result.getBody());

        log.debug(resultadoJson);
        return resultadoJson;
      } else {
        throw new NotValidDataException("No hay datos para la medición " + indice);
      }

    } catch (RestClientException e) {
      urlMedida = "";
      log.error(e.getMessage());
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
   * @see trabajo.master.servicio.Servicio#buscarMediciones()
   */
  public String buscarMediciones() throws JsonProcessingException, NotValidDataException {
    try {
      ResponseEntity<RespuestaBusquedaTodasVo> result = template.getForEntity(urlBusqueda,
          RespuestaBusquedaTodasVo.class);

      if (result != null) {
        ObjectMapper mapper = new ObjectMapper();
        String resultadoJson = mapper.writeValueAsString(result.getBody());

        log.debug(resultadoJson);
        return resultadoJson;
      } else {
        throw new NotValidDataException("No hay datos para recuperar");
      }
    } catch (RestClientException e) {
      log.error(e.getMessage());
      throw new NotValidDataException("Error al buscar mediciones");
    }
  }

  /**
   * Creacion cabeceras.
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
