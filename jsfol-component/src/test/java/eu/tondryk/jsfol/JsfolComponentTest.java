/**
 * 
 */
package eu.tondryk.jsfol;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import eu.tondryk.jsfol.JsfolComponent;
import eu.tondryk.jsfol.JsfolRenderer;

/**
 * Tests of the {@link JsfolComponent}.
 * 
 * @author ptondryk
 *
 */
@RunWith(Arquillian.class)
public class JsfolComponentTest {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "jsfol-test.war")
				.addClasses(JsfolComponent.class, JsfolRenderer.class)
				.addAsWebResource(
						new File("src/test/resources",
								"jsfolComponentTest.xhtml"), "test.xhtml")
				.addAsWebInfResource(
						new File("src/test/resources", "faces-config.xml"),
						"faces-config.xml")
				.setWebXML(new File("src/test/resources", "web.xml"));
	}

	@Drone
	private WebDriver driver;

	@ArquillianResource
	private URL deploymentUrl;

	/**
	 * in this test, verify that canvas has been added to the <code>test</code>
	 * div
	 */
	@Test
	public void testComponentRenderer() {
		this.driver.get(deploymentUrl.toExternalForm() + "test.xhtml");
		WebElement testDiv = this.driver.findElement(By.id("test"));
		List<WebElement> expectedCanvas = testDiv.findElements(By
				.tagName("canvas"));

		// verify that "test"-div has canvas as child
		Assert.assertFalse(expectedCanvas.isEmpty());
	}
}
