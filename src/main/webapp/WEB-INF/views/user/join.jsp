<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script language="javascript">
        // opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
        //document.domain = "abc.go.kr";        
        function goPopup(){
            // 주소검색을 수행할 팝업 페이지를 호출합니다.
            // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
            var pop = window.open("member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");             
            // 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
            //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
        }                
        function jusoCallBack(roadFullAddr){
                // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
                alert(roadFullAddr);
                document.form.roadFullAddr.value = roadFullAddr;
        }        
    </script>
    <title>Do you want to build a snowman?</title>
    <!-- Compiled and minified CSS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link rel="stylesheet" href="/css/join.css">
</head>
<body>
    <nav class="nav blue darken-1">
        <a href="/"><div>로고</div></a>
    </nav>    
    <form action="/applicant/create" name="form" id="form" method="POST" onsubmit="return join()">
        <div class="container">        
        <div>
                <div class="row">
                    <div class="input-field col s6">
                        <input id="name" type="text" class="validate" name="name"/>
                        <label for="name">이름</label>                        
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input id="username" type="text" class="username" name="username"/>
                        <label for="username">아이디</label>                        
                    </div>
                    <input type="button" class="btn waves-effect waves-light blue" onclick="validateDuplicateID()" style="color:white" value="중복확인" >
                    
                </div>

				<div class="row">                   
                    <div class="input-field col s6" style="">
                        <input id="pw1" type="password" class="pw1" name="password" />
                        <label for="userPassword">패스워드</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="pw2" type="password" class="pw2" />
                        <label for="userPassword2">패스워드확인</label>
                    </div>
                </div>                          

                <div class="row">                   
                    <div class="input-field col s6">
                        <input id="address" type="text" class="validate" readOnly/>
                        <label for="address">주소</label>
                    </div>   
                    <button class="btn waves-effect waves-light blue" type="submit" name="applicantUserId" onClick="goPopup();"/>주소검색</button>                 
                </div>
                                    
                <div class="input-field col s12">
                    <input id="address_detail" type="text" class="validate" name="addr" />
                    <label for="address_detail">상세주소</label>
                </div>   
                
                <div class="input-field col s6">        
                    <input id="icon_telephone" type="tel" class="validate" name="phone"/>
                    <label for="icon_telephone">전화번호 (-없이 입력하세요)</label>
                </div>
                        
                <div class="input-field inline">
                    <input id="my_email" type="email" class="validate" name="email"/>
                    <label for="my_email">이메일</label>
                </div>

                <div class="input-field col s6">
                    <input id="rrn" type="text" class="validate" name="age"/>
                    <label for="rrn">생년월일  ex) 940816</label>
                </div> 
                <p>
                    <label>
                        <input name="gender" type="radio" style="color:#1976D2 !important;" checked />
                        <span>여자</span>
                    </label>
                    </p>
                    <p>
                    <label>
                        <input name="gender" type="radio" />
                        <span>남자</span>
                    </label>
                </p>                                                
            </div>                           
        </div>       
         <input type="submit" value="회원가입" class="btn waves-effect waves-light blue" /> 
    </form>
    <!--------------------------------------- footer  -------------------------------------->
    <footer>
        <div class="footer">
            <a href="">회사소개</a>
            <a href="">찾아오시는길</a>
            <a href="">호원약관</a>
            <a href="">개인정보처리방침</a>
            <a href="">고객센터</a>
        </div>
    </footer>
    <!--------------------------------------- footer  -------------------------------------->
<script>
	var check = 1;
    function validateDuplicateID(){
        var applicantId_element = $(".username");
        var applicantId = applicantId_element.value;
        
       
        
        
        $.ajax({
            url:'/applicant/',
            headers: { // Http header
                "Content-Type": "application/json",                    
            },
            type: 'POST',
            data: JSON.stringify({
                'applicantId': applicantId
            }),
            success: function (obj) {
                if(1 == obj){
                    alert("사용가능한 아이디 입니다."); 
                    check=0;                
                }else{
                    alert("중복된 아이디가 있습니다.");
                    applicantId.focus();
                }
            },
            error: function () {            	
                alert("error");
                applicantId.focus();
            }
        })        
    }

    function join(){
    	 
        if(check == 1){
        	alert("아이디 중복체크를 해주세요");
            return false;
        }
        var pw1 = document.querySelector(".pw1");
		var pw2 = document.querySelector(".pw2");
		alert(pw1);
		alert(pw1.value);
		alert(pw2.value);

        if(pw1.value === pw2.value){
            return true;
        }else{
            pw1.value = "";
            pw2.value = "";
            pw1.focus();
            alert("비밀번호가 일치하지 않습니다.");
            return false;
        }
    }
</script>


<script>
    document.addEventListener('DOMContentLoaded', function () {
        var elems = document.querySelectorAll('select');
        var instances = M.FormSelect.init(elems, options);
    });

    // Or with jQuery

    $(document).ready(function () {
        $('select').formSelect();
    });
</script>
</body>
</html>