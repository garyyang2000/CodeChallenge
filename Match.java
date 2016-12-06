
/**
* The Match class implements all methods  that can
* be used to read data from file and match products
* and listings. 
* 
* 
* @author  Gary Yang
* @version 1.0
* @since   2016-11-16 
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Match class implements main methods to read data and match them
 */
public class Match {
	// title should has exactly the model
	static String beforePattern1 = "[^a-zA-Z0-9]";
	static String afterPattern1 = "[^0-9,]";
	static String badPattern1 = "[^a-z0-9][a-z&]{1,2}";

	// allow some models have some variants in same family
	static String beforePattern2 = "[^a-zA-Z][a-z]";
	/**Following allows listing such as NEX-3A but fails to tell T3 from T3i
	static String afterPattern2 = "[a-z]{0,2}"; 
	*/
	static String afterPattern2 = "[^a-z0-9]";

	/*
	 *read 
	 */
	public static List<Product> readProduct(String fileName) {
		List<Product> result = new ArrayList<Product>();
		String line;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// read file line by line
			while ((line = bufferedReader.readLine()) != null) {
				JSONObject jsonObject = new JSONObject(line);
				String name = (String) jsonObject.get("product_name");
				String manu = (String) jsonObject.get("manufacturer");
				String family;
				// some lines have no family
				try {
					family = (String) jsonObject.get("family");
				} catch (JSONException e) {

					family = "";
				}
				String model = (String) jsonObject.get("model");
				String announced_date = (String) jsonObject.get("announced-date");
				Product prod = new Product(name, manu, family, model, announced_date);
				result.add(prod);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<Listing> readListing(String fileName) {
		List<Listing> result = new ArrayList<Listing>();
		String line;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			//read file line by line
			while ((line = bufferedReader.readLine()) != null) {
				JSONObject jsonObject = new JSONObject(line);

				String title = (String) jsonObject.get("title");
				String manu = (String) jsonObject.get("manufacturer");
				String currency = (String) jsonObject.get("currency");
				String price = (String) jsonObject.get("price");

				Listing listing = new Listing(title, manu, currency, price);
				result.add(listing);

			}
	
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	
	public static List<Result> match(List<Product> products, List<Listing> listings) {
		List<Result> results = new ArrayList<Result>();
		for (Product product : products) {
			Result newItem = new Result(product.getProduct_name());
			String model = product.getModel();
			String manu = product.getManufacturer();

			for (Iterator<Listing> iterator = listings.iterator(); iterator.hasNext();) {
				Listing listing = iterator.next();
				String title = listing.getTitle();
				//if the listing contains the keyword and is made by same company
				if (listing.getManugacturer().toLowerCase().contains(manu.toLowerCase())
						&&title.toLowerCase().contains(model.toLowerCase())) {
					String strPattern = beforePattern1 + model + afterPattern1;
					
					Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(title);
					if (matcher.find()) {
						strPattern = badPattern1 + strPattern;
						pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
						matcher = pattern.matcher(title);
						if (!matcher.find()) {
							newItem.Add(listing);
							
							iterator.remove();
						}
					} else {
						strPattern = beforePattern2 + model + afterPattern2;
						pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
						matcher = pattern.matcher(title);
						if ((matcher.find()) && (title.toLowerCase().contains(product.getFamily().toLowerCase()))) {

							newItem.Add(listing);
							iterator.remove();
							// listings.remove(listing);
						}
					}

				}
			}
			if (newItem.listings.size()>0)
				results.add(newItem);
		}
		
		return results;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productFile = "products.txt";
		List<Product> products = readProduct(productFile);
		String listingFile = "listings.txt";
		List<Listing> listings = readListing(listingFile);
		List<Result> results = match(products, listings);
		try {
			PrintWriter writer = new PrintWriter("results.txt", "UTF-8");
			for (Result result : results) {
				writer.print(result.toString());

			}
			writer.close();
		} catch (Exception e) {
			// do something
			System.out.println("Failed to match due to :");
			e.printStackTrace();
		}
	}

}
