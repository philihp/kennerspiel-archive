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

var Tables = Backbone.Collection.extend({
	url: '/tables'
});
var Table = Backbone.Model.extend({
	urlRoot: '/tables'
})

var TableList = Backbone.View.extend({
	el: '#page',
	render: function() {
		var that = this;
		var tables = new Tables();
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
	render: function(options) {
		var that = this;
		if(options.id) {
			var table = new Table({id: options.id});
			table.fetch({
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
	events: {
		'submit .table-edit-form': 'saveTable'
	},
	saveTable: function (ev) {
		var tableDetails = $(ev.currentTarget).serializeObject();
		var table = new Table();
		console.log('saving');
		table.save(tableDetails, {
			success: function() {
				console.log('saved');
				router.navigate('', {trigger: true});
			}
		})
		return false;
	}
})

var Router = Backbone.Router.extend({
	routes: {
		'' : 'home',
		'new' : 'tableEdit',
		'edit/:id' : 'tableEdit'
	}
})

var tableList = new TableList();
var tableEdit = new TableEdit();

var router = new Router();
router.on('route:home', function() {
	tableList.render();
});
router.on('route:tableEdit', function(id) {
	tableEdit.render({id: id});
});

Backbone.history.start();
