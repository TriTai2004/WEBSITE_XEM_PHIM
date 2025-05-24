<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <!-- Hero Section Begin -->
    <section class="hero">
        <div class="hero__slider owl-carousel">
            <div class="hero__item set-bg" data-setbg="./assets/img/hero/hero-1.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="hero__text">
                                <span>Tìm kiếm video</span>
                                <h2>Nhiều thể loại được yêu thích</h2>
                                <a href="#" class="primary-btn">Xêm thêm</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hero__item set-bg" data-setbg="./assets/img/hero/hero-1.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="hero__text">
                                <span>Video</span>
                                <h2>Video đa dạng</h2>
                                <a href="#" class="primary-btn">Xêm thêm</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hero__item set-bg" data-setbg="./assets/img/hero/hero-1.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="hero__text">
                                <span>Sở thích</span>
                                <h2>Video bạn yêu thích</h2>
                                <a href="#" class="primary-btn">Xêm thêm</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Services Section Begin -->
    <section class="services spad">
        <h2 data-aos="fade-up">Video nổi bật</h2>
        <ul class="container" data-aos="fade-up">
			<c:forEach items="${ outstanding }" var="video">
	            <li >
	                <img src="/Assignment_2/assets${ video.poster }"
	                    alt="img">
	                <div class="content">
	                    <a href="/Assignment_2/video-chi-tiet?id=${ video.id }">${ video.title }</a>
	                </div>
	            </li>
            </c:forEach>
        </ul>
    </section>
    <!-- Services Section End -->

    <!-- Counter Section Begin -->
    <section class="counter">
        <div class="container">
            <h2 data-aos="fade-up">Mới nhất</h2>
            <div  class="slider d-flex" data-aos="fade-up">
            	<c:forEach items="${ news }" var="video">
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
    <section class="counter">
        <div class="container">
            <h2 data-aos="fade-up">Top yêu thích</h2>
            <div  class="slider d-flex" data-aos="fade-up">
            	<c:forEach items="${ likes }" var="video">
	                <a href="/Assignment_2/video-chi-tiet?id=${ video.id }" class="counter__content">
	                        <img src="/Assignment_2/assets${ video.poster }" alt="img">
	                        <div class="title">
	                            <p>${ video.title }</p>
	                        </div>
	                </a>   
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- Work Section Begin -->
    <section class="work">
    	<div class="container">
    		<div class="work__gallery ">
            <div class="grid-sizer"></div>
            <div data-aos="fade-right" class="work__item small__item set-bg" style="background-image: url('${pageContext.request.contextPath}/assets${ news[0].poster }');"
                data-setbg="${pageContext.request.contextPath}/assets${ news[0].poster }">
                <a href="/Assignment_2/video-chi-tiet?id=${ news[0].id }" class="play-btn"><i class="fa fa-play"></i></a>
                <div class="work__item__hover">
                    <h4>${ news[0].title }</h4>              
                </div>
            </div>
            <div data-aos="fade-right" class="work__item small__item set-bg" style="background-image: url('/Assignment_2/assets${ news[1].poster }');"
                data-setbg="/Assignment_2/assets${ news[1].poster }">
                <a href="/Assignment_2/video-chi-tiet?id=${ news[1].id}" class="play-btn "><i
                        class="fa fa-play"></i></a>
            </div>
            <div data-aos="fade-right" class="work__item small__item set-bg" style="background-image: url('${pageContext.request.contextPath}/assets${ news[2].poster }');"
                data-setbg="${pageContext.request.contextPath}/assets${ news[2].poster }">
                <a href="/Assignment_2/video-chi-tiet?id=${ news[2].id}" class="play-btn "><i
                        class="fa fa-play"></i></a>
            </div>
            <div data-aos="fade-left" class="work__item small__item set-bg" style="background-image: url('${pageContext.request.contextPath}/assets${ news[3].poster }');"
                data-setbg="${pageContext.request.contextPath}/assets${ news[3].poster }">
                <a href="/Assignment_2/video-chi-tiet?id=${ news[3].id }" class="play-btn "><i
                        class="fa fa-play"></i></a>
                <div class="work__item__hover">
                    <h4>${ news[3].title }</h4>
                </div>
            </div>
            <div data-aos="fade-left" class="work__item small__item set-bg" style="background-image: url('${pageContext.request.contextPath}/assets${ news[4].poster }');"
                data-setbg="${pageContext.request.contextPath}/assets${ news[4].poster }">
                <a href="/Assignment_2/video-chi-tiet?id=${ news[4].id }" class="play-btn"><i
                        class="fa fa-play"></i></a>
            </div>
            <div data-aos="fade-left" class="work__item small__item set-bg" style="background-image: url('${pageContext.request.contextPath}/assets${ news[5].poster }');"
                data-setbg="${pageContext.request.contextPath}/assets${ news[5].poster }">
                <a href="/Assignment_2/video-chi-tiet?id=${ news[5].id }" class="play-btn "><i
                        class="fa fa-play"></i></a>
            </div>
            
        </div>
    	</div>
        
    </section>
