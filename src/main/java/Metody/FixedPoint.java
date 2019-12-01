package Metody;

import java.util.ArrayList;
import static java.lang.Double.isNaN;

public class FixedPoint {

        private RownanieKosmiczne rk;
        private double e;
        private ArrayList<Double> E=new ArrayList<>();
        private ArrayList<Double> ea = new ArrayList<>();
    private String nazwa;

        public FixedPoint(double e, RownanieKosmiczne rk) {
            this.rk = rk;
            this.e = e;
            nazwa="Punkt staly";
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
                double xn_n = 0;
                double xn_o = 0;
                double blad_ea=0;

                while (iter < maxIter) {
                    iter++;
                    xn_o = xn_n;
                    xn_n = M+e*Math.sin(xn_n);


                    blad_ea = (xn_n - xn_o) / xn_n * 100;


                    if(!isNaN(xn_n) && !isNaN(blad_ea)) {
                        ea.add((double) iter);
                        ea.add(blad_ea);
                    }

                    if(rk.getE(xn_n,M,e)==0 || xn_o==xn_n || isNaN(xn_n))
                        iter=maxIter;
                }
                if(!isNaN(xn_n))
                    E.add(xn_n);
            }
        return  E;
    }
}
