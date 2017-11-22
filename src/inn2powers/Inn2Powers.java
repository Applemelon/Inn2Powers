/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers;

import inn2powers.DAL.AddALLCompaniesToDatabase;
import inn2powers.DAL.AddAllRelationsToDatabase;
import inn2powers.DAL.DALException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alex
 */
public class Inn2Powers extends Application {
    
    @Override

    public void start(Stage stage) throws Exception {    
        Parent root = FXMLLoader.load(getClass().getResource("GUI/VIEW/MainWindow.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException, DALException{
        AddAllRelationsToDatabase aa = new AddAllRelationsToDatabase(); 
        launch(args);
    }
    
}
