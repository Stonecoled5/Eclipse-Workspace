
import java.util.Scanner;
// macro to find maximum of two number
//#define max(a,b) (a > b) ? a : b 
// knapsack function
class knapsack{
public static int Knapsack(int[] item, int[] weight, int size, int target){
    int[][] dp = new int [size + 1][target + 1]; // dp table
    for (int i = 0; i <= size; i++)  // loop for item
    {
        for (int w = 0; w <= target; w++)  // loop for weight
        {
            if (i == 0 || w == 0) // if first 
                dp[i][w] = 0;
            else if(weight[i - 1] <= w) // otherwise two choice  
                dp[i][w] = Math.max(item[i - 1]  // keep it
                	+ dp[i - 1][w - weight[i - 1]], 
                	dp[i - 1][w]); // or leave it
            else // else only copy previous answer
                dp[i][w] = dp[i - 1][w];
        }
    }
    return dp[size][target]; // return solution
}

public static void main(String[] args)
{
	int itemNum;
	int cap;
	Scanner sc=new Scanner(System.in);
    int inst=sc.nextInt();
    StringBuilder sb = new StringBuilder();
	while(inst-->0){ 
		itemNum=sc.nextInt();
		cap=sc.nextInt(); // get n and weight
		int[] item = new int[itemNum];
		int[] weight= new int[itemNum]; // item and weight array
		for (int i = 0; i < itemNum; ++i) { // get input
			weight[i]=sc.nextInt();
			item[i]=sc.nextInt();
		}
		// call the knapsack and print the solution
		System.out.println(Knapsack(item,weight,itemNum,cap));
	}

}
}