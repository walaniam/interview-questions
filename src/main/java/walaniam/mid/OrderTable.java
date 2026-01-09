package walaniam.mid;

import java.math.BigDecimal;
import java.util.List;

public class OrderTable {

    private List<OrderRow> rows;

    public OrderTable(List<OrderRow> rows) {
        this.rows = rows;
    }

    public List<OrderRow> getRows() {
        return rows;
    }

    public record OrderRow(String customerName, OrderStatus status, BigDecimal totalPrice) {

    }
}
