<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <h1>DESCRIPTION</h1>
  
	<#list order.getOrderedProduct() as product>
	
		${product.getProduct().name}
	
	</#list>
	${order.customer.id}

</@mp.page>
