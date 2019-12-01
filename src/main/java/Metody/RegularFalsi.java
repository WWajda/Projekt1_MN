package Metody;

import Metody.RownanieKosmiczne;

import java.util.ArrayList;

import static java.lang.Double.isNaN;

public class RegularFalsi {

    private RownanieKosmiczne rk;
    private double e;
    private ArrayList<Double> E=new ArrayList<>();
    private ArrayList<Double> ea = new ArrayList<>();
    private String nazwa;

    public RegularFalsi(double e, RownanieKosmiczne rk) {
        this.rk = rk;
        this.e = e;
        nazwa="Falszywa liniowosc";
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
            double xu = 2 * Math.PI;
            double xl = 0;
            double xr_n = 0;
            double x_old=0;
            double blad_ea=0;
            while (iter < maxIter) {
                iter++;
                x_old=xr_n;

                    xr_n = xu - ((rk.getE(xu, M, e) * (xl - xu)) / (rk.getE(xl, M, e) - rk.getE(xu, M, e)));
                    if (rk.getE(xl, M, e) * rk.getE(xr_n, M, e) <= 0) {
                        xu = xr_n;
                    } else if (rk.getE(xu, M, e) * rk.getE(xr_n, M, e) <= 0) {
                        xl = xr_n;
                    }

                blad_ea = (xr_n - x_old) / xr_n * 100;

                if(!isNaN(xr_n) && !isNaN(blad_ea)) {
                    ea.add((double) iter);
                    ea.add(blad_ea);
                }

            if(rk.getE(xr_n,M,e)==0 || x_old==xr_n || isNaN(xr_n))
                iter=maxIter;
        }
        if(!isNaN(xr_n))
            E.add(xr_n);

        }
        return  E;
    }
}

