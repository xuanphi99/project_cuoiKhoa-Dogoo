import React, { Component, Fragment } from 'react'
import { Link } from 'react-router-dom'
import '../../assets/css/BlogIntro.css'
import '../../assets/css/ViewDetailBlog.css'
import ModalImage from "react-modal-image";
// import parse from 'html-react-parser';
export default class BlogItemDetail extends Component {


    render() {
        const linecap = <hr style={{ width: "50%", fontWeight: "bold", marginLeft: "0rem" }} />

        const showDate = (date) => {
            if(date !== undefined && date !== null )
          return (  new Date(...date).toDateString() )

        }
        return (
            <Fragment >
                <div className="blog-detail float-sm-right" >
                    <div className="img-blog img--detail">
                        <ModalImage
                            small={this.props.imgURL}
                            medium={this.props.imgURL}
                            showRotate={true}
                            alt={this.props.imgName || ''}
                            className="img-fluid img-detail"
                        />
                    </div>
                    <div className="blog--title blog--detail ">
                        <h4 >{this.props.title}</h4>
                        <div className="blog--createInfo">
                            <span className="blog__createAt mr-1">{showDate(this.props.createDate) || showDate(this.props.modifyDate) }</span> | 
                            <span className="blog--category ml-1">{this.props.categoryId}</span>
                        </div>
                        {linecap}
                        <p className="content--blog">
                            { 
                            //this.props.content
                             window.HTMLReactParser("" + this.props.content)
                            }   
                        </p>
                        
                            
                    </div>
                </div >
                <Link to={
                    {
                        pathname: '/home'

                    }
                } >
                    <button type="button" className="btn btn-secondary all-blog ">

                        <i className="fal fa-arrow-left "></i> All post

                     </button> 
                </Link>
            </Fragment>

        )
    }
}
