import React, { Component } from 'react'
import SideBar from '../layout/SideBar'
import FormTemplatePost from './FormTemplatePost'

export default class CreateNewPost extends Component {
    render() {
        return (
            <div className="container ">
                <div className="row">
                    <div className=" col-sm-12 col-md-3">
                        <SideBar />

                    </div>
                    <div className="col-sm-12 col-md-9">
                        <FormTemplatePost />

                    </div>

                </div>
            </div>
        )
    }
}
