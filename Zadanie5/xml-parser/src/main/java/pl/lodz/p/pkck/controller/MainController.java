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
import java.util.ResourceBundle;

public class MainController extends Controller {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private static final String TMP1_XML_FILE_NAME = "tmp_1.xml";
    private static final String TMP2_XML_FILE_NAME = "tmp_2.xml";
    private static final String XML_XSLT_FILE_NAME = "bookshelf_raport.xslt";
    private static final String PDF_XSLT_FILE_NAME = "bookshelf_raport_pdf.xslt";

    //region FXML FIELDS
    @FXML
    private TextField headerDateTextField;

    @FXML
    private TextField BookISBNTextField1;

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
    private Button addBookButton;

    @FXML
    private Button editBookButton;

    @FXML
    private Button removeBookButton;

    @FXML
    private ListView bookListView;

    @FXML
    private ListView bookAuthorsListView;

    @FXML
    private TextField realiseDayBookTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField nameBookAuthorTextField;

    @FXML
    private TextField pageNumberBookTextField1;

    @FXML
    private TextField surnameBookAuthorTextField;

    @FXML
    private TextField BookTitleTextField;

    @FXML
    private Button addBookAuthorButton;

    @FXML
    private Button editBookAuthorButton;

    @FXML
    private Button removeBookAuthorButton;

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
    private ChoiceBox ageCatBookChoiceBox;

    @FXML
    private ChoiceBox bookAuthorChoiceBox;

    @FXML
    private ChoiceBox languageBookChoiceBox;

    @FXML
    private ChoiceBox bindingBookChoiceBox;

    @FXML
    private ChoiceBox dimTypeChoiceBox1;

    @FXML
    private ChoiceBox bookAuthorNationChoiceBox11;

    private Bookshelf document = new Bookshelf();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAuthorListView();
        initializeBookAuthorsListView();
        initializeBookListView();

        bookAuthorNationChoiceBox11.setItems(
                FXCollections.observableArrayList(Arrays.asList("pl", "us")));
        bindingBookChoiceBox.setItems(
                FXCollections.observableArrayList(Arrays.asList("miekka", "twarda")));
        ageCatBookChoiceBox.setItems(
                FXCollections.observableArrayList(Arrays.asList(5, 10 ,13, 16, 18)));
        languageBookChoiceBox.setItems(
                FXCollections.observableArrayList(Arrays.asList("eng", "pl")));
        dimTypeChoiceBox1.setItems(
                FXCollections.observableArrayList(Arrays.asList("cm", "mm")));
        bookAuthorNationChoiceBox11.setItems(
                FXCollections.observableArrayList(Arrays.asList("us", "pl")));

        loadXmlButton.setOnAction(event -> loadXml());
        saveXmlButton.setOnAction(event -> saveXml());
        savePdfButton.setOnAction(event -> savePdf());

