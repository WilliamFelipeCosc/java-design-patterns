public class TariffFactory {
    public static TariffCalculator create(Shipment.TransportType type) {
        switch (type) {
            case TERRESTRE -> {
                return new TerrestrialTariffCalculator();
            }
            case AEREO -> {
                return new AerialTariffCalculator();
            }
            case MARITIMO -> {
                return new MaritimeTariffCalculator();
            }
            default -> throw new IllegalArgumentException("Tipo desconhecido");
        }
    }
}
