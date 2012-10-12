draicon.signatron.header = {
	init : function() {
	 document.counter.d2.value='20';
	 draicon.signatron.header.display();
	
	},
	
	 
	display : function(){
		 if (draicon.signatron.header.milisec<=0){ 
			 draicon.signatron.header.milisec=9; 
			 draicon.signatron.header.seconds-=1; 
			 } 
			 if (draicon.signatron.header.seconds<=-1){ 
				 draicon.signatron.header.milisec=0; 
				 draicon.signatron.header.seconds+=1;
			 } 
			 
			 else {
				draicon.signatron.header.milisec-=1;
			    document.counter.d2.value=draicon.signatron.header.seconds;//+"."+milisec 
			    if(draicon.signatron.seconds==0){
			    	draicon.signatron.header.seconds=30;
			    	}
			    setTimeout("draicon.signatron.header.display()",500); 
			    
			 }  
	}
	
	

},
jQuery(document).ready(function() {
	draicon.signatron.header.init();
});
