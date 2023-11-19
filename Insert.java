package operation;
import java.io.*;
import relation.*;
import java.util.*;
public class Insert{
	public static void insert(String requet){
		String[] mot = requet.split(" ");
		BufferedReader reader = null;
		String signature = "";
		try{
			reader = new BufferedReader(new FileReader(mot[1]));
			String ligne = reader.readLine();
			signature = ligne;
			String[] caracteristique = signature.split(" ");
			ArrayList<String> type = new ArrayList<>();
			ArrayList<String> colonne = new ArrayList<>();
			for (int i = 2;i< (caracteristique.length)-1 ;i+=2 ) {
				type.add(caracteristique[i]);
				colonne.add(caracteristique[i+1]);
			}
			Relation relation = new Relation(caracteristique[1],colonne,type);
			ArrayList<String> element = new ArrayList<>();
			for (int i = 2 ;i<mot.length ;i++ ) {
				element.add(mot[i]);
			}
			relation.addElement(element);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mot[1],true));
			for (int i =2;i<mot.length ;i++ ) {
				writer.write(mot[i]);
				writer.write(" ");;
			}
			writer.newLine();
			writer.close();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}finally{
			try{
				if(reader != null){
					reader.close();
				}
			}catch(Exception e){
				System.err.println("Error closing the reader: "+e.getMessage());
			}
		}
	}
}