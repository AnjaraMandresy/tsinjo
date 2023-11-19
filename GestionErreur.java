package erreur;
public class GestionErreur{
	public static int erreur(String phrase){
		String[] mot = phrase.split(" ");
		if(mot[0].equalsIgnoreCase("manamboatra")){
			return 1;
		}
		else if(mot[0].equalsIgnoreCase("mampiditra")){
			return 2;
		}
		else if(mot[0].equalsIgnoreCase("manapoitra")){
			return 3;
		}else if(mot[0].equalsIgnoreCase("exit")){
			return 0;
		}
		else if(mot[0].equalsIgnoreCase("produitCartesienne")){
			return 4;
		}
		else if(mot[0].equalsIgnoreCase("join")){
			return 5;
		}
		else if(mot[0].equalsIgnoreCase("joinNat")){
			return 6;
		}
		else{
			return -1;
		}
	}
}