package ex4;

import java.util.List;

// Decorator base abstrato
public abstract class SalesReportDecorator implements SalesReport {
    protected SalesReport wrappedReport;
    
    public SalesReportDecorator(SalesReport report) {
        this.wrappedReport = report;
    }
    
    @Override
    public String generate(List<Order> orders) {
        return wrappedReport.generate(orders);
    }
}