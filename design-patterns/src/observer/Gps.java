package observer;

import java.util.ArrayList;

//Classe représentant un GPS (appareil permettant de connaître sa position).
public class Gps implements Observable
{
     private String position;// Position du GPS.
     private int precision;// Précision accordé à cette position (suivant le nombre de satellites utilisés).
     private ArrayList<Observateur> tabObservateur;// Tableau d'observateurs.
     
     // Constructeur.
     public Gps()
     {
             position="inconnue";
             precision=0;
             tabObservateur=new ArrayList<Observateur>();
     }
     
     // Permet d'ajouter (abonner) un observateur à l'écoute du GPS.
     public void ajouterObservateur(Observateur o)
     {
             tabObservateur.add(o); 
     }
     
     // Permet de supprimer (résilier) un observateur écoutant le GPS
     public void supprimerObservateur(Observateur o)
     {
             tabObservateur.remove(o);              
     }

     // Méthode permettant de notifier tous les observateurs lors d'un changement d'état du GPS.
     public void notifierObservateurs()
     {
             for(int i=0;i<tabObservateur.size();i++)
             {
                     Observateur o = (Observateur) tabObservateur.get(i);
                     o.actualiser(this);// On utilise la méthode "tiré".
             }
     }

     // Méthode qui permet de mettre à jour de façon artificielle le GPS.
     // Dans un cas réel, on utiliserait les valeurs retournées par les capteurs.
     public void setMesures(String position, int precision)
     {
             this.position=position;
             this.precision=precision;
             notifierObservateurs();
     }
     
     // Méthode "tiré" donc c'est aux observeurs d'aller chercher les valeurs désiré grâce à un objet Gps.
     // Pour cela on trouve un accesseur en lecture pour position.
     
     public String getPosition()
     {
             return position;
     }
     
     // Un accesseur en lecture pour précision.
     public int getPrecision()
     {
             return precision;
     }

}
