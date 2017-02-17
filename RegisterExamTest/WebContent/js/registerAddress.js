var address = {};

window.onload = function(){
    address.event.addEvent();
    
    document.getElementById("zipCode1").focus();
}

address.event = {
    clickInputBtn : function(){
        var zipCode1 = document.getElementById("zipCode1"),
            zipCode2 = document.getElementById("zipCode2"),
            address = document.getElementById("address");
    
        window.opener.setAddress(zipCode1.value.trim(), zipCode2.value.trim(), address.value.trim());
        window.close();
    },
    
    addEvent : function(){
        var parent = window.opener.register.event;
        document.getElementById("divZipCode").addEventListener("Keypress", parent.keyPressForNumber);
        document.getElementById("divZipCode").addEventListener("Keyup", parent.keyupForNumber);
        document.getElementById("inputBtn").addEventListener("click", this.clickInputBtn);
    }
};