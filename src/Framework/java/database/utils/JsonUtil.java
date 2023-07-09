package database.utils;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {

    private JsonUtil() { }

    public static  <T> T createModelFromJSON(String file, Class<T> modelClass) {
        try {
            return new ObjectMapper().getFactory().createParser(new File(file)).readValueAs(modelClass);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
            return null;
        }
    }
}