

import java.util.ArrayList;
import java.util.List;

/*
 * Result
A file full of Result objects is what your solution will be generating. A Result simply associates a Product with a list of matching Listing objects.
{
"product_name": String
"listings": Array[Listing]
}
 * */


public class Result {
	public String product_name;
	public List<Listing> listings =new ArrayList<Listing>();
	public Result(String product_name) {
		super();
		this.product_name = product_name;
		
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public void Add(Listing item){
		this.listings.add(item);
	}
	
	public String toString() {
	     StringBuilder sb = new StringBuilder("{\"product_name\":\"");
	     sb.append(this.getProduct_name()).append("\", \"listings\": [");
	     String commar = "";
	     for (Listing listing : listings){
	    	 sb.append(commar);
	    	 commar=",";
	    	 sb.append(listing.toString());
	     }
	     sb.append("]}\n");
	     
	     return sb.toString();
	}
	
	
}
