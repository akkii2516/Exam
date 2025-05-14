<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
      <!-- 検索フォーム -->
<form method="get">
<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

  <!-- 科目情報 -->
  <div class="col-2" style = "margin-left:15px">
    <label class="form-label">科目情報</label>
  </div>

  <!-- 入学年度 -->
  <div class="col-2">
    <label class="form-label" for="student-f1-select" style = "margin-left:-15px">入学年度</label>
    <select class="form-select" style = "margin-left:-15px" id="student-f1-select" name="f1">
      <option value="0">--------</option>
      <c:forEach var="year" items="${f1}">
        <option value="${year}" <c:if test="${year == selectedF1}">selected</c:if>>${year}</option>
      </c:forEach>
    </select>
  </div>

  <!-- クラス -->
  <div class="col-2">
    <label class="form-label" for="student-f2-select" style = "margin-left:-15px">クラス</label>
    <select class="form-select" style = "margin-left:-15px" id="student-f2-select" name="f2">
      <option value="0">--------</option>
      <c:forEach var="classNum" items="${f2}">
        <option value="${classNum}" <c:if test="${classNum == selectedF2}">selected</c:if>>${classNum}</option>
      </c:forEach>
    </select>
  </div>

  <!-- 科目 -->
  <div class="col-4">
    <label class="form-label" style = "margin-left:-15px" for="student-f3-select">科目</label>
    <select class="form-select" style = "margin-left:-15px" id="student-f3-select" name="f3">
      <option value="0">--------</option>
      <c:forEach var="subject" items="${f3}">
        <option value="${subject.cd}" <c:if test="${subject.cd == selectedF3}">selected</c:if>>
          ${subject.name}
        </option>
      </c:forEach>
    </select>
  </div>

  <!-- 検索ボタン -->
    <!-- 検索ボタン -->
    <div class="col-2 d-flex justify-content-end" style="margin-left: -50px;">
      <label class="form-label d-block invisible">検索</label>
      <button class="btn btn-secondary btn-sm" id="filter-button" style="background-color: #6c757d; color: white;">検索</button>
    </div>
	<hr class="mx-3 mb-4" style="margin-top:5px" />
  <!-- 科目情報 -->
  <div class="col-2">
    <label class="form-label" style = "margin-left:15px">学生情報</label>
  </div>
  <!-- 学生番号 -->
<div class="col-4" style = "margin-top:-10px">
  <label class="form-label" for="student-no-select">学生番号</label>
  <select class="form-select" id="student-no-select" name="studentNo">
    <option value="0">--------</option>
    <c:forEach var="student" items="${students}">
      <option value="${student.no}" <c:if test="${student.no == selectedStudentNo}">selected</c:if>>
        ${student.no} - ${student.name}
      </option>
    </c:forEach>
  </select>

</div>
    <!-- 検索ボタン -->
    <div class="col-2 d-flex justify-content-end" style="margin-left: -30px;">
      <label class="form-label d-block invisible">検索</label>
      <button class="btn btn-secondary btn-sm" id="filter-button" style="background-color: #6c757d; color: white;">検索</button>
    </div>
</div>
<p style="color:#33FFFF;">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
</form>
</section>
</c:param>
</c:import>
