import Metody.*;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Planety {

    private int numer;
    private static int numer2=7;
    private String [] name = {"Merkury","Wenus","Ziemia","Mars","Jowisz","Saturn","Uran","Neptun"};
    private String nazwa;
    private double [] distance = {0.387,0.723,1,1.524,5.203,9.537,19.191,30.069};
    private double a;
    private double [] eccentricity = {0.2056,0.0068,0.0167,0.0934,0.0484,0.0542,0.0472,0.0086};
    private double e;
    private ArrayList<Double> X = new ArrayList<>();
    private ArrayList<Double> Y = new ArrayList<>();
    private XYChart.Series wspolrzedne= new XYChart.Series();
    private ArrayList<Double> bledy= new ArrayList<>();
    private String nazwa_metody;



    public Planety() {
    }

    public Planety(int numer) {
        this.numer = numer;
        nazwa = name[numer];
        a = distance[numer];
        e = eccentricity[numer];
    }

    public Planety(String nazwa, double a, double e) {
        numer = numer2++;
        this.nazwa = nazwa;
        this.a = a;
        this.e = e;
    }

    @Override
    public String toString() {
        return "Planeta " + nazwa + "\t" +
                "a=" + a + "\t" +
                "e=" + e;
    }

    public int getNumer() {
        return numer;
    }


    public String getNazwa() {
        return nazwa;
    }


    public double getA() {
        return a;
    }


    public double getE() {
        return e;
    }

    public ArrayList<Double> getBledy() {
        return bledy;
    }

    public ArrayList<Double> getX() {
        return X;
    }

    public ArrayList<Double> getY() {
        return Y;
    }

    public XYChart.Series getWspolrzedne() {
        return wspolrzedne;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setE(double e) {
        this.e = e;
    }

    public String getNazwa_metody() {
        return nazwa_metody;
    }

    public void oblicznieOrbity(int nrMetody){
        double x;
        double y;
        ArrayList<Double> E_katy = new ArrayList<>();
        switch (nrMetody){
            case 1:
                Bisection bisection = new Bisection(e,(M, E, e) -> M+e*Math.sin(E)-E);
                E_katy=bisection.solver();
                bledy.clear();
                bledy=bisection.getEa();
                nazwa_metody=bisection.getNazwa();

                break;

            case 2:
                FixedPoint fixedPoint = new FixedPoint(e,(M, E, e) -> M+e*Math.sin(E)-E);
                E_katy=fixedPoint.solver();
                bledy=fixedPoint.getEa();
                nazwa_metody=fixedPoint.getNazwa();

                break;

            case 3:
                RegularFalsi regularFalsi = new RegularFalsi(e,(M, E, e) -> M+e*Math.sin(E)-E);
                E_katy=regularFalsi.solver();
                bledy=regularFalsi.getEa();
                nazwa_metody=regularFalsi.getNazwa();

                break;

            case 4:
                MetodaSiecznych metodaSiecznych = new MetodaSiecznych(e,(M, E, e) -> M+e*Math.sin(E)-E);
                E_katy=metodaSiecznych.solver();
                bledy=metodaSiecznych.getEa();
                nazwa_metody=metodaSiecznych.getNazwa();
                break;

            case 5:
                MetodaStycznych metodaStycznych = new MetodaStycznych(e,(M, E, e) -> M+e*Math.sin(E)-E,(E, e) -> e*Math.cos(E)-1);
                E_katy=metodaStycznych.solver();
                bledy=metodaStycznych.getEa();
                nazwa_metody=metodaStycznych.getNazwa();
                break;
        }
                X.clear();
                Y.clear();
                wspolrzedne.getData().clear();
                for (int i = 0; i<E_katy.size();i++) {
                    x = a * (Math.cos(E_katy.get(i)) - e);
                    X.add(x);
                    y = a * Math.sqrt(1 - Math.pow(e, 2)) * Math.sin(E_katy.get(i));
                    Y.add(y);
                    wspolrzedne.getData().add(new XYChart.Data(x, y));
                }

    }

}
