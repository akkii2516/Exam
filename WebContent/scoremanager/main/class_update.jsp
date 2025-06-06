<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス情報変更</h2>
<form action="ClassUpdateExecute.action" method="get">

<div class="mb-3">
<label for="subject-name" class="form-label">クラス名</label>
<input type="text" class="form-control" id="ClassNum" name="newClassNum" value="${classNum.class_num}" required>
<input type="hidden" name="oldClassNum" value="${classNum.class_num}">
</div>


<div class="d-flex gap-2 mb-3">
<button class="btn btn-secondary" name="end" id="filter-button"
						style="writing-mode: horizontal-tb; white-space: nowrap; background-color: #007BFF; color: white;">
						変更
</button>
</div>

<small><font face="Arial">クラス情報を変更すると学生情報も変更されます</font></small>



<div class="col-4" style="margin-top:10px">
<a href="ClassList.action">戻る</a>
</div>
</form>
</c:param>
</c:import>