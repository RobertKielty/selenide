package integration.customcommands;

import com.codeborne.selenide.commands.Commands;
import integration.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static integration.customcommands.MyFramework.$_;
import static integration.customcommands.MyFramework.quadrupleClickCounter;
import static integration.customcommands.MyFramework.tripleClickCounter;

class CustomCommandsTest extends IntegrationTest {
  @BeforeEach
  void setUpFramework() {
    MyFramework.setUp();
    tripleClickCounter.set(0);
    quadrupleClickCounter.set(0);
  }

  @Test
  void userCanAddAnyCustomCommandsToSelenide() {
    $_("#valid-image").tripleClick().tripleClick().tripleClick().click();
    $_("#invalid-image").tripleClick().quadrupleClick();

    Assertions.assertTrue($_("#valid-image img").isDisplayed(), "Can also use standard Selenium methods");
    $_("#valid-image img").shouldBe(visible);

    Assertions.assertEquals(4, tripleClickCounter.get());
    Assertions.assertEquals(1, quadrupleClickCounter.get());
  }

  @BeforeEach
  void openTestPage() {
    openFile("page_with_images.html");
  }

  @AfterEach
  void resetSelenideDefaultCommands() {
    Commands.getInstance().resetDefaults();
  }
}
