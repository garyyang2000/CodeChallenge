package Sortable;
/**
 *
 * The structure of listings is:
 "title": String // description of product for sale
"manufacturer": String // who manufactures the product for sale
"currency": String // currency code, e.g. USD, CAD, GBP, etc.
"price": String // price, e.g. 19.99, 100.00
 * */
public class Listing {
	public String title;
	public String manugacturer;
	public String currency;
	public String price;
		
	public Listing(String title, String manugacturer, String currency, String price) {
		super();
		this.title = title;
		this.manugacturer = manugacturer;
		this.currency = currency;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getManugacturer() {
		return manugacturer;
	}
	public void setManugacturer(String manugacturer) {
		this.manugacturer = manugacturer;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "\"title\":\"" + title + "\", \"manugacturer\":\"" + manugacturer + "\", \"currency\":\"" + currency + "\", \"price\":\""
				+ price + "\"";
	}
	
	
	
}
