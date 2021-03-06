package controller;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.pixelduke.javafx.chart.DateAxis;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import model.*;

import java.io.File;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class HistoryGraphInputController extends Controller {

    @FXML
    private Slider radiusSlider;

    @FXML
    private Spinner radiusSpinner;

    @FXML
    private TextField locationTextField;

    @FXML
    private DatePicker startDateField;

    @FXML
    private DatePicker endDateField;

    @FXML
    private RadioButton contaminantRadio;

    @FXML
    private RadioButton virusRadio;

    public HistoryGraphInputController() {
    }

    /**
     * Initializes the history graph
     */

    @FXML
    public void initialize() {
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 15000, 1, 100);
        radiusSpinner.setValueFactory(valueFactory);
        radiusSpinner.setEditable(true);
        radiusSpinner.valueProperty().addListener(e -> radiusSlider.setValue((int) radiusSpinner.getValue()));
        radiusSlider.valueProperty().addListener(e -> radiusSpinner.getValueFactory().setValue((int) radiusSlider.getValue()));

        startDateField.setValue(LocalDate.now().minusYears(1));
        endDateField.setValue(LocalDate.now());
    }

    /**
     * Returns to the PostLogin screen when Back is pressed
     */
    @FXML
    private void handleBackPressed() {
        mainApp.showScreen(new File("../view/PostLogin.fxml"));
    }

    /**
     * Calls doGeoCode
     */
    @FXML
    protected void handleGeocodePressed() {
        doGeoCode(false);
    }

    /**
     * Handles what to do when view history is pressed
     */
    @FXML
    protected void handleViewHistoryPressed() {
        if (locationTextField.getText().length() > 0) {
            String locationStr = locationTextField.getText();
            ReportLocation location;
            int radius = (int) radiusSlider.getValue();

            Date startDate = new GregorianCalendar(
                    startDateField.getValue().getYear(),
                    startDateField.getValue().getMonth().getValue() - 1,
                    startDateField.getValue().getDayOfMonth(),
                    0, 0, 0).getTime();

            Date endDate = new GregorianCalendar(
                    endDateField.getValue().getYear(),
                    endDateField.getValue().getMonth().getValue() - 1,
                    endDateField.getValue().getDayOfMonth(),
                    23, 59, 59).getTime();


            if (startDate.after(endDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Submission Error");
                alert.setHeaderText("Invalid Dates");
                alert.setContentText("The start date cannot be after the end date.");
                alert.showAndWait();
                return;
            }

            try {
                String[] latLong = locationStr.split(",");
                double latitude = Double.parseDouble(latLong[0]);
                double longitude = Double.parseDouble(latLong[1]);
                location = new ReportLocation(latitude, longitude);
            } catch (Exception ex) {
                // Geocode and wait
                doGeoCode(true);
                return;
            }



            HashMap<String, Integer> numReports = new HashMap<>();
            HashMap<String, Integer> ppmTotal = new HashMap<>();
            WaterPurityReport[] reports = getReportsForQuery(startDate, endDate, location, radius);


            for (WaterPurityReport report : reports) {
                String monthStr = new SimpleDateFormat("YYYY/MM").format(report.getCreated());

                int ppm;

                if (contaminantRadio.isSelected()) {
                    // Contaminant Report
                    ppm = report.getContaminantPPM();

                } else {
                    // Virus Report
                    ppm = report.getVirusPPM();
                }

                if (!numReports.containsKey(monthStr)) {
                    numReports.put(monthStr, 1);
                    ppmTotal.put(monthStr, ppm);
                } else {
                    numReports.put(monthStr, numReports.get(monthStr) + 1);
                    ppmTotal.put(monthStr, ppmTotal.get(monthStr) + ppm);
                }
            }

            final DateAxis xAxis = new DateAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Month");

            XYChart.Series<Long, Number> series = new XYChart.Series<>();

            Calendar startDateCalendar = new GregorianCalendar();
            startDateCalendar.setTime(startDate);

            Calendar endDateCalendar = new GregorianCalendar();
            endDateCalendar.setTime(endDate);

            int year = startDateCalendar.get(Calendar.YEAR);
            int month = startDateCalendar.get(Calendar.MONTH);
            int i = 1;
            while (year < endDateCalendar.get(Calendar.YEAR) || (year <= endDateCalendar.get(Calendar.YEAR) && month <= endDateCalendar.get(Calendar.MONTH))) {
                String monthStr = month > 8 ? Integer.toString(month + 1) : "0" + Integer.toString(month + 1);
                String dateStr = Integer.toString(year) + '/' + monthStr;

                if (numReports.containsKey(dateStr)) {
                    double averagePPM = (double) ppmTotal.get(dateStr) / numReports.get(dateStr);
                    series.getData().add(new XYChart.Data<>(new GregorianCalendar(year, month, 0).getTime().getTime(), averagePPM));
                }

                i++;
                month++;
                if (month > 11) {
                    year++;
                    month = 0;
                }
            }

            // Create the chart
            final LineChart<Long, Number> lineChart =
                    new LineChart<>(xAxis, yAxis);
            lineChart.getData().add(series);


            if (contaminantRadio.isSelected()) {
                lineChart.setTitle("Contaminant PPM");
                series.setName("Contaminant PPM");

            } else {
                lineChart.setTitle("Virus PPM");
                series.setName("Virus PPM");
            }

            HistoryGraphDisplayController newController = mainApp.showHistoryDisplay();

            newController.setChart(lineChart);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Report Submission Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Enter all required information.");
            alert.showAndWait();
        }
    }

    public WaterPurityReport[] getReportsForQuery(Date startDate, Date endDate, ReportLocation location, int radius) {
        Collection<WaterReport> allReports = WaterReportsHolder.getReportsList().values();
        List<WaterPurityReport> selectedReports = new LinkedList<>();

        for (WaterReport report : allReports) {
            if (!(report instanceof WaterPurityReport)) {
                continue;
            }

            WaterPurityReport purityReport = (WaterPurityReport) (report);

            if (purityReport.getCreated().before(startDate) || purityReport.getCreated().after(endDate)) {
                continue;
            }

            if (purityReport.getLocation().distFrom(location) > radius) {
                continue;
            }

            selectedReports.add((WaterPurityReport) report);
        }

        return selectedReports.toArray(new WaterPurityReport[selectedReports.size()]);
    }

    /**
     * Gets the geocode
     *
     * @param submitOnCompletion takes in the boolean if ready for submission
     */
    private void doGeoCode(boolean submitOnCompletion) {
        String location = locationTextField.getText();
        new GeocodingService().geocode(location, (geocodingResults, geocoderStatus) -> {

            if (geocodingResults.length == 0) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Geocode Error");
                    alert.setHeaderText("Address Error");
                    alert.setContentText("Please enter a valid address or latitude, longitude.");
                    alert.showAndWait();
                });
            }

            GeocodingResult result = geocodingResults[0];

            LatLong location1 = result.getGeometry().getLocation();

            locationTextField.setText(location1.getLatitude() + ", " + location1.getLongitude());

            if (submitOnCompletion) {
                handleViewHistoryPressed();
            }
        });
    }
}
