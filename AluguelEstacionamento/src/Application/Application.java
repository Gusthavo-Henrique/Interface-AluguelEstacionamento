package Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalServices;

public class Application {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Modelo do carro: "); 
		String modelo = scan.nextLine();
		System.out.println("Retirada (dd/MM/yyyy hh:mm):");
		LocalDateTime start = LocalDateTime.parse(scan.nextLine(), dtf);
		System.out.println("Retirada (dd/MM/yyyy hh:mm):");
		LocalDateTime finish = LocalDateTime.parse(scan.nextLine(), dtf);
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(modelo));
		
		System.out.println("Preço por dia: ");
		double ppday = scan.nextDouble();
		System.out.println("Preço por hora: ");
		double pphora = scan.nextDouble();
		RentalServices service = new RentalServices(pphora, ppday, new BrazilTaxService());
		
		service.processInvoice(carRental);
		
		System.out.println("FATURA");
		System.out.println("Pagamento basico: "+carRental.getInvoice().getBasicPayment());
		System.out.println("Imposto: "+carRental.getInvoice().getTax());
		System.out.println("Pagamento total: "+carRental.getInvoice().getTotalPayment());
		
		
		scan.close();
	}
}
