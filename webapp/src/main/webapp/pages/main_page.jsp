<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html><head>
    <title>LinksCutter</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <spring:url value="/pages/res/mainpage.css" var="coreCss" />
    <link href="${coreCss}" rel="stylesheet" type="text/css">
  </head>  <body>
    <div class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand"></a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
          <form class="navbar-form navbar-left text-left" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Link Search">
            </div>
            <button type="submit" class="btn btn-default">Search</button>
          </form>
        </div>
      </div>
    </div>
    <div class="section text-center">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-center">LinksCutter</h1>
          </div>
        </div>
      </div>
    </div>
	    <div class="section text-center">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-12 text-center">
	            <h2 class="text-center">Please Sign In</h2>
	            
	            <form role="form" action="<c:url value='/j_spring_security_check' />" method='POST'>
	              <div class="form-group">
	                <label class="control-label" for="exampleInputEmail1">Login</label>
	                <input class="form-control" id="exampleInputEmail1" placeholder="Enter Login" type="text" name="login">
	              </div>
	              <div class="form-group">
	                <label class="control-label" for="exampleInputPassword1">Password</label>
	                <input class="form-control" id="exampleInputPassword1" placeholder="Enter Password" type="password" name="password">
	              </div>
	              <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	              <button type="submit" class="btn btn-lg btn-primary">Login</button>
	              <br>
	              <br>
	              <strong>Registration</strong>
	              </form>
	          </div>
	        </div>
	      </div>
	    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <hr>
          </div>
        </div>
      </div>
    </div>
    <div class="section text-center">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h2 class="text-center">Statistic</h2>
            <div class="row">
              <div class="col-md-6">
                <h2>Links created</h2>
              </div>
              <div class="col-md-6">
                <h2 class="text-center">Links followed</h2>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <h3>456</h3>
              </div>
              <div class="col-md-6">
                <h3 class="text-center">46584684</h3>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  

</body></html>