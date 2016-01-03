import React, { PropTypes, Component } from 'react';
import RondelToken from './RondelToken';

const propTypes = {
  wheel: PropTypes.object.isRequired,
};

export default class Rondel extends Component {

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

  _armPath() {
    const ARM_RADIUS = 28;
    const ARM_WIDTH = 4;
    const ARM_LENGTH = 110;
    return 'M' + (-ARM_WIDTH / 2) + ','
      + (-ARM_LENGTH) + ' Q' + (-ARM_WIDTH / 2) + ','
      + (-ARM_RADIUS) + ' ' + (Math.sin((12.0 / 13.0) * 2 * Math.PI) * ARM_RADIUS) + ','
      + (-Math.cos((12.0 / 13.0) * 2 * Math.PI) * ARM_RADIUS) + ' A' + ARM_RADIUS + ','
      + ARM_RADIUS + ' 0 1,0 ' + (Math.sin((1.0 / 13.0) * 2 * Math.PI) * ARM_RADIUS) + ','
      + (-Math.cos((1.0 / 13.0) * 2 * Math.PI) * ARM_RADIUS) + ' Q' + (ARM_WIDTH / 2) + ','
      + (-ARM_RADIUS) + ' ' + (ARM_WIDTH / 2) + ','
      + (-ARM_LENGTH) + ' z';
  }

  render() {
    if (!this.props.wheel) return null;

    // TODO refactor this as proper member vars

    const SEGS = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

    const WHEEL_RADIUS = 140;
    const ARM_TEXT_RADIUS = -19;


    const ARROW_RADIUS = 34;
    const ARROW_SIZE = 7;
    const ARROW_PATH = `M ${(-ARROW_SIZE / 2)}, ${(-ARROW_RADIUS)} l${ARROW_SIZE}, ${ARROW_SIZE / 2} v${-ARROW_SIZE} z`;

    const points = new Array(13);
    for (let n = 0; n < 13; n++) {
      const x = -Math.sin(n * Math.PI * 2 / points.length) * WHEEL_RADIUS;
      const y = -Math.cos(n * Math.PI * 2 / points.length) * WHEEL_RADIUS;
      points[n] = `${x} ${y}`;
    }

    const viewBox = [-150.5, -150.5, 300, 300].join(' ');

    return (
      <div style={{backgroundColor: '#FFDAE2'}}>
        <svg style={{height: 300, width: 300}} viewBox={viewBox}>
          <defs>
            <linearGradient id="housefill" x1="0%" y1="0%" x2="100%" y2="0%">
              <stop offset="0%" style={{stopColor: '#004e85', stopOpacity: 1}} />
              <stop offset="100%" style={{stopColor: '#1973b2', stopOpacity: 1}} />
            </linearGradient>
            <filter id="shadow" x="0" y="0">
              <feGaussianBlur in="SourceGraphic" stdDeviation="5" />
            </filter>
          </defs>
          <g id="shadowMask" opacity="0.1">
            <polyline points={points.join(' ')} fill="black" filter="url(#shadow)" />
          </g>
          <g id="wheel">
            {SEGS.map(i => {
              return (
                <polyline key={`segblock-${i}`} fill="#fcfcfc" stroke="#b3b3b3" strokeWidth="1" points={`0,0 ${points[i]} ${points[(i + 1) % SEGS.length]} 0,0`} />
              );
            })}
          </g>
          <g id="arm" transform="rotate(0)" style={{fontSize: '10px', textAnchor: 'middle'}}>
            <path d={this._armPath()} style={{fill: '#ffffff', fillOpacity: 1, stroke: '#686868', strokeWidth: 1}} />{SEGS.map(i => {
              return (
                <text key={`wheeltext-${i}`} x="0" y={ARM_TEXT_RADIUS} transform={'rotate(' + (360 * (12.5 - i) / 13) + ')'}>{this.props.wheel.armValues[12 - i]}</text>
              );
            })}
            <path d={ARROW_PATH} fill="#000" />
          </g>
          <RondelToken label="Grape" token={this.props.wheel.grape} radius={125} />
          <RondelToken label="Stone" token={this.props.wheel.stone} radius={115} />
          <RondelToken label="Grain" token={this.props.wheel.grain} radius={105} />
          <RondelToken label="Sheep" token={this.props.wheel.sheep} radius={95} />
          <RondelToken label="Wood" token={this.props.wheel.wood} radius={85} />
          <RondelToken label="Joker" token={this.props.wheel.joker} radius={75} />
          <RondelToken label="Peat" token={this.props.wheel.peat} radius={65} />
          <RondelToken label="Coin" token={this.props.wheel.coin} radius={55} />
          <RondelToken label="Clay" token={this.props.wheel.clay} radius={45} />
        </svg>
      </div>
    );
  }

}

Rondel.propTypes = propTypes;
