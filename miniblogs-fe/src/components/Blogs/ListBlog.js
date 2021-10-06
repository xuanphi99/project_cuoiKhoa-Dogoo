import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import '../../assets/css/BlogIntro.css'
import ModalImage from "react-modal-image";

export default class ListBlog extends Component {


    render() {

        const showDate = (date) => {
            if(date !== undefined && date !== null )
          return (  new Date(...date).toDateString() )

            
        }
        return (
            <div className=" animate__zoomOut container blog-item mb-4" id={this.props.id}>
                <div className="row">
                    <div className="col-4">
                        <div className="img-blog">
                            <ModalImage
                                small={this.props.imgURL}
                                medium={this.props.imgURL}
                                showRotate={true}
                                alt={this.props.imgName}
                                className="img-fluid img-detail"
                            />
                        </div>
                    </div>
                    <div className="col-8">
                        <div className="blog--title">
                            <h4>{this.props.title}</h4>
                            <div className="blog--createInfo">
                                <span className="blog__createAt mr-1">
                                    { 
                                    showDate(this.props.createDate) || showDate(this.props.modifyDate)
                                    }  
                                
                                      </span> |
                                <span className="blog--category ml-1" style={{textTransform : "uppercase"}}> {this.props.categoryId}</span>
                            </div>
                            <p className="blog--text">
                                <span className="blog--content">{this.props.description}</span>

                                <span className="blog--read-more">

                                    <Link
                                        to={{
                                            pathname: '/blog-detail/' + this.props.id
                                        }}

                                    >Read more...
                               </Link>
                                </span>
                            </p>
                        </div>
                    </div>
                </div>

            </div>
        )
    }
}
