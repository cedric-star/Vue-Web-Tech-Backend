package source.com.springbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 * Loading resources from user will cause
 * CORS conflicts.
 * @author Cedric Wünsch
 */
@Configuration
public class CorsConfig {

    /**
     * Description:
     * Defines allowed parameters:
     * origins, methods,
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080", "http://localhost:8081") // Ersetze dies mit der URL deiner Webseite
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
