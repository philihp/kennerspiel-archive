define(function (require, exports, module) {
  var Bootstrap = require('bootstrap');
  var React = require('react');
  var Jquery = require('jquery');
  var WeblaboraApp = require('components/WeblaboraApp');

  $('.history-hover').click(function () {
    $('.history-box').slideToggle(100);
  });

  React.render(
    <WeblaboraApp />, document.getElementById('app')
  )
});