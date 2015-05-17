
var Rondel = React.createClass({
  getInitialState: function() {
    return { wheel: {} };
  },
  render: function() {
    for(var i= 0, cols= []; i < this.props.wheel.armValues.length; i++) {
      cols.push(<th key={i}>{this.props.wheel.armValues[i]}</th>);
    }
    return (
      <div>
        <h3>Resource Roundel</h3>
        <table className="table">
          <tr>
            { cols }
          </tr>
          <tr>

          </tr>
        </table>
      </div>
    );
  }
});


              //this.props.wheel.armValues.map(function (v) {
       //{{#each table}}
            //  <td>{{#each this}}{{this}}<br />{{/each}}</td>
            //{{/each}}
var Board = React.createClass({
  getInitialState: function() {
    return { id: null, token: '' };
  },
  componentDidMount: function() {
    $.ajax({
      url: '/instance/' + this.props.id + '/board',
      data: {
        move: this.state.token
      },
      success: function (data) {
        this.setState({data: data});
      }.bind(this),
      error: function (xhr, status, err) {
        console.error(this.props.id, status, err.toString());
      }.bind(this)
    });
  },
  render: function() {
    if(!this.state.data) {
      return null;
    }
    return (
      <div className="board">
        <Rondel wheel={this.state.data.wheel} />
      </div>
    );
  }
});

window.weblabora = {
  load: function(id) {
    this.id = id;
    this.reload();
  },
  reload: function() {
    React.render(<Board id={this.id} />, document.getElementById('board'));
  }
};
