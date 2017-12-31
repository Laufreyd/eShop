<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <h1>LIST OF ORDERS??</h1>
  

  <#list orders as order>
  
  	<a href="/order/${order.id}">
  	<p>${order.createdAt}
  	//TODO NUMBER OF PRODUCTS
  	<#if (order.total)??>
  	${order.total} euros
  	<#else>
  	0 euros
  	</#if></p>
  	</a>
  </#list>
  
</@mp.page>
