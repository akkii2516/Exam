<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- base.jsp をインポート（パラメータ付き） -->
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<!-- ① タイトル -->
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>

      <!-- ② 新規登録リンク -->
<div class="my-2 text-end px-4">
<!-- <a href="ClassCreate.action">新規登録</a> -->
</div>

      <!-- ③ 一覧表示 -->
<div class="px-4">
<table class="table table-bordered">
<thead class="table-light">
<tr>
<th>クラス</th>
</tr>
</thead>
<tbody>
<c:forEach var="classNum" items="${classNum}">
<tr>
<td>${classNum.class_num}</td>
<td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
<td><a href="SubjectDelete.action?cd=${subject.cd}" onclick="return confirm('削除してもよろしいですか？');">削除</a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</section>
</c:param>
</c:import>