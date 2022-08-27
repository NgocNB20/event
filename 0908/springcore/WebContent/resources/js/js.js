

 var dataMembers=[];
 var dataDepartments=[];
function displayProperty(){
    var i = document.querySelector('i');
    i.addEventListener('click', function(){
  var displayP = document.getElementById('displayP');
  if(displayP.style.display == 'none'){
    displayP.style.display = 'block';
  }
  else{
    displayP.style.display = 'none';
  }
});
}

function btn_disabled(){
    var optionType = document.getElementById("optionType");
    var hidden = document.getElementById("hidden");
    if(optionType.selectedIndex == 0){
        hidden.style.display  = "none";
    }else if( optionType.selectedIndex == 1){
        hidden.style.display  = "block";
    }
}


function tableGroup(){
	
    var start = 0;
    let bophan;
    bophan = document.getElementById("department").value;
    var table = document.getElementById("listtable");
    if(!checkExits(dataDepartments,bophan)){
    	  var row = table.insertRow(start);
    	  var cell = row.insertCell(0);
    	  var cell1 = row.insertCell(1);
    	  cell.innerHTML = bophan;
    	  cell1.innerHTML = "   " + `<i onclick="deleteRow(this)" style="cursor:pointer" department=${bophan} class="fa-solid fa-trash-can"></i>`;
    	  dataDepartments.push(bophan);
		  document.getElementById("txt-departments").value=convertArrToString(dataDepartments)+"";
    }else{
    	alert("Department is exits");
    	
    }
    }
    
  




function tableMember(){
	
 
    let department, member;
    department = document.getElementById("department").value;
    member =  document.getElementById("member").value;
    var table = document.getElementById("listtable");
  
    if(!checkExits(dataMembers,member)){
    	var row = table.insertRow(0);
    	var cell = row.insertCell(0);
    	var cell1 = row.insertCell(1);
        cell.innerHTML = department + " - " + member;
        cell1.innerHTML = "   " + `<i onclick="deleteRow(this)" style="cursor:pointer" member=${member}  class="fa-solid fa-trash-can"></i>`;
        dataMembers.push(member);
		document.getElementById("txt-members").value=convertArrToString(dataMembers)+"";  
    }else{
    	alert("User is exits");
    }
 

}

function deleteArray(input){

 	let memberR= input.getAttribute("member");
 	let departmentR = input.getAttribute("department");

	for(let i=0 ;i<dataMembers.length;i++){
		if(dataMembers[i]===memberR){
			MemberD=dataMembers[i]
			dataMembers.splice(i, 1); 
					
	}
}

	for(let i=0 ;i<dataDepartments.length;i++){
		if(dataDepartments[i]===departmentR){
			departD=dataDepartments[i];
			dataDepartments.splice(i, 1); 
		}
	}
		document.getElementById("txt-departments").value=convertArrToString(dataDepartments)+"";
		document.getElementById("txt-members").value=convertArrToString(dataMembers)+"";   
		 
}
 
 

function convertArrToString(arr){
	let str="0";
	if(arr.length==1){
		return arr[0];
	}
     	for(let i=0 ;i<arr.length;i++){
		if(i==0){
			str+=arr[i];
		}else{
			str+=","+arr[i]
		}
	} 
	return str;
}




function deleteRow(input){
    var table = document.getElementById("listtable");
	deleteArray(input)
    table.deleteRow(input.parentNode.parentNode.rowIndex);
    
}



const addEvent=()=>{
	
	let form = $("#form-add-event").serialize();
	console.log(form);
	/*$.ajax({
		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "getByPage",
		data :  form,
		timeout : 1000,
		success : function(data) {
			$("#ajax-response").html(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});*/
	
	
}

















var time1=1;
function addTime(){
    var time2;
    time2 = time1;
    var table = document.getElementById("tableaddtime"),
    tbody=table.getElementsByTagName("tbody")[0];
    var row = document.createElement("tr");
    var cell1 = document.createElement("td");
    var cell2 = document.createElement("td");
    var cell3 = document.createElement("td");
    cell1.innerHTML = `<h3 style="display:inline; float:left;">Option<h3 display:inline;float:right; class="count"></h3></h3>`;
    cell2.innerHTML = '<input type="date" name="dateVoteOption">';
    cell3.innerHTML = '<i onclick="deleteRowTime(this)" style="cursor:pointer" class="fa-solid fa-trash-can"></i>';
    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    tbody.appendChild(row);
        time1+=1;
}
function deleteRowTime(input){
    var table = document.getElementById("tableaddtime");
    var rowCount = table.rows.length;
    for(let i=0;i<rowCount;i++){ 
        console.log(i) 
    }
    table.deleteRow(input.parentNode.parentNode.rowIndex);
    rowCount--;
    time1 -= 1;
}
var place1=1;
function addPlace(){
    var place2;
    place2 = place1;
    var table = document.getElementById("tableaddplace"),
    tbody=table.getElementsByTagName("tbody")[0];
    var row = document.createElement("tr");
    var cell1 = document.createElement("td");
    var cell2 = document.createElement("td");
    var cell3 = document.createElement("td");
    cell1.innerHTML = `<h3 style="display:inline; float:left;">Option<h3 display:inline;
    float:right; class="count"></h3></h3><br><h3>Image</h3>`;
    cell2.innerHTML = '<input id="NamePlaceAdd" onchange="NamePlace_alert()" name="placeVoteOption" class="text-size" type="text">' 
                        + '<br><div class="image-upload" style="align-items: center;">' 
                        + '<label for="file-input'+place1+'">'
                        + '<img name="imgid" id="img123'+place1+'" onclick="getid(this.id)" src="https://mygear.vn/wp-content/uploads/2022/04/Hinh-Anime-hoat-hinh-sac-net-scaled-2.jpg"/>'
                        + '</label>'
                        + '<input id="file-input'+place1+'" onchange="readURL(this);" type="file" name="imageVoteOption" /></div>';
    cell3.innerHTML = '<i onclick="deleteRowPlace(this)" style="cursor:pointer" class="fa-solid fa-trash-can"></i>'; 
    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    tbody.appendChild(row);
    place1+=1;
}
function NamePlace_alert(){
    var NamePlaceAdd = document.getElementById("NamePlaceAdd").value;
    if(NamePlaceAdd.length >= 250){
        alert("Name Option isn't more than 250 characters");
    }
}
function deleteRowPlace(input){
    var table = document.getElementById("tableaddplace");
    var rowCount = table.rows.length;
    for(let i=0;i<rowCount;i++){ 
        console.log(i) 
    }
    table.deleteRow(input.parentNode.parentNode.rowIndex);
    rowCount--;
}
var ID="";
function getid(id){
    ID = id;
}
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#'+ID)
                .attr('src', e.target.result)
                .width(100)
                .height(100);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function readURL1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imgplace')
                .attr('src', e.target.result)
                .width(100)
                .height(100);
        };
        reader.readAsDataURL(input.files[0]);
    }
}


