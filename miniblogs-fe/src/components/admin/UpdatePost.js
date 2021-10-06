import React, { Component } from 'react'
import SideBar from '../layout/SideBar'
import FormTemplatePost from './FormTemplatePost'

export default class UpdatePost extends Component {

    constructor(props) {
        super(props);
        this.state = {
            idPost : this.props.match.params.id,
            dataPost : {}
        }
    }

    

    render() {
        return (
            <div className="container ">
                <div className="row">
                    <div className=" col-sm-12 col-md-3">
                        <SideBar />

                    </div>
                    <div className="col-sm-12 col-md-9">
                        <FormTemplatePost 
                            idPost = {this.props.match.params.id}
                           
                        />

                    </div>

                </div>
            </div>
        )
    }
}
