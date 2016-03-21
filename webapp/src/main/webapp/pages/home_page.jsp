<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html><head>
	<title>LinksCutter</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <spring:url value="/pages/res/mainpage.css" var="coreCss" />
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
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active">
              <a href="#">Home</a>
            </li>
            <li>
              <a href="<c:url value="/logout" />">LogOut</a>
            </li>
          </ul>
          <form class="navbar-form navbar-left text-left" role="requiredlink">
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
            <h1 class="text-center">Cut your Link</h1>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-left">
            <form:form class="form-horizontal text-left" role="form" action="/webapp/homepage" method="POST" commandName="newLink">
              <div class="form-group has-feedback">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label">Long Link</label>
                </div>
                <div class="col-sm-10">
                  <form:input path="url" type="text" class="form-control" id="inputEmail3" placeholder="Insert Long Link"/>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label">Description</label>
                </div>
                <div class="col-sm-10">
                  <form:textarea path="description" class="form-control"/>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">Tags</label>
                </div>
                <div class="col-sm-10">
                  <div class="input-group">
                    <span class="input-group-addon">
                      <i class="fa fa-check"></i>
                    </span>
                    <form:input path="tags" type="text" class="form-control"/>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-10 col-sm-offset-2 text-center">
                  <button type="submit" class="btn btn-block btn-default">Cut Link</button>
                </div>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
    <c:if test="${not empty shortlink}">
	    <div class="section">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-12">
	            <div class="panel panel-primary text-center">
	              <div class="panel-heading">
	                <h3 class="panel-title">Link sucesful cutted!!!</h3>
	              </div>
	              <div class="panel-body">
	                <p>Your short link is - <a href="${shortlink}">${shortlink}</a></p>
	              </div>
	            </div>
	            <hr>
	          </div>
	        </div>
	      </div>
	    </div>
	</c:if>    
	<c:if test="${not empty urlerror}">
		<div class="section text-center">
	     <div class="container">
	       <div class="row">
	         <div class="col-md-12">
	           <div class="alert alert-danger alert-dismissable">
	             <strong>${urlerror}</strong></div>
	         </div>
	       </div>
	     </div>
    </div>
	</c:if>
	
	
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-center">Your Links</h1>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <table class="table">
              <tbody>
				
				<c:forEach var="link" items="${links}">
					<tr>
						<td>
							${link.url}
						</td>
						<td>
							${link.linkDetails.created}
						</td>
						<td>
							<a href="http://localhost:8080/webapp/${link.genCode}"><strong>${link.genCode}</strong></a>
						</td>
						<td>
							${link.linkDetails.counter}
						</td>			
					
					
					</tr>				
				</c:forEach>			
				
				
              </tbody>
              <thead>
                <tr>
                  <th>Long Url</th>
                  <th>Created</th>
                  <th>Short Url</th>
                  <th>Counter</th>
                </tr>
              </thead>
            </table>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">
            <ul class="pagination">
              <li>
                <a href="#">Prev</a>
              </li>
              <li class="active">
                <a href="#">1</a>
              </li>
              <li>
                <a href="#">2</a>
              </li>
              <li>
                <a href="#">3</a>
              </li>
              <li>
                <a href="#">4</a>
              </li>
              <li>
                <a href="#">5</a>
              </li>
              <li>
                <a href="#">Next</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  

</body></html>