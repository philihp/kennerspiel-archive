'use strict'

define(function (require, exports, module) {
  var React = require('react');
  var BoardStore = require('stores/BoardStore');
  var BoardActions = require('actions/BoardActions');
  var Rondel = require('components/Rondel');

  return React.createClass({
    displayName: 'Board',

    getInitialState: function() {
      return BoardStore.getState();
    },

    componentDidMount: function() {
      BoardStore.listen(this.onChange);
      BoardActions.fetchBoard();
    },

    componentWillUnmount: function() {
      BoardStore.unlisten(this.onChange);
    },

    onChange: function(state) {
      this.setState(state);
    },

    render: function() {
      if(this.state.board == null) {
        return (
            <div>Loading</div>
        );
      }
      else {
        return (
          <div>
            <Rondel armValues={this.state.board.wheel.armValues} />
            <div>Unbuilt Buildings</div>
            <div>Plots and Districts</div>
            <div>Players...</div>
          </div>
        );
      }
    }

  });
});