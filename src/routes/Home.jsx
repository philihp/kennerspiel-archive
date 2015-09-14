import React from 'react'
import locationStore from '../stores/LocationStore'
import nav from "react-router-bootstrap"

export default React.createClass({
    displayName: 'Home',
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
      render() {
        return (
            <div>
            <h1>
                Hello
            </h1>

                <p> Your current country is {this.state.country}</p>
                <p> Your current city is {this.state.city} </p>
                <nav.ButtonLink bsStyle='info' to="location"> Change your location !</nav.ButtonLink>
            </div>

        )
    }
})