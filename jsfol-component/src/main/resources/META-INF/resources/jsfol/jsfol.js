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
				if (input == null) {
					var input = document.createElement("input");
					input.setAttribute("id", "jsfol." + divName + ".features");
					input
							.setAttribute("name", "jsfol." + divName
									+ ".features");
					input.setAttribute("type", "hidden");
					input.setAttribute("value", ((new ol.format.GeoJSON())
							.writeFeatures(this.features.getArray())));
					input.setAttribute("autocomplete", "off");
					document.getElementById(divName).appendChild(input);
				} else {
					input.setAttribute("value", ((new ol.format.GeoJSON())
							.writeFeatures(this.features.getArray())));
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
		loadFeaturesFromGeoJson : function(geoJson) {
			this.featuresSource.addFeatures((new ol.format.GeoJSON())
					.readFeatures(geoJson));
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

		}

	}

	return {
		Map : Map,
		startDrawMode : startDrawMode,
		startEditMode : startEditMode,
		endInteraction : endInteraction
	};

})();
