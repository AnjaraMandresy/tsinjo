package relation;
import java.util.*;
public class Relation{
	String nom;
	ArrayList<String> colonne;
	ArrayList<String> type;
	ArrayList<ArrayList<String>> element;
	
	public boolean joinable(Relation relation1){
		ArrayList<String> colonne1 = relation1.getColonne();
		ArrayList<String> colonne2 = this.getColonne();
		ArrayList<String> type1 = relation1.getType();
		ArrayList<String> type2 = this.getType();
		for (int i = 0;i<colonne1.size() ;i++ ) {
			for (int j =0;j<colonne2.size() ;j++ ) {
				if(colonne1.get(i).equalsIgnoreCase(colonne2.get(j)) && type1.get(i).equalsIgnoreCase(type2.get(j))){
					return true;
				}
			}
		}
		return false;
	}
	public static Relation jointureNat(Relation relation1,Relation relation2,String cond){
		String[] condition = cond.split(" ");
		ArrayList<String> colonne1 = relation1.getColonne();
		int taille = colonne1.size();
		ArrayList<String> colonne2 = relation2.getColonne();
		ArrayList<String> type1 = relation1.getType();
		ArrayList<String> type2 = relation2.getType();
		ArrayList<ArrayList<String>> element1 = relation1.getElement();
		ArrayList<ArrayList<String>> element2 = relation2.getElement();
		int indice1 = colonne1.indexOf(condition[0]);
		int indice2 = colonne2.indexOf(condition[2]);
		ArrayList<ArrayList<String>> element = new ArrayList<ArrayList<String>>();
		for (int i=0;i<element1.size() ;i++ ) {
			for (int j=0;j<element2.size() ;j++ ) {
				if(element1.get(i).get(indice1).equalsIgnoreCase(element2.get(j).get(indice2))){
					ArrayList<String> ampiana = new ArrayList<String>();
					ampiana.addAll(element1.get(i));
					ampiana.addAll(element2.get(j));
					element.add(ampiana);
				}
			}
		}
		if(element.size()==0){
			return new Relation();
		}
		for (ArrayList<String>  e : element) {
			e.remove(indice1);
		}
		colonne1.addAll(colonne2);
		colonne1.remove(colonne1.get(indice1));
		type1.addAll(type2);
		type1.remove(type1.get(indice1));
		Relation relation = new Relation(relation1.getNom()+"*"+relation2.getNom(),colonne1,type1);
		HashSet<ArrayList<String>> malanaDoublon = new HashSet<>(element);
				element = new ArrayList<>(malanaDoublon);
				relation.setElement(element);
		return relation;
	}
	public static Relation jointure(Relation relation1,Relation relation2){
		ArrayList<String> colonne1 = relation1.getColonne();
		int taille = colonne1.size();
		ArrayList<String> colonne2 = relation2.getColonne();
		ArrayList<String> type1 = relation1.getType();
		ArrayList<String> type2 = relation2.getType();
		
			if(relation1.joinable(relation2)){
				ArrayList<ArrayList<Integer>> indice = new ArrayList<ArrayList<Integer>>();
				int isa = 0;
				//System.out.print("   :");relation1.displayRelation();
				//System.out.println("colonne1 "+colonne1);
				//System.out.println(colonne2);
				for (int i = 0;i<colonne1.size() ;i++ ) {
					for (int j =0;j<colonne2.size() ;j++ ) {
						if(colonne1.get(i).equalsIgnoreCase(colonne2.get(j)) && type1.get(i).equalsIgnoreCase(type2.get(j))){
							//System.out.println("colonne "+colonne1.get(i)+" "+colonne2.get(j));
							//System.out.println("type "+type1.get(i)+" "+type2.get(j));
							
							ArrayList<Integer> ind = new ArrayList<Integer>();
							ind.add(i);
							ind.add(j);
							indice.add(ind);
							
							isa++;
							break;
						}
					}
				}
				Relation relation = Relation.produitCartesienne(relation1,relation2);
				if(relation.getNom()==null){
					return new Relation();
				}
				ArrayList<ArrayList<String>> element = relation.getElement();
				ArrayList<ArrayList<String>> element1 = new ArrayList<ArrayList<String>>();
				ArrayList<String> colonne = new ArrayList<String>();
				ArrayList<String> type = new ArrayList<String>();
					
				for (ArrayList<String> e : element) {
					ArrayList<String> supprimer = new ArrayList<String>();
					for (int i=0; i<indice.size(); i++) {
						int ind1 = indice.get(i).get(0);
						int ind2 = indice.get(i).get(1);
						if((e.get(ind1).equalsIgnoreCase(e.get(ind2+taille)))){
							//System.out.println(e.get(ind1));
							//System.out.println(e.get(ind2+colonne1.size()-1));
							//element.remove(e);
							//break;
							supprimer.add(e.get(ind2+taille));
							element1.add(e);
							//System.out.println(e);
						}
						for (String s : supprimer) {
							e.remove(s);	
						}
						
						//relation.getColonne().remove(relation.getColonne().get(ind1));
						//relation.getType().remove(relation.getType().get(ind1));
					}
				}
				for (ArrayList<Integer> ind : indice ) {
					type.add(relation.getType().get(ind.get(1)+taille));
					colonne.add(relation.getColonne().get(ind.get(1)+taille));
							
				}
				System.out.println(colonne);
				System.out.println(type);
				for (int i=0;i<colonne.size() ;i++ ) {
					relation.getColonne().remove(colonne.get(i));
					relation.getType().remove(type.get(i));
					
				}
				HashSet<ArrayList<String>> malanaDoublon = new HashSet<>(element1);
				element1 = new ArrayList<>(malanaDoublon);
				relation.setElement(element1);
				return relation;
			}
			else{
				return new Relation();
			}
		
	}
	public static Relation produitCartesienne(Relation relation1,Relation relation2){
		ArrayList<ArrayList<String>> element1 = relation1.getElement();
		ArrayList<ArrayList<String>> element2 = relation2.getElement();
		if(element1.size()== 0 || element2.size()== 0){
			return new Relation();
		}else{
			int taille = element2.size();
			int taille1 = element1.size();
			ArrayList<ArrayList<String>> e1 = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> e2 = new ArrayList<ArrayList<String>>();
			int isa = 0;
			for (int j=0;j<element1.size() ;j++ ) {
				for (int i = 0;i<taille ; i++) {
					//element1.get(j).addAll(element2.get(i));
					e1.add(element1.get(j));
					e2.add(element2.get(i));
					isa++;
				}
				//e1.addAll(e2);
				//element2.addAll(element2);
			}

			/*System.out.println(isa);
			System.out.println(e1.size());
			System.out.println(e2.size());*/
			ArrayList<ArrayList<String>> element = new ArrayList<ArrayList<String>>();
			for (int i=0 ;i<e1.size() ;i++ ) {
				//ArrayList<String> array1 = e1.get(i);
				//ArrayList<String> array2 = e2.get(i);
				ArrayList<String> array = new ArrayList<String>(e1.get(i));
				array.addAll(e2.get(i));
				element.add(array);
				//System.out.println(e2.get(i));
			}
			ArrayList<String> colonne1 = relation1.getColonne();
			ArrayList<String> colonne2 = relation2.getColonne();
			colonne1.addAll(colonne2);
			ArrayList<String> type1 = relation1.getType();
			ArrayList<String> type2 = relation2.getType();
			type1.addAll(type2);
			Relation relation = new Relation(relation1.getNom()+"*"+relation2.getNom(),colonne1,type1);
			relation.setElement(element);
			return relation;
			
		}
	}
	public void displayRelation(){
		if(this.getNom()!=null && this.getType()!=null && this.getColonne()!=null){
			System.out.println(this.getNom());
			System.out.println(this.getType());
			System.out.println(this.getColonne());
			this.diplayElement();
		
		}else{
			System.out.println("pas de relation");
		}
	}
	public static ArrayList<String> min(ArrayList<ArrayList<String>> element,String type,int indice){
		if(type.equalsIgnoreCase("entier")){
			if((element.get(0)).get(indice).equalsIgnoreCase("null")){
				return (element.get(0));
			}
			int min = 0;
			int minInteger = Integer.parseInt((element.get(0)).get(indice));
			for (int i = 1;i<element.size() ;i++ ) {
				if((element.get(i)).get(indice).equalsIgnoreCase("null")){
					return (element.get(i));
				}
				if(Integer.parseInt((element.get(i)).get(indice))<minInteger){
					min = i;
					minInteger = Integer.parseInt((element.get(i)).get(indice));
				}
			}
			return element.get(min);
		}else if(type.equalsIgnoreCase("reel")){
			if((element.get(0)).get(indice).equalsIgnoreCase("null")){
				return (element.get(0));
			}
			int min = 0;
			double minDouble = Double.parseDouble((element.get(0)).get(indice));
			for (int i = 1;i<element.size() ;i++ ) {
				if((element.get(i)).get(indice).equalsIgnoreCase("null")){
					return (element.get(i));
				}
				if(Double.parseDouble((element.get(i)).get(indice))<minDouble){
					min = i;
					minDouble = Double.parseDouble((element.get(i)).get(indice));
				}
			}
			return element.get(min);
		}else{
			if((element.get(0)).get(indice).equalsIgnoreCase("null")){
				return (element.get(0));
			}
			int min = 0;
			String minString =((element.get(0)).get(indice));
			for (int i = 1;i<element.size() ;i++ ) {
				if((element.get(i)).get(indice).equalsIgnoreCase("null")){
				return (element.get(i));
				}
				if(((element.get(i)).get(indice)).compareTo(minString)<0){
					min = i;
					minString =((element.get(i)).get(indice));
				}
			}
			return element.get(min);
		}
	}
	
