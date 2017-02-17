/**
 * 
 */

//전역변수 선언

//리터럴 객체
var register = {}; //js 네임스페이스, 전역변수 구분 위해(페이지-document- 별로)

window.onload = function(){
	register.event.addEvent();
	document.getElementById("name").focus(); //포커싱
};

//이벤트
//객체 프로퍼티
register.event = {
	//숫자만 체크
	//프로퍼티 - 정체가 함수인
	keyPressForNumber : function(e) {
		if (e.keyCode < 48 || e.keyCode > 57) {
			e.returnValue = false;

			//이벤트 전파를 중지
			e.preventDefault();
			e.stopImmediatePropagation();
		}
	},

	//숫자 외 replace(복사 붙여넣기 방지)
	keyupForNumber : function(e){
		var target = e.target;
		//replace(pa1, pa2) -> pa1을 pa2로 바꿔라
		//나열대신 정규표현식을 쓴다 "0" .. ~ "9" -> /[^0-9]/g
		target.value = target.value.replace(/[^0-9]/g, ""); 
	},
	
	//비번 일치 확인
	checkPassword : function(e){
		var password = document.getElementById("password").value;
		var passwordCheck = document.getElementById("passwordCheck").value;
		var txtCheckPw = document.getElementById("txtCheckPw");
		
		if(password === passwordCheck){
			txtCheckPw.style.color = "green";
			txtCheckPw.innerHTML = "비밀번호 일치"
		}
		else{
			txtCheckPw.style.color = "red";
			txtCheckPw.innerHTML = "비밀번호 불일치"
		}
	},
	
	//이메일 도메인 변경
	changeEmailDomain : function(e){
		//직접 입력 선택 시
		var directEmail = document.getElementById("emailDirect");
		if(e.target.value === "direct"){
			directEmail.style.display = "block";
		}
		else{
			directEmail.style.display = "none";
		}
	},
	
	clickAddressBtn : function(e){
		//(오픈할 html파일, 타이틀, 넓이, 크기..)
		window.open("search-address-pop.html", "searchAddressPop", "width=650", "height=200");
	},
	
	//회원가입 버튼 클릭 시
	clickRegisterBtn : function(e){
		//유효성 검사 통과
		if(register.validate()){
			
			//정보출력
			var message = "다음의 정보로 회원가입 완료\n";
			
			//객체의 키값(like foreach문)
			for(key in register.result){
				//객체의 value 접근 -> 객체[key]
				message += register.result[key] + "\n";
			}
			alert(message);
			
			document.getElementById("memberForm").submit(); //.submit() 버튼태그에서 type="submit"대신에 사용
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

//회원가입 결과
//객체 프로퍼티
register.result = {
	name : "이름",
	id : "아이디",
	//password : "비밀번호",
	email : "이메일",
	phone : "핸드폰",
	address : "주소"
};

//회원가입 멤버 정보 유효성 체크
//함수 프로퍼티
register.validate = function(){
	
	var name = function(){
		var target = document.getElementById("name");
		//!target -> undefined
		if(!target || !target.value.trim()){
			target.focus();
			alert("이름을 적어주세요");
			return false;
		}
		
		register.result.name = target.value.trim();
		return true;
	},
	
	id = function(){
		var target = document.getElementById("id");
		if(!target || !target.value.trim()){
			target.focus();
			alert("아이디를 적어주세요");
			return false;
		}
		
		register.result.id = target.value.trim();
		return true;
	},
	
	password = function(){
		var password = document.getElementById("password");
		var passwordCheck = document.getElementById("passwordCheck");
		
		if(!password || !password.value.trim()){
			password.focus();
			alert("비밀번호를 적어주세요");
			return false;
		}
		else if(!passwordCheck || !passwordCheck.value.trim()){
			passwordCheck.focus();
			alert("비밀번호 확인을 적어주세요");
			return false;
		}
		else if(password.value !== passwordCheck.value){
			passwordCheck.focus();
			alert("비밀번호 일치하지 않습니다.");
			return false;
		}
	
		return true;
	},
	
	email = function(){
		
		var email = document.getElementById("email");
		
		//다이렉트면 직접 입력 텍스트 값 가져옴
		var emailDomain = 
			(document.getElementById("emailDomain").value === "direct") ? 
				document.getElementById("emailDirect") : document.getElementById("emailDomain");
				
		if(!email || !email.value.trim()){
			email.focus();
			alert("이메일을 입력하시오.");
			return false;
		}
		else if(!emailDomain || !emailDomain.value.trim()){
			emailDomain.focus();
			alert("이메일 도메인을 입력하시오.");
			return false;
		}
		
		register.result.email = email.value.trim() + "@" + emailDomain.value.trim();
		return true;
	},
	
	phone = function(){
		var phoneNumber1 = document.getElementById("phoneNumber1"),
		phoneNumber2 = document.getElementById("phoneNumber2"),
		phoneNumber3 = document.getElementById("phoneNumber3");
		
		if(!phoneNumber1 || !phoneNumber1.value.trim()){
			phoneNumber1.focus();
			alert("휴대폰 번호를 입력하시오.");
			return false;
		}
		else if(!phoneNumber2 || !phoneNumber2.value.trim()){
			phoneNumber2.focus();
			alert("휴대폰 번호를 입력하시오.");
			return false;
		}
		else if(!phoneNumber3 || !phoneNumber3.value.trim()){
			phoneNumber3.focus();
			alert("휴대폰 번호를 입력하시오.");
			return false;
		}
		
		register.result.phone = phoneNumber1.value.trim() + " - " + 
		phoneNumber2.value.trim() + " - " +
		phoneNumber3.value.trim();
		
		return true;
	},
	
	
	address = function(){
		var zipCode1 = document.getElementById("zipCode1"),
		zipCode2 = document.getElementById("zipCode2"),
		address = document.getElementById("address"),
		detailAddress = document.getElementById("detailAddress");
		
		if(!zipCode1 || !zipCode1.value.trim()){
			zipCode1.focus();
			alert("우편번호를 입력하시오.");
			return false;
		}
		else if(!zipCode2 || !zipCode2.value.trim()){
			zipCode2.focus();
			alert("우편번호를 입력하시오.");
			return false;
		}
		else if(!address || !address.value.trim()){
			address.focus();
			alert("주소를 입력하시오.");
			return false;
		}
		else if(!detailAddress || !detailAddress.value.trim()){
			detailAddress.focus();
			alert("주소를 입력하시오.");
			return false;
		}
	
		register.result.address =
			zipCode1.value.trim() + " - " + zipCode2.value.trim() + " " +
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
	}(); //() return에서 바로 check() 함수를 호출
	
};

var setAddress = function(zipCode1, zipCode2, address){
	document.getElementById("zipCode1").value = zipCode1;
	document.getElementById("zipCode2").value = zipCode2;
	document.getElementById("address").value = address;
}