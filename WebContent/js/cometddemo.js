var cometd = $.cometd;

function updateInChat(cometObj) {
	alert('chat update: ' + cometObj.data.status);
}

cometd.init({ url: '/cometddemo/cometd' });

cometd.subscribe('/chat', updateInChat);

cometd.publish('/login', { 'username': 'jackyfox' });