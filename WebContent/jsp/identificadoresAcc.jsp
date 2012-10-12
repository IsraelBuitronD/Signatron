
<link href="../css/multiselect.css" rel="stylesheet" type="text/css" /> 
<link href="css/draicon.css" rel="stylesheet" type="text/css">
<script src="../lib/jquery/jquery.bgiframe.js" type="text/javascript"></script> 
<script src="../js/multiselect.js" type="text/javascript"></script>

<script type="text/javascript"> 
$(function(){
	
	// notice how you can overwrite the defaults for instances
	$.fn.multiSelect.defaults.minWidth = 205;
	
	// $('#themeswitcher').themeswitcher({ loadTheme: 'lightness' });
	 
	// default options
	$("select.multiselect").multiSelect();
 
	
	
	$("#examples").submit(function(e){
		var data = $(this).serialize();
		alert( data.length ? data : 'Nothing to serialize; check a box or two' );
		e.preventDefault();
	});
	
});
</script> 
 

	<form action="" method="post" id="examples"> 
	
	
	<p> 
		<select name="example4" class="multiselect" multiple="multiple" size="5"> 
		<optgroup label="Flotilla A"> 
			<option value="option1">Option 1</option> 
			<option value="option2">Option 2</option> 
			<option value="option3">Option 3</option> 
		</optgroup> 
		<optgroup label="Flotilla B"> 
			<option value="option4">Option 4</option> 
			<option value="option5">Option 5</option> 
			<option value="option6">Option 6</option> 
			<option value="option7">Option 7</option> 
		</optgroup> 
		</select> 
	</p> 
	
 
<div class="autos ">
<input type="submit" name="submit" value="Buscar en Mapa" /> 
<input type="reset" name="reset" value="Reset" /> 

</div>
	
	
	</form> 
 