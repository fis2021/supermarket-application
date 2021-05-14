package services;

import exceptions.UsernameAlreadyExistsException;
import model.User;
import org.h2.store.fs.FileUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testInregistrare";
        //FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @Test
    void TestBazaDeDateGoala(){
        //assertThat(UserService.getAllUsers()).isNotNull();
        //assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    void UserIsAdded() throws UsernameAlreadyExistsException {
        UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
        //assertThat(UserService.getAllUsers()).isNotEmpty();
        //assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user=UserService.getAllUsers().get(0);
        //assertThat(user).isNotNull();
        /*assertThat(user.getUsername()).isEqualTo("adminul");
        assertThat(user.getRole()).isEqualTo("adminul");
        assertThat(user.getName()).isEqualTo("adminul");
        assertThat(user.getAddress()).isEqualTo("adminul");
        assertThat(user.getEmail()).isEqualTo("adminul");*/
    }

    @Test
    void UserCannotBeAddedTwice() {
        /*AssertThrows(UsernameAlreadyExistsException.class, () ->{
         UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
         UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
    });*/
    }

    @Test
    void UsernameAlreadyExists() {
        /*AssertThrows(UsernameAlreadyExistsException.class, () ->{
         UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
         UserService.checkUserDoesNotAlreadyExist("adminul");
    });*/
    }

    @Test
    void UserRoleIsCorrect() throws UsernameAlreadyExistsException {
         UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
         //assertThat(UserService.getUserRole("adminul", "adminul")).isEqualTo("admin");
    }

}