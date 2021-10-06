import React, { Component } from 'react'
import { NavLink } from 'react-router-dom'
import '../../assets/css/Menu.css'
export default class Menu extends Component {
    render() {
        return (
            <nav className="navbar navbar-expand-sm navbar-light bg-light menu__nav">
            <NavLink activeClassName="active" to="/" className="navbar-brand" >
              Denali
            </NavLink>
            <button className="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon" />
            </button>
            <div className="collapse navbar-collapse font-weight-bold  " id="collapsibleNavId">
              <ul className="navbar-nav ml-auto ">
                <li className="nav-item ">
                  <NavLink activeClassName="active" to="/home" className="nav-link" > Home <span className="sr-only">(current)</span></NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/about" className="nav-link" >About</NavLink>
                </li>
                <li className="nav-item">
                  <NavLink activeClassName="active" to="/contact" className="nav-link" >Contact</NavLink>
                </li> 
               
              </ul>

            </div>
          </nav>
        )
    }
}
