import React, { Component } from 'react'
import PostService from '../../service/PostService';
import SideBar from '../layout/SideBar'
import BlogItemDetail from './BlogItemDetail';
import Swal from 'sweetalert2';

export default class BlogItem extends Component {


    constructor(props) {
        super(props);
        this.state = {
            idPost : this.props.match.params.id,
            dataPost : {}
        }
    }
    
    componentDidMount() {
        PostService.getDetailPost(this.state.idPost)
        .then((response) => {
            this.setState({
                dataPost: response.data ,

                });

          })
          .catch(function (err) {
            Swal.fire(err.response.data.reasonCode,  err.response.data.description, 'error')
          });
    }
    


    render() {
        
        return (
            <div className="container ">
            <div className="row">
                <div className="col-sm-12 col-md-3">
                    <SideBar />

                </div>
                <div className="col-sm-12 col-md-9">
                 {  <BlogItemDetail
                   
                   idPost = {this.state.dataPost.idPost}
                   idName = {this.state.dataPost.imgName}
                   imgURL = {this.state.dataPost.imgURL}
                   title = {this.state.dataPost.title}
                   createBy = {this.state.dataPost.createBy}
                   createDate = {this.state.dataPost.createDate}
                   modifyDate = {this.state.dataPost.modifyDate}
                   content = {this.state.dataPost.content}
                   description = {this.state.dataPost.description}
                   categoryId = {this.state.dataPost.categoryId}
                   
                   
                   />
                 }
                   
                </div>

            </div>
        </div>
        )
    }
}
