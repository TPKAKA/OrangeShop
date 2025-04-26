<%-- 
    Document   : shop
    Created on : Jan 10, 2024, 2:13:17 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Orange Bakery</title>
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
        <style>
            /* CSS cho nút "Add to cart" */
            .add-to-cart-button {
                background-color: #ff7f0e; /* Màu cam */
                color: #fff; /* Màu chữ trắng */
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            /* Hover effect */
            .add-to-cart-button:hover {
                background-color: #ff9500; /* Màu cam nhạt khi hover */
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

        <!-- Thư viện Select2 (nếu sử dụng) -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/assets/css/select2.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/assets/js/select2.min.js"></script>
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
                <a href="./home"><img src="assets/img/logo.png" alt=""></a>
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
                                            <div class="header__top__right">
                                                <div class="header__top__right__links">
                                                    <a  href="UserProfile?uid=${sessionScope.customer.getUserID()}" >
                                                        <img class="avatar avatar-ex-small rounded-circle" src="${customer.getImage()}" alt="Avatar">
                                                    </a>
                                                </div>
                                            </div>


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
                            <h2>Shop</h2>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="breadcrumb__links">
                            <a href="./home">Home</a>
                            <span>Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->

        <!-- Shop Section Begin -->
        <section class="shop spad">
            <div class="container">
                <div class="shop__option">
                    <div class="row">
                        <div class="col-lg-7 col-md-7">
                            <div class="shop__option__search">
                                <form action="search" method="post">
                                    <select onchange="redirectOnChange(this)">
                                        <option data-href="page">Category</option>
                                        <c:forEach items="${listCC}" var="o">
                                            <option value="${o.categoryid}" data-href="category?categoryid=${o.categoryid}">
                                                ${o.categoryname}
                                            </option>
                                        </c:forEach>
                                    </select>

                                    <script>
                                        function redirectOnChange(select) {
                                            var selectedOption = select.options[select.selectedIndex];
                                            var href = selectedOption.getAttribute("data-href");
                                            window.location.href = href;
                                        }
                                    </script>


                                    <input name="txt" type="text" placeholder="Search">
                                    <button type="submit"><i class="fa fa-search"></i></button>
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-5">
                            <div class="shop__option__right">
                                <form action="sort" method="get">
                                    <select name="sortOption">
                                        <option value="default" >Filter</option>
                                        <option value="lowToHigh">Low To Hight</option>
                                        <option value="highToLow">High To Low</option>
                                        <option value="az">A-Z</option>
                                    </select>
                                    <button type="submit">sort</button>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${listP}" var="p">
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="product__item">
                                <c:forEach items="${img}" var="i">
                                    <c:if test="${p.productid eq i.productid}">
                                        <div class="product__item__pic set-bg" data-setbg="assets/img/shop/${i.imglink}">
                                            <div class="product__label">
                                                <c:forEach items="${listCC}" var="o">
                                                    <c:if test="${tag eq o.categoryid}">
                                                        <span>${o.categoryname}</span>
                                                    </c:if>
                                                </c:forEach>
                                            </div></div>
                                        </c:if>
                                    </c:forEach>

                                <form action="Cart?action=add&productid=${p.productid}" method="post">
                                    <div class="product__item__text">
                                        <h6><a href="detail?productid=${p.productid}&categoryid=${tag}">${p.name}</a></h6>
                                        <div class="product__item__price formattedPrice">${p.price}</div>
                                        <div class="cart_add">
                                            <button type="submit" class="add-to-cart-button">Add to cart</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
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


                </div>


                <div class="shop__last__option">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="shop__pagination">
                                <c:forEach var="c" begin="1" end="${endP}">
                                    <a href="sort?index=${c}">${c}</a>
                                </c:forEach>
                                <a href="#"><span class="arrow_carrot-right"></span></a>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">

                        </div>
                    </div>
                </div>
        </section>
        <!-- Shop Section End -->

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
                                <a href="#"><img src="assets/img/footer-logo.png" alt=""></a>
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