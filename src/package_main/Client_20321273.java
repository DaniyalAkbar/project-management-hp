package package_main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Client_20321273 {

	public int getProp_id() {
		return prop_id;
	}

	public void setProp_id(int prop_id) {
		this.prop_id = prop_id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getWeekly_rent() {
		return weekly_rent;
	}

	public void setWeekly_rent(String weekly_rent) {
		this.weekly_rent = weekly_rent;
	}

	public String getManag_fee() {
		return manag_fee;
	}

	public void setManag_fee(String manag_fee) {
		this.manag_fee = manag_fee;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public ArrayList<String> getClients() {
		return clients;
	}

	public void setClients(ArrayList<String> clients) {
		this.clients = clients;
	}

	private int prop_id;
	private String client_id;
	private String add;
	private String suburb;
	private String state;
	private String postal;
	private String weekly_rent;
	private String manag_fee;
	private String fullName;
	public ArrayList<String> clients;

	Client_20321273() {
	}

	Client_20321273(String property, String fName) {
		String[] x = property.split("@");
		String[] _prop = x[1].split(",");

		this.setClient_id(_prop[0]);
		this.setAdd(_prop[1]);
		this.setSuburb(_prop[2]);
		this.setState(_prop[3]);
		this.setPostal(_prop[4]);
		this.setWeekly_rent(_prop[5]);
		this.setManag_fee(_prop[6]);
		this.setClient_id(_prop[7]);
		this.setFullName(fName);

		data();
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

	public ArrayList<String> fetchClients() throws IOException {
		Scanner sc = new Scanner(System.in);

		// READING CLIENT TEXT FILE
		ArrayList<String> clients = new ArrayList<String>();
		clients = readFile("src/package_main/txtFiles/clients.txt");
		if (!clients.isEmpty() && clients.get(0).equals("no file found")) {
			System.out.println(
					"No Clients File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: ");
			String newFileName = sc.next();
			clients = readFile("src/package_main/txtFiles/" + newFileName + ".txt");
			if (clients.get(0).equals("no file found")) {
				System.out.println("Still can't find the file. Creating a file named clients.txt");
				File file = new File("src/package_main/txtFiles/clients.txt");
				file.createNewFile();
				clients.clear();
			}
		}
		this.setClients(clients);
		return clients;
	}

	private void data() {
		System.out.println(
				"\n\n============================================================================================================\n");
		System.out.println("Property Address: " + this.getAdd() + "\t Weekly Rent: " + this.getWeekly_rent()
				+ "\t Owner's Full Name: " + this.getFullName());
		System.out.println(
				"\n============================================================================================================\n\n");
	}

	public float calculate_week_rent(float week_rent_amount, int week_num) {
		return (float) week_rent_amount * week_num;
	}

	// SORTING THE CLIENT RECORDS IN ASCENDING ORDER BY THEIR LAST NAME
	public ArrayList<String> getSortedClients() {
		ArrayList<String> unSortedClients = this.getClients();
		ArrayList<String> sortedClients = new ArrayList<String>();
		int n;
		String temp;
		n = this.getClients().size(); // ENTERING THE TOTAL NUMBER OF NAMES AVAILABLE
		String names[] = new String[n];
		ArrayList<String> full_name = new ArrayList<String>();
		for (String s : this.getClients()) {
			String[] t = s.split(",");
			full_name.add(t[1]);
		}
		int count = 0;
		for (String s : full_name) {
			String[] t = s.split(" ");
			names[count] = t[1];
			count++;
		}
		// names = clients.toArray(new String[this.getClients().size()]);

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (names[i].compareTo(names[j]) > 0) {
					temp = names[i];
					names[i] = names[j];
					names[j] = temp;
				}
			}
		}
		System.out.print("Names in Sorted Order:");
		for (int i = 0; i < n; i++) {
			for (String s : unSortedClients) {
				if (s.contains(names[i])) {
					if (!sortedClients.contains(s)) {
						sortedClients.add(s);
					}
				}
			}
		}

		return sortedClients;
	}

	
	
	// FETCH CLIENT ID USING PROPERTIES
	public ArrayList<String> fetchClientIDByProperty(ArrayList<String> property) {
		ArrayList<String> C_ID = new ArrayList<String>();
		for (String s : property) {
			String[] _prop = s.split(",");
			for (String c : this.getClients()) {
				String[] _client = c.split(",");
				if (_client[0].equals(_prop[_prop.length - 1])) {
					C_ID.add(_client[0]);
				}
			}
		}
		return C_ID;
	}
	
	
	// FETCH CLIENT NAME USING PROPERTIES
	public ArrayList<String> fetchClientNameByProperty(ArrayList<String> property) {
		
		ArrayList<String> c_id = fetchClientIDByProperty(property);
		ArrayList<String> C_Name = new ArrayList<String>();
		for (String id : c_id) {
			for (String c : this.getClients()) {
				String[] _client = c.split(",");
				if (_client[0].equals(id)) {
					C_Name.add(_client[1]);
				}
			}
		}
		return C_Name;
	}



}
