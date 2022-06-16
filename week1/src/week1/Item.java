package week1;

public class Item 
{
	public int itemNumber;
	public int itemRating;
	
	public Item(int n, int r)
	{
		itemNumber = n;
		itemRating = r;
	}
	
	public int getNumber()
	{
		return itemNumber;
	}
	
	public int getRating()
	{
		return itemRating;
	}
	
	public void setNumber(int n)
	{
		itemNumber = n;
	}
	
	public void setRating(int r)
	{
		itemRating = r;
	} 
}
