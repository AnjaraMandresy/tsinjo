package operation;
import java.io.*;
import relation.*;
import java.util.*;
public class ProduitCartesienne{
	public static Relation produitCartesienne(String requet){
		String[] mot = requet.split(" ");
		ArrayList<Integer> indice = new ArrayList<>();
		for (int i=0 ;i<mot.length ;i++ ) {
			if(mot[i].equalsIgnoreCase("manapoitra")){
				indice.add(i);
			}
		}
		StringBuilder requet1 = new StringBuilder("");
		StringBuilder requet2 = new StringBuilder("");
		Relation relation1 = new Relation();
		Relation relation2 = new Relation();
		for (int i = indice.get(0) ;i<indice.get(1) ; i++ ) {
			requet1.append(mot[i]);
			requet1.append(" ");
		}
		for (int i = indice.get(1) ;i<mot.length ; i++) {
			requet2.append(mot[i]);
				requet2.append(" ");
		}
		try{
			relation1 = Select.select(requet1.toString());
			relation2 = Select.select(requet2.toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return Relation.produitCartesienne(relation1,relation2);
		//return relation1;
	}
}