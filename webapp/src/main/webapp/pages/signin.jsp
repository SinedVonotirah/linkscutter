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
          <a class="navbar-brand" href="#"><span>LinksCutter</span></a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active">
              <a href="#">Sign In</a>
            </li>
            <li>
              <a href="#">Sign Up</a>
            </li>
            <li>
              <a href="/webapp/">Home</a>
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
              <br>Autorization Page</h1>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <h1>Please Sign In</h1>
             <form role="form" name='f' action="<c:url value='/j_spring_security_check' />" method='POST'>
		             
				<c:if test="${not empty error}">
					<%-- <div class="error">${error}</div> --%>
					<div class="alert alert-danger alert-dismissable">
              			${error}
              		</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="alert alert-danger alert-dismissable">
              			<strong>Well done!</strong>
              		</div>
					<div class="msg">${msg}</div>
				</c:if>
              
              <div class="form-group">
                <label class="control-label" for="exampleInputEmail1">Login</label>
                <input class="form-control" id="exampleInputEmail1" placeholder="Enter Login" type="text" name="login">
              </div>
              <div class="form-group">
                <label class="control-label" for="exampleInputPassword1">Password</label>
                <input class="form-control" id="exampleInputPassword1" placeholder="Enter Password" type="password" name="password">
              </div>
              <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
              <button type="submit" class="btn btn-lg btn-primary">Sign In</button>
            </form>
          </div>
        </div>
      </div>
    </div>
</body></html>