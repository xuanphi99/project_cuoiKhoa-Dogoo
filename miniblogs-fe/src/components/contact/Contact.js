import React, { Component } from 'react'
import SideBar from '../layout/SideBar'
import CardBlock from '../layout/CardBlock'
import FormContact from '../layout/FormContact'

export default class Contact extends Component {
    render() {
        return (
            <div className="container ">
            <div className="row">
                <div className="col-sm-12 col-md-3">
                    <SideBar />

                </div>
                <div className="col-sm-12 col-md-9">
                   <div className="about">
                    <CardBlock /> 
                    <CardBlock /> 
                    <CardBlock /> 
                    <CardBlock /> 

                    <FormContact />

                   
                    </div> 
                </div>

            </div>
        </div>
        )
    }
}
