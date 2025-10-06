public class TerrestrialTariffCalculator implements TariffCalculator {
    private static final double RATE_PER_KM = 0.5;
    @Override
    public double calculate(Shipment s) {
        return s.getDistanceKm() * RATE_PER_KM;
    }
}