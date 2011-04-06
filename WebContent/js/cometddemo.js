var cometd = $.cometd;

function updateInChat(cometObj) {
	console.log('update: ' + cometObj);
}

cometd.init({ url: '/cometddemo/cometd' });

cometd.subscribe('/chat', updateInChat);

cometd.publish('/login', { 'username': 'jackyfox' });