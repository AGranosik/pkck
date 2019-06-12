package pl.lodz.p.pkck.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.converter.XsltConverter;
import pl.lodz.p.pkck.dao.DocumentDao;
import pl.lodz.p.pkck.exception.DaoException;
import pl.lodz.p.pkck.exception.PdfConversionException;
import pl.lodz.p.pkck.exception.XmlConversionException;
import pl.lodz.p.pkck.model.*;
import pl.lodz.p.pkck.utils.FxUtils;

import java.io.File;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends Controller {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private static final String TMP1_XML_FILE_NAME = "tmp_1.xml";
    private static final String TMP2_XML_FILE_NAME = "tmp_2.xml";
    private static final String XML_XSLT_FILE_NAME = "lista_dysków_raport.xslt";
    private static final String PDF_XSLT_FILE_NAME = "lista_dysków_raport_pdf.xslt";

    //region FXML FIELDS
    @FXML
    private TextField headerDateTextField;

    @FXML
    private ListView authorListView;

    @FXML
    private TextField authorNameTextField;

    @FXML
    private TextField authorSurnameTextField;

    @FXML
    private TextField authorIndexNumberTextField;

    @FXML
    private Button addAuthorButton;

    @FXML
    private Button editAuthorButton;

    @FXML
    private Button removeAuthorButton;

    @FXML
    private ListView driveListView;

    @FXML
    private TextField driveBrandTextField;

    @FXML
    private Button addDriveButton;

    @FXML
    private Button editDriveButton;

    @FXML
    private Button removeDriveButton;

    @FXML
    private ListView shopListView;

    @FXML
    private TextField shopNameTextField;

    @FXML
    private Button addShopButton;

    @FXML
    private Button editShopButton;

    @FXML
    private Button removeShopButton;

    @FXML
    private TextField inputXmlFileNameTextField;

    @FXML
    private TextField inputXsdFileNameTextField;

    @FXML
    private TextField outputXmlFileNameTextField;

    @FXML
    private Button loadXmlButton;

    @FXML
    private Button saveXmlButton;

    @FXML
    private TextField outputTransPdfFileNameTextField;

    @FXML
    private Button savePdfButton;

    @FXML
    private TextField driveModelTextField;

    @FXML
    private TextField driveCapacityTextField;

    @FXML
    private TextField drivePriceTextField;

    @FXML
    private ChoiceBox driveRatingChoiceBox;

    @FXML
    private ChoiceBox driveTypeChoiceBox;

    @FXML
    private ChoiceBox driveShopChoiceBox;

    @FXML
    private TextField shopUriTextField;
    //endregion

    private Bookshelf document = new Bookshelf();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAuthorListView();
//        initializeDriveListView();
//        initializeShopListView();

        driveTypeChoiceBox.setItems(
                FXCollections.observableArrayList(Arrays.asList("HDD", "SSD")));
        driveRatingChoiceBox.setItems(
                FXCollections.observableArrayList(Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0)));

        loadXmlButton.setOnAction(event -> loadXml());
        saveXmlButton.setOnAction(event -> saveXml());
        savePdfButton.setOnAction(event -> savePdf());

        addAuthorButton.setOnAction(event -> addAuthor());
        editAuthorButton.setOnAction(event -> editAuthor());
        removeAuthorButton.setOnAction(event -> removeAuthor());
        addDriveButton.setOnAction(event -> addDrive());
        editDriveButton.setOnAction(event -> editDrive());
        removeDriveButton.setOnAction(event -> removeDrive());
        addShopButton.setOnAction(event -> addShop());
        editShopButton.setOnAction(event -> editShop());
        removeShopButton.setOnAction(event -> removeShop());
    }

    //region SHOP
    private void removeShop() {
//        Shop selectedShop = document.getBody().getShops().get(shopListView.getSelectionModel().getSelectedIndex());
//        document.getBody().getShops().remove(selectedShop);
//        updateShopListView();
//        clearShopData();
    }

    private void editShop() {
//        Shop selectedShop = document.getBody().getShops().get(shopListView.getSelectionModel().getSelectedIndex());
//        selectedShop.setName(shopNameTextField.getText());
//        selectedShop.setUri(shopUriTextField.getText());
//        updateShopListView();
//        clearShopData();
    }

    private void addShop() {
//        Shop newShop = new Shop();
//        List<Shop> shopList = document.getBody().getShops();
//        newShop.setId("s" + String.format("%03d", Integer.parseInt(shopList.get(shopList.size() - 1).getId().substring(1)) + 1));
//        newShop.setName(shopNameTextField.getText());
//        newShop.setUri(shopUriTextField.getText());
//        document.getBody().getShops().add(newShop);
//        updateShopListView();
//        clearShopData();
    }

    private void updateShopListView() {
//        ObservableList<Shop> shopsObservableList = FXCollections.observableArrayList(document.getBody().getShops());
//        shopListView.setItems(shopsObservableList);
//        shopListView.refresh();
        //driveShopChoiceBox.setItems(shopsObservableList);
        ObservableList<Book> shopsObservableList = FXCollections.observableArrayList(document.getBooks());
        shopListView.setItems(shopsObservableList);
        shopListView.refresh();
        driveShopChoiceBox.setItems(shopsObservableList);
    }

    private void initializeShopListView() {
//        shopListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.intValue() >= 0) {
//
//                Shop selectedShop = document.getBody().getShops().get(newValue.intValue());
//                shopNameTextField.setText(selectedShop.getName());
//                shopUriTextField.setText(selectedShop.getUri());
//            } else {
//                clearShopData();
//            }
//        });
    }

    private void clearShopData() {
        shopNameTextField.clear();
        shopUriTextField.clear();

        shopListView.getSelectionModel().clearSelection();
    }
    //endregion

    //region DRIVE
    private void removeDrive() {
//        Drive selectedDrive = document.getBody().getDrives().get(driveListView.getSelectionModel().getSelectedIndex());
//        document.getBody().getDrives().remove(selectedDrive);
//        updateDriveListView();
//        clearDriveData();
    }

    private void editDrive() {
//        Drive selectedDrive = document.getBody().getDrives().get(driveListView.getSelectionModel().getSelectedIndex());
//        selectedDrive.setShop((Shop) driveShopChoiceBox.getValue());
//        selectedDrive.setType((String) driveTypeChoiceBox.getValue());
//        selectedDrive.setBrand(driveBrandTextField.getText());
//        selectedDrive.setModel(driveModelTextField.getText());
//        selectedDrive.setCapacity(new Capacity(driveCapacityTextField.getText()));
//        selectedDrive.setPrice(new Price(drivePriceTextField.getText()));
//        selectedDrive.setRating((double) driveRatingChoiceBox.getValue());
//        updateDriveListView();
//        clearDriveData();
    }

    private void addDrive() {
//        Drive newDrive = new Drive();
//        List<Drive> driveList = document.getBody().getDrives();
//        newDrive.setId("d" + String.format("%03d", Integer.parseInt(driveList.get(driveList.size() - 1).getId().substring(1)) + 1));
//        newDrive.setShop((Shop) driveShopChoiceBox.getValue());
//        newDrive.setType((String) driveTypeChoiceBox.getValue());
//        newDrive.setBrand(driveBrandTextField.getText());
//        newDrive.setModel(driveModelTextField.getText());
//        newDrive.setCapacity(new Capacity(driveCapacityTextField.getText()));
//        newDrive.setPrice(new Price(drivePriceTextField.getText()));
//        newDrive.setRating((double) driveRatingChoiceBox.getValue());
//        document.getBody().getDrives().add(newDrive);
//        updateDriveListView();
//        clearDriveData();
    }

    private void updateDriveListView() {
        driveListView.setItems(FXCollections.observableArrayList(document.getBookAuthorsList()));
        driveListView.refresh();
    }

    private void initializeDriveListView() {
//        driveListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.intValue() >= 0) {
//                Drive selectedDrive = document.getBody().getDrives().get(newValue.intValue());
//                driveShopChoiceBox.setValue(selectedDrive.getShop());
//                driveTypeChoiceBox.setValue(selectedDrive.getType());
//                driveBrandTextField.setText(selectedDrive.getBrand());
//                driveModelTextField.setText(selectedDrive.getModel());
//                driveCapacityTextField.setText(selectedDrive.getCapacity().toString());
//                drivePriceTextField.setText(selectedDrive.getPrice().toString());
//                driveRatingChoiceBox.setValue(selectedDrive.getRating());
//            } else {
//                clearDriveData();
//
//            }
//        });
    }

    private void clearDriveData() {
        driveShopChoiceBox.getSelectionModel().clearSelection();
        driveTypeChoiceBox.getSelectionModel().clearSelection();
        driveBrandTextField.clear();
        driveModelTextField.clear();
        driveCapacityTextField.clear();
        drivePriceTextField.clear();
        driveRatingChoiceBox.getSelectionModel().clearSelection();

        driveListView.getSelectionModel().clearSelection();
    }
    //endregion

    //region AUTHOR
    private void removeAuthor() {
//        Author selectedAuthor = document.getHeader().getAuthors().get(authorListView.getSelectionModel().getSelectedIndex());
//        document.getHeader().getAuthors().remove(selectedAuthor);
//        updateAuthorListView();
//        clearAuthorData();
    }

    private void editAuthor() {
//        Author selectedAuthor = document.getHeader().getAuthors().get(authorListView.getSelectionModel().getSelectedIndex());
//        selectedAuthor.setName(authorNameTextField.getText());
//        selectedAuthor.setSurname(authorSurnameTextField.getText());
//        selectedAuthor.setIndexNumber(authorIndexNumberTextField.getText());
//        updateAuthorListView();
//        clearAuthorData();
    }

    private void addAuthor() {
//        Author newAuthor = new Author();
//        newAuthor.setName(authorNameTextField.getText());
//        newAuthor.setSurname(authorSurnameTextField.getText());
//        newAuthor.setIndexNumber(authorIndexNumberTextField.getText());
//        document.getHeader().getAuthors().add(newAuthor);
//        updateAuthorListView();
//        clearAuthorData();
    }

    private void updateAuthorListView() {
        authorListView.setItems(FXCollections.observableArrayList(document.getInformation().getAuthors()));
        authorListView.refresh();
    }

    private void initializeAuthorListView() {
        authorListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                Author selectedAuthor = document.getInformation().getAuthors().get(newValue.intValue());
                authorNameTextField.setText(selectedAuthor.getName());
                authorSurnameTextField.setText(selectedAuthor.getSurname());
                authorIndexNumberTextField.setText(selectedAuthor.getIndexNumber());
            } else {
                clearAuthorData();
            }
        });
    }

    private void clearAuthorData() {
        authorNameTextField.clear();
        authorSurnameTextField.clear();
        authorIndexNumberTextField.clear();

        authorListView.getSelectionModel().clearSelection();
    }
    //endregion

    //region LOAD & SAVE XML
    private void loadXml() {
        String inputXsdFileName = inputXsdFileNameTextField.getText();
        DocumentDao dao = new DocumentDao(inputXsdFileName);
        String inputXmlFileName = inputXmlFileNameTextField.getText();
        try {
            document = dao.read(inputXmlFileName);
            populateGuiWithDocumentData();
            clearAuthorData();
            clearDriveData();
            clearShopData();
        } catch (DaoException e) {
            log.error(e.getMessage(), e);
            showErrorAlert("Wystąpił błąd podczas wczytywania z XML.", e.getMessage());
        }
    }

    private void saveXml() {
        String inputXsdFileName = inputXsdFileNameTextField.getText();
        DocumentDao dao = new DocumentDao(inputXsdFileName);
        String outputXmlFileName = outputXmlFileNameTextField.getText();
        try {
            updateDocumentWithGuiData();
            dao.write(document, outputXmlFileName);
        } catch (DaoException e) {
            log.error(e.getMessage(), e);
            showErrorAlert("Wystąpił błąd podczas zapisu do XML.", e.getMessage());
        }
    }
    //endregion

    private void updateDocumentWithGuiData() {
//        try {
//            document.getHeader().setGenerationTime(ZonedDateTime.parse(headerDateTextField.getText()));
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            showErrorAlert("Wystąpił błąd podczas parsowania danych z UI.", e.getMessage());
//        }
    }

    private void populateGuiWithDocumentData() {
        try {
            //headerDateTextField.setText(document.getHeader().getGenerationTime().toString());
            updateAuthorListView();
            updateDriveListView();
            updateShopListView();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            showErrorAlert("Wystąpił błąd podczas parsowania danych z XML.", e.getMessage());
        }
    }

    private void savePdf() {
        String inputXsdFileName = inputXsdFileNameTextField.getText();
        String outputPdfFilePath = outputTransPdfFileNameTextField.getText();
        DocumentDao dao = new DocumentDao(inputXsdFileName);
        XsltConverter converter = new XsltConverter();

        try {
            updateDocumentWithGuiData();
            dao.write(document, TMP1_XML_FILE_NAME);
            converter.convertXmlToXml(TMP1_XML_FILE_NAME, XML_XSLT_FILE_NAME, TMP2_XML_FILE_NAME);
            converter.convertXmlToPdf(TMP2_XML_FILE_NAME, PDF_XSLT_FILE_NAME, outputPdfFilePath);
        } catch (DaoException e) {
            log.error(e.getMessage(), e);
            showErrorAlert("Wystąpił błąd podczas zapisu do XML.", e.getMessage());
        } catch (XmlConversionException e) {
            log.error(e.getMessage(), e);
            showErrorAlert("Wystąpił błąd podczas konwersji z XML do XML.", e.getMessage());
        } catch (PdfConversionException e) {
            log.error(e.getMessage(), e);
            showErrorAlert("Wystąpił błąd podczas konwersji z XML do PDF.", e.getMessage());
        } finally {
            File tmp1 = new File("xml/" + TMP1_XML_FILE_NAME);
            File tmp2 = new File("xml/" + TMP2_XML_FILE_NAME);
            tmp1.delete();
            tmp2.delete();
        }
    }

    private void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd!");
        alert.setHeaderText(header);
        alert.setContentText(content);
        FxUtils.setStageIcon((Stage) alert.getDialogPane().getScene().getWindow());
        alert.showAndWait();
    }
}
