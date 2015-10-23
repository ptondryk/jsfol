/**
 * 
 */
package eu.tondryk.jsfol;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import eu.tondryk.jsfol.test.view.FakeView;

/**
 * Tests of the {@link JsfolComponent}.
 * 
 * @author ptondryk
 *
 */
@RunWith(Arquillian.class)
public class JsfolComponentExecuteTest {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "jsfol-test.war")
				.addClasses(JsfolComponent.class, JsfolRenderer.class,
						FakeView.class)
				.addAsWebResource(
						new File("src/test/resources",
								"jsfolComponentExecuteTest.xhtml"),
						"test.xhtml")
				.addAsWebInfResource(
						new File("src/test/resources", "faces-config.xml"),
						"faces-config.xml")
				.addAsWebResource(
						new File("src/main/resources/META-INF/resources/jsfol",
								"jsfol.js"),
						"javax.faces.resource/jsfol.js.xhtml")
				.setWebXML(new File("src/test/resources", "web.xml"));
		System.out.println(war.toString(true));
		return war;
	}

	@Drone
	private WebDriver driver;

	@ArquillianResource
	private URL deploymentUrl;

	/**
	 * id of the map-object in the view
	 */
	private final String mapId = "testMap";

	/**
	 * verify that the map has been displayed
	 */
	@Test
	@InSequence(0)
	public void testMapVisible() {
		// open the test website
		this.driver.get(deploymentUrl.toExternalForm() + "test.xhtml");

		// verify that map has been added
		Assert.assertTrue(this.driver.findElement(By.id(this.mapId))
				.isDisplayed());
	}

	/**
	 * verify that it is possible to draw a polygon on the map and that the
	 * polygon can be executed via ajax-call
	 */
	@Test
	@InSequence(1)
	public void testComponentRenderer() throws Exception {
		// draw a polygon
		this.clickMap(50, 50, false);
		this.clickMap(50, 100, false);
		this.clickMap(100, 50, true);

		// click the execute button
		this.driver.findElement(By.id("testButton")).click();

		// get the content of the testText
		String result = this.driver.findElement(By.id("testText")).getText();

		// verify that the expected result has been returned (1 polygon-feature
		// with 4 points)
		String expectedResult = "[Feature [geometry=Polygon [coordinates=[[Coordinate [x=13.271484374999998, y=52.60471559347519], Coordinate [x=13.271484374999998, y=52.52123476655552], Coordinate [x=13.4088134765625, y=52.60471559347519], Coordinate [x=13.271484374999998, y=52.60471559347519]]]], style=null]]";
		Assert.assertEquals(expectedResult, result);
	}

	/**
	 * Click the map.
	 * 
	 * @param x
	 *            offset within the visible map-object
	 * @param y
	 *            offset within the visible map-object
	 * @param doubleClick
	 *            <code>true</code> if it should be a double-click
	 * @throws InterruptedException
	 */
	private void clickMap(int x, int y, boolean doubleClick)
			throws InterruptedException {
		Actions actions = new Actions(this.driver).moveToElement(
				this.driver.findElement(By.id(this.mapId)), x, y);
		if (doubleClick) {
			// move the mouse (1 px) to avoid problem with firefox driver...
			actions.clickAndHold().release().moveByOffset(1, 0).clickAndHold()
					.release().perform();
		} else {
			actions.clickAndHold().release().perform();
		}
	}
}
