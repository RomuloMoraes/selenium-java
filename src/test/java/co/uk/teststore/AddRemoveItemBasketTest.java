package co.uk.teststore;

import base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;


public class AddRemoveItemBasketTest extends BasePage {

    public AddRemoveItemBasketTest() throws IOException {
        super();
    }

    @BeforeTest
    public void setup() throws IOException {
        driver = getDriver();
        driver.get(getUrl());
    }

    @AfterTest
    public void tearDown() {
        //driver.close();
        driver = null;
    }

    @Test
    public void addRemoveItem() {
        // creating an object of the automationtesting.co.uk webpage
        HomePage home = new HomePage(driver);

        //handle cookie visibility using JSE - added 20230217
        /*JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView()", home.getTestStoreLink());
        home.getTestStoreLink().click();*/

        // creating an object of the test store homepage
        ShopHomePage shopHome = new ShopHomePage(driver);
        shopHome.getProdOne().click();

        // creating an object of the shop products page (when a product has been selected)
        ShopProductPage shopProd = new ShopProductPage(driver);
        Select option = new Select(shopProd.getSizeOption());
        option.selectByVisibleText("XL");
        shopProd.getQuantityIncrease().click();
        shopProd.getAddToCartBtn().click();

        // creating an object of the cart content panel (once an item was added)
        ShopContentPanel cPanel = new ShopContentPanel(driver);
        cPanel.getContinueShoppingBtn().click();
        shopProd.getHomePageLink().click();
        shopHome.getProdTwo().click();
        shopProd.getAddToCartBtn().click();
        cPanel.getCheckOutBtn().click();

        ShoppingCart cart = new ShoppingCart(driver);
        cart.getDeleteItemTwo().click();

        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.invisibilityOf(cart.getDeleteItemTwo()));

        String actualValue = cart.getTotalAmount().getText();


        Assert.assertEquals(actualValue, "$45.24");

    }
}
