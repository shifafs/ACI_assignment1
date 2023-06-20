# ACI_assignment1
**1. Create a POJO for having consumer info- phone no, name and email**
public class Consumer {
    private String phoneNumber;
    private String name;
    private String email;
    private List<Invoice> invoices;
    private LocalDate lastHaircutDate;

    public Consumer(String phoneNumber, String name, String email) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.invoices = new ArrayList<>();
        this.lastHaircutDate = null;
    }
    //Includes all Getters and Setters
    public List<Invoice> getInvoices() {     
        return invoices;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }
    public LocalDate getLastHaircutDate() {
        return lastHaircutDate;
    }

    public void setLastHaircutDate(LocalDate lastHaircutDate) {
        this.lastHaircutDate = lastHaircutDate;
    }

**2.Create a POJO for Line Item like – item no, item type – hair cut, facial etc , price charged**
  public class LineItem{
    private int itemNo;
    private String itemType;
    private double priceCharged;

    public LineItem(int itemNo, String itemType, double priceCharged) {
        this.itemNo = itemNo;
        this.itemType = itemType;
        this.priceCharged = priceCharged;
    //And all the getters and setters
    }}
  
    
**3. Create a POJO for Invoices having information of billid, billdesc, list of line items, date, 
total amount, total tax, final amount including tax.**
 

    public Invoice(String billId, String billDesc, List<LineItem> lineItems, Date date,
                   double totalAmount, double totalTax, double finalAmountIncludingTax) {
        this.billId = billId;
        this.billDesc = billDesc;
        this.lineItems = lineItems;
        this.date = date;
        this.totalAmount = totalAmount;
        this.totalTax = totalTax;
        this.finalAmountIncludingTax = finalAmountIncludingTax;
      //Includes all getters and setters
    }

**4.Create a JAVA program makes use of Map (Collection) concept with Key as Consumer 
Info and value as List of Invoice.**
class ConsumerMap {
    private Map<Consumer, List<Invoice>> consumerMap;

    public ConsumerMap() {
        consumerMap = new HashMap<>();
    }

    public void addConsumer(Consumer consumer, List<Invoice> invoices) {
        consumerMap.put(consumer, invoices);
    }

    public List<Invoice> getInvoicesForConsumer(Consumer consumer) {
        return consumerMap.get(consumer);
    }
}
**5. Create a JAVA program to sort the invoices for each customer by Date in ascending or 
descending order based on input which can be customer phone no or email. If the 
consumer is not found create a custom exception like consumernotfoundexception.**
private Consumer findConsumer(String phoneOrEmail) {
      for (Consumer consumer : consumers) {
          if (consumer.getPhoneNumber().equals(phoneOrEmail) || consumer.getEmail().equals(phoneOrEmail)) {
              return consumer;
            }
        }
      return null;
    }
  This function takes the key(phone or email) as parameter and returns the customer details in case of successful search.
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
  The above  function compares the dates of the invoices of the consumer and sorts it in ascending order. If key found, prints the invoice details in order.
**6.Create a JAVA program which can generate PDF containing invoices. The rules for this 
task are as follows.**
  
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
        } }
  The above function prepapres the pdf of all consumers in separate PDFs. It creates a table and the invoices of each consumer in it.
**7. keep track of your visitor/ consumers and remind them for the next haircut. You should have the capability to notify 
the consumer regarding next haircut event. The remainder should be 25 days from last hair 
cut. Here notify can be simple print statement.**
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
            }}
  The above try block first computes the next haircut date. It then finds the days between the present and next date. If the reamaing days are less
  than or equal to zero and the invoice description is "haircut" it prints the neccessary print statement that its time for next haircut.


  

  
