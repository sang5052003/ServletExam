var address = {};

window.onload = function(){
	
	address.event.addEvent();
	
	document.getElementById("zipCode1").focus();
};

address.event ={
	//입력 이벤트
	clickInputButton : function(){
		var zipCode1 = document.getElementById("zipCode1").value,
		zipCode2 = document.getElementById("zipCode2").value,
		address = document.getElementById("address").value;
		
		window.opener.setAddress(zipCode1, zipCode2, address);
		window.close();
	},
	
	addEvent : function(){
		var parent = window.opener.register.event;
		document.getElementById("divZipCode").addEventListener("keypress", parent.keypressForNumer);
		document.getElementById("divZipCode").addEventListener("keyup", parent.keyupForNumer);
		document.getElementById("inputBtn").addEventListener("click", this.clickInputButton);
	}
};