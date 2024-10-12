<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<meta charset="UTF-8">
<head>
 <!-- 한글 인코딩 추가 -->


<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/attendanceMain.css" />
<!--QR 라이브러리  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-qrcode/1.0.0/jquery.qrcode.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
   
    
    
    
    
    
    
    
  <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
    <link
      rel="icon"
      href="${pageContext.request.contextPath }/resources/assets/img/kaiadmin/favicon.ico"
      type="image/x-icon"
    />

    <!-- Fonts and icons -->
    
      <script>
        function checkOut() {
            // AJAX 요청을 보내어 퇴근 처리
            fetch('/Attendance/checkOut', {
                method: 'GET',
                credentials: 'include' // 세션을 포함하여 요청
            })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('퇴근 처리 실패');
                }
            })
            .then(message => {
                alert(message); // 성공 메시지 출력
                // 필요에 따라 페이지를 리다이렉트할 수 있습니다.
                // location.reload(); // 페이지 리로드
            })
            .catch(error => {
                alert(error.message); // 오류 메시지 출력
            });
        }
    </script>
    
    
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/webfont/webfont.min.js"></script>
    <script>
      WebFont.load({
        google: { families: ["Public Sans:300,400,500,600,700"] },
        custom: {
          families: [
            "Font Awesome 5 Solid",
            "Font Awesome 5 Regular",
            "Font Awesome 5 Brands",
            "simple-line-icons",
          ],
          urls: ["${pageContext.request.contextPath }/resources/assets/css/fonts.min.css"],
        },
        active: function () {
          sessionStorage.fonts = true;
        },
      });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/plugins.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/kaiadmin.min.css" />

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/demo.css" />
  </head>
  <body>
    <div class="wrapper">
      <%@ include file="/resources/assets/inc/sidebar.jsp" %> <!-- sidebar -->
      <div class="main-panel">
        <div class="main-header">
          <%@ include file="/resources/assets/inc/logo_header.jsp" %> <!-- Logo Header -->
          <%@ include file="/resources/assets/inc/navbar.jsp" %> <!-- Navbar Header -->
        </div>
        <div class="container">
          <div class="page-inner">
<!------------------------------------------------------------------------------------------------------------------>
  <h1>Attendance Main</h1>

    <c:if test="${not empty checkInTime}">
        <div class="attendance-info">
            <p>출근 시간: ${checkInTime}</p>
        </div>
    </c:if>
		
		
		
				<script>
		    $(document).ready(function() {
		        $('#checkoutButton').click(function() {
		            $.ajax({
		                url: '<c:url value="checkOut" />',
		                type: 'GET',
		                success: function(response) {
		                    if (response.status === 'success') {
		                        alert(response.message);
		
		                        // 퇴근 시간이 성공적으로 업데이트되면 현재 시간을 퇴근 시간으로 표시
		                        const now = new Date();
		                        const formattedTime = now.getFullYear() + '-' +
		                            ('0' + (now.getMonth() + 1)).slice(-2) + '-' +
		                            ('0' + now.getDate()).slice(-2) + ' ' +
		                            ('0' + now.getHours()).slice(-2) + ':' +
		                            ('0' + now.getMinutes()).slice(-2) + ':' +
		                            ('0' + now.getSeconds()).slice(-2);
		                        
		                        $('#checkoutTimeDisplay').text('퇴근 시간: ' + formattedTime);
		                    } else {
		                        alert(response.message);
		                    }
		                },
		                error: function() {
		                    alert('퇴근 요청 중 오류가 발생했습니다.');
		                }
		            });
		        });
		    });
		    
		    $(document).ready(function() {
		        $('#calculateButton').click(function() {
		            $.ajax({
		                url: '<c:url value="calculateWorkingTime" />',
		                type: 'GET',
		                success: function(response) {
		                    alert(response.message);
		                    // 계산된 근무 시간을 페이지에 표시
		                    $('#workingTimeDisplay').text('총 근무 시간: ' + response.workingTime.toFixed(2) + ' 시간');
		                },
		                error: function() {
		                    alert('근무 시간 계산 중 오류가 발생했습니다.');
		                }
		            });
		        });
		    });
		    </script>

		    <button id="calculateButton">근무 시간 계산</button>
		    <p id="workingTimeDisplay">총 근무 시간이 아직 계산되지 않았습니다.</p>
		    
		
		<button id="checkoutButton">퇴근</button>
		<p id="checkoutTimeDisplay">퇴근 시간이 아직 없습니다.</p>
		<!-- 출퇴근   -->
		
		
		
		
		
		
		
		
		
		
		

        <button class="btn btn-primary">근태 결재 신청</button>
        
        
        <div>
        <button class="btn btn-primary">나의 근태 이력 조회</button>
        </div>
        
        <div>
         <button class="btn btn-primary">휴직 신청</button>
            </div>
            
       <div>
        <button class="btn btn-primary">근무제 결재 신청</button>
            </div>
     
    
       <button class="btn btn-info" id="checkinBtn">출근</button>
       <button class="btn btn-info" id="checkoutBtn">퇴근</button>
       <button class="btn btn-info" id="outworkBtn">외근</button>
       <button class="btn btn-info" id="returnBtn">복귀</button>
    








<!------------------------------------------------------------------------------------------------------------------>
          </div>
          <!-- page-inner -->
        </div>
		<!-- container -->
        <%@ include file="/resources/assets/inc/footer.jsp" %>
      </div>
      <!-- main-panel -->
    </div>
    <!-- main-wrapper -->
    

    
    <!--   Core JS Files   -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/assets/js/core/popper.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/assets/js/core/bootstrap.min.js"></script>

    <!-- jQuery Scrollbar -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

    <!-- Chart JS -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/chart.js/chart.min.js"></script>

    <!-- jQuery Sparkline -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

    <!-- Chart Circle -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/chart-circle/circles.min.js"></script>

    <!-- Datatables -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/datatables/datatables.min.js"></script>

    <!-- Bootstrap Notify -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

    <!-- jQuery Vector Maps -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/jsvectormap/world.js"></script>

    <!-- Sweet Alert -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/plugin/sweetalert/sweetalert.min.js"></script>

    <!-- Kaiadmin JS -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/kaiadmin.min.js"></script>

    <!-- Kaiadmin DEMO methods, don't include it in your project! -->
    <script src="${pageContext.request.contextPath }/resources/assets/js/setting-demo.js"></script>
    <script src="${pageContext.request.contextPath }/resources/assets/js/demo.js"></script>
    <script>
      $("#lineChart").sparkline([102, 109, 120, 99, 110, 105, 115], {
        type: "line",
        height: "70",
        width: "100%",
        lineWidth: "2",
        lineColor: "#177dff",
        fillColor: "rgba(23, 125, 255, 0.14)",
      });

      $("#lineChart2").sparkline([99, 125, 122, 105, 110, 124, 115], {
        type: "line",
        height: "70",
        width: "100%",
        lineWidth: "2",
        lineColor: "#f3545d",
        fillColor: "rgba(243, 84, 93, .14)",
      });

      $("#lineChart3").sparkline([105, 103, 123, 100, 95, 105, 115], {
        type: "line",
        height: "70",
        width: "100%",
        lineWidth: "2",
        lineColor: "#ffa534",
        fillColor: "rgba(255, 165, 52, .14)",
      });
    </script>
  </body>
</html>
