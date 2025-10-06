package ex4;

// Factory para criar relatórios com diferentes combinações de decorators
public class ReportFactory {

    public static SalesReport createBasicReport() {
        return new BasicSalesReport();
    }

    public static SalesReport createReportWithStatistics() {
        return new StatisticsDecorator(new BasicSalesReport());
    }

    public static SalesReport createReportWithCharts() {
        return new ChartDecorator(new BasicSalesReport());
    }

    public static SalesReport createFullReport() {
        return new ChartDecorator(
                new StatisticsDecorator(
                        new BasicSalesReport()));
    }

    public static SalesReport createPdfReport() {
        return new PdfExportDecorator(
                new ChartDecorator(
                        new StatisticsDecorator(
                                new BasicSalesReport())));
    }

    public static SalesReport createCustomReport(boolean includeStatistics,
            boolean includeCharts,
            boolean pdfFormat) {
        SalesReport report = new BasicSalesReport();

        if (includeStatistics) {
            report = new StatisticsDecorator(report);
        }

        if (includeCharts) {
            report = new ChartDecorator(report);
        }

        if (pdfFormat) {
            report = new PdfExportDecorator(report);
        }

        return report;
    }

    // Factory para strategies de exportação
    public static ExportStrategy createExportStrategy(String format) {
        return switch (format.toLowerCase()) {
            case "html" -> new HtmlExportStrategy();
            case "csv" -> new CsvExportStrategy();
            case "txt", "text" -> new TextExportStrategy();
            default -> throw new IllegalArgumentException("Formato não suportado: " + format);
        };
    }
}