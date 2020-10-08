package package_main;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Report_20321273 extends Client_20321273 {

	public Report_20321273() {
	}

	public void generate_report(String clientName, ArrayList<String> propInfo, ArrayList<Double> rentInfo,ArrayList<Double> expenseInfo) {
		
		
//		for(String s : propInfo) {
//			System.out.println(s);
//		}
//		System.out.println("\nThis is now all expense to make the report");
//		for(Double s : expenseInfo) {
//			System.out.println(s);
//		}
		
		
		
		
		DecimalFormat df = new DecimalFormat("0.00");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 			// FETCHING CURRENT DATE
		LocalDateTime now = LocalDateTime.now();
		double[] _rent = fetchRent(rentInfo);										//FETCHING RENT FOR EACH PROPERTY FROM THE fetchRent FUNCTION
//		System.out.println("\nThis is now all rent to make the report");
//		for(Double s : _rent) {
//			System.out.println(s);
//		}
		String[] _prop = null;
		int x = 0;
		double T_rent = 0.00, T_expense = 0.00, T_fees = 0.00, T_net = 0.00;
		String n = clientName;

//		for (String n : clientName) {								//SUMMARY REPORT FOR EACH CLIENT(S)
//
//			_prop = null;
//			x=0; T_rent = 0.00; T_expense = 0.00; T_fees = 0.00; T_net = 0.00;
			
			System.out.println(
					"\n\n==========================================================================================================================================================");
			System.out.println("PORTFOLIO REPORT\nClient: " + n + "\nReport Generated: " + dtf.format(now) + "\n\n");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("|\t\tProperty|\t\t\tRent|\t\t\tExpenses|\t\tFee Rate|\t\tFees|\t\t\tNet|");

			for (String s : propInfo) {
				
				//CHECKING IF THE EXPENSE IS EMPTY, IF SO THEN ADDING '0' VALUE
				if(expenseInfo.isEmpty() || expenseInfo.size()==0 || expenseInfo.size()<x) {
					expenseInfo.add((double) 0.0);
				}
				
				//EXTRACTING INDIVIDUAL ITEM FROM EACH PROPERTY DATA
				String[] p = s.split(",");
				double fees = (_rent[x] * Double.parseDouble(p[p.length - 2]));
				double net = 0;
				//System.out.println("inside the inner loop\nAnd value of x is: "+x+"\nThe size of expense is:"+expenseInfo.size());
				if(expenseInfo.isEmpty() || expenseInfo.size()==0 || expenseInfo.size()<=x) {
					net = (_rent[x] - fees - 0);
					System.out.println("|"+p[1]+" ||\t\t\t"+df.format(_rent[x])+" ||\t\t\t0.00||\t\t\t"+df.format(Double.parseDouble(p[p.length -2]))+"||"
							+ "\t\t\t"+df.format(fees)+"||\t\t"+ df.format(net)+"||");
					
					T_expense = T_expense + 0.00;
				}else {
					net = (_rent[x] - fees - expenseInfo.get(x));
					System.out.println("|"+p[1]+" ||\t\t\t"+df.format(_rent[x])+" ||\t\t\t"+df.format(expenseInfo.get(x))+"||\t\t\t"+df.format(Double.parseDouble(p[p.length -2]))+"||"
							+ "\t\t\t"+df.format(fees)+"||\t\t"+ df.format(net)+"||");
				
					T_expense = T_expense + expenseInfo.get(x);
				}


				
				
				//ADDING TOTALS FOR LAST ROW
				T_rent = T_rent + _rent[x];
				T_fees = T_fees + fees;
				T_net = T_net + net;
				x++;
			}
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("|\t\t   TOTAL|\t\t\t    "+df.format(T_rent)+"|\t\t\t"+df.format(T_expense)+"|\t\t\t    -   |\t\t"+df.format(T_fees)+"|\t\t"+df.format(T_net)+"|");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println(
					"==========================================================================================================================================================\n\n");
			
//		}

	}

	// FETCHING ALL THE INDIVIDUAL SUM OF RENT FOR A EACH PROPERTY
	private double[] fetchRent(ArrayList<Double> rent_info) {
		ArrayList<Double> unique = (ArrayList<Double>) rent_info.stream().distinct().collect(Collectors.toList());
		int x = 0;
		double[] rent = new double[unique.size()];
		ArrayList<Double> temp = rent_info;
		for (Double t : unique) {
			int count = (int) Collections.frequency(temp, t);
			rent[x] = count * t;
			x++;
		}
		return rent;
	}

	// CALCULATING THE EXPENSE
	private float calc_net_amount(ArrayList<String> expense, ArrayList<String> rentinfo) {
		float net_amount = 0;
		System.out.println("==================================expenseinfo starts");
		for (String s : expense) {
			System.out.println(s);
		}
		System.out.println("==================================rentinfo starts");
		for (String s : rentinfo) {
			System.out.println(s);
		}
		return net_amount;
	}

}
