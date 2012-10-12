$(document).ready(function(){
  $('#signup form').validate({
    rules: {
	  username: {
        required: true
      },
      email: {
        required: true,
        email: true
      },
      userA: {
    	  minlength: 6,
    	  required: true
    	
      },
      passw: {
        minlength: 6,
        required: true
      },
      passconf: {
        equalTo: "#password"
      }
    },
    success: function(label) {
      label.text('OK!').addClass('valid');
    }
  });
});

