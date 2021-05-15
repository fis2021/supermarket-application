package org.fis.student.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.student.services.OrderService;
import org.fis.student.services.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.fis.student.services.FileSystemService;
import org.fis.student.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class RegistrationTest {

    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String EMAIL = "email";

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testRegister";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        OrderService.initDatabase();
        ProductService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        OrderService.getDatabase().close();
        ProductService.getDatabase().close();
        UserService.getDatabase().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    @Test
    void testRegistration(FxRobot robot) {
        robot.clickOn("#backRegisterButton");
        robot.clickOn("#registerLoginButton");

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please type an Username!");
        robot.clickOn("#username");
        robot.write(USERNAME);

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please type a Password!");
        robot.clickOn("#password");
        robot.write(PASSWORD);

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please select a Role!");
        robot.clickOn("#role");
        robot.clickOn("Administrator");

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please type a Name!");
        robot.clickOn("#name");
        robot.write(NAME);

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please type an Address!");
        robot.clickOn("#address");
        robot.write(ADDRESS);

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please type an Email!");
        robot.clickOn("#email");
        robot.write(EMAIL);
        robot.clickOn("#registerButton");
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#registerLoginButton");
        robot.clickOn("#username");
        robot.write(USERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.clickOn("Administrator");
        robot.clickOn("#name");
        robot.write(NAME);
        robot.clickOn("#address");
        robot.write(ADDRESS);
        robot.clickOn("#email");
        robot.write(EMAIL);
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(
                String.format("An account with the username %s already exists!", USERNAME));

        robot.clickOn("#username");
        robot.write("1");
        robot.clickOn("#registerButton");
        assertThat(UserService.getAllUsers()).size().isEqualTo(2);
    }
}