'use strict'

define(function (require, exports, module) {
  var React = require('react');

  return React.createClass({
    displayName: 'Erection',

    render: function() {
      switch(this.props.terrainUse) {
        case 'FOREST':
            return (<img src="/assets/images/weblabora/terrain/Wood.jpg" class="img" />);
        case 'MOOR':
            return (<img src="/assets/images/weblabora/terrain/Peat.jpg" class="img" />);
        case 'BUILDING':
            return (
                <img src={'/assets/images/weblabora/buildings/'+this.props.building.image+'.jpg'}
                     className="img building clickable"
                     data-toggle="modal"
                     data-target="#useModal"
                     data-id={this.props.building.id}
                     data-image={this.props.building.image}
                     data-name={this.props.building.name}
                     />
            );
      }
      return (<div/>);
    }

  });
});