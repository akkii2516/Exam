<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- base.jsp をインポート -->
<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">
    <section class="me-4">
      <!-- タイトル -->
      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧(学生)</h2>

      <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

        <!-- ▼学生情報フォーム▼ -->
        <form action="TestListStudentExecute.action" method="get" class="row align-items-center">
          <!-- 学生情報 -->
          <div class="col-2" style="margin-left:15px">
            <label class="form-label">学生情報</label>
          </div>

          <!-- 入学年度 -->
          <div class="col-2">
            <label class="form-label" for="entYear-select" style="margin-left:-15px">入学年度</label>
            <select class="form-select" style="margin-left:-15px" id="entYear-select" name="entYear">
              <option value="">--------</option>
              <c:forEach var="year" items="${entYearList}">
                <option value="${year}" <c:if test="${year == selectedEntYear}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>

          <!-- クラス -->
          <div class="col-2">
            <label class="form-label" for="classNum-select" style="margin-left:-15px">クラス</label>
            <select class="form-select" style="margin-left:-15px" id="classNum-select" name="classNum">
              <option value="">--------</option>
              <c:forEach var="classNum" items="${classNumList}">
                <option value="${classNum}" <c:if test="${classNum == selectedClassNum}">selected</c:if>>${classNum}</option>
              </c:forEach>
            </select>
          </div>

          <!-- 学生番号 -->
          <div class="col-4" style="margin-top:-10px">
            <label class="form-label" for="studentNo-select">学生番号</label>
            <select class="form-select" id="studentNo-select" name="studentNo">
              <option value="">--------</option>
              <c:forEach var="student" items="${students}">
                <option value="${student.no}" <c:if test="${student.no == selectedStudentNo}">selected</c:if>>${student.no}</option>
              </c:forEach>
            </select>
          </div>

          <!-- 検索ボタン -->
          <div class="col-2 d-flex justify-content-end" style="margin-left: -10px;">
            <label class="form-label d-block invisible">検索</label>
            <button class="btn btn-secondary" type="submit" style="background-color: #6c757d; color: white;">検索</button>
          </div>
        </form>
      </div>

      <c:choose>
        <c:when test="${not empty testList}">
          <table class="table table-hover">
            <tr>
              <th>科目名</th>
              <th>科目コード</th>
              <th>回数</th>
              <th>点数</th>
            </tr>
            <c:forEach var="test" items="${testList}">
              <tr>
                <td>${test.subject.name}</td>
                <td>${test.subject_cd}</td>
                <td>${test.no}</td>
                <td>${test.point}</td>
              </tr>
            </c:forEach>
          </table>
        </c:when>
        <c:otherwise>
          <p>該当する成績データはありません。</p>
        </c:otherwise>
      </c:choose>

    </section>
  </c:param>
</c:import>
