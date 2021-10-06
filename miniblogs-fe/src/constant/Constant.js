var   BASE_URL = 'http://localhost:8080/speedhome/backend/v1'

export const PostConstant = {

    POST_ENDPOINTS : BASE_URL + '/posts' ,
    CONTACT_ENDPOINTS : BASE_URL + '/contacts' ,
    CATEGORY_ENDPOINT : BASE_URL + "/categories",
    TOTAL_PAGE_LIMIT : 3,
    METHOD  : ['get','post','put','delete'],
     HEADERS :  { 
        'Accept': 'application/json', 
        'apikey': '691c5597-e7d2-4c06-af49-f9369b367783'
      }


}
