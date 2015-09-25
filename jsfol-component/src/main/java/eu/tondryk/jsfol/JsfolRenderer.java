/**
 * 
 */
package eu.tondryk.jsfol;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import eu.tondryk.jsfol.converter.JsfolFormatter;
import eu.tondryk.jsfol.converter.JsfolParser;

/**
 * @author ptondryk
 *
 */
@FacesRenderer(componentFamily = JsfolComponent.COMPONENT_FAMILY, rendererType = JsfolRenderer.RENDERER_TYPE)
public class JsfolRenderer extends Renderer {

	/**
	 * 
	 */
	public static final String RENDERER_TYPE = "eu.tondryk.jsfol.JsfolRenderer";

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (component instanceof JsfolComponent) {
			JsfolComponent jsfolComponent = (JsfolComponent) component;

			if (jsfolComponent.getId() != null
					&& jsfolComponent.getWidth() != null
					&& jsfolComponent.getHeight() != null
					&& jsfolComponent.getX() != null
					&& jsfolComponent.getY() != null
					&& jsfolComponent.getZoom() != null) {

				ResponseWriter writer = context.getResponseWriter();

				// add openlayers library script
				this.addOpenlayers(writer, jsfolComponent);

				// add main map div
				this.addMapDiv(writer, jsfolComponent);

				// start js script
				writer.startElement("script", null);
				String jsVarName = "jsfolMap_" + jsfolComponent.getId();

				// load map into div
				this.initOlMap(writer, jsfolComponent, jsVarName);

				Map<String, List<ClientBehavior>> behaviors = jsfolComponent
						.getClientBehaviors();
				if (behaviors.containsKey("newfeature")) {
					// add ajax-call on "newfeature" event
					this.addNewfeatureEvent(writer, jsfolComponent, behaviors,
							jsVarName, context);
				}

				// load features from value parameter (should be geojson-string)
				this.addFeatures(writer, jsVarName, jsfolComponent);

				// set the type of interaction (if any)
				this.initInteraction(writer, jsVarName, jsfolComponent);

				// add the controls (if any)
				this.initControl(writer, jsVarName, jsfolComponent);

				writer.endElement("script");

			} else {
				throw new IOException(
						"Attributes 'id', 'width', 'height', 'x', 'y' and 'zoom' are required on the element 'jsfol'!");
			}
		}
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		if (component instanceof JsfolComponent) {
			JsfolComponent jsfolComponent = (JsfolComponent) component;
			// extract the new value from request parameters
			Map<String, String> requestMap = context.getExternalContext()
					.getRequestParameterMap();
			jsfolComponent.setValue(JsfolParser.parseGeoJson(requestMap
					.get("jsfol." + jsfolComponent.getId() + ".features")));
		}
	}

	/**
	 * This method adds the openlayers-library and style to the web-site.
	 * 
	 * @param writer
	 * @param jsfolComponent
	 * @throws IOException
	 */
	private void addOpenlayers(ResponseWriter writer,
			JsfolComponent jsfolComponent) throws IOException {
		writer.startElement("script", null);
		writer.writeAttribute("type", "text/javascript", null);
		writer.writeAttribute("src", jsfolComponent.getOpenlayersJs(), null);
		writer.endElement("script");
		writer.startElement("link", null);
		writer.writeAttribute("rel", "stylesheet", null);
		writer.writeAttribute("href", jsfolComponent.getOpenlayersCss(), null);
		writer.writeAttribute("type", "text/css", null);
		writer.endElement("link");
	}

	/**
	 * This method adds a div with given id and width/height. Map will be
	 * displayed on this div.
	 * 
	 * @param writer
	 * @param jsfolComponent
	 */
	private void addMapDiv(ResponseWriter writer, JsfolComponent jsfolComponent)
			throws IOException {
		writer.startElement("div", null);
		writer.writeAttribute("id", jsfolComponent.getId(), null);
		writer.writeAttribute("style", "width:" + jsfolComponent.getWidth()
				+ "px;height:" + jsfolComponent.getHeight() + "px;", null);
		writer.endElement("div");
	}

	/**
	 * This method initializes the openlayers-map object (<b>jsVarName</b> is
	 * the map-handler-variable).
	 * 
	 * @param writer
	 * @param jsfolComponent
	 * @param jsVarName
	 * @throws IOException
	 */
	private void initOlMap(ResponseWriter writer,
			JsfolComponent jsfolComponent, String jsVarName) throws IOException {
		writer.write("var " + jsVarName + " = new jsfol.Map();");
		writer.write(jsVarName + ".initMap('" + jsfolComponent.getId() + "',"
				+ jsfolComponent.getX() + "," + jsfolComponent.getY() + ","
				+ jsfolComponent.getZoom() + ");");
	}

	/**
	 * This method adds to the map all features from the <i>value</i> attribute
	 * (should be a geo-json-string).
	 * 
	 * @param writer
	 * @param jsVarName
	 * @param jsfolComponent
	 * @throws IOException
	 */
	private void addFeatures(ResponseWriter writer, String jsVarName,
			JsfolComponent jsfolComponent) throws IOException {
		if (jsfolComponent.getValue() != null
				&& !jsfolComponent.getValue().isEmpty()) {

			String features = "";
			String featuresArray = "";

			// prepare unique name for feature variables
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String namePrefix = "feature" + sdf.format(new Date());
			Random random = new Random();

			for (Feature feature : jsfolComponent.getValue()) {
				String name = namePrefix + Math.abs(random.nextLong());

				// prepare feature definition
				features += "var " + name + " = "
						+ JsfolFormatter.convertFeature(feature) + ";";
				features += name + ".setStyle("
						+ JsfolFormatter.convertStyle(feature.getStyle())
						+ ");";

				// add name to features array
				if (!featuresArray.isEmpty()) {
					featuresArray += ",";
				}
				featuresArray += name;
				;
			}

			writer.write(features + jsVarName + ".addFeatures(" + "["
					+ featuresArray + "]" + ");");
		}

	}

	/**
	 * This method initializes the interaction (defined in the attribute
	 * <i>interactionType</i>).
	 * 
	 * @param writer
	 * @param jsVarName
	 * @param jsfolComponent
	 * @throws IOException
	 */
	private void initInteraction(ResponseWriter writer, String jsVarName,
			JsfolComponent jsfolComponent) throws IOException {
		if (jsfolComponent.getInteractionType() != null
				&& !jsfolComponent.getInteractionType().isEmpty()
				&& !"None"
						.equalsIgnoreCase(jsfolComponent.getInteractionType())) {
			writer.write(jsVarName + ".initDraw('"
					+ jsfolComponent.getInteractionType() + "');");
		}
	}

	/**
	 * This method assigns the user-defined js-function <i>newfeature</i> to the
	 * event <i>newfeature</i>.
	 * 
	 * @param writer
	 * @param jsfolComponent
	 * @param behaviors
	 * @param jsVarName
	 * @param context
	 * @throws IOException
	 */
	private void addNewfeatureEvent(ResponseWriter writer,
			JsfolComponent jsfolComponent,
			Map<String, List<ClientBehavior>> behaviors, String jsVarName,
			FacesContext context) throws IOException {
		// create client behavior
		ClientBehaviorContext behaviorContext = ClientBehaviorContext
				.createClientBehaviorContext(context, jsfolComponent,
						"newfeature", jsfolComponent.getId(), null);

		// assign ajax-function to the map-object
		String newfeatureFunction = behaviors.get("newfeature").get(0)
				.getScript(behaviorContext)
				.replaceAll("@this", jsfolComponent.getClientId(context));
		writer.write(jsVarName + ".addNewfeatureFunction(function() {"
				+ newfeatureFunction + ";return false;});");
	}

	/**
	 * This method initialize the controls (like zoom-slider).
	 * 
	 * @param writer
	 * @param jsVarName
	 * @param jsfolComponent
	 * @throws IOException
	 */
	private void initControl(ResponseWriter writer, String jsVarName,
			JsfolComponent jsfolComponent) throws IOException {
		if (jsfolComponent.isShowZoomSlider()) {
			writer.write(jsVarName + ".addZoomSlider();");
		}

	}

}
