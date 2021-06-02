import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class practiceMatrix {

	public static void main(String[] args) {

//		String[] matrixInput = {"1,0,0,0,0","0,1,1,0,0","0,1,1,0,1", "0,0,0,1,0", "0,0,1,0,1"};
		String[] matrixInput = {"1,1,0,1,0","1,1,1,0,0","0,1,1,0,1", "0,0,0,1,0", "1,0,1,0,1"};
		ArrayList<ArrayList<Integer>> matrix = populateMatrix(matrixInput);
		printMatrix(matrix);
		
		System.out.println("Number of plans = " + getNumPlans(matrix));
	}

	private static int getNumPlans(ArrayList<ArrayList<Integer>> matrix) {
		
		ArrayList<Set<Integer>> planList = new ArrayList<Set<Integer>>();
		
		for(int i = 0; i < matrix.size(); i++) {	
			planList.add(i,new HashSet<Integer>());
			planList.get(i).add(i);
			for (int j = 0; j <=i; j++) {
				if(matrix.get(i).get(j) == 1) {
					union(planList, i, j);
				}
			}
		}
		return uniqueValues(planList);
	}

	private static int uniqueValues(ArrayList<Set<Integer>> planList) {
		Set<Set<Integer>> uniqueSet = new HashSet<Set<Integer>>();
		
		for(var element: planList) {
			uniqueSet.add(element);
		}
		return uniqueSet.size();
	}

	private static void union(ArrayList<Set<Integer>> planList, int i, int j) {
		Set<Integer> setI = planList.get(i);
		Set<Integer> setj = planList.get(j);
		if(setI == setj) {
			return;
		}
		
		setI.addAll(setj);
		for(var setVal: setI) {
			planList.set(setVal, setI);
		}
	}

	private static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
		for(int i = 0; i< matrix.size(); i++) {
			String row = "| ";
			for(int j = 0; j< matrix.get(i).size(); j++) {
				row = row + matrix.get(i).get(j) + "  ";
			}
			row = row + "|";
			System.out.println(row);
		}		
	}

	
	public static ArrayList<ArrayList<Integer>> populateMatrix(String[] matrixInput) { 
		int size = matrixInput.length;
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i< size; i++) {
			String[] row = matrixInput[i].split(",");
			matrix.add(i, new ArrayList<Integer>());
			for(int j = 0; j< size; j++) {
				int num = Integer.parseInt(row[j]);
				matrix.get(i).add(j, num);
			}
		}
		return matrix;
	}
	
	

}
