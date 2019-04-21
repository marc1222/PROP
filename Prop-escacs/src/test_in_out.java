
public class test_in_out {
    public static void main(String [ ] args)
    {
        input_output file_manager = new input_output();
        String[] aux = new String[5];
        aux = file_manager.read("./files/prova.txt",1);
        for (int i = 0; i < aux.length; ++i) System.out.println(aux[i]);
        file_manager.write("./files/prova.txt",aux);
    }
}
