package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.*;
import java.util.Map;

public class ThymeleafPdfRendererTest {
    final String DIR = "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/";
    final String SRC = "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/template.html";
    final String DST = "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/out.pdf";
    //FIXME: spring test for properties?


    @Test
    public void pdfGenerationFromTemplate() throws IOException {
        final var is = new FileInputStream(SRC);
        final var os = new FileOutputStream(DST);
        final var ctx = new Context();
        ctx.setVariables(Map.of("key", "value"));

        final var renderer = new ThymeleafPdfRenderer(new TemplateEngine());
        renderer.setSource(DIR);
        renderer.setInputStream(is);
        renderer.setOutputStream(os);

        renderer.createPdf(ctx);
    }

    @Test
    public void paramShouldAlwaysBeInitializedOrElseThrow() {
        final var ctx = new Context();
        ctx.setVariables(Map.of("key", "value"));

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            final var renderer = new ThymeleafPdfRenderer(new TemplateEngine());
            renderer.createPdf(ctx);
        });
    }
}
