<div class="generic-container">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Welcome {{authority == '' ? 'Guest' : (authority == 'ROLE_ADMIN' ? 'ADMIN' : 'USER')}} !</span>
			<br/>
			<small ng-if="authority == ''">Not have account ? Click <u><a href="#">here</a></u> to register</small>
		</div>
	</div>
</div>