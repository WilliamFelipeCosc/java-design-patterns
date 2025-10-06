public class MaritimeTariffCalculator implements TariffCalculator {
    private static final double BASE = 50.0;
    
    @Override
    public double calculate(Shipment s) {
        return BASE + s.getWeightKg() * 0.3;
    }
}