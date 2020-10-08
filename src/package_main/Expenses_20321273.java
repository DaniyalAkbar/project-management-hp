package package_main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Expenses_20321273 {

	
	Expenses_20321273(){	}
	
	public ArrayList<String> expenses;
	
	
	
	
	public ArrayList<String> getExpenses() {
		return expenses;
	}
	public void setExpenses(ArrayList<String> expenses) {
		this.expenses = expenses;
	}



	
	
	
	
	
	
	
	
	
	// READING FROM FILES FUNCTION
		public static ArrayList<String> readFile(String fileName) {
			ArrayList<String> al = new ArrayList<String>();
			try {
				File myObj = new File(fileName);
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					al.add(data);
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				if (e.toString().contains("No such file or directory")) {
					al.add("no file found");
					return al;
				}
			}
			return al;
		}
		
		
		public ArrayList<String> fetchExpenses() throws IOException{
			Scanner sc = new Scanner(System.in);

			// READING EXPENSES TEXT FILE
			ArrayList<String> clients = new ArrayList<String>();
			clients = readFile("src/package_main/txtFiles/expenses.txt");
			if (!clients.isEmpty() && clients.get(0).equals("no file found")) {
				System.out.println("No Expenses File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: ");
				String newFileName = sc.next();
				clients = readFile("src/package_main/txtFiles/" + newFileName + ".txt");
				if (clients.get(0).equals("no file found")) {
					System.out.println("Still can't find the file. Creating a file named expenses.txt");
					File file = new File("src/package_main/txtFiles/expenses.txt");
					file.createNewFile();
					clients.clear();
				}
			}
			this.setExpenses(clients);
			return clients;
		}
		
		
		
		
		
		
		
		
		
		
	
		
//		public ArrayList<Double> getExpense_info(ArrayList<String> propInfo){
//			ArrayList<Double> expense_info = new ArrayList();
//			if(!propInfo.isEmpty() ) {				
//				for(String _rent : this.getExpenses()) {							//OUTTER LOOP TRAVERSING INDIVIDUAL EXPENSE
//					String[] r = _rent.split(",");
//					for(String _prop : propInfo) {								//TRAVERSING INDIVIDUAL EXPENSE
//						String[] p = _prop.split(",");
//						if(r[0].equals(p[0])) {									//MATCHING THE PROPERTY ID FROM INDIVIDUAL EXPENSE TO THE PARAMETER ID VALUE
//							expense_info.add(Double.parseDouble(r[2]));
//						}
//					}
//				}
//				return expense_info;
//			}
//			return null;														//IF NO VALUE WAS MATCHED AND FOUND THEN RETURN A BLANK ARRAYLIST
//		}
		
		
			
		
		
		
		
		
		public ArrayList<ArrayList<Double>> getExpense_info(ArrayList<ArrayList<String>> propInfo){
			
			ArrayList<ArrayList<Double>> expense_info = new ArrayList();
			if(!propInfo.isEmpty() ) {
				for(ArrayList<String> _propArr : propInfo) {
					expense_info.add(help_GetExpense(_propArr));
				}
			}
			return expense_info;														//IF NO VALUE WAS MATCHED AND FOUND THEN RETURN A BLANK ARRAYLIST
			}

			private ArrayList<Double> help_GetExpense(ArrayList<String> _propArr){
				
				ArrayList<Double> _expense_info = new ArrayList<Double>();
				for(String _rent : this.getExpenses()) {							//OUTTER LOOP TRAVERSING INDIVIDUAL RENT
					String[] r = _rent.split(",");
					for(String _prop : _propArr) {								//TRAVERSING INDIVIDUAL RENT
						String[] p = _prop.split(",");
						if(r[0].equals(p[0])) {									//MATCHING THE PROPERTY ID FROM INDIVIDUAL RENT TO THE PARAMETER ID VALUE
							_expense_info.add(Double.parseDouble(r[2]));
						}
					}
				}
				return _expense_info;
			}
		
		
		
		
		
		
		
		
}
