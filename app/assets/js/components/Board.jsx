'use strict'

define(function (require, exports, module) {
  var React = require('react');
  var BoardStore = require('stores/BoardStore');
  var BoardActions = require('actions/BoardActions');
  var Buildings = require('components/Buildings');
  var Rondel = require('components/Rondel');

  return React.createClass({
    displayName: 'Board',

    getInitialState: function() {
      return BoardStore.getState();
    },

    componentDidMount: function() {
      BoardStore.listen(this.onChange);
      BoardActions.fetchBoard(this.props.id);
    },

    componentWillUnmount: function() {
      BoardStore.unlisten(this.onChange);
    },

    onChange: function(state) {
      this.setState(state);
    },

    render: function() {
      if(this.state.board == null) {
        return <div/>;
      }
      else {
        return (
          <div>
            <h3>Resource Rondel</h3>
            <Rondel armValues={this.state.board.wheel.armValues} table={this.state.board.wheel.table} />
            <h3>Unbuilt Buildings</h3>
            <Buildings buildings={this.state.board.unbuiltBuildings} />
            <div>Plots and Districts</div>
            <div>Players...</div>
          </div>
        );
      }
    }

  });
});