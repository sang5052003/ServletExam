var register = {};

window.onload = function(){
    register.event.addEvent();
    document.getElementById("name").focus();
}

register.event = {
    
    keyPressForNumber : function(e){
        if(e.keyCode < 48 || e.keyCode > 57){
            e.returnValue = false;
            
            e.preventDefault();
            e.stopImmediatePropagation();
        }
    },
    
    keyupForNumber : function(e){
        var target = e.target;
        
        target.value = target.value.replace(/[^0-9]/g, "");
    },
    
    checkPassword : function(e){
        var password = document.getElementById("password").value;
        var passwordCheck = document.getElementById("passwordCheck").value;
        var txtCheckPw = document.getElementById("txtCheckPw");
        
        if(password === passwordCheck){
            txtCheckPw.style.color = "green";
            txtCheckPw.innerHTML = "비번 일치"
        }
        else{
            txtCheckPw.style.color = "red";
            txtCheckPw.innerHTML = "비번 불일치";
        }
    },
    
    changeEmailDomain : function(e){
        var directEmail = document.getElementById("emailDirect");
        if(e.target.value === "direct"){
            directEmail.style.display = "block";
        }
        else{
            directEmail.style.display = "none";
        }
    },
    
    
    clickAddressBtn : function(e){
        window.open("search-address-pop.html", "searchAddress", "width=650", "height=200");
    },
    
    clickRegisterBtn : function(e){
        if(register.validate()){
            var message = "다음의 정보로 회원가입 완료\n";
            
            for(key in register.result){
                message += register.result[key] + "\n";
            }
            alert(message);
            
            document.getElementById("memberForm").submit();
        }
    },
    
    addEvent : function(e){
        document.getElementById("password").onchange = this.checkPassword;
        document.getElementById("passwordCheck").onchange = this.checkPassword;
        
        document.getElementById("divPhoneNumber").onkeyPress = this.keypressForNumber;
        document.getElementById("divPhoneNumber").onkeyup = this.keyupForNumber;
        
        document.getElementById("emailDomain").onchange = this.changeEmailDomain;
        
        document.getElementById("searchAddressBtn").onclick = this.clickAddressBtn;
        document.getElementById("registerBtn").onclick = this.clickRegisterBtn;
    }
    
};

register.result = {
    name : "이름",
    id : "아이디",
    email : "이메일",
    phone : "폰",
    address : "주소"
};

register.validate = function(){
    var name = function(){
        var target = document.getElementById("name");
        //target = undefined 이면 false이므로
        if(!target || !target.value.trim()){
            target.focus();
            alert("이름을 적어라");
            return false;
        }
        
        register.result.name = target.value.trim();
        return true;
    },
        
    id = function(){
        var target = document.getElementById("id");
        if(!target || !target.value.trim()){
            target.focus();
            alert("아이디 적어라");
            return false;
        }
        
        register.result.value = target.value.trim();
        return true;
    },
        
    password = function(){
        var password = document.getElementById("password");
        var passwordCheck = document.getElementById("passwordCheck");
        
        if(!password || !password.value.trim()){
            password.focus();
            alert("비번적어라");
            return false;
        }
        else if(!passwordCheck || !passwordCheck.value.trim()){
            passwordCheck.focus();
            alert("비번확인 적어라");
            return false;
        }
        else if(password.value !== passwordCheck.value){
            passwordCheck.focus();
            alert("비번이 틀렸다");
            return false;
        }
        
        return true;
    },
    
    email = function(){
        var email = document.getElementById("email");
        
        var emailDomain = (document.getElementById("emailDomain").value === "direct") ?
            document.getElementById("emailDirect") : document.getElementById("emailDomain");
        
        if(!email || !email.value.trim()){
            email.focus();
            alert("이메일 입력해라");
            return false;
        }
        else if(!emailDomain || !emailDomain.value.trim()){
            emailDomain.focus();
            alert("도메인 입력해라");
            return false;
        }
        
        register.result.email = email.value.trim() + "@" + emailDomain.value.trim();
        
        return true;
    },
        
    phone = function(){
        var phone1 = document.getElementById("phone1");
        phone2 = document.getElementById("phone2");
        phone3 = document.getElementById("phone3");
        
        if(!phone1 || !phone1.value.trim()){
            phone1.focus();
            alert("폰번호 입력");
            return false;
        }
        else if(!phone2 || !phone2.value.trim()){
            phone2.focus();
            alert("폰번호 입력");
            return false;
        }
        else if(!phone3 || !phone3.value.trim()){
            phone3.focus();
            alert("폰번호 입력");
            return false;
        }
        
        register.result.phone = phone1.value.trim() + " - " + phone2.value.trim() + " - " + phone3.value.trim();
        return true;
    },
        
        
    address = function(){
        var zipCode1 = document.getElementById("zipCode1"),
            zipCode2 = document.getElementById("zipCode2"),
            address = document.getElementById("address"),
            detailAddress = document.getElementById("detailAddress");
        
        if(!zipCode1 || !zipCode1.value.trim()){
            zipCode1.focus();
            alert("우편번호 입력");
            return false;
        }
        else if(!zipCode2 || !zipCode2.value.trim()){
            zipCode2.focus();
            alert("우편번호 입력");
            return false;
        }
        else if(!address || !address.value.trim()){
            address.focus();
            alert("주소입력");
            return false;
        }
        else if(!detailAddress || !detailAddress.value.trim()){
            detailAddress.focus();
            alert("상세주소 입력");
            return false;
        }
        
        register.result.address = zipCode1.value.trim() + " - " + zipCode2.value.trim() + "\n" +
            address.value.trim() + " " + detailAddress.value.trim();
        
        return true;
    };
    
    return function check(){
        if(!name()){
            return false;
        }
        else if(!id()){
            return false;
        }
        else if(!password()){
            return false;
        }
        else if(!email()){
            return false;
        }
        else if(!phone()){
            return false;
        }
        else if(!address()){
            return false;
        }
        
        return true;
    }();
}

var setAddress = function(zipCode1, zipCode2, address){
    document.getElementById("zipCode1").value = zipCode1;
    document.getElementById("zipCode2").value = zipCode2;
    document.getElementById("address").value = address;
}


