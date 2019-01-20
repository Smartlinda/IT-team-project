package commandline;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Line_by_line {
    static List<String> list = new ArrayList<String>();

    public static void main(String a[]){
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        try {
             BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt"));
             strLine = br.readLine();
             

             while (strLine != null)
               {
               strLine = br.readLine();
                sb.append(strLine);
                sb.append(System.lineSeparator());
                
                

                //strLine = br.readLine();
                if (strLine==null)
                   break;
                
                list.add(strLine.substring(4));
                
                
//                input = new BufferedReader(new FileReader(filePath));
//                input.readLine();
//                String nm;
//                while ((nm = input.readLine()) != null)
//                
                
                
                
            }
         System.out.println(Arrays.toString(list.toArray()));
             br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
     }
}
