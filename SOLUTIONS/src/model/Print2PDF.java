package model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import controller.Kontroler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Print2PDF {

    private List<String> flightDetails;

    public Print2PDF(List<String> flightDetails){
        this.flightDetails = flightDetails;
        createThePDF();
    }

    private void createThePDF() {
        try {
            Document document = new Document(PageSize.A4, 36, 36, 50, 50);
            int random = ThreadLocalRandom.current().nextInt(0, 9999999);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("DATA/" + Kontroler.getCurrentUser() + String.valueOf(random) + ".pdf"));

            writer.setPageEvent(new BorderPageEvent());

            document.open();

            Image img = Image.getInstance("SOLUTIONS/src/view/icons/airplane.png");
            img.scaleToFit(100, 100);
            img.setAlignment(Element.ALIGN_CENTER);
            img.setSpacingBefore(20f);
            img.setSpacingAfter(20f);
            document.add(img);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);
            addTableHeaderAndValues(table);
            document.add(table);

            String thankYouCode = "Hvala vam na vjernosti i letu nasim zrakoplovima " + Kontroler.getCurrentUser() +
                    ". Zelimo vam ugodan put i radujemo se sljedecem putovanju!" + "\n\nThank You for trust and using our aircraft for your flights " +
                    Kontroler.getCurrentUser() + ". Enjoy your ride and we look forward to your next trip!";

            Paragraph thankYouParagraph = new Paragraph();
            thankYouParagraph.add(new Phrase("Posebna zahvala:"));
            thankYouParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(thankYouParagraph);

            BarcodeQRCode thankYouQrCode = new BarcodeQRCode(thankYouCode, 150, 150, null);
            Image thankYouQrImage = thankYouQrCode.getImage();
            thankYouQrImage.setAlignment(Element.ALIGN_CENTER);
            document.add(thankYouQrImage);

            String barcodeData = flightDetails.toString();

            Paragraph detailsParagraph = new Paragraph();
            detailsParagraph.add(new Phrase("Detalji leta (skenirajte prije ukrcaja):"));
            detailsParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(detailsParagraph);

            BufferedImage detailsBarcodeImage = generateBarcode(barcodeData);
            Image detailsQrImage = convertBufferedImageToITextImage(detailsBarcodeImage);
            detailsQrImage.scaleToFit(300, 150);
            detailsQrImage.setAlignment(Element.ALIGN_CENTER);
            document.add(detailsQrImage);

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addTableHeaderAndValues(PdfPTable table) {
        String[] headers = { "Zrakoplov", "Uzletište", "Dolazište", "Datum polijetanja", "Vrijeme polijetanja", "Ukupna cijena" };

        String[] values = { flightDetails.get(0), flightDetails.get(1), flightDetails.get(2),
                flightDetails.get(3), flightDetails.get(4), flightDetails.get(5) };

        for (int i = 0; i < headers.length; i++) {
            PdfPCell header = new PdfPCell(new Phrase(headers[i]));
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(header);

            PdfPCell valueCell = new PdfPCell(new Phrase(values[i]));
            valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(valueCell);
        }
    }

    class BorderPageEvent extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte canvas = writer.getDirectContent();

            Rectangle rect = new Rectangle(
                    document.left() - 10,
                    document.bottom() - 10,
                    document.right() + 10,
                    document.top() + 10
            );

            rect.setBorderWidth(2);

            rect.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);

            canvas.rectangle(rect);
        }
    }
    private BufferedImage generateBarcode(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 300, 150);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    private Image convertBufferedImageToITextImage(BufferedImage bufferedImage) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Image.getInstance(imageBytes);
    }
}