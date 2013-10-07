$(function() {

	$('.worker.usable').draggable({
		start: function(event) {
		},
		drag: function(event) {
		},
		stop: function(event) {
		},
		opacity: 0.7,
		zIndex: 100,
		cursor: 'grabbing',
		cursorAt: { left: 20, top: 20 },
		revert: 'invalid',
		revertDuration: 200
	});
	
	
	$('.action.usable').droppable({
		drop: function(event, ui) {
			
			var commandDetails = {
					"instance.id" : instanceView.instance.id,
					"command" : $(event.target).data('command')
			};

			var command = new Command();
			command.save(commandDetails, {
				success: function(command) {
					instanceView.render({_id: command.get('instance.id').id});
				},
				error: function(command, xhr) {
					$('#alertPlaceholder').html('<div class="alert alert-error"><a class="close" data-dismiss="alert">&times;</a>'+
						'<strong>Error: </strong>'+xhr.responseJSON.message+'</div>');
				}
			});
			
			
		},
		hoverClass: 'actiondrophover'
	});
	
	
});