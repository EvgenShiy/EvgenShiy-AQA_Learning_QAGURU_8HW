package tests;

import data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.RegistrationPage;

import java.util.List;
import java.util.stream.Stream;

public class PracticeWithParameterizedTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    public void openUrl() {
        registrationPage.openPage();
    }


    @CsvSource(value = {
            "Bred, Pit, Male, 1234567890",
            "Coco, Chanel, Female, 0987654321",
            "Yoda, Baby, Other, 5432167890"
    })
    @ParameterizedTest(name = "Проверка регистрации с минимальными необходимыми данными для {2}")
    public void successRegistrationWithMinimumValueTest(String firstName, String lastName, String gender, String userNumber) {
        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(userNumber)
                .pressSubmitButton();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber);
    }


    @CsvFileSource(resources = "/registration_data.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Проверка регистрации с минимальными необходимыми данными из csv-файла для {2}")
    public void successRegistrationWithMinimumValueFromFileTest(String firstName, String lastName, String gender, String userNumber) {
        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(userNumber)
                .pressSubmitButton();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber);
    }



    static Stream<Arguments> successRegistrationWithMaxValueTest() {
        return Stream.of(
                Arguments.of(
                        new Person("David", "Shwimmer"),
                        new Email("David@Shwimmer.com"),
                        Genders.MALE,
                        new Phone("1234567890"),
                        "15",
                        "June",
                        "2012",
                        List.of("Civics", "Biology", "Hindi", "English", "Maths", "Chemistry"),
                        Hobbies.SPORTS,
                        new Foto("testfile.png"),
                        new Address("1234 Main St, Springfield"),
                        "NCR",
                        "Delhi"
                ),

                Arguments.of(
                        new Person("Rachel", "Green"),
                        new Email("Rachel@Green.com"),
                        Genders.FEMALE,
                        new Phone("0987654321"),
                        "25",
                        "January",
                        "1977",
                        List.of("Physics", "Economics", "Computer Science", "Commerce"),
                        Hobbies.READING,
                        new Foto("testfile2.png"),
                        new Address("1234 Main St, Springfield"),
                        "Uttar Pradesh",
                        "Agra"
                ),

                Arguments.of(
                        new Person("Snoopy", "Dog"),
                        new Email("Snoopy@Dog.com"),
                        Genders.OTHER,
                        new Phone("6789054321"),
                        "14",
                        "March",
                        "1995",
                        List.of("Social Studies", "Arts", "Accounting", "History"),
                        Hobbies.MUSIC,
                        new Foto("testfile3.png"),
                        new Address("1234 Main St, Springfield"),
                        "Haryana",
                        "Karnal"
                )
        );
    }

@MethodSource("successRegistrationWithMaxValueTest")
@ParameterizedTest(name = "Проверка формы регистрации с максимальными данными для {2}")
public void successRegistrationWithMaxValueTest(Person person, Email emailAddress, Genders genders, Phone phone, String day, String month, String year, List<String> subjects, Hobbies hobbies, Foto foto, Address currentAddress, String state, String city) {
    registrationPage
            .setFirstName(person.getFirstName())
            .setLastName(person.getLastName())
            .setUserEmail(emailAddress.getEmailAddress())
            .setGender(genders.getDescriptionGenders())
            .setUserNumber(phone.getPhone())
            .setDateOfBirth(day, month, year);

    for (String subject : subjects) {
        registrationPage.setSubjectsInput(subject);
    }

    registrationPage
            .setHobbies(hobbies.getDescriptionHobbies())
            .uploadPicture(foto.getFileName())
            .setCurrentAddress(currentAddress.getCurrentAddress())
            .setState(state)
            .setCity(city)
            .pressSubmitButton();

       registrationPage.checkResult("Student Name", person.getFirstName() + " " + person.getLastName())
            .checkResult("Student Email", emailAddress.getEmailAddress())
            .checkResult("Gender", genders.getDescriptionGenders())
            .checkResult("Mobile", phone.getPhone())
            .checkResult("Date of Birth", day + " " + month + "," + year)
            .checkResult("Subjects", String.join(", ", subjects))
            .checkResult("Hobbies", hobbies.getDescriptionHobbies())
            .checkResult("Picture", foto.getFileName())
            .checkResult("Address", currentAddress.getCurrentAddress())
            .checkResult("State and City", state + " " + city);
    }

}
