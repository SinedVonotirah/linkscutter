<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html><head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <spring:url value="/pages/res/main.css" var="coreCss" />	
        <link href="${coreCss}" rel="stylesheet" type="text/css">
    </head><body>
        <div class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"><span>LinksCutter</span></a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active">
                            <a href="#">Home</a>
                        </li>
                        <sec:authorize access="isAnonymous()">
	                        <li>
	                            <a href="/webapp/signup">Sign Up</a>
	                        </li>
	                        <li>
	                            <a href="/webapp/signin">Sign In</a>
	                        </li>
    					</sec:authorize>
                        <sec:authorize access="isAuthenticated()">
	                        <li>
	                            <a href="<c:url value="/logout" />">LogOut</a>
	                        </li>
    					</sec:authorize>
                    </ul>
                </div>
            </div>
        </div>
        <div class="section text-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center">LinksCutter
                            <br>Main Page</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="hidden-sm hidden-xs section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center">Statistic</h1>
                        <div class="row">
                            <div class="col-md-6">
                                <h2>Links created</h2>
                            </div>
                            <div class="col-md-6">
                                <h2 class="text-right">Links followed</h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <h3>456</h3>
                            </div>
                            <div class="col-md-6">
                                <h3 class="text-right">46584684</h3>
                            </div>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center">Link Search</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center">
                        <form role="form">
                            <div class="form-group">
                                <input class="form-control" id="exampleInputEmail1" placeholder="Enter your Link" type="search">
                            </div>
                            <button type="submit" class="btn btn-lg btn-primary">Search</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    

</body></html>