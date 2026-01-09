package walaniam.mid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import walaniam.mid.OrderSummaryTable.OrderSummaryRow;

class OrderStatisticsTest {

    @Test
    void summarize_mixedStatuses() {
        OrderTable table = new OrderTable(
            List.of(
                new OrderTable.OrderRow("Alice", OrderStatus.PAID, BigDecimal.valueOf(100)),
                new OrderTable.OrderRow("Alice", OrderStatus.PAID, BigDecimal.valueOf(50)),
                new OrderTable.OrderRow("Bob", OrderStatus.NEW, BigDecimal.valueOf(200)),
                new OrderTable.OrderRow("Alice", OrderStatus.CANCELLED, BigDecimal.valueOf(999)),
                new OrderTable.OrderRow("Bob", OrderStatus.PAID, BigDecimal.valueOf(10))
            )
        );

        OrderSummaryTable expected = new OrderSummaryTable(
            List.of(
                new OrderSummaryRow("Bob", OrderStatus.NEW, BigDecimal.valueOf(200), 1L),
                new OrderSummaryRow("Alice", OrderStatus.PAID, BigDecimal.valueOf(150), 2L),
                new OrderSummaryRow("Bob", OrderStatus.PAID, BigDecimal.valueOf(10), 1L)
            )
        );

        OrderSummaryTable actual = OrderStatistics.summarize(table);

        assertEquals(expected.getRows(), actual.getRows());
    }

    @Test
    void summarize_allCancelled() {
        OrderTable table = new OrderTable(
            List.of(
                new OrderTable.OrderRow("Alice", OrderStatus.CANCELLED, BigDecimal.valueOf(10)),
                new OrderTable.OrderRow("Bob", OrderStatus.CANCELLED, BigDecimal.valueOf(20))
            )
        );

        OrderSummaryTable expected = new OrderSummaryTable(List.of());

        OrderSummaryTable actual = OrderStatistics.summarize(table);

        assertEquals(expected.getRows(), actual.getRows());
    }
}

