package co.uk.teststore;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class OrderCompleteTest extends BasePage {

    public OrderCompleteTest() throws IOException {
        super();
    }

    @BeforeTest
    public void setup() {
        driver = getDriver();
        driver.get(getUrl());
    }

    @AfterTest
    public void tearDown() {
        //driver.close(); 
        driver = null;
    }

    @Test
    public void testCompleteOrder() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        //homePage.getCookie();
        //homePage.getWaitHomePage();
        //homePage.getTestStoreLink().click();
        //driver.get("http://teststore.automationtesting.co.uk/");

        ShopHomePage shopHomePage = new ShopHomePage(driver);
        shopHomePage.getProdOne().click();

        ShopProductPage shopProductPage = new ShopProductPage(driver);
        Select option = new Select(shopProductPage.getSizeOption());
        option.selectByVisibleText("M");
        shopProductPage.getQuantityIncrease().click();
        shopProductPage.getAddToCartBtn().click();

        ShopContentPanel shopContentPanel = new ShopContentPanel(driver);
        shopContentPanel.getCheckOutBtn().click();

        ShoppingCart shoppingCart = new ShoppingCart(driver);
        shoppingCart.getHavePromo().click();
        shoppingCart.getPromoTextBox().sendKeys("20OFF");
        shoppingCart.getPromoAddBtn().click();
        shoppingCart.getProceedCheckoutBtn().click();

        OrderFormPersInfo persInfo = new OrderFormPersInfo(driver);
        persInfo.getGenderMr().click();
        persInfo.getFirstNameField().sendKeys("Fulano");
        persInfo.getLastnameField().sendKeys("De Tal");
        persInfo.getEmailField().sendKeys("fuladodetal@email.com");
        persInfo.getTermsConditionsCheckbox().click();
        persInfo.getContinueBtn().click();

        OrderFormDelivery formDelivery = new OrderFormDelivery(driver);
        formDelivery.getAddressField().sendKeys(" N 101 Main Street");
        formDelivery.getCityField().sendKeys("Houston");
        Select state = new Select(formDelivery.getStateDropdown());
        state.selectByVisibleText("Texas");
        formDelivery.getPostcodeField().sendKeys("55555");
        formDelivery.getContinueBtn().click();

        OrderFormShippingMethod shippingMethod = new OrderFormShippingMethod(driver);
        shippingMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
        shippingMethod.getContinueBtn().click();

        OrderFormPayment formPayment = new OrderFormPayment(driver);
        formPayment.getPayByWireRadioBtn().click();
        formPayment.getTermsConditionsCheckbox().click();
        formPayment.getOrderBtn().click();

    }

}
