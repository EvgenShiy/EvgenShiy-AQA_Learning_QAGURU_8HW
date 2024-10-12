package tests;


import pages.RegistrationPage;
import org.junit.jupiter.api.Test;

public class TextBoxWithPageObjectTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    public void formSubmissionTest() {
        registrationPage.openPage()
                .setFirstName("John")
                .setLastName("Doe")
                .setUserEmail("johndoe@example.com")
                .setGender("Male")
                .setUserNumber("1234567890")
                .setDateOfBirth("6", "September", "2024")
                .setSubjectsInput("Maths")
                .setHobbies("Sports")
                .uploadPicture("testfile.png")
                .setCurrentAddress("1234 Main St, Springfield")
                .setState("NCR")
                .setCity("Delhi")
                .pressSubmitButton();

        registrationPage.checkResult("Student Name", "John Doe")
                .checkResult("Student Email", "johndoe@example.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890")
                .checkResult("Date of Birth", "6 September,2024")
                .checkResult("Subjects", "Math")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "testfile.png")
                .checkResult("State and City", "NCR Delhi");
    }
}