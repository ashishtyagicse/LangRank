import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AddMissingLang {

	public static void main(String[] args) throws IOException {

		ArrayList<String> Languages = new ArrayList<>();
		ArrayList<String> FileInput = new ArrayList<>();
		ArrayList<String> FileOutput = new ArrayList<>();
		
		HashMap<String, String> list = new HashMap<String, String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String Line;
			while ((Line = br.readLine()) != null) {
				if (!Languages.contains(Line.split(",")[1])) {
					Languages.add(Line.split(",")[1]);					
				}
				FileInput.add(Line);
				list.put(Line.split(",")[0] + "," + Line.split(",")[1], Line);
			}
			Collections.sort(Languages);
			Collections.sort(FileInput);
			br.close();
		}

		ArrayList<String> S1 = new ArrayList<String>();
		S1.add("c");S1.add("c#");	S1.add("c++");	S1.add("coffeescript");	S1.add("css");	S1.add("delphi");S1.add("go");S1.add("haskell");
		S1.add("java");	S1.add("javascript");S1.add("matlab");S1.add("objective-c");S1.add("perl");	S1.add("php");S1.add("powershell");
		S1.add("processing");S1.add("python");S1.add("r");S1.add("ruby");S1.add("scala");S1.add("shell");S1.add("sql");S1.add("swift");S1.add("xml");
		DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
		try (BufferedWriter BW1 = new BufferedWriter(new FileWriter(args[0]	+ "123.csv"))) {			
			for (String s : Languages) {
				if (s.startsWith("year")) {
					FileOutput.add(s);
				}
				if (S1.contains(s)) {
					
				
				for (int i = 2009; i <= 2014; i++) {
					if (!list.containsKey(i + "," + s)) {
						FileOutput.add( i + ";\"" + s + "\";\"0\"");
						System.out.println("Not found >" + i + "," + s);
					}
					else {
						FileOutput.add(i + ";\"" + s + "\";\"" + df.format(Float.parseFloat(list.get(i + "," + s).split(",")[7].trim())) + "\"");
					}	
				}
				}
			}	
			Collections.sort(FileOutput);
			for (String so : FileOutput) {
				BW1.write(so);
				BW1.newLine();
			}
			BW1.close();
		}
	}
}
