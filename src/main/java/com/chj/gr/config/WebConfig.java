package com.chj.gr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.chj.gr.properties.SwaggerParamsProperties;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	/** "gr-conf-swagger-aggregator". **/
	private SwaggerParamsProperties swaggerParamsProperties;
	
    public WebConfig(SwaggerParamsProperties swaggerParamsProperties) {
		this.swaggerParamsProperties = swaggerParamsProperties;
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(swaggerParamsProperties.getAggregator().getCors().getUri())
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    	
        /**
         * @TODO Didn't work YET depuis gr-conf-swagger-aggregator..
         */
        // Configuration explicite pour les endpoints Actuator
//        registry.addMapping("/actuator/**")
//                .allowedOrigins(swaggerParamsProperties.getAggregator().getCors().getUri())
//                .allowedMethods("GET", "POST", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
    }
}
