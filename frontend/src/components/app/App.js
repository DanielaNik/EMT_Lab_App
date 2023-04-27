import './App.css';
import {Component} from "react";
import React from "react";
import Books from  '../books/books'
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import BookService from "../../repository/bookRepository";
import Categories from "../categories/categories";
import Header from "../header/header";
import AddBook from "../books/addBook";
import EditBook from "../books/editBook"

class App extends Component{
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router basename={"/"}>
            <Header></Header>
                <Routes>
                    <Route path={"/books/add"} element={<AddBook
                        books={this.state.books}
                        categories={this.state.categories}
                        onAddBook={this.addBook}/>}/>
                    <Route path={"/books/edit/:id"} element={<EditBook
                        books={this.state.books}
                        categories={this.state.categories}
                        onEditBook={this.editBook}
                        book={this.state.selectedBook}
                    />}/>
                    <Route path={"/books"} element={<Books books={this.state.books}
                                                           categories={this.state.categories}
                                                           onDelete={this.deleteBook}
                                                           onEdit={this.getBook} onBorrow={this.borrowBook}/>} />
                    <Route path={"/categories"} element={<Categories categories={this.state.categories}/>}/>
                    <Route path={"/"} element={<Books books={this.state.books} categories={this.state.categories} onDelete={this.deleteBook} onEdit={this.getBook} onBorrow={this.borrowBook}/>} />
                    <Route path="*" element={<Navigate to="/books" />}/>
                </Routes>
            </Router>
        );
    }


    loadBooks = () => {
        BookService.fetchBooks().then((data) => {
            this.setState({
                books: data.data
            })
        })
    }

    loadCategories = () => {
        BookService.fetchCategories().then((data) => {
            this.setState({
                categories: data.data
            })
        })
    }

    deleteBook = (id) => {
        BookService.deleteBook(id).then(() => {
                this.loadBooks();
        })
    }

    editBook = (id,name,bookCategory,authorName, authorSurname, availableCopies) => {
        BookService.editBook(id,name,bookCategory,authorName, authorSurname, availableCopies).then(() => {
            this.loadBooks();
        })
    }

    addBook = (name,bookCategory,authorName, authorSurname, availableCopies) =>{
        BookService.addBook(name,bookCategory,authorName,authorSurname,availableCopies)
            .then(() => this.loadBooks())
    }

    getBook = (id) => {
        BookService.getBook(id).then((data) =>{
        this.setState({
            selectedBook:data.data
             })
        })
    }

    borrowBook = (id) => {
        BookService.borrowBook(id).then(() => {
            this.loadBooks();
        })
    }

    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
    }
}

export default App;
