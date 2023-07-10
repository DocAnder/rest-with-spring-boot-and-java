package com.example.restwithspringbootandjava.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

/*
* Ess classe foi criada para configurar a serialização do content para yml
*
* */


public class YamlJackson2HttpMesageConverter extends AbstractJackson2HttpMessageConverter {

    public YamlJackson2HttpMesageConverter() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/x-yaml"));


    }
}
