<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
    <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/img/kaiadmin/favicon.ico" type="image/x-icon" />

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- FullCalendar -->
    <link href="${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.main.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.main.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/fullcalendar/ko.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bPopup/0.11.0/jquery.bpopup.min.js"></script>


  	
	
  
		
			<style>
			#calendar{
			   width:60%;
			   margin:20px auto;
			}
			</style>
			    
    <!-- FullCalendar -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/plugins.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/kaiadmin.min.css" />
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
                    <div id='calendar'></div>
                    <div id='popup' style="width:500px; height:600px; display:none; background-color:white; padding:20px; border-radius:14px; border:2px solid #eeeeee"></div>
                </div>
                <!-- page-inner -->
<!--                 ------------------------------------------------------------------------------ -->
            
                
                <div id='calendar'></div>
                
                
                
                  <script type='text/javascript'>
	
			document.addEventListener('DOMContentLoaded', function() {
			  var calendarEl = document.getElementById('calendar');
			
			  var calendar = new FullCalendar.Calendar(calendarEl, {
			    googleCalendarApiKey: 'AIzaSyDvCs91rtqmDQj-om1W3TVwMn0Z4uoAyJU',
			    eventSources: [
			    {
			          googleCalendarId: 'dd70016109c0cb346e3df957330eb4d232a31d31cc70eba29d0754ee944f0c53@group.calendar.google.com',
			          className: '휴가',
			          color: '#be5683', //rgb,#ffffff 등의 형식으로 할 수 있어요.
			          textColor: 'black' 
			        },
				    {
				          googleCalendarId: '9cde2b34cb6272fbfa6cd48e555d80413b8e45919e765fcf74d1c358c4b29538@group.calendar.google.com',
				          className: '회의실',
				          color: 'green', //rgb,#ffffff 등의 형식으로 할 수 있어요.
				          textColor: 'black' 
				        },
			        
			        
			     {
			        googleCalendarId: 'c2e3e16c8e5f57e8f830b2c1c4bddcdbc8b6670b4e1a1ce0c567db498697c8fe@group.calendar.google.com',
			          className: '연차',
			          color: 'yellow', //rgb,#ffffff 등의 형식으로 할 수 있어요.
			          textColor: 'black' 
			        },
			        
			        
			        
			        
			        
			   /* 달력 추가  */
			        
			        
			        
			        
			        
			        
			    
			   
			    ]
			  });
			  calendar.render();
			});
			</script>
                
 <!--                 ------------------------------------------------------------------------------ -->          
                
                
                
                
                
            </div>
            <!-- container -->
            <%@ include file="/resources/assets/inc/footer.jsp" %>
        </div>
        <!-- main-panel -->
    </div>
    <!-- main-wrapper -->

    <!--   Core JS Files   -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/core/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/core/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/kaiadmin.min.js"></script>
</body>
</html>
