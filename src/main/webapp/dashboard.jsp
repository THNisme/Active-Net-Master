<%-- 
    Document   : dashboard
    Created on : Jul 7, 2025, 9:09:38 PM
    Author     : BACH YEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
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
    <link rel="stylesheet" href="./assets/css/dashboard.css">
    <link rel="stylesheet" href="./assets/css/base.css">

</head>

<body>

    <nav class="navbar bg-body-tertiary fixed-top">
        <div class="container-fluid">
            <div class="dashboard-nav-wrapper">
                <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                    data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                    <button class="btn primary-btn" type="submit"><i class="bi bi-search"></i></button>
                </form>

                <button type="button" class="btn primary-btn" data-bs-toggle="modal" data-bs-target="#addNewModal">
                    <i class="bi bi-plus-circle"></i>
                </button>
            </div>


            <div class="dashboard-nav-wrapper">
                <img src="./assets/img/logo/fac/LogoA-trans.png" alt="">
                <a class="navbar-brand dashboard-nav-brand" href="#">
                    Welcome, [user.name]
                </a>
            </div>
            <div class="offcanvas offcanvas-start dashboard-offcanvas" tabindex="-1" id="offcanvasNavbar"
                aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">
                        <a class="navbar-brand" href="#">
                            <img src="./assets/img/logo/fac/Logo-back-text.png" alt="">
                        </a>
                    </h5>
                    <!-- <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button> -->
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                        <!-- Đơn hàng -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Đơn hàng
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Quản lý đơn hàng</a></li>
                                <li><a class="dropdown-item" href="#">Chi tiết đơn hàng</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Thông tin thanh toán</a></li>
                            </ul>
                        </li>
                        <!-- Sản phẩm -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Sản phẩm
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Quản lý sản phẩm</a></li>
                                <li><a class="dropdown-item" href="#">Danh mục sản phẩm</a></li>
                                <li>
                                    <!-- <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li> -->
                            </ul>
                        </li>

                        <!-- Sự kiện -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Sự kiện
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Quản lý vé</a></li>
                                <li><a class="dropdown-item" href="#">Danh mục vé</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Thông tin sự kiện</a></li>
                            </ul>
                        </li>

                        <!-- Người dùng -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Người dùng
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Quản lý người dùng</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Tin nhắn</a></li>
                            </ul>
                        </li>
                    </ul>


                </div>
            </div>
        </div>
    </nav>

    <section class="dashboard-content container">
        <h1 class="section-heading">Quản lý <span class="section-heading-pink">Đơn hàng</span></h1>

        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle">
                <thead>
                    <tr class="table-dark text-center">
                        <th scope="col">ID</th>
                        <th scope="col">Email khách hàng</th>
                        <th scope="col">Tổng tiền</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Nội dung chuyển khoản</th>
                        <th scope="col">Ngày đặt</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                </thead>
                <tbody class="text-center">
                    <tr>
                        <th scope="row" class="text-center">1</th>
                        <td>Mark@gmail.com</td>
                        <td>120.000</td>
                        <td>Chưa xử lý</td>
                        <td>0201</td>
                        <td>14-7-2025</td>
                        <td>
                            <div class="table-tools-wrapper text-center">
                                <a href="#" class="btn primary-btn"><i class="bi bi-pencil-square"></i></a>
                                <a href="#" class="btn primary-btn"><i class="bi bi-trash3"></i></a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" class="text-center">2</th>
                        <td>lisa99@yahoo.com</td>
                        <td>85.000</td>
                        <td>Đang giao</td>
                        <td>0387</td>
                        <td>13-7-2025</td>
                        <td>
                            <div class="table-tools-wrapper text-center">
                                <a href="#" class="btn primary-btn"><i class="bi bi-pencil-square"></i></a>
                                <a href="#" class="btn primary-btn"><i class="bi bi-trash3"></i></a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" class="text-center">3</th>
                        <td>john.doe@outlook.com</td>
                        <td>230.000</td>
                        <td>Chưa xử lý</td>
                        <td>0412</td>
                        <td>12-7-2025</td>
                        <td>
                            <div class="table-tools-wrapper text-center">
                                <a href="#" class="btn primary-btn"><i class="bi bi-pencil-square"></i></a>
                                <a href="#" class="btn primary-btn"><i class="bi bi-trash3"></i></a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" class="text-center">4</th>
                        <td>anna.pham@gmail.com</td>
                        <td>175.000</td>
                        <td>Đã hoàn thành</td>
                        <td>0579</td>
                        <td>11-7-2025</td>
                        <td>
                            <div class="table-tools-wrapper text-center">
                                <a href="#" class="btn primary-btn"><i class="bi bi-pencil-square"></i></a>
                                <a href="#" class="btn primary-btn"><i class="bi bi-trash3"></i></a>
                            </div>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>


    </section>

    <!-- Modal Add New -->
    <div class="modal fade mt-5 " id="addNewModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="form-control">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Create new</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                            <label for="floatingInput">Email address</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                            <label for="floatingPassword">Password</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Create</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
</body>

</html>