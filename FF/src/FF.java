import java.util.*;
public class FF {

	public static void main(String[] args) {
		int no_of_frames;
		int no_of_pages; 
		int frames[]=new int[10];
	    int pages[]=new int[30]; 
	    int temp[]=new int[10];
	    int flag1;
	    int flag2;
	    int flag3;
	    int pos=0; 
	    int max; 
	    int faults = 0;
	    int n;
	    int q;
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		int arr[]=new int [n+1];
		for (q = 0; q < n; q++)
		{
			faults = 0;

			no_of_frames = scan.nextInt();


			no_of_pages = scan.nextInt();



			for (int i = 0; i < no_of_pages; ++i) {
				pages[i] = scan.nextInt();
			}

			for (int i = 0; i < no_of_frames; ++i) {
				frames[i] = -1;
			}

			for (int i = 0; i < no_of_pages; ++i) {
				flag1 = flag2 = 0;

				for (int j = 0; j < no_of_frames; ++j) {
					if (frames[j] == pages[i]) {
						flag1 = flag2 = 1;
						break;
					}
				}

				if (flag1 == 0) {
					for (int j = 0; j < no_of_frames; ++j) {
						if (frames[j] == -1) {
							faults++;
							frames[j] = pages[i];
							flag2 = 1;
							break;
						}
					}
				}

				if (flag2 == 0) {
					flag3 = 0;

					for (int j = 0; j < no_of_frames; ++j) {
						temp[j] = -1;

						for (int k = i + 1; k < no_of_pages; ++k) {
							if (frames[j] == pages[k]) {
								temp[j] = k;
								break;
							}
						}
					}
					
					for (int j = 0; j < no_of_frames; ++j) {
						if (temp[j] == -1) {
							pos = j;
							flag3 = 1;
							break;
						}
					}

					if (flag3 == 0) {
						max = temp[0];
						pos = 0;

						for (int j = 1; j < no_of_frames; ++j) {
							if (temp[j] > max) {
								max = temp[j];
								pos = j;
							}
						}
					}
					frames[pos] = pages[i];
					faults++;
				}
			}

			arr[q] = faults;
		}

		for (q = 0; q < n; q++)
		{
			System.out.println( arr[q]);
		}
		scan.close();
	}

}
