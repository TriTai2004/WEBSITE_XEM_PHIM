<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
       <div class="breadcrumb-option spad set-bg" data-setbg="./assets/img/breadcrumb-bg.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Video </h2>
                        <div class="breadcrumb__links">
                            <a href="#">Trang chủ</a>
                            <span>Chi tiết</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <main class="portfolio pt-2">
        <section class="detail__video container ">
            <div class="video">
                <iframe src="${ video.urlVideo }" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
            </div> 
            <div class="detail">
                <div class="row">
                    <div class="col-10">
                        <h5 class="text-white">${ video.title }</h5>
                    </div>
                    <div class="col-2 like__share">
                        <a class="${ like == null ? '':'text-danger' }" style="cursor: pointer;" id="like-${ video.id }" onClick="like(${sessionScope.user.id == null ? 0 : sessionScope.user.id}, ${ video.id })"><i class="bi bi-heart-fill"></i></a>
                        <a href=""><i class="bi bi-share-fill"></i></a>
                        <span class="text-white d-block">Lượt xem: ${ video.views } </span>
                    </div>
                </div>
                <div class="description">
                    <h6>Mô tả</h6>
                    <p>${ video.description }</p>
                </div>
            </div>
            <section class="counter">
                <div class="container p-0">
                    <h2 data-aos="fade-up">Video khác</h2>
                    <div  class="slider d-flex" data-aos="fade-up">
                    	<c:forEach items="${ list }" var="video">
	                        <a href="/Assignment_2/video-chi-tiet?id=${ video.id }" class="counter__content">
	                                <img src="/Assignment_2/assets${ video.poster }" alt="">
	                                <div class="title">
	                                    <p>${ video.title }</p>
	                                </div>
	                        </a>
                        </c:forEach>
                    </div>
                </div>
            </section>
    </section>
    </main>