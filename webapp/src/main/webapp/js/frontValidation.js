$(document).ready(function(){
		$("#addComputerForm").validate({
	    	rules: {
	    		"computerName": {
	    			"required": true,
	    			"minlength": 1,
	    			"maxlength": 255
	    		},
	    		"introduced": {
	    			"required": false,
	    		},
	    		"discontinued": {
	    			"required": false,
	    		},
	    		"companyId": {
	    			"required": false
	    		}
	    		
	    	}
	 });
		
	$( "#addComputerForm" ).submit(function( event ) {
		var introduced = $('#introduced').val();
		var discontinued = $('#discontinued').val();
		if( (new Date(introduced).getTime() > new Date(discontinued).getTime())) {
		    alert("Introduced date must be inferior to Discontinued date.");
		}
	});
});


