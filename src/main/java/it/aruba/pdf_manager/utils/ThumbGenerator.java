package it.aruba.pdf_manager.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.imgscalr.Scalr;

public class ThumbGenerator {

    private static final int THUMBNAIL_WIDTH = 300;

    public static String generateThumbnailAsBase64(byte[] bs) {

        FileOutputStream fos = null;
        PDDocument document = null;
        try {
            File tempFile = File.createTempFile("prefix", "suffix", null);
            fos = new FileOutputStream(tempFile);

            fos.write(bs);
            document = PDDocument.load(tempFile);

            // PDPageTree pdPages = document.getDocumentCatalog().getPages();
            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage image = renderer.renderImage(0);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resize(image, THUMBNAIL_WIDTH), "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (document != null)
                    document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static BufferedImage resize(BufferedImage originalImage, int newW) {
        return Scalr.resize(originalImage, newW);
    }

}