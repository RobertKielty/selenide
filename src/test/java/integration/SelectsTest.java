package integration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.hamcrest.CoreMatchers.equalTo;

class SelectsTest extends IntegrationTest {

  @BeforeEach
  void openTestPage() {
    openFile("page_with_selects_without_jquery.html");
  }

  @AfterEach
  void resetProperties() {
    Configuration.versatileSetValue = false;
  }

  @Test
  void userCanSelectOptionByValue() {
    SelenideElement select = $(By.xpath("//select[@name='domain']"));
    select.selectOptionByValue("myrambler.ru");

    select.getSelectedOption().shouldBe(selected);
    Assertions.assertEquals("myrambler.ru", select.getSelectedValue());
    Assertions.assertEquals("@myrambler.ru", select.getSelectedText());
  }

  @Test
  void userCanSelectValueUsingSetValue() {
    Configuration.versatileSetValue = true;
    SelenideElement select = $(byName("domain"));
    select.setValue("myrambler.ru");

    Assertions.assertEquals("myrambler.ru", select.getSelectedValue());
    Assertions.assertEquals("@myrambler.ru", select.getSelectedText());
  }

  @Test
  void userCanSelectOptionByIndex() {
    SelenideElement select = $(By.xpath("//select[@name='domain']"));

    select.selectOption(0);
    MatcherAssert.assertThat(select.getSelectedText(), equalTo("@livemail.ru"));

    select.selectOption(1);
    MatcherAssert.assertThat(select.getSelectedText(), equalTo("@myrambler.ru"));

    select.selectOption(2);
    MatcherAssert.assertThat(select.getSelectedText(), equalTo("@rusmail.ru"));

    select.selectOption(3);
    MatcherAssert.assertThat(select.getSelectedText(), equalTo("@мыло.ру"));
  }

  @Test()
  void throwsElementNotFoundWithOptionsText() {
    Assertions.assertThrows(ElementNotFound.class,
      () -> $x("//select[@name='domain']").selectOption("unexisting-option"),
      "Element not found {By.xpath: //select[@name='domain']/option[text:unexisting-option]}\nExpected: exist");
  }

  @Test()
  void throwsElementNotFoundWithOptionsIndex() {
    Assertions.assertThrows(ElementNotFound.class,
      () -> $x("//select[@name='domain']").selectOption(999),
      "Element not found {By.xpath: //select[@name='domain']/option[index:999]}\nExpected: exist");
  }

  @Test
  void valMethodSelectsOptionInCaseOfSelectBox() {
    Configuration.versatileSetValue = true;
    SelenideElement select = $(By.xpath("//select[@name='domain']"));
    select.val("myrambler.ru");

    select.getSelectedOption().shouldBe(selected);
    Assertions.assertEquals("myrambler.ru", select.getSelectedValue());
    Assertions.assertEquals("@myrambler.ru", select.getSelectedText());
  }

  @Test
  void userCanSelectOptionByText() {
    SelenideElement select = $(By.xpath("//select[@name='domain']"));
    select.selectOption("@мыло.ру");

    select.getSelectedOption().shouldBe(selected);
    Assertions.assertEquals("мыло.ру", select.getSelectedValue());
    Assertions.assertEquals("@мыло.ру", select.getSelectedText());
  }

  @Test
  void userCanSelectOptionByPartialText() {
    SelenideElement select = $(By.xpath("//select[@name='domain']"));
    select.selectOptionContainingText("ыло.р");

    Assertions.assertEquals("@мыло.ру", select.getSelectedText());
  }

  @Test
  void getTextReturnsTextsOfSelectedOptions() {
    Assertions.assertEquals("-- Select your hero --", $("#hero").getText());

    $("#hero").selectOptionByValue("john mc'lain");
    Assertions.assertEquals("John Mc'Lain", $("#hero").getText());
  }

  @Test
  void shouldHaveTextChecksSelectedOption() {
    $("#hero").shouldNotHave(text("John Mc'Lain").because("Option is not selected yet"));

    $("#hero").selectOptionByValue("john mc'lain");
    $("#hero").shouldHave(text("John Mc'Lain").because("Option `john mc'lain` is selected"));
  }

  @Test
  void optionValueWithApostrophe() {
    $("#hero").selectOptionByValue("john mc'lain");
    $("#hero").getSelectedOption().shouldHave(text("John Mc'Lain"));
  }

  @Test
  void optionValueWithQuote() {
    $("#hero").selectOptionByValue("arnold \"schwarzenegger\"");
    $("#hero").getSelectedOption().shouldHave(text("Arnold \"Schwarzenegger\""));
  }

  @Test
  void optionTextWithApostrophe() {
    $("#hero").selectOption("John Mc'Lain");
    $("#hero").getSelectedOption().shouldHave(value("john mc'lain"));
  }

  @Test
  void optionTextWithQuote() {
    $("#hero").selectOption("Arnold \"Schwarzenegger\"");
    $("#hero").getSelectedOption().shouldHave(value("arnold \"schwarzenegger\""));
  }

  @Test
  void optionTextWithApostropheInsideQuote() {
    $("#hero").selectOption("Mickey \"Rock'n'Roll\" Rourke");
    $("#hero").getSelectedOption().shouldHave(value("mickey rourke"));
  }

  @Test
  void selectingOptionTriggersChangeEvent() {
    $("#selectedDomain").shouldBe(empty);

    $(By.xpath("//select[@name='domain']")).selectOption("@мыло.ру");
    $("#selectedDomain").shouldHave(text("@мыло.ру"));

    $(By.xpath("//select[@name='domain']")).selectOptionByValue("myrambler.ru");
    $("#selectedDomain").shouldHave(text("@myrambler.ru"));
  }
}
