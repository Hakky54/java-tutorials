package nl.altindag.server.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.NameTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JacksonConfiguration {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(Jackson2ObjectMapperBuilder.json()
                .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .annotationIntrospector(ignoredAnnotationIntrospector())
                .build());
    }

    private static JacksonAnnotationIntrospector ignoredAnnotationIntrospector() {
        return new JacksonAnnotationIntrospector() {
            @Override
            public NameTransformer findUnwrappingNameTransformer(AnnotatedMember member) {
                return null;
            }

            @Override
            public Object findNamingStrategy(AnnotatedClass annotatedClass) {
                return null;
            }
        };
    }

}
