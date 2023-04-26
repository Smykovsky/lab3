package pl.smyk.lab3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.smyk.lab3.dao.CodeDAO;
import pl.smyk.lab3.model.Code;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private CodeDAO codeDAO;
    @FXML
    private TableColumn<Code, String> adress;
    @FXML
    private TextField adressField;
    @FXML
    private TableColumn<Code, String> county;
    @FXML
    private TextField countyField;
    @FXML
    private TableColumn<Code, Long> id;
    @FXML
    private TableColumn<Code, String> place;
    @FXML
    private TextField placeField;
    @FXML
    private TableColumn<Code, String> postCode;
    @FXML
    private TextField postCodeField;
    @FXML
    private TableView<Code> tableView;
    @FXML
    private TableColumn<Code, String> voivoship;
    @FXML
    private TextField voivoshipField;

    @FXML
    protected void load() {
       try {
           codeDAO.loadData();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @FXML
    private ObservableList<Code> loadCodes() {
        List<Code> codeList;
        try {
            codeList = codeDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(codeList);
    }
    @FXML
    private List<Code> findByCriteria() {
        List<Code> listByCriteria = codeDAO.getByCriteria(
                postCodeField.getText(),
                adressField.getText(),
                placeField.getText(),
                voivoshipField.getText(),
                countyField.getText());
        System.out.println(listByCriteria);
        tableView.getItems().setAll(listByCriteria);
        return listByCriteria;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //wyświetlanie danych po kliknięciu w wiersz tabeli
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Code selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    System.out.println(selectedItem.getLocationList());
                    tableView.getItems().setAll(selectedItem);
                }
            }
        });

        try {
            codeDAO = new CodeDAO();
            showCodes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        postCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        voivoship.setCellValueFactory(new PropertyValueFactory<>("voivoship"));
        county.setCellValueFactory(new PropertyValueFactory<>("county"));
    }

    @FXML
    public void showCodes() {
        List<Code> codes;
        id.setCellValueFactory(new PropertyValueFactory<Code, Long>("id"));
        postCode.setCellValueFactory(new PropertyValueFactory<Code, String>("postCode"));
        adress.setCellValueFactory(new PropertyValueFactory<Code, String>("adress"));
        place.setCellValueFactory(new PropertyValueFactory<Code, String>("place"));
        voivoship.setCellValueFactory(new PropertyValueFactory<Code, String>("voivoship"));
        county.setCellValueFactory(new PropertyValueFactory<Code, String>("county"));

        codes = loadCodes();

        tableView.getItems().setAll(FXCollections.observableArrayList(codes));
    }
}