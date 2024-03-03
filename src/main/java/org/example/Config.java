package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:/org/example/project.properties")
public class Config {
    @Autowired
    Environment env;

    @Bean
    ImagePdfRenderer renderer() {
        final String baseDir = env.getProperty("base.dir");
        ImagePdfRenderer renderer = new ImagePdfRenderer();
        renderer.setSrc(env.getProperty(baseDir + "src.in"));
        renderer.setOut(env.getProperty(baseDir + "src.out"));
        return renderer;
    }
}
