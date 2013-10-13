$(function() {

	$('.worker.usable').draggable({
		scope: "worker",
		zIndex: 100,
		cursor: 'grabbing',
		cursorAt: { left: 20, top: 20 },
		revert: 'invalid',
		revertDuration: 200
	});
	
	
	$('.action.usable').droppable({
		scope: "worker",
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
	
	$('.buildable').draggable({
		scope: "fence",
		revert: 'invalid',
		revertDuration: 200,
		cursorAt: { left: 20, top: 20 },
		opacity: 0.8
	});
	
	$('.active .hfence.unbuilt, .active .vfence.unbuilt').droppable({
		scope: "fence",
		hoverClass: 'buildhover',
		drop: function(event, ui) {
			var x = $(event.target).data('x');
			var y = $(event.target).data('y');
			var commandDetails = {
					"instance.id" : instanceView.instance.id,
					"command" : ":Fence:"+x+":"+y
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
			
		}
	});
	
});