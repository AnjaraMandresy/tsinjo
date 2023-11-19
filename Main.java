package aff;
import relation.*;
import java.util.*;
import erreur.*;
import operation.*;
public class Main{
	public static void main(String[] args) throws Exception{
		/*String nom ="nom";
		ArrayList<String> colonne = new ArrayList<String>();
		ArrayList<String> donnee = new ArrayList<String>();
		colonne.add("nom");
		colonne.add("vola");
		colonne.add("age");
		donnee.add("mot");
		donnee.add("reel");
		donnee.add("entier");
		Relation relation = new Relation(nom,colonne,donnee);
		ArrayList<String> donnee1 = new ArrayList<String>();
		donnee1.add("Ma");
		donnee1.add("20");
		donnee1.add("21");
		relation.addElement(donnee1);
		ArrayList<String> donnee2 = new ArrayList<String>();
		donnee2.add("Ma");
		donnee2.add("21");
		donnee2.add("22");
		relation.addElement(donnee2);
		ArrayList<String> donnee3 = new ArrayList<String>();
		donnee3.add("Ma");
		donnee3.add("22");
		donnee3.add("23");
		relation.addElement(donnee3);
		relation.displayRelation();
		
		String nom2 ="nom2";
		Relation relation2 = new Relation(nom2,colonne,donnee);
		relation2.addElement(donnee1);
		relation2.addElement(donnee2);
		relation2.addElement(donnee3);
		relation2.displayRelation();
		String[] condition = {"age","=","age"};
		Relation relation3 = Relation.jointureNat(relation,relation2,condition);
		
		relation3.displayRelation();
		//System.out.println(Relation.min(relation.getElement(),"entier",1));
		//Relation rel = Relation.trie(relation,"age");
		//rel.displayRelation();*/
		String mot ="";
		Scanner scanner = new Scanner(System.in);
		while(!mot.equalsIgnoreCase("exit")){
			System.out.print("#");
			mot = scanner.nextLine();
			//System.out.println(GestionErreur.erreur(mot));
			if(GestionErreur.erreur(mot)==1){
				Create.create(mot);
			}
			else if(GestionErreur.erreur(mot)==2){
				Insert.insert(mot);
			}else if(GestionErreur.erreur(mot)==3){
				Relation relation=Select.select(mot);
				relation.displayRelation();
			}else if(GestionErreur.erreur(mot)==4){
				Relation relation=ProduitCartesienne.produitCartesienne(mot);
				relation.displayRelation();
			}else if(GestionErreur.erreur(mot)==5){
				Relation relation=Jointure.join(mot);
				relation.displayRelation();
			}else if(GestionErreur.erreur(mot)==6){
				Relation relation=JointureNat.join(mot);
				relation.displayRelation();
			}else if(GestionErreur.erreur(mot)==-1){
				System.out.println(mot.split(" ")[0]+" commande introvable");
			}
		}
		scanner.close();
		/*ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1,2,3,4));
		ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(1,2,3,4));
		ArrayList<ArrayList<Integer>> array= new ArrayList<ArrayList<Integer>>();
		for (int i = 0;i<Math.min(array1.size(), array2.size()) ;i++ ) {
			ArrayList<Integer> entier= new ArrayList<Integer>();
			entier.add(array1.get(i));
			entier.add(array2.get(i));
			array.add(entier);

		}
		for (ArrayList<Integer> a : array) {
			System.out.println(a);	
		}*/
		
	}
}