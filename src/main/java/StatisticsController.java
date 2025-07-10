import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.List;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class StatisticsController {
    @FXML private Label totalLabel;
    @FXML private Label ageGroupsLabel;
    @FXML private Label averageLabel;
    @FXML private BarChart<String, Number> ageBarChart;

    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
        updateStats();
    }

    private void updateStats() {
        if (students == null) return;
        totalLabel.setText("Nombre d'étudiants total : " + students.size());
        double avg = students.stream().mapToDouble(Student::getGrade).average().orElse(0.0);
        averageLabel.setText("Note moyenne : " + String.format("%.2f", avg));
        updateAgeChart();
    }

    private void updateAgeChart() {
        if (students == null || ageBarChart == null) return;
        ageBarChart.getData().clear();
        long less20 = students.stream().filter(s -> s.getAge() < 20).count();
        long age20 = students.stream().filter(s -> s.getAge() == 20).count();
        long age21 = students.stream().filter(s -> s.getAge() == 21).count();
        long age22 = students.stream().filter(s -> s.getAge() == 22).count();
        long more22 = students.stream().filter(s -> s.getAge() > 22).count();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("<20", less20));
        series.getData().add(new XYChart.Data<>("20", age20));
        series.getData().add(new XYChart.Data<>("21", age21));
        series.getData().add(new XYChart.Data<>("22", age22));
        series.getData().add(new XYChart.Data<>(">22", more22));
        ageBarChart.getData().add(series);
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) totalLabel.getScene().getWindow();
        stage.close();
    }
}
