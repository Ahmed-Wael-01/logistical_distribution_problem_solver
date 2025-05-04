import java.util.Scanner;

public class trans{

	public static int[][] take_cost(int s_len, int d_len)
	{
		Scanner input = new Scanner(System.in);
		int[][] cost = new int[s_len][d_len];

		for (int i = 0; i < s_len; i++)
		{
			for(int j = 0; j < d_len; j++)
			{
				System.out.println("Enter cost from demand "+i+" to supply "+j);
				cost[i][j] = input.nextInt();
			}
		}
		return (cost);

	}

	public static int[] take_sup()
        {
                Scanner input = new Scanner(System.in);
		System.out.print("Enter how many supplies: ");
                int len = input.nextInt();
                int[] sup = new int[len];

                for (int i = 0; i < len; i++)
                {
                        System.out.println("enter num "+i+" supply: ");
                        sup[i] = input.nextInt();
                }
                return (sup);
        }

	public static int[] take_dem()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter how many demands: ");
		int len = input.nextInt();
		int[] dem = new int[len];

		for (int i = 0; i < len; i++)
		{
			System.out.println("enter num "+i+" demand: ");
			dem[i] = input.nextInt();
		}
		return (dem);
	}

	public static int[][] northwest_corner(int[] sup, int[] dem)
	{
		int d = 0;
		int s = 0;
		int tmp;
		int [] squan = new int[sup.length];
		int [] dquan = new int[dem.length];
		int [][] init = new int[sup.length][dem.length];

		while(s <= sup.length - 1 && d <= dem.length - 1){
			if (dem[d] - dquan[d] < sup[s] - squan[s])
			{
				tmp = dem[d] - dquan[d];
				init[s][d] = tmp;
				squan[s] += tmp;
				dquan[d] += tmp;
				d++;
			}
			else if (dem[d] - dquan[d] > sup[s] - squan[s])
			{
				tmp = sup[s] - squan[s];
				init[s][d] = tmp;
				squan[s] += tmp;
				dquan[d] += tmp;
				s++;
			}
			else
			{
				tmp = sup[s] - squan[s];
				init[s][d] = tmp;
                                squan[s] += tmp;
                                dquan[d] += tmp;
                                s++;
				d++;
			}
		}
		return (init);
	}

	public static int evaluate_cost(int [][] graph, int [][] cost)
	{
		int full_cost = 0;

		for(int i = 0; i < graph.length; i++)
			for(int j = 0; j < graph[i].length; j++)
				full_cost += graph[i][j] * cost[i][j];
		return (full_cost);
	}

	public static int [] append(int [] arr, int elem)
	{
		int [] new_arr = new int [arr.length + 1];
		int i;

		for(i = 0; i < arr.length; i++)
			new_arr[i] = arr[i];
		new_arr[i] = elem;
		return(new_arr);
	}

	public static int [][] append(int [][] pArr, int [] arr)
	{
		int [][] new_arr = new int [pArr.length + 1][arr.length];
		int i;

		for(i = 0; i < pArr.length; i++)
			new_arr[i] = pArr[i];
		new_arr[i] = arr;
		return(new_arr);
	}

	public static int [][][] append(int [][][] pArr, int [][] arr)
	{
		int [][][] new_arr = new int [pArr.length + 1][arr.length][0];
                int i;

                for(i = 0; i < pArr.length; i++)
                        new_arr[i] = pArr[i];
                new_arr[i] = arr;
                return(new_arr);
	}

	public static int[][] find_cpath(int [][] graph, int [][] arr)
	{
		int s_axis;
		int d_axis;
		int s_length = graph.length;
		int d_length = graph[0].length;

		s_axis = arr[arr.length - 1][0];
		d_axis = arr[arr.length - 1][1];

		for(int i = -1; (i + s_axis) >= 0; i--)
		{
			if(graph[s_axis + i][d_axis] == 0 || )
				continue;
			if(find_cpath(graph, append(arr, new int[] {s_axis + i, d_axis})) != null)
				return find_cpath(graph, append(arr, new int[] {s_axis + i, d_axis}));
		}
		return null;
	}

	public static int[][] stepping_stone(int [][] graph, int [][] cost)
	{
		int [][][]closed_path = new int [0][0][2];
		int [] path_evals = new int [0];
		int [][] tmp_arr = new int [1][2];
		boolean done = false;
		int full_cost = evaluate_cost(graph, cost);

		while(!done)
		{
			for(int i = 0; i < graph.length; i++)
				for(int j = 0; j < graph[i].length; j++)
				{
					if (graph[i][j] == 0)
					{
						tmp_arr[0][0] = i;
						tmp_arr[0][1] = j;
						if (find_cpath(graph, tmp_arr) == null)
							continue;
					}
				}
		}
		return null;	
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int [] supply = take_sup();
		int [] demand = take_dem();
		int [][] init = northwest_corner(supply, demand);
		int [][] cost = take_cost(supply.length, demand.length);

		int full_cost = evaluate_cost(init, cost);

		for(int i = 0; i < init.length; i++)
		{
			for(int j = 0; j < init[i].length; j++)
				System.out.print("|"+init[i][j]+"|");
			System.out.print("\n");
		}
		System.out.print("\n" + full_cost + "\n");
	}
}
