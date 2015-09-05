jsfol = (function() {

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
			this.map.addLayer(this.featuresLayer);
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
		 * @param drawType
		 *            None, Point, LineString, Polygon
		 */
		initInteraction : function(drawType) {
			var draw = new ol.interaction.Draw({
				features : this.features,
				type : (drawType)
			});
			this.map.addInteraction(draw);
		}
	}

	return {
		Map : Map
	};

})();
