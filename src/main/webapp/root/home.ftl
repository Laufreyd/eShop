<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <div class="row">
    <h1 class="titlePages">Last Products</h1>
    <#list lastProducts as lastProduct>
    	<div class="col homeLink">
    		<a href="/product/${lastProduct.id}">${lastProduct.name}</a>
    	</div>
    </#list>
    <div class="col-12 homeLink">
    	<a href="/product/index">See all the products</a>
    </div>
  </div>
</@mp.page>
