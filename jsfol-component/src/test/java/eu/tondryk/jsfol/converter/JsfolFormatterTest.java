/**
 * 
 */
package eu.tondryk.jsfol.converter;

import org.junit.Assert;
import org.junit.Test;

import eu.tondryk.jsfol.style.Fill;
import eu.tondryk.jsfol.style.Stroke;
import eu.tondryk.jsfol.style.Style;
import eu.tondryk.jsfol.type.Color;

/**
 * Tests of the class {@link JsfolFormatter}.
 * 
 * @author ptondryk
 *
 */
public class JsfolFormatterTest {

	/**
	 * simple test of the <code>convertStyle</code> method
	 */
	@Test
	public void testConvertStyle() {
		// prepare a test style
		Style testStyle = new Style();
		Fill testFil = new Fill(new Color(0, 0, 255, 0.2f));
		Stroke testStroke = new Stroke(new Color("red"), null, null, null,
				null, 2l);
		testStyle.setFill(testFil);
		testStyle.setStroke(testStroke);

		// prepare expected result
		String expectedResult = "new ol.style.Style({fill : new ol.style.Fill({color : 'rgba(255, 0, 0, 0.2)'}),stroke : new ol.style.Stroke({color : 'red',width : 2})})";

		// call the tested method and verify result
		Assert.assertEquals(expectedResult,
				JsfolFormatter.convertStyle(testStyle));

	}
}
