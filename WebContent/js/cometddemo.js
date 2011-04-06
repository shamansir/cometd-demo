var cometd = $.cometd;

function updateInChat(cometObj) {
	console.log('update: ' + cometObj);
}

cometd.init({ url: '/cometd' });

cometd.subscribe('/chat', updateInChat);