import React from 'react';
import { Link } from 'react-router';

const NAV_LINKS = {
  'profile': {
    link: '/profile.html',
    title: 'Profile'
  },
  'instances': {
    link: '/instances.html',
    title: 'Instances'
  },
  'stats': {
    link: '/stats.html',
    title: 'Stats'
  },
};

const NavMain = React.createClass({
  propTypes: {
    activePage: React.PropTypes.string
  },

  render() {
    let brand = <Link to="/" className="navbar-brand">React-Bootstrap</Link>;
    let links = Object.keys(NAV_LINKS).map(this.renderNavItem);

    return (
      <nav className="navbar navbar-default navbar-static-top">
        <div className="container-fluid">
          <div className="navbar-header">
            <a className="navbar-brand" href="#">Kennerspiel</a>
          </div>
          <div className="collapse navbar-collapse">
            <ul className="nav navbar-nav">
              {links}
            </ul>
          </div>
        </div>
      </nav>
    );
  },

  renderNavItem(linkName) {
    let link = NAV_LINKS[linkName];

    return (
      <li className={this.props.activePage === linkName ? 'active' : null} key={linkName}>
        <a href={link.link}>{link.title}</a>
      </li>
    );
  }
});

export default NavMain;
