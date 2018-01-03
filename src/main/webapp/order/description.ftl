<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <h1>DESCRIPTION</h1>
	
	<table class="table table-striped table-hover">
	  <thead class="thead-light">
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Name</th>
	      <th scope="col">Unit Price</th>
	      <th scope="col">Quantity</th>
	      <th scope="col">Price</th>
	      <#if (order.finished?c) == "false">
	      <th scope="col">#</th>
	      </#if>
	    </tr>
	  </thead>
	  <tbody>
	  	<#list order.getOrderedProduct() as orderProduct>
	   	 	<tr class="clickableRowTable" onclick="document.location='/product/${orderProduct.getProduct().id}'">
	    	  <th scope="row">#</th>
	    	  <td>${orderProduct.getProduct().name}</td>
	    	  <td>${orderProduct.getProduct().price}</td>
	    	  <td>${orderProduct.quantity}</td>
	    	  <td>${orderProduct.quantity * orderProduct.getProduct().price}</td>
	    	  <#if (orderProduct.getOrder().finished?c) == "false">
					<td><form action="/order/remove/${orderProduct.id}" method="post">
						<input type="number" required value="0" name="quantity" min="0" max="${orderProduct.quantity}">
						<input class="btn btn-danger" type="submit" value="Erase"></input>
					</form></td>
				</#if>
	    	</tr>
	    </#list>
	  </tbody>
	</table>
	<p class="sentenceToRight total"><span>Total : ${order.total}</span></p>
	<#if (order.finished?c) == "false">
		<a href="/order/finished"><button class="btn btn-success">End the order</button></a>
	</#if>
</@mp.page>
