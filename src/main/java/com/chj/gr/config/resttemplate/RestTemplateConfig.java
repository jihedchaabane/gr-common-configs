package com.chj.gr.config.resttemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.properties.ServiceParamsProperties;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class RestTemplateConfig {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final ServiceParamsProperties serviceParamsProperties;

	public RestTemplateConfig(ServiceParamsProperties serviceParamsProperties) {
		super();
		this.serviceParamsProperties = serviceParamsProperties;
	}
		/**
		@TODO din't work!
		javax:
		  net:
		    ssl:
		      trustStore: classpath:ms1-truststore.jks
		      trustStorePassword: jihed1234
		@TODO din't work too!      
	    server:
  		  ssl:
    	    trustStore: classpath:ms1-truststore.jks
    		trustStorePassword: jihed1234
		
		@WORKED_FINE
	    Ou configurez programmatiquement RestTemplate pour utiliser le truststore.
		*/
	
		/**
    	@LoadBalanced
    		==> l'appel avec [https/http]://MON_SERVICE_APP_NAME
    		Il faut que : eureka.instance.preferIpAddress: true
    		
    		https:
		    		eureka:
					  instance:
					    secure-port: 8441  # Advertise HTTPS port
					    secure-port-enabled: true
					    non-secure-port-enabled: false
		 */

	@Bean("restTemplateSsl")
    @LoadBalanced
    public RestTemplate restTemplateSsl() throws Exception {
    	KeyStore trustStore = KeyStore.getInstance("JKS");
    	
    	try (FileInputStream fis = new FileInputStream(serviceParamsProperties.getTruststore().getPath())) {
    	    trustStore.load(fis, serviceParamsProperties.getTruststore().getPassword().toCharArray());
    	} catch (IOException | NoSuchAlgorithmException e) {
    	    logger.error("Failed to load truststore from system path..!", e);
    	}
        
//      trustStore.load(getClass().getResourceAsStream(
//    		serviceParamsProperties.getTruststore().getPath()), serviceParamsProperties.getTruststore().getPassword().toCharArray());

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(trustStore, null)
                .build();

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }
    
    @Bean("restTemplate")
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}