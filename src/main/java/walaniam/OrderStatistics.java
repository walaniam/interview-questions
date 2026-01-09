package walaniam.mid.orders;

public class OrderStatistics {

    /**
     * Coding task: implement this method.
     * <p>
     * Requirements:
     * - Ignore orders with status CANCELLED.
     * - Group remaining orders by (customerName, status).
     * - For each group, compute totalRevenue (sum of totalPrice) and orderCount.
     * - Return an OrderSummaryTable with rows sorted by:
     * * totalRevenue descending
     * * then customerName ascending
     */
    public static OrderSummaryTable summarize(OrderTable orderTable) {
        throw new UnsupportedOperationException("Implement me");
    }
}
