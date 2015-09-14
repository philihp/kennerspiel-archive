import React from 'react'
import boot from 'react-bootstrap'
import nav from 'react-router-bootstrap'


export default React.createClass({
  displayName: 'Header',


  render() {
    return (
      <boot.Navbar staticTop brand='React-Bootstrap' className="header">
        <boot.Nav>
          <nav.NavItemLink eventKey={1} to="home" >Home</nav.NavItemLink>
          <nav.NavItemLink eventKey={2} to="location" >Flux Location Example</nav.NavItemLink>
        </boot.Nav>
        <boot.Nav right>
          <boot.DropdownButton eventKey={3} title='Dropdown'>
            <nav.MenuItemLink eventKey='1' to="static" params={{ name: 'Page one' }}>Page1</nav.MenuItemLink>
            <nav.MenuItemLink eventKey='2'  to="static" params={{ name: 'Another page' }} >Another page</nav.MenuItemLink>
            <nav.MenuItemLink eventKey='3'  to="static" params={{ name: 'Something else here' }}>Something else here</nav.MenuItemLink>
            <boot.MenuItem divider />
            <nav.MenuItemLink eventKey='4' to="static" params={{ name: 'Separated link' }}>Separated link</nav.MenuItemLink>
          </boot.DropdownButton>
        </boot.Nav>
      </boot.Navbar>
    )
  }
})
