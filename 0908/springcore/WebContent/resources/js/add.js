	const dislayMessage = ()=>{
  		let username = document.getElementById("username").value;
		let password = document.getElementById("password").value;

		if(username.trim().length==0 || password.trim().length==0 ||username==null ||password==null){
			document.getElementById("message").innerText = "Username or Password is not null !";
		} else { 
  			window.location="./add-new-after?username="+username+"&password="+password;  	
		}  	
	};
    const deleteMessage = ()=>{
    	const message = document.getElementById("message").innerText="";	 
    };