package model;





import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

    // Getters and Setters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
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
}

