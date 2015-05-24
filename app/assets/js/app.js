require(["bootstrap", "react", "jquery"], function(common) {
  $('.history-hover').click(function() {
    $('.history-box').slideToggle(100);
  });

  console.log('started');
});