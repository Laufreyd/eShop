<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <h1>LIST OF ORDERS</h1>
  
	
	  <#list orders as order>
	  
	  	<#if (order.getOrderedProduct()?size > 0)>
	  	
		  	<a href="/order/${order.id}">
		  	<p>${order.createdAt}
		  	${order.getOrderedProduct()?size} Differents products
		  	<#if (order.total)??>
		  	${order.total} euros
		  	<#else>
		  	//TODO When total null
		  	</#if></p>
		  	</a>
		  	
	  	</#if>
	  	
	  </#list>
  
</@mp.page>
