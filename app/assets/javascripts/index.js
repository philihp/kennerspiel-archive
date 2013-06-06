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
		'submit .move-create-form': 'sendCommand'
	},
	render: function(options) {
		console.log('Rerendering Table View');
		var that = this;
		that.table = Table.findOrCreate(options);
		that.table.fetch({
			success: function(table) {
				var template = _.template($('#table-view-template').html(), {table: table});
				that.$el.html(template);
			}
		})
	},
	sendCommand: function(ev) {
		event.stopPropagation();
		event.preventDefault();

		var that = this;


		var commandDetails = $(ev.currentTarget).serializeObject();
		commandDetails['table.id'] = this.table;

		var command = new Command();
		command.save(commandDetails, {
			success: function(command) {
				console.log('Saved! Navigating... /view/'+command.get('table.id').id);
				that.render({_id: command.get('table.id').id});
			},
			error: function(command, xhr) {
				//$('#errorText').text(xhr.responseJSON.error);
				//$('#errorBox').modal();
				//console.log(xhr.responseJSON.error);
				$('#alertPlaceholder').html('<div class="alert alert-error"><a class="close" data-dismiss="alert">&times;</a>'+
					'<strong>Error: </strong>'+xhr.responseJSON.error+'</div>');
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
		tableEdit.render({_id: id});
	},
	tableView: function(id) {
		console.log('ROUTER: view('+id+')');
		tableView.render({_id: id});
	}
})

var tableList = new TableList();
var tableEdit = new TableEdit();
var tableView = new TableView();
var router = new Router();

Backbone.history.start();