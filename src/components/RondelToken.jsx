import React, { Component } from 'react';

export default class RondelToken extends Component {

  render() {
    const radius = this.props.token.radius;
    const position = this.props.token.position;

    if(!this.props.token.active) return null;

    return (
      <g id="grape" transform={'rotate('+this._rotate(position)+')'}>
        <text x={0} y={radius} style={{fontSize: '10px', kerning:-0.5, textAnchor: 'middle', fill:'#000'}}>
          {this.props.label}
        </text>
      </g>
    );
  }

  _rotate(pos) {
    if (pos == 'A')
			return 360 * 12.5 / 13;
		if (pos == 'B')
			return 360 * 11.5 / 13;
		if (pos == 'C')
			return 360 * 10.5 / 13;
		if (pos == 'D')
			return 360 * 9.5 / 13;
		if (pos == 'E')
			return 360 * 8.5 / 13;
		if (pos == 'F')
			return 360 * 7.5 / 13;
		if (pos == 'G')
			return 360 * 6.5 / 13;
		if (pos == 'H')
			return 360 * 5.5 / 13;
		if (pos == 'I')
			return 360 * 4.5 / 13;
		if (pos == 'J')
			return 360 * 3.5 / 13;
		if (pos == 'K')
			return 360 * 2.5 / 13;
		if (pos == 'L')
			return 360 * 1.5 / 13;
		if (pos == 'M')
			return 360 * 0.5 / 13;
		return 0;
  }
}
