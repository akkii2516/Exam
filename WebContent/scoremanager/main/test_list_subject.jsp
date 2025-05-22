<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>

	<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
      <!-- 検索フォーム -->
      <form action="TestListSubjectExecute.action" method="get" class="row align-items-center" style="margin-bottom:-12px">
          <!-- 科目情報 -->
          <div class="col-2" style="margin-left:15px">
            <label class="form-label">科目情報</label>
          </div>

          <!-- 入学年度 -->
          <div class="col-2">
            <label class="form-label" for="student-f1-select" style="margin-left:-15px">入学年度</label>
            <select class="form-select" style="margin-left:-15px" id="student-f1-select" name="f1">
              <option value="0">--------</option>
              <c:forEach var="year" items="${f1}">
                <option value="${year}" <c:if test="${year == entYearStr}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>

          <!-- クラス -->
          <div class="col-2">
            <label class="form-label" for="student-f2-select" style="margin-left:-15px">クラス</label>
            <select class="form-select" style="margin-left:-15px" id="student-f2-select" name="f2">
              <option value="0">--------</option>
              <c:forEach var="num" items="${f2}">
                <option value="${num}" <c:if test="${num == classNum}">selected</c:if>>${num}</option>
              </c:forEach>
            </select>
          </div>

          <!-- 科目 -->
          <div class="col-4">
            <label class="form-label" style="margin-left:-15px" for="student-f3-select">科目</label>
            <select class="form-select" style="margin-left:-15px" id="student-f3-select" name="f3">
              <option value="0">--------</option>
              <c:forEach var="subject" items="${f3}">
                <option value="${subject.cd}" <c:if test="${subject.cd == subjectcd}">selected</c:if>>${subject.name}</option>
              </c:forEach>
            </select>
          </div>

          <!-- 検索ボタン -->
          <div class="col-2 d-flex justify-content-end" style="margin-left: -50px;">
            <label class="form-label d-block invisible">検索</label>
            <button class="btn btn-secondary" id="filter-button" style="background-color: #6c757d; color: white;">検索</button>
          </div>
       </form>
          <hr class="mx-3 my-3" style="width: 95%; margin-top:-15px" />

         <!-- ▼学生情報フォーム▼ -->
        <form action="TestListStudentExecute.action" method="get" class="row align-items-center">
          <!-- 学生情報 -->
          <div class="col-2">
            <label class="form-label" style="margin-left:15px">学生情報</label>
          </div>

		<!-- 学生番号 -->
		<div class="col-4" style="margin-top:-10px">
		  <label class="form-label" for="student-no-input">学生番号</label>
		  <input type="text" class="form-control" id="student-no-input" name="studentNo" placeholder="学生番号を入力してください">
		</div>



          <!-- 検索ボタン -->
          <div class="col-2 d-flex justify-content-end" style="margin-left: -10px;">
            <label class="form-label d-block invisible">検索</label>
            <button class="btn btn-secondary" type="submit" style="background-color: #6c757d; color: white;">検索</button>
          </div>
        </form>
      </div>

      <c:if test="${empty test_list}">
	  	<div class="mt-2">
	  		${errors.get("error2")}
  		</div>
      </c:if>

      <c:if test="${not empty test_list}">
        <div>科目：${subjects.name}</div>
      </c:if>

      <c:choose>
        <c:when test="${not empty test_list}">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>入学年度</th>
                <th>クラス</th>
                <th>学生番号</th>
                <th>氏名</th>
                <th>1回</th>
                <th>2回</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="test" items="${test_list}">
                <tr>
                  <td>${test.entYear}</td>
                  <td>${test.classNum}</td>
                  <td>${test.studentNo}</td>
                  <td>${test.studentName}</td>
<td>
            ${test.getPoint(1)}
</td>

<td>
            ${test.getPoint(2)}
</td>


                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>
      </c:choose>
    </section>
  </c:param>
</c:import>
