import React, { Component } from 'react'
import { PostConstant } from '../../constant/Constant';
import { MessageResponse } from '../../constant/ResponseMessage';
import PostService from '../../service/PostService';
import Message from '../Alert/Message';
import ListBlog from '../Blogs/ListBlog'
import SideBar from '../layout/SideBar'
import SearchPost from './SearchPost';

export default class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            pageSize: PostConstant.TOTAL_PAGE_LIMIT,
            currentPage: 0,
            totalPage: 1,
            isShowButtonMorePost: true,
            isShowModal: false,
            contentMsgAlert: {},
            iconLoader: false

        };
    }

    // Enable alert
    callbackFunction = (childData) => {
        this.setState({
            isShowModal: childData,
            search : ""
        });
    }

    submitForm = () => {
        this.setState({
            iconLoader: true
        });
    }

    // Click more post
    handlerChangeCurrentPage = () => {

        this.submitForm()

        var self = this;

        // Do currentPage bắt đầu từ 0 
        if (this.state.currentPage < this.state.totalPage - 1) {

            PostService.getAllPostWithPagination(this.state.currentPage + 1, this.state.pageSize)
                .then((response) => {
                    this.setState({
                        posts: this.state.posts.concat(response.data.data),
                        currentPage: response.data.paging.currentPage,
                        iconLoader: !self.state.iconLoader,
                    });

                    if (this.state.currentPage + 1 === this.state.totalPage) {
                        this.setState({
                            isShowButtonMorePost: !this.state.isShowButtonMorePost

                        });
                    }


                })
                .catch(function (error) {
                    self.setState({
                        isShowModal: !self.state.isShowModal,
                        iconLoader: !self.state.iconLoader,
                        contentMsgAlert: {
                            title: "ERROR_CONNECT_API",
                            text: MessageResponse.ERROR_CONNECT_CALL_API,
                            icon: 'error',
                            confirmButtonText: 'Close'

                        }
                    })
                });
        }



    }

    checkShowButton() {
        if (this.state.currentPage === this.state.totalPage || this.state.totalPage === 1) {
            this.setState({
                isShowButtonMorePost: !this.state.isShowButtonMorePost
            });
        }


    }

    sendDataFilter = (data) => {
        console.log(data);
        this.setState({
            search : data,
             posts : data.data,
             totalPage: data.data.paging.totalPage
        });
    }

    componentDidMount() {
        var self = this
        PostService.getAllPostWithPagination(this.state.currentPage, this.state.pageSize)
            .then((response) => {
                this.setState({
                    posts: response.data.data,
                    totalPage: response.data.paging.totalPage

                });
                this.checkShowButton()

            })
            .catch(function (error) {
                self.setState({
                    isShowModal: !self.state.isShowModal,
                    isShowButtonMorePost: false,
                    contentMsgAlert: {
                        title: "ERROR_CONNECT_API",
                        text: MessageResponse.ERROR_CONNECT_CALL_API,
                        icon: 'error',
                        confirmButtonText: 'Close'

                    }
                })
            });
    }


    render() {
        return (
           <div className="home">
               
               <div className="container search">
                    <div className="row">
                        <div className="col-4 mb-2 text-center">
                            <SearchPost
                                sendDataFilter={ (data) => {this.sendDataFilter(data)}}
                            />
                        </div>
                    </div>
               </div>
         
            <div className="container ">
                <div className="row">
                    <div className=" col-sm-12 col-md-3">
                        <SideBar />

                    </div>
                    <div className="col-sm-12 col-md-9">
                        {
                            this.state.posts.map((item, index) => {
                                return (
                                    <ListBlog

                                        key={index}
                                        id={item.idPost}
                                        idName={item.imgName}
                                        imgURL={item.imgURL}
                                        title={item.title}
                                        createBy={item.createBy}
                                        createDate={item.createDate || item.modifyDate}
                                        content={item.content}
                                        description={item.description}
                                        categoryId={item.categoryId}

                                    />
                                )
                            })
                        }

                      

                        {
                            this.state.isShowButtonMorePost === true ?
                                <button type="button" onClick={() => { this.handlerChangeCurrentPage() }} className="btn btn-secondary " style={{ marginLeft: "2rem" }}>More post
                          <i className="fal fa-arrow-right pl-2"></i>

                                    {
                                        this.state.iconLoader === true ? (

                                            <span className="loader" ref={this.loader}>
                                                <i className="fas fa-circle-notch fa-spin"></i>
                                            </span>) : ""
                                    }


                                </button> : ""
                        }


                        {
                            this.state.isShowModal === true ? (
                                <Message parentCallback={(childData) => { this.callbackFunction(childData) }}
                                    title={this.state.contentMsgAlert.title}
                                    text={this.state.contentMsgAlert.text}
                                    icon={this.state.contentMsgAlert.icon} />
                            ) : ""
                        }

                    </div>

                </div>
            </div>
            </div>
        )
    }
}
