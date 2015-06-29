'use strict'

define(function (require, exports, module) {
  var React = require('react');
  var BoardStore = require('stores/BoardStore');
  var BoardActions = require('actions/BoardActions');
  var Buildings = require('components/Buildings');
  var Extensions = require('components/Extensions');
  var Rondel = require('components/Rondel');
  var Player = require('components/Player');

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
        var players = [];
        for(var i = 0; i < this.state.board.players.length; i++) {
          var playerData = this.state.board.players[i];
          players.push(
              <Player key={i}
                      color={playerData.color}
                      landscape={playerData.landscape.terrainTable}
                  />
          );
        }

        return (
          <div>
            <h3>Resource Rondel</h3>
            <Rondel armValues={this.state.board.wheel.armValues} table={this.state.board.wheel.table} />
            <h3>Unbuilt Buildings</h3>
            <Buildings buildings={this.state.board.unbuiltBuildings} />
            <h3>Extensions</h3>
            <Extensions type="Plots" costs={this.state.board.plotCosts}/>
            <Extensions type="Districts" costs={this.state.board.districtCosts}/>
            {players}
          </div>
        );
      }
    }

  });
});