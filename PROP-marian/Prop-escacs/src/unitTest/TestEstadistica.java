package unitTest;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;

public class TestEstadistica {
    @BeforeClass
    public static void setUp() throws Exception {
        Estadistica.setFitxerStats("./src/unitTest/testingEstadistiques.txt");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        ArrayList<String> tots =  Estadistica.mostrarFitxerStats();
        for (String aux : tots) {
            String[] dades = aux.split("\\s+");
            Estadistica.eliminarStatsProblema(dades[0]);
        }
    }


    @Test
    public void guardarTemps() {
        String guarda = "ProbT UsrT 3 5511";
        String[] partsGuarda = guarda.split("\\s+");
        Estadistica.guardarTemps(partsGuarda[0], partsGuarda[1], partsGuarda[2], partsGuarda[3]);

        ArrayList<String> statsDespres = Estadistica.mostrarFitxerStats();
        String ultimAfegit = statsDespres.get(statsDespres.size() - 1);

        assertEquals("No s'ha guardat al fitxer", ultimAfegit, guarda);
    }

    @Test
    public void estadistiquesProblema() {
        String marca1 = "ProbT1 UsrT2 3 5511";
        String marca2 = "ProbT1 UsrT1 2 55311";
        String marca3 = "ProbT1 UsrT1 3 8511";

        String[] partsGuarda1 = marca1.split("\\s+");
        Estadistica.guardarTemps(partsGuarda1[0], partsGuarda1[1], partsGuarda1[2], partsGuarda1[3]);

        String[] partsGuarda2 = marca2.split("\\s+");
        Estadistica.guardarTemps(partsGuarda2[0], partsGuarda2[1], partsGuarda2[2], partsGuarda2[3]);

        String[] partsGuarda3 = marca3.split("\\s+");
        Estadistica.guardarTemps(partsGuarda3[0], partsGuarda3[1], partsGuarda3[2], partsGuarda3[3]);

        ArrayList<String> statsProblema = Estadistica.estadistiquesProblema("ProbT1");

        ArrayList<String> resultatOrdenat = new ArrayList<String>();
        resultatOrdenat.add("UsrT1  2  0min 55s"); // marca 2
        resultatOrdenat.add("UsrT2  3  0min 5s"); // marca 1
        resultatOrdenat.add("UsrT1  3  0min 8s"); // marca 3

        assertEquals("Estadistiques del problema errones", statsProblema, resultatOrdenat);
    }

    @Test
    public void estadistiquesUsuari() {
        String marca1 = "ProbT2 UsrT3 3 5511";
        String marca2 = "ProbT3 UsrT3 2 55311";
        String marca3 = "ProbT4 UsrT3 3 8511";

        String[] partsGuarda1 = marca1.split("\\s+");
        Estadistica.guardarTemps(partsGuarda1[0], partsGuarda1[1], partsGuarda1[2], partsGuarda1[3]);

        String[] partsGuarda2 = marca2.split("\\s+");
        Estadistica.guardarTemps(partsGuarda2[0], partsGuarda2[1], partsGuarda2[2], partsGuarda2[3]);

        String[] partsGuarda3 = marca3.split("\\s+");
        Estadistica.guardarTemps(partsGuarda3[0], partsGuarda3[1], partsGuarda3[2], partsGuarda3[3]);

        ArrayList<String> statsUsuari = Estadistica.estadistiquesUsuari("UsrT3");

        ArrayList<String> resultatOrdenat = new ArrayList<String>();
        resultatOrdenat.add("ProbT3  2  0min 55s"); // marca 2
        resultatOrdenat.add("ProbT2  3  0min 5s"); // marca 1
        resultatOrdenat.add("ProbT4  3  0min 8s"); // marca 3

        assertEquals("Estadistiques de l'usuari errones", statsUsuari, resultatOrdenat);
    }

    @Test
    public void eliminarStatsUsuari() {
        String marca1 = "ProbT2 UsrT4 3 5511";
        String marca2 = "ProbT3 UsrT4 2 55311";

        String[] partsGuarda1 = marca1.split("\\s+");
        Estadistica.guardarTemps(partsGuarda1[0], partsGuarda1[1], partsGuarda1[2], partsGuarda1[3]);

        String[] partsGuarda2 = marca2.split("\\s+");
        Estadistica.guardarTemps(partsGuarda2[0], partsGuarda2[1], partsGuarda2[2], partsGuarda2[3]);

        Estadistica.eliminarStatsUsuari("UsrT4");
        ArrayList<String> statsUsuari = Estadistica.estadistiquesUsuari("UsrT4");

        assertTrue("Estadistiques de l'usuari no eliminades", statsUsuari.isEmpty());
    }


    @Test
    public void eliminarStatsProblema() {
        String marca1 = "ProbEliminat Usr 3 5511";
        String marca2 = "ProbEliminat Usr 2 55311";

        String[] partsGuarda1 = marca1.split("\\s+");
        Estadistica.guardarTemps(partsGuarda1[0], partsGuarda1[1], partsGuarda1[2], partsGuarda1[3]);

        String[] partsGuarda2 = marca2.split("\\s+");
        Estadistica.guardarTemps(partsGuarda2[0], partsGuarda2[1], partsGuarda2[2], partsGuarda2[3]);

        Estadistica.eliminarStatsProblema("ProbEliminat");
        ArrayList<String> statsProblema = Estadistica.estadistiquesProblema("ProbEliminat");

        assertTrue("Estadistiques de l'usuari no eliminades", statsProblema.isEmpty());
    }
} 
