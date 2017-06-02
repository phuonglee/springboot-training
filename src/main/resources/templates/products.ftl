<div class="generic-container">
	<div class="panel panel-default" ng-show="authority == 'ROLE_ADMIN'">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Products Management</span>
		</div>
		<div class="panel-body">
			<div class="formcontainer">
				<div class="alert alert-success" role="alert"
					ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
				<div class="alert alert-danger" role="alert"
					ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.product.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="pname">Product Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.product.productName" id="pname"
									class="productname form-control input-sm"
									placeholder="Enter product name." required ng-minlength="3" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="pdetails">Product Details</label>
							<div class="col-md-7">
								<textarea ng-model="ctrl.product.productDetails" id="pdetails"
									class="form-control" row="5" placeholder="Enter product details." 
									required ng-minlength="10"></textarea>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="price">Price</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.product.price" id="price"
									class="form-control input-sm" placeholder="Enter product price."
									required ng-pattern="ctrl.onlyIntegers" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="quantity_threshold">Quantity In Stock</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.product.quantityThreshold" id="quantity_threshold"
									class="form-control input-sm" placeholder="Enter quantity number of product in stock."
									required ng-pattern="ctrl.onlyIntegers" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="{{!ctrl.product.id ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm"
								ng-disabled="myForm.$invalid || myForm.$pristine">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
								Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of all Products</span>
		</div>
		<div class="panel-body">
			<#--<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>PRODUCT NAME</th>
							<th>PRODUCT DETAILS</th>
							<th>PRICE</th>
							<th>IN STOCK</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="p in ctrl.getAllProducts()">
							<td>{{p.id}}</td>
							<td>{{p.productName}}</td>
							<td>{{p.productDetails}}</td>
							<td>{{p.price}}</td>
							<td>{{p.quantityThreshold}}</td>
							<td>
								<button type="button" ng-click="ctrl.editProduct(p.id)"
									class="btn btn-success custom-width">
										{{authority == 'ROLE_ADMIN' ? 'Edit' : ''}}
										{{authority == 'ROLE_USER' ? 'Details' : ''}}
								</button>
							</td>
							<td>
								<button type="button" ng-click="ctrl.removeProduct(p.id)"
									class="btn btn-danger">
                                    {{authority == 'ROLE_ADMIN' ? 'Remove' : ''}}
                                    {{authority == 'ROLE_USER' ? 'Add to Cart' : ''}}
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>-->

			<div ui-grid="gridOptions" class="grid" ui-grid-pagination ui-grid-selection></div>
			<div style="margin: 10px">
				<button type="button" ng-click="ctrl.addToCart()"
						class="btn btn-success floatRight">
					{{authority == 'ROLE_USER' ? 'Add to Cart' : ''}}
				</button>
			</div>

		</div>
	</div>
</div>