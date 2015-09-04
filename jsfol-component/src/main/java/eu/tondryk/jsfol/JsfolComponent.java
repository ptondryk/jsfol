/**
 * 
 */
package eu.tondryk.jsfol;

import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * @author ptondryk
 *
 */
@FacesComponent(createTag = true, tagName = "jsfol", namespace = "http://tondryk.eu/jsfol/component")
@ResourceDependencies({
		@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
		@ResourceDependency(library = "jsfol", name = "jsfol.js", target = "head") })
public class JsfolComponent extends UIComponentBase {

	@Override
	public String getFamily() {
		return "eu.tondryk.jsfol";
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {

		if (this.getId() != null && this.getWidth() != null
				&& this.getHeight() != null && this.getX() != null
				&& this.getY() != null && this.getZoom() != null) {

			ResponseWriter writer = context.getResponseWriter();

			// add openlayers library script
			writer.startElement("script", null);
			writer.writeAttribute("type", "text/javascript", null);
			writer.writeAttribute("src", this.getOpenlayersSrc(), null);
			writer.endElement("script");

			// add main map div
			writer.startElement("div", null);
			writer.writeAttribute("id", this.getId(), null);
			writer.writeAttribute("style", "width:" + this.getWidth()
					+ "px;height:" + this.getHeight() + "px;", null);
			writer.endElement("div");

			// load map into div
			String jsVarName = "jsfolMap_" + this.getId();
			writer.startElement("script", null);
			writer.write("var " + jsVarName + " = new jsfol.Map();");
			writer.write(jsVarName + ".initMap('" + this.getId() + "',"
					+ this.getX() + "," + this.getY() + "," + this.getZoom()
					+ ")");
			writer.endElement("script");

		} else {
			throw new IOException(
					"Attributes 'id', 'width', 'height', 'x', 'y' and 'zoom' are required on the element 'jsfol'!");
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.getStateHelper().eval("id").toString();
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.getStateHelper().put("id", id);
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return (Integer) this.getStateHelper().eval("width");
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {
		this.getStateHelper().put("width", width);
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return (Integer) this.getStateHelper().eval("height");
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Integer height) {
		this.getStateHelper().put("height", height);
	}

	/**
	 * @return the x
	 */
	public Double getX() {
		return (Double) this.getStateHelper().eval("x");
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(Double x) {
		this.getStateHelper().put("x", x);
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return (Double) this.getStateHelper().eval("y");
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(Double y) {
		this.getStateHelper().put("y", y);
	}

	/**
	 * @return the zoom
	 */
	public Integer getZoom() {
		return (Integer) this.getStateHelper().eval("zoom");
	}

	/**
	 * @param zoom
	 *            the zoom to set
	 */
	public void setZoom(Integer zoom) {
		this.getStateHelper().put("zoom", zoom);
	}

	/**
	 * @return the openlayersSrc
	 */
	public String getOpenlayersSrc() {
		if (this.getStateHelper().eval("openlayersSrc") == null) {
			return "http://openlayers.org/en/v3.8.2/build/ol.js";
		} else {
			return (String) this.getStateHelper().eval("openlayersSrc");
		}
	}

	/**
	 * @param openlayersSrc
	 *            the openlayersSrc to set
	 */
	public void setOpenlayersSrc(String openlayersSrc) {
		this.getStateHelper().put("openlayersSrc", openlayersSrc);
	}
}
