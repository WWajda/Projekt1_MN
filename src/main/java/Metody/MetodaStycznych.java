package Metody;

import Metody.PochodnaRownaniaKosmicznego;
import Metody.RownanieKosmiczne;

import java.util.ArrayList;

import static java.lang.Double.isNaN;

public class MetodaStycznych {

    private RownanieKosmiczne rk;
    private PochodnaRownaniaKosmicznego prk;
    private double e;
    private ArrayList<Double> E = new ArrayList<>();
    private ArrayList<Double> ea = new ArrayList<>();
    private String nazwa;

    public MetodaStycznych(double e, RownanieKosmiczne rk,PochodnaRownaniaKosmicznego prk) {
        this.rk = rk;
        this.e = e;
        this.prk = prk;
        nazwa="Metoda Stycznych";
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
            double xn_o = 0;
            double xn_n = 0;
            double blad_ea=0;
            while (iter < maxIter) {
                iter++;
                xn_o = xn_n;
                xn_n = xn_o - (rk.getE(xn_o, M, e) / prk.getE_prim(xn_o, e));

                if(rk.getE(xn_n,M,e)==0 || xn_o==xn_n || isNaN(xn_n))
                    iter=maxIter;

                blad_ea = (xn_n - xn_o) / xn_n * 100;

                if(!isNaN(xn_n) && !isNaN(blad_ea)) {
                    ea.add((double) iter);
                    ea.add(blad_ea);
                }

            }
            if(!isNaN(xn_n))
                E.add(xn_n);

        }

        return E;
    }
}
