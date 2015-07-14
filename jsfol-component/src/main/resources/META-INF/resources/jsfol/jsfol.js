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
function initMap(divName, x, y, z) {
	var map = new ol.Map({
		target : divName,
		layers : [ new ol.layer.Tile({
			source : new ol.source.OSM(),
			name : 'OpenStreetMap'
		}) ],
		view : new ol.View({
			center : ol.proj.transform([ x, y ], 'EPSG:4326', 'EPSG:3857'),
			zoom : z
		})
	});
}