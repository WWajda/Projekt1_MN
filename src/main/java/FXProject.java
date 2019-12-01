import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;

public class FXProject extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/fxproject.fxml"));
        Scene scene = new Scene(root,900,600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("/fxml/fxproject.css");
        primaryStage.show();

    }
}
