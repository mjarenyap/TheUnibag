package security;

import beans.Bag;

public class ProductFilter{
	private float[] minRange;
	private float[] maxRange;

	public ProductFilter(){
		minRange =  new float[]{0, 40, 100, 150, 201};
		maxRange = new float[]{39.99f, 99.99f, 149.99f, 200.99f, 999};
	}

	public boolean checkSideFilter(String[] filter){
		if(filter != null)
			return true;

		return false;
	}

	public boolean checkPriceRange(Bag currentBag, boolean filterMode, int filterIndex){
		if(filterMode == false)
			return false;

		float price = currentBag.getPrice();
		if(price >= minRange[filterIndex] && price <= maxRange[filterIndex])
			return true;

		else return false;
	}

	public boolean checkCollection(Bag currentBag, boolean filterMode, int filterIndex){
		if(filterMode == false)
			return false;

		String currentCollect = null;
		if(currentBag.getCollection() != null)
			currentCollect = currentBag.getCollection();

		String[] collections = {"Classic Collection", "Essential Collection", "Travel System Collection"};
		if(currentCollect.equalsIgnoreCase(collections[filterIndex]))
			return true;

		else return false;
	}
}