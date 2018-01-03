<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

    <h1>Product Description '${product.name}'</h1>
    
    
    <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Informations</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">Description</th>
      <td>
      	<#if (product.description)??>
    		${product.description}
    	<#else>
    		<span style="font-style:italic;">No description for this product</span>
    	</#if>
    	</td>
    </tr>
    <tr>
      <th scope="row">Price</th>
      <td>${product.price}</td>
    </tr>
    <tr>
      <th scope="row">Quantity left</th>
      <td>${product.quantity}</td>
    </tr>
  </tbody>
</table>

<#if (product.quantity) == 0>
    	<p class="sentenceToRight"><span>Sorry, you  can't add this product to chart (Out of stock)</span></p>
    <#elseif (account)??>
    	<form action="/order/add/${product.id}" method="post" class="sentenceToRight">
    		<input type="number" required name="quantity" value="1" min="0" max="${product.quantity}">
    		<input class="btn btn-primary" type="submit" value="Add to chart"></input>
    	</form>
    <#else>
    	<p class="sentenceToRight"><span>Be connect to be able to buy this article</span></p>
    </#if>
</@mp.page>
