import alt from '../alt';

class LocationActions {
  constructor() {
    this.generateActions('updateLocation') // ['South Lake Tahoe, 'California']
  }
}

export default alt.createActions(LocationActions)

