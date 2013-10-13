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

Handlebars.registerHelper('times', function(n, block) {
    var accum = '';
    for(var i = 0; i < n; ++i)
        accum += block.fn(i);
    return accum;
});

Handlebars.registerHelper('equals', function(v1, v2, options) {
	if(v1 == v2) {
		return options.fn(this);
	}
	return options.inverse(this);
});

Handlebars.registerHelper("log", function(context) {
	return console.log(context);
});


var Instance = Backbone.RelationalModel.extend({
	urlRoot: '/api/instances',
	idAttribute: '_id',
	relations: [{
		type: Backbone.HasMany,
		key: "commands",
		relatedModel: 'Command',


		reverseRelation: {
			key: 'instance.id',
			includeInJSON: '_id'
		}
	}]
});
var Command = Backbone.RelationalModel.extend({
	urlRoot: '/api/commands',
	idAttribute: '_id'
});
var InstanceCollection = Backbone.Collection.extend({
	url: '/api/instances',
	model: Instance
});

var InstanceList = Backbone.View.extend({
	el: '#page',
	events: {
	},
	render: function() {
		var that = this;
		var instances = new InstanceCollection();
		instances.fetch({
			success: function() {
				var template = Handlebars.compile($('#instances-list-template').html());
				that.$el.html(template({instances: instances.models}));
			}
		});
	}
});

var InstanceEdit = Backbone.View.extend({
	el: '#page',
	events: {
		'submit .instance-edit-form': 'saveInstance',
		'click .delete' : 'deleteInstance'
	},
	render: function(options) {
		var that = this;
		that.instance = Instance.findOrCreate({id: options.id});
		that.instance.fetch({
			success: function(instance) {
				var template = Handlebars.compile($('#instance-edit-template').html());
				that.$el.html(template({instance: instance}));
			}
		});
	},
	saveInstance: function(ev) {
		var instanceDetails = $(ev.currentTarget).serializeObject();
		var instance = new Instance();
		instance.save(instanceDetails, {
			success: function (instance) {
				router.navigate('', {trigger: true});
			}
		})
		return false;
	}
});


var InstanceView = Backbone.View.extend({
	el: '#page',
	events: {
		'submit .commit-form': 'sendCommand',
		'submit .undo-form': 'sendCommand',
		'submit .command-form': 'sendCommand'
	},
	render: function(options) {
		var that = this;
		that.instance = Instance.findOrCreate(options);
		that.instance.fetch({
			success: function(instance) {
				var template = Handlebars.compile($('#instance-view-template').html());
				that.$el.html(template(instance));

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
		
		console.log("Sending Command ", $(ev.target).data('command'));

		var that = this;
		var commandDetails = {
				'instance.id': this.instance,
				'command': $(ev.target).data('command')
		}

		var command = new Command();
		command.save(commandDetails, {
			success: function(command, xhr) {
				that.render({
					_id: command.get('instance.id').id,
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
		'new' : 'instanceEdit',
		'view/:id' : 'instanceView'
	},
	home: function() {
		instanceList.render();
	},
	instanceEdit: function(id) {
		instanceEdit.render({_id: id});
	},
	instanceView: function(id) {
		instanceView.render({_id: id});
	}
})

var instanceList = new InstanceList();
var instanceEdit = new InstanceEdit();
var instanceView = new InstanceView();
var router = new Router();

Backbone.history.start();
