<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Do you want to build a Snowman?</title>

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
<style>
body {
   background: linear-gradient(to top, #1976D2, white 55%);
   text-align: center;
}

.my_container {
   display: inline-block;
   width: 80%;
   text-align: left;
   background-color: white;
   overflow: hidden;
}

footer {
   width: 100%;
   height: 100px;
}
.my_info{
	display: grid;
	grid-template-columns: 4fr 1fr;
}

.comment {
   display: grid;
   grid-template-columns: 1fr 4fr 1fr;
   background-color: white;
}

.comment div {
   border: 1px solid lightgray;
   padding: 5px;
}
table{
   border-top: 1px solid black;
}
.second-td{
   width:30%;
}
.info{
   padding: 30px;
}
.bio{
   height: 300px; 
   border: 1px solid lightgray;
   padding: 10px;
}
.content_box{
   display: grid;
   grid-template-columns: 5fr 1fr;
}
.my_phoyo{
	background-size: 100% 100%;
}
</style>
</head>
<body>
   <div class="my_container">
   <br /><br />
      <div>${applicantResume.title }</div>
      <br />
      

      <input type="text" name="applicantResumeUser" id="num" value="${applicantResume.user.id}" style="visibility: hidden">
      <div class="my_info">
	      <table class="table table-hover">
	         <thead>
	            <tr>
	               <th colspan="3">개인정보</th>                           
	            </tr>
	         </thead>
	      
	         <tr class="first_tr">
	            <td class="second-td">이름</td>
	            <td colspan="3">${applicantResume.user.id }</td>
	            
	         </tr>
	         <tr>
	            <td class="second-td">나이</td>
	            
	            <td>${applicantResume.age }</td>
	         </tr>
	         <tr>
	            <td class="second-td">이메일</td>
	            <td>${applicantResume.user.email }</td>
	         </tr>
	         <tr>
	            <td class="second-td">전화번호</td>
	            <td>${applicantResume.user.phone }</td>
	         </tr>
	         <tr>
	            <td class="second-td">주소</td>
	            <td>${applicantResume.user.addr }</td>
	         </tr>
	         
	      </table>
	      <img alt="x" src="${applicantResume.filePath}" class="my_phoyo" width="100%" height="100%">
      </div>
      <br />
      

      <table class="table table-hover" >
      <thead>
         <tr>
            <th colspan="3">학력</th>
         </tr>
      </thead>
      <tbody>
         <tr class="first-tr">
            <td class="second-td">희망 근무지</td>
            <td>${applicantResume.wishAddress }</td>
         </tr>
         <tr>
            <td class="second-td">희망 연봉</td>
            <td>${applicantResume.salary }</td>
         </tr>
         <tr>
            <td class="second-td">학력</td>
            <td>${applicantResume.education }</td>
         </tr>
         <tr>
            <td class="second-td">직종</td>
            <td>${applicantResume.job }</td>
         </tr>
         <tr>
            <td class="second-td">경력</td>
            <td>${applicantResume.experience }</td>
         </tr>
         <tr>
            <td class="second-td">이력</td>
            <td>${applicantResume.applicantHistory }</td>
         </tr>
         <tr>
            <td></td> <!-- 경력 추가 -->
         </tr>

      </tbody>
      </table>
      <br />
      <hr />
      <div class="info">
         <h3>자기소개</h3>
         <hr />
         <div class="bio">dddddd</div>
         <br />
         <hr />
         
      </div>
      
      <c:choose>
      		<c:when test="${applicantResume.user.id eq user.id}">
      				<div></div>
      		</c:when>
      		<c:otherwise>
      			<div class="content_box">      
			         <input type="text" placeholder="댓글.." class="content form-control" id="content"/>
			         <input class="reply-tran btn btn-light" type="button" value="전송">
			    </div>
			    <div class="reply">
			         <div class="coment_box">
			            <c:forEach var = "item" items="${comment}">
			               <div class="comment">
			                  <div>${item.fromUser.username}</div>
			                  <div> ${item.content}</div>
			                  <div> ${item.createDate}</div>
			               </div>
			            </c:forEach>
			         </div>
			     </div>
      		</c:otherwise>
      </c:choose>
	         
         
   </div><!-- container -->
   <script>
    var coment_box = document.querySelector('.coment_box');
       $('.reply-tran').click(function(){
           var id = ${applicantResume.user.id};
           alert(id);
           var content_e = document.querySelector('.content');
           var content = content_e.value;
         alert(content_e);
         alert(content);
          

           $.ajax({
               url:'/comment/'+id,
               type:'POST',
               dataType : "json",
               contentType:"application/json",
               data: JSON.stringify({
                   content : content
               }),
               success:function(obj){                   
                   if(obj == 1){
                        alert('작성 완료');
                        reply(obj);
                   }                    
               },
               error:function(){
                 alert('error');
               }
           })
       })

       function reply(obj){
          var content_e = document.querySelector('.content');
           var content = content_e.value;
            let newDiv = document.createElement('div');
            newDiv.className = 'comment';
            newDiv.innerHTML = '<div> ${user.username} </div><div>'+content+'</div><div>'+getTimeStamp()+'</div>'
            coment_box.prepend(newDiv);
       }

       function getTimeStamp() {
         var d = new Date();
         var s =
           leadingZeros(d.getFullYear(), 4) + '-' +
           leadingZeros(d.getMonth() + 1, 2) + '-' +
           leadingZeros(d.getDate(), 2);
         return s;
       }

       function leadingZeros(n, digits) {
         var zero = '';
         n = n.toString();

         if (n.length < digits) {
           for (i = 0; i < digits - n.length; i++)
             zero += '0';
         }
         return zero + n;
       }
   </script>

   <!--------------------------------------- footer  -------------------------------------->
   <footer> </footer>
   <!--------------------------------------- footer  -------------------------------------->