        addAuthorButton.setOnAction(event -> addAuthor());
        editAuthorButton.setOnAction(event -> editAuthor());
        removeAuthorButton.setOnAction(event -> removeAuthor());
        addBookAuthorButton.setOnAction(event -> addBookAuthor());
        editBookAuthorButton.setOnAction(event -> editBookAuthor());
        removeBookAuthorButton.setOnAction(event -> removeBookAuthor());
        addBookButton.setOnAction(event -> addBook());
        editBookButton.setOnAction(event -> editBook());
        removeBookButton.setOnAction(event -> removeBook());
    }

    //region BOOKS

    private boolean ISBNexists() {
        for(Book book : document.getBooks()){
            if(book.getISBN().toString().equals( BookISBNTextField1.getText())){
                return true;
            }
        }
        return false;
    }
    private void removeBook() {
        Book selectedBook = document.getBooks().get(bookListView.getSelectionModel().getSelectedIndex());
        document.getBooks().remove(selectedBook);

        updateBookListView();
        clearBookData();
    }

    private boolean ifBookFieldsEmpty() {

        return
                BookTitleTextField.getText().isEmpty() ||
                        bookAuthorChoiceBox.getValue() == null ||
                        languageBookChoiceBox.getValue() == null ||
                        realiseDayBookTextField.getText().isEmpty() ||
                        pageNumberBookTextField1.getText().isEmpty() ||
                        bindingBookChoiceBox.getValue() == null ||
                        heightTextField.getText().isEmpty() ||
                        widthTextField.getText().isEmpty() ||
                        dimTypeChoiceBox1.getValue() == null ||
                        ageCatBookChoiceBox.getValue() == null ||
                        BookISBNTextField1.getText().isEmpty();
    }

    private void editBook() {
        try {
            Book selectedBook = document.getBooks().get(bookListView.getSelectionModel().getSelectedIndex());

            if (
                    ifBookFieldsEmpty()
            ) {
                showErrorAlert("Wystąpił błąd", "Wypełnij wszystkie pola by edytować książkę");
            } else {

                try {
                    if (BookISBNTextField1.getText().length() == 13) {
                        if(!ISBNexists() || selectedBook.getISBN().toString().equals(BookISBNTextField1.getText()) ){
                            selectedBook.setISBN(Long.parseLong(BookISBNTextField1.getText()));

                            selectedBook.setTitle(BookTitleTextField.getText());

                            selectedBook.getBookAuthor().setBookAuthorInfo((BookAuthorInfo) bookAuthorChoiceBox.getValue());

                            selectedBook.getLanguages().setIdLanguage((String) languageBookChoiceBox.getValue());

                            selectedBook.setRealiseDay(realiseDayBookTextField.getText());
                            selectedBook.setNumberOfPages(Long.parseLong(pageNumberBookTextField1.getText()));

                            selectedBook.getBinding().setBindingName((String) bindingBookChoiceBox.getValue());

                            selectedBook.getDimensions().setHeight(Integer.parseInt(heightTextField.getText()));
                            selectedBook.getDimensions().setWidth(Integer.parseInt(widthTextField.getText()));
                            selectedBook.getDimensions().setDimensionType((String) dimTypeChoiceBox1.getValue());

                            selectedBook.getAgeCategory().setMinAge(Integer.parseInt(ageCatBookChoiceBox.getValue().toString()));

                            updateBookListView();
                            clearBookData();
                        }
                        else {
                            showErrorAlert("Wystąpił błąd", "ISBN musi być wartością unikalną");

                        }

                    }
                    else
                    {
                        showErrorAlert("Wystąpił błąd", "ISBN musi mieć 13 cyfr");
                    }

                } catch (Exception e) {
                    showErrorAlert("Wystąpił błąd", e.getMessage());
                }
            }
        } catch (Exception e) {
            showErrorAlert("Wystąpił błąd", "Wybierz książkę do edycji");

        }


    }

    private void addBook() {
        if (ifBookFieldsEmpty()) {
            showErrorAlert("Wystąpił błąd", "Wypełnij wszystkie pola aby dodać książkę");
        } else {
            if (!ISBNexists() && (BookISBNTextField1.getText().length() == 13)) {
                Book newBook = new Book();
                newBook.setISBN(Long.parseLong(BookISBNTextField1.getText()));
                newBook.setTitle(BookTitleTextField.getText());

                newBook.setBookAuthor(new BookAuthor());
                newBook.getBookAuthor().setBookAuthorInfo((BookAuthorInfo) bookAuthorChoiceBox.getValue());

                newBook.setLanguages(new Language());
                newBook.getLanguages().setIdLanguage((String) languageBookChoiceBox.getValue());

                newBook.setRealiseDay(realiseDayBookTextField.getText());
                newBook.setNumberOfPages(Long.parseLong(pageNumberBookTextField1.getText()));

                newBook.setBinding(new Binding());
                newBook.getBinding().setBindingName((String) bindingBookChoiceBox.getValue());

                newBook.setDimensions(new Dimensions());
                newBook.getDimensions().setHeight(Integer.parseInt(heightTextField.getText()));
                newBook.getDimensions().setWidth(Integer.parseInt(widthTextField.getText()));
                newBook.getDimensions().setDimensionType((String) dimTypeChoiceBox1.getValue());

                newBook.setAgeCategory(new AgeCategory());
                newBook.getAgeCategory().setMinAge(Integer.parseInt(ageCatBookChoiceBox.getValue().toString()));

                document.getBooks().add(newBook);
                updateBookListView();
                clearBookData();
            } else {
                showErrorAlert("Błąd!", "ISBN musi być wartością unikalną i mieć długość 13.");
            }
        }

    }

    private void updateBookListView() {
        ObservableList<Book> booksObservableList = FXCollections.observableArrayList(document.getBooks());
        bookListView.setItems(booksObservableList);
        bookListView.refresh();
    }

    private void initializeBookListView() {
        bookListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                Book selectedBook = document.getBooks().get(newValue.intValue());
                BookISBNTextField1.setText(selectedBook.getISBN().toString());
                BookTitleTextField.setText(selectedBook.getTitle());
                bookAuthorChoiceBox.setValue(selectedBook.getBookAuthor().getBookAuthorInfo());
                languageBookChoiceBox.setValue(selectedBook.getLanguages().getIdLanguage());
                bindingBookChoiceBox.setValue(selectedBook.getBinding().getBindingName());
                pageNumberBookTextField1.setText(selectedBook.getNumberOfPages().toString());
                heightTextField.setText(selectedBook.getDimensions().getHeight().toString());
                widthTextField.setText(selectedBook.getDimensions().getWidth().toString());
                dimTypeChoiceBox1.setValue(selectedBook.getDimensions().getDimensionType());
                ageCatBookChoiceBox.setValue(selectedBook.getAgeCategory().getMinAge());
                realiseDayBookTextField.setText(selectedBook.getRealiseDay());

            } else {
                clearBookData();
            }
        });
    }

    private void clearBookData() {
        BookISBNTextField1.clear();
        BookTitleTextField.clear();
        bookAuthorChoiceBox.getSelectionModel().clearSelection();
        languageBookChoiceBox.getSelectionModel().clearSelection();
        bindingBookChoiceBox.getSelectionModel().clearSelection();
        pageNumberBookTextField1.clear();
        heightTextField.clear();
        widthTextField.clear();
        dimTypeChoiceBox1.getSelectionModel().clearSelection();
        ageCatBookChoiceBox.getSelectionModel().clearSelection();
        realiseDayBookTextField.clear();
        bookListView.getSelectionModel().clearSelection();
    }
    //endregion

    //region Book authors
    private void removeBookAuthor() {
        boolean canDelete = true;
        BookAuthorInfo selectedBookAuthor = document.getBookAuthorsList().get(bookAuthorsListView.getSelectionModel().getSelectedIndex());
        for(Book book : document.getBooks()){
            if(book.getBookAuthor().getBookAuthorInfo().getId() == selectedBookAuthor.getId()){
                canDelete = false;
                break;
            }
        }

        if(canDelete){
            document.getBookAuthorsList().remove(selectedBookAuthor);
            updateBookAuthorsListView();

            updateAuthorListView();
            clearBookAuthorsData();
        }
        else {
            showErrorAlert("Wystąpił błąd", "Nie mozna usunąć autora, ponieważ jest on przypisany do co najmniej 1 książki");
        }


    }

    private boolean ifBookAuthorFieldsEmpty() {
        return nameBookAuthorTextField.getText().isEmpty() ||
                surnameBookAuthorTextField.getText().isEmpty() ||
                bookAuthorNationChoiceBox11.getValue() == null;
    }

    private void editBookAuthor() {

        try {
            BookAuthorInfo selectedBookAuthor = document.getBookAuthorsList().get(bookAuthorsListView.getSelectionModel().getSelectedIndex());

            if (ifBookAuthorFieldsEmpty()) {
                showErrorAlert("Wystąpił błąd", "Wypełnij wszystkie pola by edytować autora książki");

            } else {
                int i = bookAuthorChoiceBox.getItems().indexOf(selectedBookAuthor);

                selectedBookAuthor.setName(nameBookAuthorTextField.getText());
                selectedBookAuthor.setSurname(surnameBookAuthorTextField.getText());
                selectedBookAuthor.getAuthorNation().setAuthorNation((String) bookAuthorNationChoiceBox11.getValue());

                bookAuthorChoiceBox.getItems().add(i, selectedBookAuthor);
                bookAuthorChoiceBox.setValue(selectedBookAuthor);

                updateBookAuthorsListView();
                updateAuthorListView();

                clearBookAuthorsData();
            }

        } catch (Exception e) {
            showErrorAlert("Wystąpił błąd", "Wybierz autora książki do edycji");
        }
    }

    private void addBookAuthor() {
        if (ifBookAuthorFieldsEmpty()) {
            showErrorAlert("Wystąpił błąd", "Wypełnij wszystkie pola aby dodać autora książki");
        } else {
            BookAuthorInfo newBookAuthorInfo = new BookAuthorInfo();
            Integer IDNumberLength  = document.getBookAuthorsList().get(document.getBookAuthorsList().size()-1).getId().toString().length();
            Integer newIDNumber = Integer.parseInt(document.getBookAuthorsList().get(document.getBookAuthorsList().size()-1).getId().substring(2,IDNumberLength)) +1;
            newBookAuthorInfo.setId("ID" + newIDNumber );
            newBookAuthorInfo.setName(nameBookAuthorTextField.getText());
            newBookAuthorInfo.setSurname(surnameBookAuthorTextField.getText());
            newBookAuthorInfo.setAuthorNation(new AuthorNation());
            newBookAuthorInfo.getAuthorNation().setAuthorNation((String) bookAuthorNationChoiceBox11.getValue());

            document.getBookAuthorsList().add(newBookAuthorInfo);
            updateBookAuthorsListView();
            clearBookAuthorsData();
        }
    }

    private void updateBookAuthorsListView() {
        ObservableList<BookAuthorInfo> bookAuthorsObservableList = FXCollections.observableArrayList(document.getBookAuthorsList());
        bookAuthorChoiceBox.setItems(bookAuthorsObservableList);
        bookAuthorsListView.setItems(bookAuthorsObservableList);
        bookAuthorsListView.refresh();
    }

    private void initializeBookAuthorsListView() {
        bookAuthorsListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {

                BookAuthorInfo selectedBookAuthor = document.getBookAuthorsList().get(newValue.intValue());
                nameBookAuthorTextField.setText(selectedBookAuthor.getName());
                surnameBookAuthorTextField.setText(selectedBookAuthor.getSurname());
                bookAuthorNationChoiceBox11.setValue(selectedBookAuthor.getAuthorNation().getAuthorNation());
            } else {
                clearBookAuthorsData();
            }
        });
    }

    private void clearBookAuthorsData() {
        nameBookAuthorTextField.clear();
        surnameBookAuthorTextField.clear();
        bookAuthorNationChoiceBox11.getSelectionModel().clearSelection();
        bookAuthorsListView.getSelectionModel().clearSelection();
    }
    //endregion

    //region AUTHOR
    private boolean ifAuthorFieldsEmpty() {
        return authorNameTextField.getText().isEmpty() ||
                authorSurnameTextField.getText().isEmpty() ||
                authorIndexNumberTextField.getText().isEmpty();
    }
    private void removeAuthor() {
        Author selectedAuthor = document.getInformation().getAuthors().get(authorListView.getSelectionModel().getSelectedIndex());
        document.getInformation().getAuthors().remove(selectedAuthor);
        updateAuthorListView();
        clearAuthorData();
    }

    private void editAuthor() {
        try {
            Author selectedAuthor = document.getInformation().getAuthors().get(authorListView.getSelectionModel().getSelectedIndex());
            if (ifAuthorFieldsEmpty()) {
                showErrorAlert("Wystąpił błąd", "Wypełnij wszystkie pola by edytować autora");
            } else {
                selectedAuthor.setName(authorNameTextField.getText());
                selectedAuthor.setSurname(authorSurnameTextField.getText());
                selectedAuthor.setIndexNumber(authorIndexNumberTextField.getText());
                updateAuthorListView();
                clearAuthorData();
            }
        } catch (Exception e) {
            showErrorAlert("Wystąpił błąd", "Wybierz autora do edycji");
        }
    }

    private void addAuthor() {
        if (ifAuthorFieldsEmpty()) {
            showErrorAlert("Wystąpił błąd", "Wypełnij wszystkie pola aby dodać autora");
        } else {
            Author newAuthor = new Author();
            newAuthor.setName(authorNameTextField.getText());
            newAuthor.setSurname(authorSurnameTextField.getText());
            newAuthor.setIndexNumber(authorIndexNumberTextField.getText());
            document.getInformation().getAuthors().add(newAuthor);
            updateAuthorListView();
            clearAuthorData();
        }
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
            clearBookAuthorsData();
            clearBookData();
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
            showAlert("informacja.", "Zapisano.");


        } catch (DaoException e) {
            log.error(e.getMessage(), e);
            showErrorAlert("Wystąpił błąd podczas zapisu do XML.", e.getMessage());
        }
    }
    //endregion

    private void updateDocumentWithGuiData() {
        try {
            document.getInformation().setGenerationTime(headerDateTextField.getText());
            //document.getHeader().setGenerationTime(ZonedDateTime.parse(headerDateTextField.getText()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            showErrorAlert("Wystąpił błąd podczas parsowania danych z UI.", e.getMessage());
        }
    }

    private void populateGuiWithDocumentData() {
        try {
            headerDateTextField.setText(document.getInformation().getGenerationTime().toString());
            updateAuthorListView();
            updateBookAuthorsListView();
            updateBookListView();
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

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(header);
        alert.setContentText(content);
        FxUtils.setStageIcon((Stage) alert.getDialogPane().getScene().getWindow());
        alert.showAndWait();
    }
}
