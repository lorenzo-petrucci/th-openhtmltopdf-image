package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.*;
import java.util.Map;

public class PdfRendererTest {
    final String DIR = "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/";
    final String SRC = "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/template.html";
    final String DST = "/home/lorenzo/IdeaProjects/openhtmltopdf-image/src/main/resources/out.pdf";
    //FIXME: spring test for properties?

    private PdfRenderer renderer;
    private InputStream is;
    private OutputStream os;
    private Context ctx;

    @Before
    public void setup() throws IOException {
        is = new FileInputStream(SRC);
        os = new FileOutputStream(DST);
        ctx = new Context();
        ctx.setVariables(Map.of("key", (Object) "value"));
        renderer = new PdfRenderer(DIR, is, os);
    }

    @After
    public void clean() throws IOException {
        this.is.close();
        this.os.close();
    }

    @Test
    public void pdfGenerationFromTemplate() throws IOException {
        final var engine = new TemplateEngine();
        renderer.createPDF(engine, ctx);
    }

    @Test
    public void generatePdfFromThymeleaf() throws IOException {
        renderer.createThymeleafPDF(ctx);
    }

}