
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <title>Export form</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
            crossorigin="anonymous">

    <style>
        form {
            margin-bottom: 60px;
            margin-top: 10px;
            padding: 10px;
        }
    </style>

</head>
<body>
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <jsp:include page="../fragments/menu.jsp" />

        </div>
    </nav>


    <div>
        <h3>Excel Export</h3>
    </div>




    <div class="row">
        <div class="col">
        <f:form action="getNiveau" method="POST" modelAttribute="niveau">

            <f:select path="titre" items="${Listniveau}" class="form-control" />

    </div>
    </div>
<div>
    <div style="text-align: right">
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</div>
    </f:form>
        <jsp:include page="../fragments/adminfooter.jsp" />
