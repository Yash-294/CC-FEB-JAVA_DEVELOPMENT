module com.codeclausetask1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.codeclausetask1 to javafx.fxml;
    exports com.codeclausetask1;
}
