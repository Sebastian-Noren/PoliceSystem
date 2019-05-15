package pust.model.administrative_functions;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportReceiptTest {
    private static final Logger LOGGER = Logger.getLogger(ReportReceiptTest.class.getName());
    private static final String pdfFile = "src/main/resources/files/pdf/template.pdf";
    private static final String pustLogo = "src/main/resources/image/Swepust2.png";
    private float[] headerColumnWidth = {100F};
    private float[] reportTableWidth = {250F, 250F};
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void generatePdf() {
        ReportReceipt reportReceipt = new ReportReceipt();

        //        try {
//            createPdf(pdfFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void createPdf(String fileName) throws IOException {

        //Setup the pdf document
        PdfWriter writer = new PdfWriter(pdfFile);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Create pages
        PdfPage pdfPage1 = pdf.addNewPage();
        PdfPage pdfPage2 = pdf.addNewPage();

        // Start page 1
        // Header
        header(pdf, document, 1);

        // Body
        informationalText(document);
        reportInformation(document);
        informerInformation(pdf, document);
        // Body ends

        // Footer
        footer(document);

        addAreaBreak(document);
        // End page 1

        // Start page 2
        // Header
        header(pdf, document, 2);

        // Body
        objectInformation(document);
        placeOfEvent(document);
        descriptionOfEvent(document);
        // Body ends

        // Footer
        footer(document);

        // Closing the document
        document.close();


    }

    private void header(PdfDocument pdf, Document document, int page) {
        Paragraph headLine = new Paragraph("Police Report\n");
        headLine.setFontSize(20F);
        headLine.setBold();
        Paragraph timeWhenReported = new Paragraph(
                "Time of report: " + LocalDateTime.now().format(dateTimeFormat) + "\n");
        timeWhenReported.setFontSize(8F);
        timeWhenReported.setMarginBottom(-20F);
        Paragraph ref = new Paragraph("Reference:\n" + "I-12-10789202-13\n");
        Paragraph pageNumbers = new Paragraph(
                "Page:\n" + page + "[" + pdf.getNumberOfPages() + "]");

        Table headerTable = new Table(headerColumnWidth);
        Cell headerInformation = new Cell();
        headerInformation.add(headLine);
        headerInformation.add(timeWhenReported);
        headerInformation.setTextAlignment(TextAlignment.CENTER);
        headerInformation.setBorder(Border.NO_BORDER);
        headerTable.addCell(headerInformation);
        headerTable.setBorder(Border.NO_BORDER);
        headerTable.setFixedPosition(200F, 770F, 200F);
        document.add(headerTable);

        Table referenceTable = new Table(headerColumnWidth);
        Cell referenceInformation = new Cell();
        referenceInformation.add(ref);
        referenceInformation.add(pageNumbers);
        referenceInformation.setBorder(Border.NO_BORDER);
        referenceTable.addCell(referenceInformation);
        referenceTable.setBorder(Border.NO_BORDER);
        referenceTable.setFixedPosition(470F, 740F, 100F);
        document.add(referenceTable);

        ImageData headerPustLogoData = null;
        try {
            headerPustLogoData = ImageDataFactory.create(pustLogo);
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        Image headerPustLogoImage = new Image(headerPustLogoData);
        headerPustLogoImage.setHeight(headerPustLogoImage.getImageHeight() / 12);
        headerPustLogoImage.setWidth(headerPustLogoImage.getImageWidth() / 12);
        headerPustLogoImage.setFixedPosition(10F, 740F);
        document.add(headerPustLogoImage);

    }

    private void informationalText(Document document) {
        document.add(addLine().setMarginTop(60F));
        Paragraph infoTextHeadLine = new Paragraph(getInfoHeadLine());
        infoTextHeadLine.setBold();
        Paragraph infoText = new Paragraph(getInfoText());
        List infoTextList = new List();
        infoTextList.setListSymbol("•");
        infoTextList.setMarginLeft(25F);
        infoTextList.setSymbolIndent(5F);

        ListItem infoText1 = new ListItem(getListInfoText1());
        ListItem infoText2 = new ListItem(getListInfoText2());
        ListItem infoText3 = new ListItem(getListInfoText3());
        ListItem infoText4 = new ListItem(getListInfoText4());
        infoTextList.add(infoText1);
        infoTextList.add(infoText2);
        infoTextList.add(infoText3);
        infoTextList.add(infoText4);

        float[] infoColumnWidth = {600F};
        Table infoTable = new Table(infoColumnWidth);
        Cell infoCell = new Cell();
        infoCell.add(infoTextHeadLine);
        infoCell.add(infoText);
        infoCell.add(infoTextList);
        infoCell.setBorder(Border.NO_BORDER);
        infoTable.addCell(infoCell);
        document.add(infoTable);

        document.add(addLine());
    }

    private void reportInformation(Document document) {
        Paragraph report = new Paragraph("Report");
        report.setBold();
        report.setMarginTop(-5F);
        report.setMarginBottom(-18F);
        document.add(report);

        document.add(addLine());

        Table reportInfoTable = new Table(reportTableWidth);
        Cell reportC11 = new Cell();
        reportC11.add("Reference number");
        reportC11.setBorder(Border.NO_BORDER);
        reportInfoTable.addCell(reportC11);

        Cell reportC12 = new Cell();
        reportC12.add("I-12-10789202-13");
        reportC12.setBorder(Border.NO_BORDER);
        reportInfoTable.addCell(reportC12);

        Cell reportC21 = new Cell();
        reportC21.add("Time of report");
        reportC21.setBorder(Border.NO_BORDER);
        reportInfoTable.addCell(reportC21);

        Cell reportC22 = new Cell();
        reportC22.add(LocalDateTime.now().format(dateTimeFormat));
        reportC22.setBorder(Border.NO_BORDER);
        reportInfoTable.addCell(reportC22);
        document.add(reportInfoTable);

        document.add(addLine());
    }

    private void informerInformation(PdfDocument pdf, Document document) {
        Paragraph informer = new Paragraph("Informer");
        informer.setBold();
        informer.setMarginTop(-5F);
        informer.setMarginBottom(-18F);
        document.add(informer);

        document.add(addLine());

        Table informerTable = new Table(reportTableWidth);
        Cell c11 = new Cell();
        c11.add("First name");
        c11.setBorder(Border.NO_BORDER);
        informerTable.addCell(c11);

        Cell c12 = new Cell();
        c12.add("Christoffer");
        c12.setBorder(Border.NO_BORDER);
        informerTable.addCell(c12);

        Cell c21 = new Cell();
        c21.add("Surname");
        c21.setBorder(Border.NO_BORDER);
        informerTable.addCell(c21);

        Cell c22 = new Cell();
        c22.add("Quick");
        c22.setBorder(Border.NO_BORDER);
        informerTable.addCell(c22);

        Cell c31 = new Cell();
        c31.add("Personal identity number");
        c31.setBorder(Border.NO_BORDER);
        informerTable.addCell(c31);

        Cell c32 = new Cell();
        c32.add("1983-06-19-3334");
        c32.setBorder(Border.NO_BORDER);
        informerTable.addCell(c32);

        Cell c41 = new Cell();
        c41.add("Address");
        c41.setBorder(Border.NO_BORDER);
        informerTable.addCell(c41);

        Cell c42 = new Cell();
        c42.add("Stora Torsjö 3191");
        c42.setBorder(Border.NO_BORDER);
        informerTable.addCell(c42);

        Cell c51 = new Cell();
        c51.add("Zip code");
        c51.setBorder(Border.NO_BORDER);
        informerTable.addCell(c51);

        Cell c52 = new Cell();
        c52.add("282 92");
        c52.setBorder(Border.NO_BORDER);
        informerTable.addCell(c52);

        Cell c61 = new Cell();
        c61.add("City");
        c61.setBorder(Border.NO_BORDER);
        informerTable.addCell(c61);

        Cell c62 = new Cell();
        c62.add("Västra Torup");
        c62.setBorder(Border.NO_BORDER);
        informerTable.addCell(c62);

        Cell c71 = new Cell();
        c71.add("Phone number");
        c71.setBorder(Border.NO_BORDER);
        informerTable.addCell(c71);

        Cell c72 = new Cell();
        c72.add("0722-352475");
        c72.setBorder(Border.NO_BORDER);
        informerTable.addCell(c72);

        Cell c81 = new Cell();
        c81.add("E-mail");
        c81.setBorder(Border.NO_BORDER);
        informerTable.addCell(c81);

        Cell c82 = new Cell();
        c82.add("christoffer@torsjogard.se");
        c82.setBorder(Border.NO_BORDER);
        informerTable.addCell(c82);
        document.add(informerTable);
    }

    private void objectInformation(Document document) {
        document.add(addLine().setMarginTop(60F));
        Paragraph object = new Paragraph("Object");
        object.setBold();
        object.setMarginTop(-5F);
        object.setMarginBottom(-18F);
        document.add(object);
        document.add(addLine());

        Table objectTable = new Table(reportTableWidth);
        Cell c11 = new Cell();
        c11.add("Object type");
        c11.setBorder(Border.NO_BORDER);
        objectTable.addCell(c11);

        Cell c12 = new Cell();
        c12.add("Motorcycle");
        c12.setBorder(Border.NO_BORDER);
        objectTable.addCell(c12);

        Cell c21 = new Cell();
        c21.add("Quantity");
        c21.setBorder(Border.NO_BORDER);
        objectTable.addCell(c21);

        Cell c22 = new Cell();
        c22.add("1");
        c22.setBorder(Border.NO_BORDER);
        objectTable.addCell(c22);

        Cell c31 = new Cell();
        c31.add("Country - Currency");
        c31.setBorder(Border.NO_BORDER);
        objectTable.addCell(c31);

        Cell c32 = new Cell();
        c32.add("Sweden - SEK");
        c32.setBorder(Border.NO_BORDER);
        objectTable.addCell(c32);

        Cell c41 = new Cell();
        c41.add("Value");
        c41.setBorder(Border.NO_BORDER);
        objectTable.addCell(c41);

        Cell c42 = new Cell();
        c42.add("145 000");
        c42.setBorder(Border.NO_BORDER);
        objectTable.addCell(c42);
        document.add(objectTable);
    }

    private void placeOfEvent(Document document) {
        document.add(addLine());
        Paragraph location = new Paragraph("Place of event");
        location.setBold();
        location.setMarginTop(-5F);
        location.setMarginBottom(-18F);
        document.add(location);
        document.add(addLine());

        Table locationTable = new Table(reportTableWidth);
        Cell c11 = new Cell();
        c11.add("Country");
        c11.setBorder(Border.NO_BORDER);
        locationTable.addCell(c11);

        Cell c12 = new Cell();
        c12.add("Germany");
        c12.setBorder(Border.NO_BORDER);
        locationTable.addCell(c12);

        Cell c21 = new Cell();
        c21.add("City");
        c21.setBorder(Border.NO_BORDER);
        locationTable.addCell(c21);

        Cell c22 = new Cell();
        c22.add("Berlin");
        c22.setBorder(Border.NO_BORDER);
        locationTable.addCell(c22);

        Cell c31 = new Cell();
        c31.add("Street");
        c31.setBorder(Border.NO_BORDER);
        locationTable.addCell(c31);

        Cell c32 = new Cell();
        c32.add("Weydemeyerstraße 106");
        c32.setBorder(Border.NO_BORDER);
        locationTable.addCell(c32);

        document.add(locationTable);
    }

    private void descriptionOfEvent(Document document) {
        document.add(addLine());
        Paragraph description = new Paragraph("Description of event");
        description.setBold();
        description.setMarginTop(-5F);
        description.setMarginBottom(-18F);
        document.add(description);
        document.add(addLine());

        Table descriptionTable = new Table(reportTableWidth);
        Cell c11 = new Cell();
        c11.add("Environment");
        c11.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c11);

        Cell c12 = new Cell();
        c12.add("By the gas station");
        c12.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c12);

        Cell c21 = new Cell();
        c21.add("Time of event");
        c21.setBold();
        c21.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c21);

        Cell c22 = new Cell();
        c22.add("");
        c22.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c22);

        Cell c31 = new Cell();
        c31.add("From");
        c31.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c31);

        Cell c32 = new Cell();
        c32.add("2018-06-28 17:00");
        c32.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c32);

        Cell c41 = new Cell();
        c41.add("To");
        c41.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c41);

        Cell c42 = new Cell();
        c42.add("2018-06-28 17:30");
        c42.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c42);

        Cell c51 = new Cell();
        c51.add("Likely event");
        c51.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c51);

        Cell c52 = new Cell();
        c52.add("Theft");
        c52.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c52);

        Cell c61 = new Cell();
        c61.add("Description");
        c61.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c61);

        Cell c62 = new Cell();
        c62.add("The vehicle was parked outside a gas station and someone " +
                "stole the bike when the victim was in the station.");
        c62.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c62);

        Cell c71 = new Cell();
        c71.add("Suspect");
        c71.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c71);

        Cell c72 = new Cell();
        c72.add("John Doe");
        c72.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c72);

        Cell c81 = new Cell();
        c81.add("Tracks");
        c81.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c81);

        Cell c82 = new Cell();
        c82.add("The thief dropped a wallet next to the bike.");
        c82.setBorder(Border.NO_BORDER);
        descriptionTable.addCell(c82);

        document.add(descriptionTable);
    }


    private void footer(Document document) {
        document.add(addLine().setFixedPosition(35F, 50F, 600F));
        Paragraph creator = new Paragraph("Generated by Pust Graphical Interface System 2019");
        creator.setFixedPosition(35F, 25F, 400F);
        document.add(creator);

        // Creating PdfLinkAnnotation
        Rectangle rectangle1 = new Rectangle(0, 0);
        PdfLinkAnnotation linkAnnotation = new PdfLinkAnnotation(rectangle1);
        PdfAction action = PdfAction.createURI("https://github.com/Sebastian-Noren/PoliceSystem");
        linkAnnotation.setAction(action);
        Link link = new Link("P.G.I.S", linkAnnotation);
        Paragraph linkedParagraph = new Paragraph(link);
        linkedParagraph.setFixedPosition(515F, 25F, 50F);
        document.add(linkedParagraph);
    }

    private void addAreaBreak(Document document) {
        AreaBreak areaBreak = new AreaBreak();
        document.add(areaBreak);
    }

    private Paragraph addLine() {
        Paragraph line = new Paragraph("______________________________________________________________________________");
        return line;
    }

    private String addSpace(int space) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < space; i++) {
            spaces.append("\n");
        }
        return spaces.toString();
    }

    private String getInfoHeadLine() {
        return "Your application has been sent to the Police Authority.\n\n";
    }

    private String getInfoText() {
        return "If you have provided an email address, you will also receive a confirmation email. One copy of " +
                "the police report (claiming copy) will be sent via regular mail to the owner or owners you have stated. " +
                "The information you provided will be processed according to the provisions of the Personal Data Act (1998: 204).";
    }

    private String getListInfoText1() {
        return "If you need to add information to your application, you can do so on the website where you did your report " +
                "notification. Then choose \"Supplement\" instead and enter your registration number that you get in this receipt.";
    }

    private String getListInfoText2() {
        return "If you need to talk to the police about your registration, you should call telephone number 114 14. Have your " +
                "registration number at hand.";
    }

    private String getListInfoText3() {
        return "As a crime victim you have the opportunity to get support and help in different forms depending on the type of crime you have become " +
                "exposed to. Read more about this on the website of the police authority to which your application was sent.";
    }

    private String getListInfoText4() {
        return "Remember that you should contact your insurance company when you have been robbed or lost something";
    }

}