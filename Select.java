package operation;
import java.io.*;
import relation.*;
import java.util.*;
public class Select{
	public static Relation select(String requet){
		System.out.println(requet);
		String[] mot = requet.split(" ");
		BufferedReader reader = null;
		String signature = "";
		Relation relation = new Relation();
		int indice  = -1;
		int indiceCondition = -1;
		int indiceGroupeBy = -1;
		for (int i = 0;i<mot.length ;i++ ) {
			if(mot[i].equalsIgnoreCase("from")){
				indice = i;
			}
			else if(mot[i].equalsIgnoreCase("where")){
				indiceCondition = i;
			}else if(mot[i].equalsIgnoreCase("groupBy")){
				indiceGroupeBy = i;
				break;
			}
		}
		try{
			reader = new BufferedReader(new FileReader(mot[indice+1]));
			String ligne = reader.readLine();
			signature = ligne;
			String[] caracteristique = signature.split(" ");
			ArrayList<String> type = new ArrayList<>();
			ArrayList<String> colonne = new ArrayList<>();
			for (int i = 2;i< (caracteristique.length)-1 ;i+=2 ) {
				type.add(caracteristique[i]);
				colonne.add(caracteristique[i+1]);
			}
			relation = new Relation(caracteristique[1],colonne,type);
			ArrayList<ArrayList<String>> element = new ArrayList<ArrayList<String>>();
			String line = "";
			while((line=reader.readLine()) != null){
				String[] teny = line.split(" ");
				ArrayList<String> donnee = new ArrayList<String>();
				for (String m : teny) {
					donnee.add(m);
				}
				element.add(donnee);
			}
			relation.setElement(element);
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

		Relation rel = new Relation();
		Relation relCondition = new Relation();
		if(indiceCondition == -1){
			rel= relation;
		}
		else if((mot.length-1)-indiceCondition == 3){
			//String[] condition=new String[3];
			ArrayList<String> condition = new ArrayList<String>();
			int cmp = 0;
			for (int i=indiceCondition+1;i<mot.length ;i++ ) {
				condition.add(mot[i]);
			}
			
			try{
				rel = relation.condition(condition);
			}catch(Exception e){
				System.err.println(e.getMessage());	
			}
			//return rel;
		}else{

			//String[] condition=new String[(mot.length-1)-indiceCondition];
			ArrayList<ArrayList<String>> condition = new ArrayList<ArrayList<String>>();

			ArrayList<String> separateur = new ArrayList<>();
			for (int i=indiceCondition+1;i<mot.length ;i+=4) {
				ArrayList<String> c =new ArrayList<String>();
				c.add(mot[i]);
				c.add(mot[i+1]);
				c.add(mot[i+2]);
				condition.add(c);
				if(i+3<mot.length){
					separateur.add(mot[i+3]);
				}
			}
			try{
				int i = 1,
					j = 0;
					rel = relation.condition(condition.get(0));
				while(i<condition.size()){
					System.out.println(separateur.get(j));
					if(separateur.get(j).equalsIgnoreCase("ou")){
						Relation vaovao = relation.condition(condition.get(i));	
						rel=Relation.union(rel,vaovao);
					}else{
						System.out.println("aoo");
						Relation vaovao= relation.condition(condition.get(i));	
						rel=Relation.intersection(rel,vaovao);
					}

					j++;
					i++;
				}
									
			}catch(Exception e){
				System.err.println(e.getMessage());	
			}
			//return rel;
		}
		
			Relation relat = new Relation();
		if(mot[1].equals("*")){
				//return rel;
				relat = rel;
		}else{
			try{
				ArrayList<String> colonne = new ArrayList<>();
				for (int i = 1;i<mot.length ;i++ ) {
					if(mot[i].equals("from")){
						break;	
					} 
					colonne.add(mot[i]);
				}
				//System.out.println(colonne);
				relat =  rel.orienterColonne1(colonne);	
			}catch(Exception e){
				System.err.println(e.getMessage());	
			}
			//return relat;
		}
		if(indiceGroupeBy==-1){
			return relat;
		}else{
			Relation r = new Relation();
			try{
				//System.out.println(mot[indiceGroupeBy+1]);
				if(indiceGroupeBy+2 == (mot.length-1)){
					r = Relation.trie(relat,mot[indiceGroupeBy+1], mot[indiceGroupeBy+2]);	
				}
				else{
					r = Relation.trie(relat,mot[indiceGroupeBy+1], "asc");
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			return r;
		}
	}
}