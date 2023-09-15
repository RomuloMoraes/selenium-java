package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopProductPage {

    public WebDriver driver;

    By sizeOption = By.cssSelector("[data-product-attribute='1']");
    By quantityIncrease = By.cssSelector(".touchspin-up");
    By quantityDecrease = By.cssSelector(".touchspin-down");
    By addToCartBtn = By.cssSelector(".add-to-cart.btn.btn-primary");
    By homePageLink = By.xpath("//span[text()=Home]");

    public ShopProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSizeOption() {
        return driver.findElement(sizeOption);
    }

    public WebElement getQuantityIncrease() {
        return driver.findElement(quantityIncrease);
    }

    public WebElement getQuantityDecrease() {
        return driver.findElement(quantityDecrease);
    }

    public WebElement getAddToCartBtn() {
        return driver.findElement(addToCartBtn);
    }

    public WebElement getHomePageLink() {
        return driver.findElement(homePageLink);
    }
}