function show(shown, hidden) {
    document.getElementById(shown).style.display='block';
    document.getElementById(hidden).style.display='none';
    return false;
  }
  

  function add_alert(){
    var timestart = document.getElementById("timestart").value;
    var timeend = document.getElementById("timeend").value;
    var Time24 = document.getElementById("Time24").value;
    var Time24h = document.getElementById("Time24h").value;
    var EventName = document.getElementById("EventName").value;
    var Phase = document.getElementById("Phase").value;
    let date=new Date(timestart);
    let date2=new Date(timeend);
    if(EventName.length === 0 || Phase.length === 0 || (parseInt(Time24) == 0 && parseInt(Time24h) == 0)){
        alert("Please enter all the information");
    }else if(date2.getDay() == date.getDay()){
        if(date2.getMonth() == date.getMonth()){
            if(date2.getFullYear() == date.getFullYear()){
                if(parseInt(Time24) >= parseInt(Time24h)){
                    alert("Start and end times must be 1 hour apart");
                }
            }
        }
    }else if(date2.getDay() < date.getDay()){
        if(date2.getMonth() <= date.getMonth()){
            if(date2.getFullYear() <= date.getFullYear()){
                alert("The end date must be later than the start date");
            }
        }
    }else if(EventName.length >= 250){
        alert("Event name isn't more than 250 characters");
    }else if(Phase.length >= 250){
        alert("Place isn't more than 250 characters");
    }
}

  
  
  
  function update_alert(){
    var timestart = document.getElementById("timestart").value;
    var timeend = document.getElementById("timeend").value;
    var Time24 = document.getElementById("Time24").value;
    var Time24h = document.getElementById("Time24h").value;
    var EventName = document.getElementById("EventName").value;
    var Phase = document.getElementById("Phase").value;
    let date=new Date(timestart);
    let date2=new Date(timeend);
    if(EventName.length === 0 || Phase.length === 0 || (parseInt(Time24) == 0 && parseInt(Time24h) == 0)){
        alert("Please enter all the information");
    }else if(date2.getDay() == date.getDay()){
        if(date2.getMonth() == date.getMonth()){
            if(date2.getFullYear() == date.getFullYear()){
                if(parseInt(Time24) >= parseInt(Time24h)){
                    alert("Start and end times must be 1 hour apart");
                }
            }
        }
    }else if(date2.getDay() < date.getDay()){
        if(date2.getMonth() <= date.getMonth()){
            if(date2.getFullYear() <= date.getFullYear()){
                alert("The end date must be later than the start date");
            }
        }
    }else if(EventName.length >= 250){
        alert("Event name isn't more than 250 characters");
    }else if(Phase.length >= 250){
        alert("Place isn't more than 250 characters");
    }
}

function search_alert(){
	let check=true;
    var timestart = document.getElementById("timestart").value;
    var timeend = document.getElementById("timeend").value;
    var EventName = document.getElementById("EventName").value;
    let Time24 = document.getElementById("Time24").value;
    let Time24h = document.getElementById("Time24h").value;
    let date=new Date(timestart);
    let date2=new Date(timeend);
    if(EventName.length >= 250){
        alert("Event name isn't more than 250 characters");
    	return false;
    }else if(date2.getDay() < date.getDay()){
        if(date2.getMonth() <= date.getMonth()){
            if(date2.getFullYear() <= date.getFullYear()){
                alert("The end date must be later than the start date");
                return false;
            }
        }
        }
    else if(date2.getDay() == date.getDay()){
        if(date2.getMonth() == date.getMonth()){
            if(date2.getFullYear() == date.getFullYear()){
                if(parseInt(Time24) >= parseInt(Time24h)){
                    alert("Start and end times must be 1 hour apart");
                    return false;
                }
            }
        }
        
    }
   return check;
     
}

function btn_reset(){
    var EventName = document.getElementById("EventName");
    var Phase = document.getElementById("Phase");
    var Creater = document.getElementById("Creater");
    var Time24 = document.getElementById("Time24");
    var Time24h = document.getElementById("Time24h");
    EventName.value = "";
    Phase.value = "";
    Creater.value = "";
    Time24.selectedIndex = 0;
    Time24h.selectedIndex = 0;
}


const checkExits = (arrayElement,value)=>{
	if(arrayElement.indexOf(value) !== -1) {  
	       return true ;
	}   
	else  {  
	        return false;  
	}  
}


