'use strict'

define(function (require, exports, module) {
  var React = require('react');
  var BoardStore = require('stores/BoardStore');

  var Board = React.createClass({
    getInitialState() {
      return BoardStore.getState();
    },

    componentDidMount() {
      BoardStore.listen(this.onChange);
    },

    componentWillUnmount() {
      BoardStore.unlisten(this.onChange);
    },

    onChange(state) {
      this.setState(state);
    },

    render() {
      return (
          <div>
            <div>Rondel</div>
            <div>Unbuilt Buildings</div>
            <div>Plots and Districts</div>
            <div>Players...</div>
          </div>
      );
    }
  });

  module.exports = Board;

});