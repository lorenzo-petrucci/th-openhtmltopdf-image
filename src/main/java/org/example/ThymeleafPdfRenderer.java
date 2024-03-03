package org.example;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ThymeleafPdfRenderer {
    private final TemplateEngine engine;
    private String source;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ThymeleafPdfRenderer(TemplateEngine engine) {
        this.engine = engine;
    }

    public void createPdf(Context ctx) throws IOException {
        try {
            assert source != null;
            assert inputStream != null;
            assert outputStream != null;
        } catch (AssertionError e) {
            throw new IllegalArgumentException("Param not initialized");
        }

        final String template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        final String html = this.engine.process(template, ctx);
        final var builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, source);
        builder.toStream(outputStream);
        builder.run();
        // TODO: add more settings with builder
    }

    public String getSource() {
        return source;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}


