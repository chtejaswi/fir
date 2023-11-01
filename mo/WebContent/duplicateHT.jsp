<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DuplicateHallTicket</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function() {
            // Initialize the date picker on the input field with the "transaction_date" id
            $("#transaction_date").datepicker({ dateFormat: 'yy-mm-dd' });
        });
    </script>
</head>
<body>
    <form action="SaveDataServlet" method="post">
        Sevak ID: <input type="text" name="sevak-id"><br>
        Exam Name: <input type="text" name="exam-name"><br>
        Fine Amount: <input type="text" name="fineamount"><br>
        <label for="transaction_date">Transaction Date:</label>
        Date: <input type="text" id="transaction_date" name="transaction_date"><br>
        Transaction Details: <input type="text" name="transactiondetails"><br>
        <input type="submit" value="Save Data">
    </form>
</body>
</html>
