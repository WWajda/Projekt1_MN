package Metody;

import Metody.RownanieKosmiczne;

import java.util.ArrayList;

import static java.lang.Double.isNaN;


public class MetodaSiecznych {

    private RownanieKosmiczne rk;
    private double e;
    private ArrayList<Double> E = new ArrayList<>();
    private ArrayList<Double> ea = new ArrayList<>();
    private String nazwa;

    public MetodaSiecznych(double e, RownanieKosmiczne rk) {
        this.rk = rk;
        this.e = e;
        nazwa="Metoda siecznych";
    }

    public ArrayList<Double> getEa() {
        return ea;
    }

    public String getNazwa() {
        return nazwa;
    }

    public ArrayList solver() {
        E.clear();
        ea.clear();
        int maxIter = 100;
        for (double M = 0; M < 2 * Math.PI; M += 0.01) {
            int iter = 0;
            double xn_n2 = 2 * Math.PI;
            double xn_n1 = 0;
            double xn_n = 0;
            double x_old=0;
            double blad_ea=0;
                while (iter < maxIter) {
                    iter++;
                    x_old=xn_n;
                    xn_n = xn_n2 - (((rk.getE(xn_n2, M, e) * (xn_n1 - xn_n2))) / (rk.getE(xn_n1, M, e) - rk.getE(xn_n2, M, e)));
                    if (Math.abs(xn_n1 - xn_n) < Math.abs(xn_n2 - xn_n)) {
                        xn_n2 = xn_n;
                    } else {
                        xn_n1 = xn_n;
                    }
                    blad_ea = (xn_n - x_old) / xn_n * 100;

                    if(!isNaN(xn_n) && !isNaN(blad_ea)) {
                        ea.add((double) iter);
                        ea.add(blad_ea);
                    }

                    if(rk.getE(xn_n,M,e)==0 || x_old==xn_n || isNaN(xn_n))
                        iter=maxIter;
                }
            if(!isNaN(xn_n))
                E.add(xn_n);
        }
        return E;
    }
}
