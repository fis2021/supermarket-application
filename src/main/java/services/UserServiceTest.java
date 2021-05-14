package services;

import org.h2.store.fs.FileUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    @BeforeEach
    void setUp(){
        FileSystemService.APPLICATION_FOLDER=".testInregistrare";
        //FileUtils.cleanDirectory();
    }

    @Test
    void TestBazaDeDateGoala(){
        System.out.println("testul1");
       // assertThat(UserService.getAllUsers());

    }


}