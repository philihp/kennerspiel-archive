import React from 'react'
import locationActions from '../actions/LocationActions'
import locationStore from '../stores/LocationStore'


export default React.createClass({
  displayName: 'LocationComponent',
  getInitialState() {
    return locationStore.getState()
  },

  componentDidMount() {
    locationStore.listen(this.onChange)
  },

  componentWillUnmount() {
    locationStore.unlisten(this.onChange)
  },

  onChange() {
    this.setState(this.getInitialState())
  },

    _cityChanged(event) {
        locationActions.updateLocation([event.target.value, this.state.country])
    },

     _countryChanged(event) {
        locationActions.updateLocation([this.state.city, event.target.value])
    },

  render() {
    return (
      <div>
        <p>
          City {this.state.city}
        </p>
        <p>
          Country {this.state.country}
        </p>
          <input type="text" value={this.state.city} onChange={this._cityChanged} ></input>
          <input type="text" value={this.state.country} onChange={this._countryChanged} ></input>
      </div>
    )
  }
})
