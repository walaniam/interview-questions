package walaniam.mid.orders;

import java.math.BigDecimal;
import java.util.List;

public class OrderSummaryTable {

    private List<OrderSummaryRow> rows;

    public OrderSummaryTable(List<OrderSummaryRow> rows) {
        this.rows = rows;
    }

    public List<OrderSummaryRow> getRows() {
        return rows;
    }

    public record OrderSummaryRow(String customerName, OrderStatus status, BigDecimal totalRevenue, long orderCount) {

    }
}
