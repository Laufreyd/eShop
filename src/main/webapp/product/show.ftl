<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <div class="row" style="display:block;">
    <h1>Product Description '${product.name}'</h1>
    <p>
    <#if (product.description)??>
    	${product.description}
    <#else>
    	'No description for this product'
    </#if>
    </p>
    <p>${product.price}</p>
    <p>${product.quantity} left</p>
    
    <#if (product.quantity) == 0>
    	Sorry, you  can't add this product to chart (Out of stock)
    <#elseif (account)??>
    	<button><a href="/order/add/${product.id}">Add to chart</a></button>
    <#else>
    	Be connect to be able to buy this article
    </#if>
    
    <#if (order)??>
    	<p>${order.id}</p>
    </#if>
    
  </div>
</@mp.page>
