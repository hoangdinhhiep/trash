import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PizzaLocation {
	static int T, ans, K, R, M, N;
	static int[][] locations, solitaires, luuLocation;
	static int[] visit, luu;

	static int calRadius(int i, int j) {
		int calRadius = 0;
		int x = 0;
		int y = 0;
		if (luuLocation[i][0] > solitaires[j][0]) {
			x = luuLocation[i][0] - solitaires[j][0];
		} else {
			x = solitaires[j][0] - luuLocation[i][0];
		}
		if (luuLocation[i][1] > solitaires[j][1]) {
			y = luuLocation[i][1] - solitaires[j][1];
		} else {
			y = solitaires[j][1] - luuLocation[i][1];
		}
		calRadius = x + y;
		return calRadius;
	}

	static int calPeople() {
		luu = new int[N];
		int countPeople = 0;
		int radius;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < N; j++) {
				if (luu[j] == 0) {
					radius = calRadius(i, j);
					if (radius <= R) {
						luu[j] = solitaires[j][2];
						countPeople += solitaires[j][2];
					}
				}
			}
		}
		return countPeople;
	}

	static void Run(int step) {
		if (step == K) {
			int people = calPeople();
			if (people > ans) {
				ans = people;
			}
			return;
		}

		for (int i = step; i < locations.length; i++) {
			if (visit[i] == 0) {
				visit[i] = 1;
				luuLocation[step][0] = locations[i][0];
				luuLocation[step][1] = locations[i][1];
				Run(step + 1);
				visit[i] = 0;
				luuLocation[step][0] = 0;
				luuLocation[step][1] = 0;
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("pizzaLocation.txt"));
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			K = sc.nextInt();
			R = sc.nextInt();
			M = sc.nextInt();
			visit = new int[M];
			locations = new int[M][2];
			luuLocation = new int[K][2];
			for (int i = 0; i < M; i++) {
				locations[i][0] = sc.nextInt();
				locations[i][1] = sc.nextInt();
			}

			N = sc.nextInt();
			
			solitaires = new int[N][3];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 3; j++) {
					solitaires[i][j] = sc.nextInt();
				}
			}
			Run(0);
			System.out.println(ans);
		}

	}

}
