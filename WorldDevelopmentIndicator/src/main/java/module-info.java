module com.himanshu.worlddevelopmentindicator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.himanshu.worlddevelopmentindicator to javafx.fxml;
    exports com.himanshu.worlddevelopmentindicator;
}