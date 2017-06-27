package trabajo.master.config;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Carmen Roberto Herrero
 * @version 1.0
 * 
 */
@Configuration
public class BeansConfiguration {

  /**
   * Rest template.
   *
   * @return the rest template
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  /**
   * Dozzer mapper.
   *
   * @return the mapper
   */
  @Bean(name = "dozzerMapper")
  public Mapper dozzerMapper() {

    return DozerBeanMapperSingletonWrapper.getInstance();

  }
}
