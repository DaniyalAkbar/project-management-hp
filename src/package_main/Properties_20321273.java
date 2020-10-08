package package_main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Properties_20321273 {

	
	Properties_20321273(){	}
	
	public ArrayList<String> properties;
	
	
	
	//GETTER / SETTER
	public void setProperties(ArrayList<String> properties) {
		this.properties = properties;
	}
	public ArrayList<String> getProperties() {
		return this.properties;
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
		
		
		public ArrayList<String> fetchProperties() throws IOException{
			Scanner sc = new Scanner(System.in);

			// READING PROPERTIES TEXT FILE
			ArrayList<String> clients = new ArrayList<String>();
			clients = readFile("src/package_main/txtFiles/properties.txt");
			if (!clients.isEmpty() && clients.get(0).equals("no file found")) {
				System.out.println("No Property File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: ");
				String newFileName = sc.next();
				clients = readFile("src/package_main/txtFiles/" + newFileName + ".txt");
				if (clients.get(0).equals("no file found")) {
					System.out.println("Still can't find the file. Creating a file named properties.txt");
					File file = new File("src/package_main/txtFiles/properties.txt");
					file.createNewFile();
					clients.clear();
				}
			}
			this.setProperties(clients);
			return clients;
		}
		
		
		
		
		
		
		
//		public ArrayList<String> getProperty_info(ArrayList<String> clientID, ArrayList<String> clientName){
//			ArrayList<String> prop_info = new ArrayList<String>();
//			if(!clientID.isEmpty() && !clientName.isEmpty()) {				
//				for(String s : this.getProperties()) {							//OUTTER LOOP TRAVERSING INDIVIDUAL PROPERTY
//					String[] _prop = s.split(",");
//					for(String id : clientID) {									//INNER LOOP TO MATCH THE CLIENT ID FROM INDIVIDUAL PROPERTY TO THE PARAMETER ID VALUE
//						if(_prop[_prop.length - 1].equals(id)) {
//							prop_info.add(s);
//						}
//					}
//				}
//				return prop_info;
//			}
//			return null;														//IF NO VALUE WAS MATCHED AND FOUND THEN RETURN A BLANK ARRAYLIST
//		}
//		
		
//		public ArrayList<String> getProperty_info(ArrayList<String> clientID, ArrayList<String> clientName){
//			ArrayList<String> prop_info = new ArrayList<String>();
//			if(!clientID.isEmpty() && !clientName.isEmpty()) {				
//				for(String id : clientID) {									//INNER LOOP TO MATCH THE CLIENT ID FROM INDIVIDUAL PROPERTY TO THE PARAMETER ID VALUE
//					for(String s : this.getProperties()) {							//OUTTER LOOP TRAVERSING INDIVIDUAL PROPERTY
//						String[] _prop = s.split(",");						
//						if(_prop[_prop.length - 1].equals(id)) {
//							prop_info.add(s);
//						}
//					}
//
//				}
//
//				return prop_info;
//			}
//			return null;														//IF NO VALUE WAS MATCHED AND FOUND THEN RETURN A BLANK ARRAYLIST
//		}
		
		
		
		//NESTED ARRAYLIST FOR PROPERTIES
		
		public ArrayList<ArrayList<String>> getProperty_info(ArrayList<String> clientID, ArrayList<String> clientName){
			
			ArrayList<ArrayList<String>> prop_info = new ArrayList<ArrayList<String>>();
			if(!clientID.isEmpty() && !clientName.isEmpty()) {				
				for(String id : clientID) {									//INNER LOOP TO MATCH THE CLIENT ID FROM INDIVIDUAL PROPERTY TO THE PARAMETER ID VALUE
					ArrayList<String> temp = new ArrayList<String>();
					for(String s : this.getProperties()) {							//OUTTER LOOP TRAVERSING INDIVIDUAL PROPERTY
						String[] _prop = s.split(",");						
						if(_prop[_prop.length - 1].equals(id)) {
							temp.add(s);
						}
					}
					prop_info.add(temp);
				}

				return prop_info;
			}
			return null;														//IF NO VALUE WAS MATCHED AND FOUND THEN RETURN A BLANK ARRAYLIST
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
