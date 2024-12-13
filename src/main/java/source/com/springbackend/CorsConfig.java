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
     * origins, methods.
     * Mapping to /** so every path will be mapped with
     * those CORS regulations.
     * @return Adds the WebMvcConfigurer to Spring Application
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8085")
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
