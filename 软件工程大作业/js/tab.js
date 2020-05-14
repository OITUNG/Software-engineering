function $(id){
	return typeof id=="string"?document.getElementById(id):id;
}

window.onload = function(){
	var titleName = $("map_rota-title").getElementsByTagName("li");
	var mapContent = $("map_rota-content").getElementsByTagName("div");
	
	if (titleName.length != mapContent.length){
		return;
	}
	for (var i=0; i<titleName.length; i++){
		titleName[i].id = i;
		titleName[i].onmouseover = function(){
			for (var j=0; j<titleName.length;j++){
				titleName[j].className = "";
				mapContent[j].style.display = "none";
			}
			this.className = "select";
			mapContent[this.id].style.display = "block";
		}
	}
}