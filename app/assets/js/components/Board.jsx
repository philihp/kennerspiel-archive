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
          <div>Board</div>
      );
    }
  });

  module.exports = Board;

});