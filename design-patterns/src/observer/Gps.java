package observer;

import java.util.ArrayList;

//Classe repr�sentant un GPS (appareil permettant de conna�tre sa position).
public class Gps implements Observable
{
     private String position;// Position du GPS.
     private int precision;// Pr�cision accord� � cette position (suivant le nombre de satellites utilis�s).
     private ArrayList<Observateur> tabObservateur;// Tableau d'observateurs.
     
     // Constructeur.
     public Gps()
     {
             position="inconnue";
             precision=0;
             tabObservateur=new ArrayList<Observateur>();
     }
     
     // Permet d'ajouter (abonner) un observateur � l'�coute du GPS.
     public void ajouterObservateur(Observateur o)
     {
             tabObservateur.add(o); 
     }
     
     // Permet de supprimer (r�silier) un observateur �coutant le GPS
     public void supprimerObservateur(Observateur o)
     {
             tabObservateur.remove(o);              
     }

     // M�thode permettant de notifier tous les observateurs lors d'un changement d'�tat du GPS.
     public void notifierObservateurs()
     {
             for(int i=0;i<tabObservateur.size();i++)
             {
                     Observateur o = (Observateur) tabObservateur.get(i);
                     o.actualiser(this);// On utilise la m�thode "tir�".
             }
     }

     // M�thode qui permet de mettre � jour de fa�on artificielle le GPS.
     // Dans un cas r�el, on utiliserait les valeurs retourn�es par les capteurs.
     public void setMesures(String position, int precision)
     {
             this.position=position;
             this.precision=precision;
             notifierObservateurs();
     }
     
     // M�thode "tir�" donc c'est aux observeurs d'aller chercher les valeurs d�sir� gr�ce � un objet Gps.
     // Pour cela on trouve un accesseur en lecture pour position.
     
     public String getPosition()
     {
             return position;
     }
     
     // Un accesseur en lecture pour pr�cision.
     public int getPrecision()
     {
             return precision;
     }

}
