package security;

import java.util.ArrayList;
import beans.Bag;
import services.BagService;

public class PurposeChecker {
	private ArrayList<String> whitelistPages;

	public PurposeChecker(){
		whitelistPages = new ArrayList<>();
		whitelistPages.add("index");
		whitelistPages.add("cart");
		whitelistPages.add("checkout");
		whitelistPages.add("products");
		whitelistPages.add("single");
	}

	public boolean checkRedirect(String redirect){
		for(int i = 0; i < whitelistPages.size(); i++)
			if(redirect.equals(whitelistPages.get(i)))
				return true;

		return false;
	}

	public boolean checkSingleProductURL(String url){
		String[] urlParts = url.split("#");
		if(urlParts.length == 2){

			String urlName = urlParts[1].replace('+', ' ');
			long urlID = 0;
			try{
				urlID = Long.parseLong(urlParts[0]);
			} catch(Exception e){
				return false;
			}

			Bag selectedBag = null;
			if(BagService.getBag(urlID) != null)
				selectedBag = BagService.getBag(urlID);

			else return false;

			if(selectedBag.getName().equals(urlName))
				return true;

			return false;
		}

		return false;
	}
}
