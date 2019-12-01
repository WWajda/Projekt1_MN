import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {

    private XYChart.Series<Number,Number> orbita = new XYChart.Series<>();
    private ArrayList<Planety> galaktyka = new ArrayList<>();
    private Planety planeta = new Planety();
    private int nrMetody = 0;
    Planety merkury = new Planety(0);
    Planety wenus = new Planety(1);
    Planety ziemia = new Planety(2);
    Planety mars = new Planety(3);
    Planety jowisz = new Planety(4);
    Planety saturn = new Planety(5);
    Planety uran = new Planety(6);
    Planety neptun = new Planety(7);
    XYChart.Series<Number,Number> slonce = new XYChart.Series<>();
    ArrayList<Planety> galaktyka_zap = new ArrayList<>();
    int nrMetody_zap=0;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox cbMerkury;

    @FXML
    private CheckBox cbWenus;

    @FXML
    private CheckBox cbZiemia;

    @FXML
    private CheckBox cbMars;

    @FXML
    private CheckBox cbJowisz;

    @FXML
    private CheckBox cbSaturn;

    @FXML
    private CheckBox cbUran;

    @FXML
    private CheckBox cbNeptun;

    @FXML
    private CheckBox cbNowa;

    @FXML
    private Label lblDane;

    @FXML
    private Label lblA;

    @FXML
    private Label lblE;

    @FXML
    private Label lblNazwa;

    @FXML
    private TextField txtNazwa;

    @FXML
    private TextField txtA;

    @FXML
    private TextField txtE;

    @FXML
    private TextArea txtUwagi;

    @FXML
    private MenuButton mbtnMetoda;

    @FXML
    private MenuItem mitmBisekcja;

    @FXML
    private MenuItem mitmPunktStaly;

    @FXML
    private MenuItem mitmFalszywaLiniowosc;

    @FXML
    private MenuItem mitmSiecznych;

    @FXML
    private MenuItem mitmStycznych;

    @FXML
    private ScatterChart<Number, Number> schGraph;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private Button btnDodaj;

    @FXML
    private Button btnRysuj;

    @FXML
    private Button btnWyczysc;

    @FXML
    private Button btnZapisz;

    @FXML
    void Bisekcja(ActionEvent event) {
        mbtnMetoda.setText("Metoda bisekcji");
        mbtnMetoda.hide();
            nrMetody=1;
    }

    @FXML
    void PunktStaly(ActionEvent event) {
        mbtnMetoda.setText("Metoda punktu stalego");
        mbtnMetoda.hide();
        nrMetody=2;
    }

    @FXML
    void FalszywaLiniowosc(ActionEvent event) {
        mbtnMetoda.setText("Metoda falszywej liniowosci");
        mbtnMetoda.hide();
        nrMetody=3;
    }


    @FXML
    void Sieczne(ActionEvent event) {
        mbtnMetoda.setText("Metoda siecznych");
        mbtnMetoda.hide();
        nrMetody=4;
    }

    @FXML
    void Styczne(ActionEvent event) {
        mbtnMetoda.setText("Metoda stycznych");
        mbtnMetoda.hide();
        nrMetody=5;
    }

    @FXML
    void Add(ActionEvent event) {
            String A;
            String E;
            StringBuilder A_n=new StringBuilder();
            StringBuilder E_n=new StringBuilder();
            int error=0;

            planeta.setNazwa(txtNazwa.getText());

            A=txtA.getText();
            A_n.append(A);
        if(A.contains(","))
            A_n.replace(A.indexOf(","),A.indexOf(",")+1,".");

        int licznik=0;
        for (int i = 0; i < A.length(); i++) {

            for (int j = 0; j < 11; j++) {
                if("0123456789.".charAt(j) != A_n.charAt(i)){
                    licznik++;
                }
            }

            if(licznik==11) {
                A_n.delete(0,A_n.length());
                A_n.append("1");
                i=A.length();
                txtA.setText(A_n.toString());
                error=1;
            }
            licznik=0;
        }

        E=txtE.getText();
        E_n.append(E);
        if(E.contains(","))
            E_n.replace(E.indexOf(","),E.indexOf(",")+1,".");

        licznik=0;
        for (int i = 0; i < E.length(); i++) {

            for (int j = 0; j < 11; j++) {
                if("0123456789.".charAt(j) != E_n.charAt(i)){
                    licznik++;
                }
            }

            if(licznik==11) {
                E_n.delete(0,E_n.length());
                E_n.append("1");
                i=E.length();
                txtE.setText(E_n.toString());
                error=1;
            }
            licznik=0;
        }
            txtA.setText(A_n.toString());
            txtE.setText(E_n.toString());
            planeta.setA(Double.parseDouble(A_n.toString()));
            planeta.setE(Double.parseDouble(E_n.toString()));

            galaktyka.add(planeta);
            if(error==1 || Double.parseDouble(E_n.toString())>=1){
                galaktyka.remove(planeta);
                txtUwagi.setVisible(true);
                txtUwagi.clear();
                txtUwagi.setText("Wprowadzono zle wartosci parametrow.\n" +
                        "Prosze wprowadzic wartosci, ktore spelniaja ponizsze kryteria.\n" +
                        "Dodatnia liczba zmiennoprzecinkowa:\n" +
                        "* EKSCENTRYCZNOSC - z zakresu (0,1).\n" +
                        "* ODLEGLOSC - jednostka [A.U].\n");
            }
            else{
                txtUwagi.clear();
                txtUwagi.setVisible(false);
            }


    }

    @FXML
    void Draw(ActionEvent event) {
            slonce.getData().clear();
            orbita.getData().clear();
            schGraph.getData().clear();

            if(nrMetody>0 && nrMetody<=5) {
                txtUwagi.clear();
                txtUwagi.setVisible(false);
                slonce.getData().add(new XYChart.Data(0, 0));
                schGraph.getData().add(slonce);
                slonce.setName("Slonce");
                galaktyka_zap.clear();
                for (int i = 0; i < galaktyka.size(); i++) {
                    galaktyka.get(i).oblicznieOrbity(nrMetody);
                    orbita = galaktyka.get(i).getWspolrzedne();
                    orbita.setName(galaktyka.get(i).getNazwa());
                    schGraph.getData().add(orbita);
                    schGraph.setLegendVisible(true);
                    galaktyka_zap.add(galaktyka.get(i));
                }
                nrMetody_zap=nrMetody;
                restart();
                xAxis.setLabel("x [A.U.]");
                yAxis.setLabel("y [A.U.]");
            }
            else{
                txtUwagi.setVisible(true);
                txtUwagi.clear();
                txtUwagi.setText("Nie wybrano metody.\n" +
                        "Prosze wybrac jedna z pieciu dostepnych metod oblicznia.");
            }

    }

    void odznacz(){
        cbMerkury.setSelected(false);
        cbWenus.setSelected(false);
        cbZiemia.setSelected(false);
        cbMars.setSelected(false);
        cbJowisz.setSelected(false);
        cbSaturn.setSelected(false);
        cbUran.setSelected(false);
        cbNeptun.setSelected(false);
    }
    void restart(){
        galaktyka.clear();
        nrMetody=0;
        odznacz();
        ukryj();
        txtNazwa.setText("Nowa");
        txtA.setText("1");
        txtE.setText("1");
        cbNowa.setSelected(false);
        mbtnMetoda.setText("Wybierz Metode");
    }
    @FXML
    void Clear(ActionEvent event) {
        schGraph.getData().clear();
        orbita.getData().clear();
        slonce.getData().clear();
        restart();
    }

    @FXML
    void Save(ActionEvent event) {
        if(galaktyka_zap.isEmpty()){
            txtUwagi.setVisible(true);
            txtUwagi.clear();
            txtUwagi.setText("Brak danych do  zapisu.");
        }
        else {
            txtUwagi.setVisible(false);
            txtUwagi.clear();
            for (int i = 0; i < galaktyka_zap.size(); i++) {
                Zapis.Save(galaktyka_zap.get(i).getNazwa(), galaktyka_zap.get(i).getX(), galaktyka_zap.get(i).getY(), galaktyka_zap.get(i).getNazwa_metody(), galaktyka_zap.get(i).getBledy());
            }
        }
    }

    @FXML
    void MerkuryChecked(ActionEvent event) {
        if (cbMerkury.isSelected()) {
            galaktyka.add(merkury);
        }
        else {
            galaktyka.remove(merkury);
        }
    }

    @FXML
    void WenusChecked(ActionEvent event) {
        if (cbWenus.isSelected()) {
            galaktyka.add(wenus);
        }
        else {
            galaktyka.remove(wenus);
        }
    }

    @FXML
    void ZiemiaChecked(ActionEvent event) {
        if (cbZiemia.isSelected()) {
            galaktyka.add(ziemia);
        }
        else {
            galaktyka.remove(ziemia);
        }
    }

    @FXML
    void MarsChecked(ActionEvent event) {
        if (cbMars.isSelected()) {
            galaktyka.add(mars);
        }
        else {
            galaktyka.remove(mars);
        }
    }

    @FXML
    void JowiszChecked(ActionEvent event) {
        if (cbJowisz.isSelected()) {
            galaktyka.add(jowisz);
        }
        else {
            galaktyka.remove(jowisz);
        }
    }

    @FXML
    void SaturnChecked(ActionEvent event) {
        if (cbSaturn.isSelected()) {
            galaktyka.add(saturn);
        }
        else {
            galaktyka.remove(saturn);
        }
    }

    @FXML
    void UranChecked(ActionEvent event) {
        if (cbUran.isSelected()) {
            galaktyka.add(uran);
        }
        else {
            galaktyka.remove(uran);
        }
    }

    @FXML
    void NeptunChecked(ActionEvent event) {
        if (cbNeptun.isSelected()) {
            galaktyka.add(neptun);
        }
        else {
            galaktyka.remove(neptun);
        }
    }

    void pokaz(){
        lblA.setVisible(true);
        lblE.setVisible(true);
        lblDane.setVisible(true);
        lblNazwa.setVisible(true);
        txtA.setVisible(true);
        txtE.setVisible(true);
        txtNazwa.setVisible(true);
        btnDodaj.setVisible(true);
    }

    void ukryj(){
        lblA.setVisible(false);
        lblE.setVisible(false);
        lblDane.setVisible(false);
        lblNazwa.setVisible(false);
        txtA.setVisible(false);
        txtE.setVisible(false);
        txtNazwa.setVisible(false);
        btnDodaj.setVisible(false);
    }

    @FXML
    void CreateNewPlanet(ActionEvent event) {
        if (cbNowa.isSelected()) {
            pokaz();
        }
        else{
            ukryj();
            galaktyka.remove(planeta);
        }

    }


    @FXML
    void initialize() {
        assert cbMerkury != null : "fx:id=\"cbMerkury\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert cbWenus != null : "fx:id=\"cbWenus\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert cbZiemia != null : "fx:id=\"cbZiemia\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert cbMars != null : "fx:id=\"cbMars\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert cbJowisz != null : "fx:id=\"cbJowisz\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert cbSaturn != null : "fx:id=\"cbSaturn\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert cbUran != null : "fx:id=\"cbUran\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert cbNeptun != null : "fx:id=\"cbNeptun\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert cbNowa != null : "fx:id=\"cbNowa\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert lblDane != null : "fx:id=\"lblDane\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert lblA != null : "fx:id=\"lblA\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert lblE != null : "fx:id=\"lblE\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert lblNazwa != null : "fx:id=\"lblNazwa\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert txtNazwa != null : "fx:id=\"txtNazwa\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert txtA != null : "fx:id=\"txtA\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert txtE != null : "fx:id=\"txtE\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert mbtnMetoda != null : "fx:id=\"mbtnMetoda\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert mitmBisekcja != null : "fx:id=\"mitmBisekcja\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert mitmPunktStaly != null : "fx:id=\"mitmPunktStaly\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert mitmFalszywaLiniowosc != null : "fx:id=\"mitmFalszywaLiniowosc\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert mitmSiecznych != null : "fx:id=\"mitmSiecznych\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert mitmStycznych != null : "fx:id=\"mitmStycznych\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert btnDodaj != null : "fx:id=\"btnDodaj\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert txtUwagi != null : "fx:id=\"txtUwagi\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert schGraph != null : "fx:id=\"schGraph\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert btnRysuj != null : "fx:id=\"btnRysuj\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert btnWyczysc != null : "fx:id=\"btnWyczysc\" was not injected: check your FXML file 'fxproject.fxml'.";
        assert btnZapisz != null : "fx:id=\"btnZapisz\" was not injected: check your FXML file 'fxproject.fxml'.";

        lblDane.setVisible(false);
        lblNazwa.setVisible(false);
        lblA.setVisible(false);
        lblE.setVisible(false);
        txtNazwa.setVisible(false);
        txtNazwa.setText("Nowa");
        txtA.setVisible(false);
        txtA.setText("1");
        txtE.setVisible(false);
        txtE.setText("1");
        btnDodaj.setVisible(false);
        txtUwagi.setVisible(false);

    }
}
