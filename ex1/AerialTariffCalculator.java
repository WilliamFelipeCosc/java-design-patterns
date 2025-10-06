public class AerialTariffCalculator implements TariffCalculator {
    private static final double RATE_PER_KG = 2.0;
    @Override
    public double calculate(Shipment s) {
        return s.getWeightKg() * RATE_PER_KG;
    }
}