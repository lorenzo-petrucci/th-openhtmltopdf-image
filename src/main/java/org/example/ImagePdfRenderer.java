package org.example;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ImagePdfRenderer {
    private final String src;
    private final String out;

    public ImagePdfRenderer(String src, String out) {
        this.src = src + RendererTypes.HTML.label;
        this.out = out + RendererTypes.PDF.label;
    }

    public void createPDF(TemplateEngine engine, IContext ctx) throws IOException {

        final String template;
        try (InputStream is = new FileInputStream((src))) {
            template = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }

        final String html = engine.process(template, ctx);
        try (OutputStream os = new FileOutputStream(out)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, src);
            builder.toStream(os);
            builder.run();
            // TODO: add more settings with builder
        }
    }

    public enum RendererTypes{
        HTML(".html"), PDF(".pdf");

        public final String label;

        RendererTypes(String label) {
            this.label = label;
        }
    }
}
