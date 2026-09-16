import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // Test 1
    @Test
    public void testUsernameCorrectlyFormatted() {
        assertEquals(true, Login.checkUserName("kyle_"));
    }

    // Test 2
    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertEquals(false, Login.checkUserName("kyle!!!"));
    }

    // Test 3
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        assertEquals(true, Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    // Test 4
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        assertEquals(false, Login.checkPasswordComplexity("password."));
    }

    // Test 5
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertEquals(true, Login.checkCellPhoneNumber("+27838968976"));
    }

    // Test 6
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertEquals(false, Login.checkCellPhoneNumber("08966553"));
    }

    // Test 7
    @Test
    public void testLoginSuccessful() {
        assertTrue(Login.loginUser("kyle_", "Password1!", "kyle_", "Password1!"));
    }

    // Test 8
    @Test
    public void testLoginUnsuccessful() {
        assertFalse(Login.loginUser("kyle_", "Password1!", "kyle_", "WrongPassword1!"));
    }

    // Test 9
    @Test
    public void testUsernameReturnsTrue() {
        assertTrue(Login.checkUserName("kyle_"));
    }

    // Test 10
    @Test
    public void testUsernameReturnsFalse() {
        assertFalse(Login.checkUserName("kyle!!!"));
    }

    // Test 11
    @Test
    public void testPasswordReturnsTrue() {
        assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    // Test 12
    @Test
    public void testPasswordReturnsFalse() {
        assertFalse(Login.checkPasswordComplexity("password."));
    }

    // Test 13
    @Test
    public void testCellPhoneReturnsTrue() {
        assertTrue(Login.checkCellPhoneNumber("+27838968976"));
    }

    // Test 14
    @Test
    public void testCellPhoneReturnsFalse() {
        assertFalse(Login.checkCellPhoneNumber("08966553"));
    }



}
