package model;


import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;




class ConsumerNotFoundException extends Exception {
    public ConsumerNotFoundException(String message) {
        super(message);
    }
}

public class SalonApp {
    private List<Consumer> consumers;



    public SalonApp() {
        consumers = new ArrayList<>();


    }

    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void sortInvoicesForConsumer(String phoneOrEmail, boolean ascending) throws ConsumerNotFoundException {
        Consumer consumer = findConsumer(phoneOrEmail);
        if (consumer == null) {
            throw new ConsumerNotFoundException("Consumer not found: " + phoneOrEmail);
        }

        List<Invoice> invoices = consumer.getInvoices();

        Comparator<Invoice> invoiceComparator = Comparator.comparing(Invoice::getDate);
        if (!ascending) {
            invoiceComparator = invoiceComparator.reversed();
        }

        Collections.sort(invoices, invoiceComparator);
    }

    private Consumer findConsumer(String phoneOrEmail) {
        for (Consumer consumer : consumers) {
            if (consumer.getPhoneNumber().equals(phoneOrEmail) || consumer.getEmail().equals(phoneOrEmail)) {
                return consumer;
            }
        }
        return null;
    }


    public void generatepdf(){

        for (Consumer consumer : consumers){
            List<Invoice> invoices = consumer.getInvoices();


            try {

                String file = "Invoice_" + consumer.getPhoneNumber() + ".pdf";
                PdfWriter writer = new PdfWriter(file);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                document.add(new Paragraph("Consumer: " + consumer.getName()));
                document.add(new Paragraph("Email: " + consumer.getEmail()));
                document.add(new Paragraph("Phone: " + consumer.getPhoneNumber()));
                document.add(new Paragraph(""));
                Table table = new Table(2);
                table.addCell("Invoice ID");
                table.addCell("Description");
                for (Invoice invoice : invoices) {
                    table.addCell(invoice.getBillId());
                    table.addCell(invoice.getBillDesc());
                }




                document.add(table);
                document.close();

                System.out.println("PDF generated successfully for consumer: " + consumer.getName());
        } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

    }}






    public static void main(String[] args) {
        Map<Consumer, List<Invoice>> invoiceMap = new HashMap<>();

        // Create sample consumers and invoices
        Consumer consumer1 = new Consumer("1234567890","shifa" ,"john@example.com");
        Consumer consumer2 = new Consumer("9876543210","rehan", "jane@example.com");

        Invoice invoice1 = new Invoice("INV001", "Haircut and Styling", null, new Date(123,05,12), 50.0, 5.0, 55.0);
        Invoice invoice2 = new Invoice("INV002", "Facial and Massage",null, new Date(123,06,10), 80.0, 8.0, 88.0);
        Invoice invoice3 = new Invoice("INV003", "Hair Color",null, new Date(123,06,05), 120.0, 12.0, 132.0);

        consumer1.addInvoice(invoice1);
        consumer1.addInvoice(invoice2);
        consumer2.addInvoice(invoice3);

        invoiceMap.put(consumer1, consumer1.getInvoices());
        invoiceMap.put(consumer2, consumer2.getInvoices());
        SalonApp salonApp = new SalonApp();




        salonApp.addConsumer(consumer1);
        consumer1.setLastHaircutDate(LocalDate.now().minusDays(20));
        salonApp.addConsumer(consumer2);
        consumer1.setLastHaircutDate(LocalDate.now().minusDays(20));
        String searchKey = "1234567890";
        salonApp.generatepdf();

        try {
            Consumer foundConsumer = salonApp.findConsumer(searchKey);
            System.out.println("Consumer found: " + foundConsumer.getName());
            LocalDate nextHaircutDate = consumer1.getLastHaircutDate().plusDays(25);
            LocalDate currentDate = LocalDate.now();
            long daysRemaining = ChronoUnit.DAYS.between(currentDate, nextHaircutDate);
            List<Invoice> invoices = foundConsumer.getInvoices();
            for (Invoice invoice : invoices) {
                if ((daysRemaining <= 0) && (invoice.getBillDesc().equals("Haircut and Styling")) ) {
                    System.out.println("It's time for your next haircut!");
                } else if(invoice.getBillDesc().equals("Haircut and Styling")) {
                    System.out.println("Next haircut in " + daysRemaining + " days.");
                }
            }

            salonApp.sortInvoicesForConsumer(searchKey, true);
            System.out.println("Invoices sorted in ascending order by date for consumer with phone number: " + searchKey);


            for (Invoice invoice : invoices) {
                System.out.println("Invoice ID: " + invoice.getBillId());
                System.out.println("Description: " + invoice.getBillDesc());
                System.out.println("Notification Date: " + invoice.getDate());
                System.out.println("-----");
            }
            System.out.println(consumer1.getInvoices());




        } catch (ConsumerNotFoundException e) {
            System.out.println(e.getMessage());
        }}}







