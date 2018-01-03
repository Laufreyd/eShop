<#macro page title js="" css="">
<!DOCTYPE HTML>
<html>

  <head>
    <title>${title?html}</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/assets/style.css">
    <link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
      integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
      crossorigin="anonymous">

    ${css}
  </head>

  <body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark <!--fixed-top-->">
      <div class="container"><div class="row myRow">
	      <a class="col navbar-brand" href="/">E-Shopping</a>
	        <div class="col-md-4 offset-md-3 customerOption">
	        	<#if (account)??>
	        		<span class="navWriten">Bienvenue </span>
	        		<div class="dropdown myDropDown">
					  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    ${account.firstName}
					  </button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					    <a class="dropdown-item" href="/user/profil">Profil</a>
					    <a class="dropdown-item" href="/order/list">My orders</a>
					    <a class="dropdown-item bg-danger text-light" href="/user/disconnexion">Deconnexion</a>
					  </div>
					</div>
	        	<#else>
	        		<a href="/user/connexion"><button class="btn btn-light">Connexion</button></a>
	        	</#if>
	        </div>
	        </div>
        </div>
    </nav>

    <main role="main" class="container">

      <#nested />

    </main>
    <!-- /.container -->


    <!-- Bootstrap core JavaScript ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
      integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
      crossorigin="anonymous"></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
      integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
      crossorigin="anonymous"></script>
    <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
      integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
      crossorigin="anonymous"></script>
    ${js}
  </body>
</html>
</#macro>
