import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Zapis {

    public static void Save(String nazwa_planety,  ArrayList<Double> X, ArrayList<Double> Y,  String nazwa_metody, ArrayList<Double> blad_metody){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("Wspolrzedne_dla " + nazwa_planety+ " Metoda - " + nazwa_metody +".txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(nazwa_planety+ "\nWspolrzedne\n\tX \t\t\t Y\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < X.size(); i++) {
                bufferedWriter.write(X.get(i) +"\t" + Y.get(i)+"\n");
            }
        }catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        try{
            if(!X.isEmpty()) {
                bufferedWriter.newLine();
                bufferedWriter.write(nazwa_metody+"\nBledy\nnr\tet [%]\n");
                for (int i = 1; i < blad_metody.size(); i+=2) {
                    if (blad_metody.get(i-1)==1)
                        bufferedWriter.newLine();
                    bufferedWriter.write(blad_metody.get(i-1)+"\t"+blad_metody.get(i)+"\n");

                }
                bufferedWriter.close();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
