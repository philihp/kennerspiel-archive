import React, { Component, PropTypes } from 'react';

export default class MoveBox extends Component {
  render () {
    const { value, onChange, options } = this.props;

    return (
      <textarea rows="7" cols="80" onChange={e => onChange(e.target.value)}>
        {value}
      </textarea>
    );
  }
}

MoveBox.propTypes = {
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired
};
