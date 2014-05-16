package singleton;

//Classe principale de l'application.
public class SingletonMain
{
     // M�thode principale.
     public static void main(String[] args)
     {
             // Cr�ation et utilisation du CompteBancaire cb1.
             CompteBancaire cb1 = new CompteBancaire(123456789);
             cb1.deposerArgent(100);
             cb1.retirerArgent(80);
             // Cr�ation et utilisation du CompteBancaire cb2.
             CompteBancaire cb2 = new CompteBancaire(987654321);
             cb2.retirerArgent(10);
             // Affichage des logs en console.
             String s = Journalisation.getInstance().afficherLog();
             System.out.println(s);
     }

}
