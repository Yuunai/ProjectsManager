<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>

<footer class="footer">
    <div class="footer-copyright text-center py-3">
        <div class="container">

            <ul class="list-unstyled list-inline text-center">
                <li class="list-inline-item flagShadow">
                    <script>
                        var addresPl = window.location.href.split('&',1).toString();
                        if(!addresPl.includes('language=')) {
                            if (addresPl.includes('?')) {
                                addresPl += '&language=pl';
                            }
                            else {
                                addresPl += '?language=pl';
                            }
                        }
                        else{
                            addresPl = addresPl.substring(0,addresPl.indexOf('language=')+9) +'pl'+  addresPl.substring(addresPl.indexOf('language=')+11);
                        }
                    </script>
                    <a onclick="location.href=addresPl"><span class="flag-icon flag-icon-pl"> </span></a>
                </li>
                <li class="list-inline-item flagShadow">
                    <script>
                        var addresEn = window.location.href.split('&',1).toString();
                        if(!addresEn.includes('language=')) {
                            if (addresEn.includes('?')) {
                                addresEn += '&language=en';
                            }
                            else {
                                addresEn += '?language=en';
                            }
                        }
                        else{
                            addresEn = addresEn.substring(0,addresEn.indexOf('language=')+9) +'en'+  addresEn.substring(addresEn.indexOf('language=')+11);
                        }
                    </script>
                    <a onclick="location.href=addresEn"><span class="flag-icon flag-icon-gb"> </span></a>
                </li>
            </ul>
        </div>
        © 2018-2019 Copyright:
        <a href="https://www.facebook.com/SpacjaTv/"> SpacjaTV</a>
    </div>
</footer>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>