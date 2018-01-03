<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <h1>DESCRIPTION</h1>
  
	<#list order.getOrderedProduct() as orderProduct>
		<#if (orderProduct.quantity > 0)>
		<a href="/product/${orderProduct.getProduct().id}">${orderProduct.getProduct().name}</a>
		<#if (orderProduct.getOrder().finished?c) == "false">
			<form action="/order/remove/${orderProduct.getProduct().id}" method="post">
				<input type="number" required value="0" name="quantity" min="0" max="${orderProduct.quantity}">
				<input type="submit" value="Erase"></input>
			</form>
		</#if>
		</#if>
	</#list>

</@mp.page>
