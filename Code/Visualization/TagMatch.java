import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TagMatch {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		ArrayList<String> Languages = new ArrayList<>();
		ArrayList<String> StackInput = new ArrayList<>();
		ArrayList<String> GitInput = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String Tagline;
			while ((Tagline = br.readLine()) != null) {
				Languages.add(Tagline);
			}
			br.close();
		}
		try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
			String Stackline;
			while ((Stackline = br.readLine()) != null) {
				StackInput.add(Stackline);
			}
			br.close();
		}
		System.out.println("Processing languages"); // Test Display code
		try (BufferedWriter BL = new BufferedWriter(new FileWriter("TagCleanLog.log"))) { // Test Display code
			try (BufferedWriter BW = new BufferedWriter(new FileWriter(args[2]))) {							
				BL.write("Initating Processing....."); // Test Display code
				BL.newLine(); // Test Display code
				int i = 0, j = 0; // Test Display code
				for (String Tag : Languages) {
					i++; // Test Display code
					BL.write("Searching for > " + Tag); // Test Display code
					BL.newLine(); // Test Display code
					System.out.println("Searching for > " + Tag); // Test
																	// Display
																	// code
					String TempYear = "";
					int Questions = 0, Answers = 0, Views = 0, Score = 0, AddFlag = 0;
					for (String Input : StackInput) {
						j++; // Test Display code
						if (Check(Tag, Input).equals(Tag)) {
							if (TempYear.equals("")) {
								TempYear = Input.split(",")[0];
							}
							if (!TempYear.equals("")
									&& !TempYear.equals(Input.split(",")[0])) {
								BW.write(TempYear + "," + Tag + "," + Questions
										+ "," + Answers + "," + Views + ","
										+ Score);
								BW.newLine();

								TempYear = Input.split(",")[0];
								Questions = Integer
										.parseInt(Input.split(",")[2]);
								Answers = Integer.parseInt(Input.split(",")[3]);
								Views = Integer.parseInt(Input.split(",")[4]);
								Score = Integer.parseInt(Input.split(",")[5]
										.trim());
								AddFlag = 0;
							} else if (TempYear.equals(Input.split(",")[0])) {
								Questions += Integer
										.parseInt(Input.split(",")[2]);
								Answers += Integer
										.parseInt(Input.split(",")[3]);
								Views += Integer.parseInt(Input.split(",")[4]);
								Score += Integer.parseInt(Input.split(",")[5]
										.trim());
								AddFlag = 1;
							}

							BL.write("Found > " + Input + " > at Line: " + j); // Test Display code
							BL.newLine(); // Test Display code
						}
					}
					if (AddFlag == 1) {
						BW.write(TempYear + "," + Tag + "," + Questions + ","
								+ Answers + "," + Views + "," + Score);
						BW.newLine();
					}
					j = 0; // Test Display code
					Questions = 0;
					Answers = 0;
					Views = 0;
					Score = 0;
					TempYear = "";
				}
				BL.write("Finishing Cleanup....."); // Test Display code
				BW.close();
			}
			
		try (BufferedReader br = new BufferedReader(new FileReader(args[2]))) {
			String Tagline;
			StackInput.clear();
			while ((Tagline = br.readLine()) != null) {
				StackInput.add(Tagline);
			}
			br.close();
			Collections.sort(StackInput);
		}

		try (BufferedReader br = new BufferedReader(new FileReader(args[3]))) {
			String Tagline;
			while ((Tagline = br.readLine()) != null) {
				GitInput.add(Tagline);
			}
			br.close();
			Collections.sort(GitInput);
		}

			System.out.println("Processing languages"); // Test Display code
			try (BufferedWriter BW = new BufferedWriter(new FileWriter("FinalOutput"))) {
				BW.write("year,language,total_questions,total_answers,total_views,total_score,total_project");
				BW.newLine();
				BL.newLine(); // Test Display code
				BL.write("Initating Merg....."); // Test Display code
				BL.newLine(); // Test Display code
				System.out.println("Initating Merging");// Test Display code
				int NoMatchFlag = 0;
				for (String GS : GitInput) {
					BL.write("Merging >" + GS); // Test Display code
					BL.newLine(); // Test Display code					
					System.out.println("Merging > " + GS);// Test Display code
					NoMatchFlag = 0;
					for (String SS : StackInput) {
						if(SS.split(",")[0].equals(GS.split(",")[0]) && SS.split(",")[1].equals(GS.split(",")[1])){
							BW.write(SS + "," + GS.split(",")[2]);
							BW.newLine();
							BL.write("With >" + SS); // Test Display code
							BL.newLine(); // Test Display code
							NoMatchFlag = 1;
							break;
						}
					}
					
					
//					Run This code when GitHub Entry are required even when there is no Stack corresponding entry
//					if (NoMatchFlag != 1) {
//						BW.write( GS.split(",")[0] + "," + GS.split(",")[1] + ",0,0,0,0" + GS.split(",")[2]);
//						BW.newLine();
//						BL.write("No Match inserting >" + GS.split(",")[0] + "," + GS.split(",")[1] + ",0,0,0,0," + GS.split(",")[2]); // Test Display code
//						BL.newLine(); // Test Display code
//					}
					
				}				
				BW.close();
			}
			BL.write("Finishing Processing....."); // Test Display code
			BL.newLine(); // Test Display code
			BL.close(); // Test Display code
		}
		System.out.println("File Writing finished terminating now..");
	}

	public static String Check(String a, String b) {

		if (b.split(",")[1].equals(a)) {
			return a;
		} else {
			int index = 0;
			int flag = 0;
			index = b.split(",")[1].indexOf(a);
			if (index != -1) {
				if (index == 0
						&& nextmatch(b.split(",")[1], 1, index, a.length())) {
					flag = 1;
				} else if (index == (b.split(",")[1].length() - a.length())
						&& nextmatch(b.split(",")[1], 2, index, a.length())) {
					flag = 1;
				} else if (index != 0
						&& index != (b.split(",")[1].length() - a.length())
						&& nextmatch(b.split(",")[1], 3, index, a.length())) {
					flag = 1;
				}
			}

			if (flag == 1) {
				return a;
			} else
				return b.split(",")[1];
		}
	}

	public static Boolean nextmatch(String A, int match, int index, int len) {

		if (match == 1) {
			char next = A.charAt(index + len);
			if (next == ' ' || next == '-' || next == '_') {
				return true;
			} else
				return false;

		} else if (match == 2) {
			char next = A.charAt(index + -1);
			if (next == ' ' || next == '-' || next == '_') {
				return true;
			} else
				return false;
		} else if (match == 3) {
			char next = A.charAt(index + len);
			char prev = A.charAt(index - 1);
			if ((next == ' ' || next == '-' || next == '_')
					&& (prev == ' ' || prev == '-' || prev == '_')) {
				return true;
			} else
				return false;
		} else
			return false;

	}
}
