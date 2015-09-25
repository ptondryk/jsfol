jsfol = (function() {

	/**
	 * (private field) contains all jsfol-map object mapped by their ids
	 */
	var mapsList = {};

	/**
	 * this function adds a new map to the list of all maps
	 */
	var addNewMap = function(divName, newMap) {
		mapsList[divName] = newMap;
	};

	/**
	 * this function turn on the draw-mode
	 * 
	 * @param divName
	 *            id (name) of the map object that should be switched into
	 *            draw-mode
	 * @param drawType
	 */
	var startDrawMode = function(divName, drawType) {
		mapsList[divName].initDraw(drawType);
	};

	/**
	 * this function turn on the modify-mode
	 * 
	 * @param divName
	 *            id (name) of the map object that should be switched into
	 *            modify-mode
	 */
	var startEditMode = function(divName) {
		mapsList[divName].initModify();
	};

	/**
	 * this function turn off the interaction-mode
	 * 
	 * @param divName
	 *            id (name) of the map object for that the interaction should be
	 *            tunred off
	 */
	var endInteraction = function(divName) {
		mapsList[divName].endInteraction();
	};

	/** Class Map */
	var Map = function() {
		this.map;
		this.features = new ol.Collection();
		this.featuresSource = new ol.source.Vector({
			features : this.features
		});
		this.featuresLayer = new ol.layer.Vector({
			source : this.featuresSource
		});
	};

	Map.prototype = {
		/**
		 * This function initializes a map within a div with given <b>divName</b>.
		 * 
		 * @param divName
		 * @param x
		 *            longitude
		 * @param y
		 *            latitude
		 * @param z
		 *            zoom
		 */
		initMap : function(divName, x, y, z) {
			this.map = new ol.Map({
				target : divName,
				layers : [ new ol.layer.Tile({
					source : new ol.source.OSM(),
					name : 'OpenStreetMap'
				}) ],
				view : new ol.View({
					center : ol.proj.transform([ x, y ], 'EPSG:4326',
							'EPSG:3857'),
					zoom : z
				})
			});
			addNewMap(divName, this);

			// add features to the map
			this.map.addLayer(this.featuresLayer);
			this.featuresSource.on('addfeature', function(evt) {

				var input = document.getElementById("jsfol." + divName
						+ ".features");
				if (evt.feature.getStyle() != null) {
					evt.feature.set("style", this.formatStyle(evt.feature
							.getStyle()));
				}

				if (input == null) {
					var input = document.createElement("input");
					input.setAttribute("id", "jsfol." + divName + ".features");
					input
							.setAttribute("name", "jsfol." + divName
									+ ".features");
					input.setAttribute("type", "hidden");
					input.setAttribute("value", (new ol.format.GeoJSON())
							.writeFeatures(this.features.getArray()));
					input.setAttribute("autocomplete", "off");
					document.getElementById(divName).appendChild(input);
				} else {
					input.setAttribute("value", (new ol.format.GeoJSON())
							.writeFeatures(this.features.getArray()));
				}

				if (this.newfeatureFunction != null) {
					this.newfeatureFunction();
				}

			}, this);

			// init interactions
			this.drawPoint = new ol.interaction.Draw({
				features : this.features,
				type : 'Point'
			});
			this.drawPolygon = new ol.interaction.Draw({
				features : this.features,
				type : 'Polygon'
			});
			this.drawLine = new ol.interaction.Draw({
				features : this.features,
				type : 'LineString'
			});
			this.modify = new ol.interaction.Modify({
				features : this.features,
				deleteCondition : function(event) {
					return ol.events.condition.shiftKeyOnly(event)
							&& ol.events.condition.singleClick(event);
				}
			});
		},
		/**
		 * This function loads features from given <b>geoJson</b>-object and
		 * presents them on this map.
		 * 
		 * @param geoJson
		 */
		addFeatures : function(featuresArray) {
			this.featuresSource.addFeatures(featuresArray);
		},
		/**
		 * initialize the draw-interaction-mode
		 * 
		 * @param drawType
		 *            None, Point, LineString, Polygon
		 */
		initDraw : function(drawType) {
			this.endInteraction();
			switch (drawType) {
			case 'Polygon':
				this.map.addInteraction(this.drawPolygon);
				break;
			case 'LineString':
				this.map.addInteraction(this.drawLine);
				break;
			case 'Point':
				this.map.addInteraction(this.drawPoint);
			}
		},
		/**
		 * initialize the modify-interaction-mode
		 */
		initModify : function() {
			this.endInteraction();
			this.map.addInteraction(this.modify);
		},
		/**
		 * 
		 */
		endInteraction : function() {
			this.map.removeInteraction(this.drawPolygon);
			this.map.removeInteraction(this.drawPoint);
			this.map.removeInteraction(this.drawLine);
			this.map.removeInteraction(this.modify);
		},
		/**
		 * @param newfeatureFunction
		 *            function that will be called when new feature is added
		 */
		addNewfeatureFunction : function(newfeatureFunction) {
			this.newfeatureFunction = newfeatureFunction;
		},
		/**
		 * this function adds a zoom-slider to the map
		 */
		addZoomSlider : function() {
			this.map.addControl(new ol.control.ZoomSlider());
		},
		/**
		 * this function creates a readable string from ol.style.Style object
		 */
		formatStyle : function(style) {
			var result = {};
			if (style.getZIndex() != null) {
				result.zIndex = style.getZIndex();
			}
			if (style.getFill() != null) {
				result.fill = this.formatFill(style.getFill());
			}
			if (style.getStroke() != null) {
				result.stroke = this.formatStroke(style.getStroke());
			}
			if (style.getText() != null) {
				result.text = this.formatText(style.getText());
			}
			if (style.getImage() != null) {
				if (style.getImage() instanceof ol.style.Icon) {
					result.image = this.formatIcon(style.getImage());
					result.image.type = 'Icon';
				} else if (style.getImage() instanceof ol.style.Circle) {
					result.image = this.formatCircle(style.getImage());
					result.image.type = 'Circle';
				}
			}
			return result;
		},
		/**
		 * this function creates a readable object from ol.style.Fill object
		 */
		formatFill : function(fill) {
			var result = {};
			if (fill.getColor() != null) {
				result.color = fill.getColor();
			}
			return result;
		},
		/**
		 * this function creates a readable object from ol.style.Stroke object
		 */
		formatStroke : function(stroke) {
			var result = {};
			if (stroke.getColor() != null) {
				result.color = stroke.getColor();
			}
			if (stroke.getLineCap() != null) {
				result.lineCap = stroke.getLineCap();
			}
			if (stroke.getLineDash() != null) {
				result.lineDash = stroke.getLineDash();
			}
			if (stroke.getLineJoin() != null) {
				result.lineJoin = stroke.getLineJoin();
			}
			if (stroke.getMiterLimit() != null) {
				result.miterLimit = stroke.getMiterLimit();
			}
			if (stroke.getWidth() != null) {
				result.width = stroke.getWidth();
			}
			return result;
		},
		/**
		 * this function creates a readable object from ol.style.Text object
		 */
		formatText : function(text) {
			var result = {};
			if (text.getFill() != null) {
				result.fill = this.formatFill(text.getFill());
			}
			if (text.getFont() != null) {
				result.font = text.getFont();
			}
			if (text.getOffsetX() != null) {
				result.offsetX = text.getOffsetX();
			}
			if (text.getOffsetY() != null) {
				result.offsetY = text.getOffsetY();
			}
			if (text.getRotation() != null) {
				result.rotation = text.getRotation();
			}
			if (text.getScale() != null) {
				result.scale = text.getScale();
			}
			if (text.getStroke() != null) {
				result.stroke = this.formatStroke(text.getStroke());
			}
			if (text.getText() != null) {
				result.text = text.getText();
			}
			if (text.getTextAlign() != null) {
				result.textAlign = text.getTextAlign();
			}
			if (text.getTextBaseline() != null) {
				result.textBaseline = text.getTextBaseline();
			}
			return result;
		},
		/**
		 * this function creates a readable object from ol.style.Icon object
		 */
		formatIcon : function(icon) {
			var result = {};
			if (icon.getAnchor() != null) {
				result.anchor = icon.getAnchor();
			}
			if (icon.getOpacity() != null) {
				result.opacity = icon.getOpacity();
			}
			if (icon.getOrigin() != null) {
				result.origin = icon.getOrigin();
			}
			if (icon.getRotateWithView() != null) {
				result.rotateWithView = icon.getRotateWithView();
			}
			if (icon.getRotation() != null) {
				result.rotation = icon.getRotation();
			}
			if (icon.getScale() != null) {
				result.scale = icon.getScale();
			}
			if (icon.getSize() != null) {
				result.size = icon.getSize();
			}
			if (icon.getSnapToPixel() != null) {
				result.snapToPixel = icon.getSnapToPixel();
			}
			if (icon.getSrc() != null) {
				result.src = icon.getSrc();
			}
			// TODO test "icon.getImage(pixelRatio)"
			return result;
		},
		/**
		 * this function creates a readable object from ol.style.Circle object
		 */
		formatCircle : function(circle) {
			var result = {};
			if (circle.getFill() != null) {
				result.fill = this.formatFill(circle.getFill());
			}
			if (circle.getOpacity() != null) {
				result.opacity = circle.getOpacity();
			}
			if (circle.getRadius() != null) {
				result.radius = circle.getRadius();
			}
			if (circle.getRotateWithView() != null) {
				result.rotationWithView = circle.getRotateWithView();
			}
			if (circle.getRotation() != null) {
				result.rotation = circle.getRotation();
			}
			if (circle.getScale() != null) {
				result.scale = circle.getScale();
			}
			if (circle.getSnapToPixel() != null) {
				result.snapToPixel = circle.getSnapToPixel();
			}
			if (circle.getStroke() != null) {
				result.stroke = this.formatStroke(circle.getStroke());
			}
			// TODO test "circle.getImage(pixelRatio)"
			return result;
		}

	}

	return {
		Map : Map,
		startDrawMode : startDrawMode,
		startEditMode : startEditMode,
		endInteraction : endInteraction
	};

})();
