
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

      <!-- エラーメッセージ表示 -->
<c:if test="${not empty error}">
<div class="alert alert-danger mx-4">${error}</div>
</c:if>

      <!-- 検索フォーム -->
<form method="get">
<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

          <!-- 入学年度 -->
<div class="col-3">
<label class="form-label" for="student-f1-select">入学年度</label>
<select class="form-select" id="student-f1-select" name="f1">
<option value="0">--------</option>
<c:forEach var="year" items="${f1}">
<option value="${year}" <c:if test="${year == selectedF1}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>

          <!-- クラス -->
<div class="col-3">
<label class="form-label" for="student-f2-select">クラス</label>
<select class="form-select" id="student-f2-select" name="f2">
<option value="0">--------</option>
<c:forEach var="classNum" items="${f2}">
<option value="${classNum}" <c:if test="${classNum == selectedF2}">selected</c:if>>${classNum}</option>
</c:forEach>
</select>
</div>

          <!-- 科目 -->
<div class="col-3">
<label class="form-label" for="student-f3-select">科目</label>
<select class="form-select" id="student-f3-select" name="f3">
<option value="0">--------</option>
<c:forEach var="subject" items="${f3}">
<option value="${subject.cd}" <c:if test="${subject.cd == selectedF3}">selected</c:if>>
                  ${subject.name}
</option>
</c:forEach>
</select>
</div>

          <!-- 回数 -->
<div class="col-2">
<label class="form-label" for="student-count-select">回数</label>
<select class="form-select" id="student-count-select" name="f4">
<option value="0">--------</option>
<c:forEach var="count" items="${f4}">
<option value="${count}" <c:if test="${count == selectedF4}">selected</c:if>>${count}</option>
</c:forEach>
</select>
</div>

          <!-- 検索ボタン -->
<div class="col-1 text-center">
<button class="btn btn-secondary mt-4" id="filter-button">検索</button>
</div>

        </div>
</form>

      <!-- 検索結果表示 -->
<c:choose>
<c:when test="${not empty tests}">
<div class="mx-4 mb-3">検索結果：${tests.size()}件</div>

          <form action="TestSave.action" method="post">
<table class="table table-hover mx-4">
<thead>
<tr>
<th>入学年度</th>
<th>クラス</th>
<th>学生番号</th>
<th>氏名</th>
<th>点数</th>
</tr>
</thead>
<tbody>
<c:forEach var="test" items="${tests}">
<tr>
<td>${test.student.entYear}</td>
<td>${test.classNum}</td>
<td>${test.student.no}</td>
<td>${test.student.name}</td>
<td>
<input type="hidden" name="studentNoList" value="${test.student.no}" />
<input type="number" name="pointList" value="${test.point}" min="0" max="100" required />
</td>
</tr>
</c:forEach>
</tbody>
</table>
<div class="text-end me-4">
<button type="submit" class="btn btn-primary">登録</button>
</div>
</form>
</c:when>

        <c:otherwise>
          <div class="mx-4">検索条件に一致する学生が見つかりませんでした。</div>
        </c:otherwise>
      </c:choose>
    </section>
  </c:param>
</c:import>



