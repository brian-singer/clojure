
dataset2 = { 
	"name": "", "value" : 11,
	"children": [ 
	{ "name": "data1", "value": 38 },
	{ "name": "data2", "value": 111 },
	{ "name": "data3", "value": 11 },
	{ "name": "data4", "value": 38 }
	]
}

var hideTooltip = function() {
	$('div.tooltip').remove();
};

var showTooltip = function(d) {
	var text = d.name + " (" + d.value + '\u20AC' + ")";
	$('div.tooltip').remove();
	$('<div class="tooltip">' + text + '</div>')
	.appendTo('body');
	$('div.tooltip').css({top: d.y + d.dy / 2, left: d.x + d.dx / 2.5});
};

var createMapTiles = function (selection){
	var color = d3.scale.category20();

	selection.style("position", "absolute")
	.style("left", function (d) {return d.x + "px";})
	.style("top", function (d) {return d.y + "px" ;})
	.style("width", function (d) {return d.dx + "px";})
	.style("height", function (d) {return d.dy + "px";})
	.attr('class','maptile')
	.style("background", function (d) {return color(d.name);})
	.on("mouseover", showTooltip)
	.on("mouseout", hideTooltip);
}

var createMap = function (dataset){
	

	var treemap = d3.layout.treemap().size([1024,768]).nodes(dataset);

	var tiles = d3.select("#mainmap").selectAll("div")
	.data(treemap)
	.enter()
	.append("div");

	createMapTiles(tiles);
	
}

var reCreateMap = function (dataset){
	var color = d3.scale.category20();

	var treemap = d3.layout.treemap().size([1024,768]).nodes(dataset);

	var tiles = d3.select("#mainmap").selectAll("div")
	.data(treemap);

	createMapTiles(tiles);
}

