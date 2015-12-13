import React from 'react';
import { Link } from 'react-router';

const NAV_LINKS = {
  'profile': {
    link: '/profile.html',
    title: 'Profile',
  },
  'instances': {
    link: '/instances.html',
    title: 'Instances',
  },
  'stats': {
    link: '/stats.html',
    title: 'Stats',
  },
};

const NavMain = React.createClass({
  propTypes: {
    activePage: React.PropTypes.string,
  },

  renderNavItem(linkName) {
    const link = NAV_LINKS[linkName];

    return (
      <li className={this.props.activePage === linkName ? 'active' : null} key={linkName}>
        <a href={link.link}>{link.title}</a>
      </li>
    );
  },

  render() {
    const brand = <Link to="/" className="navbar-brand">Kennerspiel</Link>;
    const links = Object.keys(NAV_LINKS).map(this.renderNavItem);

    return (
      <nav className="navbar navbar-default navbar-static-top">
        <div className="container-fluid">
          <div className="navbar-header">
            {brand}
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

});

export default NavMain;
