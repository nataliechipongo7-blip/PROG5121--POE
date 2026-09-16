
package prog5121.poe;


public class Login {
    String username;
    String password;
    String cellNumber;
    String firstName;
    String lastName;
    

// Checks if the username contains an underscore and that it is no more than 5 characters long.
    public boolean checkUserName(String username){
        if (username == null){
        return false;
        }
        if (!username.contains ("_")){
            return false;
        }
        if (username.length()> 5){
            return false;
        }
        return true;
       }
    
 
 // Checks if the password contains at least 8 characters,an uppercase letter, a digit, and a special character.  
    public boolean checkPasswordComplexity(String password){
        if (password == null || password.length()<8){
         return false;
        }
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            
            if (Character.isUpperCase(c)){
                hasUpper = true;
            } else if (Character.isDigit(c)){
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)){
                hasSpecial = true;
            } 
        }
        return hasUpper && hasDigit && hasSpecial;
    }
//Checks if the cellphone number starts with '+' and only contains valid international digits
    public boolean checkCellPhoneNumber(String cellNumber){
        if (cellNumber == null || !cellNumber.startsWith("+") || cellNumber.length() < 2 || cellNumber.length() > 13){
        return false;    
        }
        for (int i = 1; i < cellNumber.length(); i++){
            if (!Character.isDigit(cellNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

//Validates all inputs and stores the user details if valid
    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName){
        if (!checkUserName(username)){
          return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";  
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)){
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured. \nUser successfully registered.";
    }
// Verifies the entered credentials against stored credentials 
  public boolean loginUser(String enteredUsername, String enteredPassword){
        if (this.username == null || this.password == null) {
           return false;  
        }
        return this.username.equals(enteredUsername)&& this.password.equals(enteredPassword);
  }
// Returns login status message based on success or failure
   public String returnLoginStatus(boolean isLoggedIn){
       if (isLoggedIn){
           return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
       }
       return "Username or password incorrect, please try again.";
       
   }
}



  

        
    
    
        
      
    
        