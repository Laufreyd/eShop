<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">
<#assign count = 1>
  <h1>LIST OF ORDERS</h1>
  	  	
	  	<table class="table table-striped table-hover">
		  <thead class="thead-light">
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Creation Date</th>
		      <th scope="col">Differents products</th>
		      <th scope="col">Price</th>
		    </tr>
		  </thead>
		  <tbody>
			  <#list orders?reverse as order>
			  <#if (order.getOrderedProduct()?size > 0)>
			    <tr class="clickableRowTable" onclick="document.location='/order/${order.id}'">
			    
			      <th scope="row">${count}</th>
			      <#assign count++>
			      <td>${order.createdAt}</td>
			      <td>${order.getOrderedProduct()?size}</td>
			      <td>${order.total}</td>

			    </tr>
			    </#if>
			    </#list>
		  </tbody>
		</table>
	  	
	  
  
</@mp.page>
