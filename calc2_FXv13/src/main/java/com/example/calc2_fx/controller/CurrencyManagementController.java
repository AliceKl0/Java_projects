package com.example.calc2_fx.controller;

import com.example.calc2_fx.model.CurrencyRate;
import com.example.calc2_fx.repository.CurrencyRateRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CurrencyManagementController {
    @FXML private TableView<CurrencyRate> ratesTable;
    @FXML private TableColumn<CurrencyRate, String> fromColumn;
    @FXML private TableColumn<CurrencyRate, String> toColumn;
    @FXML private TableColumn<CurrencyRate, BigDecimal> rateColumn;
    @FXML private ComboBox<String> fromCurrency;
    @FXML private ComboBox<String> toCurrency;
    @FXML private TextField rateField;

    private CurrencyRateRepository repository = new CurrencyRateRepository();

    @FXML
    public void initialize() {
        configureTable();
        loadCurrenciesFromDatabase();
        refreshData();
        setupCellFactories();
    }

    private void setupCellFactories() {
        // Настройка редактирования для колонки с курсом
        rateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        rateColumn.setOnEditCommit(event -> {
            CurrencyRate rate = event.getRowValue();
            rate.setRate(event.getNewValue());
            repository.saveOrUpdate(rate);
            refreshData();
        });
    }

    private void setupRateColumnEditing() {
        rateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        rateColumn.setOnEditCommit(event -> {
            CurrencyRate rate = event.getRowValue();
            rate.setRate(event.getNewValue());
            repository.saveOrUpdate(rate);
        });
    }

    private void loadCurrenciesFromDatabase() {
        List<CurrencyRate> rates = repository.findAll();
        Set<String> currencies = new LinkedHashSet<>();

        rates.forEach(rate -> {
            currencies.add(rate.getFromCurrency());
            currencies.add(rate.getToCurrency());
        });

        fromCurrency.getItems().setAll(currencies);
        toCurrency.getItems().setAll(currencies);
    }

    @FXML
    private void handleAddOrUpdate() {
        String from = fromCurrency.getValue();
        String to = toCurrency.getValue();
        BigDecimal rate = new BigDecimal(rateField.getText());

        repository.saveOrUpdate(new CurrencyRate(from, to, rate));
        refreshData();
        loadCurrenciesFromDatabase(); // Обновляем список валют
    }

    @FXML
    private void handleRateUpdate(TableColumn.CellEditEvent<CurrencyRate, BigDecimal> event) {
        CurrencyRate selected = event.getRowValue();
        selected.setRate(event.getNewValue());
        repository.saveOrUpdate(selected);
    }

    private void configureTable() {
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("fromCurrency"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("toCurrency"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
    }

    private void loadCurrencies() {
        List<String> currencies = Arrays.asList("USD", "EUR", "RUB");
        fromCurrency.getItems().addAll(currencies);
        toCurrency.getItems().addAll(currencies);
    }

    private void refreshData() {
        ratesTable.getItems().setAll(repository.findAll());
    }

    @FXML
    private void handleAdd() {
        CurrencyRate rate = new CurrencyRate(
                fromCurrency.getValue(),
                toCurrency.getValue(),
                new BigDecimal(rateField.getText())
        );
        repository.saveOrUpdate(rate);
        refreshData();
    }

    @FXML
    private void handleDelete() {
        CurrencyRate selected = ratesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            repository.delete(selected.getFromCurrency(), selected.getToCurrency());
            refreshData();
        }
    }

    @FXML
    private void handleBackToMain(ActionEvent event) {
        System.out.println("Handle back to main called"); // Для отладки
        NavigationController.navigateToMain(
                (Stage) ((Button) event.getSource()).getScene().getWindow()
        );
    }
}
