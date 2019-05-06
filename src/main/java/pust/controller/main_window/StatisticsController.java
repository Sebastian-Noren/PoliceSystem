package pust.controller.main_window;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    private final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();


    @FXML
    private LineChart<Integer, Integer> linechart;
    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pieChartInfo();


    }

    public void pieChartInfo() {

        //pieChart
        details.addAll(new PieChart.Data("Reporting a crime", 5),
                new PieChart.Data("Applying for passport ", 24),
                new PieChart.Data("Reporting lost entity", 15),
                new PieChart.Data("Reporting lost person", 7),
                new PieChart.Data("Report arrest", 17)
        );

        pieChart.setData(details);
        pieChart.setTitle("Police station errands");
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(true);

    }


}