	/*public static Relation trie(Relation relation,String colonne)throws Exception{
		int indice = relation.getColonne(colonne);
		if(indice == -1){
			throw new Exception("Tsisy an'io colonne io");
		}
		Relation rel = new Relation(relation.getNom(),relation.getColonne(),relation.getType());
		System.out.println(rel.getColonne());
		ArrayList<ArrayList<String>> element = relation.getElement();
		ArrayList<ArrayList<String>> element1 = new ArrayList<ArrayList<String>>();
		while(element.size()!=0){
			ArrayList<String> s = Relation.min(element,(relation.getType()).get(indice),indice);
			//rel.addElement(s);
			element1.add(0,s);
			element.remove(s);
		}
		rel.setElement(element1);
		return rel;
		
	}*/
	public static Relation trie(Relation relation,String colonne, String ordre)throws Exception{
		int indice = relation.getColonne(colonne);
		if(indice == -1){
			throw new Exception("Tsisy an'io colonne io");
		}
		Relation rel = new Relation(relation.getNom(),relation.getColonne(),relation.getType());
		System.out.println(rel.getColonne());
		ArrayList<ArrayList<String>> element = relation.getElement();
		ArrayList<ArrayList<String>> element1 = new ArrayList<ArrayList<String>>();
		while(element.size()!=0){
			ArrayList<String> s = Relation.min(element,(relation.getType()).get(indice),indice);
			//rel.addElement(s);
			if(ordre.equalsIgnoreCase("desc")){
				element1.add(0,s);	
			}else{
				element1.add(s);
			}
			element.remove(s);
		}
		rel.setElement(element1);
		return rel;
		
	}
	public Relation conditionEt(ArrayList<String> mot1,ArrayList<String> mot2)throws Exception{
		Relation relation1 = this.condition(mot1);
		Relation relation2 = this.condition(mot2);
		return Relation.intersection(relation1,relation2);
	}
	public Relation conditionOu(ArrayList<String> mot1,ArrayList<String> mot2)throws Exception{
		Relation relation1 = this.condition(mot1);
		Relation relation2 = this.condition(mot2);
		return Relation.union(relation1,relation2);
		
	}
	public int getColonne(String mot){
		int indice =-1;
		ArrayList<String> col = this.getColonne();
		for (int i =0 ;i< col.size() ;i++ ) {
			if(col.get(i).equalsIgnoreCase(mot)){
				indice = i;
				return indice;	
			}
			
		}
		return indice;
	}
	public Relation condition(ArrayList<String> mot)throws Exception{
		int indice =-1;
		ArrayList<String> col = this.getColonne();
		for (int i =0 ;i< col.size() ;i++ ) {
			if(col.get(i).equalsIgnoreCase(mot.get(0))){
				indice = i;
				break;	
			}
			
		}
		if(indice == -1){
			throw new Exception("Tsisy an'io colonne io");
		}
		Relation relation = new Relation(this.getNom(),this.getColonne(),this.getType());
		ArrayList<ArrayList<String>> element = this.getElement();
		for (ArrayList<String> donnee :element ) {
			ArrayList<String> donnee1 = new ArrayList<String>();
			if(mot.get(1).equalsIgnoreCase("=")){
				if((this.getType().get(indice)).equalsIgnoreCase("entier")){
					if(!donnee.get(indice).equalsIgnoreCase("null")){
						if(Integer.parseInt(donnee.get(indice)) == Integer.parseInt(mot.get(2))){
							relation.addElement(donnee); 
						}
					}	
				}else if((this.getType().get(indice)).equalsIgnoreCase("reel")){
					if(!donnee.get(indice).equalsIgnoreCase("null")){
						if(Double.parseDouble(donnee.get(indice)) == Double.parseDouble(mot.get(2))){
							relation.addElement(donnee); 
						}	
					}
						
				}
				else{
					if(donnee.get(indice).equalsIgnoreCase(mot.get(2))){
						relation.addElement(donnee);
					}	
				}
					
			}else if(mot.get(1).equalsIgnoreCase("!=")){
				if(!(donnee.get(indice).equalsIgnoreCase(mot.get(2)))){
					relation.addElement(donnee);
				}	
			}else{
				
					if((this.getType().get(indice)).equalsIgnoreCase("entier")){
						if(!donnee.get(indice).equalsIgnoreCase("null")){
							if(mot.get(1).equalsIgnoreCase("<")){
								if(Integer.parseInt(donnee.get(indice)) < Integer.parseInt(mot.get(2))){
									relation.addElement(donnee); 
								}	
							}else if(mot.get(1).equalsIgnoreCase(">")){
								if(Integer.parseInt(donnee.get(indice)) > Integer.parseInt(mot.get(2))){
									relation.addElement(donnee);
								}	
							}else if(mot.get(1).equalsIgnoreCase(">=") || mot.get(1).equalsIgnoreCase("=>")){
								if(Integer.parseInt(donnee.get(indice)) >= Integer.parseInt(mot.get(2))){
									relation.addElement(donnee);
								}	
							}else if(mot.get(1).equalsIgnoreCase("<=") || mot.get(1).equalsIgnoreCase("=<")){
								if(Integer.parseInt(donnee.get(indice)) <= Integer.parseInt(mot.get(2))){
									relation.addElement(donnee);
								}	
							}
						}		
					}else if((this.getType().get(indice)).equalsIgnoreCase("reel")){
						if(!donnee.get(indice).equalsIgnoreCase("null")){	
							if(mot.get(1).equalsIgnoreCase("<")){
								if(Double.parseDouble(donnee.get(indice)) < Double.parseDouble(mot.get(2))){
									relation.addElement(donnee);
									}	
								}else if(mot.get(1).equalsIgnoreCase(">")){
									if(Double.parseDouble(donnee.get(indice)) > Double.parseDouble(mot.get(2))){
										relation.addElement(donnee);
									}	
								}else if(mot.get(1).equalsIgnoreCase(">=") || mot.get(1).equalsIgnoreCase("=>")){
									if(Double.parseDouble(donnee.get(indice)) >= Double.parseDouble(mot.get(2))){
										relation.addElement(donnee);
									}	
								}else if(mot.get(1).equalsIgnoreCase("<=") || mot.get(1).equalsIgnoreCase("=<")){
									if(Double.parseDouble(donnee.get(indice)) <= Double.parseDouble(mot.get(2))){
										relation.addElement(donnee);
									}	
								}		
							}	
						}
					else{
						throw new Exception("Invalide operateur "+mot.get(1)+" pour "+this.getColonne().get(indice));
					}				
				
			}
			
		}
		return relation;

	}
	public Relation orienterColonne1(ArrayList<String> colonne )throws Exception{
		ArrayList<Integer> indice = new ArrayList<>();
		ArrayList<String> col = this.getColonne();
		for (int j=0;j<colonne.size() ;j++ ) {
			for (int i =0 ;i< col.size() ;i++ ) {
				if(col.get(i).equalsIgnoreCase(colonne.get(j))){
					indice.add(i) ;
					break;	
				}
			}	
		}
		//System.out.println(indice);
		if(indice.size() != colonne.size()){
			throw new Exception("Tsisy an'io colonne io");
		}
		ArrayList<String> newColonne = new ArrayList<>();
		ArrayList<String> newtype = new ArrayList<>();
		for (int i = 0;i<indice.size() ;i++ ) {
			newColonne.add(col.get(indice.get(i)));
			newtype.add(this.getType().get(indice.get(i)));
		}
		Relation relation = new Relation(this.getNom(),newColonne,newtype);
		ArrayList<ArrayList<String>> element = this.getElement();
		for (ArrayList<String> donnee :element ) {
			ArrayList<String> donnee1 = new ArrayList<String>();
			for (int i =0;i<indice.size() ; i++) {
				donnee1.add(donnee.get(indice.get(i)));	
			}
			relation.addElement(donnee1);
		}
		return relation;
	}
	public  Relation orienterColonne(String colonne)throws Exception{
		int indice =-1;
		ArrayList<String> col = this.getColonne();
		for (int i =0 ;i< col.size() ;i++ ) {
			if(col.get(i).equalsIgnoreCase(colonne)){
				indice = i;
				break;	
			}
			
		}
		if(indice == -1){
			throw new Exception("Tsisy an'io colonne io");
		}
		ArrayList<String> newColonne = new ArrayList<>();
		ArrayList<String> newtype = new ArrayList<>();
		newColonne.add(col.get(indice));
		newtype.add(this.getType().get(indice));
		Relation relation = new Relation(this.getNom(),newColonne,newtype);
		ArrayList<ArrayList<String>> element = this.getElement();
		for (ArrayList<String> donnee :element ) {
			ArrayList<String> donnee1 = new ArrayList<String>();
			donnee1.add(donnee.get(indice));
			relation.addElement(donnee1);
		}
		return relation;
	}
	public static Relation difference(Relation relation1,Relation relation2)throws Exception{
		if(relation1.getColonne().size() != relation1.getColonne().size()){
			throw new Exception("les 2 colonne n' ont pas le meme nombre de colonne");
		}
		ArrayList<ArrayList<String>> inter = new ArrayList<ArrayList<String>>();
		for (int i = 0 ;relation1.getElement().size()>i ;i++ ) {
			if(!(relation2.getElement().contains(relation1.getElement().get(i)))){
				inter.add(relation1.getElement().get(i));
			}
		}
		Relation relation = new Relation(relation1.getNom(),relation1.getColonne(),relation1.getType());
		relation.setElement(inter);
		return relation;
	 
	}
	public static Relation intersection(Relation relation1,Relation relation2)throws Exception{
		/*if(relation1.getNom()== null || relation2.getNom()== null ){
			return new Relation();
		}*/
		if(relation1.getColonne().size() != relation1.getColonne().size()){
			throw new Exception("les 2 colonne n' ont pas le meme nombre de colonne");
		}
		ArrayList<ArrayList<String>> inter = new ArrayList<ArrayList<String>>();
		for (int i = 0 ;relation1.getElement().size()>i ;i++ ) {
			if(relation2.getElement().contains(relation1.getElement().get(i))){
				inter.add(relation1.getElement().get(i));
			}
		}
		Relation relation = new Relation(relation1.getNom(),relation1.getColonne(),relation1.getType());
		relation.setElement(inter);
		return relation;
	 
	}
	public static Relation union(Relation relation1,Relation relation2)throws Exception{
		/*if(relation1.getNom()== null){
			return relation2;
		}
		else if(relation2.getNom()== null){
			return relation1;
		}*/
		if(relation1.getColonne().size() != relation1.getColonne().size()){
			throw new Exception("les 2 colonne n' ont pas le meme nombre de colonne");
		}
		ArrayList<ArrayList<String>> union = new ArrayList<ArrayList<String>>();
		(relation1.getElement()).addAll(relation2.getElement());
		HashSet<ArrayList<String>> malanaDoublon = new HashSet<>(relation1.getElement());
		union = new ArrayList<>(malanaDoublon);
		Relation relation = new Relation(relation1.getNom(),relation1.getColonne(),relation1.getType());
		relation.setElement(union);
		return relation;
	}
	public void setElement(ArrayList<ArrayList<String>> donnee){
		element = donnee;
	}
	public Relation(){}
	public Relation(String nom,ArrayList<String> colonne,ArrayList<String> type){
		this.nom = nom;
		this.colonne = colonne;
		this.type = type;
		this.element = new ArrayList<ArrayList<String>>();
	}
	public void addElement(ArrayList<String> donnee)throws Exception{
		if(donnee.size() != colonne.size()){
			throw new Exception("les donnees n'ont pas le meme nmbre que les colonnes");
		}	
		for (int i=0;i<type.size() ;i++ ) {
			if((type.get(i)).equalsIgnoreCase("entier")){
				if(donnee.get(i).equalsIgnoreCase("null")){
					continue;
				}
				try{
					Integer.parseInt(donnee.get(i));
				}catch(Exception e){
					throw new Exception(donnee.get(i)+" n'est pas un entier");
				}
			}
			else if((type.get(i)).equalsIgnoreCase("reel")){
				if(donnee.get(i).equalsIgnoreCase("null")){
					continue;
				}
				try{
					Double.parseDouble(donnee.get(i));
				}catch(Exception e){
					throw new Exception(donnee.get(i)+" n'est pas un reel");
				
				}
			}
		}
		element.add(donnee);
	}
	public String getNom(){
		return nom;
	}
	public ArrayList<String> getColonne(){
		return colonne;
	}
	public ArrayList<String> getType(){
		return type;
	}
	public ArrayList<ArrayList<String>> getElement(){
		return element;
	}
	public void diplayElement(){
		if(element.size()== 0){
			System.out.println("Pas de donnee");
			return;
		}
		for (ArrayList<String> donnee: element) {
			System.out.println(donnee);
		}
	}
}