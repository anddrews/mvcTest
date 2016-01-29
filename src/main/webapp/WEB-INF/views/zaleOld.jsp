
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
</head>

<body>


<table>
      <c:forEach var="row"  begin="0" end="${zale.rows-1}">
        <tr>
          <c:forEach var="place"  begin="0" end="${zale.places-1}">
            <c:forEach var="isFree" items="${zale.isFree}">
              <c:if test="${isFree.place==place and isFree.row==row and isFree.isFree==true}">
                <td id="${row}_${place}" style="border-style: groove; height: 15px; width: 15px;
                background-color: chartreuse;" onclick="clickPlace(${row},${place})"></td>
              </c:if>
              <c:if test="${isFree.place==place and isFree.row==row and isFree.isFree==false}">
                <td id="${row}_${place}" style="border-style: groove; height: 15px; width: 15px;
                background-color: red;" ></td>
              </c:if>
            </c:forEach>
          </c:forEach>
        </tr>
      </c:forEach>
    </table>

    <form name="send" action="booking" method="post">
      <input name="place" type=hidden>
      <input name="row" type=hidden>
      <input name="booking" type=hidden>
      <input type="submit">
    </form>



    <script>

      function clickPlace(row,place){

        if(document.getElementById(row+'_'+place).style.backgroundColor=='blue')
        {
          document.getElementById(row+'_'+place).style.backgroundColor='chartreuse';
          document.send.booking.value='true';
        }
        else
        {
          document.getElementById(row+'_'+place).style.backgroundColor='blue';
          document.send.booking.value='false';

        }
        document.send.place.value=place;
        document.send.row.value=row;
        document.send.submit();
      }
    </script>


</body>
</html>