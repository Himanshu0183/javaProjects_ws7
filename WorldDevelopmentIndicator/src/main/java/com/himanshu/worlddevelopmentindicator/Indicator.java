/**********************************************
 Workshop 7
 Course: JAC 444 - Summer 2022
 Last Name: Himanshu
 First Name: Himanshu
 ID: 146109202
 Section: ZBB
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature  Himanshu
 Date: 07/23/2022
 **********************************************/

package com.himanshu.worlddevelopmentindicator;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Indicator extends Application {
    private Country[] records;
    private TableView table;
    private TextField yearTxt;
    private TextField countryTxt;
    private ComboBox<String> indicator_combo_box;
    private ComboBox<String> countries_combo_box;
    private ComboBox<String> years_combo_box;

    private Button getAll;
    private Button getRecord;
    private Button getCountryRecord;
    private Button getAllIndicatorRecord;
    private Button reset;
    private Label display;

    private void addYears() {
        years_combo_box = new ComboBox<>();
        years_combo_box.getItems().add("Select year");
        for (int i = 2011; i <= 2021; i++) {
            years_combo_box.getItems().add(String.valueOf(i));
        }
        years_combo_box.getSelectionModel().selectFirst();
    }

    public void addContries() {
        countries_combo_box = new ComboBox<String>();
        countries_combo_box.getItems().add("Select country");
        for (Country emp : records) {

            ObservableList<String> items = countries_combo_box.getItems();

            if (!items.contains(emp.getName())) {
                countries_combo_box.getItems().add(emp.getName());
            }

        }
        countries_combo_box.getSelectionModel().selectFirst();
    }

    public void addIndicators() {
        indicator_combo_box = new ComboBox<String>();
        indicator_combo_box.getItems().add("Select indicator");
        for (Country emp : records) {

            ObservableList<String> items = indicator_combo_box.getItems();

            if (!items.contains(emp.getSeriesName())) {
                indicator_combo_box.getItems().add(emp.getSeriesName());
            }

        }
        indicator_combo_box.getSelectionModel().selectFirst();
    }

    @Override
    public void start(Stage stage) {

        Data data = new Data();
        data.setFlag(true);
        data.ReadData("WDI.csv");
        records = data.getRecords();

        addIndicators();
        addContries();
        addYears();

        Label label_w = new Label("Enter or Select Option");
        Label yearLbl = new Label("Year:");
        Label countryLbl = new Label("Country:");
        Label indicatorLbl = new Label("Indicator:");
        yearTxt = new TextField();
        countryTxt = new TextField();


        GridPane input = new GridPane();
        input.addRow(0, new Label(""), label_w, new Label("(if year or country not selected, the Enter values in text field are used else the selected option)"));
        input.addRow(1, yearLbl, yearTxt, years_combo_box);
        input.addRow(2, countryLbl, countryTxt, countries_combo_box);
        input.addRow(3, new Label(""), indicatorLbl, indicator_combo_box);
        input.setHgap(10);
        input.setVgap(10);
        input.setPadding(new Insets(20, 20, 20, 20));
        input.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));


        getRecord = new Button("Get specific Record");
        getAll = new Button("View all records");
        getCountryRecord = new Button("filter by country");
        getAllIndicatorRecord = new Button("Filter by indicator");
        reset = new Button("Reset");
        display = new Label("Not recorded");

        table = new TableView();
        TableColumn<String, Country> cl1 = new TableColumn<>("Country Name");
        cl1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Integer, Country> cl2 = new TableColumn<>("Series Name");
        cl2.setCellValueFactory(new PropertyValueFactory<>("seriesName"));

        TableColumn<Integer, Country> cl3 = new TableColumn<>("Years data (2011-2021)");
        cl3.setCellValueFactory(new PropertyValueFactory<>("m_years_value"));

        // Add two columns into TableView
        table.getColumns().add(cl1);
        table.getColumns().add(cl2);
        table.getColumns().add(cl3);
        for (Country emp : records) {
            table.getItems().add(emp);
        }

        table.setVisible(false);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(getRecord,  getCountryRecord, getAllIndicatorRecord, getAll, reset);
        hBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(30);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14);

        getAll.setFont(font);
        getCountryRecord.setFont(font);
        getAllIndicatorRecord.setFont(font);
        getRecord.setFont(font);
        reset.setFont(font);
        display.setFont(font);
        display.setPadding(new Insets(10, 10, 10, 10));

        attachEventListeners();
        VBox box = new VBox();
        box.getChildren().addAll(input, hBox, display, table);
        Scene scene = new Scene(box, 850, 700);
        stage.setTitle("World Development Indicators System");
        stage.setScene(scene);
        stage.show();
    }

    public boolean validateYear(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private void attachEventListeners() {
        getRecord.setOnAction(event -> {
            display.setText("");
            table.setVisible(false);
            String year, country, indicator;
            int yearIndex;
            if (yearTxt.getText().trim().isEmpty()) {
                year = years_combo_box.getValue();
            } else {
                year = yearTxt.getText();
            }
            if (countryTxt.getText().trim().isEmpty()) {
                country = countries_combo_box.getValue();
            } else {
                country = countryTxt.getText();
            }
            indicator = indicator_combo_box.getValue();
            if (validateYear(year)) {
                int yr = Integer.parseInt(year);
                if (yr >= 2011 && yr <= 2021) {
                    yearIndex = yr - 2011;
                    for (Country emp : records) {
                        if (emp.getName().equals(country) && emp.getSeriesName().equals(indicator)) {
                            String results = "Country:" + country
                                    + "\nIndicator: " + indicator
                                    + "\nYear: " + year
                                    + "\nValue: " + ((emp.getYearByIndex(yearIndex) == 0) ? "NO Values recorded (0.0)" : emp.getYearByIndex(yearIndex));
                            display.setText(results);
                            break;
                        }
                    }
                } else {
                    display.setText("NO records found\n invalid Year( " + year + ") entered \naccepted range: 2011 to 2021 ");
                }

            } else {
                display.setText("NO records found\n invalid Year( " + year + ") entered");
            }
        });

        getAll.setOnAction(event -> {
            display.setText("");
            table.setVisible(true);
            table.getItems().clear();

            // Load objects into table
            for (Country emp : records) {
                table.getItems().add(emp);
            }
            if (table.getItems().size() == 0) {
                display.setText("NO records found");
            }
        });

        getCountryRecord.setOnAction(event -> {
            display.setText("");
            table.setVisible(true);
            table.getItems().clear();

            // Load objects into table
            for (Country emp : records) {
                if (emp.getName().equals(countries_combo_box.getValue())) {
                    table.getItems().add(emp);
                }
            }
            if (table.getItems().size() == 0) {
                display.setText("NO records found, Select a valid country");
            }
        });

        getAllIndicatorRecord.setOnAction(event -> {
            table.setVisible(true);
            display.setText("");
            table.getItems().clear();

            // Load objects into table
            for (Country emp : records) {
                if (emp.getSeriesName().equals(indicator_combo_box.getValue())) {
                    table.getItems().add(emp);
                }
            }

            if (table.getItems().size() == 0) {
                display.setText("NO records found, Select a valid indicator");
            }
        });

        //reset
        reset.setOnAction(event -> {
            yearTxt.setText("");
            countryTxt.setText("");
            indicator_combo_box.getSelectionModel().selectFirst();
            countries_combo_box.getSelectionModel().selectFirst();
            years_combo_box.getSelectionModel().selectFirst();
            display.setText("");
            table.setVisible(false);
            table.getItems().clear();
        });
    }


    public static void main(String[] args) {
        launch();
    }
}