package org.example;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;

public class ImagePdfRenderer {
    public void createPDF(TemplateEngine engine, IContext ctx) throws IOException {

        final String template;
        try (InputStream is = new FileInputStream("src/main/resources/template.html")) {
            template = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        final String html = engine.process(template, ctx);

        try (OutputStream os = new FileOutputStream("src/main/resources/out.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, "/home/lorenzo/IdeaProjects/openhtmltopdf-test/src/main/resources/template.html");
            builder.toStream(os);
            builder.run();
        }
    }
}
