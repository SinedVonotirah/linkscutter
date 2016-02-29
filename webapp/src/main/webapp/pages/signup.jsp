<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
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
                    <a class="navbar-brand" href="#"><span>Links</span><span>Cutter</span></a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active">
                            <a href="#">Sign Up<br></a>
                        </li>
                        <li>
                            <a href="#">Sign In</a>
                        </li>
                        <li>
                            <a href="#">Home</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center">LinksCutter
                            <br>Registration Page</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center">Please Sign Up</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center">
                        <form:form role="form" action="register" method="POST" commandName="userForm">
                            <div class="form-group">
                                <label class="control-label" for="exampleInputEmail1">Login</label>
                                <form:input path="login" class="form-control" id="exampleInputEmail1" placeholder="Enter Login" type="text"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="exampleInputPassword1">Email</label>
                                <form:input path="mail" class="form-control" id="exampleInputPassword1" placeholder="Enter Email" type="email"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="exampleInputEmail1">Password</label>
                                <form:input path="password" class="form-control" id="exampleInputPassword1" placeholder="Enter Password" type="password"/>
                            </div><div class="form-group">
                                <label class="control-label" for="exampleInputEmail1">Confirm Password</label>
                                <input class="form-control" id="exampleInputPassword1" placeholder="Enter Password" type="password">
                            </div>
                            <button type="submit" class="btn btn-lg btn-primary">Sign Up</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    

</body></html>