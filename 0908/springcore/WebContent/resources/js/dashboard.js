	const clickDelete = (id)=> {
		const username = document.getElementById(id).getAttribute("username"); 
		const is_sure = window.confirm("Do you want delete "+username);
		 if(is_sure){
			 window.location="./delete?id="+id;
		 }else {	
		}
	}
 
    		 
 