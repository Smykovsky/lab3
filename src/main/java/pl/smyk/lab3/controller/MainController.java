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
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import pl.smyk.lab3.dao.CodeDAO;
import pl.smyk.lab3.dto.LocationDTO;
import pl.smyk.lab3.dto.LocationDtoMapper;
import pl.smyk.lab3.model.Code;
import pl.smyk.lab3.model.Location;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    private CodeDAO codeDAO;
    @FXML
    private TableColumn<Code, String> adress;
    @FXML
    private TextField adressField;
    @FXML
    private TextField commentsField;
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
    private TableColumn<Code, String> comments;
    @FXML
    private TableView<LocationDTO> locationTable;
    @FXML
    private TableColumn<LocationDTO, Long> locationId;
    @FXML
    private TableColumn<LocationDTO, String> name;
    @FXML
    private TableColumn<LocationDTO, String> description;
    @FXML
    private TableColumn<LocationDTO, Long> codeId;
    @FXML
    private TextField codeIdEditField;
    @FXML
    private TextField postCodeEditField;
    @FXML
    private TextField adressEditField;
    @FXML
    private TextField placeEditField;
    @FXML
    private TextField voivoshipEditField;
    @FXML
    private TextField countyEditField;
    @FXML
    private TextField commentsEditField;


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

    @FXML
    private void deleteById() {
        codeDAO.delete(Long.parseLong(codeIdEditField.getText()));
        showCodes();
    }

    @FXML
    private void update() {
        codeDAO.update(
                Long.parseLong(codeIdEditField.getText()),
                postCodeEditField.getText(),
                adressEditField.getText(),
                placeEditField.getText(),
                voivoshipEditField.getText(),
                countyEditField.getText(),
                commentsEditField.getText());
        showCodes();
    }

    public void rowClickEvent() {
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Code selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    LocationDtoMapper mapper = new LocationDtoMapper();
                    List<Location> locationList = selectedItem.getLocationList();
                    List<LocationDTO> dtos = new ArrayList<>();
                    for (Location location : locationList) {
                        LocationDTO dto = mapper.map(location);
                        dtos.add(dto);
                    }

                    locationTable.getItems().setAll(dtos);
                    codeIdEditField.setText(selectedItem.getId().toString());
                    postCodeEditField.setText(selectedItem.getPostCode());
                    adressEditField.setText(selectedItem.getAdress());
                    placeEditField.setText(selectedItem.getPlace());
                    voivoshipEditField.setText(selectedItem.getPlace());
                    countyEditField.setText(selectedItem.getCounty());
                    commentsEditField.setText(selectedItem.getComments());
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //wyświetlanie danych po kliknięciu w wiersz tabeli
        rowClickEvent();
        try {
            codeDAO = new CodeDAO();
            showCodes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        locationTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        locationId.setCellValueFactory(new PropertyValueFactory<LocationDTO, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<LocationDTO, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<LocationDTO, String>("description"));
        codeId.setCellValueFactory(new PropertyValueFactory<LocationDTO, Long>("codeId"));


        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        postCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        voivoship.setCellValueFactory(new PropertyValueFactory<>("voivoship"));
        county.setCellValueFactory(new PropertyValueFactory<>("county"));
        comments.setCellValueFactory(new PropertyValueFactory<>("comments"));
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
        comments.setCellValueFactory(new PropertyValueFactory<Code, String>("comments"));

        codes = loadCodes();

        tableView.getItems().setAll(FXCollections.observableArrayList(codes));
    }
}