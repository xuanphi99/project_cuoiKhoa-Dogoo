import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import './App.css';
import About from './components/about/About';
import Contact from './components/contact/Contact';
import Home from './components/home/Home';
import Menu from './components/layout/Menu';
import PageNotFound from './components/layout/PageNotFound';
import ManagePost from './components/admin/ManagePost';
import CreateNewPost from './components/admin/CreateNewPost';
import BlogItem from './components/Blogs/BlogItem';
import UpdatePost from './components/admin/UpdatePost';


function App() {

  return (

    <Router    >
      {/* Header */}
      <div className="container menu">
        <Menu />

      </div>
      <div className="container-fluid body--page">
      <div className="container body--page__center">
        <Switch >

          <Route path="/" exact component={Home} />
          <Route path="/home" exact component={Home} />
          <Route path="/about" exact component={About} />
          <Route path="/contact" exact component={Contact} />
          <Route path="/blog-detail/:id" exact component={BlogItem} />
          <Route path="/admin-post" exact component={ManagePost} />
          <Route path="/add-post" exact component={CreateNewPost} />
          <Route path="/add-post/:id" exact component={UpdatePost} />
          <Route path="*" exact component={PageNotFound} />

        </Switch>
        </div>
      </div>


    </Router>


  );
}

export default App;
