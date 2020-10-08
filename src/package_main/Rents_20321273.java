package package_main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rents_20321273 {

	Rents_20321273() {
	}

	public ArrayList<String> rents;

	public ArrayList<String> getRents() {
		return rents;
	}

	public void setRents(ArrayList<String> rents) {
		this.rents = rents;
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

	public ArrayList<String> fetchRents() throws IOException {
		Scanner sc = new Scanner(System.in);

		// READING RENTS TEXT FILE
		ArrayList<String> clients = new ArrayList<String>();
		clients = readFile("src/package_main/txtFiles/rents.txt");
		if (!clients.isEmpty() && clients.get(0).equals("no file found")) {
			System.out.println(
					"No Rensts File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: ");
			String newFileName = sc.next();
			clients = readFile("src/package_main/txtFiles/" + newFileName + ".txt");
			if (clients.get(0).equals("no file found")) {
				System.out.println("Still can't find the file. Creating a file named rents.txt");
				File file = new File("src/package_main/txtFiles/rents.txt");
				file.createNewFile();
				clients.clear();
			}
		}
		this.setRents(clients);
		return clients;
	}

//		public ArrayList<Double> getRent_info(ArrayList<String> propInfo){
//			ArrayList<Double> rent_info = new ArrayList();
//			if(!propInfo.isEmpty() ) {				
//				for(String _rent : this.getRents()) {							//OUTTER LOOP TRAVERSING INDIVIDUAL RENT
//					String[] r = _rent.split(",");
//					for(String _prop : propInfo) {								//TRAVERSING INDIVIDUAL RENT
//						String[] p = _prop.split(",");
//						if(r[0].equals(p[0])) {									//MATCHING THE PROPERTY ID FROM INDIVIDUAL RENT TO THE PARAMETER ID VALUE
//							rent_info.add(Double.parseDouble(r[1]));
//						}
//					}
//				}
//				return rent_info;
//			}
//			return null;														//IF NO VALUE WAS MATCHED AND FOUND THEN RETURN A BLANK ARRAYLIST
//		}

	
	
	
	
	public ArrayList<ArrayList<Double>> getRent_info(ArrayList<ArrayList<String>> propInfo){
	ArrayList<ArrayList<Double>> rent_info = new ArrayList();
	if(!propInfo.isEmpty() ) {				
		for(ArrayList<String> _propArr : propInfo) {
			rent_info.add(help_GetRent(_propArr));
		}
	}
	return rent_info;														//IF NO VALUE WAS MATCHED AND FOUND THEN RETURN A BLANK ARRAYLIST
	}

	private ArrayList<Double> help_GetRent(ArrayList<String> _propArr){
		
		ArrayList<Double> _rent_info = new ArrayList<Double>();
		for(String _rent : this.getRents()) {							//OUTTER LOOP TRAVERSING INDIVIDUAL RENT
			String[] r = _rent.split(",");
			for(String _prop : _propArr) {								//TRAVERSING INDIVIDUAL RENT
				String[] p = _prop.split(",");
				if(r[0].equals(p[0])) {									//MATCHING THE PROPERTY ID FROM INDIVIDUAL RENT TO THE PARAMETER ID VALUE
					_rent_info.add(Double.parseDouble(r[1]));
				}
			}
		}
		return _rent_info;
	}
	
	
	
	
	public double fetchMonetaryAmount(String propID) {
		String[] propertyID = propID.split("@"); 
		double amount = 0; 
		if(!(propID.equals(null))) {
			for(String _rents : this.getRents()) {
				String[] r = _rents.split(",");
				if(r[0].equals(propertyID[1])) {
					amount = Double.parseDouble(r[1]);			//FETCHING THE MONETARY AMOUNT FOR THE PROPERTY
				}
			}
		}
		return amount;
	}
	
	
	
	
	
	
	
	
	
}
