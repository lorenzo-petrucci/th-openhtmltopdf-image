package org.example;

import org.junit.Before;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Map;

public class ImagePdfRendererTest {
    ImagePdfRenderer renderer;

    @Before
    public void setup() {
        renderer = new ImagePdfRenderer(
                "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/template",
                "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/out"
        );
        //FIXME: spring test for environment vars?
    }

    @Test
    public void pdfGenerationFromTemplateWithImageTest() throws IOException {
        final var ctx = new Context();
        final var variables = Map.of("key", (Object) "value");
        ctx.setVariables(variables);

        final var engine = new TemplateEngine();

        renderer.createPDF(engine, ctx);
    }
}