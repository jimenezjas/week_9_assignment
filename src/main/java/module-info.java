module com.example.week_9_assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.week_9_assignment to javafx.fxml;
    exports com.example.week_9_assignment;
}