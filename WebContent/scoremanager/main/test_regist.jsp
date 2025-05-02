<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>

      <div class="my-2 text-end px-4">
        <a href="StudentCreate.action">新規登録</a>
      </div>

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
                <option value="${subject}" <c:if test="${subject == selectedF3}">selected</c:if>>${subject}</option>
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
		<c:choose>
		  <c:when test="${not empty tests}">
		    <div>検索結果：${tests.size()}件</div>

		    <form action="TestSave.action" method="post">
		      <table class="table table-hover">
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
				    <td>${test.entYearSet}</td>
				    <td>${test.cNumlist}</td>
				    <td>${test.list}</td>
				    <td>${test.countList}</td>
				    <td><input name="point" value="${test.point}"></td>
				  </tr>

		              <td>		          </c:forEach>
		        </tbody>
		      </table>
		      <div class="text-end">
		        <button type="submit" class="btn btn-primary">登録</button>
		      </div>
		    </form>

		  </c:when>
		  <c:otherwise>
		    <div>学生情報が存在しませんでした。</div>
		  </c:otherwise>
		</c:choose>

    </section>
  </c:param>
</c:import>
