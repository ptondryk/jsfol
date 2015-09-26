/**
 * 
 */
package eu.tondryk.jsfol.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.tondryk.jsfol.Feature;
import eu.tondryk.jsfol.geom.Geometry;
import eu.tondryk.jsfol.geom.GeometryCollection;
import eu.tondryk.jsfol.geom.Polygon;
import eu.tondryk.jsfol.style.Circle;
import eu.tondryk.jsfol.style.Fill;
import eu.tondryk.jsfol.style.Icon;
import eu.tondryk.jsfol.style.Stroke;
import eu.tondryk.jsfol.style.Style;
import eu.tondryk.jsfol.style.Text;
import eu.tondryk.jsfol.type.Color;
import eu.tondryk.jsfol.type.Coordinate;
import eu.tondryk.jsfol.type.Size;

/**
 * Tests of the class {@link JsfolFormatter}.
 * 
 * @author ptondryk
 *
 */
public class JsfolFormatterTest {

	/**
	 * simple test of the <code>convertFeature</code> method
	 */
	@Test
	public void testConvertFeature() {

		// prepare a test feature
		List<Coordinate> testLinearRing = new ArrayList<Coordinate>();
		testLinearRing.add(new Coordinate(30.5, 30.7));
		testLinearRing.add(new Coordinate(31.5, 30.7));
		testLinearRing.add(new Coordinate(30.5, 31.7));
		testLinearRing.add(new Coordinate(30.5, 30.7));
		List<List<Coordinate>> testCoordinates = new ArrayList<List<Coordinate>>();
		testCoordinates.add(testLinearRing);
		Geometry testPolygon = new Polygon(testCoordinates);
		Feature testFeature = new Feature(testPolygon, null);

		Assert.assertEquals(
				"new ol.Feature({geometry : new ol.geom.Polygon([[[30.5, 30.7], [31.5, 30.7], [30.5, 31.7], [30.5, 30.7]]])})",
				JsfolFormatter.convertFeature(testFeature));
	}

	/**
	 * simple test of the <code>convertFeature</code> method
	 */
	@Test
	public void testConvertFeatureWithGeometryCollection() {

		// prepare a test feature
		List<Coordinate> testLinearRing1 = new ArrayList<Coordinate>();
		testLinearRing1.add(new Coordinate(30.5, 30.7));
		testLinearRing1.add(new Coordinate(31.5, 30.7));
		testLinearRing1.add(new Coordinate(30.5, 31.7));
		testLinearRing1.add(new Coordinate(30.5, 30.7));
		List<Coordinate> testLinearRing2 = new ArrayList<Coordinate>();
		testLinearRing2.add(new Coordinate(20.5, 20.7));
		testLinearRing2.add(new Coordinate(21.5, 20.7));
		testLinearRing2.add(new Coordinate(20.5, 21.7));
		testLinearRing2.add(new Coordinate(20.5, 20.7));
		List<List<Coordinate>> testCoordinates1 = new ArrayList<List<Coordinate>>();
		testCoordinates1.add(testLinearRing1);
		List<List<Coordinate>> testCoordinates2 = new ArrayList<List<Coordinate>>();
		testCoordinates2.add(testLinearRing2);
		Geometry testPolygon1 = new Polygon(testCoordinates1);
		Geometry testPolygon2 = new Polygon(testCoordinates2);
		Feature testFeature = new Feature(new GeometryCollection(Arrays.asList(
				testPolygon1, testPolygon2)), null);

		Assert.assertEquals(
				"new ol.Feature({geometry : new ol.geom.GeometryCollection([new ol.geom.Polygon([[[30.5, 30.7], [31.5, 30.7], [30.5, 31.7], [30.5, 30.7]]]),new ol.geom.Polygon([[[20.5, 20.7], [21.5, 20.7], [20.5, 21.7], [20.5, 20.7]]])])})",
				JsfolFormatter.convertFeature(testFeature));
	}

