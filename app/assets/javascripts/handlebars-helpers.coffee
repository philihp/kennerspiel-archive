
Handlebars.JavaScriptCompiler.prototype.nameLookup = (parent, name, type) ->
  result = '(' + parent + ' instanceof Backbone.Model ? ' + parent + '.get("' + name + '") : ' + parent;
  if /^[0-9]+$/.test name
    result + "[" + name + "])"
  else if Handlebars.JavaScriptCompiler.isValidJavaScriptVariableName name
    result + "." + name + ")"
  else
    result + "['" + name + "'])"
  
Handlebars.registerHelper "log", (context) ->
  console.log context  

Handlebars.registerHelper "equals", (v1, v2, options) ->
  if v1==v2
    options.fn this
  else
    options.inverse this
