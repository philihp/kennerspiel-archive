$(function() {

	console.log('Workers Draggable');
	$('.worker.usable').draggable({
		start: function(event) {
		},
		drag: function(event) {
		},
		stop: function(event) {
		},
		opacity: 0.7,
		zIndex: 100,
		cursor: 'pointer',
		cursorAt: { left: 20, top: 20 },
		revert: 'invalid',
		revertDuration: 200
	});
	
	
	console.log('Actions Droppable');
	$('.action').droppable({
		drop: function(event, ui) {
			
			console.log(tableView.table);
			var commandDetails = {
					"table.id" : tableView.table.id,
					"command" : $(event.target).data('command')
			};
			console.log(commandDetails);

			var command = new Command();
			command.save(commandDetails, {
				success: function(command) {
					tableView.render({_id: command.get('table.id').id});
				},
				error: function(command, xhr) {
					$('#alertPlaceholder').html('<div class="alert alert-error"><a class="close" data-dismiss="alert">&times;</a>'+
						'<strong>Error: </strong>'+xhr.responseJSON.message+'</div>');
				}
			});
			
			
		},
		hoverClass: 'actionable'
	});
	
	
});