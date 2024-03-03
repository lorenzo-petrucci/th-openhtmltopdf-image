package org.example;

import org.junit.Before;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Map;

public class ImagePdfRendererTest {
    final String SRC = "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/template";
    final String OUT = "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/out";
    //FIXME: spring test for environment vars?

    ImagePdfRenderer renderer;

    @Before
    public void setup() {
        renderer = new ImagePdfRenderer(SRC, OUT);
    }

    @Test
    public void pdfGenerationFromTemplateWithImageTest() throws IOException {
        final var engine = new TemplateEngine();
        final var ctx = new Context();
        final var variables = Map.of("key", (Object) "value");
        ctx.setVariables(variables);
        renderer.createPDF(engine, ctx);
    }
}