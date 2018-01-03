<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">
<div class="row">
<div class="col-5"> 
<h1>Connexion</h1>
 
        <form action="authentification" method="post">
  <div class="form-group">
    <label for="firstNameId">First name</label>
    <input type="text" class="form-control" id="firstNameId" required name="firstName">
  </div>
  <div class="form-group">
    <label for="lastNameId">Password</label>
    <input type="text" class="form-control" id="lastNameId" required name="lastName">
  </div>
  <button type="submit" class="btn btn-primary">Connexion</button>
</form>
</div>

 
<div class="col-5 offset-1">
<h1>Inscription</h1>
<form action="inscription" method="post">
  <div class="form-group">
    <label for="firstNameId">First name</label>
    <input type="text" class="form-control" id="firstNameId" required name="firstName">
  </div>
  <div class="form-group">
    <label for="lastNameId">Password</label>
    <input type="text" class="form-control" id="lastNameId" required name="lastName">
  </div>
  <button type="submit" class="btn btn-primary">Inscription</button>
</form>
</div>
</div> 
       
   <#if (error)??>
   		<p>${error}</p>
   </#if>
  
</@mp.page>
