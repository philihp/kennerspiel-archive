define(function (require, exports, module) {
  var Bootstrap = require('bootstrap');
  var React = require('react');
  var Jquery = require('jquery');
  var Board = require('components/Board');

  $('.history-hover').click(function () {
    $('.history-box').slideToggle(100);
  });

  React.render(
    <Board id={$('#app').data('instance')} />, document.getElementById('app')
  )
});