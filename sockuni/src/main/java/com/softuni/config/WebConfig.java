package com.softuni.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by niakoi on 25/7/16.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Module datatypeHibernateModule() {
        Hibernate4Module module = new Hibernate4Module();
        module.configure(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
        return module;
    }
    @Bean
    public Module jodaHibernateModule() {
        return new JodaModule();
    }
}
