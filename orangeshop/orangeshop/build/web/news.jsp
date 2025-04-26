<%-- 
    Document   : news.jsp
    Created on : Jan 11, 2024, 1:01:31 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zxx">

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
        <title>Orange News</title>
        <link rel="icon" type="image" href="assets/img/favicon.png">

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/flaticon.css" type="text/css">
        <link rel="stylesheet" href="assets/css/barfiller.css" type="text/css">
        <link rel="stylesheet" href="assets/css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="assets/css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/style.css" type="text/css">
        <link rel="stylesheet" href="assets/css/style.min.css" type="text/css">

    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Offcanvas Menu Begin -->
        <div class="offcanvas-menu-overlay"></div>
        <div class="offcanvas-menu-wrapper">
            <div class="offcanvas__cart">
                <div class="offcanvas__cart__links">
                    <a href="#" class="search-switch"><img src="assets/img/icon/search.png" alt=""></a>
                    <a href="#"><img src="assets/img/icon/heart.png" alt=""></a>
                </div>
                <div class="offcanvas__cart__item">
                    <a href="#"><img src="assets/img/icon/cart.png" alt=""> <span>0</span></a>
                    <div class="cart__price">Cart: <span>$0.00</span></div>
                </div>
            </div>
            <div class="offcanvas__logo">
                <a href="./index.html"><img src="assets/img/logo.png" alt=""></a>
            </div>
            <div id="mobile-menu-wrap"></div>
            <div class="offcanvas__option">
                <ul>
                   
                  
                    <c:choose>
                        <c:when test="${empty sessionScope.admin && empty sessionScope.staff && empty sessionScope.customer && empty sessionScope.guest}">
                            <li><a href="./login">Sign in</a> <span class="arrow_carrot-down"></span></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="./logout">Sign out</a> <span class="arrow_carrot-down"></span></li>
                            </c:otherwise>
                        </c:choose>
                </ul>
            </div>
        </div>
        <!-- Offcanvas Menu End -->

        <!-- Header Section Begin -->
        <header class="header">
            <div class="header__top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="header__top__inner">
                                <div class="header__top__left">
                                    <ul>
                                       
                                       
                                        <c:choose>
                                            <c:when test="${empty sessionScope.admin && empty sessionScope.staff && empty sessionScope.customer && empty sessionScope.guest}">
                                                <li><a href="./login">Sign in</a> <span class="arrow_carrot-down"></span></li>
                                                </c:when>
                                                <c:otherwise>
                                                <li><a href="./logout">Sign out</a> <span class="arrow_carrot-down"></span></li>
                                                </c:otherwise>
                                            </c:choose>
                                    </ul>
                                </div>
                                <div class="header__logo">
                                    <a href="./home"><img src="assets/img/logo.png" alt=""></a>
                                </div>
                                <div class="header__top__right">
                                    <div class="header__top__right__links">
                                        <a href="#" class="search-switch"><img src="assets/img/icon/search.png" alt=""></a>
                                        <a href="#"><img src="assets/img/icon/heart.png" alt=""></a>
                                    </div>
                                   <div class="header__top__right__cart">
                                        <a href="Cart?action=showcart">
                                            <img src="assets/img/icon/cart.png" alt="">
                                            <span>
                                                <c:choose>
                                                    <c:when test="${sessionScope.size > 0}">
                                                        ${sessionScope.size}
                                                    </c:when>
                                                    <c:otherwise>
                                                        0
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </a>

                                        <div class="cart__price">Cart</div>
                                    </div>
                                    <c:choose>
                                        <c:when test="${empty sessionScope.admin && empty sessionScope.staff && empty sessionScope.customer && empty sessionScope.guest}">
                                            <!-- User is not logged in, do not show the avatar button -->
                                        </c:when>
                                        <c:when test="${customer != null }">

                                            <!-- User is logged in, show the avatar button -->

                                            <!-- Avatar and button -->

                                            <a  href="UserProfile?uid=${sessionScope.customer.getUserID()}" >
                                                <img class="avatar avatar-ex-small rounded-circle" src="${customer.getImage()}" alt="Avatar">
                                            </a>



                                        </c:when>

                                        <c:when test="${ admin != null }">

                                            <!-- User is logged in, show the avatar button -->
                                            <div class="header__top__right">
                                                <div class="header__top__right__links">
                                                    <!-- Avatar and button -->

                                                    <a href="UserProfile?uid=${sessionScope.admin.getUserID()}" >
                                                        <img class="avatar avatar-ex-small rounded-circle" src="${admin.getImage()}" alt="Avatar">
                                                    </a>

                                                </div>
                                            </div>
                                        </c:when>

                                        <c:when test="${ staff != null}">

                                            <!-- User is logged in, show the avatar button -->
                                            <div class="header__top__right">
                                                <div class="header__top__right__links">
                                                    <!-- Avatar and button -->

                                                    <a href="UserProfile?uid=${sessionScope.staff.getUserID()}" >
                                                        <img class="avatar avatar-ex-small rounded-circle" src="${staff.getImage()}" alt="Avatar">
                                                    </a>

                                                </div>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="canvas__open"><i class="fa fa-bars"></i></div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <nav class="header__menu mobile-menu">
                            <ul>
                                <li><a href="./home" style="text-decoration: none;">Home</a></li>
                                <li><a href="page" style="text-decoration: none;">Shop</a></li>
                                <li><a href="./news" style="text-decoration: none;">News</a></li>
                                    <c:if test="${sessionScope.admin !=null || sessionScope.staff !=null}">
                                    <li><a href="./dashboard" style="text-decoration: none;">Management</a></li>
                                    </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
        <!-- Header Section End -->

        <!-- Breadcrumb Begin -->
        <div class="breadcrumb-option">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="breadcrumb__text">
                            <h2>News ${page}</h2>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="breadcrumb__links">
                            <a href="./home">Home</a>
                            <span>News</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->

        <!-- Blog Section Begin -->
        <section class="blog spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">

                        <c:forEach items="${news}" var="c">                           
                            <div class="blog__item">
                                <a href="./newsdetails?id=${c.id}" title="${c.name}">
                                    <div class="blog__item__pic set-bg" data-setbg="assets/img/blog/${c.image}">
                                        <div class="blog__pic__inner">
                                            <div class="label">
                                                <c:forEach items="${newsCategories}" var="nc">
                                                    <c:if test="${nc.id == c.category}">${nc.name}</c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </a>       
                                <div class="blog__item__text"> 
                                    <h2><a href="./newsdetails?id=${c.id}" title="${c.name}" style="all: inherit;">${c.name}</a></h2>
                                    <p><a href="./newsdetails?id=${c.id}" title="${c.name}" style="all: inherit;">${c.content.substring(0, 250)}...</a></p>
                                    <a class="read_more" href="./newsdetails?id=${c.id}" title="${c.name}">READ MORE</a>
                                </div>
                                </a>
                            </div>                     
                        </c:forEach>

                        <!--paging-->
                        <div class="shop__pagination">
                            <c:if test="${not empty numOfPage}">
                                <%
                                    int n = (Integer) request.getAttribute("numOfPage");
                                    for (int i = 1; i <= n; i++) {
                                %>
                                <a href="news?page=<%= i%>"> <%= i%> </a>
                                <%
                                    }
                                %>
                                <!--<a href="#"><span class="arrow_carrot-right"></span></a>-->
                            </c:if>
                        </div>

                    </div>
                    <div class="col-lg-4">
                        <div class="blog__sidebar">
                            <div class="blog__sidebar__search">
                                <form action="news" method="POST">
                                    <input name="newsName" type="text" placeholder="Enter keyword">
                                    <button type="submit"><i class="fa fa-search"></i></button>
                                </form>
                            </div>

                            <div class="blog__sidebar__item">
                                <h5>Follow me</h5>
                                <div class="blog__sidebar__social">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-youtube-play"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                </div>
                            </div>
                            <div class="blog__sidebar__item">
                                <h5>New posts</h5>
                                <div class="blog__sidebar__recent">
                                    <c:forEach items="${fiveNewPost}" var="c">    
                                        <a href="./newsdetails?id=${c.id}" class="blog__sidebar__recent__item">
                                            <div class="blog__sidebar__recent__item__pic">
                                                <img src="assets/img/blog/${c.image}" alt="" style="width: 30px; height: 30px;">
                                            </div>
                                            <div class="blog__sidebar__recent__item__text">
                                                <h4>${c.name}</h4>
                                            </div>
                                        </a>    
                                    </c:forEach>    
                                </div>
                            </div>
                            <div class="blog__sidebar__item">
                                <h5>Categories</h5>
                                <div class="blog__sidebar__item__categories">
                                    <ul>
                                        <c:forEach items="${newsCategories}" var="c">
                                            <li><a href="./news?category=${c.id}">${c.name}</a></li>
                                            </c:forEach> 
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Section End -->

        <!-- Footer Section Begin -->
        <footer class="footer set-bg" data-setbg="assets/img/footer-bg.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="footer__widget">
                            <h6>WORKING HOURS</h6>
                            <ul>
                                <li>Monday - Friday: 08:00 am – 08:30 pm</li>
                                <li>Saturday: 10:00 am – 16:30 pm</li>
                                <li>Sunday: 10:00 am – 16:30 pm</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="footer__about">
                            <div class="footer__logo">
                                <a href="home"><img src="assets/img/footer-logo.png" alt=""></a>
                            </div>
                            <p>Lorem ipsum dolor amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                                labore dolore magna aliqua.</p>
                            <div class="footer__social">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-instagram"></i></a>
                                <a href="#"><i class="fa fa-youtube-play"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="footer__newslatter">
                            <h6>Subscribe</h6>
                            <p>Get latest updates and offers.</p>
                            <form action="#">
                                <input type="text" placeholder="Email">
                                <button type="submit"><i class="fa fa-send-o"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="copyright">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-7">
                            <p class="copyright__text text-white"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            </p>
                        </div>
                        <div class="col-lg-5">
                            <div class="copyright__widget">
                                <ul>
                                    <li><a href="#">Privacy Policy</a></li>
                                    <li><a href="#">Terms & Conditions</a></li>
                                    <li><a href="#">Site Map</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer Section End -->

        <!-- Search Begin -->
        <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>
        <!-- Search End -->

        <!-- Js Plugins -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.nice-select.min.js"></script>
        <script src="assets/js/jquery.barfiller.js"></script>
        <script src="assets/js/jquery.magnific-popup.min.js"></script>
        <script src="assets/js/jquery.slicknav.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/jquery.nicescroll.min.js"></script>
        <script src="assets/js/main.js"></script>
    </body>

</html>