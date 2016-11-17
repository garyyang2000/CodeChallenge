package Sortable;
/**
 * The structure of product is:
 *
{"product_name": String // A unique id for the product
"manufacturer": String
"family": String // optional grouping of products
"model": String
"announced-date": String // ISO-8601 formatted date string, e.g. 2011-04-28T19:00:00.000-05:00
}
 */


public class Product {
	public String product_name;
	public String manufacturer;
	public String family;
	public String model;
	public String announced_date;
	
	public Product(String name, String manu, String family, String model, String announce){
		this.product_name = name;
		this.manufacturer = manu;
		this.family = family;
		this.model = model;
		this.announced_date = announce;
		
	}
	
	@Override
	public String toString() {
		return "\"product_name\":\"" + product_name + "\", \"manufacturer\":\"" + manufacturer + "\", \"family\":\"" + family
				+ "\", \"model\":\"" + model + "\", \"announced_date\":\"" + announced_date + "\"";
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAnnounced_date() {
		return announced_date;
	}
	public void setAnnounced_date(String announced_date) {
		this.announced_date = announced_date;
	}
}
