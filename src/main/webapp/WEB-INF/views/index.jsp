<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Insert title here</title>
<!-- Compiled and minified CSS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


<link rel="stylesheet" href="/css/style.css">
</head>
<script src="/js/cityajax.js" type="text/javascript"></script>
<body>
	<script>
    function searchOnchange() {
        $("#borough").find("option").remove();

        let city_E = document.querySelector("#city");
        let city = city_E.value;
        $.ajax({
            url: 'http://localhost:8000/applicant/address',
            headers: { // Http header
            "Content-Type": "application/json"
            },
            type: 'post',
            data: JSON.stringify({
            'city': city
            }),
            success: function (obj) {
            let json = JSON.parse(obj);
            borough(json);
            },
            error: function () {
            alert("error");
            }
        })
        function borough(json) {

        let select = $("#borough");

            for (var i = 0; i < json.length; i++) {
            let newOption = document.createElement("option");
                newOption.className = "gugun";
                newOption.innerHTML = json[i].borough;
                newOption.name = json[i].borough;
                select.append(newOption);
            }
        }
    }
</script>
    <nav class="blue darken-1">

        <div class="my_nav blue darken-2">

            <div><a href="">IT</a></div>

            <div class="">
                <form class="search_all">

                    <div class="input-field col s6 darken-1">
                        <input id="icon_prefix" type="text" class="validate darken-1">
                        <label for="icon_prefix"><b style="color: white;">검색</b></label>
                    </div>
                    <div><button class="btn waves-effect waves-light blue" type="submit" name="action">검색</button></div>
                </form>
            </div>

            <div>
                <form action="/project/search" class="search_city" method="POST">
                    <div class="input-field col s6 darken-1">
                        <select id="city" name="city" class="" onchange="searchOnchange()" >
                            <option name="전체">전체</option>
                            <option name="서울">서울</option>
                            <option name="부산">부산</option>
                            <option name="울산">울산</option>
                            <option name="인천">인천</option>
                            <option name="광주">광주</option>
                            <option name="강원">강원</option>
                            <option name="경북">경북</option>
                            <option name="대전">대전</option>
                            <option name="대구">대구</option>
                            <option name="전북">전북</option>
                            <option name="전남">전남</option>
                            <option name="경기">경기</option>
                            <option name="경남">경남</option>
                            <option name="충북">충북</option>
                            <option name="충남">충남</option>
                            <option name="제주">제주</option>
                        </select>
                    </div>
                    <div class="input-field col s12">
                        <select id="borough" name="gu">
                            <option value="*" class="gugun">전체</option>
                        </select>
                    </div>
                    <button class="btn waves-effect waves-light blue" type="submit" name="action">검색</button>
                </form>
                
            </div>

			
			
			
			

            <div class="mene">
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="sass.html">이력서 등록</a></li>
                    <li><a href="badges.html">지원현황</a></li>
                    <li><a href="collapsible.html">직종검색</a></li>
                </ul>
            </div>
			<div class="small_menubtn"><a href="#" >★</a></div>
			
        </div>
    </nav>
	<div class="small_menu">
			<a href=""><div>이력서 등록</div></a>
			<a href=""><div>지원현황</div></a>
			<a href=""><div>직종검색</div></a>
	</div>
	<script>		
		var menubtn = $(".small_menubtn");
		
		var n = 1;
		menubtn.click(function(){
			if(n==1){
				$(".small_menu").css("height","200px");			
				n = 0;
			}else if(n==0){
				$(".small_menu").css("height","0px");
				n = 1;
			}
		});		
	</script>


			



    <div class="container_b">
        <div class="container_s">
            <div>
                <main>
                    <div class="slide_container">
                        <div class="slide_box">
                            <div class="slider">
                                <c:forEach var="item" items="${applicants}" begin="0" end="9">
                                    <div class="img_box">
                                        <a href="">
                                            <div class="img">
                                                <div>
                                                    <b>${item.applicantID}</b><br />
                                                    ${item.applicantName}<br />${item.applicantPhone}<br />
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="slide_btn">
                                <a href="#" class="prev">
                                    <</a> <a href="#" class="next">>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="my_page">
                        <div class="my1">
                            <div class="my_face">
                                <img src="/css/gokgang.png" alt="x" style="width: 100%; height: 100%;">
                            </div>
                            <div class="my_name">
                                <div>이름</div>
                                <div>직종</div>
                                <div>
                                    <a href="#">정보수정</a>
                                </div>
                                <div>
                                    <a href="#">로그아웃</a>
                                </div>
                            </div>
                        </div>
                        <div class="my2">
                           
                            <div style="border: 1px solid rgb(95, 168, 241) !important;"><b>이력서목록</b></div>
                            <div style="border: 1px solid rgb(95, 168, 241) !important;"><b>공지사항</b></div>
                            <div class="resume">
								
                                <c:forEach var="item" items="${applicants}">
                                    <a href=""><div>${item.applicantName}</div></a>
                                </c:forEach>
                            </div>

                            <div class="information">
								 
                                <c:forEach var="item" items="${applicants}">
                                    <a href=""><div>${item.applicantName}</div></a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </main>
            </div>


            <section>
                <div class="new_cpn">
                    <div class="new_cpn_box" id="new_cpn_box">

                        <c:forEach var="item" items="${applicants}" begin="0" end="7">
                            <a href="#">
                                <div>
                                    <b>${item.applicantID}</b>
                                    <div>${item.applicantName}</div>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <a href="#" class="new_btn_close btn btn-secondary" style="display:none">접기</a>
                <a href="#" class="new_btn btn btn-secondary">펼치기</a>
                <!--------------------------------------- 신규  -------------------------------------->

                <div class="career_cpn">
                    <div class="career_cpn_box" id="career_cpn_box">
                        <c:forEach var="item" items="${applicants}" begin="0" end="7">
                            <a href="#">
                                <div>
                                    <b>${item.applicantID}</b>
                                    <div>${item.applicantName}</div>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <a href="#" class="career_btn_close btn btn-secondary" style="display:none">접기</a>
                <a href="#" class="career_btn btn btn-secondary" onclick="">펼치기</a>
                <!--------------------------------------- 경력  -------------------------------------->
            </section>
            <!--------------------------------------- section  -------------------------------------->
        </div>
        <div class="on1">
            <a href="#" class="on">▲</a>
        </div>
    </div>












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
    var container = $(".container_b");
    var sliderShow = container.find(".slide_box");
    var slideBox = sliderShow.find(".slider");
    var slides = slideBox.find(">div");         //슬라이드 박스

    var slideCount = slides.length;              //슬라이드 개수
    var currentIndex = 0;                       //현재 이미지        
    //이미지 움직이기 
    function back() {
        
        if (0 < currentIndex) {
            slideBox.animate({ left: "+=100%" }, 300);
            currentIndex--;
        }
    }
    function next() {
        
        if (currentIndex < slideCount - 1) {
            slideBox.animate({ left: "-=100%" }, 300);
            currentIndex++;
        }
    }
    $(".prev").click(function(){
        back();
    });
    $(".next").click(function(){        	
        next();
    });
       
	</script>
	<script>
    var newcpnbtn = $(".new_btn");
    var newcpnclose = $(".new_btn_close");
    var newcpnbox = $(".new_cpn");

    newcpnbtn.click(function(){
        <c:forEach var="item" items="${applicants}" begin="8">
        var newA = document.createElement("a");
        newA.setAttribute("href", "#");
            newA.innerHTML = "<div><div>${item.applicantID}</div><b>${item.applicantName}</b></div>";
    		$(".new_cpn_box").append(newA);
        </c:forEach>
    newcpnbtn.css("display", "none");
    newcpnclose.css("display", "block");

    return false;
    });

    newcpnclose.click(function () {
    $("#new_cpn_box").find("a").remove();
    <c:forEach var="item" items="${applicants}" begin="0" end="7">
        var newA = document.createElement("a");
        newA.setAttribute("href", "#");
            newA.innerHTML = "<div><div>${item.applicantID}</div><b>${item.applicantName}</b></div>";
        $(".new_cpn_box").append(newA);
        </c:forEach>
        newcpnclose.css("display", "none");
    newcpnbtn.css("display", "block");


});


	

    var careercpnbtn = $(".career_btn");
    var careercpnclose = $(".career_btn_close")
    var careercpnbox = $(".career_cpn");

    careercpnbtn.click(function () {

        <c:forEach var="item" items="${applicants}" begin="8">
            var newA = document.createElement("a");
            newA.setAttribute("href", "#");
                newA.innerHTML = "<div><div>${item.applicantID}</div><b>${item.applicantName}</b></div>";
            $(".career_cpn_box").append(newA);
            </c:forEach>
            careercpnbtn.css("display", "none");
        careercpnclose.css("display", "block");

        return false;

    });
    careercpnclose.click(function () {
        $("#career_cpn_box").find("a").remove();
        <c:forEach var="item" items="${applicants}" begin="0" end="7">
            var newA = document.createElement("a");
            newA.setAttribute("href", "#");
                newA.innerHTML = "<div><div>${item.applicantID}</div><b>${item.applicantName}</b></div>";
            $(".career_cpn_box").append(newA);
            </c:forEach>
            careercpnclose.css("display", "none");
        careercpnbtn.css("display", "block");
        return false;
        });            
</script >


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