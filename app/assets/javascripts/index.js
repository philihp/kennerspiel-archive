/*
$ ->
  $.get "/tables", (data) ->
    $.each data, (index, table) ->
      $("#tables").append $("<li>").html "<a href=\""+table.id+"/index\">Table #"+table.id+" : "+table.name+"</a>"
*/

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if(!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value||'');
		}
		else {
			o[this.name] = this.value||'';
		}
	});
	return o;
}



var Table = Backbone.RelationalModel.extend({
	urlRoot: '/api/tables',
	idAttribute: '_id',
	relations: [{
		type: Backbone.HasMany,
		key: "commands",
		relatedModel: 'Command',


		reverseRelation: {
			key: 'table.id',
			includeInJSON: '_id'
		}
	}]
});
var Command = Backbone.RelationalModel.extend({
	urlRoot: '/api/commands',
	idAttribute: '_id'
});
var TableCollection = Backbone.Collection.extend({
	url: '/api/tables',
	model: Table
});

var TableList = Backbone.View.extend({
	el: '#page',
	events: {
	},
	render: function() {
		var that = this;
		var tables = new TableCollection();
		tables.fetch({
			success: function() {
				console.log('tables.fetch --> success', tables.models);
				var template = _.template($('#tables-list-template').html(), {tables: tables.models});
				that.$el.html(template);
			}
		});
	}
});

var TableEdit = Backbone.View.extend({
	el: '#page',
	events: {
		'submit .table-edit-form': 'saveTable',
		'click .delete' : 'deleteTable'
	},
	render: function(options) {
		var that = this;
		if(options.id) {
			that.table = Table.findOrCreate({id: options.id});
			that.table.fetch({
				success: function(table) {
					var template = _.template($('#table-edit-template').html(), {table: table});
					that.$el.html(template);
				}
			});

		}
		else {
			var template = _.template($('#table-edit-template').html(), {table: null});
			this.$el.html(template);
		}
	},
	saveTable: function(ev) {
		var tableDetails = $(ev.currentTarget).serializeObject();
		var table = new Table();
		table.save(tableDetails, {
			success: function (table) {
				router.navigate('', {trigger: true});
			}
		})
		return false;
	},
	deleteTable: function(ev) {
		this.table.destroy({
			success: function() {
				router.navigate('', {trigger: true});
			}
		})
	}
});


var TableView = Backbone.View.extend({
	el: '#page',
	events: {
		'submit .commit-form': 'sendCommand',
		'submit .undo-form': 'sendCommand'
	},
	render: function(options) {
		console.log('Rerendering Table View');
		var that = this;
		that.table = Table.findOrCreate(options);
		that.table.fetch({
			success: function(table) {
				console.log('Table Returned: ',table);
				var template = _.template($('#table-view-template').html(), {table: table});
				that.$el.html(template);

				if(options.message) {
					console.log('Alerted');
					$('#alertPlaceholder').html('<div class="alert alert-success"><a class="close" data-dismiss="alert">&times;</a>'+
						'<strong>Note: </strong>'+options.message+'</div>');
				}
			}
		});
	},
	sendCommand: function(ev) {
		event.stopPropagation();
		event.preventDefault();

		var that = this;
		var commandDetails = {
				'table.id': this.table,
				'command': $(ev.target).data('command')
		}

		var command = new Command();
		command.save(commandDetails, {
			success: function(command, xhr) {
				console.log('SUCCESS', xhr);
				console.log('Saved! Navigating... /view/'+command.get('table.id').id);
				that.render({
					_id: command.get('table.id').id,
					message: xhr.message
				});
			},
			error: function(command, xhr) {
				$('#alertPlaceholder').html('<div class="alert alert-error"><a class="close" data-dismiss="alert">&times;</a>'+
					'<strong>Error: </strong>'+xhr.responseJSON.message+'</div>');
			}
		});
	}
})

var Router = Backbone.Router.extend({
	routes: {
		'' : 'home',
		'new' : 'tableEdit',
		'view/:id' : 'tableView'
	},
	home: function() {
		console.log('ROUTER: home');
		tableList.render();
	},
	tableEdit: function(id) {
		console.log('ROUTER: tableEdit('+id+')');
		tableEdit.render({_id: id});
	},
	tableView: function(id) {
		console.log('ROUTER: tableView('+id+')');
		tableView.render({_id: id});
	}
})

var tableList = new TableList();
var tableEdit = new TableEdit();
var tableView = new TableView();
var router = new Router();

Backbone.history.start();
