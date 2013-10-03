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

Handlebars.JavaScriptCompiler.prototype.nameLookup = function(parent, name, type) {
	var result = '(' + parent + ' instanceof Backbone.Model ? ' + parent + '.get("' + name + '") : ' + parent;
	if (/^[0-9]+$/.test(name)) {
		return result + "[" + name + "])";
	} else if (Handlebars.JavaScriptCompiler.isValidJavaScriptVariableName(name)) {
		return result + "." + name + ')';
	} else {
		return result + "['" + name + "'])";
	}
};

Handlebars.registerHelper('equals', function(v1, v2, options) {
	if(v1 == v2) {
		return options.fn(this);
	}
	return options.inverse(this);
});

Handlebars.registerHelper("log", function(context) {
	return console.log(context);
});


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
				var template = Handlebars.compile($('#tables-list-template').html());
				that.$el.html(template({tables: tables.models}));
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
		that.table = Table.findOrCreate({id: options.id});
		that.table.fetch({
			success: function(table) {
				var template = Handlebars.compile($('#table-edit-template').html());
				that.$el.html(template({table: table}));
			}
		});
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
	}
});


var TableView = Backbone.View.extend({
	el: '#page',
	events: {
		'submit .commit-form': 'sendCommand',
		'submit .undo-form': 'sendCommand'
	},
	render: function(options) {
		var that = this;
		that.table = Table.findOrCreate(options);
		that.table.fetch({
			success: function(table) {
				var template = Handlebars.compile($('#table-view-template').html());
				that.$el.html(template(table));

				if(options.message) {
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
		tableList.render();
	},
	tableEdit: function(id) {
		tableEdit.render({_id: id});
	},
	tableView: function(id) {
		tableView.render({_id: id});
	}
})

var tableList = new TableList();
var tableEdit = new TableEdit();
var tableView = new TableView();
var router = new Router();

Backbone.history.start();
