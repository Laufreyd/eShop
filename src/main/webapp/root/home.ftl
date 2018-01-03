<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <div class="row">
    <h1>Last Products</h1>
    <#list lastProducts as lastProduct>
    	<a href="/product/${lastProduct.id}">${lastProduct.name}</a>
    </#list>
    <a href="/product/index">See all the products</a>
  </div>
</@mp.page>
