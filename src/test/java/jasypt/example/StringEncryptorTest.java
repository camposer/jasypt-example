package jasypt.example;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StringEncryptorTest {
    @Autowired
    private StringEncryptor encryptor;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("jasypt.encryptor.password", "secret");
    }
    
    // Every password generated is different because of salt
    @Test
    public void createPassword() {
        String encryptedText = encryptor.encrypt("this-is-my-password");
        System.out.println(encryptedText); // You can add this password to your properties file
    }

    @Configuration
    @EnableEncryptableProperties
    static class Config {

    }

}