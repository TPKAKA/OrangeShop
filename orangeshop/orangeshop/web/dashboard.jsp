<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <!-- Deverlopment-side SEO -->
        <meta charset="UTF-8">
            <meta name="description" content="Khám phá thế giới ngọt ngào tại cửa hàng bánh sinh nhật Việt Nam.
                  Orange Cake cung cấp một loạt các loại bánh ngọt tươi ngon, từ bánh cupcake đa dạng hương vị 
                  đến bánh tart tinh tế và bánh cookie thơm phức. Hãy đến với chúng tôi để thưởng thức những trải
                  nghiệm ngọt ngào và lãng mạn không thể quên!">
                <meta name="keywords" content="Bánh, sinh nhật, bánh ngọt, Việt Nam">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <meta http-equiv="X-UA-Compatible" content="ie=edge">
                            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                                <meta name="robot" content="index,follow">
                                    <title>Dashboard</title>
                                    <link rel="icon" type="image" href="assets/img/favicon.png">


                                        <!-- Bootstrap Styles-->
                                        <link href="assets/css/bootstrap.css" rel="stylesheet" />
                                        <!-- FontAwesome Styles-->
                                        <link href="assets/css/font-awesome.css" rel="stylesheet" />
                                        <!-- Morris Chart Styles-->
                                        <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
                                        <!-- Custom Styles-->
                                        <link href="assets/css/custom-styles.css" rel="stylesheet" />
                                        <!-- Google Fonts-->
                                        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
                                        <link rel="stylesheet" href="assets/js/Lightweight-Chart/cssCharts.css"> 

                                            </head>

                                            <body>

                                                <div id="wrapper">
                                                    <nav class="navbar navbar-default top-navbar" role="navigation">
                                                        <div class="navbar-header">
                                                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                                                                <span class="sr-only">Toggle navigation</span>
                                                                <span class="icon-bar"></span>
                                                                <span class="icon-bar"></span>
                                                                <span class="icon-bar"></span>
                                                            </button>
                                                            <a class="navbar-brand" href="home"><strong><i class="icon fa fa-home"></i> SWP391-KS-G5</strong></a>

                                                            <div id="sideNav" href="">
                                                                <i class="fa fa-bars icon"></i> 
                                                            </div>
                                                        </div>

                                                        <ul class="nav navbar-top-links navbar-right">



                                                            <li class="dropdown">


                                                            </li>

                                                            <li class="dropdown">
                                                                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                                                                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                                                                </a>
                                                                <ul class="dropdown-menu dropdown-user">
                                                                    <li> <a  href="UserProfile?uid=${sessionScope.admin.getUserID()}" ><i class="fa fa-user fa-fw"></i> User Profile</a>
                                                                    </li>

                                                                    <li class="divider"></li>
                                                                    <li><a href="./logout">Sign out</a><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                                                                    </li>
                                                                </ul>

                                                            </li>

                                                        </ul>
                                                    </nav>
                                                    <!--/. NAV TOP  -->
                                                    <nav class="navbar-default navbar-side" role="navigation">
                                                        <div class="sidebar-collapse">
                                                            <ul class="nav" id="main-menu">
                                                                <li>
                                                                    <a href="dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#"><i class="fa fa-sitemap"></i> Manage<span class="fa arrow"></span></a>
                                                                    <ul class="nav nav-second-level">
                                                                        <li>
                                                                            <a href="#">User Management<span class="fa arrow"></span></a>
                                                                            <ul class="nav nav-third-level">
                                                                                <li>
                                                                                    <a href="manageaccount">Customer Management</a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="StaffManagement">Staff Management</a>
                                                                                </li>
                                                                            </ul>
                                                                        </li>
                                                                        <li>
                                                                            <a href="#">News Management<span class="fa arrow"></span></a>
                                                                            <ul class="nav nav-third-level">
                                                                                <li>
                                                                                    <a href="newsmanagement">News</a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="newscategoriesmanagement">News Category</a>
                                                                                </li>
                                                                            </ul>
                                                                        </li>
                                                                        <li>
                                                                            <a href="#">Product Management<span class="fa arrow"></span></a>
                                                                            <ul class="nav nav-third-level">
                                                                                <li>
                                                                                    <a href="productmanagement">Product</a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="productcategoriesmanagement">Product Category</a>
                                                                                </li>
                                                                            </ul>
                                                                        </li>
                                                                        <li>
                                                                            <a href="#">Order Management<span class="fa arrow"></span></a>
                                                                            <ul class="nav nav-third-level">
                                                                                <li>
                                                                                    <a href="ordermanager">Order Management</a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="requestorder">Order From KiotViet</a>
                                                                                </li>
                                                                            </ul>
                                                                        </li>
                                                                    </ul>
                                                                </li>
                                                                <li>
                                                                    <a href="callapi.jsp"><i class="fa fa-money"></i> KiotViet'Product Selling</a>
                                                                </li>
                                                            </ul>

                                                        </div>
                                                    </nav>
                                                    <!-- /. NAV SIDE  -->

                                                    <div id="page-wrapper">
                                                        <div class="header"> 
                                                            <h1 class="page-header">
                                                                Dashboard <small>Welcome 
                                                                    <c:choose>
                                                                        <c:when test="${not empty sessionScope.admin}">
                                                                            ${sessionScope.admin.fullName}
                                                                        </c:when>
                                                                        <c:when test="${not empty sessionScope.staff}">
                                                                            ${sessionScope.staff.fullName}
                                                                        </c:when>
                                                                    </c:choose>
                                                                </small>
                                                            </h1>


                                                            <ol class="breadcrumb">
                                                                <li><a href="home">Home</a></li>
                                                                <li><a href="dashboard"">Dashboard</a></li>
                                                                <li class="active">Data</li>
                                                            </ol> 

                                                        </div>
                                                        <div id="page-inner">

                                                            <!-- /. ROW  -->

                                                            <div class="row">
                                                                <div class="col-md-3 col-sm-12 col-xs-12">
                                                                    <div class="board">
                                                                        <div class="panel panel-primary">
                                                                            <div class="number">
                                                                                <h3>
                                                                                    <h3 class="formattedPrice">${estimatedRevenue}</h3>
                                                                                    <small>Estimated revenue</small>
                                                                                </h3> 
                                                                            </div>
                                                                            <div class="icon">
                                                                                <i class="fa fa-dollar fa-5x red"></i>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-3 col-sm-12 col-xs-12">
                                                                    <div class="board">
                                                                        <div class="panel panel-primary">
                                                                            <div class="number">
                                                                                <h3>
                                                                                    <h3>${dailyOrders}</h3>
                                                                                    <small>Daily orders</small>
                                                                                </h3> 
                                                                            </div>
                                                                            <div class="icon">
                                                                                <i class="fa fa-shopping-cart fa-5x blue"></i>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-3 col-sm-12 col-xs-12">
                                                                    <div class="board">
                                                                        <div class="panel panel-primary">
                                                                            <div class="number">
                                                                                <h3>
                                                                                    <h3 class="formattedPrice">${dailyOrders ne 0 ? estimatedRevenue/dailyOrders : 0}</h3>
                                                                                    <small>Average revenue</small>
                                                                                </h3> 
                                                                            </div>
                                                                            <div class="icon">
                                                                                <i class="fa fa-comments fa-5x green"></i>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <!--                                                                <div class="col-md-3 col-sm-12 col-xs-12">
                                                                                                                                    <div class="board">
                                                                                                                                        <div class="panel panel-primary">
                                                                                                                                            <div class="number">
                                                                                                                                                <h3>
                                                                                                                                                    <h3>89,645</h3>
                                                                                                                                                    <small>Chưa dùng</small>
                                                                                                                                                </h3> 
                                                                                                                                            </div>
                                                                                                                                            <div class="icon">
                                                                                                                                                <i class="fa fa-user fa-5x yellow"></i>
                                                                                                                                            </div>
                                                                
                                                                                                                                        </div>
                                                                                                                                    </div>
                                                                                                                                </div>-->
                                                            </div>

                                                            <div class="row">
                                                                <div class="col-xs-6 col-md-3">
                                                                    <div class="panel panel-default">
                                                                        <div class="panel-body easypiechart-panel">
                                                                            <h4>Actual revenue</h4>
                                                                            <div class="easypiechart" id="easypiechart-blue" data-percent="${(actualRevenue/estimatedRevenue)*100}" ><span class="percent">${estimatedRevenue ne 0 ? (actualRevenue/estimatedRevenue)*100 : 0}%</span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-6 col-md-3">
                                                                    <div class="panel panel-default">
                                                                        <div class="panel-body easypiechart-panel">
                                                                            <h4>Completed orders</h4>
                                                                            <div class="easypiechart" id="easypiechart-orange" data-percent="${(completedOrders/dailyOrders)*100}" ><span class="percent">${dailyOrders ne 0 ? (completedOrders/dailyOrders)*100 : 0}%</span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-6 col-md-3">
                                                                    <div class="panel panel-default">
                                                                        <div class="panel-body easypiechart-panel">
                                                                            <h4>Actual average revenue</h4>
                                                                            <div class="easypiechart" id="easypiechart-teal" data-percent="${(actualRevenue/completedOrders)/(estimatedRevenue/dailyOrders)*100}" ><span class="percent">${dailyOrders*completedOrders ne 0 ? ((actualRevenue/completedOrders)/(estimatedRevenue/dailyOrders))*100 : 0}%</span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!--                                                                <div class="col-xs-6 col-md-3">
                                                                                                                                    <div class="panel panel-default">
                                                                                                                                        <div class="panel-body easypiechart-panel">
                                                                                                                                            <h4>Chưa dùng</h4>
                                                                                                                                            <div class="easypiechart" id="easypiechart-red" data-percent="46" ><span class="percent">46%</span>
                                                                                                                                            </div>
                                                                                                                                        </div>
                                                                                                                                    </div>
                                                                                                                                </div>-->
                                                            </div>



                                                            <!--                                                            <div class="row">
                                                                                                                            <div class="col-md-5">
                                                                                                                                <div class="panel panel-default">
                                                                                                                                    <div class="panel-heading">
                                                                                                                                        Line Chart
                                                                                                                                    </div>
                                                                                                                                    <div class="panel-body">
                                                                                                                                        <div id="morris-line-chart"></div>
                                                                                                                                    </div>						
                                                                                                                                </div>   
                                                                                                                            </div>
                                                            
                                                                                                                            <div class="col-md-7">
                                                                                                                                <div class="panel panel-default">
                                                                                                                                    <div class="panel-heading">
                                                                                                                                        Bar Chart Example
                                                                                                                                    </div>
                                                                                                                                    <div class="panel-body">
                                                                                                                                        <div id="morris-bar-chart"></div>
                                                                                                                                    </div>
                                                            
                                                                                                                                </div>  
                                                                                                                            </div>
                                                            
                                                                                                                        </div> 
                                                            
                                                            
                                                            
                                                                                                                        <div class="row">
                                                                                                                            <div class="col-md-9 col-sm-12 col-xs-12">
                                                                                                                                <div class="panel panel-default">                            
                                                                                                                                    <div class="panel-heading">
                                                                                                                                        Area Chart
                                                                                                                                    </div>
                                                                                                                                    <div class="panel-body">
                                                                                                                                        <div id="morris-area-chart"></div>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                            <div class="col-md-3 col-sm-12 col-xs-12">
                                                                                                                                <div class="panel panel-default">
                                                                                                                                    <div class="panel-heading">
                                                                                                                                        Donut Chart Example
                                                                                                                                    </div>
                                                                                                                                    <div class="panel-body">
                                                                                                                                        <div id="morris-donut-chart"></div>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                            </div>
                                                            
                                                                                                                        </div>-->
                                                            <div class="row">
                                                                <div class="col-md-12">

                                                                </div>		
                                                            </div> 	
                                                            <!-- /. ROW  -->
                                                            <div class="row">
                                                                <div class="col-md-4 col-sm-12 col-xs-12">
                                                                    <div class="panel panel-default">
                                                                        <div class="panel-heading">
                                                                            Top selling products this month
                                                                        </div>
                                                                        <div class="panel-body">
                                                                            <div class="list-group">
                                                                                <c:forEach items="${topSellingProduct}" var="c">
                                                                                    <a href="detail?productid=${c.key.productid}" class="list-group-item">
                                                                                        <span class="badge">${c.value}</span>
                                                                                        <i class="fa fa-fw fa-globe"></i>${c.key.name}
                                                                                    </a>
                                                                                </c:forEach>

                                                                            </div>
                                                                            <div class="text-right">
                                                                                <a href="productmanagement">All products <i class="fa fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                                <div class="col-md-8 col-sm-12 col-xs-12">

                                                                    <div class="panel panel-default">
                                                                        <div class="panel-heading">
                                                                            Loyal customers
                                                                        </div> 
                                                                        <div class="panel-body">
                                                                            <div class="table-responsive">
                                                                                <table class="table table-striped table-bordered table-hover">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th>Rank</th>
                                                                                            <th>Full Name</th>
                                                                                            <th>Email</th>
                                                                                            <th>Phone</th>
                                                                                            <th>Spend</th>
                                                                                        </tr>
                                                                                    </thead>
                                                                                    <tbody>
                                                                                        <c:forEach items="${loyalCustomer}" var="c" varStatus="i">
                                                                                            <tr>
                                                                                                <td>${i.index + 1}</td>
                                                                                                <td>${c.key.fullName}</td>
                                                                                                <td>${c.key.email}</td>
                                                                                                <td>${c.key.phone}</td>
                                                                                                <td class="formattedPrice">${c.value}</td>
                                                                                            </tr>
                                                                                        </c:forEach>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                            <!-- /. ROW  -->




                                                            </footer>
                                                        </div>
                                                        <!-- /. PAGE INNER  -->
                                                    </div>
                                                    <!-- /. PAGE WRAPPER  -->
                                                </div>
                                                <!-- /. WRAPPER  -->
                                                <!-- JS Scripts-->
                                                <!-- jQuery Js -->
                                                <script src="assets/js/jquery-1.10.2.js"></script>
                                                <!-- Bootstrap Js -->
                                                <script src="assets/js/bootstrap.min.js"></script>

                                                <!-- Metis Menu Js -->
                                                <script src="assets/js/jquery.metisMenu.js"></script>
                                                <!-- Morris Chart Js -->
                                                <script src="assets/js/morris/raphael-2.1.0.min.js"></script>
                                                <script src="assets/js/morris/morris.js"></script>


                                                <script src="assets/js/easypiechart.js"></script>
                                                <script src="assets/js/easypiechart-data.js"></script>

                                                <script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>

                                                <!-- Custom Js -->
                                                <script src="assets/js/custom-scripts.js"></script>


                                                <!--Chart Js--> 
                                                <script type="text/javascript" src="assets/js/Chart.min.js"></script>  
                                                <script type="text/javascript" src="assets/js/chartjs.js"></script> 
                                                <script>
                                                    // Lấy tất cả các phần tử có class là 'formattedPrice'
                                                    var priceElements = document.querySelectorAll('.formattedPrice');

                                                    // Duyệt qua từng phần tử và định dạng giá tiền của mỗi sản phẩm
                                                    priceElements.forEach(function (element) {
                                                        var price = parseFloat(element.textContent); // Chuyển đổi giá tiền thành số
                                                        var formattedPrice = price.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                                                        formattedPrice = formattedPrice.replace(' đ', ''); // Loại bỏ ký tự ' đ'
                                                        element.textContent = formattedPrice; // Gán giá tiền đã định dạng vào phần tử
                                                    });
                                                </script>
                                            </body>

                                            </html>