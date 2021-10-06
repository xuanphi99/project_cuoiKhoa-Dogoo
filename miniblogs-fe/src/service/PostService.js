import axios from 'axios';
import {PostConstant } from '../constant/Constant'
// http://localhost:8080/speedhome/backend/v1/posts
//http://localhost:8080/speedhome/backend/v1/posts?currentPage=1&pageSize=5
const POSTS_API_BASE_URL = PostConstant.POST_ENDPOINTS;
const CATEGORIES_API_BASE_URL = PostConstant.CATEGORY_ENDPOINT;
const CONTACT_API_BASE_URL = PostConstant.CONTACT_ENDPOINTS;


function getConfig(method,url,params,headers){

    return  {
      method : method,
      url : url,
      params : params,
      headers : headers
    };

}

class PostService {
    getAllPostWithPagination(currentPage , pageSize){
        return axios(getConfig(
          PostConstant.METHOD[0],
          POSTS_API_BASE_URL,
          {
            'currentPage' : currentPage,
            'pageSize' : pageSize
          },
          PostConstant.HEADERS
          ))
    }
    searchPost(currentPage , pageSize,keyWord){
      return axios(getConfig(
        PostConstant.METHOD[0],
        POSTS_API_BASE_URL,
        {
          'currentPage' : currentPage,
          'pageSize' : pageSize,
          'search' : keyWord
        },
        PostConstant.HEADERS
        ))
    }

    getDetailPost(idPost){
      return axios(getConfig(
        PostConstant.METHOD[0],
        POSTS_API_BASE_URL+"/"+idPost,
        {},
        PostConstant.HEADERS

      ))
    }

    getAllCategory(){
      return axios(getConfig(
        PostConstant.METHOD[0],
        CATEGORIES_API_BASE_URL,
        {},
        PostConstant.HEADERS

      ))
    }

    createPost(data){

      return axios.post(POSTS_API_BASE_URL,data,
        {
          'headers' : {
             'Accept': 'application/json', 
             "content-type": 'multipart/form-data',
             'apikey': '691c5597-e7d2-4c06-af49-f9369b367783',
 
           }
         }
        
        )  
 
    }

    createContact(data){

      return axios.post(CONTACT_API_BASE_URL,data,
        {
          'headers' : {
             'Accept': 'application/json', 
             "content-type": 'application/json',
             'apikey': '691c5597-e7d2-4c06-af49-f9369b367783',
 
           }
         }
        
        )  
 
    }

    updatePost(data,idPost){

      return axios.put(POSTS_API_BASE_URL+"/"+idPost,data,
        {
          'headers' : {
             'Accept': 'application/json', 
             "content-type": 'multipart/form-data',
             'apikey': '691c5597-e7d2-4c06-af49-f9369b367783',
 
           }
         }
        
        )  
 
    }


    removePost(idPost){

      return axios.delete(POSTS_API_BASE_URL+"/"+idPost,
        {
          'headers' : {
             'Accept': 'application/json', 
             'apikey': '691c5597-e7d2-4c06-af49-f9369b367783',
 
           }
         }
        
        )  
 
    }



}
export default new PostService()
