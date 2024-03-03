package org.example;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class PdfRenderer {
    private final String src;
    private final InputStream is;
    private final OutputStream os;

    public PdfRenderer(String src, InputStream is, OutputStream os) throws IOException {
        this.src = src;
        this.is = is;
        this.os = os;
    }

    public void createPDF(TemplateEngine engine, Context ctx) throws IOException {
        final String template = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        final String html = engine.process(template, ctx);
        final var builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, src);
        builder.toStream(os);
        builder.run();
        // TODO: add more settings with builder
    }

    public void createThymeleafPDF(Context ctx) throws IOException {
        final var template = new TemplateEngine();
        createPDF(template, ctx);
    }
}


