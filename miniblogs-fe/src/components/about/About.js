import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import CardBlock from '../layout/CardBlock'
import SideBar from '../layout/SideBar'

export default class About extends Component {


    render() {
        return (
            <div className="container ">
            <div className="row">
                <div className="col-sm-12 col-md-3">
                    <SideBar />

                </div>
                <div className="col-sm-12 col-md-9 " >
                    <div className="about" style={{background : 'while'}}>
                   <CardBlock />  <CardBlock />  <CardBlock />  <CardBlock />  <CardBlock />  <CardBlock />
                   <Link 
                               to = {{
                                   pathname: '/contact' 
                               }}
                               >
                   <button  className="btn btn-secondary mt-2 mb-3 ml-3 ">Get In Touch 
                        
                    </button>
                   </Link  >
                    </div>
                </div>

            </div>
        </div>
        )
    }
}
