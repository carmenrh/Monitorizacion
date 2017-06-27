package trabajo.master;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Constant log.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
@Slf4j
@SpringBootApplication
public class App {

  /**
   * Instantiates a new app.
   */
  public App() {
    // default implementation
  }

  /**
   * The main method.
   *
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {

    log.debug("Comienzo de la aplicación");
    SpringApplication.run(App.class, args);
    log.debug("Aplicación arrancada");
  }
}
