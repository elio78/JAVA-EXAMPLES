package observer;

//Affiche un r�sum� en console des informations (position) du GPS.
public class AfficheResume implements Observateur
{
     // M�thode appel�e automatiquement lors d'un changement d'�tat du GPS.
     public void actualiser(Observable o)
     {
             if(o instanceof Gps)
             {       
                     Gps g = (Gps) o;
                     System.out.println("Position : "+g.getPosition());
             }       
     }

}
