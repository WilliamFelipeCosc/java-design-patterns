public class Shipment {
    public enum TransportType { TERRESTRE, AEREO, MARITIMO }
    private TransportType type;
    private double weightKg;
    private double distanceKm;

    public Shipment(TransportType type, double weightKg, double distanceKm) {
        this.type = type;
        this.weightKg = weightKg;
        this.distanceKm = distanceKm;
    }

    public TransportType getType() {
        return type;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public double getDistanceKm() {
        return distanceKm;
    }
}
