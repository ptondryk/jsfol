/**
 * 
 */
package eu.tondryk.jsfol.test.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import eu.tondryk.jsfol.Feature;
import eu.tondryk.jsfol.geom.Point;

/**
 * @author ptondryk
 *
 */
@ViewScoped
@ManagedBean
public class FakeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3120599902693128937L;

	/**
	 * 
	 */
	private List<Feature> features = new ArrayList<>();

	/**
	 * this method adds a new test feature to the features list
	 */
	public void addFeature() {
		Point point = new Point(13.271494d, 52.6047356d);
		Feature newFeature = new Feature(point, null);
		this.features.add(newFeature);
	}

	/**
	 * 
	 * @return
	 */
	public int getWidth() {
		return 200;
	}

	/**
	 * 
	 * @return
	 */
	public int getHeight() {
		return 200;
	}

	/**
	 * 
	 * @return
	 */
	public int getZoom() {
		return 9;
	}

	/**
	 * 
	 * @return
	 */
	public double getX() {
		return 13.4094d;
	}

	/**
	 * 
	 * @return
	 */
	public double getY() {
		return 52.5208d;
	}

	/**
	 * 
	 * @return
	 */
	public String getInteractionType() {
		return "Polygon";
	}

	/**
	 * 
	 * @return
	 */
	public List<Feature> getValue() {
		return this.features;
	}

	/**
	 * 
	 * @param features
	 */
	public void setValue(List<Feature> features) {
		this.features = features;
	}
}
