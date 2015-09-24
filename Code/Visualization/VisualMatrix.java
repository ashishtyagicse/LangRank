import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class VisualMatrix {

	public static void main(String[] args) throws IOException {

		float MaxQuestion = 0, MaxAnswer = 0, MaxView = 0, MaxProject = 0;
		long YMaxQuestion = 0, YMaxAnswer = 0, YMaxView = 0, YMaxProject = 0;
		float TQuestion = 0, TAnswer = 0, TView = 0, TProject = 0, TOverall = 0;
		ArrayList<String> FinalInput = new ArrayList<>();
		ArrayList<String> FinalInputMid = new ArrayList<>();
		ArrayList<String> YearMaxInput = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String Line;
			while ((Line = br.readLine()) != null) {
				FinalInput.add(Line);
			}
			br.close();
		}
		
		String TempYear = "";
		try (BufferedWriter BL = new BufferedWriter(new FileWriter("VisualMatLog.log"))) { 
			BL.write("Initating Processing....."); 
			BL.newLine(); 
			System.out.println("Initating Processing.....");
			for (String SI : FinalInput) {
				if (SI.startsWith("year")) {
					continue;
				}
				if (TempYear.equals("")) {
					TempYear = SI.split(",")[0];
					System.out.println("Init Year:" + TempYear);
				}
				if (!TempYear.equals(SI.split(",")[0])) {
					YearMaxInput.add(TempYear + ","	+ YMaxQuestion + "," + YMaxAnswer + "," + YMaxView + "," + YMaxProject);
					BL.write("New year " + TempYear + " > Ques:"
							+ YMaxQuestion + ", Ans:" + YMaxAnswer + ", View:"
							+ YMaxView + ", Proj:" + YMaxProject); 
					BL.newLine(); 
					System.out.println("New year " + TempYear + " > Ques:"
							+ YMaxQuestion + ", Ans:" + YMaxAnswer + ", View:"
							+ YMaxView + ", Proj:" + YMaxProject);
					TempYear = SI.split(",")[0];
					YMaxQuestion = 0;
					YMaxAnswer = 0;
					YMaxView = 0;
					YMaxProject = 0;
				}
				YMaxQuestion += Long.parseLong(SI.split(",")[2]);
				YMaxAnswer += Long.parseLong(SI.split(",")[3]);
				YMaxView += Long.parseLong(SI.split(",")[4]);
				YMaxProject += Long.parseLong(SI.split(",")[6].trim());
			}
			YearMaxInput.add(TempYear + ","	+ YMaxQuestion + "," + YMaxAnswer + "," + YMaxView + "," + YMaxProject);
			BL.write("New year " + TempYear + " > Ques:"
					+ YMaxQuestion + ", Ans:" + YMaxAnswer + ", View:"
					+ YMaxView + ", Proj:" + YMaxProject); 
			BL.newLine();
			
			for (String SI : FinalInput) {
				if (SI.startsWith("year")) {
					FinalInputMid.add(SI);
				}
				for (String YearCounts : YearMaxInput) {
					String year = YearCounts.split(",")[0];					
					if (year.equals(SI.split(",")[0])){						
						TQuestion = Float.parseFloat(SI.split(",")[2]) / Float.parseFloat(YearCounts.split(",")[1]) * 100;
						TAnswer = Float.parseFloat(SI.split(",")[3]) / Float.parseFloat(YearCounts.split(",")[2]) * 100;
						TView = Float.parseFloat(SI.split(",")[4]) / Float.parseFloat(YearCounts.split(",")[3]) * 100;
						TProject = Float.parseFloat(SI.split(",")[6].trim()) / Float.parseFloat(YearCounts.split(",")[3]) * 100;						
						FinalInputMid.add(year + "," + SI.split(",")[1] + "," + TQuestion + "," + TAnswer + "," + TView + "," + SI.split(",")[5] + "," + TProject);
						break;
					}
				}
			}
			
			BL.write("Calculating...");
			BL.newLine();
			BL.write("Student ((Question % * .25) + (Answer % * .25) + (View % * .25) + (Project % * .25)) * 10");
			BL.newLine();
			BL.write("Company ((Question % * .20) + (Answer % * .30) + (View % * .40) + (Project % * .10)) * 10");
			BL.newLine();
			BL.write("Startup ((Question % * .30) + (Answer % * .20) + (View % * .20) + (Project % * .30)) * 10");
			BL.newLine();
			BL.write("Training ((Question % * .45) + (Answer % * .05) + (View % * .45) + (Project % * .05)) * 10");
			BL.newLine();
			BL.newLine();
			DecimalFormat df = new DecimalFormat("#");
	        df.setMaximumFractionDigits(6);
			try (BufferedWriter BW1 = new BufferedWriter(new FileWriter(args[1] + "_Student.csv"))) {
				BufferedWriter BW2 = new BufferedWriter(new FileWriter(args[1] + "_Company.csv"));
				BufferedWriter BW3 = new BufferedWriter(new FileWriter(args[1] + "_Startup.csv"));
				BufferedWriter BW4 = new BufferedWriter(new FileWriter(args[1] + "_Training.csv"));
				BL.write("Initating Processing....."); // Test Display code
				BL.newLine(); // Test Display code
				for (String SO : FinalInputMid) {
					if (SO.startsWith("year")) {
						BW1.write(SO + ",Overall");
						BW1.newLine();
						BW2.write(SO + ",Overall");
						BW2.newLine();
						BW3.write(SO + ",Overall");
						BW3.newLine();
						BW4.write(SO + ",Overall");
						BW4.newLine();
						continue;
					}
					
					MaxQuestion = Float.parseFloat(SO.split(",")[2]);
					MaxAnswer = Float.parseFloat(SO.split(",")[3]);
					MaxView = Float.parseFloat(SO.split(",")[4]);
					MaxProject = Float.parseFloat(SO.split(",")[6].trim());
					
					
					TQuestion = (float) (MaxQuestion * 0.25) ;
					TAnswer = (float) (MaxAnswer * 0.25);
					TView = (float) (MaxView * 0.25);
					TProject = (float) (MaxProject * 0.25);
					TOverall = (TQuestion + TAnswer + TView + TProject)*10;
					BW1.write(SO.split(",")[0] + "," + SO.split(",")[1] + "," + df.format(TQuestion) + "," + df.format(TAnswer) + "," + df.format(TView) + ","  + SO.split(",")[5] + "," + df.format(TProject) + "," + df.format(TOverall));
					BW1.newLine();					
					BL.write("Student> " + SO.split(",")[0] + "," + SO.split(",")[1] + "," + df.format(TQuestion) + "," + df.format(TAnswer) + "," + df.format(TView) + ","  + SO.split(",")[5] + "," + df.format(TProject) + "," + df.format(TOverall));
					BL.newLine();
					
					TQuestion = (float) (MaxQuestion * 0.10);
					TAnswer = (float) (MaxAnswer * 0.10);
					TView = (float) (MaxView * 0.40);
					TProject = (float) (MaxProject * 0.40);
					TOverall = (TQuestion + TAnswer + TView + TProject)*10;
					BW2.write(SO.split(",")[0] + "," + SO.split(",")[1] + "," + df.format(TQuestion) + "," + df.format(TAnswer) + "," + df.format(TView) + ","  + SO.split(",")[5] + "," + df.format(TProject) + "," + df.format(TOverall));
					BW2.newLine();
					BL.write("Company> " + SO.split(",")[0] + "," + SO.split(",")[1] + "," + df.format(TQuestion) + "," + df.format(TAnswer) + "," + df.format(TView) + ","  + SO.split(",")[5] + "," + df.format(TProject) + "," + df.format(TOverall));
					BL.newLine();
					
					TQuestion = (float) (MaxQuestion * 0.20) ;
					TAnswer = (float) (MaxAnswer * 0.10);
					TView = (float) (MaxView * 0.20);
					TProject = (float) (MaxProject * 0.50);
					TOverall = (TQuestion + TAnswer + TView + TProject)*10;
					BW3.write(SO.split(",")[0] + "," + SO.split(",")[1] + "," + df.format(TQuestion) + "," + df.format(TAnswer) + "," + df.format(TView) + ","  + SO.split(",")[5] + "," + df.format(TProject) + "," + df.format(TOverall));
					BW3.newLine();
					BL.write("Startup> " + SO.split(",")[0] + "," + SO.split(",")[1] + "," + df.format(TQuestion) + "," + df.format(TAnswer) + "," + df.format(TView) + ","  + SO.split(",")[5] + "," + df.format(TProject) + "," + df.format(TOverall));
					BL.newLine();
					
					TQuestion = (float) (MaxQuestion * 0.60) ;
					TAnswer = (float) (MaxAnswer * 0.05);
					TView = (float) (MaxView * 0.30);
					TProject = (float) (MaxProject * 0.05);
					TOverall = (TQuestion + TAnswer + TView + TProject)*10;
					BW4.write(SO.split(",")[0] + "," + SO.split(",")[1] + "," + df.format(TQuestion) + "," + df.format(TAnswer) + "," + df.format(TView) + ","  + SO.split(",")[5] + "," + df.format(TProject) + "," + df.format(TOverall));
					BW4.newLine();
					BL.write("Training> " + SO.split(",")[0] + "," + SO.split(",")[1] + "," + df.format(TQuestion) + "," + df.format(TAnswer) + "," + df.format(TView) + ","  + SO.split(",")[5] + "," + df.format(TProject) + "," + df.format(TOverall));
					BL.newLine();
				}
				BW4.close();
				BW3.close();
				BW2.close();
				BW1.close();
			}
			System.out.println("Processing Completed...");
			BL.write("Processing Completed....");
			BL.newLine();
			BL.close();
		}
	}
}
