package tests;

import data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import pages.components.CalendarComponent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeWithParameterizedTests extends TestBase {

    @BeforeEach
    public void openUrl() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove();");
        executeJavaScript("$('footer').remove();");
    }
    @CsvSource(value = {
            "Bred, Pit, Male, 1234567890",
            "Coco, Chanel, Female, 0987654321",
            "Yoda, Baby, Other, 5432167890"
    })
    @ParameterizedTest(name = "Проверка регистрации с минимальными необходимыми данными для {2}")
    public void successRegistrationWithMinimumValueTest(String firstName, String lastName, String gender, String userNumber) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#submit").click();
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive tbody tr:nth-child(1) td:nth-child(2)")
                .shouldHave(text(firstName + " " + lastName));
        $(".table-responsive tbody tr:nth-child(3) td:nth-child(2)")
                .shouldHave(text(gender));
        $(".table-responsive tbody tr:nth-child(4) td:nth-child(2)")
                .shouldHave(text(userNumber));
        $(By.cssSelector("#closeLargeModal")).scrollTo().click();
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
                        new Foto("testfile.png")
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
                        new Foto("testfile2.png")
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
                        new Foto("testfile3.png")
                )
        );
    }

    @MethodSource("successRegistrationWithMaxValueTest")
    @ParameterizedTest(name = "Проверка формы регистрации с максимальными данными для {2}")
    public void successRegistrationWithMaxValueTest(Person person, Email emailAddress, Genders genders, Phone phone, String day, String month, String year, List<String> subjects, Hobbies hobbies, Foto foto){
        $("#firstName").setValue(person.getFirstName());
        $("#lastName").setValue(person.getLastName());
        $("#userEmail").setValue(emailAddress.getEmailAddress());
        $("#genterWrapper").$(byText(genders.getDescriptionGenders())).click();
        $("#userNumber").setValue(phone.getPhone());

        CalendarComponent calendarComponent = new CalendarComponent();
        calendarComponent.setDate(day, month, year);

        for (String subject : subjects) {
            $("#subjectsInput").setValue(subject).pressEnter();
        }
        $("#hobbiesWrapper").$(byText(hobbies.getDescriptionHobbies())).click();
        $("#uploadPicture").uploadFromClasspath(foto.getFileName());
        $("#currentAddress").setValue("1234 Main St, Springfield");
//        $("#react-select-3-input").setValue("NCR").pressEnter();
//        $("#react-select-4-input").setValue("Delhi").pressEnter();
//        $("#submit").click();
//        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
//        $(".table-responsive tbody tr:nth-child(1) td:nth-child(2)")
//                .shouldHave(text("John Doe"));
//        $(".table-responsive tbody tr:nth-child(2) td:nth-child(2)")
//                .shouldHave(text("johndoe@example.com"));
//        $(".table-responsive tbody tr:nth-child(3) td:nth-child(2)")
//                .shouldHave(text("Male"));
//        $(".table-responsive tbody tr:nth-child(4) td:nth-child(2)")
//                .shouldHave(text("1234567890"));
//        $(".table-responsive tbody tr:nth-child(5) td:nth-child(2)")
//                .shouldHave(text("06 September,2024"));
//        $(".table-responsive tbody tr:nth-child(6) td:nth-child(2)")
//                .shouldHave(text("Maths"));
//        $(".table-responsive tbody tr:nth-child(7) td:nth-child(2)")
//                .shouldHave(text("Sports"));
//        $(".table-responsive tbody tr:nth-child(8) td:nth-child(2)")
//                .shouldHave(text("testfile.png"));
//        $(".table-responsive tbody tr:nth-child(9) td:nth-child(2)")
//                .shouldHave(text("1234 Main St, Springfield"));
//        $(".table-responsive tbody tr:nth-child(10) td:nth-child(2)")
//                .shouldHave(text("NCR Delhi"));
//        $(By.cssSelector("#closeLargeModal")).scrollTo().click();

    }

}