	/**
	 * simple test of the <code>convertStyle</code> method
	 */
	@Test
	public void testConvertStyle1() {
		// prepare a test style
		Style testStyle = new Style();
		Fill testFil = new Fill(new Color(0, 0, 255, 0.2f));
		Stroke testStroke = new Stroke(new Color("red"), "square", "bevel",
				null, 3, 2);
		testStyle.setFill(testFil);
		testStyle.setStroke(testStroke);

		// prepare expected result
		String expectedResult = "new ol.style.Style({fill : new ol.style.Fill({color : 'rgba(255, 0, 0, 0.2)'}),stroke : new ol.style.Stroke({color : 'red',lineCap : 'square',lineJoin : 'bevel',miterLimit : 3,width : 2})})";

		// call the tested method and verify result
		Assert.assertEquals(expectedResult,
				JsfolFormatter.convertStyle(testStyle));

	}

	/**
	 * simple test of the <code>convertStyle</code> method, focus on the
	 * {@link Text} object
	 */
	@Test
	public void testConvertStyle2() {
		// prepare a test style
		Style testStyle = new Style();
		Fill testFill = new Fill(new Color(0, 0, 255, 0.2f));
		Stroke testStroke = new Stroke(new Color("red"), null, null, null,
				null, 2);
		Text testText = new Text("Arial", "test", "left", null, testFill,
				testStroke, 2, 3, 1.0, 0.0);
		testStyle.setText(testText);

		// prepare expected result
		String expectedResult = "new ol.style.Style({text : new ol.style.Text({fill : new ol.style.Fill({color : 'rgba(255, 0, 0, 0.2)'}),font : 'Arial',text : 'test',textAlign : 'left',offsetX : 2,offsetY : 3,rotation : 0.0,scale : 1.0,stroke : new ol.style.Stroke({color : 'red',width : 2})})})";

		// call the tested method and verify result
		Assert.assertEquals(expectedResult,
				JsfolFormatter.convertStyle(testStyle));

	}

	/**
	 * simple test of the <code>convertStyle</code> method, focus on
	 * {@link Icon}
	 */
	@Test
	public void testConvertStyle3() {
		// prepare a test style
		Style testStyle = new Style();
		Icon testIcon = new Icon();
		testIcon.setOpacity(0.5);
		testIcon.setSrc("img/test.jpg");
		testIcon.setSize(new Size(32, 32));
		testIcon.setOffset(new Double[] { 0.5d, 0.8d });
		testIcon.setAnchor(new Double[] { 0.5, 0.5 });
		testIcon.setAnchorXUnits("pixels");
		testIcon.setAnchorYUnits("pixels");
		testStyle.setImage(testIcon);

		// prepare expected result
		String expectedResult = "new ol.style.Style({image : new ol.style.Icon({anchor : [0.5, 0.5],anchorXUnits : 'pixels',anchorYUnits : 'pixels',opacity : 0.5,src : 'img/test.jpg',size : [32, 32],offset : [0.5, 0.8]})})";

		// call the tested method and verify result
		Assert.assertEquals(expectedResult,
				JsfolFormatter.convertStyle(testStyle));

	}

	/**
	 * simple test of the <code>convertStyle</code> method, focus on
	 * {@link Circle}
	 */
	@Test
	public void testConvertStyle4() {
		// prepare a test style
		Style testStyle = new Style();
		Fill testFill = new Fill(new Color(0, 0, 255, 0.2f));
		Stroke testStroke = new Stroke(new Color("green"), null, null,
				new Integer[] { 1, 2, 1, 3 }, null, 2);
		Circle testCircle = new Circle();
		testCircle.setOpacity(0.7d);
		testCircle.setFill(testFill);
		testCircle.setStroke(testStroke);
		testCircle.setRadius(5.5d);
		testCircle.setSnapToPixel(false);
		testStyle.setImage(testCircle);

		// prepare expected result
		String expectedResult = "new ol.style.Style({image : new ol.style.Circle({opacity : 0.7,radius : 5.5,fill : new ol.style.Fill({color : 'rgba(255, 0, 0, 0.2)'}),stroke : new ol.style.Stroke({color : 'green',width : 2,lineDash : [1, 2, 1, 3]})})})";

		// call the tested method and verify result
		Assert.assertEquals(expectedResult,
				JsfolFormatter.convertStyle(testStyle));

	}
}
