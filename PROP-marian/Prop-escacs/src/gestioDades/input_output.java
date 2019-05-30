package gestioDades;
import java.io.*;
//Classe que s'encarrega de fer tot el input / output cap a fitxers,
// i que es crida per totes les classes que necessiten algun tipus de lectura escriptura a un fitxer
public class input_output {
    //Operacio que llegeix dun fitxer anomenat filename i retorna
   public String[] read(String filename, int id_linea) {
       File archivo = null;
       FileReader fr = null;
       BufferedReader br = null;
       try {
           archivo = new File(filename);
           fr = new FileReader(archivo);
           br = new BufferedReader(fr);

           String line;
           while ((line = br.readLine()) != null) {
               String[] splited = line.split(" ");
               int result = Integer.parseInt(splited[0]);
               if (result == id_linea) {
                   String[] aux = new String[splited.length];
                   for (int i = 0; i < splited.length; ++i) aux[i] = splited[i];
                   return aux;
               }
           }
       } catch(Exception e){
        e.printStackTrace();
       } finally{
           try{
               if( null != fr ){
                   fr.close();
               }
           }catch (Exception e2){
               e2.printStackTrace();
           }
       }
       String[] aux = new String[1];
       aux[0] = "NF";
       return aux;
   }
   //Se encara de escribir tot el array line en una nova linea de un fitxer ja existent
   public void write(String filename, String[] line) {
       FileWriter fichero = null;
       PrintWriter pw = null;
       try {
           fichero = new FileWriter(filename,true);
           pw = new PrintWriter(fichero);
           String aux = String.join(" ",line);
           pw.println(aux);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
               if (null != fichero) fichero.close();
           } catch (Exception e2) {
               e2.printStackTrace();
           }
       }
   }

}
