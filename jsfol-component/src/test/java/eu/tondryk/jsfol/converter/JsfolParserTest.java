/**
 * 
 */
package eu.tondryk.jsfol.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.tondryk.jsfol.Feature;
import eu.tondryk.jsfol.geom.Polygon;

/**
 * Tests of the class {@link JsfolParser}.
 * 
 * @author ptondryk
 *
 */
public class JsfolParserTest {

	/**
	 * simple test of the <code>parseStyle</code> method
	 */
	@Test
	public void testParseStyle() {

		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[1485480.6809926806,6883620.941418062],[1521091.7860974488,6882396.987459423],[1497402.99845664,6921068.8947993545],[1485480.6809926806,6883620.941418062]]]},\"properties\":{\"style\":{\"fill\":{\"color\":\"rgba(255, 0, 0, 0.2)\"},\"stroke\":{\"color\":\"red\",\"width\":2}}}}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertEquals("red", parsedFeatures.get(0).getStyle().getStroke()
				.getColor().getLiteral());
		Assert.assertEquals(1521091.7860974488d,
				((Polygon) parsedFeatures.get(0).getGeometry())
						.getCoordinates().get(0).get(1).getX(), 0.1d);
	}
}
