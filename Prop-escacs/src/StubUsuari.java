public class StubUsuari {
	public StubUsuari () {
		System.out.println("Usuari creat.");
	}
	
	public StubUsuari (int color) {
		System.out.println("Usuari amb color creat.");
	}
	
	public static String getFitxerUsuaris() {
		return "Fitxer";
	}
	
	public static void setFitxerUsuaris(String fitxer) {
		System.out.println("Nou fitxer especificat.");
	}
	
	public int getTipus() {
		return 0;
	}
	
	public String getNom() {
		return "nom";
	}
	
	public void setNom(String nom) {
		System.out.println("Nom especificat.");
	}
	
	public int getColor() {
		return 0;
	}
	
	public void setColor(int color) {
		System.out.println("Color especificat.");
	}
	
	public boolean iniciarSessio(String nomUsuari) {
		return true;
	}
	
	public boolean registrar(String nomUsuari, String contrasenya1, String contrasenya2) {
		return true;
	}
	
	public boolean baixa() {
		return true;
	}
	
	private void eliminarUsuari(String usuari) {
		System.out.println("Usuari eliminat.");
	}
	
	public static String[] totsUsuaris() {
		String s = "";
		String[] tots = {s};
		return tots;
	}
	
	public long moviment(Posicion origen, Posicion desti) {
		return 0;
	}
	
	 public static void mostrarsetFitxerUsuaris() {
		 System.out.println("Contingut fitxer on es guarden usuaris.");
	 }
}
