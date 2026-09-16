package prog5121.poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LoginTest {
    Login login = new Login();
    
    //===============================
    //USERNAME TESTS
    //===============================
    
    @Test
    public void testUserNameCorrect(){
      assertTrue(login.checkUserName("kyl_1"));     
    }
    
    @Test
    public void testUserNameInCorrect(){
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }
    
   //===============================
   //PASSWORD TESTS
   //===============================
    
    @Test
    public void testPasswordSuccess(){
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testPasswordFailure(){
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
   //=============================
   //CELLPHONE NUMBER TESTS
   //=============================
    
    @Test
    public void testCellPhoneNumberSuccess(){
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test 
    public void testCellPhoneNumberFailure(){
        assertFalse(login.checkCellPhoneNumber("0838968976"));
    }
    
   //==============================
   //LOGIN TESTS
   //==============================
    
   @Test
   public void testLoginSuccess(){
    login.registerUser("kyl_1","Ch&&sec@ke99!", "+27838968976", "Kyle", "LastName");
    assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
   }
   
   @Test
   public void testLoginFailure(){
       login.registerUser("kyl_1","Ch&&sec@ke99!", "+27838968976", "Kyle","LastName");
       assertFalse(login.loginUser("kyl_1", "wrongPass"));
      }
   
   }

    
    