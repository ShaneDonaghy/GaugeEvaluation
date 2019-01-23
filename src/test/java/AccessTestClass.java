import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class AccessTestClass {

    private WebDriver baseDriver;

    public AccessTestClass() {
        System.setProperty("webdriver.gecko.driver", "/Users/shane/Downloads/geckodriver");
        baseDriver = new FirefoxDriver();
        baseDriver.manage().window().maximize();
    }

    @Step("User opens the active admin application")
    public void openActiveAdmin() {
        baseDriver.get("http://localhost:8080/");
    }

    @Step("User clicks on signup link")
    public void clickSignUpLink() {
        baseDriver.findElement(By.linkText("Sign up")).click();
    }

    @Step("User enters <username> in username field")
    public void enterUsernameInField(String str){
        baseDriver.findElement(By.id("user_username")).sendKeys(str);
    }

    @Step("User enters <test01@test.com> in email field")
    public void enterEmail(String str) {
        baseDriver.findElement(By.id("user_email")).sendKeys(str);
    }

    @Step("User enters <test01> in password field")
    public void enterPassword(String str) {
        baseDriver.findElement(By.id("user_password")).sendKeys(str);
    }

    @Step("User enters <test01> in confirm password field")
    public void confirmPassword(String str) { baseDriver.findElement(By.id("user_password_confirmation")).sendKeys(str); }

    @Step("User presses signup button")
    public void pressSignUpButton() {
        baseDriver.findElement(By.xpath("//*[@id=\"new_user\"]/p[5]/input")).click();
    }

    //Helpfully, there are two divs with the id flash_notice. It is the second one we are interested in; it thanks the user for signing up.
    @Step("User is registered successfully")
    public void verifySignUp(){
        List<WebElement> flash_notices = baseDriver.findElements(By.id("flash_notice"));
        boolean confirmationMessagePresent = flash_notices.get(1).getText().contains("Thank you for signing up");
        assertThat(confirmationMessagePresent);
    }

    @Step("User closes the window")
    public void closeWindow(){
        baseDriver.close();
    }

    @Step("User clicks log out")
    public void logOut(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("GOING TO CLICK LOG OUT NOW");

        WebElement authDiv = baseDriver.findElement(By.id("auth"));
        List<WebElement> authLinks = authDiv.findElements(By.tagName("a"));
        System.out.println("Number of anchors in auth div: " + authLinks.size());
        authLinks.get(2).click();
    }

    @Step("User clicks log in link")
    public void clickLoginLink(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        baseDriver.findElement(By.xpath("/html/body/div/div[2]/a[4]")).click();
    }

    @Step("User clicks log in")
    public void clickLogIn(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        baseDriver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    }

    @Step("User <username> enters username")
    public void enterUsername(String str){
        baseDriver.findElement(By.id("login")).sendKeys(str);
    }

    @Step("User enters password <password>")
    public void enterLoginPassword(String str){ baseDriver.findElement(By.id("password")).sendKeys(str); }

    @Step("User clicks on random item")
    public void clickOnRandomItem(){
        // Selects a product at random
        Random rand = new Random();
        int selector = rand.nextInt(33) + 3;
        try{
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SELECTOR: " + selector);
        baseDriver.findElement(By.xpath("//*[@id=\"container\"]/div[" + selector + "]/h2/a")).click();
    }

    @Step("User adds item to cart")
    public void addToCart() { baseDriver.findElement(By.xpath("/html/body/div/div[3]/p[2]/a")).click(); }

    @Step("User checks out now")
    public void checkoutNow(){ baseDriver.findElement(By.xpath("/html/body/div/form/input")).click(); }

    //
    @Step("User sees shipping confirmation notice")
    public void confirmPurchase(){
        // Ambiguous ID - can you believe that?
        // XPath expression wasn't evaluating to correct element
        List<WebElement> flashNotices = baseDriver.findElements(By.xpath("//*[@id=\"flash_notice\"]"));
        String flashNoticeBannerText = flashNotices.get(1).getText();
        System.out.println(flashNoticeBannerText);
        assertThat(flashNoticeBannerText.contains("Thank you for your purchase"));
    }

    @Step("Admin opens administration interface")
    public void openAdminInterface(){
        baseDriver.findElement(By.xpath("//*[@id=\"auth\"]/a[1]")).click();
    }

    @Step("Admin opens the products tab")
    public void openProductsTab(){ baseDriver.findElement(By.xpath("/html/body/div/div[1]/ul[1]/li[2]/a")).click(); }

    @Step("Admin clicks the new product button")
    public void newProductButton(){ baseDriver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/span/a")).click(); }

    @Step("Admin enters the title <title>")
    public void enterTitle(String str){ baseDriver.findElement(By.id("product_title")).sendKeys(str); }

    @Step("Admin enters the description <str>")
    public void enterDescription(String str){ baseDriver.findElement(By.id("product_description")).sendKeys(str); }

    @Step("Admin enters the author <str>")
    public void enterAuthor(String str){ baseDriver.findElement(By.id("product_author")).sendKeys(str); }

    @Step("Admin enters the price at <19.99>")
    public void enterPrice(String str){ baseDriver.findElement(By.id("product_price")).sendKeys(str); }

    @Step("Admin toggles featured attribute <str>")
    public void toggleFeatured(String str){ if(str.equalsIgnoreCase("true")){ baseDriver.findElement(By.id("product_featured_input")).click(); } }

    @Step("Admin adds date <str>")
    public void setDate(String str){

        String[] dateStrings = str.split("/");

        System.out.println(dateStrings[0]);
        System.out.println(dateStrings[1]);
        System.out.println(dateStrings[2]);

        if(validateDate(str)){
            Select dayDropDown =  new Select(baseDriver.findElement(By.id("product_available_on_3i")));
            dayDropDown.selectByVisibleText(dateStrings[0]);

            Select monthDropDown =  new Select(baseDriver.findElement(By.id("product_available_on_2i")));
            monthDropDown.selectByVisibleText(dateStrings[1]);

            Select yearDropDown =  new Select(baseDriver.findElement(By.id("product_available_on_1i")));
            yearDropDown.selectByVisibleText(dateStrings[2]);
        } else {
            System.out.println("ERROR ENTERING THE DATE");
            System.out.println(dateStrings[0]);
            System.out.println(dateStrings[1]);
            System.out.println(dateStrings[2]);
        }
    }

    public boolean validateDate(String str){
        boolean success = false;
        char[] strArr = str.toCharArray();
        int countOfSlashes = 0;
        for(char a : strArr){
            if(a == '/'){
                countOfSlashes++;
            }
        }
        if(countOfSlashes == 2){
            success = true;
            System.out.println("Date is valid");
        }
        return success;
    }

    @Step("Admin adds image file path <str>")
    public void addImageFilePath(String str){ baseDriver.findElement(By.id("product_image_file_name")).sendKeys(str); }

    @Step("Admin clicks add product")
    public void addProduct(){ baseDriver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div/form/fieldset[2]/ol/li[1]/input")).click(); }

    @Step("Admin sees product added confirmation message")
    public void confirmationMessagePresent(){
        String flashNoticeText = baseDriver.findElement(By.xpath("/html/body/div/div[3]/div")).getText();
        assertThat(flashNoticeText.contains("Product was successfully created"));
    }

    @Step("Admin selects a test book")
    public void selectTestBook(){
        List<WebElement> testbooks = baseDriver.findElements(By.partialLinkText("Test Book"));
        testbooks.get(0).click();
    }

    @Step("Admin deletes test book")
    public void deleteTestBook(){ baseDriver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/span[2]/a")).click(); }

    @Step("Admin accepts confirmation alertbox")
    public void acceptAlert(){ baseDriver.switchTo().alert().accept(); }

    @Step("Admin sees product removed confirmation message")
    public void confirmDeleteMessagePresent(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String flashNoticeText = baseDriver.findElement(By.xpath("/html/body/div/div[3]/div")).getText();
        System.out.println(flashNoticeText);
        assertThat(flashNoticeText.contains("Product was successfully destroyed"));
    }

    @Step("Admin closes window")
    public void closeAdminWindow(){ baseDriver.close(); }

}
