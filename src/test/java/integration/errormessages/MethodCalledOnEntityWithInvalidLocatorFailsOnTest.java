package integration.errormessages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import integration.IntegrationTest;
import integration.helpers.HTMLBuilderForTestPreconditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.InvalidSelectorException;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

@Disabled
class MethodCalledOnEntityWithInvalidLocatorFailsOnTest extends IntegrationTest {

  @BeforeEach
  void openPage() {
    HTMLBuilderForTestPreconditions.Given.openedPageWithBody(
      "<ul>Hello to:",
      "<li class='the-expanse detective'>Miller <label>detective</label></li>",
      "<li class='the-expanse missing'>Julie Mao</li>",
      "</ul>"
    );
    Configuration.timeout = 0;
  }

  @Test
  void shouldCondition_When$Element_WithInvalidLocator() {
    SelenideElement element = $("##invalid-locator");

    try {
      element.shouldHave(text("Miller"));
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified"));
    }
    //todo - need to fix
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            Command duration or timeout: 76 milliseconds
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void actionWithoutWaiting_When$Element__WithInvalidLocator() {
    SelenideElement element = $("##invalid-locator");

    try {
      element.exists();
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified"));
    }
    //todo - need to fix
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */

  }

  @Test
  void shouldCondition_WhenCollectionElementByIndex_WithInvalidCollectionLocator() {
    SelenideElement element = $$("##invalid-locator").get(0);

    try {
      element.shouldHave(text("Miller"));
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified"));
    }
    //todo - need to fix
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void shouldCondition_WhenCollectionElementByCondition_WithInvalidCollectionLocator() {
    SelenideElement element = $$("##invalid-locator").findBy(cssClass("the-expanse"));

    try {
      element.shouldBe(present);
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified"));
    }
    //todo  - need to fix
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void shouldCondition_WhenInnerElement_WithInvalidInnerElementLocator() {
    SelenideElement element = $("ul").find("##invalid-locator");

    try {
      element.shouldBe(present);
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified"));
    }
    //todo  - need to fix
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void shouldCondition_WhenInnerElement_WithInvalidOuterElementLocator() {
    SelenideElement element = $("##invalid-locator").find(".the-expanse");

    try {
      element.shouldBe(exactTextCaseSensitive("Miller"));
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified"));
    }
    //todo  - need to fix
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void shouldCondition_When$$Collection_WithInvalidLocator() {
    ElementsCollection collection = $$("##invalid-locator");

    try {
      collection.shouldHave(exactTexts("Miller", "Julie Mao"));
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      //todo - need to fix
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified\n"));
    }
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void actionWithoutWaiting_WhenFilteredCollection_WithInvalidLocator() {
    ElementsCollection collection = $$("##invalid-locator").filter(cssClass("the-expanse"));

    try {
      collection.getTexts();
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      //todo - need to fix
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified\n"));
    }
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void shouldCondition_WhenFilteredCollection_WithInvalidLocator() {
    ElementsCollection collection = $$("##invalid-locator").filter(cssClass("the-expanse"));

    try {
      collection.shouldHave(exactTexts("Miller", "Julie Mao"));
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      //todo - need to fix
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified\n"));
    }
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void shouldCondition_WhenInnerCollection_WithOuterInvalidLocator() {
    ElementsCollection collection = $("##invalid-locator").findAll("li");

    try {
      collection.shouldHave(exactTexts("Miller", "Julie Mao"));
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      //todo - need to fix
      assertThat(expected.getMessage(), startsWith("Element not found {##invalid-locator}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(),
        startsWith("The given selector ##invalid-locator is either invalid or does not result in a WebElement. " +
          "The following error occurred:\n" +
          "InvalidSelectorError: An invalid or illegal selector was specified\n"));
    }
        /*
            org.openqa.selenium.InvalidSelectorException:
            The given selector ##invalid-locator is either invalid or does not result in a WebElement.
            The following error occurred:
            InvalidSelectorError: An invalid or illegal selector was specified
            ...
            *** Element info: {Using=css selector, value=##invalid-locator}
        */
  }

  @Test
  void shouldCondition_WhenInnerCollection_WithInnerInvalidLocator() {
    ElementsCollection collection = $("ul").findAll("##invalid-locator");

    try {
      collection.shouldHave(exactTexts("Miller", "Julie Mao"));
      Assertions.fail("Expected ElementNotFound");
    } catch (ElementNotFound expected) {
      //todo - need to fix
      assertThat(expected.getMessage(), startsWith("Element not found {ul}"));
      assertThat(expected.getScreenshot(), containsString(Configuration.reportsFolder));
      assertThat(expected.getCause(), instanceOf(InvalidSelectorException.class));
      assertThat(expected.getCause().getMessage(), containsString("##invalid-locator"));
    }
        /*
            Element not found {ul}
            Expected: exist

            Screenshot: file:/E:/julia/QA/selenide/build/reports/tests/firefox/integration
            /errormessages/MethodCalledOnEntityWithInvalidLocatorFailsOnTest/
            shouldCondition_WhenInnerCollection_WithInnerInvalidLocator/1471820076618.1.png
            Timeout: 6 s.
            Caused by: NoSuchElementException: Unable to locate element: {"method":"css selector","selector":"ul"}
        */
  }
}
