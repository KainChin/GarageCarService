<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Service Ticket</title>
        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <style>
        .fade-out {
            animation: fadeOutAnimation 2s ease-in-out 3s forwards;
        }

        @keyframes fadeOutAnimation {
            0% { opacity: 1; }
            100% { opacity: 0; display: none; }
        }
    </style>


    <body class="bg-light">

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow">
                        <div class="card-header bg-primary text-white text-center">
                            <h4>Update Service Information</h4>
                        </div>
                        <div class="card-body">

                            <!-- cho nay hien thi cap nhat cai comment hour rate -->
                            <c:if test="${not empty message}">
                                <div class="alert alert-info text-center fade-out">
                                    ${message}
                                </div>
                            </c:if>

                            <form action="UpdateSMController" method="post">
                                <div class="mb-3">
                                    <label class="form-label">Service Ticket ID:</label>
                                    <input type="number" name="serviceTicketID" value="${serviceSM.serviceTicketID}" class="form-control" readonly>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Service ID:</label>
                                    <input type="number" name="serviceID" value="${serviceSM.serviceID}" class="form-control" readonly>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Working Hours:</label>
                                    <input type="number" name="hours" class="form-control" value="${serviceSM.hours}" required min="0">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Comment:</label>
                                    <textarea name="comment" class="form-control" rows="3" >${serviceSM.comment}</textarea>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Service Rate:</label>
                                    <input type="number" name="rate" class="form-control" value="${serviceSM.rate}" required min="0">
                                </div>

                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                    <a href="MechanicDashboard.jsp" class="btn btn-secondary">Back</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap Script -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>