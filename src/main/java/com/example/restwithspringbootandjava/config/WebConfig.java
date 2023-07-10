package com.example.restwithspringbootandjava.config;

import com.example.restwithspringbootandjava.serialization.converter.YamlJackson2HttpMesageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/*Essa anotation configuration diz ao springboot que, antes de subir a api, ele deve verificar nesse
* arquivo as configurações sobre o comportamento da aplicação*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
    * Para personalizar o tipo de conteudo que nossa api pode fornecer (json, xml, etc) podemos
    * configurar o tratamento da requisição e fornecer o tipo de arquivo solicitado.
    * Para isso, criamos a classe de configuração, que implementa o WebMvcConfigurer, que contem
    * o metodo apto a configurar o content negotiation.
    *
    * Antes isso éra feito via EXTENSION, digitando ao final do link o tipo de extensão desejada,
    * como: .xml, .json, etc. Exemplo: http://localhost:8080/api/person/v1.xml
    * Isso passou a ser DEPRECATED no springboot, que passou a receber o tipo de arquivo pelo
    * query param ou via header param. Exemplo: http://localhost:8080/api/person/v1?mediaType=xml
    *
    * No metodo abaixo sobrescrito, foi realizada a configuração necessária.
    * */


    //A constante foi criada para ser capaz de devolver YML
    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        /*
        CONFIGURAÇÃO PARA RECEBER O PARAMETRO VIA QUERY PARAM

        configurer.favorParameter(true)//seta que o configurer aceita parametros
                .parameterName("mediaType")//seta o nome do parametro
                .ignoreAcceptHeader(true)//seta que irá ignorar parametros no header
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)//seta o content padrão
                .mediaType("json",MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);

         */

        // CONFIGURAÇÃO PARA RECEBER VIA HEADER PARAM

        configurer.favorParameter(false)//seta que o configurer NÃO aceita parametros
                .ignoreAcceptHeader(false)//seta que NÃO irá ignorar parametros no header
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)//seta o content padrão
                .mediaType("json",MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);



    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMesageConverter());
    }
}
