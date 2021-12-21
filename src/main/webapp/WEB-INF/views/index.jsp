<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/index.css">
</head>
<body>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <main>
        ${cnt.book}
        ${cnt.member}
        ${cnt.rental}
        <h1>도서관리 대시보드 (Book management dashboard)</h1>
        <div class="content_area">
            <div class="book_info">
                <h2><i class="fas fa-book"></i> 도서 정보</h2>
                <p> 총 등록 도서 : <span>${cnt.book[0]}권</span></p>
                <p> 대출 가능 도서 : <span>${cnt.book[1]}권</span></p>
                <p> 대출 불가능 도서 : <span>${cnt.book[2]}권</span></p>
                <p><i class="far fa-clock"></i> 업데이트 날짜 : <span><fmt:formatDate value="${update.book}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
            </div>
            <div class="member_info">
                <h2><i class="fas fa-address-book"></i> 회원 정보</h2>
                <p> 총 등록 회원 : <span>${cnt.member[0]}명</span></p>
                <p> 신규 가입 회원 : <span>${cnt.member[1]}명</span></p>
                <p> 정지 회원 : <span>${cnt.member[2]}명</span></p>
                <p> 탈퇴 회원 : <span>${cnt.member[3]}명</span></p>
                <p><i class="far fa-clock"></i> 업데이트 날짜 : <span><fmt:formatDate value="${update.member}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
            </div>
            <div class="book_rental_info">
                <h2><i class="fas fa-book-reader"></i> 대출/반납 정보</h2>
                <p> 대출 도서 수 : <span>${cnt.rental[0]}권</span></p>
                <p> 반납 도서 수 : <span>${cnt.rental[1]}권</span></p>
                <p><i class="far fa-clock"></i> 업데이트 날짜 : <span>2021-12-09 12:00:00</span></p>
            </div>
        </div>
    </main>
</body>
</html>