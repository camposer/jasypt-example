package jasypt.example;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class StringDecryptorTest {
    @Autowired
    private PropertyServiceForJasyptSimple service;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("jasypt.encryptor.password", "secret");
    }

    @Test
    public void validatePassword() {
        assertEquals("this-is-my-password", service.password);
    }

    @Configuration
    @EncryptablePropertySource("application.properties")
    static class Config {
        @Bean
        public PropertyServiceForJasyptSimple propertyServiceForJasyptSimple(@Value("${password}") String password) {
            return new PropertyServiceForJasyptSimple(password);
        }
    }

}

class PropertyServiceForJasyptSimple {
    public final String password;

    public PropertyServiceForJasyptSimple(String password) {
        this.password = password;
    }

}