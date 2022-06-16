/*
 * Author Courtney Driscoll
 * 18/2/16
 */
package week1;

import java.util.*;
import java.io.*;

public class RecSystem {
	
	double userMeanSum = 0;
	double userMedianSum = 0;
	double userStdvSum = 0;
	double userMaxSum = 0;
	double userMinSum = 0;

	double itemMeanSum = 0;
	double itemMedianSum = 0;
	double itemStdvSum = 0;
	double itemMaxSum = 0;
	double itemMinSum = 0;
		
	int totalUsers = 0;
	int totalItems = 0;
	int totalRatings = 0;
	int onesTot = 0;
	int twosTot = 0;
	int threesTot = 0;
	int foursTot = 0;
	int fivesTot = 0;

	Map<String, List<Item>> userMap = new HashMap<String, List<Item>>();
	Map<String, List<User>> itemMap = new HashMap<String, List<User>>();

	
	public static void main(String[] args)
	{  
		RecSystem obj = new RecSystem();
		obj.fill();
		obj.getTotals();
		obj.getRatingsTotals();
		obj.getDensity();
		//obj.getUserStats();
		//obj.getItemStats();
	}
	
	public void fill()
	{
		
		String csvFile = "/Users/Courtney/Desktop/Collective Intelligence/ratings.csv.txt";
		BufferedReader br = null;
		String line = "";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(",");
				int u, i, r;
				u = Integer.parseInt(info[0]);
				i = Integer.parseInt(info[1]);
	            r = Integer.parseInt(info[2]);
	            
	            Item itemInfo = new Item(i, r);
				List<Item> l = userMap.get(info[0]);
				if(l==null)
				{
					userMap.put(info[0], l=new ArrayList<Item>());
					totalUsers ++;
				}
				l.add(itemInfo);	
				
				User userInfo = new User(u, r);
				List<User> ul = itemMap.get(info[1]);
				if(ul==null)
				{
					itemMap.put(info[1], ul=new ArrayList<User>());
					totalItems ++;
				}
				ul.add(userInfo);
				
				if(r>0 && r <6)
					totalRatings = 5;
				
				switch(r)
				{
					case 1:
						onesTot++;
						break;
					case 2:
						twosTot++;
						break;
					case 3:
						threesTot++;
						break;
					case 4:
						foursTot++;	
						break;
					case 5:
						fivesTot++;	
						break;
				} 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void getTotals()
	{
		System.out.println("Total number of users is: " + totalUsers);
		System.out.println("Total number of items is: " + totalItems);
		System.out.println("Total number of ratings is: " + totalRatings);
	}
	
	public void getRatingsTotals()
	{
		System.out.println("Total number of 1 ratings is: " + onesTot);
		System.out.println("Total number of 2 ratings is: " + twosTot);
		System.out.println("Total number of 3 ratings is: " + threesTot);
		System.out.println("Total number of 4 ratings is: " + foursTot);
		System.out.println("Total number of 5 ratings is: " + fivesTot);
	}
	
	public void getUserStats()
	{
		for(Map.Entry<String, List<Item>> entry : userMap.entrySet())
		{
			List<Item> ar = entry.getValue();
			int[] a = new int[ar.size()];
			double sum = 0;
			double mean = 0;
			for(int j = 0; j < ar.size(); j ++)
			{
				a[j] = ar.get(j).getRating();
				sum += a[j];
			}
			mean = sum/ar.size();
			Arrays.sort(a);
			double median = 0;
			double mediansum = 0;
			int medianInd;
			if(ar.size()%2 == 1)
			{
				medianInd = (ar.size()-1)/2;
				median = a[medianInd];
			}	
			else 
			{
				medianInd = ar.size()/2;
				mediansum = a[medianInd] + a[medianInd-1];
				median = mediansum/2;
			}
			double stdsum = 0;
			for(int k = 0; k < ar.size(); k++)
			{
				stdsum += Math.pow((a[k]-mean),2);
			}
			double stdv = Math.sqrt(stdsum/ar.size());
			int max = a[ar.size()-1];
			int min = a[0];
			System.out.println("For User: " + entry.getKey() + " - mean: " + mean + " median: " + median + " stdv: " + stdv + " max: " + max + " min: " + min);
			userMeanSum += mean;
			userMedianSum += median;
			userStdvSum += stdv;
			userMaxSum += max;
			userMinSum += min;
		}
		double avgUserMean = userMeanSum/totalUsers;
		double avgUserMedian = userMedianSum/totalUsers;
		double avgUserStdv = userStdvSum/totalUsers;
		double avgUserMax = userMaxSum/totalUsers;
		double avgUserMin = userMinSum/totalUsers;
		System.out.println("User averages - mean: " + avgUserMean + " median: " + avgUserMedian + " stdv: " + avgUserStdv + " max: " + avgUserMax + " min: " + avgUserMin);		
	}
	
	public void getItemStats()
	{
		for(Map.Entry<String, List<User>> entry : itemMap.entrySet())
		{
			List<User> ur = entry.getValue();
			int[] b = new int[ur.size()];
			double sumb = 0;
			double meanb = 0;
			for(int j = 0; j < ur.size(); j ++)
			{
				b[j] = ur.get(j).getRating();
				sumb += b[j];
			}
			meanb = sumb/ur.size();
			Arrays.sort(b);
			double medianb = 0;
			double mediansumb = 0;
			int medianIndb;
			if(ur.size()%2 == 1)
			{
				medianIndb = (ur.size()-1)/2;
				medianb = b[medianIndb];
			}	
			else 
			{
				medianIndb = ur.size()/2;
				mediansumb = b[medianIndb] + b[medianIndb-1];
				medianb = mediansumb/2;
			}
			double stdsumb = 0;
			for(int k = 0; k < ur.size(); k++)
			{
				stdsumb += Math.pow((b[k]-meanb),2);
			}
			double stdvb = Math.sqrt(stdsumb/ur.size());
			int maxb = b[ur.size()-1];
			int minb = b[0];
			System.out.println("For Item: " + entry.getKey() + " - mean: " + meanb + " median: " + medianb + " stdv: " + stdvb + " max: " + maxb + " min: " + minb);			
			itemMeanSum += meanb;
			itemMedianSum += medianb;
			itemStdvSum += stdvb;
			itemMaxSum += maxb;
			itemMinSum += minb;
		}
		double avgItemMean = itemMeanSum/totalItems;
		double avgItemMedian = itemMedianSum/totalItems;
		double avgItemStdv = itemStdvSum/totalItems;
		double avgItemMax = itemMaxSum/totalItems;
		double avgItemMin = itemMinSum/totalItems;
		System.out.println("Item averages - mean: " + avgItemMean + " median: " + avgItemMedian + " stdv: " + avgItemStdv + " max: " + avgItemMax + " min: " + avgItemMin);
	}
	
	public void getDensity()
	{
		double matrixsize = totalUsers*totalItems;
		double allRatings = onesTot + twosTot + threesTot + foursTot + fivesTot;
		double density = (allRatings/matrixsize)*100;
		System.out.println("The ratings density matric is " + density + "%");
	}
}
