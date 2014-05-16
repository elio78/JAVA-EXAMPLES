package observer;


//Affiche en console de fa�on compl�te les informations (position et pr�cision) du GPS.
public class AfficheComplet implements Observateur
{
   // M�thode appel�e automatiquement lors d'un changement d'�tat du GPS.
   public void actualiser(Observable o)
   {
           if(o instanceof Gps)
           {       
                   Gps g = (Gps) o;
                   System.out.println("Position : "+g.getPosition()+"  Pr�cision : "+g.getPrecision()+"/10");
           }                     
   }

}
