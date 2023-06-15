package model;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
