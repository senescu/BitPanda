package utils;

import java.io.*;

import java.util.Properties;


public class credentialsLoader {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    String firstName;
    String surName;
    String passWord;
    String emailAddress;

    // ---------------------------------------------------------------------------------

    Properties obj = new Properties();
    InputStream inputStream;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    {

        try {
            inputStream = new FileInputStream("src/test/java/tests/TestUser1.properties");
            obj.load(inputStream);
            firstName = obj.getProperty("firstName");
            surName = obj.getProperty("surName");
            passWord = obj.getProperty("passWord");
            emailAddress = obj.getProperty("emailAddress");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public String getFirstname() {
        return firstName;
    }

    public String getSurname() {
        return surName;
    }

    public String getPassword() {
        return passWord;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
