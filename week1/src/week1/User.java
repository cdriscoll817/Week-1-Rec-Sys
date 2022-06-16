package week1;

public class User {
	
	public int userNumber;
	public int userItemRating;
	
	public User(int n, int r)
	{
		userNumber = n;
		userItemRating = r;
	}
	
	public int getNumber()
	{
		return userNumber;
	}
	
	public int getRating()
	{
		return userItemRating;
	}
	
	public void setNumber(int n)
	{
		userNumber = n;
	}
	
	public void setRating(int r)
	{
		userItemRating = r;
	} 
		
}
