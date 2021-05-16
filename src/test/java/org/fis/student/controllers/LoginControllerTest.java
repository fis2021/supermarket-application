package org.fis.student.controllers;

import com.sun.glass.events.KeyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.fis.student.exceptions.UsernameAlreadyExistsException;
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
class LoginControllerTest {

    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testLogin";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Login Example");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    @Test
    void testLoginClient(FxRobot robot) {
        try {
            UserService.addUser(USERNAME, PASSWORD, "Client", USERNAME, "addressTest", "emailTest");
        }catch (UsernameAlreadyExistsException e){};
        robot.clickOn("#registerLoginButton");
        robot.clickOn("#backRegisterButton");

        robot.clickOn("#usernameLogin");
        robot.write(USERNAME);
        robot.clickOn("#passwordLogin");
        robot.write(PASSWORD);

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginAdministrator(FxRobot robot) {
        try {
            UserService.addUser(USERNAME, PASSWORD, "Administrator", USERNAME, "addressTest", "emailTest");
        }catch (UsernameAlreadyExistsException e){};

        robot.clickOn("#usernameLogin");
        robot.write(USERNAME);
        robot.clickOn("#passwordLogin");
        robot.write(PASSWORD);

        robot.press(KeyCode.valueOf("ENTER"));
        robot.release(KeyCode.valueOf("ENTER"));
    }

    @Test
    void testLoginIncorrect(FxRobot robot) {
        try {
            UserService.addUser(USERNAME, PASSWORD, "Client", USERNAME, "addressTest", "emailTest");
        }catch (UsernameAlreadyExistsException e){};

        robot.clickOn("#usernameLogin");
        robot.write("wrong");
        robot.clickOn("#passwordLogin");
        robot.write(PASSWORD);

        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Incorrect login!");
    }

    @Test
    void testLoginIncomplete(FxRobot robot) {
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Please type in a username!");
        robot.clickOn("#usernameLogin");
        robot.write("incomplete");

        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Password cannot be empty");
        robot.clickOn("#passwordLogin");
        robot.write(PASSWORD);

        robot.clickOn("#loginButton");
    }
}