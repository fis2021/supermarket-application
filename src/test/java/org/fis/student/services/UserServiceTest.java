package org.fis.student.services;

import org.apache.commons.io.FileUtils;
import org.fis.student.exceptions.UsernameAlreadyExistsException;
import org.fis.student.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testInregistrare";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
    }

    @Test
    void TestBazaDeDateGoala(){
        assertNotEquals(null,UserService.getAllUsers());  //assertThat(UserService.getAllUsers()).isNotNull();
        assertTrue(UserService.getAllUsers().isEmpty());   //assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    void UserIsAdded() throws UsernameAlreadyExistsException {
        UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");


        assertNotEquals(null,UserService.getAllUsers());    //assertThat(UserService.getAllUsers()).isNotEmpty();
        assertEquals(1,UserService.getAllUsers().size());   //assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user=UserService.getAllUsers().get(0);
        assertNotEquals(null,UserService.getAllUsers());   //assertThat(user).isNotNull();
        assertEquals("adminul",user.getUsername());         //assertThat(user.getUsername()).isEqualTo("adminul");
        assertEquals("admin",user.getRole());               //assertThat(user.getRole()).isEqualTo("admin");
        assertEquals("adminul",user.getName());     //assertThat(user.getName()).isEqualTo("adminul");
        assertEquals("adminul",user.getAddress());     //assertThat(user.getAddress()).isEqualTo("adminul");
        assertEquals("adminul",user.getEmail());     //assertThat(user.getEmail()).isEqualTo("adminul");
    }

    @Test
    void UserCannotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () ->{
            UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
            UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
        });
    }

    @Test
    void UsernameAlreadyExists() {
        assertThrows(UsernameAlreadyExistsException.class, () ->{
            UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
            UserService.checkUserDoesNotAlreadyExist("adminul");
        });
    }

    @Test
    void UserRoleIsCorrect() throws UsernameAlreadyExistsException {
        UserService.addUser("adminul","adminul","admin","adminul","adminul","adminul");
        assertEquals("admin", UserService.getUserRole("adminul", "adminul"));     //assertThat(UserService.getUserRole("adminul", "adminul")).isEqualTo("admin");
    }

}