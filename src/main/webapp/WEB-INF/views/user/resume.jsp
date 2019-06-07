<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Do you want to build a Snowman?</title>
    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        body{
            background : linear-gradient( to top , #1976D2, white 55% );
            text-align:center;            
        }
        h3{
            margin-top: 60px;
        }
        .my_container{
            display: inline-block;
            width: 90%;
            margin-top: 30px;
            text-align: left;
        }
        .my_menu{
            display: grid;
            grid-template-columns: 2fr 1fr;
            grid-gap: 40px;
        }
        .my_info{
            width: 100%;
        }
        .my_info >div{
            margin-top: 15px;
            width: 100%;
        }
        .filebox{
            width: 150px;
            height: 200px;
        }
        .filebox label { 
            display: inline-block; 
            padding: .5em .75em; 
            color: #999; 
            font-size: inherit; 
            line-height: normal; 
            vertical-align: middle; 
            background-color: #fdfdfd; 
            cursor: pointer; 
            border: 1px solid #ebebeb; 
            border-bottom-color: #e2e2e2; 
            border-radius: .25em; 
        } 
        .filebox input[type="file"] { 
            /* 파일 필드 숨기기 */ 
            position: absolute; 
            width: 1px; 
            height: 1px; 
            padding: 0; 
            margin: -1px; 
            overflow: hidden; 
            clip:rect(0,0,0,0); 
            border: 0; 
        }
        .my_page{
            text-align: left;
            display: grid;
            grid-template-columns: 2fr 1fr;
        }
        .resume{
            display: grid;
            grid-template-columns: 2fr 1fr;            
            grid-gap: 30px;
        }
        .my_page2{
            text-align: left;        
        }
        .my_page2 select{
            margin-top: 10px;
        }
        .search{
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-gap: 20px;                         
        }
        .job{
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-gap: 10px;
        }
        .gyeong{
            margin-top: 20px;
        }
        .resume_btn{
            width: 50%;
        }
        footer{
            width: 100%;
            height: 100px;
        }
    </style>
</head>
<body>
    <hr />
    <h1>이력서</h1>
    
    <div class="my_container">
            <form action="/resume/create" method="post" enctype="multipart/form-data" name="myForm">
            <input type="text" class="form-control" placeholder="제목" name="title" required="required">
        <div>
            <h3>개인정보</h3>
            <hr/>
            <div class="my_menu">
                <div class="my_info">
                    <div><input type="text" class="form-control" readonly="readonly" value = "${user.name }"></div>   
                    <div><input type="text" class="form-control"  readonly="readonly" value = "${user.email }"></div>                    
                    <div><input type="text" class="form-control" readonly="readonly" value ="${user.phone }"></div>
                    <div><input type="text" class="form-control" readonly="readonly" value ="${user.addr }"></div> 
                      <div>
                        <select class="form-control age" name="age" id="">                            
                            <option value="서울">나이</option>
                            <option value="부산">20</option>
                            <option value="대구">21</option>
                            <option value="대구">22</option>
                            <option value="대구">23</option>
                            <option value="대구">24</option>
                            <option value="대구">25</option>
                            <option value="대구">26</option>
                            <option value="대구">27</option>
                            <option value="대구">28</option>
                            <option value="대구">29</option>
                            <option value="대구">30</option>
                        </select>
                    </div> 
                </div>
                <div class="filebox"> <label for="ex_file">사진등록</label> <input type="file" id="ex_file" name="file" required="required" /> </div>
                
            </div>
        </div>

        <h3>학력</h3>
        <hr />
        <div class="resume">
                <div class="my_page2">
                    
                  
                    <div class="search">
                      <select class="form-control wishaddress" name="wishaddress" id="" onchange="addr()">
                            <option>희망 근무지</option>
                            <option value="전체">전체</option>
                            <option value="서울">서울</option>
                            <option value="부산">부산</option>
                            <option value="울산">울산</option>
                            <option value="인천">인천</option>
                            <option value="광주">광주</option>
                            <option value="강원">강원</option>
                            <option value="경북">경북</option>
                            <option value="대전">대전</option>
                            <option value="대구">대구</option>
                            <option value="전북">전북</option>
                            <option value="전남">전남</option>
                            <option value="경기">경기</option>
                            <option value="경남">경남</option>
                            <option value="충북">충북</option>
                            <option value="충남">충남</option>
                            <option value="제주">제주</option>
                      </select>
                      
                      <select class="form-control borough">
                          <option value="ㅋ">전체</option>
                      </select>
                      
                    </div>
                    <div class="salary">
                      <select class="form-control salary" name="salary" id="">
                              <option value="">연봉</option>
                              <option value="2000">2000</option>
                              <option value="2500">2500</option>
                              <option value="3000">3000</option>
                              <option value="3500">3500</option>
                              <option value="4000">4000</option>
                              <option value="4500">4500</option>
                              <option value="5000">5000</option>
                              <option value="">협의</option>
                      </select>
                  </div>
  
                  <div class="education">            
                      <select class="form-control education" name="education" id="">
                          <option value="중졸">중졸</option>
                          <option value="고졸">고졸</option>
                          <option value="초대졸">초대졸</option>
                          <option value="대졸">대졸</option>
                          <option value="석사">석사</option>
                          <option value="박사">박사</option>
                      </select>
                  </div>
                  <div class="job">            
                      <select class="form-control bigOrthogonal" name="bigOrthogonal" id="bigjob" onchange="jobchang()">
                           <option value="게임 GAME">게임 GAME</option>
                           <option value="웹개발">웹개발</option>
                           <option value="응용프로그램개발">응용프로그램개발</option>
                           <option value="시스템개발">시스템개발</option>
                           <option value="서버 네트워크 보안">서버 네트워크 보안</option>
                           <option value="ERP 시스템분석 설계">ERP 시스템분석 설계ㄴ</option>
                           <option value="데이터베이스DBA">데이터베이스DBA</option>
                           <option value="퍼블리싱UI개발">퍼블리싱UI개발</option>
                           <option value="통신 모바일">통신 모바일</option>
                           <option value="웹기획PM">웹기획PM</option>
                           <option value="IT디자인 컴퓨터 교육">IT디자인 컴퓨터 교육</option>
                           <option value="웹마스터QA 테스터">웹마스터QA 테스터</option>
                           <option value="동영상 편집코덱">동영상 편집코텍</option>
                           <option value="인공지능AI빅데이터">인공지능AI빅데이터</option>
                          <option value="하드웨어 소프트웨어">하드웨어 소프트웨어</option>
                          <option value="컨텐츠 사이트운영">컨텐츠 사이트운영</option>
                          <option value="웹디자인">웹디자인</option>                        
                      </select>
                      
                      <select class="form-control" name="job" id="smalljob">
                          <option value="전체" class="s_job">전체</option>                    
                      </select>
                  </div>
                  <div class="gyeong">
                        <p>
                            <label>
                                <input name="experience" type="radio" checked />
                                <span>신입</span>
                            </label>
                        </p>
                        <p>
                            <label>
                                <input name="experience" type="radio" />
                                <span>경력</span>
                            </label>
                        </p>
                    </div>
                </div>
                
  
  
              <div>
                  <input id="zip" type="file" name="zip" required="required"/>              
                                  
              </div>
              <div class="resume_btn"><button class="btn btn-light" onclick="call(this)" >등록</button></div>
             </form>
        </div>

    </div>
    <!--------------------------------------- footer  -------------------------------------->
    <footer>
       
    </footer>
    <!--------------------------------------- footer  -------------------------------------->
   
         
         
               
                
                
        <script>
                function call(e){
                    var form = document.myForm;            
                    e.form.submit();
                    alert("이력서 등록 완료");
                }
        </script>          
                  
                  
                  
       
      <script>                  
         function jobchang(){
            $('#smalljob').find('option').remove();
            
            var bigOrthogonal_E = document.querySelector('#bigjob');
             var bigOrthogonal = bigOrthogonal_E.value;
            
             
             $.ajax({
                url:'/job/'+bigOrthogonal,
                headers:{
                   "Content-Type":"application/json",
                },
                type: 'POST',
                data: JSON.stringify({
                   'bigOrthogonal': bigOrthogonal
                }),
                
                success: function(obj){                                                              
                   smalljob(obj);                                
                },
                error: function(){
                   alert("error");
                }
             })
             
             function smalljob(obj){
                var smalljob = document.querySelector('#smalljob');
                
                for(var i = 0; i< obj.length; i++){
                   let newOption = document.createElement("option");
                   newOption.className = "s_job";
                   newOption.innerHTML = obj[i].smallJob;
                   smalljob.append(newOption);
                }
             }
             
         }
         </script>
         
         
         <script>                  
            function addr(){
               $('.borough').find('option').remove();
               
               var wishaddress_E = document.querySelector('.wishaddress');
                var wishaddress = wishaddress_E.value;
               
                
                $.ajax({
                   url:'/addr/'+wishaddress,
                   headers:{
                      "Content-Type":"application/json",
                   },
                   type: 'POST',
                   data: JSON.stringify({
                      'wishaddress': wishaddress
                   }),
                   
                   success: function(obj){
                                                              
                      borough(obj);
                      
                   
                   },
                   error: function(){
                      alert("error");
                   }
                })
                
                function borough(obj){
                   var borough = document.querySelector('.borough');
                   
                   for(var i = 0; i< obj.length; i++){
                      let newOption = document.createElement("option");
                      newOption.className = "borough";
                      newOption.innerHTML = obj[i].borough;
                      borough.append(newOption);
                   }
                }
                
            }
         </script>
</body>
</html>