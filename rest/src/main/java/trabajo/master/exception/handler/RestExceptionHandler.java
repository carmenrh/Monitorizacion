package trabajo.master.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import trabajo.master.dto.ErrorResponseDto;
import trabajo.master.exception.NotValidCordinateException;
import trabajo.master.exception.NotValidDataException;
import trabajo.master.exception.NotValidNumberException;
import trabajo.master.utils.Constantes;

/**
 * Handler para tratar las excepciones lanzadas por los servicios REST.
 *
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
@ControllerAdvice
public class RestExceptionHandler {

  /**
   * Gestiona los errores 500.
   *
   * @param exception
   *          the exception
   * @return the error response dto
   */
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorResponseDto handleRuntimeException(RuntimeException exception) {

    return new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
        exception.getMessage(), null);

  }

  /**
   * Gestiona los errores lanzados como NotValidDataException.
   *
   * @param exception
   *          the exception
   * @return the error response dto
   */
  @ExceptionHandler(NotValidDataException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponseDto handleNotValidDataException(NotValidDataException exception) {

    return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), Constantes.BAD_FORMAT,
        exception.getMessage(), exception.getErrors());

  }

  /**
   * Gestiona los errores lanzados como NotValidCordinateException.
   *
   * @param exception
   *          the exception
   * @return the error response dto
   */
  @ExceptionHandler(NotValidCordinateException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponseDto handleNotValidCordinateException(NotValidCordinateException exception) {

    return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), Constantes.BAD_FORMAT,
        exception.getMessage(), exception.getErrors());

  }

  /**
   * Gestiona los errores lanzados como NotValidCordinateException.
   *
   * @param exception
   *          the exception
   * @return the error response dto
   */
  @ExceptionHandler(NotValidNumberException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponseDto handleNotValidNumberException(NotValidNumberException exception) {

    return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), Constantes.BAD_FORMAT,
        exception.getMessage(), exception.getErrors());

  }
}
