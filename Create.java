package operation;
import java.io.*;
public class Create{
	public static void create(String requet){
		String[] mot = requet.split(" ");
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(mot[2]))){
			for (int i =1;i<mot.length ;i++ ) {
				writer.write(mot[i]);
				writer.write(" ");;
			}
			writer.newLine();
			writer.close();
		}catch (IOException e){
			System.err.println("Erreur lors de la creation du tahiry: "+e.getMessage());
		}
	}
}