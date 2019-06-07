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
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<!-- Popper JS -->
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


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

            <div class="logo"><a href="" class="nav-a">IT</a></div>

           

            <div>
           
                <form action="/project/search" class="search_city" method="POST">
                    <div class="input-field col s6 darken-1">
                        <select id="city" name="city" class="form-control" onchange="searchOnchange()" >
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
                        <select id="borough" name="gu" class="form-control">
                            <option value="*" class="gugun">전체</option>
                        </select>
                    </div>
                    <button class="btn btn-light" type="submit" name="action">검색</button>
                </form>
                
            </div>


<div class="mene">
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="#" class="newresume nav-a">이력서 등록</a></li>
                    <li><a href="collapsible.html" class="nav-a">직종검색</a></li>
                </ul>
            </div>
         <div class="small_menubtn"><a href="#" >★</a></div>
         
        </div>
    </nav>
   <!-- <div class="small_menu">
         <a href=""><div>이력서 등록</div></a>
         <a href=""><div>직종검색</div></a>
   </div> -->
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
                                <c:forEach var="item" items="${applicantResume}" begin="0" end="9">
                                    <div class="img_box">
                                        <a href="/view/get/${item.id }">
                                            <div class="img">
                                                <div>
                                                    <b>${item.title}</b><br />
                                                    ${item.job}<br />
                                                    ${item.education}<br />
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
                            
                            <div class="my_name">
                                <div>${user.name }</div>
                                <div>직종</div>
                                <div>
                                    <a href="/user/${user.id}">정보수정</a>                                                                    
                                </div>
                                <div>
                                    <a href="/logout">로그아웃</a>
                                </div>
                            </div>
                            <div >
                              <div class="resumelist">
	                                <input type="button" value="내 이력서 목록" class="resumelist_btn btn btn-outline-primary height="100%"/>
	                              <div class="myresumelist">
	                                 <c:forEach var ="item" items="${applicantResumelist}">
	                                   		<a href="#"><div>${item.title}</div></a>                                 
	                                   </c:forEach>
	                               </div>    
                              </div>
                                                        
                            </div>
                        </div>
                        <script>  /* 이력서 목록   */
                          let check = 1;
                          $('.resumelist_btn').click(function(){
                            
                            if(check == 1){
                              
                              $('.myresumelist').css('height','200');
                              check = 0;
                            }else if(check == 0){
                            	
								$('.myresumelist').css('height','0px');
								
                              check = 1;
                            }             
                            
                          })
                        </script>
                        
                        
                        
                        
                        
                        
                        <div class="my2">                           
                            
                            <div style="border: 1px solid rgb(95, 168, 241) !important;"><b>공지사항</b></div>
                           
                            <div class="information">
                         
                                <c:forEach var="item" items="${userlist}">
                                    <a href=""><div>${item.username}</div></a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </main>
            </div>


            <section>
                <div class="new_cpn">
                    <div class="new_cpn_box" id="new_cpn_box">

                        <c:forEach var="item" items="${applicantResume}" begin="0" end="7">
                            <a href="/view/get/${item.id }">
                                <div>
                                    <b>${item.title}</b>
                                    <div>${item.job}</div>
                                    <div>${item.education}</div>
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
                        <c:forEach var="item" items="${applicantResume}" begin="0" end="7">
                            <a href="/view/get/"${item.id }>
                                <div>
                                    <b>${item.title}</b>
                                    <div>${item.job}</div>
                                    <div>${item.education}</div>
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
        <c:forEach var="item" items="${applicantResume}" begin="8">
        var newA = document.createElement("a");
        newA.setAttribute("href", "/view/get/${item.id }");
            newA.innerHTML = "<div><b>${item.title}</b><div>${item.job}</div><div>${item.education}</div></div>";
          $(".new_cpn_box").append(newA);
        </c:forEach>
    newcpnbtn.css("display", "none");
    newcpnclose.css("display", "block");

    return false;
    });

    newcpnclose.click(function () {
    $("#new_cpn_box").find("a").remove();
    <c:forEach var="item" items="${applicantResume}" begin="0" end="7">
        var newA = document.createElement("a");
        newA.setAttribute("href", "/view/get/${item.id }");
            newA.innerHTML = "<div><b>${item.title}</b><div>${item.job}</div><div>${item.education}</div></div>";
        $(".new_cpn_box").append(newA);
        </c:forEach>
        newcpnclose.css("display", "none");
    newcpnbtn.css("display", "block");


});


   

    var careercpnbtn = $(".career_btn");
    var careercpnclose = $(".career_btn_close")
    var careercpnbox = $(".career_cpn");

    careercpnbtn.click(function () {

        <c:forEach var="item" items="${applicantResume}" begin="8">
            var newA = document.createElement("a");
            newA.setAttribute("href", "/view/get/${item.id }");
                newA.innerHTML = "<div><b>${item.title}</b><div>${item.job}</div><div>${item.education}</div></div>";
            $(".career_cpn_box").append(newA);
            </c:forEach>
            careercpnbtn.css("display", "none");
        careercpnclose.css("display", "block");

        return false;

    });
    careercpnclose.click(function () {
        $("#career_cpn_box").find("a").remove();
        <c:forEach var="item" items="${applicantResume}" begin="0" end="7">
            var newA = document.createElement("a");
            newA.setAttribute("href", "/view/get/${item.id }");
                newA.innerHTML = "<div><b>${item.title}</b><div>${item.job}</div><div>${item.education}</div></div>";
            $(".career_cpn_box").append(newA);
            </c:forEach>
            careercpnclose.css("display", "none");
        careercpnbtn.css("display", "block");
        return false;
        });            
</script >
<script>
  $('.newresume').click(function(){
    window.open('/resume',  "a","width=860, height=800, left=300, top=50");       
  })
</script>

    
</body>

</html>