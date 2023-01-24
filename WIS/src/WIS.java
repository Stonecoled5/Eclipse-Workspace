
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
    Logic of the program:
    1)First of all sort the array in ascending order according to the end time
    2)Now we need to apply a recursion step with a base of
    if(n == 1) return arr[0];
    Otherwise
    return Max(Maximum profit by excluding current job, Maximum profit by including current job)
 */
//Our main logic
class WIS{
  public static int binarySearch(Job[] arr, int idx){
      int low = 0, high = idx - 1;

      while (low <= high){
          int mid = low+(high - low)/2;
          if (arr[mid].j <= arr[low].i){
              if (arr[mid + 1].j <= arr[idx].i)
                  low = mid+1;
              else
                  return mid;
          }
          else
              high = mid-1;
      }
      return -1;
  }
  public static int scheduleJobs(Job[] arr){
      Arrays.sort(arr, new MyComparator());
      int n = arr.length;
      int[] dp = new int[n];
      dp[0] = arr[0].k;
      
      for (int i=1; i<n; i++){
          int val = arr[i].k;
          int low = binarySearch(arr, i);
          if (low != -1)
              val += dp[low];
          dp[i] = Math.max(val, dp[i-1]);
      }
      return dp[n-1];
  }
  public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int inst=Integer.parseInt(sc.next());
      StringBuilder sb = new StringBuilder();
      while (inst-- > 0){
          int numJob=Integer.parseInt(sc.next());
          Job[] arr= new Job[numJob];
          for (int l=0; l<numJob; l++){
              int i = Integer.parseInt(sc.next());
              int j = Integer.parseInt(sc.next());
              int k = Integer.parseInt(sc.next());
              
              arr[l] = new Job(i, j, k);
          }
          sb.append(scheduleJobs(arr)).append("\n");
      }
      System.out.println(sb);
      sc.close();
  }
}

class Job extends WIS{
    int i;
    int j;
    int k;

    public Job(int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }
}

class MyComparator extends WIS implements Comparator<Job>{
    public int compare(Job a, Job b){
        return Integer.compare(a.j, b.j);
    }
}
