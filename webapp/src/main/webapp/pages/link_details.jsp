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
              <a href="#">Contacts</a>
            </li>
          </ul>
          <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
          </form>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-center">Link Details</h1>
          </div>
        </div>
      </div>
    </div>
    <div class="section text-left">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-left">
            <blockquote class="lead">
              <p class="text-warning">Link</p>
              <p><a href="${link.url}">${link.url}</a></p>
            </blockquote>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <blockquote class="lead">
              <p class="text-warning" contenteditable="true">Cutted Link</p>
              <p><p><a href="http://localhost:8080/webapp/${link.genCode}">http://localhost:8080/webapp/${link.genCode}</a></p></p>
            </blockquote>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <blockquote class="lead">
              <p contenteditable="true" class="text-warning">Description</p>
              <p>
              	${link.linkDetails.description}
              </p>
            </blockquote>
           </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <blockquote class="lead">
              <p contenteditable="true" class="text-warning">Tags</p>
              <p>              	
              	<c:forEach var="tag" items="${link.linkDetails.tags}">
              		${tag.name}              
              	</c:forEach>
              	</p>
            </blockquote>
          </div>
        </div>
      </div>
    </div><div class="section"><div class="container"><div class="row"><div class="col-md-12 text-center"><table class="table"><thead><tr><th>#</th><th>First Name</th><th>Last Name</th><th>Username</th></tr></thead><tbody><tr><td>1</td><td>Mark</td><td>Otto</td><td>@mdo</td></tr><tr><td>2</td><td>Jacob</td><td>Thornton</td><td>@fat</td></tr><tr><td>3</td><td>Larry</td><td>the Bird</td><td>@twitter</td></tr></tbody></table></div></div><div class="row"><div class="col-md-12 text-center"><ul class="pagination"><li><a href="#">Prev</a></li><li><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li><a href="#">Next</a></li></ul></div></div></div></div>
  

</body></html>