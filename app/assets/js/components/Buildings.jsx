'use strict'

define(function (require, exports, module) {
  var React = require('react');

  return React.createClass({
    displayName: 'Buildings',

    render: function() {
      var buildings = [];
      for(var i = 0; i < this.props.buildings.length; i++) {
        buildings.push(<img key={i} className="building" src={'/assets/images/weblabora/buildings/'+this.props.buildings[i].image+'.jpg'} />);
      }
      return (<div>{buildings}</div>);
    }

  });
});