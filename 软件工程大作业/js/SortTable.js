
    var tag=1;
    function sortNumberAS(a, b)
    {
        return a - b    
    }
    function sortNumberDesc(a, b)
    {
        return b - a
    }

    function SortTable(obj){
        var td0s=document.getElementsByName("td0");
        var td1s=document.getElementsByName("td1");
        var td2s=document.getElementsByName("td2");
        var td3s=document.getElementsByName("td3");
        var td4s=document.getElementsByName("td4");
		var td5s=document.getElementsByName("td5");
        var tdArray0=[];
        var tdArray1=[];
        var tdArray2=[];
        var tdArray3=[];
        var tdArray4=[];
		var tdArray5=[];
        for(var i=0;i<td0s.length;i++){
            tdArray0.push(td0s[i].innerHTML);
        }
        for(var i=0;i<td1s.length;i++){
            tdArray1.push(parseInt(td1s[i].innerHTML));
        }
        for(var i=0;i<td2s.length;i++){
            tdArray2.push(parseInt(td2s[i].innerHTML));
        }
        for(var i=0;i<td3s.length;i++){
            tdArray3.push(parseInt(td3s[i].innerHTML));
        }
        for(var i=0;i<td4s.length;i++){
            tdArray4.push(parseInt(td4s[i].innerHTML));
        }
		for(var i=0;i<td5s.length;i++){
		    tdArray5.push(parseInt(td5s[i].innerHTML));
		}
        var tds=document.getElementsByName("td"+obj.id.substr(2,1));
        var columnArray=[];
        for(var i=0;i<tds.length;i++){
            columnArray.push(parseInt(tds[i].innerHTML));
        }
        var orginArray=[];
        for(var i=0;i<columnArray.length;i++){
            orginArray.push(columnArray[i]);
        }
        if(obj.className=="as"){
            columnArray.sort(sortNumberAS);               //排序后的新值
            obj.className="desc";
        }else{
            columnArray.sort(sortNumberDesc);               //排序后的新值
            obj.className="as";
        }


        for(var i=0;i<columnArray.length;i++){
            for(var j=0;j<orginArray.length;j++){
                if(orginArray[j]==columnArray[i]){
                    document.getElementsByName("td0")[i].innerHTML=tdArray0[j];
                    document.getElementsByName("td1")[i].innerHTML=tdArray1[j];
                    document.getElementsByName("td2")[i].innerHTML=tdArray2[j];
                    document.getElementsByName("td3")[i].innerHTML=tdArray3[j];
                    document.getElementsByName("td4")[i].innerHTML=tdArray4[j];
                    document.getElementsByName("td5")[i].innerHTML=tdArray5[j];
                    orginArray[j]=null;
                    break;
                }
            }
        }
    }

