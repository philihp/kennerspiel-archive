import React, { PropTypes, Component } from 'react';

const propTypes = {
  token: PropTypes.object.isRequired,
  radius: PropTypes.number,
};

const defaultProps = {
  radius: 50,
};

export default class RondelToken extends Component {

  _rotate(pos) {
    switch (pos) {
    case 'A':
      return 360 * 12.5 / 13;
    case 'B':
      return 360 * 11.5 / 13;
    case 'C':
      return 360 * 10.5 / 13;
    case 'D':
      return 360 * 9.5 / 13;
    case 'E':
      return 360 * 8.5 / 13;
    case 'F':
      return 360 * 7.5 / 13;
    case 'G':
      return 360 * 6.5 / 13;
    case 'H':
      return 360 * 5.5 / 13;
    case 'I':
      return 360 * 4.5 / 13;
    case 'J':
      return 360 * 3.5 / 13;
    case 'K':
      return 360 * 2.5 / 13;
    case 'L':
      return 360 * 1.5 / 13;
    case 'M':
      return 360 * 0.5 / 13;
    default:
      return 0;
    }
  }

  render() {
    const radius = -this.props.radius;
    const position = this.props.token.position;

    if (!this.props.token.active) return null;

    return (
      <g id="grape" transform={'rotate(' + this._rotate(position) + ')'}>
        <text x={0} y={radius} style={{fontSize: '10px', kerning: -0.5, textAnchor: 'middle', fill: '#000'}}>
          {this.props.label}
        </text>
      </g>
    );
  }
}

RondelToken.propTypes = propTypes;
RondelToken.defaultProps = defaultProps;
