<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <h1>PROFIL de ${account.firstName} ${account.lastName}</h1>
  <div class="col-12 homeLink profilLink"><a href="/order/list">My orders</a></div>
	
	<form action="modification" method="post">
  <div class="row">
	  <div class="form-group col">
	    <label for="firstNameId">First Name</label>
	    <input type="text" class="form-control" id="firstNameId" required name="firstName" value="${account.firstName}">
	  </div>
	  <div class="form-group col">
	    <label for="lastNameId">Last Name</label>
	    <input type="text" class="form-control" id="lastNameId" required name="lastName" value="${account.lastName}">
	  </div>
  </div>
  <div class="form-group">
    <label for="adressId">Address</label>
    <#if (account.address)??>
    	<input type="text" class="form-control" id="adressId" name="address" value="${account.address}">
    <#else>
    	<input type="text" class="form-control" id="adressId" name="address">
    </#if>
  </div>
  <div class="form-group">
    <label for="cityId">City</label>
    <#if (account.city)??>
    	<input type="text" class="form-control" id="cityId" name="city" value="${account.city}">
    <#else>
    	<input type="text" class="form-control" id="cityId" name="city">
    </#if>
  </div>
  <div class="row">
  <div class="form-group col">
    <label for="phoneNumberId">Phone Number</label>
    <#if (account.phone)??>
    	<input type="text" class="form-control" id="phoneNumberId" name="phone" value="${account.phone}">
    <#else>
    	<input type="text" class="form-control" id="phoneNumberId" name="phone">
    </#if>
  </div>
  <div class="form-group col">
    <label for="zipCodeId">Zip Code</label>
    <#if (account.zip)??>
    	<input type="number" class="form-control" id="zipCodeId" name="zip" value="${account.zip}">
    <#else>
    	<input type="number" class="form-control" id="zipCodeId" name="zip">
    </#if>
  </div></div>
  <button type="submit" class="btn btn-primary">Change</button>
</form>
  
</@mp.page>
