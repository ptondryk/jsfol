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
public class JsfolFeaturesTest {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "jsfol-test.war")
				.addClasses(JsfolComponent.class, JsfolRenderer.class,
						FakeView.class)
				.addAsWebResource(
						new File("src/test/resources",
								"jsfolFeaturesTest.xhtml"), "test.xhtml")
				.addAsWebInfResource(
						new File("src/test/resources", "faces-config.xml"),
						"faces-config.xml")
				.addAsWebResource(
						new File("src/main/resources/META-INF/resources/jsfol",
								"jsfol.js"),
						"javax.faces.resource/jsfol.js.xhtml")
				.setWebXML(new File("src/test/resources", "web.xml"));
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
	public void testAddFeature() throws Exception {
		// draw a polygon
		this.clickMap(50, 50, false);
		this.clickMap(50, 100, false);
		this.clickMap(100, 50, true);

		// get the content of the testText
		String result = this.driver.findElement(By.id("testAddFeatureText"))
				.getText();

		// verify that the expected result has been returned
		Assert.assertEquals("Polygon", result);
	}

	/**
	 * In this test I draw a polygon on the map. I execute the map (= I upload
	 * the polygon to view) and I display the polygons string-representation in
	 * text-field.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	@InSequence(2)
	public void testExecuteFeature() throws InterruptedException {
		// draw a polygon
		this.clickMap(110, 110, false);
		this.clickMap(110, 140, false);
		this.clickMap(140, 140, false);
		this.clickMap(140, 110, true);

		// click the execute button
		this.driver.findElement(By.id("testExecuteButton")).click();

		// get the content of the testText
		String result = this.driver.findElement(By.id("testExecuteText"))
				.getText();

		// verify that the expected result has been returned (2 polygon-feature,
		// one with 4 points, one with 5 points)
		String expectedResult = "[Feature [geometry=Polygon [coordinates=[[Coordinate [x=13.271484374999998, y=52.60471559347519], Coordinate [x=13.271484374999998, y=52.52123476655552], Coordinate [x=13.4088134765625, y=52.60471559347519], Coordinate [x=13.271484374999998, y=52.60471559347519]]]], style=null], Feature [geometry=Polygon [coordinates=[[Coordinate [x=13.436279296875, y=52.504519532512035], Coordinate [x=13.436279296875, y=52.454335675121854], Coordinate [x=13.5186767578125, y=52.454335675121854], Coordinate [x=13.5186767578125, y=52.504519532512035], Coordinate [x=13.436279296875, y=52.504519532512035]]]], style=null]]";
		Assert.assertEquals(expectedResult, result);
	}

	/**
	 * In this test I rerender the map (and add a new feature) and check whether
	 * the features has been properly added.
	 * 
	 * @see {@link FakeView}.addFeature
	 */
	@Test
	@InSequence(3)
	public void testRenderFeature() {

		// click the render button
		this.driver.findElement(By.id("testRenderButton")).click();

		// get the content of the testText
		String result = this.driver.findElement(By.id("testAddFeatureText"))
				.getText();

		// verify that 3 features are contained: 2 polygons and 1 point
		String expectedResult = "Polygon,Polygon,Point";
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
