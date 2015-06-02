import java.util.*;
import java.io.*;
class me
{
	static PrintWriter writer;
	static int arr[] = new int[200];
	static int runs[] = new int[200];
	static int wkts[] = new int[200];
	static int sum_r,sum_w,sum,ibat=0;
	public static void mainUpdate(int bat_r,int bowl_r)
	{
		sum = bat_r + bowl_r;
		for(int i=0;i<sum;i++)
		{
			if(i<bat_r)
				arr[i] = 1;				
			else
				arr[i] = 0;
		}
	}
	public static void updateRuns(int bat_r,int bowl_r)
	{
		//int bat_r = a;
		//int bowl_r = b;
		sum_r = bat_r + bowl_r;
		for(int i=0;i<sum_r;i++)
		{
			if(i<bat_r)
			{
						//System.out.println("BLOCK1");
				if(i%2 == 0)
					runs[i] = 6;
				else
					runs[i] = 4;
			}
			else
			{
				if(i%2 == 0)
					runs[i] = 2;
				else if(i == 15)
				{
					runs[i] = 3;
					//ibat = n_str;	
				}
				else
				{
					runs[i] = 1;
					//ibat = n_str;
				}
					
			}
		}
	}
	public static void updateWkts(int bat_w,int bowl_w)
	{
		//int bat_w = a;
		//int bowl_w = b;
		 sum_w = bat_w + bowl_w;
		for(int i=0;i<sum_w;i++)
		{
			
			if(i<(bowl_w/4))
			{
				wkts[i] = 10;
						//System.out.println("BLOCK3");
			}
			else
			{
						//System.out.println("BLOCK4");
				if(i%2 == 0)
				{
					wkts[i] = 1;
					//ibat = n_str;
				}
				else
					wkts[i] = 0;
			}
		}
	}
		
	
	public static void main(String args[])
	{
		Scanner s =new Scanner(System.in);
		int bat_skill[] = new int[11];
		int bowl_skill[] = new int[5];
		/*for(int i=0;i<11;i++)
		{
			System.out.println("Enter Batsman skill index");
			bat_skill[i]= s.nextInt();
		}
		
			for(int i=0;i<5;i++)
		{
			System.out.println("Enter Bowler skill index");
			bowl_skill[i] = s.nextInt();
		} */
		try
		{	writer = new PrintWriter("Crico.txt","UTF-8");
			System.out.print("CREATED...");
		}
		catch(Exception e){ System.out.print("ERRRRROOO>>>");} 
		//catch(Exception e){ System.out.print("ERRRRROOO>>>");} */
		try 					//Read from File.....
		{
			File file = new File("Cric.txt");
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) 
			{
				for(int i=0;i<11;i++)
					bat_skill[i]=Integer.parseInt(line);  //System.out.println(line);						// process the line.
				for(int i=0;i<5;i++)
					bowl_skill[i] = Integer.parseInt(line);
			}
		}
		catch(Exception e)
		{
		}
		//a = 80;
		//b = 85;
		//int arr[] = new int[200];
		//int runs[] = new int[200];
		//int wkts[] = new int[200];
		/*for(int i=0;i<(a+b);i++)
		{
			if(i<=a)
				arr[i] = 1;				
			else
				arr[i] = 0;
		} */
		Random r = new Random();
		int ibow=0;								//Denotes index of bat_skill[ibat] and bowl_skill[ibow],,......
						//Denotes current batsmen skill and bowler skill....
		mainUpdate(bat_skill[ibat],bowl_skill[ibow]);
		updateRuns(bat_skill[ibat],bowl_skill[ibow]);
		updateWkts(bat_skill[ibat],bowl_skill[ibow]);
		//to account for strke change...
		mainUpdate(bat_skill[ibat],bowl_skill[ibow]);
		updateRuns(bat_skill[ibat],bowl_skill[ibow]);
		updateWkts(bat_skill[ibat],bowl_skill[ibow]);
		int run=0;
		int wkt=0;
		int count =0;
		for(int j=0;j<120 && wkt<10;j++)
		{	
			if(j%6 == 0)
			{
				System.out.println("OVER " + (((j+1)/6)+1));
				writer.println("OVER " + (((j+1)/6)+1));
				count++;
				if(count == 5)
				{
					count = 1;
					System.out.println("BOWLER CHANGE...");
					writer.println("BOWLER CHANGE...");
					ibow++;
					mainUpdate(bat_skill[ibat],bowl_skill[ibow]);
					updateRuns(bat_skill[ibat],bowl_skill[ibow]);
					updateWkts(bat_skill[ibat],bowl_skill[ibow]);
					
					mainUpdate(bat_skill[ibat],bowl_skill[ibow]);
					updateRuns(bat_skill[ibat],bowl_skill[ibow]);
					updateWkts(bat_skill[ibat],bowl_skill[ibow]);
				}
				
			}
			System.out.print("BALL " +(j+1)%6);
			writer.print("BALL " +(j+1)%6);
			int x = r.nextInt(sum);
			if(arr[x] == 1)
			{	
				int y = r.nextInt(sum_r);
				System.out.print("BATSMAN " + ibat);
				writer.print("BATSMAN ");
				System.out.println(" " +runs[y]+ "RUNS");
				writer.println(" " +runs[y]+ "RUNS");
				run = run + runs[y];
			}
			else
			{
				int z = r.nextInt(sum_w);
				System.out.print("BOWLER ");
				writer.print("BOWLER ");
				if(wkts[z] == 10)
				{
					System.out.println(" " + "WICKET");
					writer.println(" " + "WICKET");
					wkt++;
					System.out.println("BATSMAN CHANGE");
					writer.println("BATSMAN CHANGE");
					//ibat = n_str;
					ibat++;
					mainUpdate(bat_skill[ibat],bowl_skill[ibow]);
					updateRuns(bat_skill[ibat],bowl_skill[ibow]);
					updateWkts(bat_skill[ibat],bowl_skill[ibow]);
					
					mainUpdate(bat_skill[ibat],bowl_skill[ibow]);
					updateRuns(bat_skill[ibat],bowl_skill[ibow]);
					updateWkts(bat_skill[ibat],bowl_skill[ibow]);
					
				}
				else
				{
					System.out.println(" " + wkts[z]+ "RUNS");
					writer.println(" " + wkts[z]+ "RUNS");
					run = run + wkts[z];
				}
			}
		}
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Total score : "+run+"/"+wkt);
		writer.println(" ");
		writer.println(" ");
		writer.println("Total score : "+run+"/"+wkt);		
		
	}
}
				
		
