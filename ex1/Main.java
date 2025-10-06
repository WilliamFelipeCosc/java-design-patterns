//  Padrões e estratégias de projeto: Polimorfismo, Factory Method e SOLID (Single Responsibility Principle e Open/Closed Principle).

/*
 * Polimorfismo: diferentes classes implementam a mesma interface (TariffCalculator) com comportamentos distintos (cálculo de tarifa por modalidade).
 * Criar uma interface/abstract class (por exemplo, TariffCalculator) com um método calculate(Shipment) permite que clientes usem qualquer implementação sem conhecer detalhes internos.
 * Regras específicas ficam encapsuladas: o cálculo aéreo (por kg) fica numa classe, o terrestre (por km e possivelmente por peso mínimo) em outra, e assim por diante. Isso evita espalhar condicionais (ifs/switches) pelo sistema, reduz acoplamento e facilita testes focados
 * Suporta o princípio Open/Closed: para acrescentar uma nova modalidade basta criar uma nova implementação; o código cliente não precisa mudar.
 * Factory Method: a TariffFactory encapsula a lógica de criação dos objetos TariffCalculator. O cliente (Main) não precisa saber qual classe concreta usar, apenas chama o método estático create() com o tipo desejado.
 * Isso isola a lógica de instanciamento, facilitando manutenção e possíveis mudanças futuras (ex: usar injeção de dependência).
 * (SOLID) Single Responsibility Principle: cada classe tem uma única responsabilidade clara (cálculo de tarifa para uma modalidade específica, criação de objetos de tarifa, representação de uma remessa).
 * Isso torna o código mais coeso, fácil de entender e manter.
 */

public class Main {
	public static void main(String[] args) {
		Shipment s = new Shipment(Shipment.TransportType.TERRESTRE, 25.0, 150.0);
		TariffCalculator calc = TariffFactory.create(s.getType());
		double price = calc.calculate(s);
		System.out.println("Valor: " + price);
	}
}