package br.com.invistaix.InvistaIX.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI CustomizacaoSwagger() {
        return new OpenAPI()
                .info(new Info()
                        .title("InvistaIX - Gerenciamento de imóveis")
                        .version("1.0")
                        .description("Documentação da API de gerenciamento de imoveis feitas para a empresa InvistaIX"));
    }
}