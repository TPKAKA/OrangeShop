<%-- 
    Document   : index
    Created on : Jan 15, 2024, 12:56:45 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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
        <title>Orange Cake</title>
        <link rel="icon" type="image" href="assets/img/favicon.png">


        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css"  />
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
    </head>
    <body>
        <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
    <df-messenger
        intent="WELCOME"
        chat-title="OrangeShop"
        agent-id="44bc72a9-d8f7-4f08-b7bf-ff97c8fdbe7a"
        language-code="vi"
        ></df-messenger>
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
                                    <form action="search" method="post" class="form-inline my-2 my-lg-0">
                                        <div class="input-group input-group-sm">
                                            <input oninput="searchByName(this)" value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                                            <div class="input-group-append">
                                                <button type="submit" class="btn btn-secondary btn-number">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>

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
                </div>
                <div class="canvas__open"><i class="fa fa-bars"></i></div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <nav class="header__menu mobile-menu">
                        <ul>

                            <c:forEach items="${m}" var="menu">
                                <li class><a href="homecontrol?pid=${menu.id}" style="text-decoration: none;">${menu.name}</a></li>
                                </c:forEach>
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
    <c:if test="${messs != null}">
        <div  id="messageDiv" style="color: orange; display: flex; justify-content: center; text-align: center;">
            ${messs}
        </div>
    </c:if>
    <c:if test="${mess != null}">
        <div id="messageDiv" style="color: red; display: flex; justify-content: center; text-align: center;">
            ${mess}
        </div>
    </c:if>


    <section class="hero">

        <div class="hero__slider owl-carousel">
            <c:forEach items="${n}" var="n">
                <div class="hero__item set-bg" data-setbg="assets/img/blog/${n.image}">
                    <div class="container">
                        <div class="row d-flex justify-content-center">
                            <div class="col-lg-8">
                                <div class="hero__text">
                                    <h2>${n.name}</h2>
                                    <a href="#" class="primary-btn">Our cakes</a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </c:forEach>
        </div>

    </section>
    <!-- Hero Section End -->

    <!-- About Section Begin -->
    <section class="about spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="about__text">
                        <div class="section-title">
                            <span>About Cake shop</span>
                            <h2>Cakes and bakes from the house of Queens!</h2>
                        </div>
                        <p>The "Cake Shop" is a Jordanian Brand that started as a small family business. The owners are
                            Dr. Iyad Sultan and Dr. Sereen Sharabati, supported by a staff of 80 employees.</p>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="about__bar">
                        <div class="about__bar__item">
                            <p>Cake design</p>
                            <div id="bar1" class="barfiller">
                                <div class="tipWrap"><span class="tip"></span></div>
                                <span class="fill" data-percentage="95"></span>
                            </div>
                        </div>
                        <div class="about__bar__item">
                            <p>Cake Class</p>
                            <div id="bar2" class="barfiller">
                                <div class="tipWrap"><span class="tip"></span></div>
                                <span class="fill" data-percentage="80"></span>
                            </div>
                        </div>
                        <div class="about__bar__item">
                            <p>Cake Recipes</p>
                            <div id="bar3" class="barfiller">
                                <div class="tipWrap"><span class="tip"></span></div>
                                <span class="fill" data-percentage="90"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- About Section End -->

    <!-- Categories Section Begin -->
    <div class="categories">
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">

                    <c:forEach items="${c}" var="category">
                        <a href="category?categoryid=${category.categoryid}">
                            <div class="categories__item">
                                <div class="categories__item__icon">
                                    <span class="flaticon-029-cupcake-3"></span>
                                    <h5>${category.categoryname}</h5>
                                </div>
                            </div>
                        </a>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
    <!-- Categories Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <c:forEach items="${pr}" var="p">

                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="product__item">
                            <c:forEach items="${img}" var="i">
                                <c:if test="${p.productid eq i.productid}">
                                    <a href="detail?productid=${p.productid}">
                                        <div class="product__item__pic set-bg" data-setbg="assets/img/shop/${i.imglink}"></div>
                                    </a>
                                </c:if> 
                            </c:forEach>
                            <form action="Cart?action=addnow&productid=${p.productid}" method="post">
                                <div class="product__item__text">
                                    <h6><a href="detail?productid=${p.productid}">${p.name}</a></h6>
                                    <div class="product__item__price formattedPrice">${p.price}</div>
                                    <div class="cart_add">
                                        <button type="submit" class="add-to-cart-button">Order now</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                </c:forEach>

            </div>

    </section>
    <!-- Product Section End -->



    <!--Instagram Section Begin--> 
    <section class="instagram spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 p-0">
                    <div class="instagram__text">
                        <div class="section-title">
                            <span>Follow us on instagram</span>
                            <h2>Sweet moments are saved as memories.</h2>
                        </div>
                        <h5><i class="fa fa-instagram"></i> @sweetcake</h5>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-6">
                            <div class="instagram__pic">
                                <img src="assets/img/instagram/instagram-1.jpg" alt="">
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-6">
                            <div class="instagram__pic middle__pic">
                                <img src="assets/img/instagram/instagram-2.jpg" alt="">
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-6">
                            <div class="instagram__pic">
                                <img src="assets/img/instagram/instagram-3.jpg" alt="">
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-6">
                            <div class="instagram__pic">
                                <img src="assets/img/instagram/instagram-4.jpg" alt="">
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-6">
                            <div class="instagram__pic middle__pic">
                                <img src="assets/img/instagram/instagram-5.jpg" alt="">
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-6">
                            <div class="instagram__pic">
                                <img src="assets/img/instagram/instagram-3.jpg" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Instagram Section End -->

    <!-- Map Begin -->
    <div class="map">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-7">

                </div>
            </div>
        </div>
        <div class="map__iframe">
            <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d10784.188505644011!2d19.053119335158936!3d47.48899529453826!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sbd!4v1543907528304" height="300" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
        </div>
    </div>
    <!-- Map End -->

    <!-- Footer Section Begin -->
    <footer class="footer set-bg" data-setbg="assets/img/footer-bg.jpg">
        <div class="container">

            <div class="row">
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="footer__widget">
                        <h6>WORKING HOURS</h6>
                        <ul>
                            <c:forEach items="${time}" var="time">
                                <li>Monday - Friday: ${time.name}</li>
                                </c:forEach>
                                <c:forEach items="${address}" var="address">
                                <li>Address: ${address.name}</li> 
                                </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="footer__about">
                        <div class="footer__logo">
                            <a href="#"><img src="assets/img/footer-logo.png" alt=""></a>
                        </div>
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
                        <h6>Contact</h6>
                        <ul>
                            <c:forEach items="${mail}" var="mail">
                                <li>Email: ${mail.name}</li> 
                                </c:forEach>
                                <c:forEach items="${phone}" var="phone">
                                <li>Phone: ${phone.name}</li> 
                                </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <div class="copyright">
            <div class="container">
                <div class="row">

                    <div class="col-lg-5">
                        <div class="copyright__widget">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <!--    <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>-->
    <!-- Search End -->

    <!-- Js Plugins -->
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
    <script src="assets/js/jquery-3.3.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.nice-select.min.js"></script>
    <script src="assets/js/jquery.barfiller.js"></script>
    <script src="assets/js/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/jquery.slicknav.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <script src="assets/js/jquery.nicescroll.min.js"></script>
    <script src="assets/js/main.js"></script>
    <script>
        setTimeout(function () {
            var messageDiv = document.getElementById('messageDiv');
            messageDiv.style.display = 'none';
        }, 5000);
    </script>

</body>
</html>
