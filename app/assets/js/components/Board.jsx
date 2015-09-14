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
      BoardActions.fetchBoard(BoardStore.getState().token);
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
                      inventory={playerData.inventory}
                  />
          );
        }

        return (
          <div>
            <div className="row">
              <div className="col-md-8">
                <h3>Resource Rondel</h3>
                <Rondel armValues={this.state.board.wheel.armValues} table={this.state.board.wheel.table} />
              </div>
              <div className="col-md-4">
                <input type="text" value={this.state.token} readOnly="true" />
                <h3>Extensions</h3>
                <Extensions type="Plot" command="P(0,COAST)" costs={this.state.board.plotCosts}/>
                <Extensions type="District" command="D(2,HILLS)" costs={this.state.board.districtCosts}/>

                <button className="btn btn-warning" onClick={this._undo}>Undo</button>

              </div>
            </div>
            <h3>Unbuilt Buildings</h3>
            <Buildings buildings={this.state.board.unbuiltBuildings} />
            {players}
          </div>
        );
      }
    },

    _undo: function() {
      BoardActions.undoCommand();
    }

  });
});
