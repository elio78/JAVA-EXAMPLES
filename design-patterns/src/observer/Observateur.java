package observer;

//Interface implémentée par tous les observateurs.
	public interface Observateur
	{
	        // Méthode appelée automatiquement lorsque l'état (position ou précision) du GPS change.
	        public void actualiser(Observable o);
	}
