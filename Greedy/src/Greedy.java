
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 * 
 * @author Cole Johnstone
 * java version of STL Pair class in C++
 *
 */
class Pair {
	
	int first, second;

	Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

class Sortbyfirst implements Comparator<Pair> {

	public int compare(Pair a, Pair b) {
		int ans = 1;
		if (a.first < b.first) {
			ans = -1;
		} else if (a.first == b.first && a.second < b.second) {
			ans = -1;
		}
		return ans;
	}
}

public class Greedy {
	public static void main(String[] args) {
		int instances;
		Scanner sc = new Scanner(System.in);
		instances = sc.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < instances; i++) {
			int jobs = sc.nextInt();
			ArrayList<Pair> schedules = new ArrayList<Pair>();
			for (int j = 0; j < jobs; j++) {
				int f, s;
				f = sc.nextInt();
				s = sc.nextInt();
				schedules.add(new Pair(f, s));
			}

			Collections.sort(schedules, new Sortbyfirst());
			int end = 0;
			int count = 0;
			for (Pair p : schedules) {
				if (p.first >= end) {
					end = p.second;
					count++;
				}
			}
			list.add(count);
		}
		sc.close();
		for (int i : list) {
			System.out.println(i);
		}
	}
}
