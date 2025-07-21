<%@page import="model.User"%>
<!DOCTYPE html>
<html lang="en">
    <%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Đặt hàng thành công</title>

        <!-- LINK BOOTSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <!-- LINK FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
              rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Playpen+Sans:wght@100..800&display=swap" rel="stylesheet">

        <!-- LINK CSS -->
        <link rel="stylesheet" href="./assets/css/cart.css">
        <link rel="stylesheet" href="./assets/css/base.css">

    </head>

    <body>
        <nav class="navbar nav-bg">
            <div class="container">
                <div class="row nav-wrapper">
                    <div class="col-md-3">
                        <a class="nav-logo-link" href="">
                            <img src="./assets/img/logo/fac/Logo-Nav.png" alt="F-Active Logo" class="nav-brand-img">
                        </a>
                    </div>
                    <div class="col-md-6">
                        <ul class="nav-list">
                            <a href="home" class="nav-item-link">
                                <li class="nav-list-item">Trang chủ</li>
                            </a>
                            <a href="about" class="nav-item-link">
                                <li class="nav-list-item">Giới thiệu</li>
                            </a>
                            <a href="product" class="nav-item-link">
                                <li class="nav-list-item">Cửa hàng</li>
                            </a>
                            <a href="contact" class="nav-item-link">
                                <li class="nav-list-item">Liên hệ</li>
                            </a>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <%
                            User d = (User) session.getAttribute("user");
                            if (d == null) {

                        %>
                        <a href="login" class="btn primary-btn btn-nav-login active">Đăng nhập</a>
                        <%                        } else {
                        %>
                        <a href="logout" class="btn primary-btn btn-nav-login active"><i class="bi bi-box-arrow-right"></i></a>
                        <a href="profile" class="btn primary-btn btn-nav-login active"><i class="bi bi-person-circle"></i></a>
                        <a href="cart" class="btn primary-btn btn-nav-login active"><i class="bi bi-bag"></i></a>
                        
                            <%                        }
                            %>
                    </div>
                </div>
        </nav>

        <main class="container">
            <section class="order-success">
                <h1 class="order-success section-heading">
                    Đơn hàng đã được đặt
                    <span class="section-heading-pink">thành công</span>
                </h1>
                <img src="./assets/img/icons/tich.png" alt="dau tich" class="logo-img" />
                <p class="order-success-event-info">
                    <strong>Thông tin vé sự kiện và nhận merchandise sẽ <br> được gửi về email của bạn!</strong>
                </p>
                <p class="order-success-event-info">Liên hệ hộ trợ: 0786-963-526</p>
                <p class="order-success-event-info">Email: clbfactive1420@gmail.com</p>
            </section>
        </main>



        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="./assets/img/logo/fac/LogoA-trans.png" alt="F-Active Logo" class="footer-logo-img">
                        <p class="footer-text">
                            Active Net một dự án cộng tác từ Câu lạc bộ sự kiện F-Active cùng HiFive Team
                        </p>
                        <ul class="footer-social-link-list">
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-facebook"></i>
                                </li>
                            </a>
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-youtube"></i>
                                </li>
                            </a>
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-tiktok"></i>
                                </li>
                            </a>
                        </ul>
                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer-content">
                            <h4 class="footer-heading">
                                Active Net
                            </h4>
                            <ul class="footer-link-list">
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">Trang chủ</li>
                                </a>
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">Giới thiệu</li>
                                </a>
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">Cửa hàng</li>
                                </a>
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">Liên hệ</li>
                                </a>
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">đăng nhập</li>
                                </a>
                            </ul>
                        </div>

                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer-content">
                            <h4 class="footer-heading">
                                Get In Touch
                            </h4>
                            <ul class="footer-link-list">
                                <a href="mailto: clbfactive1420@gmail.com" class="footer-item-link">
                                    <li class="footer-link-list-item">clbfactive1420@gmail.com</li>
                                </a>
                                <a href="tel:0704906670" class="footer-item-link">
                                    <li class="footer-link-list-item">070 490 6670</li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <p class="footer-copyright">Copyright © 2025 Hifive Team | All Rights Deserved @hifiveteam</p>
                </div>
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
    </body>

</html>