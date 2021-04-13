import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom"
import LibraryService from "../../repository/libraryRepository";
import Header from "../Header/header"
import Books from "../Books/BooksList/books"
import AddBook from "../Books/BooksAdd/addBook";
import EditBook from "../Books/BooksEdit/editBook";

class App extends Component{
  constructor(props) {
    super(props);
    this.state = {
        books : [],
        authors : [],
        selectedBook : []
    }
  }


  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
                <Route path={"/books/add"} exact render={() => <AddBook authors = {this.state.authors} onAddBook = {this.addNewBook}/>}/>
                <Route path={"/books/edit/:id"} exact render={() => <EditBook authors = {this.state.authors} onEditBook = {this.editBook} book = {this.state.selectedBook}/>} />
                <Route path={"/books"} exact render={() => <Books books={this.state.books} onDelete = {this.deleteBook} markTaken = {this.markAsTaken} onSelected = {this.getBook} />}/>
                <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>
    );
  }

  componentDidMount() {
    this.loadBooks();
    this.loadAuthors();
  }

  addNewBook = (name, quantity, category, author) => {
      LibraryService.addBook(name, quantity, category, author).then( () => {
          this.loadBooks();
      })
  }

  editBook = (id, name, quantity, category, author) => {
      LibraryService.editBook(id,name,category,quantity,author).then(() => {
         this.loadBooks();
      });
  }

  loadAuthors = () => {
      LibraryService.fetchAuthors().then((data) => {
         this.setState({
             authors : data.data
         });
      });
  }

  loadBooks = () => {
    LibraryService.fetchBooks().then((data) => {
      this.setState({
        books : data.data
      });
    });
  }

  markAsTaken = (id) => {
      LibraryService.markAsTaken(id).then(() => {
          this.loadBooks();
      });
  }

  deleteBook = (id) => {
      LibraryService.deleteBook(id).then(() => {
          this.loadBooks();
      });
  }

  getBook = (id) => {
      LibraryService.getBook(id).then((data) => {
          this.setState({
              selectedBook : data.data
          })
      });
  }

}

export default App;
