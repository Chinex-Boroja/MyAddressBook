/**A program that stores, retrieves, adds, and updates addresses.
 ** Using a fixed-length string for storing each attribute in the address.
 ** Used random access file for reading and writing an address.
 ** Assume that the size of name, street, emailAddress, phone, city, state, and zip,
 ** are 32, 32, 32, 12, 20, 10, 5 bytes, respectively. */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddressBook extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ResourceFiles/addressBook.fxml"));

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("MyAddressBook");
        Scene scene = new Scene(root,400,530);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
