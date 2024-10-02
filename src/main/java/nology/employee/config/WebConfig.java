package nology.employee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  public void addCorsMappings(CorsRegistry registry) {
    String[] allowedOrigins = { "http://localhost:5173/", "http://127.0.0.1:5173/",
        "https://polite-forest-0e22d7a00.5.azurestaticapps.net/" };
    registry.addMapping("/**").allowedOrigins(allowedOrigins).allowedMethods("*").allowedHeaders("*");
  }
}
