
var Board = React.createClass({
  render: function() {
    return (
        <div className="board">
          <h3>Resource Roundel</h3>
          <h3>Unbuilt Buildings</h3>
        </div>
    );
  }
});

window.weblabora = {
  id: null,
  load: function(id) {
    this.id = id;
    this.reload();
  },
  reload: function() {
    $.ajax({
      url: '/instance/'+this.id+'/board',
      data: {
        move: $('#token').val()
      },
      success: function (data) {
        window.weblabora.board = data;
        React.render(<Board/>, document.getElementById('board'));
      }
    });
  }
};
