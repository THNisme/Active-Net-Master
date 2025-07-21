<%-- 
    Document   : about
    Created on : Jul 7, 2025, 9:08:48 PM
    Author     : BACH YEN
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Giới thiệu</title>

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
        <link rel="stylesheet" href="./assets/css/about.css">
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
                            <a href="product" class="nav-item-link active">
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

        <section class="about-header">
            <div class="about-video-container">
                <video autoplay muted loop playsinline>
                    <source src="./assets/video/Clip-Bg-About.webm" type="video/webm">
                </video>
                <div class="about-overlay">
                    <img src="./assets/img/logo/fac/Logo-white-vertical.png" alt="Logo" class="about-header-img">
                    <ul class="about-header-list">
                        <li class="about-header-list-item"><strong>Theo dõi F-Active tại:</strong></li>
                        <a href="" class="about-header-item-link">
                            <li class="about-header-list-item">Facebook</li>
                        </a>
                        <a href="" class="about-header-item-link">
                            <li class="about-header-list-item">Tiktok</li>
                        </a>
                        <a href="" class="about-header-item-link">
                            <li class="about-header-list-item">Youtube</li>
                        </a>
                        <a href="" class="about-header-item-link">
                            <li class="about-header-list-item">clbfactive1420@gmail.com</li>
                        </a>
                    </ul>
                </div>
            </div>


        </section>

        <section class="about-text-content">
            <!-- INTRODUCE CLUB -->
            <div class="container">
                <h1 class="section-heading"><span class="section-heading-pink">F-Active</span> chúng tôi là</h1>

                <h3 class="about-sub-description">
                    Câu lạc bộ sự kiện với những người trẻ có niềm đam mê trong lĩnh vực truyền thông, cùng nhau gắn kết và
                    mang
                    lại những giá trị cho cộng đồng trẻ
                </h3>

                <p class="about-text-content-para">
                    F-Active mong muốn mang đến cho cộng đồng sinh viên FPT nói riêng và người trẻ nói chung những dự án
                    (cuộc thi, hoạt động, sự kiện, hội thảo, workshop, talkshow, chuyên mục,...)
                </p>
                <p class="about-text-content-para">
                    Đề cao sự sáng tạo và luôn làm mới mẻ nội dung, câu lạc bộ muốn mang đến giá trị, sự quan tâm, niềm yêu
                    thích đến người trẻ thông qua từng dự án.
                </p>
            </div>
        </section>

        <section class="about-birthday">
            <!-- CLUB'S BIRTHDAY -->
            <div class="container">
                <h1 class="section-heading"><span class="section-heading-pink">0107</span> là ngày</h1>

                <h3 class="about-sub-description">
                    Thành lập câu lạc bộ FAC vào năm 2020, mỗi năm đều tổ chức sự kiện sinh nhật câu lạc bộ, một hoạt động ý
                    nghĩa với trải nghiệm khó quên.
                </h3>

                <p class="about-text-content-para">
                    Ngày sinh nhật là một sự kiện nội bộ được tổ chức thường niên với các hoạt động và concept đa dạng:
                </p>



                <!-- SLIDER BIRTHDAT CONCEPT -->

                <div id="carouselClubBirthday" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselClubBirthday" data-bs-slide-to="0" class="active"
                                aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselClubBirthday" data-bs-slide-to="1"
                                aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselClubBirthday" data-bs-slide-to="2"
                                aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="./assets/img/events/slide-sn2.png" class="d-block w-100" alt="Sinh nhat 2 tui">
                        </div>
                        <div class="carousel-item">
                            <img src="./assets/img/events/slide-sn3.png" class="d-block w-100" alt="Sinh nhat 3 tui">
                        </div>
                        <div class="carousel-item">
                            <img src="./assets/img/events/slide-sn4.png" class="d-block w-100" alt="Sinh nhat 4 tui">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselClubBirthday"
                            data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselClubBirthday"
                            data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>


                <div class="birthday-subcontent-wrapper">
                    <!-- Phát triển -->
                    <h3 class="sub-description">
                        <i class="bi bi-person-arms-up"></i>
                        Sự phát triển
                    </h3>

                    <p class="about-text-content-para">
                        Sau nhiều năm định hướng và phát triển, F-Active dần lấn sân vào mảng truyền thông sự kiện với nhiều
                        dự án mang các màu sắc đa dạng đã triển khai thành công như: Sắc Việt, Nhất Huyền, CamCare, Triển
                        lãm Hương,...
                    </p>

                    <!-- Tầm nhìn & sứ mệnh -->
                    <h3 class="sub-description">
                        <i class="bi bi-stars"></i>
                        Tầm nhìn và Sứ mệnh
                    </h3>

                    <p class="about-text-content-para">
                        F-Active đặt mục tiêu trở thành Câu lạc bộ hàng đầu trong lĩnh vực truyền thông tại trường đại học
                        FPT nói riêng và cộng đồng người trẻ nói chung.
                    </p>
                    <p class="about-text-content-para">
                        Đặt sứ mệnh trở thành kênh truyền thông hấp dẫn, hiệu quả. F-Active cam kết mang lại những giá trị
                        quan trọng, trở thành cộng đồng phát triển những cá nhân có niềm đam mê học hỏi, trau dồi kỹ năng,
                        đặc biệt là trong lĩnh vực truyền thông.
                    </p>

                    <!-- Thành tích -->
                    <h3 class="sub-description">
                        <i class="bi bi-award-fill"></i>
                        Thành tích xuất sắc qua các kỳ
                    </h3>
                    <ul class="about-text-content-list">
                        <li>“Câu lạc bộ hoạt động phong trào xuất sắc” - <strong>KỲ SU24</strong></li>
                        <li>“Câu lạc bộ hoạt động phong trào xuất sắc” - <strong>KỲ SP24</strong></li>
                        <li>“Câu lạc bộ hoạt động phong trào xuất sắc” - <strong>KỲ FA23</strong></li>
                        <li>“Câu lạc bộ hoạt động phong trào xuất sắc” - <strong>KỲ SU23</strong></li>
                        <li>“TOP 4 Câu lạc bộ xuất sắc” - <strong>KỲ SU20</strong></li>
                    </ul>
                </div>


            </div>
        </section>

        <section class="about-works">
            <div class="container">
                <h1 class="section-heading">Các <span class="section-heading-pink">sự kiện tâm huyết</span> đã làm</h1>
                <img class="about-works-heading-img" src="./assets/img/logo/fac/Logo-back-text.png" alt="logo">

                <!-- MASONRY GRID -->
                <div class="about-works-grid">
                    <div class="about-works-grid-sizer"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/0107-2.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/cam-care.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/flens.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/dong-va-em.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/gingle-mingle.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/huong.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/luu-dau-chan-huong.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/yem-linh.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/wcstwc.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/tram-den-cham.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/tcs-xgk.jpg"></div>
                    <div class="about-works-grid-item"><img src="./assets/img/events/sac-xuan.jpg"></div>
                    <!-- ... -->
                </div>
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

        <section class="about-contact">
            <div class="container">
                <h1 class="section-heading"><span class="section-heading-pink">Liên hệ</span> chúng tôi</h1>

                <h3 class="about-contact-text">
                    <a href="mailto:clbfactive1420@gmail.com"> clbfactive1420@gmail.com</a>
                </h3>
                <h3 class="about-contact-text">
                    <a href="tel:0704906670">070 490 6670</a>
                </h3>
                <h3 class="about-contact-text">
                    Cầu Rau Răm, An Bình, Ninh Kiều, Cần Thơ, Việt Nam, Thành Phố Cần Thơ, Vietnam
                </h3>

            </div>
        </section>

        <section class="about-booking">
            <div class="container">
                <h1 class="section-heading">Gửi <span class="section-heading-pink">lời nhắn</span> đến chúng tôi</h1>

                <form action="messages?view=add&hook=0" method="post" class="booking-form">
                    <img src="./assets/img/Logo-back-text.png" alt="">
                    <label for="messageInput">Để lại lời nhắn cho chúng tớ nhé:</label>
                    <textarea name="messageInput" id="messageInput" cols="80" rows="10" placeholder="Nhập lời nhắn của bạn"></textarea>

                    <button type="submit" class="btn primary-btn">Gửi lời nhắn</button>
                </form>
            </div>
        </section>


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
                                <a href="home" class="footer-item-link">
                                    <li class="footer-link-list-item">Trang chủ</li>
                                </a>
                                <a href="about" class="footer-item-link">
                                    <li class="footer-link-list-item">Giới thiệu</li>
                                </a>
                                <a href="product" class="footer-item-link">
                                    <li class="footer-link-list-item">Cửa hàng</li>
                                </a>
                                <a href="contact" class="footer-item-link">
                                    <li class="footer-link-list-item">Liên hệ</li>
                                </a>
                                <a href="login" class="footer-item-link">
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


        <!-- MASONRY INIT -->
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var elem = document.querySelector('.about-works-grid');
                var msnry = new Masonry(elem, {
                    itemSelector: '.about-works-grid-item',
                    columnWidth: '.about-works-grid-sizer',
                    percentPosition: true
                });
            });
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>

        <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>


    </body>

</html>
