import BootstrapTable from 'react-bootstrap-table-next';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css';
import 'react-bootstrap-table2-filter/dist/react-bootstrap-table2-filter.min.css';
import ToolkitProvider, { Search } from 'react-bootstrap-table2-toolkit';
import React, { useState, useEffect } from 'react';
import filterFactory  from 'react-bootstrap-table2-filter';
import { Link } from 'react-router-dom';
import paginationFactory from 'react-bootstrap-table2-paginator';
import { PostConstant } from '../../constant/Constant';
import PostService from '../../service/PostService';
import Swal from 'sweetalert2'


function ManagePost(props) {

    const [data, setData] = useState([])
    const [filters, setFilters] = useState({
      currentPage: 0,
      pageSize: PostConstant.TOTAL_PAGE_LIMIT, // total page,
    });

    useEffect(() => {

        async function getAllBook() {
          PostService.getAllPostWithPagination(0,PostConstant.TOTAL_PAGE_LIMIT)
            .then((res) => {
              setData(res.data.data)
              PostService.getAllPostWithPagination(0,res.data.paging.totalElement)
              .then((res) => {
                setData(res.data.data)
  
              })
            })
           
            .catch(function (err) {
              console.log(err.messages);
            })
        }


        getAllBook();
    
    
      }, [])
      function setDataTable() {
        return (data.map((item, index) => {
          return (
            { 
              key : {index},
              id: item.idPost,
              title: item.title , 
              createDate: item.createDate,
              description: item.description,
              categoryId : item.categoryId,
              img : <img className="img-fluid" id={item.idName} src={item.imgURL} alt="img-blog" />,
              update :  
              <Link 
              to = {{
                  pathname: '/add-post/'+  item.idPost
              }}
              >
              <button className="btn btn-outline-info"  >Update Post</button>
              </Link> ,

              delete :  
            
              <button className="btn btn-warning" onClick={ () => { handleRemove(item.idPost) }}  >Delete Post</button>
             


             }
          )
        })
        )
      }
     
      

  const products = setDataTable();
 

  const columns = [
    { dataField: 'id', text: 'idPost', sort: true},
    { dataField: 'title',
     text: 'title', 
     sort: true  },
    { dataField: 'createDate', text: 'createDate', sort: true },
    { dataField: 'description', text: 'description', sort: true },
    { dataField: 'categoryId', text: 'category', sort: true },
    { dataField: 'img', text: 'img_post', sort: true },
    { dataField: 'update', text: 'Update' },
    { dataField: 'delete', text: 'Delete' },
  ];
  const defaultSorted = [{
    dataField: 'title',
    order: 'desc'
  }];

  const { SearchBar, ClearSearchButton } = Search;

  const pagination = paginationFactory({
    page: 1, // page đang đứng
    sizePerPage: PostConstant.TOTAL_PAGE_LIMIT, // số bản ghi 1 page
    lastPageText: '>>',
    firstPageText: '<<',
    nextPageText: '>',
    prePageText: '<',
    showTotal: true,
    alwaysShowAllBtns: true,
    onPageChange: function (page, sizePerPage) {
      setFilters({
        ...filters,
        currentPage: page-1,
        pageSize: sizePerPage

      })

    },
    onSizePerPageChange: function (page, sizePerPage) {
      setFilters({
        ...filters,
        currentPage: page-1,
        pageSize: sizePerPage

      })
      console.log('page', page);
      console.log('sizePerPage', sizePerPage);
    }
  });


  const MyExportCSV = (props) => {
    const handleClick = () => {
      props.onExport();
    };
    return (
      <div>
        <button className="btn btn-success" onClick={handleClick}>Export to CSV</button>
      </div>
    );
  };

  function handleRemove(idPost){

    Swal.fire({
      title: 'Are you sure?',
      text: "Delete Post!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) { // remove
       
        PostService.removePost(idPost)
        .then((response) => {
         setData(data.filter(item => item.idPost !== idPost))
          Swal.fire(
            'Deleted!',
            'Remove Success.',
            'success'
          )

        })
        .catch(function (err) {
          Swal.fire(err.response.data.reasonCode,  err.response.data.description, 'error')
        });
       
       
      }
    })

  }

    return (
     
        <div className="list--post">
        <h3>List Post</h3>
          
          <Link
            to={{pathname: '/add-post'}}
            className="btn btn-outline-primary"
            >ADD Book
                      </Link>
    
        <ToolkitProvider
          bootstrap4
          keyField='id'
          data={products}
          columns={columns}
          search
          exportCSV
  
        >
  
          {
            props => (
              <div>
                <h6>Nhập tiêu đề bài post :</h6>
                <SearchBar {...props.searchProps} />
                <ClearSearchButton {...props.searchProps} />
  
                <MyExportCSV {...props.csvProps} />
                <BootstrapTable
               
         
                  hover
                  bordered={ false }
                  // selectRow={ { mode: 'checkbox' } }
                  classes = "table__style"
                  defaultSorted={defaultSorted}
                  pagination={pagination}
                  filter={ filterFactory() }
                  {...props.baseProps}
                />
              </div>
            )
          }
  
        </ToolkitProvider>
      </div>

    )
}



export default ManagePost

