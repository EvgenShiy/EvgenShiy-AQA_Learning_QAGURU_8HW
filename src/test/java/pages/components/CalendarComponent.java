package pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String day, String month, String year){
        // Убедитесь, что календарь открыт
        $("#dateOfBirthInput").click();

        // Выбор года, месяца и дня
        $(".react-datepicker__year-select").$(byText(year)).click();
        $(".react-datepicker__month-select").$(byText(month)).click();
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}
