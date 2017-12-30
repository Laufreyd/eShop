<#import "../masterpage.ftl" as mp />
<@mp.page title="Show list">

  <div class="row">
    <h1>Product Description</h1>
    <p>${product.quantity}</p>
    <p>${product.price}</p>
  </div>
</@mp.page>
