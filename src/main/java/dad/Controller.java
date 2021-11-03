package dad;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {

    // model

    private ListProperty<String> disponibleListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ListProperty<String> seleccionListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    @FXML
    private Button derButton;

    @FXML
    private Button derTotalButton;

    @FXML
    private ListView<String> disponibleList;

    @FXML
    private Button izqButton;

    @FXML
    private Button izqTotalButton;

    @FXML
    private ListView<String> seleccionadoList;

    @FXML
    private GridPane view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        seleccionadoList.itemsProperty().bind(seleccionListProperty);
        disponibleList.itemsProperty().bind(disponibleListProperty);

        seleccionListProperty.setAll("Alejandro", "Pedro");
        disponibleListProperty.setAll("Tere", "Andres");

        izqButton.disableProperty().bind(seleccionadoList.getSelectionModel().selectedItemProperty().isNull());
        derButton.disableProperty().bind(disponibleList.getSelectionModel().selectedItemProperty().isNull());
        // derTotalButton.disableProperty().bind();
        // izqTotalButton.disableProperty().bind();
    }

    public Controller() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Inventario.fxml"));
        loader.setController(this);
        loader.load();
    }

    @FXML
    void onDerAction(ActionEvent event) {

        seleccionListProperty.addAll(disponibleList.getSelectionModel().getSelectedItems());
        disponibleListProperty.remove(disponibleList.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onDerTotalAction(ActionEvent event) {

        seleccionListProperty.addAll(disponibleList.getItems());
        disponibleListProperty.removeAll(disponibleList.getItems());
    }

    @FXML
    void onIzqAction(ActionEvent event) {
        disponibleListProperty.addAll(seleccionadoList.getSelectionModel().getSelectedItems());
        seleccionListProperty.remove(seleccionadoList.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onTotalizqAction(ActionEvent event) {

        disponibleListProperty.addAll(seleccionadoList.getItems());
        seleccionListProperty.removeAll(seleccionadoList.getItems());

    }

    public GridPane getView() {
        return view;
    }

}
