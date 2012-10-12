String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, "");
};
String.prototype.ltrim = function() {
	return this.replace(/^\s+/, "");
};
String.prototype.rtrim = function() {
	return this.replace(/\s+$/, "");
};
function $(id) {
	return document.getElementById(id);
};
function inicializa() {
	var msgInfo = $("msg").value;
	// $("forma").reset();
	if (msgInfo != "") {
		$("msgValida").innerHTML = msgInfo;
	}
	$("username").focus();
};
function limpiaMensajes() {
	$("msgValida").innerHTML = "";
};
function validaDatos(form) {
	var datosOK = true, focus = null, username = $("username"), passw = $("passw"), msg = "";

	if (passw.value == "") {
		focus = passw;
		datosOK = false;
		alert("Especificar la contrase&ntilde;a");
		msg = "Especificar la contrase&ntilde;a."
				+ ((msg == "") ? "" : ("<br/>" + msg));
	}
	if (username.value == "") {
		focus = username;
		datosOK = false;
		alert("Especificar el usuario");
		msg = "Especificar el usuario." + ((msg == "") ? "" : ("<br/>" + msg));

	}

	if (datosOK) {
		$("Submit").disabled = true;
		$("Reset").disabled = true;
		form.submit();
	} else {
		$("msgValida").innerHTML = msg;
		focus.focus();
	}

};

 