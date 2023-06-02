package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalServices {
	private double pricePerHour;
	private double pricePerDay;
	
	private TaxServices taxServices;
	
	public RentalServices() {
		
	}

	public RentalServices(double pricePerHour, double pricePerDay, BrazilTaxService taxServices) {
		super();
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxServices = taxServices;
	}
	
	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public TaxServices getTaxServices() {
		return taxServices;
	}

	public void setTaxServices(BrazilTaxService taxServices) {
		this.taxServices = taxServices;
	}

	public void processInvoice(CarRental carRental) {
		
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hour = minutes/60;
		double basicpayMent;
		double tax;
		if(hour <= 12) {
			basicpayMent = pricePerHour * Math.ceil(hour);
		}
		else {
			basicpayMent = pricePerDay * Math.ceil(hour/24);
		}
		tax = taxServices.tax(basicpayMent);		
		carRental.setInvoice(new Invoice(basicpayMent , tax));
	}
}
