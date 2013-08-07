function handleMessage(data) {
	jQuery('#pushComment').append('<br />' + data);
}

function handleComplete(xht, status, args) {
	if (args.validationFailed) {
		alert('error');
	} else {
		if (args.lista) {
			try{
				var items = eval('(' + args.lista + ')');
				for ( var i = 0; i < items.length; i++) {
					handleMessage(items[i]);
				}
			}catch(e){}
		}
	}
}
