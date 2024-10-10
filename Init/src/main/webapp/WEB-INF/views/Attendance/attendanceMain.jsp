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
    
    
    <!--웹소켓  -->
        <script src="https://unpkg.com/html5-qrcode/minified/html5-qrcode.min.js"></script>
    <!-- WebSocket 및 QR 코드 스캔 스크립트 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jsqr/1.4.0/jsQR.min.js"></script>
    
    
    
    
    
  <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
    <link
      rel="icon"
      href="${pageContext.request.contextPath }/resources/assets/img/kaiadmin/favicon.ico"
      type="image/x-icon"
    />

    <!-- Fonts and icons -->
    
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
   
   
  
    <div id="attendanceInfo">
        <p id="attendanceMessage">아직 출퇴근 기록이 없습니다.</p>
    </div>
    
    <!-- ZXing QR 코드 스캔을 위한 div -->
    <div id="reader" style="width: 300px; height: 300px;"></div>

   
    
	
	
	
	

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
     
        <div class="list">
            <div class="cell">
                <div class="icon">
                </div>
                <div class="text">
                    <div class="text---1">
                        - 고정 근무
                    </div>
                    <div class="text--0900-1800">
                        ㄴ 09:00 ~18:00
                    </div>
                </div>
                <svg id="123:021595" class="icon-1"></svg>
            </div>
            <svg id="123:021566" class="divider"></svg>
            <div class="cell-1">
                <div class="icon-2">
                </div>
                <div class="text-36">
                    <div class="text---2">
                        - 선택 근무
                    </div>
                    <div class="text--1400-1800">
                        ㄴ 14:00 ~18:00
                    </div>
                </div>
                <svg id="123:021610" class="icon-3"></svg>
            </div>
            <svg id="123:021564" class="divider-1"></svg>
            <div class="cell-2">
                <div class="icon-4">
                </div>
                <div class="text-37">
                    <div class="text---3">
                        - 야간 근무
                    </div>
                    <div class="text--2200-0700">
                        ㄴ 22:00 ~07:00
                    </div>
                </div>
                <svg id="123:021569" class="icon-5"></svg>
            </div>
            <svg id="123:021562" class="divider-2"></svg>
            <div class="cell-3">
                <div class="icon-6">
                </div>
                <div class="text-38">
                    <div class="text---4">
                        - 특별 근무
                    </div>
                    <div class="text--0900-1801">
                        ㄴ 09:00 ~18:00
                    </div>
                </div>
                <svg id="123:021613" class="icon-7"></svg>
            </div>
        </div>
        <div class="profile">
            <div class="navigation-bar">
                <div class="icon-8">
                </div>
                <div class="icons">
                    <div class="icon-9">
                    </div>
                    <svg id="123:039532" class="icon-10"></svg>
                </div>
            </div>
            <div class="profile-contents">
                <div class="avatar">
                    <img src="https://image-resource.creatie.ai/140658751204418/140658751204420/53f2f16fc321bb84025e76c3953fca73.jpeg" class="image" />
                    <div class="username">
                        <div class="james-john">
                            James John
                        </div>
                        <svg id="123:039549" class="icon-11"></svg>
                    </div>
                </div>
            </div>
            <div class="text------------">
                <div><span> - 사원 번호 :<div class="_p"></div>
                        <div class="_p"></div> - 부서 : <div class="_p"></div>
                        <div class="_p"></div> - 직책:<div class="_p"></div>
                        <div class="_p"></div> - 출근시간 :<div class="_p"></div>
                        <div class="_p"></div> - 퇴근시간 :<div class="_p"></div>
                        <div class="_p"></div> - 근무한시간 :
                    </span></div>
            </div>
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
