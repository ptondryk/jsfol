/**
 * 
 */
package eu.tondryk.jsfol;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

/**
 * @author ptondryk
 *
 */
@FacesComponent(createTag = true, tagName = "jsfol", namespace = "http://tondryk.eu/jsfol/component")
@ResourceDependencies({
		@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
		@ResourceDependency(library = "jsfol", name = "jsfol.js", target = "head") })
@ListenerFor(systemEventClass = PostAddToViewEvent.class)
public class JsfolComponent extends UIComponentBase implements
		ClientBehaviorHolder {

	/**
	 * 
	 */
	public static final String COMPONENT_FAMILY = "eu.tondryk.jsfol";

	/**
	 * Properties used in this component.
	 * 
	 * @author ptondryk
	 *
	 */
	enum PropertyKeys {
		value, x, y, zoom, interactionType, openlayersJs, openlayersCss, width, height, showZoomSlider;
	}

	/**
	 * empty constructor
	 */
	public JsfolComponent() {
		this.setRendererType(JsfolRenderer.RENDERER_TYPE);
	}

	@Override
	public String getFamily() {
		return JsfolComponent.COMPONENT_FAMILY;
	}

	@Override
	public Collection<String> getEventNames() {
		return Arrays.asList("newfeature");
	}

	@Override
	public void processUpdates(FacesContext context) {
		super.processUpdates(context);
		for (PropertyKeys propertyKey : PropertyKeys.values()) {
			ValueExpression ve = this.getValueExpression(propertyKey.name());
			if (ve != null
					&& !this.getStateHelper().eval(propertyKey)
							.equals(ve.getValue(context.getELContext()))) {
				try {
					ve.setValue(context.getELContext(), this.getStateHelper()
							.eval(propertyKey));
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * reload values from bean
	 */
	public void reloadValues(FacesContext context) {
		for (PropertyKeys propertyKey : PropertyKeys.values()) {
			ValueExpression ve = this.getValueExpression(propertyKey.name());
			if (ve != null
					&& !this.getStateHelper().eval(propertyKey)
							.equals(ve.getValue(context.getELContext()))) {
				this.getStateHelper().put(propertyKey,
						ve.getValue(context.getELContext()));
			}
		}
	}

	@Override
	public void processEvent(ComponentSystemEvent event)
			throws AbortProcessingException {
		if (event instanceof PostAddToViewEvent) {
			UINamingContainer scripts = new UINamingContainer() {
				@Override
				public void encodeBegin(FacesContext context)
						throws IOException {
					ResponseWriter writer = context.getResponseWriter();
					writer.startElement("script", this);
					writer.writeAttribute("type", "text/javascript", null);
					writer.writeAttribute("src", getOpenlayersJs(), null);
					writer.endElement("script");
					writer.startElement("link", null);
					writer.writeAttribute("rel", "stylesheet", null);
					writer.writeAttribute("href", getOpenlayersCss(), null);
					writer.writeAttribute("type", "text/css", null);
					writer.endElement("link");
				}
			};
			FacesContext
					.getCurrentInstance()
					.getViewRoot()
					.addComponentResource(FacesContext.getCurrentInstance(),
							scripts);
		}
		super.processEvent(event);
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.width, 100);
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {
		this.getStateHelper().put(PropertyKeys.width, width);
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.height, 100);
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Integer height) {
		this.getStateHelper().put(PropertyKeys.height, height);
	}

	/**
	 * @return the x
	 */
	public Double getX() {
		return (Double) this.getStateHelper().eval(PropertyKeys.x, 0.0d);
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(Double x) {
		this.getStateHelper().put(PropertyKeys.x, x);
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return (Double) this.getStateHelper().eval(PropertyKeys.y, 0.0d);
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(Double y) {
		this.getStateHelper().put(PropertyKeys.y, y);
	}

	/**
	 * @return the zoom
	 */
	public Integer getZoom() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.zoom, 10);
	}

	/**
	 * @param zoom
	 *            the zoom to set
	 */
	public void setZoom(Integer zoom) {
		this.getStateHelper().put(PropertyKeys.zoom, zoom);
	}

	/**
	 * values in the geo-json format
	 * 
	 * @return the value
	 */
	@SuppressWarnings("unchecked")
	public List<Feature> getValue() {
		return (List<Feature>) this.getStateHelper().eval(PropertyKeys.value,
				Collections.emptyList());
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(List<Feature> value) {
		this.getStateHelper().put(PropertyKeys.value, value);
	}

	/**
	 * @return the interactionType
	 */
	public String getInteractionType() {
		return (String) this.getStateHelper().eval(
				PropertyKeys.interactionType, "None");
	}

	/**
	 * 
	 * @param interactionType
	 *            the interactionType to set
	 */
	public void setInteractionType(String interactionType) {
		this.getStateHelper()
				.put(PropertyKeys.interactionType, interactionType);
	}

	/**
	 * @return the openlayersJs
	 */
	public String getOpenlayersJs() {
		return (String) this.getStateHelper().eval(PropertyKeys.openlayersJs,
				"http://openlayers.org/en/v3.10.1/build/ol.js");
	}

	/**
	 * @param openlayersJs
	 *            the openlayersJs to set
	 */
	public void setOpenlayersJs(String openlayersJs) {
		this.getStateHelper().put(PropertyKeys.openlayersJs, openlayersJs);
	}

	/**
	 * @return the showZoomSlider
	 */
	public Boolean isShowZoomSlider() {
		return (Boolean) this.getStateHelper().eval(
				PropertyKeys.showZoomSlider, false);
	}

	/**
	 * 
	 * @param showZoomSlider
	 *            the showZoomSlider to set
	 */
	public void setShowZoomSlider(boolean showZoomSlider) {
		this.getStateHelper().put(PropertyKeys.showZoomSlider, showZoomSlider);
	}

	/**
	 * @return the openlayersCss
	 */
	public Object getOpenlayersCss() {
		return (String) this.getStateHelper().eval(PropertyKeys.openlayersCss,
				"http://openlayers.org/en/v3.10.1/css/ol.css");
	}

	/**
	 * @param openlayersCss
	 *            the openlayersCss to set
	 */
	public void setOpenlayersCss(String openlayersCss) {
		this.getStateHelper().put(PropertyKeys.openlayersCss, openlayersCss);
	}
}
