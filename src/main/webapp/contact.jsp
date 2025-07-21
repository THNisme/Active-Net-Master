<%-- 
    Document   : contact
    Created on : Jul 7, 2025, 9:09:28 PM
    Author     : BACH YEN
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Liên Hệ</title>

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
        <link rel="stylesheet" href="./assets/css/contact.css">
        <link rel="stylesheet" href="./assets/css/base.css">

    </head>

    <body>

        <nav class="navbar nav-bg">
            <div class="container">
                <div class="row nav-wrapper">
                    <div class="col-md-3">
                        <a class="nav-logo-link" href="#">
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
                            <a href="contact" class="nav-item-link active">
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
                            <%                        }
                            %>
                    </div>
                </div>
        </nav>


        <main>
            <section class="contact">
                <div class="container">
                    <h1 class="section-heading"><span class="section-heading-pink">Liên hệ</span> chúng tôi</h1>

                    <h3 class="contact-text">
                        <a href="mailto:clbfactive1420@gmail.com"> clbfactive1420@gmail.com</a>
                    </h3>
                    <h3 class="contact-text">
                        <a href="tel:0704906670">070 490 6670</a>
                    </h3>
                    <h3 class="contact-text">
                        Cầu Rau Răm, An Bình, Ninh Kiều, TP.Cần Thơ, Việt Nam.
                    </h3>
                </div>
            </section>


            <section class="shop-booking">
                <div class="container">
                    <h1 class="section-heading">Gửi <span class="section-heading-pink">lời nhắn</span> đến chúng tôi</h1>

                    <form action="messages?view=add&hook=1" method="post" class="booking-form">
                        <img src="./assets/img/Logo-back-text.png" alt="">
                        <label for="messageInput">Để lại lời nhắn cho chúng tớ nhé:</label>
                        <textarea name="messageInput" id="messageInput" cols="80" rows="10"
                                  placeholder="Nhập lời nhắn của bạn"></textarea>
                        <button type="submit" class="btn primary-btn">Gửi lời nhắn</button>
                    </form>
                </div>
            </section>


            <!-- SECTION CTA - Xem VÉ -->
            <section class="cta-section">
                <div class="container text-center">
                    <h2 class="cta-heading">
                        <span class="highlight">Mua vé</span> tham gia sự kiện ngay !
                    </h2>
                    <a href="product" class="btn primary-btn mt-3">Xem vé</a>
                </div>
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
                                    <li class="footer-link-list-item">Đăng nhập</li>
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