/**
 * Created by phil_busby on 15-05-23.
 */

$(document).ready(function () {
  var Handlebars = { registerHelper: function() {} };
  Handlebars.registerHelper('chit', function(n, image) {
    s='';
    for(i=0; i<n; i++) {
      s+='<img src="/assets/images/weblabora/chit/'+image+'.jpg" class="img-rounded" /> ';
    }
    return new Handlebars.SafeString(s);
  });
  Handlebars.registerHelper('lower', function(str) {
    if(str == null) return "";
    return str.toLowerCase();
  });
  Handlebars.registerHelper('erection', function(use, building) {
    switch(use) {
      case 'FOREST':
        return new Handlebars.SafeString('<img src="/assets/images/weblabora/terrain/Wood.jpg" class="img" />');
      case 'MOOR':
        return new Handlebars.SafeString('<img src="/assets/images/weblabora/terrain/Peat.jpg" class="img" />');
      case 'BUILDING':
        return new Handlebars.SafeString('<img src="/assets/images/weblabora/buildings/'+
            (building.image)+
            '.jpg" class="img building clickable" '+
            'data-toggle="modal" '+
            'data-target="#useModal" '+
            'data-id="'+building.id+'" '+
            'data-image="'+building.image+'" '+
            'data-name="'+building.name+'" '+
            '/>');
    }
  });

  $('#useModal').on('show.bs.modal', function (event) {
    var clickable = $(event.relatedTarget); // Button that triggered the modal
    var image = clickable.data('image'); // Extract info from data-* attributes
    var name = clickable.data('name'); // Extract info from data-* attributes
    var id = clickable.data('id');
    var modal = $(this);
    modal.data('id', id);
    modal.data('params', '');
    modal.data('modifier', '');
    $('#useModal').find('.modal-footer').find('.btn-primary').prop('disabled', false);
    modal.find('.modal-title').text('Use ' + name);
    modal.find('.modal-body').load('/assets/html/weblabora/'+image+'.html');
  });

  $('#useClergyman').click(function() {
    var modal = $('#useModal');
    var id = modal.data('id');
    var params = modal.data('params');
    var modifier = modal.data('modifier');
    $('#token').val('use('+id+params+')'+modifier);
    modal.modal('hide');

    window.weblabora.reload();
  })

  $('#usePrior').click(function() {
    $('#useModal').data('modifier', '*');
    $('#useClergyman').click();
  })

  window.weblabora.load(@instance.id);
}) // $(document).ready()
