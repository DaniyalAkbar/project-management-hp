package package_main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Save_20321273 {

	public Save_20321273() {
	}

	
	//SAVING NEW RENT ENTERY TO THE RENTS.TXT FILE
	public void saveRents(ArrayList<String> rents) {
		if (!rents.isEmpty()) {
			try (FileWriter f = new FileWriter("src/package_main/txtFiles/rents.txt", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
				
				for(String s : rents) {
					p.println(s);					
				}

			} catch (IOException i) {
				i.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Rent Saved Successfully!");
		}
	}
	
	
	
	//SAVING NEW RENT EXPENSE TO THE EXPENSES.TXT FILE
		public void saveExpense(ArrayList<String> expenses) {
			if (!expenses.isEmpty()) {
				try (FileWriter f = new FileWriter("src/package_main/txtFiles/expenses.txt", true);
					BufferedWriter b = new BufferedWriter(f);
					PrintWriter p = new PrintWriter(b);) {
					
					for(String e : expenses) {
						p.println(e);					
					}

				} catch (IOException i) {
					i.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Expenses Saved Successfully!");
			}
		}
		
		
}
