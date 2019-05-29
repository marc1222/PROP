package gestioDades;

import java.io.*;
import java.util.ArrayList;

public class GestorPersistenciaProblema {
    static private String fitxer = "./files/problemes.txt";
    static private String fitxerId = "./files/index.txt";
    private static int index = -1;

    /**
     * Assigna els Id als problemes
     * Pre: true
     * @return el següent Id de problema disponible o 0 si és el primer (identificador únic)
     */
    public static int getNextId() {
        if (index == -1) {
            index = llegirId();
        }
        ++index;
        guardarId();
        return index;
    }

    /**
     * Escriu l'últim Id de problema assignat en el fitxer index.txt i si no existia el crea
     * Pre: true
     */
    private static void guardarId() {
        try (FileWriter fileWriter = new FileWriter(fitxerId, false); PrintWriter pw = new PrintWriter(fileWriter)) {
            pw.println(String.valueOf(index));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Llegeix l'id actual del fitxer
     * Pre: true
     * @return l'últim Id de problema assignat del fitxer index.txt si exitiex, si no retorna -1 o -2 en cas d'error
     */
    private static int llegirId() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerId))) {
            String line;
            if ((line = br.readLine()) != null) {
                return Integer.parseInt(line);
            }
            else return -1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -2; // try return
    }

    public boolean comprovarExistencia(String snjug, String sprim, String sdif, String ini_pos, String idc) {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split("\\s+");
                if (camps[1].equals(snjug) && camps[2].equals(sprim) && camps[3].equals(ini_pos) && camps[5].equals(idc)) { //o nomes prob_id?
                    //problema ja existeix
                    //System.out.println("El problema ja existex");
                    return true;
                }
            }
            return false;
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void escriuProblema(String sid, String snjug, String sprim, String ini_pos, String sdif, String idc) {
        input_output in_out = new input_output();
        String[] linia = {sid, snjug, sprim, ini_pos, sdif, idc};
        in_out.write(fitxer, linia);
    }

    public int borrarProblema(String borra_linia) {
        File tempfile = new File ("/home/narcis/PROP/Prop-escacs/files/mytemp.txt");
        File inputfile = new File (fitxer);
        boolean trobat = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxer));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(borra_linia)) {
                    writer.write(line + System.lineSeparator());
                }
                else {
                    trobat = true;
                }
            }
            if (trobat) {
                inputfile.delete();
                tempfile.renameTo(inputfile);
            }
            else {
                return -1;
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String[][] llegirProblemes() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            ArrayList<String[]> probs = new ArrayList<String[]>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split("\\s+");
                probs.add(camps);
            }
            String res[][] = new String[probs.size()][6];
            res = probs.toArray(res);
            return res;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            String res[][] = new String[1][1];
            res[0][0] = "error";
            return res;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] llegirProblemaId(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split("\\s+");
                String sid = String.valueOf(id);
                if (camps[0].equals(sid)) {
                    return camps;
                }
            }
            String res[] = new String[2];
            res[0] = "error";
            res[1] = "No trobat";
            return res;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            String res[] = new String[1];
            res[0] = "error";
            return res;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
