import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	static WebDriver d=null;

	
	@FindBy (xpath="//*[@id=\"root\"]/div/main/div/div[1]/div[4]/label/span")
	WebElement sizeSelection;
	
	@FindBy (xpath="//*[@id=\"root\"]/div/main/main/div/div[1]/button")
	WebElement product1;

	@FindBy (xpath="//*[@id=\"root\"]/div/main/main/div/div[2]/button")
	WebElement product2;
	
	@FindBy (xpath="//*[@id=\"root\"]/div/div[2]/div/div[3]/div/p[1]")
	WebElement PriceVerification;
	
	@FindBy (xpath="//*[@id=\"root\"]/div/div[2]/div/div[3]/button")
	WebElement checkout;
	
	
	
	public Homepage(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d, this);
	}
	
	public void selectSizeBTN() {
		sizeSelection.click();
	}
	
	public void selectp1BTN() {
		product1.click();
	}
	public void selectp2BTN() {
		product2.click();
	}
	
	public String Totalprice() {
		return PriceVerification.getText();
	}
	
	public void checkoutBTN() {
		 checkout.click();
	}
}
