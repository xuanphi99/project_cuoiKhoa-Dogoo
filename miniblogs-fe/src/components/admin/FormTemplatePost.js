import React, { Component } from 'react'
import 'antd/dist/antd.css';
import { Form, Select, Button, Input } from 'antd';
import PostService from '../../service/PostService';
import Message from '../Alert/Message';
import { MessageResponse, SweetAlertInfo } from '../../constant/ResponseMessage';
import Swal from 'sweetalert2';
import { Image } from 'antd';

export default class FormTemplatePost extends Component {

    formRef = React.createRef();
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            categories: [],
            selectedFile: {},
            fileName: "",
            imgShow: "",
            isShowModal: false,
            contentMsgAlert: {},
            dataInit: {}

        };
    }
    // Receivers from message
    callbackFunction = (childData) => {
        this.setState({
            isShowModal: childData
        });
    }
    // Get All Categories


    componentDidMount() {

        // init Data

        var self = this;
        PostService.getAllCategory()
            .then((res) => {

                this.setState({
                    posts: res.data,
                });
            })

            .catch(function (err) {
                self.setState({
                    isShowModal: !self.state.isShowModal,
                    contentMsgAlert: {
                        title: "ERROR_CONNECT_API",
                        text: MessageResponse.ERROR_CONNECT_CALL_API,
                        icon: 'error',
                        confirmButtonText: 'Close'

                    }
                })

            });

        if (this.props.idPost !== undefined) {
            PostService.getDetailPost(this.props.idPost)
                .then((response) => {
                    this.setState({
                        dataInit: response.data,
                        imgShow : response.data.imgURL
                    });
                    // set Data
                    this.formRef.current.setFieldsValue({
                        title: response.data.title,
                        createBy: response.data.createBy,
                        content: response.data.content,
                        description: response.data.description,
                        Category: response.data.categoryId,

                    })

                })
                .catch(function (err) {
                    Swal.fire(err.response.data.reasonCode, err.response.data.description, 'error')
                });
        }


    }


    onFileChange = event => {

        // Update the state
        if (event.target.files && event.target.files[0]) {
            this.setState({
                selectedFile: event.target.files[0],
                fileName: event.target.files[0].name,
                imgShow: URL.createObjectURL(event.target.files[0])
            });
        }

    };





    render() {


        const { Option } = Select;
        const formItemLayout = {
            labelCol: {
                span: 6,
            },
            wrapperCol: {
                span: 14,
            },
        };



        const onFinish = (values) => {


            var jsonData = {
                'title': values.title,
                'description': values.description,
                'content': values.content,
                'categoryId': values.Category,
                'createBy': values.createBy

            }
            const formData = new FormData();
           
            var self = this;
            if (this.props.idPost === null || this.props.idPost === undefined) {
                formData.append('file', this.state.selectedFile)
                formData.append('jsonData', JSON.stringify(jsonData))
                console.log(JSON.stringify(jsonData));
                console.log(this.state.selectedFile);
                        PostService.createPost(formData)
                            .then((res) => {
                                console.log(res);
                                self.setState({
                                    isShowModal: !self.state.isShowModal,
                                    contentMsgAlert: {
                                        title: "Server Response",
                                        text: MessageResponse.SUCCESS_ADD_DATA,
                                        icon: SweetAlertInfo.ICON[0],
                                        confirmButtonText: 'Close'
            
                                    }
                                })
            
            
                            })
            
                            .catch(function (err) {
                                console.log(err.response);
                                if(err.response){
                                self.setState({
                                    isShowModal: !self.state.isShowModal,
                                    contentMsgAlert: {
                                        title: err.response.data.reasonCode,
                                        text: err.response.data.description || MessageResponse.FILE_IS_NOT_NULL,
                                        icon: 'error',
                                        confirmButtonText: 'Close'
            
                                    }
                                })
                            }
                            else if (err.request) {
                                Swal.fire(MessageResponse.ERROR_CONNECT_CALL_API , '', 'error')
            
                              } 
                            else {
                                Swal.fire(MessageResponse.ERROR_CONNECT_CALL_API , '', 'error')
                            }  
            
                            }); 
            }
            // Case Update
            else {
                // jsonData = {...this.state.dataInit};
                // jsonData.idPost = this.props.idPost;
                // delete jsonData.createDate;
                // delete jsonData.modifyDate;
                jsonData = {...jsonData}
                jsonData.idPost = this.props.idPost;
                jsonData.imgName= this.state.dataInit.imgName;
                jsonData.imgURL= this.state.dataInit.imgURL;
                if( this.state.selectedFile !== {}){
                formData.append('file', this.state.selectedFile)
                }
                console.log( this.state.selectedFile);
                formData.append('jsonData', JSON.stringify(jsonData))
                formData.append("idPost",this.props.idPost)

                console.log(JSON.stringify(jsonData));
                console.log(this.state.selectedFile);

                PostService.updatePost(formData,this.props.idPost)
                .then((res) => {
                    console.log(res);
                    self.setState({
                        isShowModal: !self.state.isShowModal,
                        contentMsgAlert: {
                            title: "Server Response",
                            text: MessageResponse.SUCCESS_UPDATE_POST,
                            icon: SweetAlertInfo.ICON[0],
                            confirmButtonText: 'Close'

                        }
                    })


                })

                .catch(function (err) {
                    console.log(err.response);
                    if(err.response){
                    self.setState({
                        isShowModal: !self.state.isShowModal,
                        contentMsgAlert: {
                            title: err.response.data.reasonCode,
                            text: err.response.data.description || MessageResponse.ERROR_CONNECT_CALL_API ,
                            icon: 'error',
                            confirmButtonText: 'Close'

                        }
                    })
                }

                }); 

            }
          
        };


        return (

            <Form ref={this.formRef}
                name="validate_other"
                {...formItemLayout}

                initialValues={{}}
                onFinish={onFinish}
            >

                {/* // title         */}
                <Form.Item label="Title"  >
                    <Form.Item

                        noStyle
                        name="title"
                        rules={[{ required: true, message: 'Không được bỏ trống tiêu đề bài post !' }]}
                    >

                        <Input placeholder="Nhập tiêu đề bài post" />
                    </Form.Item>
                </Form.Item>


                {/* // Create By */}
                <Form.Item label="CreateBy" >
                    <Form.Item

                        name="createBy"
                        rules={[{ required: false, message: 'Không được bỏ trống tác giả !' }]}
                    >
                        <Input placeholder="Nhập tác giả bài post" />
                    </Form.Item>
                </Form.Item>

                {/* Nội dung    */}
                <Form.Item label="Content" >
                    <Form.Item

                        name="content"
                        rules={[{ required: false, message: 'Nội dung bai post!' }]}
                    >
                        <Input.TextArea placeholder="Nhập nội dung bài post" />
                    </Form.Item>
                </Form.Item>
                {/* Mô tả */}
                <Form.Item label="Description"
                >
                    <Form.Item
                        rules={[{ required: false, message: 'Không được bỏ trống mô tả bài post!' }]}

                        name="description"

                    >
                        <Input placeholder="Nhập tóm tắt bài post" />
                    </Form.Item>
                </Form.Item>

                {/* Danh mục */}
                <Form.Item label="Category">
                    <Form.Item
                        name="Category"

                        hasFeedback
                        rules={[
                            {
                                required: false,
                                message: 'Chọn danh mục của bài post!',
                            },
                        ]}
                    >

                        <Select placeholder="Nhap danh muc của bài post">
                            {
                                this.state.posts.map((item, key) => {
                                    return (
                                        <Option key={key} value={item.idCategory}>{item.name}</Option>
                                    )
                                })
                            }
                        </Select>
                    </Form.Item>
                </Form.Item>


                {/* // upload ảnh */}
                <Form.Item label="Upload File">
                    <Form.Item
                        name="file"

                        extra="Upload ảnh nhỏ hơn 1MB"
                        rules={[{ required: false, message: 'Upload ảnh cho bài viết!' }]}

                    >
                        <Input type="file" placeholder="Upload image" onChange={this.onFileChange} />
                      
                        {/* <input type="file" className="custom-file-input" onChange={this.onFileChange} id="actual-btn" /> */}
                        {/* <span className="custom-file-label" >Choose file</span>  */}
                        {/* <span >{this.state.fileName}</span> 
                         {/* <img alt="upload file" id="target" src={this.state.imgShow} /> */}


                    </Form.Item>
                </Form.Item>
                <Form.Item label="Preview image" >
                  <Image className="text-center"
                            width={200}
                            src={this.state.imgShow}
                        />
                </Form.Item>
                {/* // submit */}
                <Form.Item
                    wrapperCol={{
                        span: 12,
                        offset: 6,
                    }}
                >
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                    {
                        this.state.isShowModal === true ? (
                            <Message parentCallback={(childData) => { this.callbackFunction(childData) }}
                                title={this.state.contentMsgAlert.title}
                                text={this.state.contentMsgAlert.text}
                                icon={this.state.contentMsgAlert.icon} />
                        ) : ""
                    }
                </Form.Item>
                {/* <img alt="" id="target" src={this.state.imgShow} /> */}

            </Form>

        )
    }
}
