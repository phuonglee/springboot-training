<div class="generic-container">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Your Cart</span>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>PRODUCT NAME</th>
							<th>PRODUCT DETAILS</th>
							<th>PRICE</th>
							<th>QUANTITY</th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="p in ctrl.getAllProductsInCart()">
							<td>{{p.id}}</td>
							<td>{{p.productName}}</td>
							<td>{{p.productDetails}}</td>
							<td>{{p.price}}</td>
							<td>1</td>
							<td>
								<button type="button" ng-click="ctrl.removeProductInCart(p.id)"
									class="btn btn-danger">Remove</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

            <hr class="divider"/>

            <div style="margin: 10px">
                <button type="button" ng-click="ctrl.checkOut()"
                        class="btn btn-success floatRight">
                    {{authority == 'ROLE_USER' ? 'Check out' : ''}}
                </button>
            </div>
		</hr>
	</div>
</div>