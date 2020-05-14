// 数据说明提示框——数据说明、现有确诊、无症状、现有疑似、更新说明
var className = "tooltip-box";

var getDocID = function(id){
	return document.getElementById(id);
}

var parentdiv = getDocID("viewport");

function showTooltip(obj,id,html,width,height){
	if (getDocID(id) == null){
		var tooltipBox;
		tooltipBox = document.createElement("viewport");
		tooltipBox.className = className;
		tooltipBox.id = id;
		tooltipBox.innerHTML = html;
		
		obj.appendChild(tooltipBox);
		// 提示框大小
		tooltipBox.style.width = width?width+"px":"auto";
		tooltipBox.style.height = height?height+"px":"auto";
		// 提示框位置
		tooltipBox.style.position = "absolute";
		tooltipBox.style.display = "block";
		
		var left = obj.offsetLeft+150;
		var top = obj.offsetTop+20;
		
		tooltipBox.style.left = left+"px";
		tooltipBox.style.top = top+"px";
		
		//延时
		obj.addEventListener("mouseout",function(){ 
				setTimeout (function(){
					getDocID(id).style.display = "none";
				},250);
		});

	}else{
		getDocID(id).style.display = "block";
	}
}
parentdiv.addEventListener("mouseover",function(e){
	var target = e.target;
	 if (target.className == "tooltip"){
		 var _html;
		 var _id;
		 var _width;
		 switch(target.id){
			case "Current_diagnosis":
				_id = "Curr_diag";
				_html = "什么是“现有确诊”数？1、“现有确诊”口径取自国家卫健委每日公布的“现有确诊病例数”，该数值反映了当前正在治疗中的确诊人数。2、实时更新时，我们会用“现有确诊人数=累计确诊人数-累计治愈人数-累计死亡人数”计算得到。";
				_width = 200;
				break;
			case "notice_text":
				_id = "no_text";
				_html = "4月28日0——24时，31个省（自治区、直辖市）和新疆生产建设兵团报告新增确诊病例22例，其中21例为境外输入病例，1例为本土病例（广东1例）；无新增死亡病例；新增疑似病例2例，为境外输入疑似病例（内蒙古1例，上海1例）。累计收到港澳台地区通报确诊病例1511例。其中香港特别行政区1037例（出院811例，死亡4例），澳门特别行政区45例（出院33例），台湾地区429例（出院307例，死亡6例）。";
				_width = 200;
				break;	
			case "data_sp":
				_id = "da_sp";
				_html = "1、数据来源：腾讯实时公开疫情数据";
				_width = 200;
				break;
			case "ljqz":
				_id = "da_xyqz";
				_html = "1、数据来源：腾讯实时公开疫情数据";
				_width = 200;
				break;
			//添加其他点击事件
		 }
		 showTooltip(target,_id,_html,_width);
	 }
});

// var Curr_diag = getDocID("Current_diagnosis"); //现有确诊

// Curr_diag.onmousemove = function(){
// 	showTooltip(this,"Curr_diag","什么是“现有确诊”数？1、“现有确诊”口径取自国家卫健委每日公布的“现有确诊病例数”，该数值反映了当前正在治疗中的确诊人数。2、实时更新时，我们会用“现有确诊人数=累计确诊人数-累计治愈人数-累计死亡人数”计算得到。",200);
// }