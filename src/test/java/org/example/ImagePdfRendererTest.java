package org.example;

import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Map;

public class ImagePdfRendererTest {
    @Test
    public void pdfGenerationFromTemplateWithImageTest() throws IOException {
        final var ctx = new Context();
        final var variables = Map.of("key", (Object) "value");
        ctx.setVariables(variables);

        final var engine = new TemplateEngine();
        final var renderer = new ImagePdfRenderer();

        renderer.createPDF(engine, ctx);
    }
}