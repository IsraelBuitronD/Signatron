var COLORS = [["red", "#ff0000"], ["orange", "#ff8800"], ["green", "#008000"],
		["blue", "#000080"], ["purple", "#800080"]];
var options = {};
var lineCounter_ = 0;
var shapeCounter_ = 0;
var markerCounter_ = 0;
var colorIndex_ = 0;
var featureTable_;
var map3;

function select(buttonId) {
	document.getElementById("hand_b").className = "unselected";
	document.getElementById("shape_b").className = "unselected";
	document.getElementById("delete_b").className = "unselected";
	document.getElementById(buttonId).className = "selected";
}

function stopEditing() {
	select("hand_b");
}

function getColor(named) {
	return COLORS[(colorIndex_++) % COLORS.length][named ? 0 : 1];
}

function getIcon(color) {
	var icon = new GIcon();
	icon.image = "http://google.com/mapfiles/ms/micons/" + color + ".png";
	icon.iconSize = new GSize(32, 32);
	icon.iconAnchor = new GPoint(15, 32);
	return icon;
}

function startShape() {
	select("shape_b");
	var color = getColor(false);
	var polygon = new GPolygon([], color, 2, 0.7, color, 0.2);
	startDrawing(polygon, "Area " + (++shapeCounter_), function() {
				var cell = this;
				var area = polygon.getArea();
				cell.innerHTML = (Math.round(area / 10000) / 100)
						+ "km<sup>2</sup>";
			}, color);
}
function deleteShape() {
	select("delete_b");
	map3.removeOverlay();
}

function startLine() {
	select("line_b");
	var color = getColor(false);
	var line = new GPolyline([], color);
	startDrawing(line, "Line " + (++lineCounter_), function() {
				var cell = this;
				var len = line.getLength();
				cell.innerHTML = (Math.round(len / 10) / 100) + "km";
			}, color);
}

function addFeatureEntry(name, color) {
	currentRow_ = document.createElement("tr");
	var colorCell = document.createElement("td");
	currentRow_.appendChild(colorCell);
	colorCell.style.backgroundColor = color;
	colorCell.style.width = "1em";
	var nameCell = document.createElement("td");
	currentRow_.appendChild(nameCell);
	nameCell.innerHTML = name;
	var descriptionCell = document.createElement("td");
	currentRow_.appendChild(descriptionCell);
	featureTable_.appendChild(currentRow_);
	return {
		desc : descriptionCell,
		color : colorCell
	};

}

function startDrawing(poly, name, onUpdate, color) {
	map3.addOverlay(poly);
	poly.enableDrawing(options);
	poly.enableEditing({
				onEvent : "mouseover"
			});
	poly.disableEditing({
				onEvent : "mouseout"
			});
	GEvent.addListener(poly, "endline", function() {
				select("hand_b");
				var cells = addFeatureEntry(name, color);
				GEvent.bind(poly, "lineupdated", cells.desc, onUpdate);
				GEvent.addListener(poly, "click", function(latlng, index) {
							if (typeof index == "number") {
								poly.deleteVertex(index);
							} else {
								var newColor = getColor(false);
								cells.color.style.backgroundColor = newColor
								poly.setStrokeStyle({
											color : newColor,
											weight : 4
										});
							}
						});
			});
}

function placeMarker() {
	select("placemark_b");
	var listener = GEvent.addListener(map3, "click", function(overlay, latlng) {
				if (latlng) {
					select("hand_b");
					GEvent.removeListener(listener);
					var color = getColor(true);
					var marker = new GMarker(latlng, {
								icon : getIcon(color),
								draggable : true
							});
					map3.addOverlay(marker);
					var cells = addFeatureEntry("Placemark "
									+ (++markerCounter_), color);
					updateMarker(marker, cells);
					GEvent.addListener(marker, "dragend", function() {
								updateMarker(marker, cells);
							});
					GEvent.addListener(marker, "click", function() {
								updateMarker(marker, cells, true);
							});
				}
			});
}

function updateMarker(marker, cells, opt_changeColor) {
	if (opt_changeColor) {
		var color = getColor(true);
		marker.setImage(getIcon(color).image);
		cells.color.style.backgroundColor = color;
	}
	var latlng = marker.getPoint();
	cells.desc.innerHTML = "(" + Math.round(latlng.y * 100) / 100 + ", "
			+ Math.round(latlng.x * 100) / 100 + ")";
}

function initialize() {
	if (GBrowserIsCompatible()) {
		map3 = new GMap2(document.getElementById("map3"));
		map3.setCenter(new GLatLng(24.126702,-101.601562), 5);
		map3.addControl(new GSmallMapControl());
		map3.addControl(new GMapTypeControl());
		map3.clearOverlays();
		featureTable_ = document.getElementById("featuretbody");
		select("hand_b");
	}
}
