<div class="generic-container">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Login</span>
		</div>
		<div class="panel-body">
			<div class="form-container">
				<div class="alert alert-danger" role="alert"
					ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<!-- <input type="hidden" ng-model="ctrl.user.id" /> -->
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="uname">User
								name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.account.userName" id="uname"
									class="form-control input-sm"
									placeholder="Enter your user name." required
									ng-minlength="3" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="upass">Password</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.account.password" id="upass"
									class="form-control input-sm"
									placeholder="Enter your user password." required
									ng-minlength="8" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Login" class="btn btn-primary btn-sm"
								ng-disabled="myForm.$invalid || myForm.$pristine">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Cancel</button>
						</div>
						<div class="floatLeft">
							<small ng-if="authority == ''">Not have account ? Click <u><a href="#">here</a></u> to register</small>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
