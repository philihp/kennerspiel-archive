'use strict'

define(function (require, exports, module) {
  var Bootstrap = require('bootstrap');
  var React = require('react');
  var Jquery = require('jquery');
  var Board = require('components/Board');

  Window.INSTANCE_ID = $('#app').data('instance');

  $('.history-hover').click(function () {
    $('.history-box').slideToggle(100);
  });

  $('#app').each(function(app) {
    React.render(
      <Board />, document.getElementById('app')
    )
  });

});