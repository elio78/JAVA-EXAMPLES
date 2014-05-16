package observer;


//Affiche en console de façon complète les informations (position et précision) du GPS.
public class AfficheComplet implements Observateur
{
   // Méthode appelée automatiquement lors d'un changement d'état du GPS.
   public void actualiser(Observable o)
   {
           if(o instanceof Gps)
           {       
                   Gps g = (Gps) o;
                   System.out.println("Position : "+g.getPosition()+"  Précision : "+g.getPrecision()+"/10");
           }                     
   }

}
