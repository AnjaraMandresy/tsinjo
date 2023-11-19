package operation;
import java.io.*;
import relation.*;
import java.util.*;
public class JointureNat{
	public static Relation join(String requet){
		String[] mot = requet.split(" ");
		ArrayList<Integer> indice = new ArrayList<>();
		int indiceOn = -1; 
		for (int i=0 ;i<mot.length ;i++ ) {
			if(mot[i].equalsIgnoreCase("manapoitra")){
				indice.add(i);
			}
			if(mot[i].equalsIgnoreCase("on")){
				indiceOn = i;
			}
		}
		System.out.println("isa "+indiceOn);
		if(indiceOn == -1){
			System.out.println("Pas de condition");
			return new Relation();
		}
		StringBuilder requet1 = new StringBuilder("");
		StringBuilder requet2 = new StringBuilder("");
		StringBuilder condition = new StringBuilder("");
		Relation relation1 = new Relation();
		Relation relation2 = new Relation();
		for (int i = indice.get(0) ;i<indice.get(1) ; i++ ) {
			requet1.append(mot[i]);
			requet1.append(" ");
		}
		for (int i = indice.get(1) ;i<indiceOn ; i++) {
			requet2.append(mot[i]);
				requet2.append(" ");
		}
		for (int i = indiceOn+1;i<mot.length ;i++ ) {
			condition.append(mot[i]);
			condition.append(" ");	
		}
		try{
			relation1 = Select.select(requet1.toString());
			//relation1.displayRelation();
			relation2 = Select.select(requet2.toString());
			//relation2.displayRelation();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		Relation relation = Relation.jointureNat(relation1,relation2,condition.toString());
		return relation;
	}
}