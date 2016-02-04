
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"  type="text/css" href="css/zale.css">

        <div class="zale">
                <c:set var="id_row" value="0"/>

                <c:forEach var="row" items="${zale}">
                        <c:set var="row_width" value="${row.value}"/>
                        <div id="${id_row=id_row+1}" class="row" >
                                <c:set var="id_place" value="0"/>

                                <c:forEach var="place" items="${row.value}">
                                        <c:if test="${place.status eq -1}">
                                                <c:set var="place_class" value="place red "/>
                                        </c:if>
                                        <c:if test="${place.status eq 1}">
                                                <c:set var="place_class" value="place booked ${place.price}"/>
                                        </c:if>
                                        <c:if test="${place.status eq 0}">
                                                <c:set var="place_class" value="place free ${place.price}"/>
                                        </c:if>

                                        <div id="${id_row}_${id_place=id_place+1}" class="${place_class}" ></div>
                                </c:forEach>
                        </div>

                </c:forEach>

        </div>
        <c:if test="${not empty user}">
                <form id="form" name="send" action="booking" method="POST">
                        <input id="place" name="place" type="hidden">
                        <input id="status" name="status" type="hidden">
                        <input id="price" name="price" type="hidden">
                        <input name="idPlay" value="${play.id}" type="hidden">
                        <input name="date" value="${date}" type="hidden">

                </form>
                <script type="text/javascript" src=" http://code.jquery.com/jquery-1.11.2.js "></script>
                <script type="text/javascript">
                        $(document).ready(function()
                        {
                                $("div.booked, div.free").click(function(){
                                        $(this).toggleClass("free booked");
                                        $("#place").attr("value",$(this).attr("id"));
                                        var s = $(this).attr("class").split(' ');
                                        $("#status").attr("value",s[2]);
                                        $("#price").attr("value",s[1]);
                                        $("#form").submit();

                                });


                        });
                </script>
        </c:if>

