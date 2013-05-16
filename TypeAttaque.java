import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public enum TypeAttaque
{
    OMEGA_SLASH_DE_L_ULTIME_APOLLON(250, "Omega Slash de l'ultime Apollon", "Une série de coups frénétiques qui laisseront tous les ennemis sur le tapis",PorteeAttaque.COURTE), // en attente de créer les vraies attaques
    CANON(400, "Tir de canon", "Envoie un obus sur votre ennemi. Efficace contre l'infanterie.",PorteeAttaque.COURTE),
    FUSIL(250, "Tir de fusil", "Envoie une salve de balles sur votre ennemi.",PorteeAttaque.COURTE);
    
    private int degats; // dégâts de base de l'attaque
    private String aNom;
    private String aDescription;
    private PorteeAttaque aTypePortee; 
    public HashMap<TypeUnite, Double> efficacite;// on multipliera l'attaque par ce nombre, donc 1 est l'élément neutre, quand c'est plus grand que 1 on a une efficacité plus grande, et réciproquement
    
    
    TypeAttaque(int pDegats, String pNom, String pDescription ,final PorteeAttaque pTypePortee ) // ajouter PorteeAttaque pPortee
    {
        degats = pDegats;
        //efficacite.put(TypeUnite.NAVAL, 1.5);
        //efficacite = pEfficacite;
        aNom = pNom;
        aDescription = pDescription;
        efficacite= new HashMap<TypeUnite,Double> ();
        aTypePortee = pTypePortee;
        Scanner fichier = null;
        
        try {
            fichier = new Scanner(new File("TypeAttaque.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        String ligne;
        String[] tab;
        
        while(fichier.hasNextLine()){
            ligne = fichier.nextLine();
            tab = ligne.split(":");
            if(tab[0].equals(pNom)){
                for(TypeUnite type : TypeUnite.values()) {
                    if(type.getNom() == tab[1]) {
                        efficacite.put(type,Double.parseDouble(tab[2]));break;
                    }
                }
            }
        }
    }
     /**
     * Accesseurs et Mutateurs.
     */
    public String getNom()
    {
        return this.aNom;
    }
    
    public String getDescription()
    {
        return this.aDescription;
    }
    
    public int getDegats()
    {
        return degats; 
    }
    
    public void setDegats(int pDegat)
    {
        degats=pDegat; 
    }
    
    public void setNom(String pNom)
    {
        this.aNom = pNom;
    }
    
    public void setDescription(String pDescription)
    {
        this.aDescription = pDescription;
    }


}
