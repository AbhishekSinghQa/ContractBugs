package search;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
public class TestCase {
	@Test
	public void openUrl() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://dev-trade.pocketful.in/api/space/v2/contractdetails/searchScrip?searchText=CENTEX&page=1");	
		System.out.println("page opened successfully");
	
	}
	@Test
	public void serachCentex() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://dev-trade.pocketful.in/api/space/v2/contractdetails/searchScrip?searchText=CENTEX&page=1");	
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		WebElement data = driver.findElement(By.xpath("//pre"));
		Assert.assertTrue(data.getText().contains("CENTEXT"), "CENTEX is not contained with in the page");
	
	}
	@Test
	public void validateTradingSymbol() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://dev-trade.pocketful.in/api/space/v2/contractdetails/searchScrip?searchText=CENTEX&page=1");	
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		WebElement data = driver.findElement(By.xpath("//pre"));
		String nseData = data.getText();
		try {
			JSONObject jsonObject = new JSONObject(nseData);
			JSONArray lineItems = jsonObject.getJSONArray("data");
			for (int i = 0; i < lineItems.length(); ++i) {
				JSONObject rec = lineItems.getJSONObject(i);
				String exchange = rec.getString("exchange");
				if (exchange.equalsIgnoreCase("NSE")) {
					String tradingSymbol = rec.getString("tradingSymbol");
					Assert.assertTrue(tradingSymbol.equalsIgnoreCase("CENTEXT"),
							"tradingSymbol is not equal to CENTEX but it is :" + tradingSymbol);

				}
			}

		} catch (JSONException e) {
			System.out.println("Error " + e.toString());
		}
		
	}
	
}
