(()=>{
		let confirmId = "";
		
		document.querySelector("#btnIdCheck").addEventListener('click', ()=>{
			let userId = document.querySelector("#userId").value;
			
			if(!userId){
				document.querySelector("#idCheck").innerHTML = '아이디를 입력하세요.';
				return;
			}
			
			fetch("/member/id-check?userId=" + userId)
			.then(response => {
				if(response.ok){
					return response.text();
				}else{
					throw new Error(response.status);
				}
				})
			.then(text => {
				if(text == "available"){
					document.querySelector("#idCheck").innerHTML = '사용 가능한 아이디 입니다.';
					confirmId = userId;
				}else{
					document.querySelector("#idCheck").innerHTML = '이미 존재하는 아이디 입니다.';
				}
			})
			.catch(error => {
				document.querySelector("#idCheck").innerHTML = '응답에 실패했습니다. 상태코드 : ' + error;
			})
		});
		
		document.querySelector("#frm_join").addEventListener("submit",e=>{
			let userId = document.querySelector("#userId").value;
			let password = document.querySelector("#passowrd").value;
			let pwReg = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9])(?=.{8,})/; // 영문자,숫자,특수문자,총 8자리로 구성
			let tell = document.querySelector("#tell").value;
			let tellReg = /^\d{9,11}$/; // 숫자로만 구성, 9~11자리
			
			if(confirmId != userId){
				e.preventDefalut(); // 이벤트 전파 차단
				document.querySelector("#idCheck").innerHTML = "아이디 중복 검사를 하지 않았습니다.";
				document.querySelector("#userId").focus();
			}
			
			/*if(!pwReg.test(password)){
				e.preventDefalut(); // 이벤트 전파 차단
				document.querySelector("#pwCheck").innerHTML = "비밀번호는 숫자,영문자,특수문자 포함의 8글자 이상인 문자열이어야 합니다.";
			}
			
			if(!tellReg.test(tell)){
				e.preventDefalut(); // 이벤트 전파 차단
				document.querySelector("#tellCheck").innerHTML = "전화번호는 9~11자리의 숫자여야 합니다.";
			}*/
		})
	})();