import React, { Component } from 'react'
import { DebounceInput } from 'react-debounce-input'; // npm i react-debounce-input
import Swal from 'sweetalert2';
import { PostConstant } from '../../constant/Constant';
import { MessageResponse } from '../../constant/ResponseMessage';
import PostService from '../../service/PostService';

export default class SearchPost extends Component {
   
    constructor(props) {
        super(props);
        this.state = {

            keyword: '',
            dataSearch: {},

        };
    }

       // gửi lên component cha
       sendDataFilter = (data) => {
        this.props.sendDataFilter(data);
    }
  

    handleInputChange = (e) => {
        if (e.target.value !== '') {
            PostService.searchPost(0,PostConstant.TOTAL_PAGE_LIMIT,e.target.value)
            .then((res) => {
              
                if (res.data.length !==  0 ) {
                    this.setState({
                        dataSearch: res.data.data,
                        currentPage : res.data.paging.currentPage,
                        keyword: e.target.value
                    })
                    this.sendDataFilter(res.data); // Async
                }

                else {
                    this.setState({                  
                        keyword: e.target.value ,
                        dataSearch : {}
                    })
                this.sendDataFilter([]);
                    // hiển thị thông báo
                    Swal.fire("ALERT", "Không tìm thấy dữ liệu"  , 'info')

                }
            })
            .catch(function (error) {
               if(error.response)
                Swal.fire("ALERT",  error.response.data.description || MessageResponse.ERROR_CONNECT_CALL_API , 'warning')
            });

           
        }



    }

    handlerMessage = (e) => {
        if (e === '') {
            return (
                Swal.fire("ALERT",  "Nhập để tìm kiếm" , 'warning')
            )
        }
        else if (this.state.dataSearch.length === undefined){
            return (

                Swal.fire("ALERT",  "Không có data", 'warning')
            )
        }
        else 
        {
           
            PostService.searchPost(0,PostConstant.TOTAL_PAGE_LIMIT,e)
            .then((res) => {
              
                if (res.data.length !==  0 &&  res.data.length !== undefined ) {
                    this.setState({
                        dataSearch: res.data.data,
                        currentPage : res.data.paging.currentPage,
                        keyword: e
                    })
                    this.sendDataFilter(res.data); // Async
                }
    
                else {
                   
                    this.setState({                  
                        keyword: e ,
                        dataSearch : {}
                    })
                    this.sendDataFilter([]);
                    // hiển thị thông báo
                    Swal.fire("ALERT", "Không tìm thấy dữ liệu"  , 'info')
    
                }
            })
            .catch(function (error) {
            //    if(error.response)
            //     Swal.fire("ALERT",  error.response.data.description  , 'warning')
            //     else 
                Swal.fire("ALERT", MessageResponse.ERROR_CONNECT_CALL_API , 'warning')

            });
    
        }
        
        

    }

    submitSearch = () => {

        return this.handlerMessage(this.state.keyword);
    }

    render() {
        return (
            <div>

            <div className="input-group text-center">
                {/* kĩ thuật denounce */}
                <DebounceInput
                    debounceTimeout={1000}
                    minLength={1}
                    forceNotifyByEnter={true}
                    onChange={(e) => { this.handleInputChange(e) }}
                    type="search" className="form-control rounded" placeholder="Nhập nội dung cần tìm" aria-label="Search"
                />
               

                <button data-toggle="modal" data-target="#exampleModalCenter"
                    onClick={(e) => { this.submitSearch(e) }}
                    type="button" className="btn btn-outline-primary">

                    <i className="fas fa-search"></i>
                </button>
        {     () => { this.handlerMessage(this.state.keyword)} }
      

            </div>
        </div>
        )
    }
}
